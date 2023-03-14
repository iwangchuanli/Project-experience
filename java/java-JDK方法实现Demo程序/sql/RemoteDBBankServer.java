/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.sql;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.util.Date; // import explicitly to disambiguate from java.sql.Date
import com.davidflanagan.examples.rmi.Bank.*;  // Import inner classes of Bank

/**
 * This class is another implementation of the RemoteBank interface.
 * It uses a database connection as its back end, so that client data isn't
 * lost if the server goes down.  Note that it takes the database connection
 * out of "auto commit" mode and explicitly calls commit() and rollback() to
 * ensure that updates happen atomically.
 **/
public class RemoteDBBankServer extends UnicastRemoteObject
    implements RemoteBank
{
    Connection db;   // The connection to the database that stores account info
    
    /** The constructor.  Just save the database connection object away */
    public RemoteDBBankServer(Connection db) throws RemoteException { 
        this.db = db;
    }
    
    /** Open an account */
    public synchronized void openAccount(String name, String password)
	throws RemoteException, BankingException
    {
        // First, check if there is already an account with that name
        Statement s = null;
        try { 
            s = db.createStatement();
            s.executeQuery("SELECT * FROM accounts WHERE name='" + name + "'");
            ResultSet r = s.getResultSet();
            if (r.next()) throw new BankingException("Account name in use.");
	    
            // If it doesn't exist, go ahead and create it Also, create a
            // table for the transaction history of this account and insert an
            // initial transaction into it.
            s = db.createStatement();
            s.executeUpdate("INSERT INTO accounts VALUES ('" + name + "', '" +
			    password + "', 0)");
            s.executeUpdate("CREATE TABLE " + name + 
			    "_history (msg VARCHAR(80))");
            s.executeUpdate("INSERT INTO " + name + "_history " +
			    "VALUES ('Account opened at " + new Date() + "')");
	    
            // And if we've been successful so far, commit these updates,
            // ending the atomic transaction.  All the methods below also use
            // this atomic transaction commit/rollback scheme
            db.commit();
        }
        catch(SQLException e) {
            // If an exception was thrown, "rollback" the prior updates,
            // removing them from the database.  This also ends the atomic
            // transaction.
            try { db.rollback(); } catch (Exception e2) {}
            // Pass the SQLException on in the body of a BankingException
            throw new BankingException("SQLException: " + e.getMessage() + 
				       ": " + e.getSQLState());
        }
        // No matter what happens, don't forget to close the DB Statement
        finally { try { s.close(); } catch (Exception e) {} }
    }
    
    /** 
     * This convenience method checks whether the name and password match
     * an existing account.  If so, it returns the balance in that account.
     * If not, it throws an exception.  Note that this method does not call
     * commit() or rollback(), so its query is part of a larger transaction.
     **/
    public int verify(String name, String password) 
	throws BankingException, SQLException
    {
        Statement s = null;
        try {
            s = db.createStatement();
            s.executeQuery("SELECT balance FROM accounts " +
			   "WHERE name='" + name + "' " +
			   "  AND password = '" + password + "'");
            ResultSet r = s.getResultSet();
            if (!r.next())
                throw new BankingException("Bad account name or password");
            return r.getInt(1);
        }
        finally { try { s.close(); } catch (Exception e) {} }
    }
    
    /** Close a named account */
    public synchronized FunnyMoney closeAccount(String name, String password)
	throws RemoteException, BankingException
    {
        int balance = 0;
        Statement s = null;
        try {
            balance = verify(name, password);
            s = db.createStatement();
            // Delete the account from the accounts table
            s.executeUpdate("DELETE FROM accounts " + 
			    "WHERE name = '" + name + "' " +
			    "  AND password = '" + password + "'");
            // And drop the transaction history table for this account
            s.executeUpdate("DROP TABLE " + name + "_history");
            db.commit();
        }
        catch (SQLException e) {
            try { db.rollback(); } catch (Exception e2) {}
            throw new BankingException("SQLException: " + e.getMessage() + 
				       ": " + e.getSQLState());
        }
        finally { try { s.close(); } catch (Exception e) {} }
	
        // Finally, return whatever balance remained in the account
        return new FunnyMoney(balance);
    }
    
    /** Deposit the specified money into the named account */
    public synchronized void deposit(String name, String password, 
				     FunnyMoney money) 
	throws RemoteException, BankingException
    {
        int balance = 0; 
        Statement s = null;
        try {
            balance = verify(name, password);
            s = db.createStatement();
            // Update the balance
            s.executeUpdate("UPDATE accounts " +
			    "SET balance = " + balance + money.amount + " " +
			    "WHERE name='" + name + "' " +
			    "  AND password = '" + password + "'");
            // Add a row to the transaction history
            s.executeUpdate("INSERT INTO " + name + "_history " + 
			    "VALUES ('Deposited " + money.amount + 
			    " at " + new Date() + "')");
            db.commit();
        }
        catch (SQLException e) {
            try { db.rollback(); } catch (Exception e2) {}
            throw new BankingException("SQLException: " + e.getMessage() + 
				       ": " + e.getSQLState());
        }
        finally { try { s.close(); } catch (Exception e) {} }
    }
    
    /** Withdraw the specified amount from the named account */
    public synchronized FunnyMoney withdraw(String name, String password, 
					    int amount)
	throws RemoteException, BankingException
    {
        int balance = 0;
        Statement s = null;
        try {
            balance = verify(name, password);
            if (balance < amount)
		throw new BankingException("Insufficient Funds");
            s = db.createStatement();
            // Update the account balance
            s.executeUpdate("UPDATE accounts " +
			    "SET balance = " + (balance - amount) + " " +
			    "WHERE name='" + name + "' " +
			    "  AND password = '" + password + "'");
            // Add a row to the transaction history
            s.executeUpdate("INSERT INTO " + name + "_history " + 
			    "VALUES ('Withdrew " + amount + 
			    " at " + new Date() + "')");
            db.commit();
        }
        catch (SQLException e) {
            try { db.rollback(); } catch (Exception e2) {}
            throw new BankingException("SQLException: " + e.getMessage() + 
				       ": " + e.getSQLState());
        }
        finally { try { s.close(); } catch (Exception e) {} }
	
        return new FunnyMoney(amount);
    }
    
    /** Return the balance of the specified account */
    public synchronized int getBalance(String name, String password)
	throws RemoteException, BankingException
    {
        int balance;
        try {
            // Get the balance
            balance = verify(name, password);
            // Commit the transaction
            db.commit();
        }
        catch (SQLException e) {
            try { db.rollback(); } catch (Exception e2) {}
            throw new BankingException("SQLException: " + e.getMessage() + 
				       ": " + e.getSQLState());
        }
        // Return the balance
        return balance;
    }
    
    /** Get the transaction history of the named account */
    public synchronized List getTransactionHistory(String name, 
						   String password)
	throws RemoteException, BankingException
    {
        Statement s = null;
        List list = new ArrayList();
        try {
            // Call verify to check the password, even though we don't 
            // care what the current balance is.
            verify(name, password);
            s = db.createStatement();
            // Request everything out of the history table
            s.executeQuery("SELECT * from " + name + "_history");
            // Get the results of the query and put them in a Vector
            ResultSet r = s.getResultSet();
            while(r.next()) list.add(r.getString(1));
            // Commit the transaction
            db.commit();
        }
        catch (SQLException e) {
            try { db.rollback(); } catch (Exception e2) {}
            throw new BankingException("SQLException: " + e.getMessage() + 
				       ": " + e.getSQLState());
        }
        finally { try { s.close(); } catch (Exception e) {} }
        // Return the Vector of transaction history.
        return list;
    }
    
    /**
     * This main() method is the standalone program that figures out what
     * database to connect to with what driver, connects to the database,
     * creates a RemoteDBBankServer object, and registers it with the registry,
     * making it available for client use
     **/
    public static void main(String[] args) {
        try {
            // Create a new Properties object.  Attempt to initialize it from
            // the BankDB.props file or the file optionally specified on the 
            // command line, ignoring errors.
            Properties p = new Properties();
            try { p.load(new FileInputStream(args[0])); }
            catch (Exception e) {
                try { p.load(new FileInputStream("BankDB.props")); }
                catch (Exception e2) {}
            }
	    
            // The BankDB.props file (or file specified on the command line)
            // must contain properties "driver" and "database", and may
            // optionally contain properties "user" and "password".
            String driver = p.getProperty("driver");
            String database = p.getProperty("database");
            String user = p.getProperty("user", "");
            String password = p.getProperty("password", "");
	    
            // Load the database driver class
            Class.forName(driver);
	    
            // Connect to the database that stores our accounts
            Connection db = DriverManager.getConnection(database,
							user, password);
	    
            // Configure the database to allow multiple queries and updates
            // to be grouped into atomic transactions
            db.setAutoCommit(false);
            db.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	    
            // Create a server object that uses our database connection
            RemoteDBBankServer bank = new RemoteDBBankServer(db);
	    
            // Read a system property to figure out how to name this server.
            // Use "SecondRemote" as the default.
            String name = System.getProperty("bankname", "SecondRemote");
	    
            // Register the server with the name
            Naming.rebind(name, bank);
	    
            // And tell everyone that we're up and running.
            System.out.println(name + " is open and ready for customers.");
        }
        catch (Exception e) {
            System.err.println(e);
            if (e instanceof SQLException) 
                System.err.println("SQL State: " +
				   ((SQLException)e).getSQLState());
            System.err.println("Usage: java [-Dbankname=<name>] " +
		        "com.davidflanagan.examples.sql.RemoteDBBankServer " +
			       "[<dbpropsfile>]");
            System.exit(1);
        }
    }
}
