/**
 * Our GraphException is an exception that pertains to errors that can occur
 * within the implementation of our Graph class.
 * 
 * @author Shawn Bailey
 */
class GraphException extends Exception {
	/**
	 * Default constructor
	 */
	public GraphException() {
	}

	/**
	 * Value constructor
	 * 
	 * @param message specifies some info message explaining why the GraphException
	 *                was constructed and thrown
	 */
	public GraphException(String message) {
		super(message);
	}
}
