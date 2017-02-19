package chessBoard;

import java.util.List;
import java.util.Map;

import pieces.Piece;

public class Move {
	public final static int MOVE = 0;
	public final static int ATTACK = 1;
	private String toPosition;
	private int motionOutput;
	private ChessBoard chessBoard;
	private Piece requiredToMove;

	public Move(ChessBoard chessBoard, Piece requiredToMove,
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
					chessBoard.getCurrentPlayer().addDeadPiece(chessBoard
							.getBoardMap().get(toPosition).getPiece());
					motionOutput = ATTACK;
				} else {
					motionOutput = MOVE;
				}
				chessBoard.getBoardMap().get(requiredToMove.getPosition())
						.setPiece(null);
				requiredToMove.setPosition(toPosition);
			}

		}
	}

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
