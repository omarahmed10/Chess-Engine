package chess;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * this is the class of single chess tile. the tile is empty on creation until a
 * new piece is given to it.
 * 
 * @author omar
 *
 */
public class Tile implements MouseListener {
	protected final static int TILEWIDTH = 50;
	private final Point tileCoordinate;
	private Piece myPiece;
	private final String myPosition;
	private final int myColor;
	private JPanel parent;
	/**
	 * this boolean means that this tile is an avaliable tile for the next move.
	 */
	private boolean selected;

	protected Tile(JPanel parent, Point tileCoordinate, String position,
			int color) {
		this.tileCoordinate = tileCoordinate;
		this.myPosition = position;
		this.myColor = color;
		selected = false;
		parent.addMouseListener(this);
		this.parent = parent;
	}

	protected void setPiece(Piece piece) {
		this.myPiece = piece;
	}

	protected boolean hasPiece() {
		return myPiece != null;
	}

	protected Piece getPiece() {
		return myPiece;
	}

	protected String getPostion() {
		return myPosition;
	}

	protected Point getCoordinate() {
		return tileCoordinate;
	}

	protected void draw(Graphics g) {
		if (myColor == 1)
			g.setColor(UIManager
					.getColor("OptionPane.warningDialog.titlePane.shadow"));
		else
			g.setColor(UIManager
					.getColor("OptionPane.warningDialog.titlePane.foreground"));

		g.fillRect(tileCoordinate.x, tileCoordinate.y, TILEWIDTH, TILEWIDTH);
		
		g.setColor(Color.black);
		g.setFont(new Font("DejaVu Sans Condensed", Font.BOLD | Font.ITALIC, 10));
		g.drawString(myPosition, tileCoordinate.x + TILEWIDTH - 15,
				tileCoordinate.y + TILEWIDTH - 5);
		if (selected && !hasPiece()) {
			g.setColor(Color.green);

			g.fillOval(tileCoordinate.x + TILEWIDTH / 4,
					tileCoordinate.y + TILEWIDTH / 4, TILEWIDTH / 4,
					TILEWIDTH / 4);
		}
		if (hasPiece()) {
			myPiece.draw(g);
		}
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

		Rectangle rec = new Rectangle(tileCoordinate.x, tileCoordinate.y,
				TILEWIDTH, TILEWIDTH);
		if (rec.contains(e.getPoint())) {
			selected = true;
			System.out.println(myPosition);
			parent.repaint();
		}
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
