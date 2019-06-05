package com.library.manager;

import java.awt.*;
import javax.swing.*;

public class MyContentPane extends JPanel {
	public ImageIcon img;
	/**
	 * 设置背景图和背景图的透明度alpha，0为全透明，1.0f为不透明。
	 */
	private float alpha;

	public MyContentPane(String bgPath, float alpha) {
		super();
		img = new ImageIcon(Test.class.getResource(bgPath));
		this.alpha = alpha;
	}

	@Override
	protected void paintComponent(Graphics g) {
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
		Composite old = ((Graphics2D) g).getComposite();
		((Graphics2D) g).setComposite(ac);
		if (img != null) {
			g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), this);
		}
		((Graphics2D) g).setComposite(old);
		super.paintComponent(g);
	}

}
