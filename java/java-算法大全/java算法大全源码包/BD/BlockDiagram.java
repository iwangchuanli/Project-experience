// BlockDiagram.java
//		Block diagram animation
//
import java.awt.*;
import java.lang.*;
import java.io.*;
import java.util.*;
import java.net.*;

public class BlockDiagram implements DrawingObj {
	
	
	String label;
	// int transform_case;
	IOPoint input, output;
	Arc arc;
	Vector sum_pts;
	Vector f_blks;
	Vector arcs;
	int x, y;


	public BlockDiagram( String label ) {
		this.label = new String( label );
		input = output = null;
		sum_pts = new Vector( 10 );
		f_blks = new Vector( 10 );
		arcs = new Vector( 10 );
		x = y = 0;
		}

	
	public void move( int dx, int dy ) {
		// Implement this as offset the whole drawing
								 int n,k;
		
		input.x = input.x + dx;
		input.y = input.y + dy;
		output.x = output.x + dx;
		output.y = output.y + dy;
		n = f_blks.size();
		for(k=0;k<n;k++) {
			x = ((FunctionBlock)f_blks.elementAt(k)).getX();
			y = ((FunctionBlock)f_blks.elementAt(k)).getY();
			((FunctionBlock)f_blks.elementAt(k)).move (x + dx, y + dy);
			}
		n = sum_pts.size();
		for(k=0;k<n;k++) {
			x = ((SummingPoint)sum_pts.elementAt(k)).getX();
			y = ((SummingPoint)sum_pts.elementAt(k)).getY();
									((SummingPoint)sum_pts.elementAt(k)).move (x + dx, y + dy);
			}
		 n = arcs.size();
		for(k=0;k<n;k++) {
			x = ((Arc)arcs.elementAt(k)).getX();
			y = ((Arc)arcs.elementAt(k)).getY();
								 ((Arc)arcs.elementAt(k)).move (x + dx, y + dy);
			}
						
		}

	//public int getFBw() {
	//	int k,n ;
	//	n = f_blks.size();
	//	for(k=0;k<n;k++) {
	//		x = ((FunctionBlock)f_blks.elementAt(k)).getX();
	//		y = ((FunctionBlock)f_blks.elementAt(k)).getY();
			//((FunctionBlock)f_blks.elementAt(k)).move (x + dx, y + dy);
	//		return
	//		}
		//k =	f_blks.lastIndexOf(fb);
		//return ((FunctionBlock)f_blks.elementAt(k)).getW();
	//	}

	public void addSummingPoint( SummingPoint s ) {
		sum_pts.addElement( s );
		}

	public void addFunctionBlock( FunctionBlock fb ) {
		f_blks.addElement( fb );
		}

	public void addArc( Arc a ) {
		arcs.addElement( a );
		}
	
	public void setInput ( IOPoint io ) {
		input = io;
		}

	public void setOutput ( IOPoint io ) {
		output = io;
		}

	public int getX() { return x; }
	public int getY() { return y; }
	

	public void draw( Graphics g ) {
		int k, n;
		// g.drawString( label, lx, ly );

		n = arcs.size();
		for(k=0;k<n;k++) {
			((Arc)arcs.elementAt(k)).draw( g );
			}
			
		input.draw( g );
		output.draw( g );

		n = f_blks.size();
		for(k=0;k<n;k++) {
			((FunctionBlock)f_blks.elementAt(k)).draw( g );
			}
		n = sum_pts.size();
		for(k=0;k<n;k++) {
			((SummingPoint)sum_pts.elementAt(k)).draw( g );
			}
		
		}
	
	public Dimension getLimit( int dirn ) {
		return new Dimension( 0, 0 );
		}
			


