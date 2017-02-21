package chessBoard;

import java.util.Map;
import pieces.Piece;
import player.Player;

public class Move {
	public final static int MOVE = 0;
	public final static int ATTACK = 1;
	public final static Map<String, Tile> MOVE_FAILED = null;
	private String toPosition, fromPosition;
	private int motionOutput;
	private Piece requiredToMove, killedPiece;

	public Move(Piece requiredToMove, String toPosition) {
		this.toPosition = toPosition;
		this.requiredToMove = requiredToMove;
		fromPosition = requiredToMove.getPosition();
	}

	public void doMove(Player opponentPlayer) {
		if (!requiredToMove.isDead()) {

			if (requiredToMove.hasMoveTo(toPosition) != null) {

				// if this position has an enemy , then we send it to the grave
				// yard
				if (ChessTiles.getSquareStatus(requiredToMove,
						toPosition) == Tile.HAS_ENEMY) {
					killedPiece = ChessTiles.getBoardTiles().get(toPosition)
							.getPiece();
					opponentPlayer.addDeadPiece(killedPiece);
					motionOutput = ATTACK;
				} else {
					motionOutput = MOVE;
				}
				Map<String, Tile> board = ChessTiles.getBoardTiles();
				Tile oldTile = board.get(requiredToMove.getPosition());
				oldTile.setPiece(null);
				Tile newTile = board.get(toPosition);
				newTile.setPiece(requiredToMove);
				requiredToMove.setPosition(toPosition, newTile.getCoordinate());
			}
		}
	}

	/*
	 * for check mate and stale mate and Undo Action for Game.
	 */
	public void undoMove(Player opponentPlayer) {
		String oldPosition = fromPosition, currentPosition = toPosition;
		if (isDone()) {
			if (isAttack()) {
				opponentPlayer.awakeLastDeadPiece(currentPosition, killedPiece);
			}
			Map<String, Tile> board = ChessTiles.getBoardTiles();
			Tile oldTile = board.get(currentPosition);
			oldTile.setPiece(null);
			Tile newTile = board.get(oldPosition);
			newTile.setPiece(requiredToMove);
			requiredToMove.setPosition(oldPosition, newTile.getCoordinate());
		}
	}

	/*
	 * still under construction for AI only.
	 */
//	public Map<String, Tile> dofakeMove(Player opponentPlayer) {
//		if (!requiredToMove.isDead()) {
//			try {
//				Piece fakePiece = (Piece) requiredToMove.clone();
//				Player fakePlayer = (Player) opponentPlayer.clone();
//				if (fakePiece.hasMoveTo(toPosition) != null) {
//
//					// if this position has an enemy , then we send it to the
//					// grave
//					// yard
//					if (ChessTiles.getSquareStatus(fakePiece,
//							toPosition) == Tile.HAS_ENEMY) {
//						fakePlayer.addDeadPiece(ChessTiles.getBoardTiles()
//								.get(toPosition).getPiece());
//						motionOutput = ATTACK;
//					} else {
//						motionOutput = MOVE;
//					}
//					Map<String, Tile> board = ChessBoard
//							.getCopy(ChessTiles.getBoardTiles());
//					Tile oldTile = board.get(fakePiece.getPosition());
//					oldTile.setPiece(null);
//					Tile newTile = board.get(toPosition);
//					newTile.setPiece(requiredToMove);
//					fakePiece.setPosition(toPosition, newTile.getCoordinate());
//					return board;
//				}
//			} catch (CloneNotSupportedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return MOVE_FAILED;
//	}

	public String getToPosition() {
		return toPosition;
	}

	public Piece getRequiredPiece() {
		return requiredToMove;
	}

	public boolean isDone() {
		return motionOutput == MOVE || motionOutput == ATTACK;
	}

	public boolean isAttack() {
		return motionOutput == ATTACK;
	}

	@Override
	public String toString() {
		return toPosition;
	}
}
