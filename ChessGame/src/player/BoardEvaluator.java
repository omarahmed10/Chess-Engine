package player;

import java.util.List;

import chessBoard.ChessBoard;
import pieces.Piece;

public class BoardEvaluator {

	protected int evaluate(ChessBoard board) {
		int value = 0;
		List<Piece> playerAliveArmy = board.getCurrentPlayer().getArmy();
		for (Piece p : playerAliveArmy) {
			value += p.getPieceValue();
		}
		return value;
	}
}