	public void ConsTestCase() {

		Arc a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11;
		FunctionBlock g1, g2, g3, h1, h2;
		SummingPoint sp1, sp2;
		Node in_node, out_node, g1_node, g2_node, g3_node, h1_node, h2_node, sp1_node, sp2_node;
		int Sx, Sy, gap;
		Sx = 100;
		Sy = 150;
				gap = 100;

		setInput( new IOPoint("R", "s", Sx, Sy, 50, 30, true ) );
								in_node = new Node(input, Node.IOtype);
		setOutput( new IOPoint("Y", "s", Sx + 6*gap, Sy, 50, 30, false ) );
								out_node = new Node(output, Node.IOtype);

		// Summing Points
		addSummingPoint( sp1 = new SummingPoint( Sx + gap, Sy ) );
		sp1_node = new Node (sp1, Node.SPtype);
		addSummingPoint( sp2 = new SummingPoint( Sx + 3*gap, Sy ) );
								sp2_node = new Node (sp2, Node.SPtype);

		// Function Blocks
		g1 = new FunctionBlock("G", "1", Sx + 2*gap, Sy, 40, 30 );
		addFunctionBlock( g1 );
		g1_node = new Node( g1, Node.FBtype );
		g2 = new FunctionBlock("G", "2", Sx + 4*gap, Sy, 40, 30 );
		g2_node = new Node( g2, Node.FBtype );
		addFunctionBlock( g2 );
		g3 = new FunctionBlock("G", "3", Sx + 5*gap, Sy, 40, 30 );
		g3_node = new Node( g3, Node.FBtype );
		addFunctionBlock( g3 );
		h1 = new FunctionBlock("H", "1", Sx + 3*gap, Sy + gap, 40, 30 );
								addFunctionBlock( h1 );
		h1_node = new Node( h1, Node.FBtype );
								h2 = new FunctionBlock("H", "2", Sx + 4*gap, Sy - gap, 40, 30 );
								addFunctionBlock( h2 );
		h2_node = new Node( h2, Node.FBtype );


		// Arcs
		a1 = new Arc( 3, true, true, in_node,sp1_node);
		a1.addPoint( Sx ,Sy );
		a1.addPoint( Sx + gap, Sy );
		addArc( a1 );

		a2 = new Arc( 3, true, false, sp1_node, g1_node);
		a2.addPoint( Sx + gap, Sy );
		a2.addPoint( Sx + 2*gap, Sy );
		addArc( a2 );

		a3 = new Arc( 3, true, true, g1_node, sp2_node);
		a3.addPoint( Sx + 2*gap,Sy );
		a3.addPoint( Sx + 3*gap, Sy );
		addArc( a3 );

		a4 = new Arc( 3, true, false, sp2_node, g2_node);
		a4.addPoint( Sx + 3*gap,Sy );
		a4.addPoint( Sx + 4*gap, Sy );
		addArc( a4 );

								a5 = new Arc( 3, true, false, g2_node, g3_node );
		a5.addPoint( Sx + 4*gap, Sy );
		a5.addPoint( Sx + 5*gap, Sy);
		addArc( a5 );
		
								a6 = new Arc( 3, true, false, g3_node, out_node );
		a6.addPoint( Sx + 5*gap, Sy );
		a6.addPoint( Sx + 6*gap, Sy);
		addArc( a6 );

								a7 = new Arc( 3, false, false, g3_node, h1_node );
		a7.addPoint( Sx + (6*gap - gap/2), Sy );
		a7.addPoint(Sx + (6*gap - gap/2), Sy + gap);
		a7.addPoint( Sx + 3*gap, Sy + gap );
		addArc( a7 );

								a8 = new Arc( 3, false, true, h1_node, sp1_node );
		a8.addPoint( Sx + 3*gap,Sy + gap);
		a8.addPoint(Sx + gap, Sy + gap);
		a8.addPoint( Sx + gap, Sy );
		addArc( a8 );

		a9 = new Arc( 3, false, false, g2_node, h2_node );
		a9.addPoint( Sx + (5*gap - gap/2) ,Sy);
		a9.addPoint(Sx + (5*gap - gap/2), Sy - gap);
		a9.addPoint( Sx + 4*gap, Sy - gap);
		addArc( a9 );

		a10 = new Arc( 3, true, true, h2_node, sp2_node );
		a10.addPoint( Sx + 4*gap,Sy - gap);
		a10.addPoint(Sx + 3*gap, Sy - gap);
		a10.addPoint( Sx + 3*gap, Sy);
		addArc( a10 );
		}

	private int tx, ty, tw, th;

	private boolean getXYWH( String p ) {
		System.out.println("getXYWH [" + p + "]" );
		int ix = p.indexOf( "{" );
		String p1 = p.substring( ix+1 );
		StringTokenizer t = new StringTokenizer( p1, ",}" );
		tx = Integer.valueOf(t.nextToken()).intValue();
		ty = Integer.valueOf(t.nextToken()).intValue();
		tw = Integer.valueOf(t.nextToken()).intValue();
		th = Integer.valueOf(t.nextToken()).intValue();
		System.out.println( "tx " + tx + " ty " + ty + " tw " + tw + " th " + th + "]");
		return true;
		}

	private String s_label; 
	private String subs;

	private String getQuoted( String x ) {
		System.out.println("getQuoted [" + x + "]" );
		String p1 = x.substring(1);
		int index = p1.indexOf( "\"" );
		System.out.println("getQuoted p1 [" + p1 + "] index " + index );
		return p1.substring(0,index);
		}

