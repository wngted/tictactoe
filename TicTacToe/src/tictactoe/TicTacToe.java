package tictactoe;

public class TicTacToe {
	Board board = new Board();
	Computer computer = new Computer(this);
	Human human = new Human(this);
	TrackingAndValidationService trackingService = new TrackingAndValidationService();
	
	private void play(){
		for( int step = 0; step<9; step++ ){
			Player curPlayer = step%2==0? computer : human;
			curPlayer.mark();
			
			BoardSupport.showBoard(board);
			
			if( BoardSupport.isWinning(board, curPlayer.myMark) ){
				System.out.println(curPlayer.myName + " WON");
				break;
			}else if( BoardSupport.isTie(board)){
				System.out.println("A Tie Game");
				break;
			}
		}
	}
	
	void markCell(Mark mark, int pos){
		mark(mark, pos);
		trackingService.logStep(pos);
	}
	void mark(Mark mark, int pos){
		board.setMark(mark, new int[]{pos/3, pos%3} );
	}
	
	int getStepNumber(){
		return trackingService.getStepNumber();
	}
	
	public static void main(String[] args) {
		TicTacToe inst = new TicTacToe();
		inst.play();
	}

}
