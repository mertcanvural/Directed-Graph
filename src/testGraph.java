import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A test driver in which we implement JUnit5 testing to
 * ensure the implementation of our Graph is correct.
 * 
 * @author Shawn Bailey
 */
class testGraph {
	/* ! ---- TEST SETUP ---- */

	// sample_directed_graph_1.txt
	String directedAndConnectedVertices = "{1,3,2,4,5,1,2}";
	String directedAndConnectedEdges = "{(1,4),(2,1),(2,3),(3,5),(4,5),(5,2)}";

	// sample_directed_graph_2.txt
	String directedAndNotConnectedVertices = "{1,3,2,4,5,1,2}";
	String directedAndNotConnectedEdges = "{(1,4),(2,1),(2,3),(3,5),(4,5),(0,1),(0,7),(1,7)}";

	// sample_undirected_graph_1.txt
	String undirectedAndConnectedVertices = "{0,1,3,2,4,5,6,7,8,9,0,2}";
	String undirectedAndConnectedEdges = "{(0,5),(1,7),(2,4),(3,6),(4,9),(5,8),(6,9),(7,9),(8,9),(5,0)}";

	// sample_undirected_graph_2.txt
	String undirectedAndNotConnectedVertices = "{0,1,3,2,4,5,6,7,8,9,0,2}";
	String undirectedAndNotConnectedEdges = "{(0,5),(1,7),(2,4),(4,9),(5,8),(6,9),(7,9),(8,9),(5,0)}";

	// define g so that is accessible to all tests
	vural_Graph g;

	/**
	 * This method is called one time, before any of the Unit Tests are executed.
	 * In our case, we are not utilizing this method, but wanted students to be
	 * aware of its existence.
	 * 
	 * @throws Exception in our case, no exception actually gets thrown here.
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// NOP
	}

	/**
	 * This method is called before each Unit Test is executed.
	 * We create a fresh graph before each test, so our tests are independent from
	 * one another.
	 * 
	 * Note that we are assuming the graph will be undirected.
	 * For tests which need the graph to be directed, we will
	 * new the Graph again within the test unit to account for this.
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	public void setUpBeforeEach() throws Exception {
		g = new vural_Graph();
	}

	/* ! ---- BEGIN UNIT TESTING ---- */

	// 1
	// test that the zero parameter construct makes the graph undirected
	@Test
	void testIsDirectedForUndirectedGraph() {
		assertEquals(false, g.isDirected());
	}

	// 2
	// test that the one parameter construct makes the graph directed with true
	// parameter
	@Test
	void testIsDirectedForDirectedGraph() {
		g = new vural_Graph(true);
		assertTrue(g.isDirected());
	}

	// 3
	// test that the one parameter construct makes the graph undirected with false
	// parameter
	@Test
	void testIsDirectedForUndirectedGraph2() {
		g = new vural_Graph(false);
		assertFalse(g.isDirected());
	}

	// 4
	@Test
	void testGetNumberOfVertices() {
		int[] getNumberOfVertices = new int[101];
		int[] expectedResultGetNumberOfVertices = new int[getNumberOfVertices.length];
		getNumberOfVertices[0] = g.getNumberOfVertices();
		expectedResultGetNumberOfVertices[0] = 0;
		for (int i = 1; i < getNumberOfVertices.length; i++) {
			expectedResultGetNumberOfVertices[i] = i;
			try {
				g.addVertex(i - 1);
				getNumberOfVertices[i] = g.getNumberOfVertices();
			} catch (GraphException e) {
			}
		}
		assertArrayEquals(getNumberOfVertices, expectedResultGetNumberOfVertices);
	}

	// 5
	@Test
	void testGetNumberOfEdges() {
		int[] getNumberOfEdges = new int[101];
		int[] expectedResultGetNumberOfEdges = new int[getNumberOfEdges.length];
		getNumberOfEdges[0] = g.getNumberOfEdges();
		expectedResultGetNumberOfEdges[0] = 0;
		for (int i = 1; i < getNumberOfEdges.length; i++) {
			try {
				g.addVertex(i);
			} catch (GraphException e) {

			}
		}
		for (int j = 1; j < getNumberOfEdges.length; j++) {
			expectedResultGetNumberOfEdges[j] = j;
			try {
				g.addEdge(j - 1, j);
				getNumberOfEdges[j] = g.getNumberOfEdges();
			} catch (GraphException e) {

			}
		}
		assertArrayEquals(getNumberOfEdges, expectedResultGetNumberOfEdges);
	}

	// 6
	@Test
	void testAddVertex() {
		int[] numberOfVertices = new int[101];
		int[] expectedNumberOfVertices = new int[numberOfVertices.length];
		numberOfVertices[0] = g.getNumberOfVertices();
		expectedNumberOfVertices[0] = 0;
		for (int i = 0; i < numberOfVertices.length - 1; i++) {
			expectedNumberOfVertices[i + 1] = i + 1;
			try {
				g.addVertex(i);
				numberOfVertices[i + 1] = g.getNumberOfVertices();
				g.addVertex(i);
			} catch (GraphException e) {

			}
		}
		assertArrayEquals(numberOfVertices, expectedNumberOfVertices);
	}

	// 7
	@Test
	void testAddEdge() {
		int[] numberOfEdges = new int[101];
		int[] expectedNumberOfEdges = new int[numberOfEdges.length];
		numberOfEdges[0] = g.getNumberOfEdges();
		expectedNumberOfEdges[0] = 0;
		for (int i = 0; i < numberOfEdges.length - 1; i++) {
			expectedNumberOfEdges[i + 1] = i + 1;
			try {
				if (i == 0) {
					g.addVertex(i);
				}
				g.addVertex(i + 1);
				g.addEdge(i, i + 1);
				numberOfEdges[i + 1] = g.getNumberOfEdges();
				g.addEdge(i, i + 1);
			} catch (GraphException e) {
			}
		}
		assertArrayEquals(numberOfEdges, expectedNumberOfEdges);
	}

