/**
 * 
 */
package edu.ncsu.csc216.flix_2.inventory;

import java.util.Scanner;

/**
 * Describes a movie in the rental system inventory.
 * 
 * @author Denis
 * 
 */
public class Movie {

	/* The name of the movie. */
	private String name;
	/* The number of movies currently in stock. */
	private int inStock;

	/**
	 * Constructs a Movie from a string of the format
	 * [number-in-stock][whitespace][movie-title]. Throws an
	 * IllegalArgumentException if the string argument is not valid (exception
	 * on reading a number or empty name after trimming the white space off the
	 * ends of the name). If [number-in-stock] is negative, this constructor
	 * sets it to 0.
	 * 
	 * @param s
	 *            The input String of the format
	 *            [number-in-stock][whitespace][movie-title]
	 */
	public Movie(String s) {
		// Extra error check to make sure s is not null
		if (s == null) {
			throw new IllegalArgumentException();
		} else {
			Scanner scan = new Scanner(s);
			// scan.useDelimiter(" ");
			int number = 0;
			String tempName = "";

			/*
			 * Loop through to parse the input string (simple fsm) ... loop may
			 * not be needed as i run code through the debugger
			 */
			int pos = 0;
			while (scan.hasNext()) {
				// if starting state look for an integer
				if (pos == 0) {
					if (!scan.hasNextInt()) {

						// close the scanner
						scan.close();
						throw new IllegalArgumentException();
					} else {
						number = scan.nextInt();
						pos = 1;
					}
				}
				// if integer state look for a string
				else if (pos == 1) {
					if (!scan.hasNext()) {

						// close the scanner
						scan.close();
						throw new IllegalArgumentException();
					} else {
						tempName = scan.next();
						pos = 2;
					}
				}
				// if string state tack on more strings
				else if (pos == 2) {
					tempName += (" " + scan.next());
				}
			}

			// See if the input integer is negative, if so set it to 0
			if (number <= 0) {
				this.inStock = 0;
			} else {
				this.inStock = number;
			}

			// Check if the movie name is valid after trimming the whitespaces
			// from the ends
			if (tempName.trim().equals("")) {

				// close the scanner
				scan.close();
				throw new IllegalArgumentException();
			} else {
				this.name = tempName;
			}
			// close the scanner
			scan.close();
		}
	}

	/**
	 * Returns the movie name (title).
	 * 
	 * @return the name of the movie
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the movie title + " (currently unavailable)" if the movie is out
	 * of stock; returns only the movie title if the movie is in stock.
	 * 
	 * @return The movie name, and if its unavailbale
	 */
	public String getDisplayName() {
		String dispName = getName();
		if (!isAvailable()) {
			dispName += " (currently unavailable)";
		}
		return dispName;
	}

	/**
	 * Compares lexicographically titles of two movies to determine their order
	 * in the inventory list.
	 * 
	 * @param movie
	 *            object of movie used for comparing
	 * @return compare 0 if both movie names match
	 */
	public int compareToByName(Movie movie) {
		String thisName = removeArticle(getName());
		String compareName = removeArticle(movie.getName());

		// int compare = this.name.compareTo(movie.getName());
		int compare = thisName.compareToIgnoreCase(compareName);

		return compare;
	}

	/**
	 * Returns whether there are copies of this movie in stock in the inventory.
	 * 
	 * @return true if more than one copy of the move false if 0
	 */
	public boolean isAvailable() {
		boolean available = false;
		if (inStock > 0) {
			available = true;
		}
		return available;
	}

	/**
	 * Puts a copy of the movie back into the inventory stock
	 */
	public void backToInventory() {
		inStock++;
	}

	/**
	 * Removes a copy of the movie from the inventory stock. Throws an
	 * IllegalStateException if there are no copies of the movie in stock.
	 */
	public void removeOneCopyFromInventory() {
		if (inStock > 0) {
			inStock--;
		} else {
			throw new IllegalStateException("No copy of this movie currently available.");
		}
	}

	/**
	 * Removes "The", "An", or "A" articles from the parameter string
	 * 
	 * @param s
	 *            The string to remove Articles from
	 * @return s The string without leading Articles
	 */
	private String removeArticle(String s) {
		String str = s;
		if (str.length() > 4 && str.substring(0, 4).equalsIgnoreCase("The ")) {
			str = str.substring(4);
		} else if (str.length() > 3
				&& str.substring(0, 3).equalsIgnoreCase("An ")) {
			str = str.substring(3);
		} else if (str.length() > 2
				&& str.substring(0, 2).equalsIgnoreCase("A ")) {
			str = str.substring(2);
		}
		return str;
	}
}
