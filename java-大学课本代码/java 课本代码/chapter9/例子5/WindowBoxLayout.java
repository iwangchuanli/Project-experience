import javax.swing.*;
public class WindowBoxLayout extends JFrame  {
    Box baseBox,boxV1,boxV2; 
    public WindowBoxLayout() {
        setLayout(new java.awt.FlowLayout());
        init();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void init() {
        boxV1=Box.createVerticalBox();
        boxV1.add(new JLabel("ÐÕÃû"));
        boxV1.add(Box.createVerticalStrut(8));
        boxV1.add(new JLabel("email"));
        boxV1.add(Box.createVerticalStrut(8));
        boxV1.add(new JLabel("Ö°Òµ"));
        boxV2=Box.createVerticalBox();
        boxV2.add(new JTextField(10));
        boxV2.add(Box.createVerticalStrut(8));
        boxV2.add(new JTextField(10));
        boxV2.add(Box.createVerticalStrut(8));
        boxV2.add(new JTextField(10));
        baseBox=Box.createHorizontalBox();
        baseBox.add(boxV1);
        baseBox.add(Box.createHorizontalStrut(10));
        baseBox.add(boxV2);
        add(baseBox);       
    }
}
