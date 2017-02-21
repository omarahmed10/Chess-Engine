package player;

import chessBoard.ChessBoard;
import chessBoard.Move;
import pieces.Piece;

public class AlphaBeta implements MoveStartegy {

	private BoardEvaluator boardEvaluator;
	private Move bestMove;

	public AlphaBeta() {
		boardEvaluator = new BoardEvaluator();
	}

	@Override
	public Move excute(ChessBoard board, int depth, boolean maximizingPlayer) {
		if (board.getCurrentPlayer().getArmyType() == Piece.WHITE_ARMY) {
			alphabeta(board, Integer.MIN_VALUE, Integer.MAX_VALUE, depth, true);
		} else {
			alphabeta(board, Integer.MIN_VALUE, Integer.MAX_VALUE, depth,
					false);
		}
		return bestMove;
	}

	private int alphabeta(ChessBoard board, int α, int β, int depth,
			boolean maximizingPlayer) {
		int bestVal;
		if (depth == 0 /* or checkmate */) {
			return boardEvaluator.evaluate(board);
		}
		if (maximizingPlayer) {
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
					int currentValue = alphabeta(board, α, β, depth - 1, false);
					if (currentValue >= bestVal) {
						bestVal = currentValue;
						bestMove = move;
					}
					α = Math.max(α, currentValue);
					if (β <= α) { // β cut-off
						break;
					}
				}
			}
		} else {
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
					int currentValue = alphabeta(board, α, β, depth - 1, true);
					if (currentValue <= bestVal) {
						bestVal = currentValue;
						bestMove = move;
					}
					β = Math.min(β, currentValue);
					if (β <= α) { // α cut-off
						break;
					}
				}
			}
		}
		return bestVal;
	}
}
