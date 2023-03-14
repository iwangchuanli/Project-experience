/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.security;
import java.security.*;
import java.io.*;
import java.util.*;

/**
 * This program creates a manifest file for the specified files, or verifies
 * an existing manifest file.  By default the manifest file is named
 * MANIFEST, but the -m option can be used to override this.  The -v
 * option specifies that the manifest should be verified.  Verification is
 * also the default option if no files are specified.
 **/
public class Manifest {
    public static void main(String[] args) throws Exception {
	// Set the default values of the command-line arguments
	boolean verify = false;             // Verify manifest or create one?
	String manifestfile = "MANIFEST";   // Manifest file name
	String digestAlgorithm = "MD5";     // Algorithm for message digests
	String signername = null;           // Signer. No sig. by default
	String signatureAlgorithm = "DSA";  // Algorithm for digital sig.
	String password = null;             // Private keys are protected
	File keystoreFile = null;           // Where are keys stored
	String keystoreType = null;         // What kind of keystore
	String keystorePassword = null;     // How to access keystore
	List filelist = new ArrayList();    // The files to digest
	
	// Parse the command-line arguments, overriding the defaults above
	for(int i = 0; i < args.length; i++) {
	    if (args[i].equals("-v")) verify = true;
	    else if (args[i].equals("-m")) manifestfile = args[++i];
	    else if (args[i].equals("-da")&& !verify)
		digestAlgorithm = args[++i];
	    else if (args[i].equals("-s")&& !verify)
		signername = args[++i];
	    else if (args[i].equals("-sa")&& !verify) 
		signatureAlgorithm = args[++i];
	    else if (args[i].equals("-p"))
		password = args[++i];
	    else if (args[i].equals("-keystore"))
		keystoreFile = new File(args[++i]);
	    else if (args[i].equals("-keystoreType"))
		keystoreType = args[++i];
	    else if (args[i].equals("-keystorePassword"))
		keystorePassword = args[++i];

	    else if (!verify) filelist.add(args[i]);
	    else throw new IllegalArgumentException(args[i]);
	}

	// If certain arguments weren't supplied, get default values.
	if (keystoreFile == null) {
	    File dir = new File(System.getProperty("user.home"));
	    keystoreFile = new File(dir, ".keystore");
	}
	if (keystoreType == null) keystoreType = KeyStore.getDefaultType();
	if (keystorePassword == null) keystorePassword = password;

	if (!verify && signername != null && password == null) {
	    System.out.println("Use -p to specify a password.");
	    return;
	}

	// Get the keystore we'll use for signing or verifying signatures
	// If no password was provided, then assume we won't be dealing with 
	// signatures, and skip the keystore.
	KeyStore keystore = null;
	if (keystorePassword != null) {
	    keystore = KeyStore.getInstance(keystoreType);
	    InputStream in =
		new BufferedInputStream(new FileInputStream(keystoreFile));
	    keystore.load(in, keystorePassword.toCharArray());
	}

	// If -v was specified or no file were given, verify a manifest
	// Otherwise, create a new manifest for the specified files
	if (verify || (filelist.size() == 0)) verify(manifestfile, keystore);
	else create(manifestfile, digestAlgorithm,
		    signername, signatureAlgorithm,
		    keystore, password, filelist);
    }
    
