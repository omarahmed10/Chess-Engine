package chessBoard;

import java.util.List;
import java.util.Map;

import pieces.Piece;

public class Move {
	public final static int MOVE = 0;
	public final static int ATTACK = 1;
	private String toPosition;
	private int motionOutput;
	private Map<String, Tile> chessBoard;
	private Piece requiredToMove;

	public Move(Map<String, Tile> chessBoard, Piece requiredToMove,
			String toPosition) {
		this.toPosition = toPosition;
		this.chessBoard = chessBoard;
		this.requiredToMove = requiredToMove;
	}

	public void doMove(List<Piece> graveyard) {
		if (!requiredToMove.isDead()) {

			if (requiredToMove.getLegalMoves().contains(toPosition)) {

				// if this position has an enemy , then we send it to the grave
				// yard
				if (requiredToMove
						.getSquareStatus(toPosition) == Tile.HAS_ENEMY) {
					chessBoard.get(toPosition).getPiece()
							.sendToGraveyard(graveyard);
					motionOutput = ATTACK;
				} else {
					motionOutput = MOVE;
				}
				chessBoard.get(requiredToMove.getPosition()).setPiece(null);
				requiredToMove.setPosition(toPosition);
			}

		}
	}

	public String getToPosition() {
		return toPosition;
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
