package tictactoe;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TrackingAndValidationService {
	private File trackingFile = new File(USER_HOME + "/" + TRACKING_FILE_NAME );
	private List<String> knownLosingBoardTraces = new ArrayList<String>();
	PosNormalizer posNormalizer;
	
	private int[] stepTrace = new int[9];
	private int step = 0;

	{
		if( trackingFile.isFile()==false )
			createTrackingFile();
		readTrackingFile();
	}
	
	public void logStep(int pos){
		if(posNormalizer==null){
			posNormalizer = PosNormalizer.instance(pos); 
		}
		stepTrace[step++] = posNormalizer==null? pos : posNormalizer.normalize(pos);
	}
	
	public void addLosingTrack(Board board){
		StringBuffer buf = new StringBuffer();
		boolean appendSep = false;
		for( int i : BoardSupport.getCellListOfMark(Mark.CROSS, board) ){
			buf.append(appendSep?",": "").append(i);
			appendSep = true;
		}
		
		buf.append('|');
		
		appendSep = false;
		for( int i : BoardSupport.getCellListOfMark(Mark.NOUGHT, board) ){
			buf.append(appendSep?",": "").append(i);
			appendSep = true;
		}
		
		if( knownLosingBoardTraces.contains(buf.toString()) == false ){
			knownLosingBoardTraces.add(buf.toString());
			outputTrackingFile();
		}
	}
	
	private void createTrackingFile(){
		try ( FileWriter writer = new FileWriter(trackingFile, false) ) {
			writer.write("");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void readTrackingFile(){
		try ( LineNumberReader reader = new LineNumberReader(new FileReader(trackingFile)) ) {
			String line = reader.readLine();
			while(line!=null){
				if( line.length()>0 )
					knownLosingBoardTraces.add(line);
				line = reader.readLine();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} 
	}
	
	private void outputTrackingFile(){
		try (FileWriter writer = new FileWriter(trackingFile, false)) {
			for( String line : knownLosingBoardTraces ){
				writer.write(line);
				writer.write(System.lineSeparator());
				writer.flush();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	int getStepNumber(){
		return step;
	}
	
	private static String TRACKING_FILE_NAME = "tic-tac-toe.txt";
	private static String USER_HOME = System.getProperty("user.home");
	
	
	private static class PosNormalizer{
		int[] posMap;
		
		private static PosNormalizer instance(int refPos){
			if(refPos==4)
				return null;
			
			PosNormalizer inst = new PosNormalizer();
			switch(refPos){
			case 2:
			case 5:
				inst.posMap = new int[]{2,5,8,1,4,7,0,3,6};
				break;
			case 8:
			case 7:
				inst.posMap = new int[]{8,7,6,5,4,3,2,1,0};
				break;
			case 6:
			case 3:
				inst.posMap = new int[]{6,3,0,7,4,1,8,5,2};
				break;
			case 0:
			case 1:
			default:
				inst.posMap = new int[]{0,1,2,3,4,5,6,7,8};
			}
			return inst;
		}
		
		private int normalize(int posOnBoard){
			return posMap[posOnBoard];
		}
	}
}
