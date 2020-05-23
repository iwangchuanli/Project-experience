package SY_8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.AlphaComposite;  
import java.awt.Color;  
import java.awt.Font;  
import java.awt.GradientPaint;  
import java.awt.Graphics;  
import java.awt.Graphics2D;  
import java.awt.RenderingHints;  
import java.awt.Shape;  
import java.awt.event.MouseAdapter;  
import java.awt.event.MouseEvent;  
import java.awt.geom.RoundRectangle2D;  
import javax.swing.JButton;  

class RButton extends JButton {  
    private static final long serialVersionUID = 39082560987930759L;  
    public static final Color BUTTON_COLOR1 = new Color(205, 255, 205);  
    public static final Color BUTTON_COLOR2 = new Color(51, 154, 47);  
    // public static final Color BUTTON_COLOR1 = new Color(125, 161, 237);  
    // public static final Color BUTTON_COLOR2 = new Color(91, 118, 173);  
    public static final Color BUTTON_FOREGROUND_COLOR = Color.WHITE;  
    private boolean hover;  
  
    public RButton(String name) {  
        this.setText(name);  
        setFont(new Font("system", Font.PLAIN, 12));  
        setBorderPainted(false);  
        setForeground(BUTTON_COLOR2);  
        setFocusPainted(false);  
        setContentAreaFilled(false);  
        addMouseListener(new MouseAdapter() {  
            @Override  
            public void mouseEntered(MouseEvent e) {  
                setForeground(BUTTON_FOREGROUND_COLOR);  
                hover = true;  
                repaint();  
            }  
  
            @Override  
            public void mouseExited(MouseEvent e) {  
                setForeground(BUTTON_COLOR2);  
                hover = false;  
                repaint();  
            }  
        });
    }
  
    @Override  
    protected void paintComponent(Graphics g) {  
        Graphics2D g2d = (Graphics2D) g.create();  
        int h = getHeight();  
        int w = getWidth();  
        float tran = 1F;  
        if (!hover) {  
            tran = 0.3F;  
        }  
  
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  
                RenderingHints.VALUE_ANTIALIAS_ON);  
        GradientPaint p1;  
        GradientPaint p2;  
        if (getModel().isPressed()) {  
            p1 = new GradientPaint(0, 0, new Color(0, 0, 0), 0, h - 1,  
                    new Color(100, 100, 100));  
            p2 = new GradientPaint(0, 1, new Color(0, 0, 0, 50), 0, h - 3,  
                    new Color(255, 255, 255, 100));  
        } else {  
            p1 = new GradientPaint(0, 0, new Color(100, 100, 100), 0, h - 1,  
                    new Color(0, 0, 0));  
            p2 = new GradientPaint(0, 1, new Color(255, 255, 255, 100), 0,  
                    h - 3, new Color(0, 0, 0, 50));  
        }  
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,  
                tran));  
        RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, w - 1,  
                h - 1, 20, 20);  
        Shape clip = g2d.getClip();  
        g2d.clip(r2d);  
        GradientPaint gp = new GradientPaint(0.0F, 0.0F, BUTTON_COLOR1, 0.0F,  
                h, BUTTON_COLOR2, true);  
        g2d.setPaint(gp);  
        g2d.fillRect(0, 0, w, h);  
        g2d.setClip(clip);  
        g2d.setPaint(p1);  
        g2d.drawRoundRect(0, 0, w - 1, h - 1, 20, 20);  
        g2d.setPaint(p2);  
        g2d.drawRoundRect(1, 1, w - 3, h - 3, 18, 18);  
        g2d.dispose();  
        super.paintComponent(g);  
    }  
}


