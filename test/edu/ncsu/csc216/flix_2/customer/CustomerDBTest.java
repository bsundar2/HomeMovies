/**
 * 
 */
package edu.ncsu.csc216.flix_2.customer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test File for the CustomerDB Class
 * 
 * @author Denis
 * 
 */
public class CustomerDBTest {

	/* Test CustomerDB object */
	CustomerDB database;

	/**
	 * Sets up the testing for CustomerDBTest
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		database = new CustomerDB();
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.customer.CustomerDB#CustomerDB()}.
	 */
	@Test
	public void testCustomerDB() {
		CustomerDB dat = new CustomerDB();
		assertEquals("", dat.listAccounts());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.customer.CustomerDB#verifyCustomer(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testVerifyCustomer() {
		assertEquals("", database.listAccounts());
		try {
			database.verifyCustomer("a", "a");
			fail("No Exception/Didn't catch it");
		} catch (Exception e) {
			assertEquals("", database.listAccounts());
		}
		Customer testCust = new Customer("a", "a", 2);
		assertEquals("", database.listAccounts());
		database.addNewCustomer("a", "a", 2);

		assertEquals(0,
				testCust.compareToByName(database.verifyCustomer("a", "a")));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.customer.CustomerDB#listAccounts()}.
	 */
	@Test
	public void testListAccounts() {
		assertEquals("", database.listAccounts());
		database.addNewCustomer("a", "a", 2);
		assertEquals("a\n", database.listAccounts());
		database.addNewCustomer("q", "q", 2);
		assertEquals("a\nq\n", database.listAccounts());
		database.addNewCustomer("b", "b", 2);
		assertEquals("a\nb\nq\n", database.listAccounts());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.customer.CustomerDB#addNewCustomer(java.lang.String, java.lang.String, int)}
	 * .
	 */
	@Test
	public void testAddNewCustomer() {
		assertEquals("", database.listAccounts());
		database.addNewCustomer("b", "b", 2);
		assertEquals("b\n", database.listAccounts());

		// Try to add a duplicate customer
		try {
			database.addNewCustomer("b", "b", 2);
			fail("No Exception/Didn't catch it");
		} catch (Exception e) {

			assertEquals("b\n", database.listAccounts());
		}


		assertEquals("b\n", database.listAccounts());
		
		// add to the end
		database.addNewCustomer("d", "b", 2);
		assertEquals("b\nd\n", database.listAccounts());
		// insert between the two
		database.addNewCustomer("a", "b", 2);
		assertEquals("a\nb\nd\n", database.listAccounts());
		//add another one
		database.addNewCustomer("c", "b", 2);
		assertEquals("a\nb\nc\nd\n", database.listAccounts());
		
		database.cancelAccount("b");
		assertEquals("a\nc\nd\n", database.listAccounts());
		
		database.addNewCustomer("b", "b", 2);
		database.addNewCustomer("e", "b", 2);
		database.addNewCustomer("f", "b", 2);
		database.addNewCustomer("g", "b", 2);
		database.addNewCustomer("h", "b", 2);
		database.addNewCustomer("i", "b", 2);
		database.addNewCustomer("j", "b", 2);
		database.addNewCustomer("k", "b", 2);
		database.addNewCustomer("l", "b", 2);
		database.addNewCustomer("m", "b", 2);
		database.addNewCustomer("n", "b", 2);
		database.addNewCustomer("p", "b", 2);
		database.addNewCustomer("o", "b", 2);
		database.addNewCustomer("q", "b", 2);
		database.addNewCustomer("r", "b", 2);
		database.addNewCustomer("s", "b", 2);
		database.addNewCustomer("t", "b", 2);
		assertEquals(
				"a\nb\nc\nd\ne\nf\ng\nh\ni\nj\nk\nl\nm\nn\no\np\nq\nr\ns\nt\n",
				database.listAccounts());

		// adding more than 20 customers to the database
		try {
			database.addNewCustomer("u", "b", 2);
			fail("No Exception/Didn't catch it");
		} catch (Exception e) {
			assertEquals(
					"a\nb\nc\nd\ne\nf\ng\nh\ni\nj\nk\nl\nm\nn\no\np\nq\nr\ns\nt\n",
					database.listAccounts());
		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.customer.CustomerDB#cancelAccount(java.lang.String)}
	 * .
	 */
	@Test
	public void testCancelAccount() {
		// Try to cancel an invalid account
		assertEquals("", database.listAccounts());
		try {
			database.cancelAccount("a");
			fail("No Exception/Didn't catch it");
		} catch (Exception e) {
			assertEquals("", database.listAccounts());
		}

		// ad some customers
		database.addNewCustomer("a", "a", 2);
		database.addNewCustomer("c", "c", 2);
		database.addNewCustomer("b", "b", 2);
		database.addNewCustomer("d", "b", 2);
		database.addNewCustomer("e", "b", 2);
		assertEquals("a\nb\nc\nd\ne\n", database.listAccounts());

		// cancel a valid account in the front
		database.cancelAccount("a");
		assertEquals("b\nc\nd\ne\n", database.listAccounts());
		// cancel a valid account in the back
		database.cancelAccount("e");
		assertEquals("b\nc\nd\n", database.listAccounts());
		// cancel a valid account in the middle
		database.cancelAccount("c");
		assertEquals("b\nd\n", database.listAccounts());
	}

}
