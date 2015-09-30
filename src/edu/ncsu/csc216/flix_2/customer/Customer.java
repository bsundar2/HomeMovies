/**
 * 
 */
package edu.ncsu.csc216.flix_2.customer;

import edu.ncsu.csc216.flix_2.inventory.Movie;
import edu.ncsu.csc216.flix_2.list_util.MultiPurposeList;

/**
 * A system customer. Each customer has an atHomeQueue and reserveQueue of
 * movies currently at home and on reserve.
 * 
 * @author Denis
 */
public class Customer {

	/** The customer's user name */
	private String id;
	/** The customer's password */
	private String password;
	/** The maximum allowable number of rental movie DVDs at home. */
	private int maxAtHome;
	/** The number of rental movie DVDs currently at home. */
	private int nowAtHome;

	/*
	 * Movies that the customer now has at home. Elements are added to the end
	 * of this list.
	 */
	private MultiPurposeList<Movie> atHomeQueue;
	/*
	 * Movies in the customer's reserve queue. Elements are added to the end of
	 * this list.
	 */
	private MultiPurposeList<Movie> reserveQueue;

	/**
	 * Customer constructor initializes the instance variables (id, password and
	 * maxAtHome). Throws an IllegalArgumentException if the first arguments are
	 * null or of length 0 after trimming whitespace from the ends. If the
	 * maximum number of movies is negative, it is set to 0.
	 * 
	 * @param id
	 *            The user login id
	 * @param password
	 *            The user login password
	 * @param maxAtHome
	 *            The maximum movies the customer can have at home
	 */
	public Customer(String id, String password, int maxAtHome) {
		if (id == null || password == null || id.trim().length() == 0
				|| password.trim().length() == 0) {

			throw new IllegalArgumentException();

		} else {

			this.id = id.trim();
			this.password = password.trim();
		}

		if (maxAtHome < 0) {
			this.maxAtHome = 0;
		} else {
			this.maxAtHome = maxAtHome;
		}

		atHomeQueue = new MultiPurposeList<Movie>();

		reserveQueue = new MultiPurposeList<Movie>();
	}

	/**
	 * Checks to see if the password matches the set password
	 * 
	 * @param pass
	 *            Password to compare to
	 * @return match True if the parameter matches the password false if it
	 *         doesn't.
	 */
	public boolean verifyPassword(String pass) {
		boolean match = false;
		if (pass.trim().equals(password)) {
			match = true;
		}
		return match;
	}

	/**
	 * Returns the customer's id (username).
	 * 
	 * @return id the user login id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Lexicographic, case-insensitive comparison of two customers
	 * 
	 * @param customer
	 *            A Customer object that can be compared against
	 * @return compare returns 0 if the user names of the customers match
	 */
	public int compareToByName(Customer customer) {
		int compare = this.id.compareToIgnoreCase(customer.getId());
		// int compare = customer.getId().compareToIgnoreCase(id);
		return compare;
	}

	/**
	 * Returns a string of names of movies in the reserve queue in order.
	 * Successive movies are separated by newlines.
	 * 
	 * @return movieNames String of names of movies in the reserve queue in
	 *         order.
	 */
	public String traverseReserveQueue() {

		return traverseQueue(reserveQueue);

	}

	/**
	 * Returns a string of names of movies at home in order. Successive movies
	 * are separated by newlines
	 * 
	 * @return movieNames String of names of movies at home in order.
	 */
	public String traverseAtHomeQueue() {

		return traverseQueue(atHomeQueue);

	}

	/**
	 * Closes this account and returns all movies at home to the inventory.
	 */
	public void closeAccount() {
		// Save the original size to make sure we go through the entire list
		// int reserveSize = reserveQueue.size();
		int homeSize = atHomeQueue.size();
		// return from queue first so it doesnt add to the home queue when those
		// are removed
		// for (int i = 0; i < reserveSize; i++) {
		// always remove at the first movie to get rid of all otherwise a
		// null pointer exception...
		// unReserve(0);
		// }
		// now remove from the home queue to remove all of the moves from the
		// database
		//while (homeSize > 0) {
			for (int i = 0; i < homeSize; i++) {
				// always remove at the first movie to get rid of all otherwise
				// a
				// null pointer exception...
				// returnDVD(0);

				atHomeQueue.lookAtItemN(0).backToInventory();
				atHomeQueue.remove(0);
				nowAtHome--;

			}
		//	homeSize = atHomeQueue.size();
		//}
	}