class RePanel extends JPanel{
           Image im;
           public RePanel(Image im) {
             this.im = im;
             int width = Toolkit.getDefaultToolkit().getScreenSize().width;
             int height = Toolkit.getDefaultToolkit().getScreenSize().height;
             this.setSize(width,height);
             this.setLayout(null);
			 this.setOpaque(false);
              }
             @Override
              protected void paintComponent(Graphics g) {
              super.paintComponent(g);
              g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);
             }
}
public class Chess extends JFrame
{
	private RePanel p1 = new RePanel(new ImageIcon("images/5.png").getImage());
	private RePanel p2 = new RePanel(new ImageIcon("images/6.png").getImage()); 
	private JSplitPane sp1,sp2,sp3,sp4;
	private JPanel p3,p4,p5,p6,p7,p8;
	private JMenuBar mb = new JMenuBar();
	private JMenu[] menus ={new JMenu("游戏(G)"),new JMenu("帮助(H)")};
	private JLabel[] label = new JLabel[64];
	private ImageIcon[] image1 = new ImageIcon[8];
	private ImageIcon[] image2 = new ImageIcon[32];
	private ImageIcon[] image3 = new ImageIcon[12];
	private ImageIcon[] image4 = new ImageIcon[12];
	private int count1=0;       //黑色色图片
	private int count2=0;       //白色图片
	private int count3=0;       //红色棋子图片
	private int count4=0;       //白色棋子图片
	private JLabel
		label1 = new JLabel("红棋"),
		label2 = new JLabel("白棋"),
		label3 = new JLabel("聊天",SwingConstants.CENTER),
		label4 = new JLabel(new ImageIcon("images/10.png"),SwingConstants.CENTER),
		label5 = new JLabel(new ImageIcon("images/12.png"),SwingConstants.CENTER);
	private JRadioButton
		rb1 = new JRadioButton("开"),
		rb2 = new JRadioButton("关");
	private RButton
		b1 =new RButton("放弃(R)"),
		b2 =new RButton("和局(D)"),
		b3 =new RButton("微移(N)");
	private JTextPane t1 = new JTextPane();
	private JScrollPane js = new JScrollPane(t1);
	private JComboBox jc = new JComboBox();
	private DefaultListModel listModel = new DefaultListModel(); 
	private JScrollPane sourceListScroller;
	public static Chess c;
	public Chess()
	{
		setLayout(new BorderLayout());
		setTitle("Internet跳棋");
		setIconImage(new ImageIcon("images/7.png").getImage());

		for(JMenu menu:menus)
			mb.add(menu);
		setJMenuBar(mb);

		p3 = new JPanel(null);
		p4 = new JPanel(null);
		p5 = new JPanel(null);
		p6 = new JPanel();
		p7 = new JPanel(null);
		p8 = new JPanel();

		jc.addItem("(请选择你要发送的信息)");
		t1.setText("你的身份是..");
		t1.setEditable(false);

		p1.add(label1);
		p1.add(label4);
		p2.add(b1);p2.add(b2);p2.add(b3);
		p2.add(label2);
		p2.add(label5);

		p4.add(p6);
		p6.setLayout(new BoxLayout(p6, BoxLayout.Y_AXIS));
		p6.add(js);
		js.setPreferredSize(new Dimension(100,80));
		p6.add(jc);

		listModel.addElement("白棋(开)");
		listModel.addElement("红棋(开)");
		JList sourceList = new JList(listModel);
		sourceList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); 
		sourceListScroller = new JScrollPane(sourceList);

		p5.add(p7);
		p7.add(sourceListScroller); p7.add(p8);
		p8.setLayout(new BoxLayout(p8, BoxLayout.Y_AXIS));
		p8.add(label3);p8.add(rb1);p8.add(rb2);


		for(int j=0;j<3;j++)           //前三行
		{
			System.out.println(j);
			for(int i=8*j;i<8*j+8;i++)           
			{
			if(i%2==0&&j%2==0)              //偶数行
			{
				image2[count2] = new ImageIcon("images/9.png");
				label[i] = new JLabel(image2[count2],SwingConstants.CENTER);
				p3.add(label[i]);
				count2++;
			}
			else if(i%2!=0&&j%2==0)
			{
				image3[count3] = new ImageIcon("images/10.png");
				label[i] = new JLabel(image3[count3],SwingConstants.CENTER);
				p3.add(label[i]);
				count3++;
			}
			else if(i%2==0&&j%2!=0)          //奇数行
			{
				image3[count3] = new ImageIcon("images/10.png");
				label[i] = new JLabel(image3[count3],SwingConstants.CENTER);
				p3.add(label[i]);
				count3++;
			}
			else if(i%2!=0&&j%2!=0)
			{
				image2[count2] = new ImageIcon("images/9.png");
				label[i] = new JLabel(image2[count2],SwingConstants.CENTER);
				p3.add(label[i]);
				count2++;
			}
			}
		}
		for(int j=0;j<2;j++)
		{
		for(int i=24+8*j;i<24+8*j+8;i++)
		{
			if(i%2==0&&j%2==0)              //偶数行
			{
				image1[count1] = new ImageIcon("images/8.png");
				label[i] = new JLabel(image1[count1],SwingConstants.CENTER);
				p3.add(label[i]);
				count1++;
			}
			else if(i%2!=0&&j%2==0)
			{
				image2[count2] = new ImageIcon("images/9.png");
				label[i] = new JLabel(image2[count2],SwingConstants.CENTER);
				p3.add(label[i]);
				count2++;
			}
			else if(i%2==0&&j%2!=0)          //奇数行
			{
				image2[count2] = new ImageIcon("images/9.png");
				label[i] = new JLabel(image2[count2],SwingConstants.CENTER);
				p3.add(label[i]);
				count2++;
			}
			else if(i%2!=0&&j%2!=0)
			{
				image1[count1] = new ImageIcon("images/8.png");
				label[i] = new JLabel(image1[count1],SwingConstants.CENTER);
				p3.add(label[i]);
				count1++;
			}
		}
		}
		for(int j=0;j<3;j++)
		{
		for(int i=40+8*j;i<40+8*j+8;i++)
		{
			if(i%2==0&&j%2==0)              //偶数行
			{
				image4[count4] = new ImageIcon("images/12.png");
				label[i] = new JLabel(image4[count4],SwingConstants.CENTER);
				p3.add(label[i]);
				count4++;
			}
			else if(i%2!=0&&j%2==0)
			{
				image2[count2] = new ImageIcon("images/9.png");
				label[i] = new JLabel(image2[count2],SwingConstants.CENTER);
				p3.add(label[i]);
				count2++;
			}
			else if(i%2==0&&j%2!=0)          //奇数行
			{
				image2[count2] = new ImageIcon("images/9.png");
				label[i] = new JLabel(image2[count2],SwingConstants.CENTER);
				p3.add(label[i]);
				count2++;
			}
			else if(i%2!=0&&j%2!=0)
			{
				image4[count4] = new ImageIcon("images/12.png");
				label[i] = new JLabel(image4[count4],SwingConstants.CENTER);
				p3.add(label[i]);
				count4++;
			}
		}
		}


