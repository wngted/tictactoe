package tictactoe;

public class Computer extends Player{
	
	private Marker marker;
	
	public static final Mark MARK = Mark.CROSS;

	{
		myMark = MARK;
		myName = "Computer Player";
		marker = new RandomMarker();
		marker.addMarker(new Make3Marker());
		marker.addMarker(new BlockMarker());
		marker.addMarker(new SmartMarker());
	}
	
	Computer(TicTacToe game){
		super(game);
	}
	
	@Override
	public void mark() {
		int[] pos = marker.nextMarkPos(game);
		if(pos!=null){
			System.out.println(myName + " marked " + (pos[0]*3+pos[1]) + '(' + pos[0] + ", " + pos[1] + ')');
			placeMark(pos[0]*3 + pos[1]);
		}else{
			throw new RuntimeException("Unable to find Computer's next mark position");
		}
	}

}
