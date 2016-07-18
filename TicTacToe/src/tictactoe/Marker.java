package tictactoe;

/**
 * Marker hierarchy represents different strategies of computer player.
 * Chain of Responsibility pattern is applied. 
 * @see RandomMarker
 * @see Make3Marker
 * @see BlockMarker
 * @see SmartMarker
 */
public abstract class Marker {
	Marker nextMarker;
	public Marker addMarker(Marker marker){
		if ( this.nextMarker == null ){
			nextMarker = marker;
			return marker;
		}else{
			return nextMarker.addMarker(marker);
		}
	}
	
	public int[] nextMarkPos(TicTacToe game){
		int[] pos = this.handle(game);
		if(pos!=null)
			return pos;
		else if(nextMarker!=null)
			return nextMarker.nextMarkPos(game);
		else
			return null;
	}

	protected abstract int[] handle(TicTacToe game);
	
}
