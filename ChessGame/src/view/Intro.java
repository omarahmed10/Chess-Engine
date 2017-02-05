package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Intro extends JPanel implements Serializable, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image introBackGround;
	private JButton newGame, exit;
	private JFrame parent;

	public Intro(JFrame parent) {
		try {
			introBackGround = ImageLoader.loadImage("BackGround2.jpg").getImage();
			setLayout(null);
			setButtonProp(newGame, "New Game", 160);
			setButtonProp(exit, "Exit Game", 240);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.parent = parent;
	}

	private void setButtonProp(JButton button, String actionCommand, int y) {
		button = new JButton(actionCommand);
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBounds(130, y, 300, 80);
		button.addActionListener(this);
		button.setForeground(Color.LIGHT_GRAY);
		button.setFont(new Font("DejaVu Sans Condensed", Font.BOLD | Font.ITALIC, 30));
		add(button);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(introBackGround, 0, 0, getWidth(), getHeight(), this);
	}

	private void startGame() {
		removeAll();
		parent.remove(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("newGame")) {
			startGame();
		} else if (e.getActionCommand().equals("option")) {
			// new OptionScreen(this);
		} else if (e.getActionCommand().equals("exit")) {
			System.exit(0);
		}
	}
}
