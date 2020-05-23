import java.applet.Applet;
import java.awt.*;

public class StatsPanel extends Panel {

	AlgAnimFrame frame;
	StatsEntryPanel source_stats_entry, general_stats_entry;

	String s;
	String statsTitle;	

	public StatsPanel(AlgAnimFrame frame, String statsTitle) {
		this.frame = frame;
		this.statsTitle = statsTitle;

		GridLayout gl = new GridLayout(0,2);
		setLayout(gl);
		source_stats_entry = new StatsEntryPanel();
		add(source_stats_entry);
		general_stats_entry = new StatsEntryPanel();
		add(general_stats_entry);		
	}

	public Dimension preferredSize() {
		return new Dimension(100,200);
	}

	public void addStatsLabel(String statsTitle, String s_label) {
		if (statsTitle.equals("Source stats")) {
			source_stats_entry.addStatsLabel(statsTitle, s_label);
		}
		else if (statsTitle.equals("General stats")) {
			general_stats_entry.addStatsLabel(statsTitle, s_label);
		}
	}

	public void updateStatsValue(String statsTitle, String s_label, int statsValue) {
		if (statsTitle.equals("Source stats")) {
			source_stats_entry.updateStatsValue(statsTitle, s_label, statsValue);
		}
		else if (statsTitle.equals("General stats")) {
			general_stats_entry.updateStatsValue(statsTitle, s_label, statsValue);
		}	
	}

} // end class StatsPanel

class StatsEntryPanel extends Panel {

	String statsLabel[];
	TextField statsEntry[];
	Label llist[];	
	
	int statCount=0, sourceStatsCount=0, generalStatsCount=0;
	int currentStat=0, maxStat=100;

	public StatsEntryPanel() {
		GridLayout gl = new GridLayout(0,2);
		setLayout(gl);		
		statsLabel = new String[maxStat];
		statsEntry = new TextField[maxStat];
		llist = new Label[maxStat];	
	}

	public void addStatsLabel(String statsTitle, String s_label) {
		Label lbl;
		TextField tf;
	if (statsTitle.equals("Source stats")) {
		if (sourceStatsCount < maxStat) {
			statsLabel[sourceStatsCount] = new String(s_label);
			lbl = new Label(s_label, Label.LEFT);
			add(lbl);
			tf = new TextField(s_label, 20);
			statsEntry[sourceStatsCount] = tf;
			statsEntry[sourceStatsCount].setEditable(false);
			add(tf);
			sourceStatsCount++;
		}
		else { 
			System.out.println("Sorry, maximum entries is " + maxStat);
		}
	}
	else if (statsTitle.equals("General stats")) {
		if (generalStatsCount < maxStat) {
			statsLabel[generalStatsCount] = new String(s_label);
			lbl = new Label(s_label, Label.LEFT);
			add(lbl);
			tf = new TextField(s_label, 20);
			statsEntry[generalStatsCount] = tf;
			statsEntry[generalStatsCount].setEditable(false);
			add(tf);
			generalStatsCount++;
		}
		else { 
			System.out.println("Sorry, maximum entries is " + maxStat);
		}
	}

	}

	public void updateStatsValue(String statsTitle, String s_label, int statsValue) {
	if (statsTitle.equals("Source stats")) {
		for (int i=0; i<sourceStatsCount; i++) {
			if (s_label.equals(statsLabel[i])) {
				statsEntry[i].setText(" " + statsValue);
				return;
			}
			else {
				//System.out.println("Cannot locate stats entry");
			}
		}
	}
	else if (statsTitle.equals("General stats")) {
			for (int i=0; i<generalStatsCount; i++) {
			if (s_label.equals(statsLabel[i])) {
				statsEntry[i].setText(" " + statsValue);
				return;
			}
			else {
				//System.out.println("Cannot locate stats entry");
			}
		}
	}

	}


} // end class StatsEntryPanel
