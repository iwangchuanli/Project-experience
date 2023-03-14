package com.chap6;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

public class TestSwing extends JFrame{
	JLabel lblName = new JLabel("姓名：");
	JLabel lblNumber = new JLabel("身份证号：");
	JLabel lblSex = new JLabel("性别：");
	JLabel lblJob = new JLabel("职业：");
	JLabel lblText = new JLabel("个性化宣言：");
	
	JTextField tfName = new JTextField(23);
	JTextField tfNumber = new JTextField(20);
	
	JTextArea taText = new JTextArea(5,20);
	
	JRadioButton jMale = new JRadioButton("男");
	JRadioButton jFemale = new JRadioButton("女");
	
	ButtonGroup group = new ButtonGroup();
	JComboBox cmbJob = new JComboBox();
	JButton btnOk = new JButton("确定");
	JButton btnDisplay = new JButton("取消");
	JTable tblInf = new JTable();
	DefaultTableModel dtm = new DefaultTableModel();
	
	//布局设置
	JTree tree = new JTree();
	
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel();
	JPanel p6 = new JPanel();
	JPanel p7 = new JPanel(new BorderLayout());
	JPanel p8 = new JPanel();
	JPanel p9 = new JPanel(new BorderLayout());
	
	public TestSwing() {
		group.add(jMale);
		group.add(jFemale);
		
		cmbJob.addItem("计算机业");
		cmbJob.addItem("医生");
		cmbJob.addItem("教师");
		cmbJob.addItem("军队");
		
		p1.add(lblName);
		p1.add(tfName);
		
		p2.add(lblNumber);
		p2.add(tfNumber);
		
		p3.add(lblSex);
		p3.add(jMale);
		p3.add(jFemale);
		
		p4.add(lblJob);
		p4.add(cmbJob);
		
		p5.add(p3);
		p5.add(p4);
		
		p6.setLayout(new BorderLayout());
		p6.add(p1,BorderLayout.NORTH);
		p6.add(p2,BorderLayout.CENTER);
		p6.add(p5,BorderLayout.SOUTH);
		
		p7.add(lblText,BorderLayout.NORTH);
		p7.add(taText, BorderLayout.CENTER);
		
		p8.setLayout(new FlowLayout(FlowLayout.CENTER,30,10));
		p8.add(btnOk);
		p8.add(btnDisplay);
		
		p9.add(p6,BorderLayout.NORTH);
		p9.add(p7, BorderLayout.CENTER);
		p9.add(p8, BorderLayout.SOUTH);
		//调用设置表的方法
		setTable();
		//调用设置树的方法
		setTree();
		getContentPane().add(p9, BorderLayout.NORTH);
		JScrollPane s = new JScrollPane(tblInf);
		getContentPane().add(s, BorderLayout.CENTER);
		getContentPane().add(tree, BorderLayout.SOUTH);
	}
	//设置表，用Vector辅助添加
	public void setTable(){
		Vector vCdata = new Vector();
		vCdata.add("姓名");
		vCdata.add("身份证号");
		vCdata.add("性别");
		tblInf.setModel(dtm);
		for (int i = 0; i < vCdata.size(); i++) {
			dtm.addColumn((String)vCdata.elementAt(i));
		}
		Vector vRdata = new Vector();
		vRdata.add("王飞");
		vRdata.add("111122197904292064");
		vRdata.add("男");
		dtm.addRow(vRdata);
	}
	//设置树
	public void setTree(){
		DefaultMutableTreeNode root;
		DefaultMutableTreeNode NodeName,NodeNumber,NodeSex;
		DefaultMutableTreeNode leafNme,leafNumber,LeafSex;
		root = new DefaultMutableTreeNode("个人信息");
		NodeName = new DefaultMutableTreeNode("姓名");
		leafNme = new DefaultMutableTreeNode("王飞");
		
		NodeNumber = new DefaultMutableTreeNode("身份证号");
		leafNumber = new DefaultMutableTreeNode("111122197904292064");
		NodeSex = new DefaultMutableTreeNode("性别");
		LeafSex = new DefaultMutableTreeNode("男");
		root.add(NodeName);
		root.add(NodeNumber);
		root.add(NodeSex);
		NodeName.add(leafNme);
		NodeNumber.add(leafNumber);
		NodeSex.add(LeafSex);
		//设置Tree渲染的基本属性
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setShowsRootHandles(true);
		//树不可以编辑
		tree.setEditable(false);
		tree.setModel(new DefaultTreeModel(root));		
	}
	
	public static void main(String [] args){
		TestSwing ts = new TestSwing();
		ts.setSize(400,450);
		ts.show();
	}
}

