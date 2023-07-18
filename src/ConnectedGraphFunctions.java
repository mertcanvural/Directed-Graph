/**
 * 
 * An interface which specifies how our Graph class should be implemented.
 * 
 * @author Shawn Bailey
 *
 */
interface ConnectedGraphFunctions {
	/**
	 * Getter for the Graph's number of vertices.
	 * 
	 * @return the number of vertices in the graph.
	 */
	public int getNumberOfVertices();

	/**
	 * Getter for the Graph's number of edges.
	 * 
	 * @return the number of edges in the graph.
	 */
	public int getNumberOfEdges();

	/**
	 * 
	 * Getter for whether the Graph is directed.
	 * 
	 * @return whether the graph is directed (true) or undirected (false).
	 */
	public boolean isDirected();

	/**
	 * Attempts to add a vertex to the Graph.
	 * Throws a GraphException for duplicate vertices.
	 * 
	 * @param v - the new vertex to be added.
	 * @throws GraphException - thrown if a duplicate vertex is specified.
	 */
	public void addVertex(int v) throws GraphException;

	// (1, 2) (2, 1) undirected
	// directed (1, 2) (2, 1)
	/**
	 * 
	 * Attempts to add an edge to the Graph.
	 * Throws a GraphException for duplicate / invalid edges.
	 * 
	 * @param from - the from vertex of the edge being added.
	 * @param to   - the to vertex of the edge being added.
	 * @throws GraphException - thrown if a duplicate or invalid edge is specified.
	 */
	public void addEdge(int from, int to) throws GraphException;

	/**
	 * Implementation dictates what information is printed when the
	 * Graph is printed via System.out.
	 * 
	 * @return a string representation of the information pertinent to our Graph.
	 */
	public String toString();

	/**
	 * Getter for whether the Graph is connected.
	 * Note: For an undirected graph, the return indicates whether the graph is
	 * connected.
	 * Note: For a directed graph, the return indicates whether the graph is
	 * strongly connected.
	 * 
	 * @return whether the graph is connected (true) or disconnected (false)
	 */
	public boolean isConnected();
}