    /**
     * This method creates a manifest file with the specified name, for
     * the specified vector of files, using the named message digest
     * algorithm.  If signername is non-null, it adds a digital signature
     * to the manifest, using the named signature algorithm.  This method can
     * throw a bunch of exceptions.
     **/
    public static void create(String manifestfile, String digestAlgorithm, 
			      String signername, String signatureAlgorithm,
			      KeyStore keystore, String password,
			      List filelist)
	throws NoSuchAlgorithmException, InvalidKeyException, 
	       SignatureException, KeyStoreException,
	       UnrecoverableKeyException, IOException 
    {
        // For computing a signature, we have to process the files in a fixed,
        // repeatable order, so sort them alphabetically.
	Collections.sort(filelist);
	int numfiles = filelist.size();
        
        Properties manifest = new Properties(), metadata = new Properties();
        MessageDigest md = MessageDigest.getInstance(digestAlgorithm);
        Signature signature = null;
        byte[] digest;
        
        // If a signer name was specified, then prepare to sign the manifest
        if (signername != null) {
            // Get a Signature object
            signature = Signature.getInstance(signatureAlgorithm);

	    // Look up the private key of the signer from the keystore
	    PrivateKey key = (PrivateKey)
		keystore.getKey(signername, password.toCharArray());

            // No prepare to create a signature for the specified signer
            signature.initSign(key);
        }
        
        // Now, loop through the files, in a well-known alphabetical order
        System.out.print("Computing message digests");
        for(int i = 0; i < numfiles; i++) {
	    String filename = (String)filelist.get(i);
            // Compute the digest for each, and skip files that don't exist.
            try { digest = getFileDigest(filename, md); } 
            catch (IOException e) {
                System.err.println("\nSkipping " + filename + ": " + e);
                continue;
            }
            // If we're computing a signature, use the bytes of the filename 
            // and of the digest as part of the data to sign.
            if (signature != null) {
                signature.update(filename.getBytes());
                signature.update(digest);
            }
            // Store the filename and the encoded digest bytes in the manifest
            manifest.put(filename, hexEncode(digest));
            System.out.print('.');
            System.out.flush();
        }
        
        // If a signer was specified, compute signature for the manifest
        byte[] signaturebytes = null;
        if (signature != null) {
            System.out.print("done\nComputing digital signature...");
            System.out.flush();
            
            // Compute the digital signature by encrypting a message digest of
            // all the bytes passed to the update() method using the private
            // key of the signer.  This is a time consuming operation.
            signaturebytes = signature.sign();
        }
	
        // Tell the user what comes next
        System.out.print("done\nWriting manifest...");
        System.out.flush();
	
        // Store some metadata about this manifest, including the name of the
        // message digest algorithm it uses
        metadata.put("__META.DIGESTALGORITHM", digestAlgorithm);
        // If we're signing the manifest, store some more metadata
        if (signername != null) {
            // Store the name of the signer
            metadata.put("__META.SIGNER", signername);
            // Store the name of the algorithm
            metadata.put("__META.SIGNATUREALGORITHM", signatureAlgorithm);
            // And generate the signature, encode it, and store it
            metadata.put("__META.SIGNATURE", hexEncode(signaturebytes));
        }
	
        // Now, save the manifest data and the metadata to the manifest file
        FileOutputStream f = new FileOutputStream(manifestfile);
        manifest.store(f, "Manifest message digests");
        metadata.store(f, "Manifest metadata");
        System.out.println("done");
    }
    
    /**
     * This method verifies the digital signature of the named manifest
     * file, if it has one, and if that verification succeeds, it verifies
     * the message digest of each file in filelist that is also named in the
     * manifest.  This method can throw a bunch of exceptions
     **/
    public static void verify(String manifestfile, KeyStore keystore) 
	throws NoSuchAlgorithmException, SignatureException, 
	       InvalidKeyException, KeyStoreException, IOException
    {
        Properties manifest = new Properties();
        manifest.load(new FileInputStream(manifestfile));
        String digestAlgorithm =
	    manifest.getProperty("__META.DIGESTALGORITHM");
        String signername = manifest.getProperty("__META.SIGNER");
        String signatureAlgorithm = 
            manifest.getProperty("__META.SIGNATUREALGORITHM");
        String hexsignature = manifest.getProperty("__META.SIGNATURE");
	
        // Get a list of filenames in the manifest.  
	List files = new ArrayList();
        Enumeration names = manifest.propertyNames();
        while(names.hasMoreElements()) {
            String s = (String)names.nextElement();
            if (!s.startsWith("__META")) files.add(s);
        }
	int numfiles = files.size();
	
	// If we've got a signature but no keystore, warn the user
	if (signername != null && keystore == null)
	    System.out.println("Can't verify digital signature without " +
			       "a keystore.");

        // If the manifest contained metadata about a digital signature, then
        // verify that signature first
        if (signername != null && keystore != null) {
            System.out.print("Verifying digital signature...");
            System.out.flush();

            // To verify the signature, we must process the files in exactly
            // the same order we did when we created the signature.  We
            // guarantee this order by sorting the filenames.
	    Collections.sort(files);
	    
            // Create a Signature object to do signature verification with.
	    // Initialize it with the signer's public key from the keystore
            Signature signature = Signature.getInstance(signatureAlgorithm);
	    PublicKey publickey =
		keystore.getCertificate(signername).getPublicKey();
            signature.initVerify(publickey);
	    
            // Now loop through these files in their known sorted order For
            // each one, send the bytes of the filename and of the digest to
            // the signature object for use in computing the signature.  It is
            // important that this be done in exactly the same order when
            // verifying the signature as it was done when creating the
            // signature.
            for(int i = 0; i < numfiles; i++) {
		String filename = (String) files.get(i);
                signature.update(filename.getBytes());
                signature.update(hexDecode(manifest.getProperty(filename)));
            }
	    
            // Now decode the signature read from the manifest file and pass
            // it to the verify() method of the signature object.  If the
            // signature is not verified, print an error message and exit.
            if (!signature.verify(hexDecode(hexsignature))) {
                System.out.println("\nManifest has an invalid signature");
                System.exit(0);
            }
            
            // Tell the user we're done with this lengthy computation
            System.out.println("verified.");
        }

        // Tell the user we're starting the next phase of verification
        System.out.print("Verifying file message digests");
        System.out.flush();
	
        // Get a MessageDigest object to compute digests
        MessageDigest md = MessageDigest.getInstance(digestAlgorithm);
        // Loop through all files
        for(int i = 0; i < numfiles; i++) {
	    String filename = (String)files.get(i);
            // Look up the encoded digest from the manifest file
            String hexdigest = manifest.getProperty(filename);
            // Compute the digest for the file.
            byte[] digest;
            try { digest = getFileDigest(filename, md); } 
            catch (IOException e) {
                System.out.println("\nSkipping " + filename + ": " + e);
                continue;
            }

            // Encode the computed digest and compare it to the encoded digest
            // from the manifest.  If they are not equal, print an error
            // message.
            if (!hexdigest.equals(hexEncode(digest)))
                System.out.println("\nFile '" + filename +
				   "' failed verification.");
	    
            // Send one dot of output for each file we process.  Since
            // computing message digests takes some time, this lets the user
            // know that the program is functioning and making progress
            System.out.print("."); 
            System.out.flush();
        }
        // And tell the user we're done with verification.
        System.out.println("done.");
    }
    