		sp4 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,false,p3,p2);
		sp3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,false,p4,p5);
		sp2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,false,p1,sp4);
		sp1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,false,sp2,sp3);
		
		sp1.setDividerSize(1);
		sp2.setDividerSize(1);
		sp3.setDividerSize(1);
		sp4.setDividerSize(1);

		label1.setFont(new Font("楷体",Font.BOLD,20));
		label2.setFont(new Font("楷体",Font.BOLD,20));

		p4.setBackground(new Color(0,0,0));
		p5.setBackground(new Color(0,0,0));
		p7.setBackground(new Color(0,0,0));
		p8.setBackground(new Color(0,0,0));

		rb1.setOpaque(false);rb2.setOpaque(false);
		label1.setForeground(new Color(255,255,255));
		label2.setForeground(new Color(255,255,255));
		label3.setForeground(new Color(255,255,255));
		rb1.setForeground(new Color(255,255,255));
		rb2.setForeground(new Color(255,255,255));

		add(sp1);

		addComponentListener(xl);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);      
        setSize(800,600);
		setUndecorated(false);
		setLocationRelativeTo(null);
        setVisible(true);

		sp1.addComponentListener(xl);
		sp2.addComponentListener(xl);
		sp3.addComponentListener(xl);
		sp4.addComponentListener(xl);
		p3.addComponentListener(yl);
		addComponentListener(xl);
	}
	public static void main(String args[])
	{
		try {  
           UIManager.setLookAndFeel(  
        UIManager.getSystemLookAndFeelClassName());   
        } catch (Exception e) {} 
		c = new Chess();
	}
	private ComponentAdapter xl = new ComponentAdapter()
	{
		public void componentResized(ComponentEvent e)
		{
			sp1.setDividerLocation(sp1.getHeight()-100);
			sp2.setDividerLocation(0.23);
			sp3.setDividerLocation(sp1.getWidth()-200);
			sp4.setDividerLocation(0.7);

			p6.setBounds(10,10,p4.getWidth()-20,p4.getHeight()-20);
			p7.setBounds(10,10,p5.getWidth()-30,p5.getHeight()-20);

			sourceListScroller.setBounds(20,10,60,60);
			p8.setBounds(100,10,60,70);


			b1.setBounds(p2.getWidth()/4,p2.getHeight()/4,100,30);
			b2.setBounds(p2.getWidth()/4,p2.getHeight()/4+50,100,30);
			b3.setBounds(p2.getWidth()/4,p2.getHeight()/4+100,100,30);
			label2.setBounds(p2.getWidth()/4+20,p2.getHeight()/2+50,60,30);
			label1.setBounds(p1.getWidth()/2-30,40,60,30);

			label4.setBounds(p1.getWidth()/2-56,100,100,30);
			label5.setBounds(p2.getWidth()/4-10,p2.getHeight()/2+100,100,30);
		}
	};
	private ComponentAdapter yl = new ComponentAdapter()
	{
		public void componentResized(ComponentEvent e)
		{
			for(int i=0;i<8;i++)
			image1[i].setImage(image1[i].getImage().getScaledInstance(p3.getWidth()/8,p3.getWidth()/8,Image.SCALE_DEFAULT));
			for(int i=0;i<32;i++)
			image2[i].setImage(image2[i].getImage().getScaledInstance(p3.getWidth()/8,p3.getWidth()/8,Image.SCALE_DEFAULT));
			for(int i=0;i<12;i++)
			image3[i].setImage(image3[i].getImage().getScaledInstance(p3.getWidth()/8,p3.getWidth()/8,Image.SCALE_DEFAULT));
			for(int i=0;i<12;i++)
			image4[i].setImage(image4[i].getImage().getScaledInstance(p3.getWidth()/8,p3.getHeight()/8,Image.SCALE_DEFAULT));

			for(int j=0;j<8;j++)
			{
				int k=0;
				for(int i=j*8;i<j*8+8;i++)
				{
					label[i].setBounds(k*p3.getWidth()/8-10,j*p3.getHeight()/8-10,p3.getWidth()/8+20,p3.getHeight()/8+20);
					k++;
				}
			}
		}
	};
}
