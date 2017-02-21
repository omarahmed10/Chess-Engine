package player;


import chessBoard.ChessBoard;
import chessBoard.Move;
import pieces.Piece;

public class MiniMax implements MoveStartegy {

	private BoardEvaluator boardEvaluator;
	private Move bestMove;

	public MiniMax() {
		boardEvaluator = new BoardEvaluator();
	}

	@Override
	public Move excute(ChessBoard board, int depth, boolean maximizingPlayer) {
		if (board.getCurrentPlayer().getArmyType() == Piece.WHITE_ARMY) {
			minimax(board, depth, false);
		} else {
			minimax(board, depth, true);
		}
		return bestMove;
	}

	private int minimax(ChessBoard board, int depth, boolean maximizingPlayer) {
		int bestVal;
		if (depth == 0 /* or checkmate */) {
			return boardEvaluator.evaluate(board);
		}

		if (maximizingPlayer) {// computer Turn.(maximizing Player). which call
								// minimum player
			bestVal = Integer.MIN_VALUE;
			// you have to get the current player every turn because players is
			// switching every move.
			/**
			 * List<Move> playerLegalMove =
			 * board.getCurrentPlayer().getLegalMoves();
			 */
			for (Move move : board.getCurrentPlayer().getLegalMoves()) {
				move.doMove(board.getOpponentPlayer());
				if (move.isDone()) {
					board.switchPlayers();
					int currentValue = minimax(board, depth - 1, false);
					if (currentValue >= bestVal) {
						bestVal = currentValue;
						bestMove = move;
					}
				}
			}
			return bestVal;
		} else {// Human Turn.(minimizing Player). which call maximum player
			bestVal = Integer.MAX_VALUE;
			// you have to get the current player every turn because players is
			// switching every move.
			/**
			 * List<Move> playerLegalMove =
			 * board.getCurrentPlayer().getLegalMoves();
			 */
			for (Move move : board.getCurrentPlayer().getLegalMoves()) {
				move.doMove(board.getOpponentPlayer());
				if (move.isDone()) {
					board.switchPlayers();
					int currentValue = minimax(board, depth - 1, false);
					if (currentValue >= bestVal) {
						bestVal = currentValue;
						bestMove = move;
					}
				}
			}
			return bestVal;
		}
	}

}