    /**
     * This convenience method is used by both create() and verify().  It
     * reads the contents of a named file and computes a message digest
     * for it, using the specified MessageDigest object.
     **/
    public static byte[] getFileDigest(String filename, MessageDigest md) 
	throws IOException {
        // Make sure there is nothing left behind in the MessageDigest
        md.reset();
	
        // Create a stream to read from the file and compute the digest
        DigestInputStream in = 
            new DigestInputStream(new FileInputStream(filename),md);
	
        // Read to the end of the file, discarding everything we read.
        // The DigestInputStream automatically passes all the bytes read to
        // the update() method of the MessageDigest
        while(in.read(buffer) != -1) /* do nothing */ ;
	
        // Finally, compute and return the digest value.
        return md.digest();
    }
    
    /** This static buffer is used by getFileDigest() above */
    public static byte[] buffer = new byte[4096];
    
    /** This array is used to convert from bytes to hexadecimal numbers */
    static final char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7',
				   '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    
    /**
     * A convenience method to convert an array of bytes to a String.  We do
     * this simply by converting each byte to two hexadecimal digits.
     * Something like Base 64 encoding is more compact, but harder to encode.
     **/
    public static String hexEncode(byte[] bytes) {
        StringBuffer s = new StringBuffer(bytes.length * 2);
        for(int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            s.append(digits[(b& 0xf0) >> 4]);
            s.append(digits[b& 0x0f]);
        }
        return s.toString();
    }
    
    /**
     * A convenience method to convert in the other direction, from a string
     * of hexadecimal digits to an array of bytes.
     **/
    public static byte[] hexDecode(String s) throws IllegalArgumentException {
        try {
            int len = s.length();
            byte[] r = new byte[len/2];
            for(int i = 0; i < r.length; i++) {
                int digit1 = s.charAt(i*2), digit2 = s.charAt(i*2 + 1);
                if ((digit1 >= '0')&& (digit1 <= '9')) digit1 -= '0';
                else if ((digit1 >= 'a')&& (digit1 <= 'f')) digit1 -= 'a' - 10;
                if ((digit2 >= '0')&& (digit2 <= '9')) digit2 -= '0';
                else if ((digit2 >= 'a')&& (digit2 <= 'f')) digit2 -= 'a' - 10;
                r[i] = (byte)((digit1 << 4) + digit2);
            }
            return r;
        }
        catch (Exception e) {
            throw new IllegalArgumentException("hexDecode(): invalid input");
        }
    }
}
