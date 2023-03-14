package S;
 
import java.awt.Color; 
import java.awt.FlowLayout; 
import java.awt.Graphics; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; 

import javax.swing.JButton;
import javax.swing.JPanel;

public class snackWin extends JPanel implements ActionListener, KeyListener {
    static final int Up = 0 , Down = 1 , Left = 2 , Right = 3;
    static final int GameLocX = 50 , GameLocY = 50 , GameWidth = 700 , GameHeight = 500 , Size = 10;
    static int rx , ry , score = 0 , speed = 5;
    boolean startFlag = false;
    JButton startButton , stopButton , quitButton;
    snack snack;
    public snackWin() {
        snack = new snack();
        rx = (int)(Math.random() * (GameWidth - 10) + GameLocX);
        ry = (int)(Math.random() * (GameHeight - 10) + GameLocY);
        startButton = new JButton("开始");
        stopButton = new JButton("暂停");
        quitButton = new JButton("退出");
        setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(startButton);
        this.add(stopButton);
        this.add(quitButton);
        startButton.addActionListener(this);
        stopButton.addActionListener(this);
        quitButton.addActionListener(this);
        this.addKeyListener(this);
        new Thread(new snackThread()).start();  
    }
    public void paint(Graphics g)
    {
        super.paint(g);   //没有会将button刷掉
        g.setColor(Color.white);
        g.fillRect(GameLocX, GameLocY, GameWidth, GameHeight);  //刷新界面
        g.setColor(Color.black);
        g.drawRect(GameLocX, GameLocY, GameWidth, GameHeight);  //绘制边界
        g.drawString("Score: " + score + "        Speed: " + speed + "      速度最大为100" , 250, 25);
        g.setColor(Color.green);
        g.fillRect(rx, ry, 10, 10);   //食物
        snack.draw(g);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton) {
            startFlag = true;
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
        }
        if(e.getSource() == stopButton) {
            startFlag = false;
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        }
        if(e.getSource() == quitButton) {
            System.exit(0);
        }
        this.requestFocus();
    }
    public void keyPressed(KeyEvent e) {
        //System.out.println(e.getKeyCode()); 
        if(!startFlag) return ;
        switch(e.getKeyCode()) {
        case KeyEvent.VK_UP:
            if(snack.length() != 1 && snack.getDir() == Down) break;
            snack.changeDir(Up);
            break;
        case KeyEvent.VK_DOWN:
            if(snack.length() != 1 && snack.getDir() == Up) break;
            snack.changeDir(Down);
            break;
        case KeyEvent.VK_LEFT:
            if(snack.length() != 1 && snack.getDir() == Right) break;
            snack.changeDir(Left);
            break;
        case KeyEvent.VK_RIGHT:
            if(snack.length() != 1 && snack.getDir() == Left) break;
            snack.changeDir(Right);
            break;
        } 
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    class snackThread implements Runnable
    {
        public void run() {
            while(true) {
                try {
                    Thread.sleep(100 - speed >= 0 ? 100 - speed : 0);
                    repaint();
                    if(startFlag) {
                        snack.move();
                    }
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}