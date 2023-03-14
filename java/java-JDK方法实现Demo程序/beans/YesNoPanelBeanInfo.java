/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.beans;
import java.beans.*;
import java.lang.reflect.*;
import java.awt.*;

/**
 * This BeanInfo class provides additional information about the YesNoPanel
 * bean in addition to what can be obtained through  introspection alone.
 **/
public class YesNoPanelBeanInfo extends SimpleBeanInfo {
    /**
     * Return an icon for the bean.  We should really check the kind argument
     * to see what size icon the beanbox wants, but since we only have one
     * icon to offer, we just return it and let the beanbox deal with it
     **/
    public Image getIcon(int kind) { return loadImage("YesNoPanelIcon.gif"); }
    
    /**
     * Return a descriptor for the bean itself.  It specifies a customizer
     * for the bean class.  We could also add a description string here
     **/
    public BeanDescriptor getBeanDescriptor() {
	return new BeanDescriptor(YesNoPanel.class,
				  YesNoPanelCustomizer.class);
    }
    
    /** This is a convenience method for creating PropertyDescriptor objects */
    static PropertyDescriptor prop(String name, String description) {
	try {
	    PropertyDescriptor p =
		new PropertyDescriptor(name, YesNoPanel.class);
	    p.setShortDescription(description);
	    return p;
	}
	catch(IntrospectionException e) { return null; } 
    }

    // Initialize a static array of PropertyDescriptor objects that provide
    // additional information about the properties supported by the bean.
    // By explicitly specifying property descriptors, we are able to provide
    // simple help strings for each property; these would not be available to
    // the beanbox through simple introspection.  We are also able to register
    // a special property editors for the messageText property
    static PropertyDescriptor[] props = {
	prop("messageText", "The message text that appears in the bean body"),
	prop("alignment", "The alignment of the message text"),
	prop("yesLabel", "The label for the Yes button"),
	prop("noLabel", "The label for the No button"),
	prop("cancelLabel","The label for the Cancel button"),
	prop("font", "The font for the message and buttons"),
	prop("background", "The background color"),
	prop("foreground", "The foreground color"),
    };
    static {
	props[0].setPropertyEditorClass(YesNoPanelMessageEditor.class);
    }
    
    /** Return the property descriptors for this bean */
    public PropertyDescriptor[] getPropertyDescriptors() { return props; }
    
    /** The message property is most often customized; make it the default */
    public int getDefaultPropertyIndex() { return 0; }
}
