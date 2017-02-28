package player;

import java.util.List;

import chessBoard.ChessBoard;
import pieces.Piece;

public class BoardEvaluator {

	private final static int CHECK_MATE_BONUS = 10000;
	private final static int CHECK_BONUS = 50;
	private final static int MOBILITY_BONUS = 1;

	protected int evaluate(final ChessBoard board, final int depth) {
		return score(board, board.whitePlayer(), depth)
				- score(board, board.blackPlayer(), depth);
	}

	private static int score(final ChessBoard board, final Player player,
			final int depth) {
		return mobility(player) + checkmate(board, depth) + pieceValue(player);
	}

	private static int pieceValue(final Player player) {
		int value = 0;
		List<Piece> playerAliveArmy = player.getArmy();
		for (Piece p : playerAliveArmy) {
			value += p.getPieceValue();
		}
		return value;
	}

	private static int mobility(final Player player) {
		return MOBILITY_BONUS * player.getLegalMoves().size();
	}

	private static int checkmate(final ChessBoard board, final int depth) {
		return board.opponentPlayer().isInCheckMate(board.getMap(),
				board.currentPlayer()) ? CHECK_MATE_BONUS * depthBonus(depth)
						: check(board, depth);
	}

	private static int check(final ChessBoard board, final int depth) {
		return board.opponentPlayer().isInCheck(board.getMap()) ? CHECK_BONUS : 0;
	}

	private static int depthBonus(final int depth) {
		return depth == 0 ? 1 : 100 * depth;
	}
}