	/**
	 * Removes the movie in the given position from the queue of movies at home
	 * and returns it to the inventory. Throws an IllegalArgumentException if
	 * the position is out of bounds.
	 * 
	 * @param pos
	 *            The position in the queue
	 */
	public void returnDVD(int pos) {

		if (pos < 0 || pos > nowAtHome) {
			throw new IllegalArgumentException();
		} else {
			atHomeQueue.lookAtItemN(pos).backToInventory();
			atHomeQueue.remove(pos);
			nowAtHome--;

			// get the next movie from the reserve queue if there is one and add
			// it to the home queue
			if (!reserveQueue.isEmpty()) {
				int i = 0;
				boolean removed = false;
				while (!removed && i < reserveQueue.size()) {
					if (reserveQueue.lookAtItemN(i).isAvailable()) {
						Movie movie = reserveQueue.remove(i);
						atHomeQueue.addToRear(movie);
						movie.removeOneCopyFromInventory();
						nowAtHome++;
						removed = true;
					} else {
						i++;
					}
				}
			}
		}
	}

	/**
	 * Moves the movie in the given position ahead one position in the reserve
	 * queue. Throws an IllegalArgumentException if the position is out of
	 * bounds. If the position is 0, there is no exception but there is also no
	 * change in the list.
	 * 
	 * @param pos
	 *            The position in the queue
	 */
	public void moveAheadOneInReserves(int pos) {
		if (pos < 0 || pos > reserveQueue.size()) {
			throw new IllegalArgumentException();
		} else {
			reserveQueue.moveAheadOne(pos);
		}

	}

	/**
	 * Removes the movie in the given position from the reserve queue. Throws an
	 * IllegalArgumentException if the position is out of bounds.
	 * 
	 * @param pos
	 *            Position in the queue
	 */
	public void unReserve(int pos) {
		if (pos < 0 || pos > reserveQueue.size()) {
			throw new IllegalArgumentException("No movie selected.");
		} else {
			reserveQueue.remove(pos);
		}

	}

	/**
	 * Places the movie at the end of the reserve queue (UC9). Throws an
	 * IllegalArgumentException if Movie is null.
	 * 
	 * @param movie
	 *            Object of movie class
	 */
	public void reserve(Movie movie) {
		// Throw Exception if Movie is null
		if (movie == null) {
			throw new IllegalArgumentException("Movie not specified.");
		} else {
			// Make sure that the movie is available to rent
			if (movie.isAvailable()) {
				// can we add to the home queue directely?
				if (nowAtHome < maxAtHome) {
					atHomeQueue.addToRear(movie);
					movie.removeOneCopyFromInventory();
					nowAtHome++;
				}
				// Home queue is full add to the reserve queue
				else {
					reserveQueue.addToRear(movie);
				}
			} else {
				reserveQueue.addToRear(movie);
			}
		}

	}

	/**
	 * Returns a string of names of movies in the queue in order. Successive
	 * movies are separated by newlines.
	 * 
	 * @param movieList
	 *            A queue of movies of type MultiPurposeList
	 * @return movieNames String of names of movies in the queue in order
	 */
	private String traverseQueue(MultiPurposeList<Movie> movieList) {
		String movieNames = "";

		movieList.resetIterator();

		while (movieList.hasNext()) {
			movieNames += movieList.next().getName() + "\n";
		}

		return movieNames;
	}

	/**
	 * Description Goes Here
	 */
	/*
	 * private void checkOut() { // TODO Auto-generated constructor stub
	 * System.out.println("Doing Nothing Need to fix this later!!!"); }
	 */
	/**
	 * Description Goes Here
	 * 
	 * @return
	 */
	/*
	 * private Movie removeFirstAvailable() { // TODO Auto-generated constructor
	 * stub for (int i = 0; i < reserveQueue.size(); i++) { if
	 * (reserveQueue.lookAtItemN(i).isAvailable()) { return reserveQueue.next();
	 * } }
	 * 
	 * return null; }
	 */
}
