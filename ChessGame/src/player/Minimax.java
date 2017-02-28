package player;

import java.util.List;

import javax.swing.JPanel;

import chessBoard.ChessBoard;
import chessBoard.Move;
import pieces.Piece;

public class Minimax implements MoveStartegy {
	private BoardEvaluator evaluator;

	public Minimax() {
		// TODO Auto-generated constructor stub
		this.evaluator = new BoardEvaluator();
	}

	@Override
	public Move excute(final ChessBoard board, final int depth) {

		Move bestMove = null;
		int highestSeenValue = Integer.MIN_VALUE;
		int lowestSeenValue = Integer.MAX_VALUE;
		int currentValue;
		List<Move> playerLegalMoves = board.currentPlayer().getLegalMoves();
		final int numMoves = playerLegalMoves.size();
		ChessBoard boardCopy = null;
		for (int i = 0; i < numMoves; i++) {
			Move move = playerLegalMoves.get(i);
			boardCopy = board.copy();
			// System.out.println("copy: " + boardCopy);
			move.makeMove(boardCopy.getMap(), boardCopy.opponentPlayer());
			if (move.isDone()) {
				boardCopy.switchPlayers();
				currentValue = boardCopy.currentPlayer()
						.getArmyType() == Piece.WHITE_ARMY
								? min(boardCopy, depth - 1)
								: max(boardCopy, depth - 1);
				if (boardCopy.currentPlayer().getArmyType() == Piece.WHITE_ARMY
						&& currentValue >= highestSeenValue) {
					highestSeenValue = currentValue;
					bestMove = move;
				} else if (boardCopy.currentPlayer()
						.getArmyType() == Piece.BLACK_ARMY
						&& currentValue <= lowestSeenValue) {
					lowestSeenValue = currentValue;
					bestMove = move;
				}
			}
		}
		System.out.println(boardCopy);
		System.out.println("original: " + board);
		return bestMove;
	}

	private int min(final ChessBoard board, final int depth) {
		if (depth == 0 /* || isEndGameScenario(board) */) {
			return this.evaluator.evaluate(board, depth);
		}
		int lowestSeenValue = Integer.MAX_VALUE;
		List<Move> playerLegalMoves = board.currentPlayer().getLegalMoves();
		for (int i = 0; i < playerLegalMoves.size(); i++) {
			Move move = playerLegalMoves.get(i);
			ChessBoard boardCopy = board.copy();
			// System.out.println("copy: " + boardCopy);
			move.makeMove(boardCopy.getMap(), boardCopy.opponentPlayer());
			if (move.isDone()) {
				boardCopy.switchPlayers();
				final int currentValue = max(boardCopy, depth - 1);
				if (currentValue <= lowestSeenValue) {
					lowestSeenValue = currentValue;
				}
			}
		}
		return lowestSeenValue;
	}

	private int max(final ChessBoard board, final int depth) {
		if (depth == 0 /* || isEndGameScenario(board) */) {
			return this.evaluator.evaluate(board, depth);
		}
		int highestSeenValue = Integer.MIN_VALUE;
		List<Move> playerLegalMoves = board.currentPlayer().getLegalMoves();
		for (int i = 0; i < playerLegalMoves.size(); i++) {
			Move move = playerLegalMoves.get(i);
			ChessBoard boardCopy = board.copy();
			// System.out.println("copy: " + boardCopy);
			move.makeMove(boardCopy.getMap(), boardCopy.opponentPlayer());
			if (move.isDone()) {
				boardCopy.switchPlayers();
				final int currentValue = min(boardCopy, depth - 1);
				if (currentValue >= highestSeenValue) {
					highestSeenValue = currentValue;
				}
			}
		}
		return highestSeenValue;
	}
}
