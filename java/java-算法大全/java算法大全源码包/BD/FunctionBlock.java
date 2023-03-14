// FunctionBlock.java
//    Function block for block diagram animation
//
import java.awt.*;
import java.util.*;

public class FunctionBlock extends LabelledBox
//							implements DrawingObj 
				{
	
	//
	Arc input;
	Vector output;


	public FunctionBlock( String label, String subscript,int x, int y, int w, int h ) {
		super( label, subscript, x, y, w, h );
		input = null;
		output = new Vector( 2 );
	
		}

	public void setInput( Arc in ) {
		input = in;
		}

	public void addOutput( Arc out ) {
		output.addElement( out );
		}

	public Node getInput( ) {
		return input.getStart();
		}

	public Arc getInputArc() { return input; }
	public Arc getOutputArc( int k ) { return (Arc)output.elementAt(k); }

	public Node getOutput( int k ) {
		return ((Arc)output.elementAt( k )).getEnd();
		}

	public int outputCnt( ) {
		return output.size();
		}

}
	