	// 8
	@Test
	void testGraphExceptionForDuplicateVertex() {
		try {
			g.addVertex(0);
		} catch (GraphException e) {
		}
		assertThrows(GraphException.class, () -> g.addVertex(0));
	}

	// 9
	@Test
	void testGraphExceptionForDuplicateEdge() {
		try {
			g.addVertex(0);
			g.addVertex(1);
			g.addEdge(0, 1);
		} catch (GraphException e) {
		}
		assertThrows(GraphException.class, () -> g.addEdge(0, 1));
	}

	// 10
	@Test
	void testGraphExceptionForDuplicateEdge2() {
		try {
			g.addVertex(0);
			g.addVertex(1);
			g.addEdge(0, 1);
		} catch (GraphException e) {
		}
		assertThrows(GraphException.class, () -> g.addEdge(1, 0));
	}

	// 11
	@Test
	void testGraphExceptionForInvalidEdge() {
		assertThrows(GraphException.class, () -> g.addEdge(0, 1));
	}

	// 12
	@Test
	void testGraphExceptionForDuplicateEdge3b() {
		g = new vural_Graph(true);
		try {
			g.addVertex(0);
			g.addVertex(1);
			g.addEdge(0, 1);
			assertDoesNotThrow(() -> g.addEdge(1, 0));
		} catch (GraphException e) {
		}
	}

	// 13
	@Test
	void testIsConnectedUndirectedAndConnected() {
		g = new vural_Graph(false);
		java.util.StringTokenizer st = new java.util.StringTokenizer(undirectedAndConnectedVertices, "{},");
		while (st.hasMoreTokens()) {
			int newVertex = Integer.parseInt(st.nextToken());

			try {
				g.addVertex(newVertex);
			} catch (GraphException e) {
			}
		}
		st = new java.util.StringTokenizer(undirectedAndConnectedEdges, "{}");
		String inn = st.nextToken();
		st = new java.util.StringTokenizer(inn, "(),");
		while (st.hasMoreTokens()) {
			int from = Integer.parseInt(st.nextToken());

			int to = Integer.parseInt(st.nextToken());
			try {
				g.addEdge(from, to);
			} catch (GraphException e) {
			}
		}
		assertEquals(true, g.isConnected());

	}

	// 14
	@Test
	void testIsConnectedUndirectedAndNotConnected() {
		g = new vural_Graph(false);
		java.util.StringTokenizer st = new java.util.StringTokenizer(undirectedAndNotConnectedVertices, "{},");
		while (st.hasMoreTokens()) {
			int newVertex = Integer.parseInt(st.nextToken());

			try {
				g.addVertex(newVertex);
			} catch (GraphException e) {
			}
		}
		st = new java.util.StringTokenizer(undirectedAndNotConnectedEdges, "{}");
		String inn = st.nextToken();
		st = new java.util.StringTokenizer(inn, "(),");
		while (st.hasMoreTokens()) {
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			try {
				g.addEdge(from, to);
			} catch (GraphException e) {
			}
		}
		assertEquals(false, g.isConnected());

	}

	// 15
	@Test
	void testIsConnectedDirectedAndConnected() {
		g = new vural_Graph(true);
		java.util.StringTokenizer st = new java.util.StringTokenizer(directedAndNotConnectedVertices, "{},");
		while (st.hasMoreTokens()) {
			int newVertex = Integer.parseInt(st.nextToken());

			try {
				g.addVertex(newVertex);
			} catch (GraphException e) {
			}
		}
		st = new java.util.StringTokenizer(directedAndConnectedEdges, "{}");
		String inn = st.nextToken();
		st = new java.util.StringTokenizer(inn, "(),");
		while (st.hasMoreTokens()) {
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			try {
				g.addEdge(from, to);
			} catch (GraphException e) {
			}
		}
		assertEquals(true, g.isConnected());

	}

	// 16
	@Test
	void testIsConnecteDirectedAndNotConnected() {
		g = new vural_Graph(true);
		java.util.StringTokenizer st = new java.util.StringTokenizer(directedAndNotConnectedVertices, "{},");
		while (st.hasMoreTokens()) {
			int newVertex = Integer.parseInt(st.nextToken());

			try {
				g.addVertex(newVertex);
			} catch (GraphException e) {
			}
		}
		st = new java.util.StringTokenizer(directedAndNotConnectedEdges, "{}");
		String inn = st.nextToken();
		st = new java.util.StringTokenizer(inn, "(),");
		while (st.hasMoreTokens()) {
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			try {
				g.addEdge(from, to);
			} catch (GraphException e) {
			}
		}

	}

	// 17
	@Test
	void testToString() {
		String expectedToString = "G = (V, E)\n";
		expectedToString = expectedToString + "V = {0,1,2,3,4,5,6,7,8,9}\n";
		expectedToString = expectedToString + "E = {(0,1),(1,2),(2,3),(3,4),(4,5),(5,6),(6,7),(7,8),(8,9)}";

		for (int i = 0; i < 10; i++) {
			try {
				g.addVertex(i);
			} catch (GraphException e) {
			}
		}
		for (int i = 0; i < 9; i++) {
			try {
				g.addEdge(i, i + 1);
			} catch (GraphException e) {
			}
		}
		assertEquals(expectedToString, g.toString());
	}
}
