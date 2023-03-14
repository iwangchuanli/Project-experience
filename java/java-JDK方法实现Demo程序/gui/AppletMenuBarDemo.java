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
import java.awt.*;
import java.applet.*;

public class AppletMenuBarDemo extends Applet {
    public  void init() {
	AppletMenuBar menubar = new AppletMenuBar();
	menubar.setForeground(Color.black);
	menubar.setHighlightColor(Color.red);
	menubar.setFont(new Font("helvetica", Font.BOLD, 12));
	this.setLayout(new BorderLayout());
	this.add(menubar, BorderLayout.NORTH);

	PopupMenu file = new PopupMenu();
	file.add("New..."); file.add("Open..."); file.add("Save As...");
	PopupMenu edit = new PopupMenu();
	edit.add("Cut"); edit.add("Copy"); edit.add("Paste");

	menubar.addMenu("File", file);
	menubar.addMenu("Edit", edit);
    }
}
