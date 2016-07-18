package tictactoe;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class BoardSupport {
	
	public static Integer getWinningPos(Board board, Mark mark){
		List<Integer> myMarks = getCellListOfMark(mark, board);
		if( myMarks.size()<2 )
			return null;
				
		List<Integer> empties = getCellListOfMark(Mark.EMPTY, board);
		if( empties.size()<1)
			return null;
		
		for( Integer[] n : WINNING_NUMBERS ){
			if( empties.contains(n[0]) && myMarks.contains(n[1]) && myMarks.contains(n[2]))
				return n[0];
			else if( empties.contains(n[1]) && myMarks.contains(n[0]) && myMarks.contains(n[2]) )
				return n[1];
			else if( empties.contains(n[2]) && myMarks.contains(n[0]) && myMarks.contains(n[1]) )
				return n[2];
			else
				continue;
		}
		return null;
	}
	
	public static boolean computerWillLose(Board board, Integer nextCross){
		List<Integer> empties = getCellListOfMark(Mark.EMPTY, board);
		if( empties.size()>6)
			return false;
		empties.remove(nextCross);
		
		List<Integer> humanMarks = getCellListOfMark(Human.MARK, board);
		
		Set<Integer> winPos = new HashSet<Integer>();
		for( Integer[] n : WINNING_NUMBERS ){
			if( empties.contains(n[0]) && humanMarks.contains(n[1]) && humanMarks.contains(n[2]))
				winPos.add(n[0]);
			else if( empties.contains(n[1]) && humanMarks.contains(n[0]) && humanMarks.contains(n[2]) )
				winPos.add(n[1]);
			else if( empties.contains(n[2]) && humanMarks.contains(n[0]) && humanMarks.contains(n[1]) )
				winPos.add(n[2]);
			else
				continue;
		}
		return winPos.size()>1;
	}
	
	public static boolean isWinning(Board board, Mark mark){
		List<Integer> myMarks = getCellListOfMark(mark, board);
		if( myMarks.size()<2 )
			return false;
		for( Integer[] n : WINNING_NUMBERS )
			if( myMarks.contains(n[0]) && myMarks.contains(n[1]) && myMarks.contains(n[2]) )
				return true; 
		
		return false;
	}
	
	public static boolean isTie(Board board){
		return !isWinning(board, Mark.CROSS) && ! isWinning(board, Mark.NOUGHT) && getCellListOfMark(Mark.EMPTY, board).size()==0;
	}
	
	public static void showBoard(Board board){
		System.out.println("+-+-+-+");
		for (int row = 0; row < 3; ++row) {
			System.out.print('|');
			for (int col = 0; col < 3; ++col) {
				System.out.print(board.getMark(row, col).mark());
				if (col != 2) {
					System.out.print("|");
				}
			}
			System.out.println('|');
			if (row != 2) {
				System.out.println("+-+-+-+");
			}
		}
		System.out.println("+-+-+-+");
	}
	
	public static List<Integer> getCellListOfMark(Mark mark, Board board){
		List<Integer> list = new LinkedList<Integer>();
		for( int i=0; i<3; i++ )
			for( int j=0; j<3; j++ ){
				if( mark.equals(board.getMark(i, j)) )
					list.add(i*3+j);
			}
		return list;
	}
	
	static final List<Integer[]> WINNING_NUMBERS
		= Arrays.asList(new Integer[][]{ {0,1,2}, {0,3,6}, {0,4,8}, {1,4,7}, {2,5,8}, {2,4,6}, {3,4,5}, {6,7,8} } );
}
