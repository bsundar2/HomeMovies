/**
 * 
 */
package edu.ncsu.csc216.flix_2.inventory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test File for the Movie Class
 * 
 * @author Denis
 * 
 */
public class MovieTest {
	/* Test input string into the movie */
	String inputString;
	/* Test Movie object */
	Movie mov;

	/**
	 * Sets up the testing for MovieTest
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		inputString = "2 Beasts of the Southern Wild";
		mov = new Movie(inputString);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.inventory.Movie#Movie(java.lang.String)}.
	 */
	@Test
	public void testMovie() {
		Movie movie;

		// test correct input
		inputString = "1  Argo";
		movie = new Movie(inputString);
		assertEquals("Argo", movie.getName());
		assertEquals("Argo", movie.getDisplayName());

		// test null input
		movie = null;
		try {
			movie = new Movie(null);
			fail("No Exception/Didn't catch it");
		} catch (Exception e) {
			assertEquals(null, movie);
		}

		// test invalid string input
		inputString = "q Invalid";
		try {
			movie = new Movie(inputString);
			fail("No Exception/Didn't catch it");
		} catch (Exception e) {
			assertEquals(null, movie);
		}

		// test invalid string input number available is a decimal
		inputString = "1.1 Some movie";
		try {
			movie = new Movie(inputString);
			fail("No Exception/Didn't catch it");
		} catch (Exception e) {
			assertEquals(null, movie);
		}

		// test valid string input if movie name is a number
		inputString = "1 9";
		movie = new Movie(inputString);
		assertEquals("9", movie.getName());
		assertEquals("9", movie.getDisplayName());

		// test to see if the negative integer gets turned into 0
		inputString = "-1  Argo";
		movie = new Movie(inputString);
		assertEquals("Argo (currently unavailable)", movie.getDisplayName());
		
		inputString = "0  Argo";
		movie = new Movie(inputString);
		assertEquals("Argo (currently unavailable)", movie.getDisplayName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.flix_2.inventory.Movie#getName()}.
	 */
	@Test
	public void testGetName() {
		inputString = "1  Cool Name";
		mov = new Movie(inputString);
		assertEquals("Cool Name", mov.getName());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.inventory.Movie#getDisplayName()}.
	 */
	@Test
	public void testGetDisplayName() {
		// Available
		inputString = "1  Mov Name";
		mov = new Movie(inputString);
		assertEquals("Mov Name", mov.getDisplayName());
		// Not Available
		inputString = "0  Mov Name";
		mov = new Movie(inputString);
		assertEquals("Mov Name (currently unavailable)", mov.getDisplayName());
		
		inputString = "1 The Manchurian Candidate";
		mov = new Movie(inputString);
		assertEquals("The Manchurian Candidate", mov.getDisplayName());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.inventory.Movie#compareToByName(edu.ncsu.csc216.flix_2.inventory.Movie)}
	 * .
	 */
	@Test
	public void testCompareToByName() {
		Movie mov2;
		inputString = "1  aMovie";
		mov = new Movie(inputString);
		inputString = "1  aMovie";
		mov2 = new Movie(inputString);
		// Movies have the same title
		assertEquals(0, mov.compareToByName(mov2));

		// second movie is higher alphabetically
		inputString = "1  bMovie";
		mov2 = new Movie(inputString);
		assertTrue(mov.compareToByName(mov2) < 0);

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.inventory.Movie#isAvailable()}.
	 */
	@Test
	public void testIsAvailable() {
		// Available
		inputString = "1  Mov Name";
		mov = new Movie(inputString);
		assertEquals("Mov Name", mov.getDisplayName());
		// Not Available
		inputString = "0  Mov Name";
		mov = new Movie(inputString);
		assertEquals("Mov Name (currently unavailable)", mov.getDisplayName());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.inventory.Movie#backToInventory()}.
	 */
	@Test
	public void testBackToInventory() {
		// Not Available
		inputString = "0  Mov Name";
		mov = new Movie(inputString);
		assertEquals("Mov Name (currently unavailable)", mov.getDisplayName());
		// Available
		mov.backToInventory();
		assertEquals("Mov Name", mov.getDisplayName());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.inventory.Movie#removeOneCopyFromInventory()}
	 * .
	 */
	@Test
	public void testRemoveOneCopyFromInventory() {
		// Available
		inputString = "1  Mov Name";
		mov = new Movie(inputString);
		assertEquals("Mov Name", mov.getDisplayName());

		// Remove the one movie
		mov.removeOneCopyFromInventory();
		// Not Available
		assertEquals("Mov Name (currently unavailable)", mov.getDisplayName());

		// Try to remove the unavailable movie
		try {
			mov.removeOneCopyFromInventory();
			fail("No Exception/Didn't catch it");
		} catch (Exception e) {
			assertEquals("Mov Name (currently unavailable)",
					mov.getDisplayName());
		}
	}

}
