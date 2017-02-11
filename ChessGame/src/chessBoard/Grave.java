package chessBoard;

import javax.swing.JPanel;
import javax.swing.UIManager;

public class Grave extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected int graveWidth;

	protected Grave(int x, int y) {
		setBounds(x, y, 8 * Tile.TILEWIDTH, 50);
		setBackground(UIManager
				.getColor("OptionPane.warningDialog.titlePane.background"));
	}
}
