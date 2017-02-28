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

	/**
	 * Do standard Move.
	 * 
	 * @param opponentPlayer
	 */
	public void doMove(Player opponentPlayer) {
		makeMove(ChessTiles.getBoardTiles(), opponentPlayer);
	}

	/*
	 * still under construction for AI only.
	 */
	public void makeMove(Map<String, Tile> board, Player opponentPlayer) {
		Tile requiredTile = board.get(fromPosition);
		Piece requiredPiece = null;
		if (requiredTile != null) {
			requiredPiece = requiredTile.getPiece();
		}
		if (requiredPiece != null && !requiredPiece.isDead()) {

			if (requiredPiece.hasMoveTo(toPosition) != null) {

				// if this position has an enemy , then we send it to the grave
				// yard
				if (ChessTiles.getTileStatus(requiredPiece, toPosition,
						board) == Tile.HAS_ENEMY) {
					killedPiece = board.get(toPosition).getPiece();
					opponentPlayer.addDeadPiece(killedPiece);
					motionOutput = ATTACK;
				} else {
					motionOutput = MOVE;
				}
				Tile oldTile = board.get(requiredPiece.getPosition());
				oldTile.setPiece(null);
				Tile newTile = board.get(toPosition);
				newTile.setPiece(requiredPiece);
				requiredPiece.setPosition(toPosition, newTile.getCoordinate());
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

	public String getToPosition() {
		return toPosition;
	}

	public String getFromPosition() {
		return requiredToMove.getPosition();
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
		return fromPosition + " >> " + toPosition;
	}
}
