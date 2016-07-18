package tictactoe;

import java.util.Random;

/**
 * The first Marker computer player will use as the first step of play.
 * @see Marker 
 */
public class RandomMarker extends Marker {
	Random rn = new Random();
	
	@Override
	protected int[] handle(TicTacToe game){
		if( game.getStepNumber()>0)
			return null;
		int next = Math.abs(rn.nextInt())%9;
		return new int[] { next/3, next%3 };
	}
}
