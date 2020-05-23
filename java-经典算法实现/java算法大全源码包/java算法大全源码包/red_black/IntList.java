// IntList class
//    Main purpose of this class is to assemble lists of integers for
//    testing
//
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class IntList {
	private int nodes_array[];
	DataInputStream inStream;
	private int num = 0;
	
	public IntList( URL url ) {
		try {
			URLConnection urlc = url.openConnection();
			inStream = new DataInputStream( urlc.getInputStream() );
			num = loadNodes(inStream);
			}
		catch( IOException e ) {
			System.out.println("IntList: URL connection error");
			}
		}
	
	private int loadNodes(DataInputStream inStream) {
		int n_nodes = 0, cnt = 0;
		String line, item1;
		
		System.out.println("Load_Nodes: entry" );
		try {
			if ((line = inStream.readLine()) != null) {
				System.out.println("Load_Nodes: line [" + line + "]" );
				StringTokenizer Data = new StringTokenizer(line, " ");
				item1 = Data.nextToken();
				n_nodes = Integer.parseInt( item1 );
				System.out.println("instream is "+ n_nodes);		 
				nodes_array = new int[n_nodes];
		
				while(( cnt < n_nodes ) && ((line = inStream.readLine()) != null) ) {
					System.out.println("Load_Nodes: line [" + line + "]" );
					Data = new StringTokenizer(line, " ");
					item1 = Data.nextToken();
					nodes_array[cnt] = Integer.parseInt(item1);
					cnt++;	
					}
				
				}
			}	
		catch (IOException e) {
			System.err.println("error in file" +e.toString());
			System.exit(1);
			}
		return n_nodes;	
		}
	public int elementAt( int k ) {
		if ( k < nodes_array.length ) {
			return nodes_array[k];
			}
		else return -99;
		}

	public int size() {
		return num;
		}
	}
