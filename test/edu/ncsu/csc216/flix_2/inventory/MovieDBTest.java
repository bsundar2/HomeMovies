/**
 * 
 */
package edu.ncsu.csc216.flix_2.inventory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test File for the MovieDB Class
 * 
 * @author Denis
 * 
 */
public class MovieDBTest {
	/* File name to test from the MovieDB class */
	String fileName = "MoviesInput_SimpleTest.txt";
	/* a movie database instance to test from */
	MovieDB movDB;

	/**
	 * Sets up the testing for MovieDBTest
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		movDB = new MovieDB(fileName);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.inventory.MovieDB#MovieDB(java.lang.String)}
	 * .
	 */
	@Test
	public void testMovieDB() {
		MovieDB movDBTest = null;
		try {
			movDBTest = new MovieDB("invalidFileName");
			fail("No Exception/Didn't catch it");
		} catch (Exception e) {
			assertEquals(null, movDBTest);
		}

		movDBTest = new MovieDB(fileName);
		assertTrue(movDBTest != null);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.inventory.MovieDB#traverse()}.
	 */
	@Test
	public void testTraverse() {
		String s1 = "Beast of the Sounthern Wild";
		String s2 = "Promised Land";
		String s3 = "No";
		String s4 = "The Impossible";
		// String s5 = "Jack Reacher";
		// String s6 = "Armor";

		System.out.println();
		System.out.println("Compare");
		System.out.println(s1.compareTo(s2));
		System.out.println(s1.compareTo(s3));
		System.out.println(s2.compareTo(s3));
		// System.out.println(s1.compareTo(s2));
		System.out.println();
		System.out.println();
		System.out.println(s4.substring(4));
		System.out.println(s4.substring(0, 4));
		System.out.println(s4.substring(0, 4).equalsIgnoreCase("The "));
		/*
		 * String display_Unordered = ""; display_Unordered +=
		 * "Beasts of the Southern Wild\n"; display_Unordered +=
		 * "Promised Land\n"; display_Unordered += "No\n"; display_Unordered +=
		 * "The Impossible\n"; display_Unordered += "Jack Reacher\n";
		 * display_Unordered += "Amour\n"; display_Unordered +=
		 * "Side Effects\n"; display_Unordered += "Safe Haven\n";
		 * display_Unordered += "Murder 3\n"; display_Unordered +=
		 * "Noah (currently unavailable)\n"; //
		 */
		String displayOrdered = "";
		displayOrdered += "Amour\n";
		displayOrdered += "Beasts of the Southern Wild\n";
		displayOrdered += "The Impossible\n"; // ignoring articles
		displayOrdered += "Jack Reacher\n";
		displayOrdered += "Murder 3\n";
		displayOrdered += "No\n";
		displayOrdered += "Noah (currently unavailable)\n";
		displayOrdered += "Promised Land\n";
		displayOrdered += "Safe Haven\n";
		displayOrdered += "Side Effects\n";
		// displayOrdered += "The Impossible\n";//ignoring articles

		System.out.println("display var: \n" + displayOrdered);
		System.out.println("traverce movDB: \n" + movDB.traverse());
		assertEquals(0, displayOrdered.compareTo(movDB.traverse()));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.inventory.MovieDB#findItemAt(int)}.
	 */
	@Test
	public void testFindItemAt() {
		String displayOrdered = "";
		displayOrdered += "Amour\n";
		displayOrdered += "Beasts of the Southern Wild\n";
		displayOrdered += "The Impossible\n";
		displayOrdered += "Jack Reacher\n";
		displayOrdered += "Murder 3\n";
		displayOrdered += "No\n";
		displayOrdered += "Noah (currently unavailable)\n";
		displayOrdered += "Promised Land\n";
		displayOrdered += "Safe Haven\n";
		displayOrdered += "Side Effects\n";

		assertEquals(0, displayOrdered.compareTo(movDB.traverse()));

		try {
			movDB.findItemAt(-1);
			fail("No Exception/Didn't catch it");
		} catch (Exception e) {
			assertEquals(0, displayOrdered.compareTo(movDB.traverse()));
			System.out.println("Caught The Exception!");
		}

		Movie mov1 = new Movie("1 Amour");
		Movie mov2 = new Movie("4 Jack Reacher");
		Movie mov3 = new Movie("1 Side Effects");

		assertTrue(0 == movDB.findItemAt(0).compareToByName(mov1));
		assertTrue(0 == movDB.findItemAt(3).compareToByName(mov2));
		assertTrue(0 == movDB.findItemAt(9).compareToByName(mov3));

		try {
			movDB.findItemAt(10);
			fail("No Exception/Didn't catch it");
		} catch (Exception e) {
			assertEquals(0, displayOrdered.compareTo(movDB.traverse()));
			System.out.println("Caught The Exception!");
		}
	}
}
