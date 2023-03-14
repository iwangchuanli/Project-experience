/* ExtraFrame.java */

import java.awt.*;
import java.applet.*;

public class ExtraFrame extends Frame {
	
	AlgAnimFrame parentApp;
	LegendPanel legend_panel;
	
	public ExtraFrame( AlgAnimFrame parentApp ) {
		this.parentApp = parentApp;
		setTitle(parentApp.algname);
		
		// for the legend window
		int num = 4;
		Color collection[];
		String set[];
		int width = 250, height = 200;
		
		// generate the legend in a new window
		collection = new Color[num];
		set = new String[num];
		

		collection = Generate_Colour_Data( collection );
		set = Generate_String_Data( set );
		legend_panel = new LegendPanel(collection, set, num);

		//Arrange panels within frame
		/*
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gc = new GridBagConstraints();
		setLayout( gb );
		
		gc.fill = GridBagConstraints.BOTH;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.gridheight = GridBagConstraints.REMAINDER;
		gc.gridx = 0; gc.gridy = 0;
		gb.setConstraints( legend_panel, gc );
		*/
		setLayout( new BorderLayout() );
		add("Center", legend_panel );
		
		//	pack();
		validate();
		resize(width, height);
		show();
		}
	
	public boolean handleEvent(Event event) {
		if (event.id == Event.WINDOW_DESTROY) {
			dispose();
			}
		return super.handleEvent(event);
		}
	
	public void Remove_Window( ) {
		dispose();
		}
	
	private Color[] Generate_Colour_Data( Color[] collection ) {
		collection[0] = Color.blue;
		collection[1] = Color.black;
		collection[2] = Color.yellow;
		collection[3] = Color.red;
		return collection;
		}
	
	private String[] Generate_String_Data( String[] set ) {
		set[0] = "Original Node Colour";
		set[1] = "Original Edge Colour";
		set[2] = "Node/Edge In Focus";
		set[3] = "Node/Edge Chosen";
		return set;
		}
	
	}
