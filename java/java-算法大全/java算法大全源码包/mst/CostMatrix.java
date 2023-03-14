import java.awt.*;

public class CostMatrix {
	double Adj_Matrix[][];
	int num_nodes, num_edges;
	Graph graph;
	
	public CostMatrix( Graph graph ) {
		this.graph = graph;
		num_nodes = graph.Node_Cnt();
		num_edges = graph.Edge_Cnt();
		Adj_Matrix = new double[num_nodes][num_nodes];
	}

	public void Fill_Matrix( ) {
		int i, j, k;
		Edge edges[];

		edges = graph.Edge_Array();
		
		for (i = 0; i < num_nodes; i++) {
			for ( j = 0; j < num_nodes; j++) {
					Adj_Matrix[i][j] = 0.00;
				}
			}
		
		for (i = 0; i < num_nodes; i++) {
			for (k = 0; k < num_edges; k++) {
				if ((edges[k].Get_Start()) == i+1) {
					j = edges[k].Get_End() - 1;
					Adj_Matrix[i][j] = edges[k].Cost();
					}
				}
			}
		}

	public double Cost( int i, int j ) {
		return Adj_Matrix[i][j];
		}

	public int Length( ) {
		return num_nodes;
		}
}
