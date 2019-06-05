
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class JavaIDE {
	public static void main(String[] args) {
		FileWindow win = new FileWindow();
		win.pack();
		win.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		win.setBounds(200, 180, 550, 360);
		win.setVisible(true);
	}
}

@SuppressWarnings("serial")
class FileWindow extends JFrame implements ActionListener, Runnable {
	Thread compiler = null;
	Thread run_prom = null;
	boolean bn = true;
	CardLayout mycard; 
	File file_saved = null;
	JButton button_input_text, 
			button_compiler_text, button_compiler, button_run_prom, button_see_doswin;

	JPanel p = new JPanel();
	JTextArea input_text = new JTextArea();
	JTextArea compiler_text = new JTextArea();
	JTextArea dos_out_text = new JTextArea();
	JTextField input_file_name_text = new JTextField();
	JTextField run_file_name_text = new JTextField();

	public FileWindow() {
		super("JavaIDE");
		mycard = new CardLayout();
		compiler = new Thread(this);
		run_prom = new Thread(this);
		button_input_text = new JButton("ProgramInput(white)");
		button_compiler_text = new JButton("Compiler(pick)");
		button_see_doswin = new JButton("Run(blue)");
		button_compiler = new JButton("compiler");
		button_run_prom = new JButton("run");

		p.setLayout(mycard);
		p.add("input", input_text);
		p.add("compiler", compiler_text);
		p.add("dos", dos_out_text);
		add(p, "Center");

		compiler_text.setBackground(Color.pink);
		dos_out_text.setBackground(Color.cyan);
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(3, 3));

		p1.add(button_input_text);
		p1.add(button_compiler_text);
		p1.add(button_see_doswin);
		p1.add(new JLabel("Input File Name(.java):"));
		p1.add(input_file_name_text);
		p1.add(button_compiler);
		p1.add(new JLabel("Input Class Name"));
		p1.add(run_file_name_text);
		p1.add(button_run_prom);
		add(p1, "North");


		button_input_text.addActionListener(this);
		button_compiler_text.addActionListener(this);
		button_compiler.addActionListener(this);
		button_run_prom.addActionListener(this);
		button_see_doswin.addActionListener(this);

	}

	public void run() {
		if (Thread.currentThread() == compiler) {
			compiler_text.setText(null);
			String temp = input_text.getText().trim();
			byte[] buffer = temp.getBytes();
			int b = buffer.length;
			String file_name = null;
			file_name = input_file_name_text.getText().trim();

			try {
				file_saved = new File(file_name);
				FileOutputStream writefile = null;
				writefile = new FileOutputStream(file_saved);
				writefile.write(buffer, 0, b);
				writefile.close();
			} catch (Exception e) {
				System.out.println("ERROR");
			}
			try {
				
				Runtime rt = Runtime.getRuntime();
				InputStream in = rt.exec("javac " + file_name).getErrorStream();
				BufferedInputStream bufIn = new BufferedInputStream(in);
				byte[] shuzu = new byte[100];
				int n = 0;
				boolean flag = true;
				while ((n = bufIn.read(shuzu, 0, shuzu.length)) != -1) {
					String s = null;
					s = new String(shuzu, 0, n);
					compiler_text.append(s); 
					if (s != null) {
						flag = false;
					}
				}
				if (flag) { 
					compiler_text.append("Compiler Succeed!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (Thread.currentThread() == run_prom) {
			dos_out_text.setText(null);
			try {
				Runtime rt = Runtime.getRuntime();
				String path = run_file_name_text.getText().trim();
				Process stream = rt.exec("java " + path);
				InputStream in = stream.getInputStream();
				BufferedInputStream bisErr = new BufferedInputStream(stream.getErrorStream());
				BufferedInputStream bisIn = new BufferedInputStream(in);

				byte[] buf = new byte[150];
				byte[] err_buf = new byte[150];

				@SuppressWarnings("unused")
				int m = 0;
				@SuppressWarnings("unused")
				int i = 0;
				String s = null;
				String err = null;
				while ((m = bisIn.read(buf, 0, 150)) != -1) {
					s = new String(buf, 0, 150);
					dos_out_text.append(s);
				}
				while ((i = bisErr.read(err_buf)) != -1) {
					err = new String(err_buf, 0, 150);
					dos_out_text.append(err);
				}
			} catch (Exception e) {

			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button_input_text) {
			mycard.show(p, "input");
		} else if (e.getSource() == button_compiler_text) {
			mycard.show(p, "compiler");
		} else if (e.getSource() == button_see_doswin) {
			mycard.show(p, "dos");
		} else if (e.getSource() == button_compiler) {
			if (!(compiler.isAlive())) {
				compiler = new Thread(this);
			}
			try {
				compiler.start();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			mycard.show(p, "compiler");
		} else if (e.getSource() == button_run_prom) {
			if (!(run_prom.isAlive())) {
				run_prom = new Thread(this);
			}
			try {
				run_prom.start();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			mycard.show(p, "dos");
		}
	}

}
