package S;
 
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
   
class Node { 
    private int x , y;  
    public Node() {} 
    public Node(int a , int b) {x = a; y = b;}
    public Node(Node tmp) {x = tmp.getX(); y = tmp.getY();}
    int getX() {return x;}
    int getY() {return y;}
    void setX(int a) {x = a;}
    void setY(int b) {y = b;}
}
public class snack {
    static final int DIR[][] = {{0 , -1} , {0 , 1} , {-1 , 0} , {1 , 0}};
    private List<Node> lt = new ArrayList<Node>();
    private int curDir;
    public snack() {
        curDir = 3; 
        lt.add(new Node(350 , 250));
    }
    int length() {return lt.size();}
    int getDir() {return curDir;}
    void draw(Graphics g) 
    {
        g.setColor(Color.black);
        for(int i = 0; i < lt.size(); i++) {
            g.fillRect(lt.get(i).getX(), lt.get(i).getY(), 10, 10);
        }
    }
    boolean Dead() {
        for(int i = 1; i < lt.size(); i++) {
            if(lt.get(0).getX() == lt.get(i).getX() && lt.get(0).getY() == lt.get(i).getY()) 
                return true;
        }
        return false;
    }
    Node headMove()
    {
        int tx = lt.get(0).getX() + snackWin.Size * DIR[curDir][0];
        int ty = lt.get(0).getY() + snackWin.Size * DIR[curDir][1];
        if(tx > snackWin.GameLocX + snackWin.GameWidth - snackWin.Size) tx = snackWin.GameLocX;
        if(tx < snackWin.GameLocX) tx = snackWin.GameWidth + snackWin.GameLocX - snackWin.Size;
        if(ty > snackWin.GameLocY + snackWin.GameHeight - snackWin.Size) ty = snackWin.GameLocY;
        if(ty < snackWin.GameLocY) ty = snackWin.GameHeight + snackWin.GameLocY - snackWin.Size;
        Node tmp = new Node(tx, ty);
        return tmp;
    }
    void move()
    {
        Node head = headMove() , newNode = new Node();
        boolean eat = false;
        if(Math.abs(head.getX() - snackWin.rx) < 10 && Math.abs(head.getY() - snackWin.ry) < 10) {
            eat = true;
            newNode = new Node(lt.get(lt.size() - 1));
            snackWin.rx = (int)(Math.random() * (snackWin.GameWidth - 10) + snackWin.GameLocX);
            snackWin.ry = (int)(Math.random() * (snackWin.GameHeight - 10) + snackWin.GameLocY);
        }
        for(int i = lt.size() - 1; i > 0; i--) 
            lt.set(i, lt.get(i - 1));
        lt.set(0, head);
        if(Dead()) {
            JOptionPane.showMessageDialog(null, "Snake eating itself", "Message", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        if(eat) {
            lt.add(newNode);
            snackWin.score++;
            snackWin.speed++;
        }
    }
    void changeDir(int dir) {curDir = dir;}
}