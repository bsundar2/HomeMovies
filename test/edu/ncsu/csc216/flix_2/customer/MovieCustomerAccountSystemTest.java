/**
 * 
 */
package edu.ncsu.csc216.flix_2.customer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.flix_2.rental_system.DVDRentalSystem;
import edu.ncsu.csc216.flix_2.rental_system.RentalManager;

/**
 * Test File for the MovieCustomerAccountSystem Class
 * 
 * @author Denis
 * 
 */
public class MovieCustomerAccountSystemTest {
	/* File name to test from the MovieDB class */
	String fileName = "MoviesInput_SimpleTest.txt";

	/** Test object of the MovieCustomerAccountSystem class */
	MovieCustomerAccountSystem system;

	/** Test object of the RentalManager class */
	RentalManager manager;

	/** Test username and password for the admin */
	String admin;

	/** Test object of the CustomerDB class */
	// CustomerDB customerList;

	/**
	 * Sets up the testing for MovieCustomerAccountSystem Class
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		manager = new DVDRentalSystem(fileName);
		admin = "admin";
		system = new MovieCustomerAccountSystem(manager);
		// customerList = new CustomerDB();
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.customer.MovieCustomerAccountSystem#MovieCustomerAccountSystem(edu.ncsu.csc216.flix_2.rental_system.RentalManager)}
	 * .
	 */
	@Test
	public void testMovieCustomerAccountSystem() {
		// check if the variables are initialized properly
		assertFalse(system.isAdminLoggedIn());
		assertFalse(system.isCustomerLoggedIn());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.customer.MovieCustomerAccountSystem#login(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testLogin() {

		// test exception thrown if customer does not exist
		try {
			system.login("user", "password");
			fail("No Exception/Didn't catch it)");
		} catch (IllegalArgumentException e) {
			assertFalse(system.isCustomerLoggedIn());
		}

		// test exception thrown if the admin has invalid username or password
		try {
			system.login(admin, "password");
			fail("No Exception/Didn't catch it)");
		} catch (IllegalArgumentException e) {
			assertFalse(system.isAdminLoggedIn());
		}

		// test exception thrown if a user has not logged out
		// and another user tries to log in
		system.login(admin, admin);
		assertTrue(system.isAdminLoggedIn());
		assertFalse(system.isCustomerLoggedIn());
		try {
			system.login("username", "password");
			fail("No Exception/Didn't catch it)");
		} catch (IllegalStateException e) {
			assertTrue(system.isAdminLoggedIn());
			assertFalse(system.isCustomerLoggedIn());
		}

		system.logout();

		// test if admin is logged in
		system.login(admin, admin);
		assertTrue(system.isAdminLoggedIn());
		system.logout();

		// test if a customer is logged in
		system.login(admin, admin);
		system.addNewCustomer("user", "user", 10);
		system.logout();
		// System.out.println(system.listAcounts());
		system.login("user", "user");
		assertTrue(system.isCustomerLoggedIn());
		system.logout();
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.customer.MovieCustomerAccountSystem#logout()}
	 * .
	 */
	@Test
	public void testLogout() {
		// test if the admin logged in has logged out
		system.login(admin, admin);
		assertTrue(system.isAdminLoggedIn());
		system.logout();
		assertFalse(system.isAdminLoggedIn());

		// Create a new customer to work with
		system.login(admin, admin);
		system.addNewCustomer("user", "user", 10);
		system.logout();

		// test if the customer logged in has logged out
		system.login("user", "user");
		assertTrue(system.isCustomerLoggedIn());
		system.logout();
		assertFalse(system.isCustomerLoggedIn());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.customer.MovieCustomerAccountSystem#isAdminLoggedIn()}
	 * .
	 */
	@Test
	public void testIsAdminLoggedIn() {
		// test if the admin has logged in and logged out
		system.login(admin, admin);
		assertTrue(system.isAdminLoggedIn());
		system.logout();
		assertFalse(system.isAdminLoggedIn());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.customer.MovieCustomerAccountSystem#isCustomerLoggedIn()}
	 * .
	 */
	@Test
	public void testIsCustomerLoggedIn() {
		// Create a new customer to work with
		system.login(admin, admin);
		system.addNewCustomer("user", "user", 10);
		system.logout();
		// test if the customer logged in has logged out
		system.login("user", "user");
		assertTrue(system.isCustomerLoggedIn());
		system.logout();
		assertFalse(system.isCustomerLoggedIn());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.customer.MovieCustomerAccountSystem#addNewCustomer(java.lang.String, java.lang.String, int)}
	 * .
	 */
	@Test
	public void testAddNewCustomer() {
		// test exception thrown if admin is not logged in
		// and we try to create a new customer	
		assertEquals("", system.listAcounts());
		try {
			system.addNewCustomer("user", "password", 10);
			fail("No Exception/Didn't catch it)");
		}
		catch(IllegalStateException e) {
			assertEquals("", system.listAcounts());
		}
		
		assertEquals("", system.listAcounts());
		// creating a new user
		system.login(admin, admin);
		// test if exceptions are propogated when
		// username or password is null
		try {
			system.addNewCustomer(null, "user1", 10);
			fail("No Exception/Didn't catch it)");
		}
		catch (Exception e) {
			assertEquals("", system.listAcounts());
		}
		system.logout();
		
		
		// test creating and adding a new customer
		assertEquals("", system.listAcounts());
		system.login(admin, admin);
		system.addNewCustomer("user", "user", 10);
		system.logout();
		assertEquals("user\n", system.listAcounts());
		
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.customer.MovieCustomerAccountSystem#cancelAccount(java.lang.String)}
	 * .
	 */
	@Test
	public void testCancelAccount() {
		// creating a test user account to work with
		system.login(admin, admin);
		system.addNewCustomer("user", "user", 10);
		system.logout();
		assertEquals("user\n", system.listAcounts());
		
		// test exception thrown if admin is not logged in
		// and we try to cancel a customer's account	
		assertEquals("user\n", system.listAcounts());
		try {
			system.cancelAccount("user");
			fail("No Exception/Didn't catch it)");
		}
		catch(IllegalStateException e) {
			assertEquals("user\n", system.listAcounts());
		}
		
		assertEquals("user\n", system.listAcounts());
		// creating a new user
		system.login(admin, admin);
		// test if exceptions are propogated when
		// username or password is null
		try {
			system.addNewCustomer(null, "user1", 10);
			fail("No Exception/Didn't catch it)");
		}
		catch (Exception e) {
			assertEquals("user\n", system.listAcounts());
		}
		system.logout();
		
		// test canceling a customer's account
		assertEquals("user\n", system.listAcounts());
		system.login(admin, admin);
		system.cancelAccount("user");
		system.logout();
		assertEquals("", system.listAcounts());
		
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.customer.MovieCustomerAccountSystem#listAcounts()}
	 * .
	 */
	@Test
	public void testListAcounts() {
		// adding a new customer and listing the accounts
		assertEquals("", system.listAcounts());
		system.login(admin, admin);
		system.addNewCustomer("user", "password", 10);
		system.logout();
		assertEquals("user\n", system.listAcounts());
		
		// adding two more customers to check if 
		// it lists the usernames is lexicographic order
		assertEquals("user\n", system.listAcounts());
		system.login(admin, admin);
		system.addNewCustomer("realUser", "password", 10);
		system.addNewCustomer("theRealUser", "password", 10);
		system.logout();
		assertEquals("realUser\ntheRealUser\nuser\n", system.listAcounts());
	}

}
