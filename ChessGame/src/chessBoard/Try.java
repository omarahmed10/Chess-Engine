package chessBoard;

import java.util.ArrayList;

import pieces.Piece;

public class Try {
	ArrayList<Integer> arrLst = new ArrayList<Integer>();
	
	void changeArray(ArrayList<Integer> arr) {
		arr.add(11111111);
	}
	
	
	public static void main(String[] args) {
		Try try1 = new Try();
		
		try1.arrLst.add(5);
		
		try1.arrLst.add(63);
		
		try1.changeArray(try1.arrLst);
		
		try1.arrLst.add(20);
		
		try1.arrLst.add(78);
		
		try1.changeArray(try1.arrLst);
		
		for (Integer i : try1.arrLst) {
			System.out.println(i);
		}
		
		System.out.println(try1.getClass().getSuperclass().equals(Piece.class));
	
	}
}
