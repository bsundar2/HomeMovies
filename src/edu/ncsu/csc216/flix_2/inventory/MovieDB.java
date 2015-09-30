/**
 * 
 */
package edu.ncsu.csc216.flix_2.inventory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.flix_2.list_util.MultiPurposeList;

/**
 * The database of movies in the rental system.
 * 
 * @author Denis
 * 
 */
public class MovieDB {

	/* The list of elements that comprise the movies in the inventory. */
	private MultiPurposeList<Movie> movies;

	/**
	 * Constructs the database from a file, where the parameter is the name of
	 * the file. Throws an IllegalArgumentException if the file cannot be read.
	 * 
	 * @param fileName
	 *            A String of the name of the file including the path to the
	 *            file (local file is searched for if just the name)
	 */
	public MovieDB(String fileName) {
		movies = new MultiPurposeList<Movie>();
		readFromFile(fileName);
	}

	/**
	 * Returns a string corresponding to the movies in the database in the
	 * proper order. Strings for successive movies are separated by newlines.
	 * The string is appropriate for the display in the Movie Inventory area
	 * 
	 * @return String of all the movie names
	 */
	public String traverse() {
		String movieNames = "";
		// traverse through the database list movies
		movies.resetIterator();

		while (movies.hasNext()) {
			movieNames += movies.next().getDisplayName() + "\n"; // or is it
																	// getName?
		}
		return movieNames;
	}

	/**
	 * Returns the movie at the given position. Throws an
	 * IllegalArgumentException if the position is out of range (less than 0 or
	 * >= size
	 * 
	 * @param pos
	 *            The position of the movie to find
	 * @return the movie found at the requested position
	 */
	public Movie findItemAt(int pos) {
		Movie movieToFind = null;
		int size = movies.size();
		if (pos < 0 || pos >= size) {
			throw new IllegalArgumentException();
		} else {
			movies.resetIterator();
			for (int i = 0; i < pos; i++) {
				movies.next();
			}
			movieToFind = movies.next();
		}
		return movieToFind;
	}

	/**
	 * Try to open the file with the name specified. If file can be opened throw
	 * an IllegalArgumentException if it can read it in
	 * 
	 * @param fileName
	 *            The file name to read from
	 */
	private void readFromFile(String fileName) {
		Scanner inputFileScanner = null; // null signifies NO object reference

		try {// try to create the file object with the file name provided
			inputFileScanner = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("Input file '" + fileName + "' not found.\n");
			throw new IllegalArgumentException();
		}

		while (inputFileScanner.hasNextLine()) {
			String s = inputFileScanner.nextLine();
			Movie mov = new Movie(s);
			insertInOrder(mov);
			// movies.addToRear(new Movie(inputFileScanner.nextLine()));
		}
		inputFileScanner.close();
	}

	/**
	 * Insert the passes in movie into the list keeping it in order
	 * 
	 * @param movie
	 *            The movie object to insert
	 */
	private void insertInOrder(Movie movie) {
		boolean inserted = false;
		int pos = 0;
		movies.resetIterator();
		while (movies.hasNext() && !inserted) {
			// if the movie passed in is alphabetically less (the next one is
			// alphabetically higher) than the one in the
			// next position insert it.
			if (0 < movies.next().compareToByName(movie)) {
				movies.addItem(pos, movie);
				inserted = true;
			}
			pos++;
		}

		// if didnt insert in the loop that means the its alphabetically higher
		// then all so we add to the rear
		if (!inserted) {
			movies.addToRear(movie);
		}
	}
}