	private boolean getSL( String p ) {
		System.out.println("getSL [" + p + "]" );
		String token;
		StringTokenizer t = new StringTokenizer( p, "," );
		String x = t.nextToken();
		s_label = getQuoted( x );
		x = t.nextToken();
		subs = getQuoted( x );
		return true;
		}
		
	private Node parseInput( String p ) {
		Node in_node;
		System.out.println("parseInput [" + p + "]" );
		// Strip the leading brace
		String p1 = p.substring( 1 );
		StringTokenizer t = new StringTokenizer( p1, "}" );
		String sl = t.nextToken();
		if ( getSL( sl ) ) {
			String x = t.nextToken();
			System.out.println("x [" + x + "]" );
			int comma = x.indexOf( "," );
			String x1 = x.substring( comma+1 );
			System.out.println("x1 [" + x1 + "]" );
			if ( getXYWH( x1 ) ) {
				setInput( new IOPoint( s_label, subs, tx, ty, tw, th, true ) );
				System.out.println("Input [" + s_label + "," + subs + "]" );
				in_node = new Node(input, Node.IOtype);
				return in_node;
				}
			}
		return null;
		}

	private void parseOutput( String p ) {
		}

	private void parseSP( String p ) {
		}

	private void parseFB( String p ) {
		}

	private void parseARC( String p ) {
		}

	public BlockDiagram( URL sourceURL ) {
		InputStream source = null;
		StringBuffer sb;
		String file_name;
		Node in_node, out_node;
		int k;
		String kw[] = { "Input", "Output", "SP", "FB", "ARC", "END" };

		file_name = sourceURL.toString();
		System.out.println("Read BD from " + file_name );
		try {
			source = sourceURL.openStream();
		} catch( IOException e ) {
			System.out.println("Read BD IOException on " + file_name );
	 	}
		if ( source != null ) {

			/* Read the file */
			DataInputStream ds = new DataInputStream( source );
			k = 0;
			try {
				while( k != 5 ) {
					String s;
					if ( ( s = ds.readLine()) != null ) {
						System.out.println("Line [" + s + "]" );
						StringTokenizer t = new StringTokenizer( s, "=\n\r" );
						String keyword = t.nextToken();
						String params = t.nextToken();
						System.out.println("key [" + keyword + "][" + params + "]" );
						for(k=0;k<kw.length;k++) {
							if ( keyword.equals( kw[k] ) ) break;
							}
						// Strip the leading and trailing { }
						String p1 = params.substring( 1 );
						// Strip the trailing one also!!
   
						System.out.println( "BD(URL): k = " + k );
						switch( k ) {
							case 0: // Input
									in_node = parseInput( p1 ); break;
							case 1: // Output
									parseOutput( params ); break;
							case 2: // SP
									parseSP( params ); break;
							case 3: // FB
									parseFB( params ); break;
							case 4: // ARC
									parseARC( params ); break;
							case 5: // END
									System.out.println("BD(URL): END found"); break;
							default:	
									System.out.println("BD(URL): unknown keyword");
									break;
							}
						}
					else break;
					}
				} catch( IOException e ) {
					System.out.println("Read BD IOEx reading");
			
				}
				System.out.println("BD(URL): file read");
			}
		else {
			System.out.println("Null source");
			}
		}

	public BlockDiagram( int transform_case ) {
					
		Arc a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11;
		FunctionBlock g1, g2, g3, h1, h2;
		SummingPoint sp1, sp2;
		Node in_node, out_node, g1_node, g2_node, g3_node, h1_node, h2_node, sp1_node, sp2_node;
		int Sx, Sy, gap, Bw , Bh;
		Sx = 100;
		Sy = 250;
								gap = 100;
		Bw = 40;
		Bh = 30;
	
		if (transform_case == 1) {
					setInput( new IOPoint("R", "s", Sx, Sy, Bw, Bh, true ) );
								in_node = new Node(input, Node.IOtype);
		setOutput( new IOPoint("Y", "s", Sx + 3*gap, Sy, Bw, Bh, false ) );
								out_node = new Node(output, Node.IOtype);

		g1 = new FunctionBlock("G", "1", Sx + gap, Sy, Bw, Bh );
		addFunctionBlock( g1 );
		g1_node = new Node( g1, Node.FBtype );
		g2 = new FunctionBlock("G", "2", Sx + 2*gap, Sy, Bw, Bh );
		g2_node = new Node( g2, Node.FBtype );
		addFunctionBlock( g2 );

		a1 = new Arc( 3, true, false, in_node,g1_node);
		a1.addPoint( Sx ,Sy );
		a1.addPoint( Sx + gap, Sy );
		addArc( a1 );

		a2 = new Arc( 3, true, false, g1_node, g2_node);
		a2.addPoint( Sx + gap, Sy );
		a2.addPoint( Sx + 2*gap, Sy );
		addArc( a2 );

		a3 = new Arc( 3, true, true, g2_node, out_node);
		a3.addPoint( Sx + 2*gap,Sy );
		a3.addPoint( Sx + 3*gap, Sy );
		addArc( a3 );	}
		}

