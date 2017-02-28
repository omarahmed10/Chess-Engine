package chessBoard;

import java.awt.Point;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import javax.swing.JPanel;
import pieces.Piece;
import player.*;

public class ChessBoard implements Cloneable {

	public final static Point START_POINT = new Point(0, Piece.PIECE_HEIGHT);
	public final static int WHITE_TURN = 1, BLACK_TURN = -1;
	private Player whitePlayer, blackPlayer;
	private int playerTurn;
	private Map<String, Tile> tilesMap;
	private Piece currentPiece;
	private Grave whiteGraveYard, blackGraveYard;
	private Stack<Move> history;
	private JPanel guiBoard;
	private Move currentMove;
	private MoveStartegy AIStrategy;

	/**
	 * @param guiBoard
	 *            is the panel where the board will drawn.
	 * @param opponentPlayer
	 *            is the type of the black player (Human or computer)
	 */
	public ChessBoard(JPanel guiBoard, PlayerType opponentPlayer) {
		this.guiBoard = guiBoard;
		/*************************************************************/
		whitePlayer = new Player(Piece.WHITE_ARMY, PlayerType.HUMAN);
		blackPlayer = new Player(Piece.BLACK_ARMY, opponentPlayer);
		ChessTiles.createSkeletonBoard(this, whitePlayer, blackPlayer);
		// whitePlayer start the game.
		this.playerTurn = WHITE_TURN;
		tilesMap = ChessTiles.getBoardTiles();
		/*************************************************************/

		if (opponentPlayer == PlayerType.COMPUTER) {
			AIStrategy = new Minimax();
		}
		blackGraveYard = new Grave(guiBoard.getX(), guiBoard.getY());
		whiteGraveYard = new Grave(guiBoard.getX(),
				ChessBoard.START_POINT.y + 8 * Tile.TILEWIDTH);
		guiBoard.add(whiteGraveYard);
		guiBoard.add(blackGraveYard);
	}

	private ChessBoard() {

	}

	public boolean markAvaliableTiles(Piece selectedPiece, List<Move> list) {
		Player currentPlayer = currentPlayer();
		if (currentPlayer.getPlayerType() != PlayerType.COMPUTER
				&& currentPlayer.getArmy().contains(selectedPiece)) {
			currentPiece = selectedPiece;
			for (Move m : list) {
				tilesMap.get(m.getToPosition()).setAvaliability(true);
			}
			guiBoard.repaint();
			return true;
		}
		return false;
	}

	/*
	 * move function for Human Player only the mouse listener will call this
	 * one.
	 */
	public void moveSelectedPiece(String toPosition) {
		LinkedList<Piece> graveyard;
		Grave grave;
		// if white player kill a piece then it will be add in black player
		// grave yard and vice versa.
		if (playerTurn == WHITE_TURN) {
			grave = blackGraveYard;
			graveyard = blackPlayer.getDeadArmy();
		} else {
			grave = whiteGraveYard;
			graveyard = whitePlayer.getDeadArmy();
		}
		if (currentPiece != null) {
			currentMove = currentPiece.hasMoveTo(toPosition);
			if (currentMove != null) {
				currentMove.doMove(opponentPlayer());
				if (currentMove.isDone()) {
					ChessTiles.setAvailablePositions(tilesMap);
					// switch turns.
					switchPlayers();
					if (currentMove.isAttack()) {
						grave.addDeadPiece(graveyard.getLast());
					}
					// Adding Move after movement
					// history.add(currentMove);
					guiBoard.repaint();
					gui.Board.pieceMovmentSound.start();
					/*
					 * the next player becomes the current player because the
					 * turns is switched
					 */
					if (currentPlayer()
							.getPlayerType() == PlayerType.COMPUTER) {
						new Thread(new Runnable() {
							@Override
							public void run() {
								MakeComputerMove();
							}
						}).start();
					}
				}
			}
		}
	}

	public void MakeComputerMove() {

		Move computerMove = AIStrategy.excute(this, 2);
		guiBoard.repaint();
//		currentPiece = tilesMap.get(computerMove.getFromPosition()).getPiece();
//		System.out.println(currentPiece + " " + currentPiece.getPosition() + " "
//				+ computerMove);
//		// System.out.println(this);
//		moveSelectedPiece(computerMove.getToPosition());
	}

	public void switchPlayers() {
		this.playerTurn *= BLACK_TURN;
	}

	public void setPlayerTurn(int pT) {
		playerTurn = pT;
	}

	public Player currentPlayer() {
		if (playerTurn == WHITE_TURN) {
			return whitePlayer;
		} else {
			return blackPlayer;
		}
	}

	public Player whitePlayer() {
		return this.whitePlayer;
	}

	public Player blackPlayer() {
		return blackPlayer;
	}

	public Player opponentPlayer() {
		if (playerTurn == WHITE_TURN) {
			return blackPlayer;
		} else {
			return whitePlayer;
		}
	}

	public Map<String, Tile> getMap() {
		return tilesMap;
	}

	/**
	 * this is undo is wrong. you have to undo two moves one for current player
	 * and the other for the opponent player.
	 */
	public void undo() {
		history.pop();

		currentMove.undoMove(currentPlayer());
	}

	public Stack<Move> getHistory() {
		return history;
	}

	private void setBoardFields(Map<String, Tile> board, Player whitePlayer,
			Player blackPlayer, int playerTurn) {
		this.tilesMap = board;
		this.whitePlayer = whitePlayer;
		this.blackPlayer = blackPlayer;
		this.playerTurn = playerTurn;
	}

	public ChessBoard copy() {
		return copyEverything();
	}

	private ChessBoard copyEverything() {
		ChessBoard newBoard = new ChessBoard();
		Player newWhitePlayer = copyPlayer(this.whitePlayer);
		Player newBlackPlayer = copyPlayer(this.blackPlayer);
		newBoard.setBoardFields(
				getCopy(tilesMap, newWhitePlayer, newBlackPlayer),
				newWhitePlayer, newBlackPlayer, playerTurn);
		return newBoard;
	}

	private Map<String, Tile> getCopy(Map<String, Tile> originalChessBoard,
			Player whitePlayer, Player blackPlayer) {
		Map<String, Tile> chessBoardCopy = new HashMap<String, Tile>();

		for (String position : originalChessBoard.keySet()) {
			try {
				Tile tileCopy = (Tile) originalChessBoard.get(position).clone();
				if (tileCopy.getPiece() != null) {
					Piece pieceCopy = (Piece) tileCopy.getPiece().clone();
					if (pieceCopy.getArmyType() == Piece.WHITE_ARMY) {
						whitePlayer.addPiece(pieceCopy);
					} else {
						blackPlayer.addPiece(pieceCopy);
					}
				}
				chessBoardCopy.put(position, tileCopy);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}

		return chessBoardCopy;
	}

	private Player copyPlayer(Player player) {
		try {
			Player playerCopy = (Player) player.clone();
			playerCopy.reset();
			return playerCopy;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addMouseListener(Tile t) {
		guiBoard.addMouseListener(t);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String board = currentPlayer().getArmyType() + " " + hashCode()
				+ ": \n";
		for (int j = 0; j < 8; j++) {
			for (int i = 65; i <= 72; i++) {
				String s = (char) i + "" + ChessTiles.arr[j];
				Tile t = tilesMap.get(s);
				if (t.getPiece() != null) {
					board += t.getPiece().toString();
				} else {
					board += "** ";
				}

			}
			board += "\n";
		}
		return board;
	}
}
