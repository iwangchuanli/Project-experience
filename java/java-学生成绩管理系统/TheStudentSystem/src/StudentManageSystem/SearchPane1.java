package StudentManageSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class SearchPane1 extends JPanel implements ActionListener{
	//JPanel globalPane;
	//JButton gsearch;
	
	JPanel localPane;
	JRadioButton sByID;
	JRadioButton sByName;
	JTextField sText;
	JButton lsearch;
	
	JFrame ownedFrame;
	int searchMode = 1;
	public SearchPane1(JFrame f)
	{
		this.ownedFrame = f;
		setLayout(new CardLayout());

		localPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lsearch = new JButton("��ѯ");				lsearch.addActionListener(this);	
		sByID = new JRadioButton ("������ѧ�Ų�ѯ");		sByID.addActionListener(this);
		sByID.setSelected(true);
		sByName = new JRadioButton("������������ѯ");	sByName.addActionListener(this);
		sText = new JTextField();					sText.addActionListener(this);
		sText.setColumns(20);
		
		localPane.add(sByID);
		localPane.add(sByName);
		localPane.add(sText);
		localPane.add(lsearch);
		add(localPane,"��ѧ��������ѯ");
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == lsearch || e.getSource() == sText)
		{
			String inputInfo = sText.getText();
			if(inputInfo.equals(""))
			{
				JOptionPane.showMessageDialog(ownedFrame,"��������Ҫ��ѯ�Ļ�����Ϣ");
				return;
			}
			new LocalSearchDialog(ownedFrame).showInfo(sText.getText(),searchMode);
		}
		else if(e.getSource() == sByID)
		{
			sByName.setSelected(false);
			searchMode = 1;
		}
		else if(e.getSource() == sByName)
		{
			sByID.setSelected(false);
			searchMode = 0;
		}
	}
}
