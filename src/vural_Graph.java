import java.util.ArrayList;

class vural_Graph implements ConnectedGraphFunctions {

    // Fields
    private final ArrayList<Integer> vertices; // contains the vertices
    private final ArrayList<Edge> edges; // contains the list of edges
    private final boolean isDirected; // tells us if the graph is or is not directed

    /**
     * Default constructor - we default to an undirected graph
     */
    public vural_Graph() {
        this(false);
    }

    /**
     * Value constructor
     * 
     * @param isDirected
     */
    public vural_Graph(boolean isDirected) {
        this.isDirected = isDirected;
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    /**
     * Getter for the Graph's number of vertices.
     * 
     * @return the number of vertices in the graph.
     */
    @Override
    public int getNumberOfVertices() {
        return this.vertices.size();
    }

    /**
     * Getter for the Graph's number of edges.
     * 
     * @return the number of edges in the graph.
     */
    @Override
    public int getNumberOfEdges() {
        return this.edges.size();
    }

    /**
     * 
     * Getter for whether the Graph is directed.
     * 
     * @return whether the graph is directed (true) or undirected (false).
     */
    @Override
    public boolean isDirected() {
        return this.isDirected;
    }

    /**
     * Attempts to add a vertex to the Graph.
     * Throws a GraphException for duplicate vertices.
     * 
     * @param v - the new vertex to be added.
     * @throws GraphException - thrown if a duplicate vertex is specified.
     */
    @Override
    public void addVertex(int v) throws GraphException {

        // A duplicate vertex is attempted to be added to the Graph.
        if (vertices.contains(v)) {
            throw new GraphException("GraphException: attempting to add duplicate vertex " + "\"" + v + "\"");
        } else {
            vertices.add(v);
        }
    }

    /**
     * 
     * Attempts to add an edge to the Graph.
     * Throws a GraphException for duplicate / invalid edges.
     * 
     * @param from - the from vertex of the edge being added.
     * @param to   - the to vertex of the edge being added.
     * @throws GraphException - thrown if a duplicate or invalid edge is specified.
     */
    @Override
    public void addEdge(int from, int to) throws GraphException {
        // Invalid checks
        boolean flag = false;
        for (int vertex : vertices) {
            if ((vertex == from) || vertex == to) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            throw new GraphException("An invalid edge is attempted to be added to the Graph.");
        } else {
            // Duplicate checks
            // for directed case
            if (this.isDirected()) {
                for (Edge currentEdge : edges) {
                    if ((currentEdge.fromVertex() == from) && (currentEdge.toVertex() == to)) {
                        throw new GraphException("GraphException: attempting to add duplicate vertex " + "\""
                                + currentEdge.toString() + "\"");
                    }
                }
                edges.add(new Edge(from, to));
            }
            // for undirected graph
            else {
                for (Edge currentEdge : edges) {
                    if (((currentEdge.fromVertex() == from) && (currentEdge.toVertex() == to))
                            || ((currentEdge.fromVertex() == to) && (currentEdge.toVertex() == from))) {
                        throw new GraphException("GraphException: attempting to add duplicate vertex " + "\""
                                + currentEdge.toString() + "\"");
                    }
                }
                edges.add(new Edge(from, to));
            }
        }
    }

    /**
     * Getter for whether the Graph is connected.
     * Note: For an undirected graph, the return indicates whether the graph is
     * connected.
     * Note: For a directed graph, the return indicates whether the graph is
     * strongly connected.
     * 
     * @return whether the graph is connected (true) or disconnected (false)
     * @throws GraphException
     */
    public boolean isConnected(ArrayList<Edge> edges) { // helper methd
        java.util.HashSet<Integer> connectedSubset = new java.util.HashSet<>();
        java.util.ArrayDeque<Integer> newlyAddedVertices = new java.util.ArrayDeque<>();

        int startingVertex = this.vertices.get(0);

        connectedSubset.add(startingVertex);
        newlyAddedVertices.add(startingVertex);

        while (!newlyAddedVertices.isEmpty()) {
            int currentVertex = newlyAddedVertices.pollFirst();
            // Loop over all of the edges in our Graph
            for (Edge currentEdge : edges) {
                // CASE 1: dealing with an undirected graph
                if (!this.isDirected) {

                    if (currentVertex == currentEdge.fromVertex()) {
                        if (!(connectedSubset.contains((currentEdge.toVertex())))) {
                            connectedSubset.add(currentEdge.toVertex());
                            newlyAddedVertices.add(currentEdge.toVertex());
                        }
                    } else if (currentVertex == currentEdge.toVertex()) {
                        if (!(connectedSubset.contains((currentEdge.fromVertex())))) {
                            connectedSubset.add(currentEdge.fromVertex());
                            newlyAddedVertices.add(currentEdge.fromVertex());

                        }
                    }
                }
                // CASE 2: dealing with a directed graph
                else {
                    if (currentVertex == currentEdge.fromVertex()) {
                        if (!(connectedSubset.contains((currentEdge.toVertex())))) {
                            connectedSubset.add(currentEdge.toVertex());
                            newlyAddedVertices.add(currentEdge.toVertex());

                        }
                    }
                }
            }
        }
        if (connectedSubset.size() != this.getNumberOfVertices()) {
            return false;
        }
        return true;
    }

    public boolean isConnected() {
        // Undirected case
        if (!this.isDirected) {
            return isConnected(edges);
        }
        // Directed Case
        else {
            ArrayList<Edge> reverseEdges = new ArrayList<>();
            // Check if there is a forward path with the original edges
            boolean forwardPath = isConnected(edges);

            // reverse the original array list
            for (int j = 0; j < edges.size(); j++) {
                reverseEdges.add(new Edge(edges.get(j).toVertex(), edges.get(j).fromVertex()));
            }
            // Check if there is a backward path with the reversed edges
            boolean reversePath = isConnected(reverseEdges);
            return (forwardPath && reversePath);
        }
    }

    /**
     * Implementation dictates what information is printed when the
     * Graph is printed via System.out.
     * 
     * @return a string representation of the information pertinent to our Graph.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("G = (V, E)\n");
        sb.append("V = {");
        for (int vertex : vertices) {
            sb.append(vertices.get(vertex).toString() + ",");
        }
        // removing the last comma
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}\n");
        sb.append("E = {");
        for (Edge edge : edges) {
            sb.append(edge.toString() + ",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }
}
