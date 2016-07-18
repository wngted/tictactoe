package tictactoe;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SmartMarker extends Marker{

	@Override
	protected int[] handle(TicTacToe game) {
		List<Integer> empties = BoardSupport.getCellListOfMark(Mark.EMPTY, game.board);
		if( empties==null || empties.size()==0 )
			return null;  // no empty spot
		Integer spot = findAnOpen2(game);
		if(spot!=null)
			return new int[]{spot/3, spot%3};
		
		for(int i : empties){
			int x = i/3;
			int y = i%3;
			int[] nx = new int[]{x-1, x, x+1};
			int[] ny = new int[]{y-1, y, y+1};
			for(int xi : nx)
				for(int yi : ny){
					if( (xi==x && yi==y) || xi<0 || xi>2 || yi<0 || yi>2 ){
						continue; // invalid
					}
					if( game.board.getMark(xi, yi)==Computer.MARK )
						return new int[]{x, y};  // an empty next to my marked cell
				}
		}
		return new int[]{ empties.get(0)/3, empties.get(0)%3 };  // arbitrary return
	}
	
	private Integer findAnOpen2(TicTacToe game){
		List<Integer> empties = BoardSupport.getCellListOfMark(Mark.EMPTY, game.board);
		List<Integer> myCells = BoardSupport.getCellListOfMark(Mark.CROSS, game.board);

		for(Integer[] pos : BoardSupport.WINNING_NUMBERS){
			if( myCells.contains(pos[0]) && empties.contains(pos[1]) && empties.contains(pos[2])){
				return pos[1];
			} else if( myCells.contains(pos[1]) && empties.contains(pos[0]) && empties.contains(pos[2])){
				return pos[0];
			} else if( myCells.contains(pos[2]) && empties.contains(pos[0]) && empties.contains(pos[1])){
				return pos[1];
			}
		}
		return null;
	}
}
