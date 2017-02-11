package chessBoard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import javax.swing.UIManager;

import pieces.Piece;

/**
 * this is the class of single chess tile. the tile is empty on creation until a
 * new piece is given to it.
 * 
 * @author omar
 *
 */
public class Tile implements MouseListener {
	public final static int TILEWIDTH = 50;
	private final Point tileCoordinate;
	private Piece myPiece;
	private final String myPosition;
	private final int myColor;
	private ChessBoard parent;
	private final Rectangle bound;
	/**
	 * this boolean means that this tile is an avaliable tile for the next move.
	 */
	private boolean avaliable, selected;

	protected Tile(ChessBoard parent, Point tileCoordinate, String position,
			int color) {
		this.tileCoordinate = tileCoordinate;
		this.myPosition = position;
		this.myColor = color;
		avaliable = false;
		parent.addMouseListener(this);
		this.parent = parent;
		bound = new Rectangle(tileCoordinate.x, tileCoordinate.y, TILEWIDTH,
				TILEWIDTH);
	}

	public void setPiece(Piece piece) {
		this.myPiece = piece;
	}

	public boolean hasPiece() {
		return myPiece != null;
	}

	public Piece getPiece() {
		return myPiece;
	}

	public String getPostion() {
		return myPosition;
	}

	public Point getCoordinate() {
		return tileCoordinate;
	}

	public void setAvaliability(boolean flag) {
		avaliable = flag;
		selected = flag;
	}

	public void draw(Graphics g) {
		if (myColor == 1) {
			g.setColor(UIManager
					.getColor("OptionPane.warningDialog.titlePane.shadow"));
		} else {
			g.setColor(UIManager
					.getColor("OptionPane.warningDialog.titlePane.foreground"));
		}

		g.fillRect(tileCoordinate.x, tileCoordinate.y, TILEWIDTH, TILEWIDTH);

		g.setColor(Color.black);
		g.setFont(
				new Font("DejaVu Sans Condensed", Font.BOLD | Font.ITALIC, 10));
		g.drawString(myPosition, tileCoordinate.x + TILEWIDTH - 15,
				tileCoordinate.y + TILEWIDTH - 5);
		if (avaliable) {
			g.setColor(Color.green);

			g.fillOval(tileCoordinate.x, tileCoordinate.y, TILEWIDTH / 4,
					TILEWIDTH / 4);
			avaliable = false;
		}
		if (hasPiece()) {
			myPiece.draw(g);
		}
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		if (bound.contains(e.getPoint())) {
			if (hasPiece()) {
				if (parent.markAvaliableTiles(myPiece,
						myPiece.getAvailablePositions())) {
					System.out.println(myPiece.getAvailablePositions() + "  "
							+ myPiece.getClass().getName());
				} else {
					// attack this is an enemy.
					parent.moveSelectedPiece(myPosition);
				}
			} else if (!hasPiece() && selected) {
				selected = false;
				parent.moveSelectedPiece(myPosition);
			}
		}
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
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
