package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import chessBoard.Tile;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @throws InterruptedException
	 */
	public MainFrame() throws InterruptedException {
		initialize();
	}

	private void initialize() throws InterruptedException {
		setBounds(200, 100, 500, 100 + 8 * Tile.TILEWIDTH);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Intro intro = new Intro(this);
		// intro.setSize(getWidth(), getHeight());
		// add(intro);
		setLayout(null);
		JPanel b = new Board();
		b.setSize(getWidth(), getHeight());
		add(b);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
