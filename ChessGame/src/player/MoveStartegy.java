package player;

import chessBoard.ChessBoard;
import chessBoard.Move;

public interface MoveStartegy {
	Move excute(ChessBoard board, int depth, boolean maximizingPlayer);
}
