package StudentManageSystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import DataBase.DBcon;
import javax.swing.*;

@SuppressWarnings("serial")
public class SearchPane2 extends JPanel implements ActionListener{
	JPanel globalPane;
	JButton gsearch;
	
	JFrame ownedFrame;
	int searchMode = 1;
	String a;
	public SearchPane2(JFrame f,String s)
	{
		a=s;
		this.ownedFrame = f;
		setLayout(new CardLayout());
		globalPane = new JPanel();
		gsearch = new JButton("һ����ѯ����");	gsearch.addActionListener(this);
		globalPane.add(gsearch);	
		add(globalPane,"һ����ѯ����");
		//add(localPane,"�ֲ���ѯ����");
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == gsearch){
			try{
				DBcon db = new DBcon();
				ResultSet rs = db.query("select type from users where userid='"+a+"'");
				rs.next();
				int r = Integer.parseInt(rs.getString("type"));
				if(r == 0){
					new GlobalSearchDialog1(ownedFrame,a).showDialog();//showDialog�������ԶԴ��ڵĴ�С����������
				}else{
					//JOptionPane.showMessageDialog(null, "�ɼ��ύ�ɹ��������������޸�ѧ���ɼ���");
					//new GlobalSearchDialogT(ownedFrame,a);//.showDialog();
					new GlobalSearchDialogT(ownedFrame,a).showDialog();
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
			
		}
	}
}