	public BlockDiagram( int tf_no, boolean before ) {
		// Code to build the "standard" transforms
		
					Arc a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11;
				FunctionBlock g1, g2, g3, h1, h2, y2, r2;
	 		SummingPoint sp1, sp2;
				Node in_node, out_node, y2_node, r2_node, g1_node, g2_node, g3_node, h1_node, h2_node, sp1_node, sp2_node;
				int Sx, Sy, gap, Bw , Bh;

		System.out.println("BlockDiagram cons(" + tf_no + "," + before + ")" );
				Sx = 30;
				Sy = 30;
		gap = 70;
				Bw = 30;
				Bh = 20;

				this.label = new String( "TF" + tf_no );
				input = output = null;
				sum_pts = new Vector( 10 );
				f_blks = new Vector( 10 );
				arcs = new Vector( 10 );
				x = y = 0;

		if ( before ) {
		 	switch( ++tf_no ) {
							case 1:
					setInput( new IOPoint("R", "s", Sx, Sy, Bw, Bh, true ) );
							in_node = new Node(input, Node.IOtype);
							setOutput( new IOPoint("Y", "s", Sx + 3*gap, Sy, Bw, Bh, false ) );
							out_node = new Node(output, Node.IOtype);

							g1 = new FunctionBlock("G", "1", Sx + gap, Sy, Bw, Bh );
						if ( g1 == null ) {
							System.out.println( "BD cons(" + tf_no + "," + before + ") cant create FB");
							}
							addFunctionBlock( g1 );
					g1_node = new Node( g1, Node.FBtype );
							g2 = new FunctionBlock("G", "2", Sx + 2*gap, Sy, Bw, Bh );
							g2_node = new Node( g2, Node.FBtype );
							addFunctionBlock( g2 );

							a1 = new Arc( 3, true, false, in_node,g1_node);
							a1.addPoint( Sx ,Sy );
							a1.addPoint( Sx + gap, Sy );
							addArc( a1 );

							a2 = new Arc( 3, true, false, g1_node, g2_node);
							a2.addPoint( Sx + gap, Sy );
							a2.addPoint( Sx + 2*gap, Sy );
							addArc( a2 );

							a3 = new Arc( 3, true, false, g2_node, out_node);
							a3.addPoint( Sx + 2*gap,Sy );
							a3.addPoint( Sx + 3*gap, Sy );
							addArc( a3 );
							break;

				case 2:
		
					setInput( new IOPoint("R", "s", Sx, Sy, Bw, Bh, true ) );
							in_node = new Node(input, Node.IOtype);
							setOutput( new IOPoint("Y1", "s", Sx + 3*gap, Sy, Bw, Bh, false ) );
							out_node = new Node(output, Node.IOtype);

					addSummingPoint( sp1 = new SummingPoint( Sx + gap, Sy ) );
					sp1_node = new Node (sp1, Node.SPtype);
			
			 			g1= new FunctionBlock("G", "1", Sx + 2*gap, Sy, Bw, Bh );
					g1_node = new Node( g1, Node.FBtype );
					addFunctionBlock( g1 );	
																				y2 = new FunctionBlock("Y2", "s", Sx + 2*gap, Sy+gap/2, Bw, Bh );
																				y2_node = new Node( y2, Node.FBtype );
																				addFunctionBlock( y2 );
		
					a1 = new Arc( 3, true, true, in_node,sp1_node);
							a1.addPoint( Sx ,Sy );
	 					a1.addPoint( Sx + gap, Sy );
							addArc( a1 );

							a2 = new Arc( 3, true, false, sp1_node, g1_node);
							a2.addPoint( Sx + gap, Sy );
							a2.addPoint( Sx + 2*gap, Sy );
							addArc( a2 );
			
					a3 = new Arc( 3, true, false, g1_node, out_node);
							a3.addPoint( Sx + 2*gap, Sy );
							a3.addPoint( Sx + 3*gap, Sy );
							addArc( a3 );

																				a4 = new Arc( 3, true, false, y2_node, sp1_node);
							a4.addPoint( Sx + 2*gap, Sy+gap/2 );
							a4.addPoint( Sx + gap, Sy+gap/2 );
					a4.addPoint( Sx + gap, Sy );
							addArc( a4 );
																				break;
				
				case 3:
					setInput( new IOPoint("R", "s", Sx, Sy, Bw, Bh, true ) );
							in_node = new Node(input, Node.IOtype);
							setOutput( new IOPoint("Y", "s", Sx + 3*gap, Sy, Bw, Bh, false ) );
							out_node = new Node(output, Node.IOtype);
																										
			 			g1 = new FunctionBlock("G", "1", Sx + gap, Sy, Bw, Bh );
					g1_node = new Node( g1, Node.FBtype );
					addFunctionBlock( g1 );
																				y2 = new FunctionBlock("Y", "s", Sx + gap, Sy+gap/2, Bw, Bh );
																				y2_node = new Node( y2, Node.FBtype );
																				addFunctionBlock( y2 );

					a1 = new Arc( 3, true, false, in_node,g1_node);
							a1.addPoint( Sx ,Sy );
	 					a1.addPoint( Sx + gap, Sy );
							addArc( a1 );

							a2 = new Arc( 3, true, false, g1_node, out_node);
							a2.addPoint( Sx + gap, Sy );
							a2.addPoint( Sx + 3*gap, Sy );
							addArc( a2 );
			
					a3 = new Arc( 3, true, false, g1_node, y2_node);
							a3.addPoint( Sx + 2*gap, Sy );
					a3.addPoint( Sx + 2*gap, Sy+gap/2 );
							a3.addPoint( Sx + gap, Sy+gap/2 );
							addArc( a3 );
																				break;	
				
				case 4:
																				setInput( new IOPoint("R", "s", Sx, Sy, Bw, Bh, true ) );
							in_node = new Node(input, Node.IOtype);
							setOutput( new IOPoint("Y", "s", Sx + 3*gap, Sy, Bw, Bh, false ) );
							out_node = new Node(output, Node.IOtype);
																										
			 			g1 = new FunctionBlock("G", "1", Sx + 2*gap, Sy, Bw, Bh );
					g1_node = new Node( g1, Node.FBtype );
					addFunctionBlock( g1 );
																				r2 = new FunctionBlock("R", "s", Sx , Sy+gap/2, Bw, Bh );
																				r2_node = new Node( r2, Node.FBtype );
																				addFunctionBlock( r2 );
	
					a1 = new Arc( 3, true, false, in_node,g1_node);
							a1.addPoint( Sx ,Sy );
	 					a1.addPoint( Sx + 2*gap, Sy );
							addArc( a1 );

							a2 = new Arc( 3, true, false, g1_node, out_node);
							a2.addPoint( Sx + 2*gap, Sy );
							a2.addPoint( Sx + 3*gap, Sy );
							addArc( a2 );
			
					a3 = new Arc( 3, true, false, in_node, r2_node);
							a3.addPoint( Sx + gap, Sy );
					a3.addPoint( Sx + gap, Sy+gap/2 );
							a3.addPoint( Sx, Sy+gap/2 );
							addArc( a3 );
																				break;	
				
				case 5:
					setInput( new IOPoint("R1", "s", Sx, Sy, Bw, Bh, true ) );
							in_node = new Node(input, Node.IOtype);
							setOutput( new IOPoint("Y", "s", Sx + 3*gap, Sy, Bw, Bh, false ) );
							out_node = new Node(output, Node.IOtype);

					addSummingPoint( sp1 = new SummingPoint( Sx + 2*gap, Sy ) );
					sp1_node = new Node (sp1, Node.SPtype);
									
			 			g1 = new FunctionBlock("G", "1", Sx + gap, Sy, Bw, Bh );
					g1_node = new Node( g1, Node.FBtype );
					addFunctionBlock( g1 );
																				r2 = new FunctionBlock("R2", "s", Sx + gap , Sy+gap/2, Bw, Bh );
																				r2_node = new Node( r2, Node.FBtype );
																				addFunctionBlock( r2 );
	
					a1 = new Arc( 3, true, false, in_node,g1_node);
							a1.addPoint( Sx ,Sy );
	 					a1.addPoint( Sx + gap, Sy );
							addArc( a1 );

							a2 = new Arc( 3, true, true, g1_node, sp1_node);
							a2.addPoint( Sx + gap, Sy );
							a2.addPoint( Sx + 2*gap, Sy );
							addArc( a2 );
			
					a3 = new Arc( 3, true, false, sp1_node, out_node);
							a3.addPoint( Sx + 2*gap, Sy );
					a3.addPoint( Sx + 3*gap, Sy );
							addArc( a3 );
			
														a4 = new Arc( 3, true, true, r2_node, sp1_node);
							a4.addPoint( Sx + gap, Sy + gap/2 );
					a4.addPoint( Sx + 2*gap, Sy + gap/2 );
					a4.addPoint( Sx + 2*gap, Sy);
							addArc( a4 );
																				break;	
								case 6:
					setInput( new IOPoint("R", "s", Sx, Sy, Bw, Bh, true ) );
							in_node = new Node(input, Node.IOtype);
							setOutput( new IOPoint("Y", "s", Sx + 3*gap, Sy, Bw, Bh, false ) );
							out_node = new Node(output, Node.IOtype);

					addSummingPoint( sp1 = new SummingPoint( Sx + gap, Sy ) );
					sp1_node = new Node (sp1, Node.SPtype);
									
			 			g1 = new FunctionBlock("G", "1", Sx + 2*gap, Sy, Bw, Bh );
					g1_node = new Node( g1, Node.FBtype );
					addFunctionBlock( g1 );
																				h1 = new FunctionBlock("H", "1", Sx + 2*gap , Sy+gap/2, Bw, Bh );
																				h1_node = new Node( h1, Node.FBtype );
																				addFunctionBlock( h1 );
	
					a1 = new Arc( 3, true, true, in_node,sp1_node);
							a1.addPoint( Sx ,Sy );
	 					a1.addPoint( Sx + gap, Sy );
							addArc( a1 );

							a2 = new Arc( 3, true, false, sp1_node, g1_node);
							a2.addPoint( Sx + gap, Sy );
							a2.addPoint( Sx + 2*gap, Sy );
							addArc( a2 );
			
					a3 = new Arc( 3, true, false, g1_node, out_node);
							a3.addPoint( Sx + 2*gap, Sy );
					a3.addPoint( Sx + 3*gap, Sy );
							addArc( a3 );
			
														a4 = new Arc( 3, true, false, g1_node, h1_node);
							a4.addPoint( Sx + (2*gap)+(gap/2), Sy );
					a4.addPoint( Sx + (2*gap)+(gap/2), Sy + gap/2 );
					a4.addPoint( Sx + 2*gap, Sy + gap/2);
							addArc( a4 );
					
					a5 = new Arc( 3, true, true, h1_node, sp1_node);
							a5.addPoint( Sx + 2*gap, Sy + gap/2 );
					a5.addPoint( Sx + gap, Sy + gap/2);
					a5.addPoint( Sx + gap, Sy);
							addArc( a5 );
																				break;	
				case 7:
					
								setInput( new IOPoint("", "", Sx+gap, Sy, 0, 0, true ) );
					in_node = new Node(input, Node.IOtype);
					setOutput( new IOPoint("", "", Sx + 2*gap, Sy, 0, 0, false ) );
							out_node = new Node(output, Node.IOtype);
					a1 = new Arc( 100, true, false, in_node,out_node);
					a1.setColour(Color.black);
							a1.addPoint( Sx +gap,Sy );
							a1.addPoint( Sx + 2*gap, Sy );
					
							addArc( a1 ); }
		}

		 else{
			switch(++ tf_no ) {
											 case 1:
					setInput( new IOPoint("R", "s", Sx, Sy, Bw, Bh, true ) );
							in_node = new Node(input, Node.IOtype);
							setOutput( new IOPoint("Y", "s", Sx + 3*gap, Sy, Bw, Bh, false ) );
							out_node = new Node(output, Node.IOtype);

							g1 = new FunctionBlock("G1G2", "", Sx + gap + gap/2, Sy, Bw*2, Bh );
						if ( g1 == null ) {
							System.out.println( "BD cons(" + tf_no + "," + before + ") cant create FB");
							}
							addFunctionBlock( g1 );
					g1_node = new Node( g1, Node.FBtype );
							

							a1 = new Arc( 3, true, false, in_node,g1_node);
							a1.addPoint( Sx ,Sy );
							a1.addPoint( Sx + gap + gap/2, Sy );
							addArc( a1 );

							a2 = new Arc( 3, true, false, g1_node, out_node);
							a2.addPoint( Sx + gap + gap/2, Sy );
							a2.addPoint( Sx + 3*gap, Sy );
							addArc( a2 );																					 					
							break;

				case 2:
		
					setInput( new IOPoint("R", "s", Sx, Sy, Bw, Bh, true ) );
							in_node = new Node(input, Node.IOtype);
							setOutput( new IOPoint("Y1", "s", Sx + 3*gap, Sy, Bw, Bh, false ) );
							out_node = new Node(output, Node.IOtype);

					addSummingPoint( sp1 = new SummingPoint( Sx + 2*gap, Sy ) );
					sp1_node = new Node (sp1, Node.SPtype);
			
			 			g1= new FunctionBlock("G", "1", Sx + gap, Sy, Bw, Bh );
					g1_node = new Node( g1, Node.FBtype );
					addFunctionBlock( g1 );	
					g2= new FunctionBlock("G", "2", Sx + 2*gap, Sy+2*gap/3, Bw, Bh );
					g2_node = new Node( g2, Node.FBtype );
					addFunctionBlock( g2 );	
																				y2 = new FunctionBlock("Y2", "s", Sx + 3*gap, Sy+2*gap/3, Bw, Bh );
																				y2_node = new Node( y2, Node.FBtype );
																				addFunctionBlock( y2 );
		
					a1 = new Arc( 3, true, false, in_node,g1_node);
							a1.addPoint( Sx ,Sy );
	 					a1.addPoint( Sx + gap, Sy );
							addArc( a1 );

							a2 = new Arc( 3, true, true, g1_node, sp1_node);
							a2.addPoint( Sx + gap, Sy );
							a2.addPoint( Sx + 2*gap, Sy );
							addArc( a2 );
			
					a3 = new Arc( 3, true, false, sp1_node, out_node);
							a3.addPoint( Sx + 2*gap, Sy );
							a3.addPoint( Sx + 3*gap, Sy );
							addArc( a3 );

																				a4 = new Arc( 3, true, false, y2_node, g2_node);
							a4.addPoint( Sx + 3*gap, Sy+2*gap/3 );
							a4.addPoint( Sx + 2*gap, Sy+2*gap/3 );
					addArc( a4 );
	
					a5 = new Arc( 3, true, true, g2_node, sp1_node);
							a5.addPoint( Sx + 2*gap, Sy+2*gap/3 );
							a5.addPoint( Sx + 2*gap, Sy );
					addArc( a5 );
																				break;
				
				case 3:
					setInput( new IOPoint("R", "s", Sx, Sy, Bw, Bh, true ) );
							in_node = new Node(input, Node.IOtype);
							setOutput( new IOPoint("Y", "s", Sx + 3*gap, Sy, Bw, Bh, false ) );
							out_node = new Node(output, Node.IOtype);
																										
			 			g1 = new FunctionBlock("G", "1", Sx + 2*gap, Sy, Bw, Bh );
					g1_node = new Node( g1, Node.FBtype );
					addFunctionBlock( g1 );
					g2 = new FunctionBlock("G", "1", Sx + gap, Sy + 2*gap/3, Bw, Bh );
					g2_node = new Node( g2, Node.FBtype );
					addFunctionBlock( g2 );
																				y2 = new FunctionBlock("Y", "s", Sx, Sy+2*gap/3, Bw, Bh );
																				y2_node = new Node( y2, Node.FBtype );
																				addFunctionBlock( y2 );

					a1 = new Arc( 3, true, false, in_node,g1_node);
							a1.addPoint( Sx ,Sy );
	 					a1.addPoint( Sx + 2*gap, Sy );
							addArc( a1 );

							a2 = new Arc( 3, true, false, g1_node, out_node);
							a2.addPoint( Sx + 2*gap, Sy );
							a2.addPoint( Sx + 3*gap, Sy );
							addArc( a2 );
			
					a3 = new Arc( 3, true, false, in_node, g2_node);
							a3.addPoint( Sx + gap, Sy );
					a3.addPoint( Sx + gap, Sy+2*gap/3 );
							addArc( a3 );

					a4 = new Arc( 3, true, false, g2_node, y2_node);
							a4.addPoint( Sx + gap, Sy+2*gap/3);
					a4.addPoint( Sx , Sy+2*gap/3 );
							addArc( a4 );
																				break;	
				
				case 4:
																				setInput( new IOPoint("R", "s", Sx, Sy, Bw, Bh, true ) );
							in_node = new Node(input, Node.IOtype);
							setOutput( new IOPoint("Y", "s", Sx + 3*gap, Sy, Bw, Bh, false ) );
							out_node = new Node(output, Node.IOtype);
																										
			 			g1 = new FunctionBlock("G", "1", Sx + 2*gap, Sy, Bw, Bh );
					g1_node = new Node( g1, Node.FBtype );
					addFunctionBlock( g1 );
					g2 = new FunctionBlock("1/G", "", Sx + 2*gap, Sy+gap/2, Bw, Bh );
					g2_node = new Node( g2, Node.FBtype );
					addFunctionBlock( g2 );
																				r2 = new FunctionBlock("R", "s", Sx + gap , Sy+gap/2, Bw, Bh );
																				r2_node = new Node( r2, Node.FBtype );
																				addFunctionBlock( r2 );
	
					a1 = new Arc( 3, true, false, in_node,g1_node);
							a1.addPoint( Sx ,Sy );
	 					a1.addPoint( Sx + 2*gap, Sy );
							addArc( a1 );

							a2 = new Arc( 3, true, false, g1_node, out_node);
							a2.addPoint( Sx + 2*gap, Sy );
							a2.addPoint( Sx + 3*gap, Sy );
							addArc( a2 );
			
					a3 = new Arc( 3, true, false, g1_node, g2_node);
							a3.addPoint( Sx + 5*gap/2, Sy );
					a3.addPoint( Sx + 5*gap/2, Sy+gap/2 );
							a3.addPoint( Sx + 2*gap, Sy+gap/2 );
							addArc( a3 );

					a4 = new Arc( 3, true, false, g2_node, r2_node);
							a4.addPoint( Sx + 2*gap, Sy+gap/2 );
							a4.addPoint( Sx + gap, Sy+gap/2 );
							addArc( a4 );
																				break;	
				
				case 5:
					setInput( new IOPoint("R1", "s", Sx, Sy, Bw, Bh, true ) );
							in_node = new Node(input, Node.IOtype);
							setOutput( new IOPoint("Y", "s", Sx + 3*gap, Sy, Bw, Bh, false ) );
							out_node = new Node(output, Node.IOtype);

					addSummingPoint( sp1 = new SummingPoint( Sx + gap, Sy ) );
					sp1_node = new Node (sp1, Node.SPtype);
									
			 			g1 = new FunctionBlock("G", "1", Sx + 2*gap, Sy, Bw, Bh );
					g1_node = new Node( g1, Node.FBtype );
					addFunctionBlock( g1 );
					g2 = new FunctionBlock("1/G", "", Sx + 2*gap, Sy+gap/2, Bw, Bh );
					g2_node = new Node( g2, Node.FBtype );
					addFunctionBlock( g2 );
																				r2 = new FunctionBlock("R2", "s", Sx + 3*gap , Sy+gap/2, Bw, Bh );
																				r2_node = new Node( r2, Node.FBtype );
																				addFunctionBlock( r2 );
	
					a1 = new Arc( 3, true, true, in_node,sp1_node);
							a1.addPoint( Sx ,Sy );
	 					a1.addPoint( Sx + gap, Sy );
							addArc( a1 );

							a2 = new Arc( 3, true, false, sp1_node, g1_node);
							a2.addPoint( Sx + gap, Sy );
							a2.addPoint( Sx + 2*gap, Sy );
							addArc( a2 );
			
					a3 = new Arc( 3, true, false, g1_node, out_node);
							a3.addPoint( Sx + 2*gap, Sy );
					a3.addPoint( Sx + 3*gap, Sy );
							addArc( a3 );
			
														a4 = new Arc( 3, true, false, r2_node, g2_node);
							a4.addPoint( Sx + 3*gap, Sy + gap/2 );
					a4.addPoint( Sx + 2*gap, Sy + gap/2 );
					addArc( a4 );

					a5 = new Arc( 3, true, true, g2_node, sp1_node);
							a5.addPoint( Sx + 2*gap, Sy + gap/2 );
					a5.addPoint( Sx + gap, Sy + gap/2 );
					a5.addPoint( Sx + gap, Sy );
					addArc( a5 );
																				break;	
								case 6:
					setInput( new IOPoint("R", "s", Sx, Sy, Bw, Bh, true ) );
							in_node = new Node(input, Node.IOtype);
							setOutput( new IOPoint("Y", "s", Sx + 3*gap, Sy, Bw, Bh, false ) );
							out_node = new Node(output, Node.IOtype);

							g1 = new FunctionBlock("G1/1+(G1H1)", "", Sx + 3*gap/2, Sy, Bw*3, Bh );
						if ( g1 == null ) {
							System.out.println( "BD cons(" + tf_no + "," + before + ") cant create FB");
							}
							addFunctionBlock( g1 );
					g1_node = new Node( g1, Node.FBtype );
							

							a1 = new Arc( 3, true, false, in_node,g1_node);
							a1.addPoint( Sx ,Sy );
							a1.addPoint( Sx + 3*gap/2, Sy );
							addArc( a1 );

							a2 = new Arc( 3, true, false, g1_node, out_node);
							a2.addPoint( Sx + 3*gap/2, Sy );
							a2.addPoint( Sx + 3*gap, Sy );
							addArc( a2 );																					 					
							break;
					}	
							}
		 }


}
	
