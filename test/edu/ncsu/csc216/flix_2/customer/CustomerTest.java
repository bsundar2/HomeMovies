/**
 * 
 */
package edu.ncsu.csc216.flix_2.customer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.flix_2.inventory.Movie;
import edu.ncsu.csc216.flix_2.list_util.MultiPurposeList;

/**
 * Test File for the Customer Class
 * 
 * @author Denis
 * 
 */
public class CustomerTest {
	/* Test Customer object */
	Customer customer;
	/* Test input id for the user */
	String id;
	/* Test input password for the user */
	String pass;
	/* Test input number of maximum movies allowed for the user */
	int maxMovies;
	/* Test MultiPurposeList */
	MultiPurposeList<Movie> movieTestQueue;

	/**
	 * Sets up the testing for CustomerTest
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		id = "d";
		pass = "pass";
		maxMovies = 2;
		customer = new Customer(id, pass, maxMovies);
		movieTestQueue = new MultiPurposeList<Movie>();
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.customer.Customer#Customer(java.lang.String, java.lang.String, int)}
	 * .
	 */
	@Test
	public void testCustomer() {
		Customer customer1 = new Customer("d", "pass", maxMovies);

		// test correct id
		assertEquals("d", customer1.getId());

		// test correct password
		assertTrue(customer1.verifyPassword("pass"));

		// test null id
		try {
			customer1 = new Customer(null, "pass", 6);
			fail("No Exception/Didn't catch it");
		} catch (IllegalArgumentException e) {
			// check to see if the id has not changed
			assertEquals(id, customer1.getId());
		}

		// test blank password
		try {
			customer1 = new Customer("user", "", 8);
			fail("No Exception/Didn't catch it");
		} catch (IllegalArgumentException e) {
			// check to see if the password hasn't changed
			assertTrue(customer1.verifyPassword(pass));
		}

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.customer.Customer#verifyPassword(java.lang.String)}
	 * .
	 */
	@Test
	public void testVerifyPassword() {
		// test correct input password
		assertTrue(customer.verifyPassword(pass));
		assertFalse(customer.verifyPassword("gibberish"));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.flix_2.customer.Customer#getId()}.
	 */
	@Test
	public void testGetId() {
		// test correct input id
		assertEquals(id, customer.getId());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.customer.Customer#compareToByName(edu.ncsu.csc216.flix_2.customer.Customer)}
	 * .
	 */
	@Test
	public void testCompareToByName() {
		// creating new customer to compare
		Customer customer1 = new Customer("d", "pass", 10);
		Customer customer2 = new Customer("C", "q", 5);
		assertEquals(0, customer.compareToByName(customer1));
		assertTrue(0 > customer2.compareToByName(customer1));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.customer.Customer#traverseReserveQueue()}.
	 */
	@Test
	public void testTraverseReserveQueue() {
		assertEquals("", customer.traverseReserveQueue());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.customer.Customer#traverseAtHomeQueue()}.
	 */
	@Test
	public void testTraverseAtHomeQueue() {
		assertEquals("", customer.traverseAtHomeQueue());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.customer.Customer#closeAccount()}.
	 */
	@Test
	public void testCloseAccount() {
		customer.reserve(new Movie("1 Amour"));
		customer.reserve(new Movie("2 Beasts of the Southern Wild"));
		customer.reserve(new Movie("3   The Impossible"));

		assertEquals("Amour\nBeasts of the Southern Wild\n",
				customer.traverseAtHomeQueue());
		assertEquals("The Impossible\n", customer.traverseReserveQueue());

		// close the account
		customer.closeAccount();
		assertEquals("", customer.traverseAtHomeQueue());
		//assertEquals("", customer.traverseReserveQueue());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.customer.Customer#returnDVD(int)}.
	 */
	@Test
	public void testReturnDVD() {
		assertEquals("", customer.traverseAtHomeQueue());
		assertEquals("", customer.traverseReserveQueue());
		try {
			customer.returnDVD(10);
			fail("No Exception/Didn't catch it");
		} catch (IllegalArgumentException e) {
			assertEquals("", customer.traverseAtHomeQueue());
			assertEquals("", customer.traverseReserveQueue());
		}

		customer.reserve(new Movie("1 Amour"));
		assertEquals("Amour\n", customer.traverseAtHomeQueue());

		try {
			customer.returnDVD(10);
			fail("No Exception/Didn't catch it");
		} catch (IllegalArgumentException e) {
			assertEquals("Amour\n", customer.traverseAtHomeQueue());
			assertEquals("", customer.traverseReserveQueue());
		}

		customer.returnDVD(0);
		assertEquals("", customer.traverseAtHomeQueue());

		customer.reserve(new Movie("1 Amour"));
		customer.reserve(new Movie("2 Beasts of the Southern Wild"));
		customer.reserve(new Movie("3   The Impossible"));

		assertEquals("Amour\nBeasts of the Southern Wild\n",
				customer.traverseAtHomeQueue());
		assertEquals("The Impossible\n", customer.traverseReserveQueue());

		customer.returnDVD(0);
		assertEquals("Beasts of the Southern Wild\nThe Impossible\n",
				customer.traverseAtHomeQueue());
		assertEquals("", customer.traverseReserveQueue());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.customer.Customer#moveAheadOneInReserves(int)}
	 * .
	 */
	@Test
	public void testMoveAheadOneInReserves() {
		assertEquals("", customer.traverseAtHomeQueue());
		assertEquals("", customer.traverseReserveQueue());
		// test out of bounds position
		try {
			customer.moveAheadOneInReserves(10);
			fail("No Exception/Didn't catch it");
		} catch (IllegalArgumentException e) {
			assertEquals("", customer.traverseAtHomeQueue());
			assertEquals("", customer.traverseReserveQueue());
		}
		customer.reserve(new Movie("1 Amour"));
		customer.reserve(new Movie("2 Beasts of the Southern Wild"));
		customer.reserve(new Movie("3   The Impossible"));
		customer.reserve(new Movie("4 Jack Reacher"));

		assertEquals("Amour\nBeasts of the Southern Wild\n",
				customer.traverseAtHomeQueue());
		assertEquals("The Impossible\nJack Reacher\n",
				customer.traverseReserveQueue());

		// test a valid position
		customer.moveAheadOneInReserves(1);
		assertEquals("Amour\nBeasts of the Southern Wild\n",
				customer.traverseAtHomeQueue());
		assertEquals("Jack Reacher\nThe Impossible\n",
				customer.traverseReserveQueue());

		// test the front position - shouldn't do anything
		customer.moveAheadOneInReserves(0);
		assertEquals("Amour\nBeasts of the Southern Wild\n",
				customer.traverseAtHomeQueue());
		assertEquals("Jack Reacher\nThe Impossible\n",
				customer.traverseReserveQueue());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.customer.Customer#unReserve(int)}.
	 */
	@Test
	public void testUnReserve() {
		assertEquals("", customer.traverseAtHomeQueue());
		assertEquals("", customer.traverseReserveQueue());
		// Test the out of bounds position
		try {
			customer.unReserve(10);
			fail("No Exception/Didn't catch it");
		} catch (IllegalArgumentException e) {
			assertEquals("", customer.traverseAtHomeQueue());
			assertEquals("", customer.traverseReserveQueue());
		}
		// add some movies
		customer.reserve(new Movie("1 Amour"));
		customer.reserve(new Movie("2 Beasts of the Southern Wild"));
		customer.reserve(new Movie("3   The Impossible"));
		customer.reserve(new Movie("4 Jack Reacher"));

		assertEquals("Amour\nBeasts of the Southern Wild\n",
				customer.traverseAtHomeQueue());
		assertEquals("The Impossible\nJack Reacher\n",
				customer.traverseReserveQueue());
		// un-reserve a valid position
		customer.unReserve(1);
		assertEquals("Amour\nBeasts of the Southern Wild\n",
				customer.traverseAtHomeQueue());
		assertEquals("The Impossible\n", customer.traverseReserveQueue());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.customer.Customer#reserve(edu.ncsu.csc216.flix_2.inventory.Movie)}
	 * .
	 */
	@Test
	public void testReserve() {
		assertEquals("", customer.traverseAtHomeQueue());
		assertEquals("", customer.traverseReserveQueue());
		// test a null pass in
		try {
			customer.reserve(null);
			fail("No Exception/Didn't catch it");
		} catch (IllegalArgumentException e) {
			assertEquals("", customer.traverseAtHomeQueue());
			assertEquals("", customer.traverseReserveQueue());
		}

		// reserve one movie - should add to the home list
		customer.reserve(new Movie("1 Amour"));
		assertEquals("Amour\n", customer.traverseAtHomeQueue());
		assertEquals("", customer.traverseReserveQueue());

		// reserve 2 more one should go to home the other to reserve queue b/c
		// the limit for he customer is 2 movies
		customer.reserve(new Movie("2 Beasts of the Southern Wild"));
		customer.reserve(new Movie("3   The Impossible"));
		assertEquals("Amour\nBeasts of the Southern Wild\n",
				customer.traverseAtHomeQueue());
		assertEquals("The Impossible\n", customer.traverseReserveQueue());
	}
}
