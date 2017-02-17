package player;

import chessBoard.ChessBoard;
import chessBoard.Move;

public class MiniMax implements MoveStartegy {

	@Override
	public Move excute(ChessBoard board, int depth, boolean maximizingPlayer) {
		minimax(board, depth, true);
		return null;
	}

	private Move minimax(ChessBoard board, int depth,
			boolean maximizingPlayer) {
		int bestVal;
		Move bestMove = null;
		if (depth == 0 /* or checkmate */) {
			// return boardScore
		}

		if (maximizingPlayer) {// computer Turn. which call minimum player
			bestVal = Integer.MIN_VALUE;
			return bestMove;
		} else {// Human Turn.(minimizing Player). which call maximum player
			bestVal = Integer.MAX_VALUE;
			return bestMove;
		}
	}

}
