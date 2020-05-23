// IOPoint.java
//    Function block for block diagram animation
//
import java.awt.*;
import java.util.*;

public class IOPoint extends LabelledBox {
	
	boolean Input;
	Arc input;
	Vector output;


	public IOPoint( String label, String subscript,
		int x, int y, int w, int h, boolean Input ) {
		super( label, subscript, x, y, w, h );
		this.Input = Input;
		}
	
	public void setInput( Arc in ) {
		if (!Input) {input = in;}
		}

	public void addOutput( Arc out ) {
		if (Input) {output.addElement( out );}
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
	
