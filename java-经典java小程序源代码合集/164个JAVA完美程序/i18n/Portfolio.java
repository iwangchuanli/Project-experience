/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.i18n;
import java.text.*;
import java.util.*;
import java.io.*;

/**
 * A partial implementation of a hypothetical stock portfolio class.
 * We use it only to demonstrate number and date internationalization.
 **/
public class Portfolio {
    EquityPosition[] positions;
    Date lastQuoteTime = new Date();

    public Portfolio(EquityPosition[] positions, Date lastQuoteTime) {
	this.positions = positions;
	this.lastQuoteTime = lastQuoteTime;
    }
    
    public void print(PrintWriter out) {
        // Obtain NumberFormat and DateFormat objects to format our data.
        NumberFormat number = NumberFormat.getInstance();
        NumberFormat price = NumberFormat.getCurrencyInstance();
        NumberFormat percent = NumberFormat.getPercentInstance();
        DateFormat shortdate = DateFormat.getDateInstance(DateFormat.MEDIUM);
        DateFormat fulldate = DateFormat.getDateTimeInstance(DateFormat.LONG,
							     DateFormat.LONG);
	
        // Print some introductory data.
        out.println("Portfolio value at " +
		    fulldate.format(lastQuoteTime) + ":");
        out.println("Symbol\tShares\tPurchased\tAt\t" +
		    "Quote\tChange");
	
        // Display the table using the format() methods of the Format objects.
        for(int i = 0; i < positions.length; i++) {
            out.print(positions[i].name + "\t");
            out.print(number.format(positions[i].shares) + "\t");
            out.print(shortdate.format(positions[i].purchased) + "\t");
            out.print(price.format(positions[i].bought) + "\t");
            out.print(price.format(positions[i].current) + "\t");
            double change =
                (positions[i].current-positions[i].bought)/positions[i].bought;
            out.println(percent.format(change));
	    out.flush();
        }
    }
    
    static class EquityPosition {
        String name;             // Name of the stock.
        int shares;              // Number of shares held.
        Date purchased;          // When purchased.
        double bought;           // Purchase price per share
	double current;          // Current price per share
        EquityPosition(String n, int s, Date when, double then, double now) {
            name = n; shares = s; purchased = when;
	    bought = then; current = now;
        }
    }

    /**
     * This is a test program that demonstrates the class
     **/
    public static void main(String[] args) {
	// This is the portfolio to display.  Note we use a deprecated
	// Date() constructor here for convenience. It represents the year
	// offset from 1900, and will cause a warning message when compiling.
	EquityPosition[] positions = new EquityPosition[] {
	    new EquityPosition("XXX", 400, new Date(100,1,3),11.90,13.00),
	    new EquityPosition("YYY", 1100, new Date(100,2,2),71.09,27.25),
	    new EquityPosition("ZZZ", 6000, new Date(100,4,17),23.37,89.12)
	};

	// Create the portfolio from these positions
	Portfolio portfolio = new Portfolio(positions, new Date());

        // Set the default locale using the language code and country code
	// specified on the command line.
        if (args.length == 2) Locale.setDefault(new Locale(args[0], args[1]));

	// Now print the portfolio
	portfolio.print(new PrintWriter(System.out));
    }
}
