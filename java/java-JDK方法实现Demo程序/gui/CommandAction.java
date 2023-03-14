/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.gui;
import com.davidflanagan.examples.reflect.*;
import javax.swing.*;
import java.awt.event.*;

public class CommandAction extends AbstractAction {
    Command command;  // The command to execute in response to an ActionEvent
    
    /**
     * Create an Action object that has the various specified attributes, 
     * and invokes the specified Command object in response to ActionEvents
     **/
    public CommandAction(Command command, String label,
			 Icon icon, String tooltip, 
			 KeyStroke accelerator, int mnemonic,
			 boolean enabled) 
    {
	this.command = command;  // Remember the command to invoke
	
	// Set the various action attributes with putValue()
	if (label != null) putValue(NAME, label);
	if (icon != null) putValue(SMALL_ICON, icon);
	if (tooltip != null) putValue(SHORT_DESCRIPTION, tooltip);
	if (accelerator != null) putValue(ACCELERATOR_KEY, accelerator);
	if (mnemonic != KeyEvent.VK_UNDEFINED) 
	    putValue(MNEMONIC_KEY, new Integer(mnemonic));

	// Tell the action whether it is currently enabled or not
	setEnabled(enabled);
    }
    
    /**
     * This method implements ActionListener, which is a super-interface of
     * Action.  When a component generates an ActionEvent, it is passed to
     * this method.  This method simply passes it on to the Command object
     * which is also an ActionListener object
     **/
    public void actionPerformed(ActionEvent e) { command.actionPerformed(e); }

    // These constants are defined by Action in Java 1.3.
    // For compatibility with Java 1.2, we re-define them here.
    public static final String ACCELERATOR_KEY = "AcceleratorKey";
    public static final String MNEMONIC_KEY = "MnemonicKey";
}
