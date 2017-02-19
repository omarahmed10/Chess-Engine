package chessBoard;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.event.MouseInputAdapter;

import pieces.Piece;

public class Grave extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected int graveWidth;
	// private List<Piece> deadPieces;
	private List<JButton> deadPieces;

	protected Grave(int x, int y) {
		setBounds(x, y, 8 * Tile.TILEWIDTH, Piece.PIECE_HEIGHT);
		setBackground(UIManager
				.getColor("OptionPane.warningDialog.titlePane.background"));
		deadPieces = new LinkedList<JButton>();
		graveWidth = 0;
	}

	protected void addDeadPiece(Piece deadPiece) {
		Image img = deadPiece.getImage();
		Image newimg = img.getScaledInstance(Piece.PIECE_WIDTH,
				Piece.PIECE_HEIGHT, java.awt.Image.SCALE_SMOOTH);
		JButton button = new JButton(new ImageIcon(newimg));
		setButtonProp(button);
		button.setBounds(getX() + graveWidth, 0, Piece.PIECE_WIDTH,
				Piece.PIECE_HEIGHT);
		graveWidth += button.getWidth() + 10;
		deadPieces.add(button);
		addButtons();
		this.repaint();
	}

	private void setButtonProp(JButton button) {
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.addMouseListener(new MouseInputAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Done");
			}

		});
	}

	private void addButtons() {
		for (JButton b : deadPieces) {
			add(b);
		}
	}

}
