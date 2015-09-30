/**
 * 
 */
package edu.ncsu.csc216.flix_2.rental_system;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.flix_2.customer.Customer;

/**
 * Test File for the DVDRentalSystem Class
 * 
 * @author Denis
 * 
 */
public class DVDRentalSystemTest {

	/* File name to test from the MovieDB class */
	String fileName = "MoviesInput_SimpleTest.txt";
	/* A DVDRental system object to test */
	DVDRentalSystem rentalSystem;

	/* Test customer to use with the resnalSystem test object */
	Customer testCustomer;

	/**
	 * Sets up the testing for DVDRentalSystemTest
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		rentalSystem = new DVDRentalSystem(fileName);
		testCustomer = new Customer("id", "pass", 3);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.rental_system.DVDRentalSystem#DVDRentalSystem(java.lang.String)}
	 * .
	 */
	@Test
	public void testDVDRentalSystem() {
		DVDRentalSystem rentalSystem1 = new DVDRentalSystem(fileName);

		assertTrue(null != rentalSystem1.showInventory()
				|| 0 != "".compareTo(rentalSystem1.showInventory()));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.rental_system.DVDRentalSystem#showInventory()}
	 * .
	 */
	@Test
	public void testShowInventory() {
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

		assertEquals(0, displayOrdered.compareTo(rentalSystem.showInventory()));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.rental_system.DVDRentalSystem#setCustomer(edu.ncsu.csc216.flix_2.customer.Customer)}
	 * .
	 */
	@Test
	public void testSetCustomer() {
		try {
			rentalSystem.traverseAtHomeQueue();
			fail("No Exception/Didn't catch it)");
		} catch (Exception e) {
			System.out.println("Exception caught by testSetCustomer");
		}
		rentalSystem.setCustomer(testCustomer);
		assertEquals(0, "".compareTo(rentalSystem.traverseAtHomeQueue()));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.rental_system.DVDRentalSystem#addToCustomerQueue(int)}
	 * .
	 */
	@Test
	public void testAddToCustomerQueue() {
		try {
			rentalSystem.traverseAtHomeQueue();
			fail("No Exception/Didn't catch it)");
		} catch (Exception e) {
			System.out.println("Exception caught by testAddToCustomerQueue");
		}
		rentalSystem.setCustomer(testCustomer);
		assertEquals(0, "".compareTo(rentalSystem.traverseAtHomeQueue()));
		rentalSystem.addToCustomerQueue(0);

		System.out.println("Traversing home queue:"
				+ rentalSystem.traverseAtHomeQueue());
		System.out.println("Traversing reserve queue:"
				+ rentalSystem.traverseReserveQueue());
		assertEquals(0, "Amour\n".compareTo(rentalSystem.traverseAtHomeQueue()));
		assertEquals(0, "".compareTo(rentalSystem.traverseReserveQueue()));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.rental_system.DVDRentalSystem#reserveMoveAheadOne(int)}
	 * .
	 */
	@Test
	public void testReserveMoveAheadOne() {
		try {
			rentalSystem.reserveMoveAheadOne(0);
			fail("No Exception/Didn't catch it)");
		} catch (Exception e) {
			System.out.println("Exception caught by testAddToCustomerQueue");
		}
		rentalSystem.setCustomer(testCustomer);
		assertEquals(0, "".compareTo(rentalSystem.traverseAtHomeQueue()));
		rentalSystem.addToCustomerQueue(0);

		// System.out.println("Traversing home queue:/n" +
		// rentalSystem.traverseAtHomeQueue());
		// System.out.println("Traversing reserve queue:/n" +
		// rentalSystem.traverseReserveQueue());
		assertEquals(0, "Amour\n".compareTo(rentalSystem.traverseAtHomeQueue()));
		assertEquals(0, "".compareTo(rentalSystem.traverseReserveQueue()));

		rentalSystem.addToCustomerQueue(1);
		rentalSystem.addToCustomerQueue(2);
		rentalSystem.addToCustomerQueue(3);
		rentalSystem.addToCustomerQueue(4);
		System.out.println("Traversing home queue:\n"
				+ rentalSystem.traverseAtHomeQueue());
		System.out.println("Traversing reserve queue:\n"
				+ rentalSystem.traverseReserveQueue());

		assertEquals(0,
				"Amour\nBeasts of the Southern Wild\nThe Impossible\n"
						.compareTo(rentalSystem.traverseAtHomeQueue()));
		assertEquals(0, "Jack Reacher\nMurder 3\n".compareTo(rentalSystem
				.traverseReserveQueue()));

		rentalSystem.reserveMoveAheadOne(1);
		assertEquals(0,
				"Amour\nBeasts of the Southern Wild\nThe Impossible\n"
						.compareTo(rentalSystem.traverseAtHomeQueue()));
		assertEquals(0, "Murder 3\nJack Reacher\n".compareTo(rentalSystem
				.traverseReserveQueue()));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.rental_system.DVDRentalSystem#removeSelectedFromReserves(int)}
	 * .
	 */
	@Test
	public void testRemoveSelectedFromReserves() {
		try {
			rentalSystem.removeSelectedFromReserves(0);
			fail("No Exception/Didn't catch it)");
		} catch (Exception e) {
			System.out.println("Exception caught by testAddToCustomerQueue");
		}

		rentalSystem.setCustomer(testCustomer);
		rentalSystem.addToCustomerQueue(0);
		rentalSystem.addToCustomerQueue(1);
		rentalSystem.addToCustomerQueue(2);
		rentalSystem.addToCustomerQueue(3);
		rentalSystem.addToCustomerQueue(4);

		assertEquals(0, "Jack Reacher\nMurder 3\n".compareTo(rentalSystem
				.traverseReserveQueue()));
		rentalSystem.removeSelectedFromReserves(1);
		assertEquals(0,
				"Jack Reacher\n".compareTo(rentalSystem.traverseReserveQueue()));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.rental_system.DVDRentalSystem#traverseReserveQueue()}
	 * .
	 */
	@Test
	public void testTraverseReserveQueue() {
		try {
			rentalSystem.traverseReserveQueue();
			fail("No Exception/Didn't catch it)");
		} catch (Exception e) {
			System.out.println("Exception caught by testAddToCustomerQueue");
		}
		rentalSystem.setCustomer(testCustomer);
		assertEquals(0, "".compareTo(rentalSystem.traverseAtHomeQueue()));
		assertEquals(0, "".compareTo(rentalSystem.traverseReserveQueue()));
		rentalSystem.addToCustomerQueue(0);

		assertEquals(0, "Amour\n".compareTo(rentalSystem.traverseAtHomeQueue()));
		assertEquals(0, "".compareTo(rentalSystem.traverseReserveQueue()));

		rentalSystem.addToCustomerQueue(1);
		rentalSystem.addToCustomerQueue(2);
		rentalSystem.addToCustomerQueue(3);
		rentalSystem.addToCustomerQueue(4);

		assertEquals(0,
				"Amour\nBeasts of the Southern Wild\nThe Impossible\n"
						.compareTo(rentalSystem.traverseAtHomeQueue()));
		assertEquals(0, "Jack Reacher\nMurder 3\n".compareTo(rentalSystem
				.traverseReserveQueue()));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.rental_system.DVDRentalSystem#traverseAtHomeQueue()}
	 * .
	 */
	@Test
	public void testTraverseAtHomeQueue() {
		try {
			rentalSystem.traverseAtHomeQueue();
			fail("No Exception/Didn't catch it)");
		} catch (Exception e) {
			System.out.println("Exception caught by testAddToCustomerQueue");
		}
		rentalSystem.setCustomer(testCustomer);
		assertEquals(0, "".compareTo(rentalSystem.traverseAtHomeQueue()));
		assertEquals(0, "".compareTo(rentalSystem.traverseReserveQueue()));
		rentalSystem.addToCustomerQueue(0);

		assertEquals(0, "Amour\n".compareTo(rentalSystem.traverseAtHomeQueue()));
		assertEquals(0, "".compareTo(rentalSystem.traverseReserveQueue()));

		rentalSystem.addToCustomerQueue(1);
		rentalSystem.addToCustomerQueue(2);
		rentalSystem.addToCustomerQueue(3);
		rentalSystem.addToCustomerQueue(4);

		assertEquals(0,
				"Amour\nBeasts of the Southern Wild\nThe Impossible\n"
						.compareTo(rentalSystem.traverseAtHomeQueue()));
		assertEquals(0, "Jack Reacher\nMurder 3\n".compareTo(rentalSystem
				.traverseReserveQueue()));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.rental_system.DVDRentalSystem#returnItemToInventory(int)}
	 * .
	 */
	@Test
	public void testReturnItemToInventory() {
		try {
			rentalSystem.returnItemToInventory(0);
			fail("No Exception/Didn't catch it)");
		} catch (Exception e) {
			System.out.println("Exception caught by testAddToCustomerQueue");
		}
		rentalSystem.setCustomer(testCustomer);
		assertEquals(0, "".compareTo(rentalSystem.traverseAtHomeQueue()));
		assertEquals(0, "".compareTo(rentalSystem.traverseReserveQueue()));
		rentalSystem.addToCustomerQueue(0);

		assertEquals(0, "Amour\n".compareTo(rentalSystem.traverseAtHomeQueue()));
		assertEquals(0, "".compareTo(rentalSystem.traverseReserveQueue()));

		rentalSystem.returnItemToInventory(0);
		assertEquals(0, "".compareTo(rentalSystem.traverseAtHomeQueue()));
		assertEquals(0, "".compareTo(rentalSystem.traverseReserveQueue()));
	}

}
