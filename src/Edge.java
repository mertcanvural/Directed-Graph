/**
 * 
 * The record Edge is a representation of some edge between two vertices in our
 * Graph.
 * 
 * @author Shawn Bailey
 */
record Edge(Integer fromVertex, Integer toVertex) {
	/**
	 * Dictates how edges information is printed.
	 * 
	 * @return a String representation of the edge's fromVertex and toVertex
	 */
	@Override
	public String toString() {
		return "(" + fromVertex + "," + toVertex + ")";
	}
}/*
* ! ---- BELOW NOT NEEDED for new versions of Java that support Record types
* ----
*//*
* class Edge
* {
* 	private final Integer fromVertex;
* 	private final Integer toVertex;
* 
* 	Edge(Integer fromVertex, Integer toVertex)
* 	{
* 		this.fromVertex = fromVertex;
* 		this.toVertex = toVertex;
* 	}
* 
* 	public Integer fromVertex()
* 	{
* 		return fromVertex;
* 	}
* 
* 	public Integer toVertex()
* 	{
* 		return toVertex;
* 	}
* 
* 	public String toString()
* 	{
* 		return "(" + fromVertex + "," + toVertex + ")";
* 	}
* }
*/
