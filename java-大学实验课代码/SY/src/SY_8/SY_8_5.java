package SY_8;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;


class WindowMenu extends JFrame {
	JMenuBar menubar;//创建菜单条
	JMenu files,edit,formats,view,helps;//创建菜单
	JMenuItem newFile,open,save,saveAs,pageSetting,print,exit;//新建，打开，保存，另存为，页面设置，打印，退出
	JMenuItem undo,cut,copy,paste,delete,find,findNext,replace,goTo,selectAll,timeDate;//撤销，剪切，复制，粘贴，删除，查找，查找下一个，替换，转到，全选，时间/日期
	JMenuItem lineWrap,fonts;//自动换行，字体
	JMenuItem state;//状态栏
	JMenuItem findHelp,about;//查看帮助，关于记事本
	JTextArea textArea;//创建文本区
	public WindowMenu(){
		init();
		setVisible(true);
		setIconImage(new ImageIcon("images/记事本.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	void init(){
		menubar=new JMenuBar();//初始化菜单条
		menubar.setBackground(Color.white);//将菜单条的背景设置成白色
		textArea=new JTextArea();//初始化文本区
		add(new JScrollPane(textArea));//在滚动窗格中添加文本区
		//添加“文件”菜单
		files=new JMenu("文件(F)");
		files.setFont(new Font("幼圆",0,14));//设置字体格式和大小
		newFile=new JMenuItem("新建(N)");//在“文件”菜单中添加子菜单“新建”
		newFile.setFont(new Font("幼圆",0,14));
		newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));//设置快捷键
		files.add(newFile);
		open=new JMenuItem("打开(O)...");//在“文件”菜单中添加子菜单“打开”
		open.setFont(new Font("幼圆",0,14));
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));//设置快捷键
		files.add(open);
		save=new JMenuItem("保存(S)");//在“文件”菜单中添加子菜单“保存”
		save.setFont(new Font("幼圆",0,14));
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));//设置快捷键
		files.add(save);
		saveAs=new JMenuItem("另存为(A)");//在“文件”菜单中添加子菜单“另存为”
		saveAs.setFont(new Font("幼圆",0,14));
		files.add(saveAs);
		files.addSeparator();//添加分隔线
		pageSetting=new JMenuItem("页面设置(U)...");//在“文件”菜单中添加子菜单“页面设置”
		pageSetting.setFont(new Font("幼圆",0,14));
		files.add(pageSetting);
		print=new JMenuItem("打印(P)...");//在“文件”菜单中添加子菜单“打印”
		print.setFont(new Font("幼圆",0,14));
		print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,InputEvent.CTRL_MASK));//设置快捷键
		files.add(print);
		files.addSeparator();//添加分隔线
		exit=new JMenuItem("退出(X)");
		exit.setFont(new Font("幼圆",0,14));
		files.add(exit);
		//添加”编辑“菜单
		edit=new JMenu("编辑(E)");
		edit.setFont(new Font("幼圆",0,14));
		undo=new JMenuItem("撤销(U)");
		undo.setFont(new Font("幼圆",0,14));
		undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,InputEvent.CTRL_MASK));//设置快捷键
		edit.add(undo);
		edit.addSeparator();
		cut=new JMenuItem("剪切(T)");
		cut.setFont(new Font("幼圆",0,14));
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK));//设置快捷键
		edit.add(cut);
		copy=new JMenuItem("复制(C)");
		copy.setFont(new Font("幼圆",0,14));
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));//设置快捷键
		edit.add(copy);
		paste=new JMenuItem("粘贴(P)");
		paste.setFont(new Font("幼圆",0,14));
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));//设置快捷键
		edit.add(paste);
		delete=new JMenuItem("删除(L)");
		delete.setFont(new Font("幼圆",0,14));
		delete.setAccelerator(KeyStroke.getKeyStroke("Del"));
		edit.add(delete);
		edit.addSeparator();//添加分隔线
		find=new JMenuItem("查找(F)...");
		find.setFont(new Font("幼圆",0,14));
		find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_MASK));//设置快捷键
		edit.add(find);
		findNext=new JMenuItem("查找下一个(N)");
		findNext.setFont(new Font("幼圆",0,14));
		findNext.setAccelerator(KeyStroke.getKeyStroke("F3"));//设置快捷键
		edit.add(findNext);
		replace=new JMenuItem("替换(R)...");
		replace.setFont(new Font("幼圆",0,14));
		replace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,InputEvent.CTRL_MASK));//设置快捷键
		edit.add(replace);
		goTo=new JMenuItem("转到(G)...");
		goTo.setFont(new Font("幼圆",0,14));
		goTo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,InputEvent.CTRL_MASK));//设置快捷键
		edit.add(goTo);
		edit.addSeparator();
		selectAll=new JMenuItem("全选(A)");
		selectAll.setFont(new Font("幼圆",0,14));
		selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_MASK));//设置快捷键
		edit.add(selectAll);
		timeDate=new JMenuItem("时间/日期(D)");
		timeDate.setFont(new Font("幼圆",0,14));
		timeDate.setAccelerator(KeyStroke.getKeyStroke("F5"));//设置快捷键
		edit.add(timeDate);
		//添加”格式“菜单
		formats=new JMenu("格式(O)");
		formats.setFont(new Font("幼圆",0,14));
		lineWrap=new JMenuItem("自动换行(W)");
		lineWrap.setFont(new Font("幼圆",0,14));
		formats.add(lineWrap);
		fonts=new JMenuItem("字体(F)...");
		fonts.setFont(new Font("幼圆",0,14));
		formats.add(fonts);
		//添加“查看”菜单
		view=new JMenu("查看(V)");
		view.setFont(new Font("幼圆",0,14));
		state=new JMenuItem("状态栏(S)");
		state.setFont(new Font("幼圆",0,14));
		view.add(state);
		//添加”帮助“菜单
		helps=new JMenu("帮助(H)");
		helps.setFont(new Font("幼圆",0,14));
		findHelp=new JMenuItem("查看帮助(H)");
		findHelp.setFont(new Font("幼圆",0,14));
		helps.add(findHelp);
		about=new JMenuItem("关于记事本(A)");
		about.setFont(new Font("幼圆",0,14));
		helps.add(about);
		menubar.add(files);
		menubar.add(edit);
		menubar.add(formats);
		menubar.add(view);
		menubar.add(helps);
		setJMenuBar(menubar);
	}
}

public class SY_8_5 {
	public static void main(String[] args){
		WindowMenu win=new WindowMenu();
		win.setBounds(300,200,1000,800);
		win.setTitle("无标题-记事本");
	}
}
