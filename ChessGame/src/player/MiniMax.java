//package player;
//
//import java.util.List;
//
//import chessBoard.ChessBoard;
//import chessBoard.Move;
//import pieces.Piece;
//
//public class MiniMax implements MoveStartegy {
//
//	private BoardEvaluator boardEvaluator;
//	private Move bestMove;
//
//	public MiniMax() {
//		boardEvaluator = new BoardEvaluator();
//	}
//
//	@Override
//	public Move excute(ChessBoard board, int depth, boolean maximizingPlayer) {
//		if (board.currentPlayer().getArmyType() == Piece.WHITE_ARMY) {
//			minimax(board, depth, false);
//		} else {
//			minimax(board, depth, true);
//		}
//		return bestMove;
//	}
//
//	private int minimax(ChessBoard board, int depth, boolean maximizingPlayer) {
//		int bestVal;
//		if (depth == 0 /* or checkmate */) {
//			return boardEvaluator.evaluate(board);
//		}
//
//		if (maximizingPlayer) {// computer Turn.(maximizing Player). which call
//								// minimum player
//			bestVal = Integer.MIN_VALUE;
//			List<Move> playerLegalMoves = board.currentPlayer()
//					.getLegalMoves();
//			for (int i = 0; i < playerLegalMoves.size(); i++) {
//				Move move = playerLegalMoves.get(i);
//				ChessBoard boardCopy = board.copy();
//				// System.out.println("copy: " + boardCopy);
//				move.makeMove(boardCopy.getMap(), board.opponentPlayer());
//				if (move.isDone()) {
//					boardCopy.switchPlayers();
//					// System.out.println("copy: " + boardCopy);
//					int currentValue = minimax(boardCopy, depth - 1, false);
//					if (currentValue > bestVal) {
//						bestVal = currentValue;
//						bestMove = move;
//					}
//				}
//			}
//			return bestVal;
//		} else {// Human Turn.(minimizing Player). which call maximum player
//			bestVal = Integer.MAX_VALUE;
//			List<Move> playerLegalMoves = board.currentPlayer()
//					.getLegalMoves();
//			for (int i = 0; i < playerLegalMoves.size(); i++) {
//				Move move = playerLegalMoves.get(i);
//				ChessBoard boardCopy = board.copy();
//				// System.out.println("copy: " + boardCopy);
//				move.makeMove(boardCopy.getMap(), board.opponentPlayer());
//				if (move.isDone()) {
//					boardCopy.switchPlayers();
//					// System.out.println("copy: " + boardCopy);
//					int currentValue = minimax(boardCopy, depth - 1, true);
//					if (currentValue < bestVal) {
//						bestVal = currentValue;
//						bestMove = move;
//					}
//				}
//			}
//			return bestVal;
//		}
//	}
//
//}
