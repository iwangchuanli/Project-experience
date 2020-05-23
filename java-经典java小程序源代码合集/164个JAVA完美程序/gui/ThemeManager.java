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
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.DefaultMetalTheme;

/**
 * This class reads theme descriptions from a GUIResourceBundle and uses them
 * to specify colors and fonts for the Metal look-and-feel.
 **/
public class ThemeManager {
    JFrame frame;                  // The frame which themes are applied to
    GUIResourceBundle resources;   // Properties describing the themes

    /** 
     * Build a ThemeManager for the frame and resource bundle.  If there
     * is a default theme specified, apply it to the frame
     **/
    public ThemeManager(JFrame frame, GUIResourceBundle resources) {
	this.frame = frame;
	this.resources = resources;
	String defaultName = getDefaultThemeName();
	if (defaultName != null) setTheme(defaultName);
    }

    /** Look up the named theme, and apply it to the frame */
    public void setTheme(String themeName) {
	// Look up the theme in the resource bundle
	Theme theme = new Theme(resources, themeName);
	// Make it the current theme
	MetalLookAndFeel.setCurrentTheme(theme);
	// Re-apply the Metal look-and-feel to install new theme
	try { UIManager.setLookAndFeel(new MetalLookAndFeel()); }
	catch(UnsupportedLookAndFeelException e) {}
	// Propagate the new l&f across the entire component tree of the frame
	SwingUtilities.updateComponentTreeUI(frame);
    }

    /** Get the "display name" or label of the named theme */
    public String getDisplayName(String themeName) {
	return resources.getString(themeName + ".name", null);
    }

    /** Get the name of the default theme, or null */
    public String getDefaultThemeName() {
	return resources.getString("defaultTheme", null);
    }

    /**
     * Get the list of all known theme names.  The returned values are
     * theme property names, not theme display names.
     **/
    public String[] getAllThemeNames() {
	java.util.List names = resources.getStringList("themelist");
	return (String[]) names.toArray(new String[names.size()]);
    }

    /**
     * Get a JMenu that lists all known themes by display name and
     * installs any selected theme.
     **/
    public JMenu getThemeMenu() {
	String[] names = getAllThemeNames();
	String defaultName = getDefaultThemeName();
	JMenu menu = new JMenu("Themes");
	ButtonGroup buttongroup = new ButtonGroup();
	for(int i = 0; i < names.length; i++) {
	    final String themeName = names[i];
	    String displayName = getDisplayName(themeName);
	    JMenuItem item = menu.add(new JRadioButtonMenuItem(displayName));
	    buttongroup.add(item);
	    if (themeName.equals(defaultName)) item.setSelected(true);
	    item.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
			setTheme(themeName);
		    }
		});
	}
	return menu;
    }

    /**
     * This class extends the DefaultMetalTheme class to return Color and
     * Font values read from a GUIResourceBundle
     **/
    public static class Theme extends DefaultMetalTheme {
	// These fields are the values returned by this Theme
	String displayName;
	FontUIResource controlFont, menuFont, smallFont;
	FontUIResource systemFont, userFont, titleFont;
	ColorUIResource primary1, primary2, primary3;
	ColorUIResource secondary1, secondary2, secondary3;

	/**
	 * This constructor reads all the values it needs from the
	 * GUIResourceBundle.  It uses intelligent defaults if properties
	 * are not specified.
	 **/
	public Theme(GUIResourceBundle resources, String name) {
	    // Use this theme object to get default font values from
	    DefaultMetalTheme defaultTheme = new DefaultMetalTheme();

	    // Look up the display name of the theme
	    displayName = resources.getString(name + ".name", null);

	    // Look up the fonts for the theme
	    Font control = resources.getFont(name + ".controlFont", null);
	    Font menu = resources.getFont(name + ".menuFont", null);
	    Font small = resources.getFont(name + ".smallFont", null);
	    Font system = resources.getFont(name + ".systemFont", null);
	    Font user = resources.getFont(name + ".userFont", null);
	    Font title = resources.getFont(name + ".titleFont", null);

	    // Convert fonts to FontUIResource, or get defaults
	    if (control != null) controlFont = new FontUIResource(control);
	    else controlFont = defaultTheme.getControlTextFont();
	    if (menu != null) menuFont = new FontUIResource(menu);
	    else menuFont = defaultTheme.getMenuTextFont();
	    if (small != null) smallFont = new FontUIResource(small);
	    else smallFont = defaultTheme.getSubTextFont();
	    if (system != null) systemFont = new FontUIResource(system);
	    else systemFont = defaultTheme.getSystemTextFont();
	    if (user != null) userFont = new FontUIResource(user);
	    else userFont = defaultTheme.getUserTextFont();
	    if (title != null) titleFont = new FontUIResource(title);
	    else titleFont = defaultTheme.getWindowTitleFont();

	    // Look up primary and secondary colors
	    Color primary = resources.getColor(name + ".primary", null);
	    Color secondary = resources.getColor(name + ".secondary", null);

	    // Derive all six colors from these two, using defaults if needed
	    if (primary != null) primary1 = new ColorUIResource(primary);
	    else primary1 = new ColorUIResource(102, 102, 153);
	    primary2 = new ColorUIResource(primary1.brighter());
	    primary3 = new ColorUIResource(primary2.brighter());
	    if (secondary != null) secondary1 = new ColorUIResource(secondary);
	    else secondary1 = new ColorUIResource(102, 102, 102);
	    secondary2 = new ColorUIResource(secondary1.brighter());
	    secondary3 = new ColorUIResource(secondary2.brighter());
	}

	// These methods override DefaultMetalTheme and return the property
	// values we looked up and computed for this theme
	public String getName() { return displayName; }
	public FontUIResource getControlTextFont() { return controlFont;}
	public FontUIResource getSystemTextFont() { return systemFont;}
	public FontUIResource getUserTextFont() { return userFont;}
	public FontUIResource getMenuTextFont() { return menuFont;}
	public FontUIResource getWindowTitleFont() { return titleFont;}
	public FontUIResource getSubTextFont() { return smallFont;}
	protected ColorUIResource getPrimary1() { return primary1; } 
	protected ColorUIResource getPrimary2() { return primary2; }
	protected ColorUIResource getPrimary3() { return primary3; }
	protected ColorUIResource getSecondary1() { return secondary1; }
	protected ColorUIResource getSecondary2() { return secondary2; }
	protected ColorUIResource getSecondary3() { return secondary3; }
    }
}
