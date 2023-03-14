package Dialog;

import java.util.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import java.sql.*;

import javax.swing.table.*;

@SuppressWarnings("serial")
public class StudentScore extends JPanel implements ActionListener{
	@SuppressWarnings("unused")
	private String XYID;
	private String ID;
	private Connection con;
	private Statement sql;
	private ResultSet rs;
	private Statement sql2;
	private ResultSet rs2;
	//��ŵ�ǰ��ʦ���ڵ�ѧԺ��
	@SuppressWarnings("unused")
	private String coll_id;
	@SuppressWarnings("rawtypes")
	private Map map_dept=new HashMap();
	@SuppressWarnings("rawtypes")
	private Vector v_dept=new Vector();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox jcb=new JComboBox(v_dept);
	//������ʾ��Ϣ��ǩ
	private JLabel jl=new JLabel("��ѡ����Ҫ�����Ŀγ�");
	//������ű�ͷ��������ݵ�Vector����
	@SuppressWarnings("rawtypes")
	private Vector v_head=new Vector();
	@SuppressWarnings("rawtypes")
	private Vector v_data=new Vector();
	private JTable jt;//�����������
	private JScrollPane jsp;
	//����������ť��ֻ�й�����ĳɼ�ѧ���ſ��Կ���9
	private JButton jb=new JButton("�ύ");
	private JButton jb2=new JButton("����");
	public StudentScore(String coll_id,String ID)
	{
		this.coll_id=coll_id;
		this.ID=ID;
		this.initialData();//��ʼ������
		this.initialFrame();//��ʼ������
		this.initialListener();//ע�������
	}
	public void initialFrame()//��ʼ������
	{   //���ؼ���ӵ�������
		this.setLayout(null);
		jl.setBounds(30,20,150,30);this.add(jl);
		jcb.setBounds(180,20,100,30);this.add(jcb);
		jb.setBounds(325,20,70,30);this.add(jb);
		jb2.setBounds(400,20,70,30);this.add(jb2);
		jt=new JTable(new DefaultTableModel(v_data,v_head));
		jsp=new JScrollPane(jt);
		jsp.setBounds(30,70,500,500);this.add(jsp);
	}
	public void initialListener()//ע�������
	{
		jcb.addActionListener(this);jb.addActionListener(this);
		jb2.addActionListener(this);
		TableChangeListener tl=new TableChangeListener(sql);
		jt.getSelectionModel().addListSelectionListener(tl);
		jt.getColumnModel().addColumnModelListener(tl);
		jt.getModel().addTableModelListener(tl);
	}
	@SuppressWarnings("unchecked")
	public void initialData()//��ʼ�����ݵķ���
	{//��ʼ����ͷ
		v_head.add("�γ̺�");v_head.add("ѧ��");
		v_head.add("����");v_head.add("�ɼ�(��)");
		String s="select distinct �γ�ID from �ɼ� where"+
				" ��ʦID='"+ID+"'and (����=0 or ����=2)";
		try
		{//��ѯ���ݿ⣬���γ������γ̺Ŵ���map_dept��v_dept��
			this.initialConnection();
			rs=sql.executeQuery(s);
			while(rs.next()){			
				String cou_id=rs.getString(1);
				String s1="select  �γ�Name from �γ� where �γ�ID='"+cou_id+"'";
				rs2=sql2.executeQuery(s1);
				while(rs2.next()){
					String cou_name=rs2.getString(1);
					map_dept.put(cou_name,cou_id);
					v_dept.add(cou_name);
				}
			}
			rs.close();//�رս����
		}
		catch(SQLException e){e.printStackTrace();}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void actionPerformed(ActionEvent e)
	{//ʵ��ActionListener�ӿ��еķ���
		if(e.getSource()==jcb)
		{//�������б���е�ѡ�����ݷ����仯ʱ�Ĵ������
			v_data.removeAllElements();//��v_data���
			//��������б�ѡ�еĿγ���
			String cur_cou_name=(String)jcb.getSelectedItem();
			//���ݿγ�����ÿγ̺�
			String cur_cou_id=(String)map_dept.get(cur_cou_name);
			String s2="select �ɼ�.�γ�ID,�ɼ�.ѧ��ID,ѧ��.ѧ��Name,�ɼ� "+
			           "from �ɼ�,ѧ�� where �ɼ�.ѧ��ID=ѧ��.ѧ��ID and "+
			            "(����=0 or ����=2) and �ɼ�.�γ�ID='"+cur_cou_id+"'";//����sql���
			try{//ִ�����
				rs=sql.executeQuery(s2);
			    while(rs.next()){//����ÿγ̺���ص�δ�������Ϣ����v_data
			    	Vector v=new Vector();
			    	String cou_id=rs.getString(1);
			    	String stu_id=rs.getString(2);
			    	String stu_name=rs.getString(3);
			    	String score=rs.getDouble(4)+"";
			    	v.add(cou_id);v.add(stu_id);v.add(stu_name);v.add(score);
			    	v_data.add(v);
			    }
			    rs.close();//�رս����
			    DefaultTableModel temp1=(DefaultTableModel)jt.getModel();//���±��ģ�ͣ�
			    temp1.setDataVector(v_data,v_head);
			    temp1.fireTableStructureChanged();//������ʾ��Ϣ
			}
			catch(Exception ea){ea.printStackTrace();}
		}
		else if(e.getSource()==jb){//�����¹����ɼ��İ�ťʱ
			try{//���Ҫ�����ɼ��Ŀγ���
				String cur_cou_name=(String)jcb.getSelectedItem();
				if(cur_cou_name!=null){//�������ݿ��еı�־��
					String cur_cou_id=(String)map_dept.get(cur_cou_name);
					String s3="update �ɼ� set ����=1 where "+
					            "�γ�ID='"+cur_cou_id+"' and (����=0 or ����=2)";
					@SuppressWarnings("unused")
					int i=sql.executeUpdate(s3);
					JOptionPane.showMessageDialog(this,"�ύ�ɹ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				}
				else{//û��ѡ��γ����Ĵ�����ʾ����
					JOptionPane.showMessageDialog(this,"����ѡ��γ�����","����",
					                                JOptionPane.ERROR_MESSAGE);
				}
			}
			catch(Exception ea){ea.printStackTrace();}	
		}
		else if(e.getSource()==jb2){//�����±���ɼ��İ�ťʱ
			try{
				String cur_cou_name=(String)jcb.getSelectedItem();
				if(cur_cou_name!=null){//�������ݿ��еı�־��
					String cur_cou_id=(String)map_dept.get(cur_cou_name);
					String sss="update �ɼ� set ����=2 where �γ�ID='"+cur_cou_id+"'and (����=0 or ����=2)";
					@SuppressWarnings("unused")
					int i=sql.executeUpdate(sss);
					JOptionPane.showMessageDialog(this,"����ɹ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				}
				else{//û��ѡ��γ����Ĵ�����ʾ����
					JOptionPane.showMessageDialog(this,"����ѡ��γ�����","����",
					                                JOptionPane.ERROR_MESSAGE);
				}
			}
			catch(Exception ea){ea.printStackTrace();}	
		}
	}
	//�Զ���ĳ�ʼ�����ݿ����ӵķ���
	public void  initialConnection()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		try{
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/�ɼ�����ϵͳ?useSSL=true","root","123456");
			sql=con.createStatement();
			sql2=con.createStatement();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	//�ر����ӵķ���
	public void closeConn()
	{
		try
		{
			if(rs!=null)
			{rs.close();}
			if(sql!=null)
			{sql.close();}
			if(con!=null)
			{con.close();}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	class TableChangeListener implements ListSelectionListener,
	                          TableModelListener,TableColumnModelListener
	{
		int rowNum,colNum;
		Statement statement;
		public TableChangeListener(Statement statement)
		{this.statement=statement;}
		public void valueChanged(ListSelectionEvent e){//�����е�ֵ
			rowNum=jt.getSelectedRow();
		}
		public void columnSelectionChanged(ListSelectionEvent e){//�����е�ֵ
			colNum=jt.getSelectedColumn();
		}
		public void tableChanged(TableModelEvent e)
		{//�����ĵ��ǵ�����(������)ʱ�Ĵ������
			if(colNum==3)
			{   //������������
				String str=(String)jt.getValueAt(rowNum,colNum);
				//��ø���������Ӧ�Ŀγ̺�
				String cou_id=(String)jt.getValueAt(rowNum,0);
				//��ø���������Ӧѧ����ѧ��
				String stu_id=(String)jt.getValueAt(rowNum,1);
				try{//��strת��ΪDouble
					Double d=Double.parseDouble(str);
					if(d<0||d>100)//��������С��0�Ҳ��ܴ���100
					{//���ڷ�Χ�ڣ�������Ϊ0
						jt.setValueAt("0",rowNum,colNum);
					}
				}
				catch(Exception ea){//�������֣�������Ϊ0
					jt.setValueAt("0",rowNum,colNum);
				}
				//�����ĵ�����ͬ�������ݿ�
				String s4="update �ɼ� set �ɼ�="+str+" where "+
				           "�γ�ID='"+cou_id+"' and ѧ��ID='"+stu_id+"'";
				try{//ִ��sql���
					@SuppressWarnings("unused")
					int i=statement.executeUpdate(s4);
				}
				catch(Exception ea){ea.printStackTrace();}
			}
		}
		//ʵ�ֽӿ��е���������
		public void columnMoved(TableColumnModelEvent e){}
		public void columnRemoved(TableColumnModelEvent e){}
		public void columnMarginChanged(ChangeEvent e){}
		public void columnAdded(TableColumnModelEvent e){}
	}
}

