package SY_8;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BMI extends JFrame {

private static final long serialVersionUID = -2317974201224415240L;
private JLabel lblHeight;
private JLabel lblWeight;
private JLabel lblBMI;
private JLabel lblResult;
private JButton btnRun;
private JPanel pnlMain;
private JTextField txtHeight;
private JTextField txtWeight;
private JTextField txtBMI;
private JTextField txtResult;
DecimalFormat dformat = new DecimalFormat("#.00");

public BMI() {
    lblHeight = new JLabel("身高(米/m)");
    txtHeight = new JTextField(10);
    lblWeight = new JLabel("体重(千克/kg)");
    txtWeight = new JTextField(10);
    lblBMI = new JLabel("健康值(BMI)");
    txtBMI = new JTextField(10);
    lblResult = new JLabel("结果");
    txtResult = new JTextField(10);
    btnRun = new JButton("评估");
    pnlMain = new JPanel();
    pnlMain.setLayout(null);
    lblHeight.setBounds(100, 50, 80, 25);
    txtHeight.setBounds(200, 50, 100, 25);
    lblWeight.setBounds(100, 80, 80, 25);
    txtWeight.setBounds(200, 80, 100, 25);
    lblBMI.setBounds(100, 110, 80, 25);
    txtBMI.setBounds(200, 110, 100, 25);
    lblResult.setBounds(100, 170, 80, 25);
    txtResult.setBounds(200, 170, 100, 25);
    btnRun.setBounds(150, 140, 80, 25);

    pnlMain.add(lblHeight);
    pnlMain.add(txtHeight);
    pnlMain.add(lblWeight);
    pnlMain.add(txtWeight);
    pnlMain.add(lblBMI);
    pnlMain.add(txtBMI);
    pnlMain.add(lblResult);
    pnlMain.add(txtResult);
    pnlMain.add(btnRun);
    this.setContentPane(pnlMain);
    setSize(400, 300);
    setTitle("健康评估");
    setVisible(true);
    setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    btnRun.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if ("".equals(txtHeight.getText())) {
                JOptionPane.showMessageDialog(null, "身高为空，请输入一个正值身高！");
                return;
            }
            if ("".equals(txtWeight.getText())) {
                JOptionPane.showMessageDialog(null, "体重为空，请输入一个正值体重！");
                return;
            }
            double height = Double.valueOf(txtHeight.getText());
            if (height <= 0) {
                JOptionPane.showMessageDialog(null, "身高低于o.1cm，请输入一个正值身高。");
                return;
            }
            double weight = Double.valueOf(txtWeight.getText());
            if (weight <= 0) {
                JOptionPane.showMessageDialog(null, "体重小于0.1kg，请输入一个正值体重。");
                return;
            }
            // BMl＝[体重(千克)／身高(米)]/2
            double bmi = weight / height / 2;
            txtBMI.setText(dformat.format(bmi));
            txtResult.setText(getResult(bmi));
        }

    });
}

private String getResult(double bmi) {
    // BMl＜18.5为消瘦；BMl 在18.5～24.9之间为正常；BMl≥25为超重；
    if (bmi < 18.5) {
        return "瘦";
    } else if (bmi >= 18.5 && bmi < 25) {
        return "正常";
    } else {
        return "胖";
    }
}


public static void main(String[] args) {
    new BMI();
}
}
