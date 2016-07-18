package tictactoe;

import java.util.List;
import java.util.Scanner;

public class Human extends Player{
	public static final Mark MARK = Mark.NOUGHT;

	{
		myMark = MARK;
		myName = "Human Player";
	}
	
	private Scanner sc = new Scanner(System.in);
	
	Human(TicTacToe game)
	{
		super(game);
	}
	
	@Override
	public void mark() {
		List<Integer> emptyCells = BoardSupport.getCellListOfMark(Mark.EMPTY, game.board);
		while(true){
			System.out.print("Give an Integer 0 through 8: ");
			int in = sc.nextInt();
			if( in<0 || in>8 ){
				System.out.println("Invalid input: unknow cell");
			}else if( !emptyCells.contains(in) ){
				System.out.println("Invalid input: nonempty cell");
			}else{
				System.out.println(myName + " marked " + in + '(' + (in/3) + ", " + (in%3) + ')');
				placeMark(in);
				break;
			}
		}
	}
}
