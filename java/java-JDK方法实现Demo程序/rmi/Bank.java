/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.rmi;
import java.rmi.*;
import java.util.List;

/**
 * This class is a placeholder that simply contains other classes and 
 * for interfaces remote banking.
 **/
public class Bank {
    /**
     * This is the interface that defines the exported methods of the 
     * bank server.
     **/
    public interface RemoteBank extends Remote {
        /** Open a new account, with the specified name and password */
        public void openAccount(String name, String password) 
	    throws RemoteException, BankingException;
	
        /** Close the named account */
        public FunnyMoney closeAccount(String name, String password) 
	    throws RemoteException, BankingException;
	
        /** Deposit money into the named account */
        public void deposit(String name, String password, FunnyMoney money)
	    throws RemoteException, BankingException;
	
        /** Withdraw the specified amount of money from the named account */
        public FunnyMoney withdraw(String name, String password, int amount) 
	    throws RemoteException, BankingException;
	
        /** Return the amount of money in the named account */
        public int getBalance(String name, String password) 
	    throws RemoteException, BankingException;
	
        /** 
	 * Return a List of Strings that list the transaction history 
	 * of the named account 
	 **/
        public List getTransactionHistory(String name, String password) 
	    throws RemoteException, BankingException;
    }
    
    /**
     * This simple class represents a monetary amount.  This implementation
     * is really nothing more than a wrapper around an integer.  It is a useful
     * to demonstrate that RMI can accept arbitrary non-String objects as
     * arguments and return them as values, as long as they are Serializable.
     * A more complete implementation of this FunnyMoney class might bear
     * a serial number, a digital signature, and other security features to 
     * ensure that it is unique and non-forgeable.
     **/
    public static class FunnyMoney implements java.io.Serializable {
        public int amount;
        public FunnyMoney(int amount) { this.amount = amount; }
    }
    
    /**
     * This is a type of exception used to represent exceptional conditions
     * related to banking, such as "Insufficient Funds" and  "Invalid Password"
     **/
    public static class BankingException extends Exception {
        public BankingException(String msg) { super(msg); }
    }
    
    /**
     * This class is a simple stand-alone client program that interacts
     * with a RemoteBank server.  It invokes different RemoteBank methods
     * depending on its command-line arguments, and demonstrates just how
     * simple it is to interact with a server using RMI.
     **/
    public static class Client {
        public static void main(String[] args) {
            try {
                // Figure out what RemoteBank to connect to by reading a system
                // property (specified on the command line with a -D option to
                // java) or, if it is not defined, use a default URL.  Note
                // that by default this client tries to connect to a server on
                // the local machine
                String url = System.getProperty("bank", "rmi:///FirstRemote");
		
                // Now look up that RemoteBank server using the Naming object,
                // which contacts the rmiregistry server.  Given the url, this
                // call returns a RemoteBank object whose methods may be
                // invoked remotely
                RemoteBank bank = (RemoteBank) Naming.lookup(url);
                
                // Convert the user's command to lower case
                String cmd = args[0].toLowerCase();
		
                // Now, go test the command against a bunch of possible options
                if (cmd.equals("open")) {           // Open an account
                    bank.openAccount(args[1], args[2]);
                    System.out.println("Account opened.");
                }
                else if (cmd.equals("close")) {     // Close an account
                    FunnyMoney money = bank.closeAccount(args[1], args[2]);
                    // Note: our currency is denominated in wooden nickels
                    System.out.println(money.amount +
				       " wooden nickels returned to you.");
                    System.out.println("Thanks for banking with us.");
                }
                else if (cmd.equals("deposit")) {   // Deposit money
                    FunnyMoney money=new FunnyMoney(Integer.parseInt(args[3]));
                    bank.deposit(args[1], args[2], money);
                    System.out.println("Deposited " + money.amount +
				       " wooden nickels.");
                }
                else if (cmd.equals("withdraw")) {  // Withdraw money
                    FunnyMoney money = bank.withdraw(args[1], args[2], 
						    Integer.parseInt(args[3]));
                    System.out.println("Withdrew " + money.amount +
				       " wooden nickels.");
                }
                else if (cmd.equals("balance")) {   // Check account balance
                    int amt = bank.getBalance(args[1], args[2]);
                    System.out.println("You have " + amt +
				       " wooden nickels in the bank.");
                }
                else if (cmd.equals("history")) {   // Get transaction history
                    List transactions =
			bank.getTransactionHistory(args[1], args[2]);
                    for(int i = 0; i < transactions.size(); i++)
                        System.out.println(transactions.get(i));
                }
                else System.out.println("Unknown command");
            }
            // Catch and display RMI exceptions
            catch (RemoteException e) { System.err.println(e); }
            // Catch and display Banking related exceptions
            catch (BankingException e) { System.err.println(e.getMessage()); }
            // Other exceptions are probably user syntax errors, so show usage.
            catch (Exception e) { 
                System.err.println(e);
                System.err.println("Usage: java [-Dbank=<url>] Bank$Client " + 
				   "<cmd> <name> <password> [<amount>]");
                System.err.println("where cmd is: open, close, deposit, " + 
				   "withdraw, balance, history");
            }
        }
    }
}
