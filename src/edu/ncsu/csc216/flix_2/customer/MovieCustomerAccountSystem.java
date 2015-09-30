/**
 * 
 */
package edu.ncsu.csc216.flix_2.customer;

import edu.ncsu.csc216.flix_2.rental_system.RentalManager;

/**
 * Represents the customer part of the overall system, implements
 * CustomerAccountManager
 * 
 * @author Denis
 * 
 */
public class MovieCustomerAccountSystem implements CustomerAccountManager {
	/* True if and only if the administrator is logged into the system. */
	private boolean adminLoggedIn;
	/* True if and only if a customer is logged into the system. */
	private boolean customerLoggedIn;
	/**/
	private static final String ADMIN = "admin";

	/* Database of customers in the system. */
	private CustomerDB customerList;
	/* The rental inventory associated with the system. */
	private RentalManager inventorySystem;

	/**
	 * Constructor Initializes the customerList and inventorySystem. The
	 * inventory system is set to the parameter passed into the constructor Sets
	 * the loggedIn variables to false
	 * 
	 * @param rentals
	 *            a DVD rental system that is passed into the constructor.
	 * 
	 */
	public MovieCustomerAccountSystem(RentalManager rentals) {
		// TODO Auto-generated constructor stub
		customerList = new CustomerDB();
		inventorySystem = rentals;
		customerLoggedIn = false;
		adminLoggedIn = false;
	}

	/**
	 * Logs a user into the system.
	 * 
	 * @param username
	 *            id/username of the user
	 * @param password
	 *            user's password
	 * @throws IllegalStateException
	 *             if a customer or the administrator is already logged in
	 * @throws IllegalArgumentException
	 *             if the customer account does not exist
	 * @see edu.ncsu.csc216.flix_2.customer.CustomerAccountManager#login(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public void login(String username, String password) {
		// TODO Auto-generated method stub
		if (isAdminLoggedIn() || isCustomerLoggedIn()) {
			throw new IllegalStateException("Current customer or admin must first log out.");
		} else {
			// Customer logInCustomer = customerList.verifyCustomer(username,
			// password);
			if (username.equalsIgnoreCase(ADMIN)
					&& password.equalsIgnoreCase(ADMIN)) {
				adminLoggedIn = true;
			} else {
				inventorySystem.setCustomer(customerList.verifyCustomer(
						username, password));
				customerLoggedIn = true;
			}
		}

	}

	/**
	 * Logs the current customer or administrator out of the system.
	 * 
	 * @see edu.ncsu.csc216.flix_2.customer.CustomerAccountManager#logout()
	 */
	@Override
	public void logout() {
		// TODO Auto-generated method stub
		customerLoggedIn = false;
		adminLoggedIn = false;
	}

	/**
	 * Is an administrator logged into the system?
	 * 
	 * @return true if yes, false if no
	 * 
	 * @see edu.ncsu.csc216.flix_2.customer.CustomerAccountManager#isAdminLoggedIn()
	 */
	@Override
	public boolean isAdminLoggedIn() {
		// TODO Auto-generated method stub
		return adminLoggedIn;
	}

	/**
	 * Is a customer logged into the system?
	 * 
	 * @return true if yes, false if no
	 * 
	 * @see edu.ncsu.csc216.flix_2.customer.CustomerAccountManager#isCustomerLoggedIn
	 *      ()
	 */
	@Override
	public boolean isCustomerLoggedIn() {
		// TODO Auto-generated method stub
		return customerLoggedIn;
	}

	/**
	 * Add a new customer to the customer database. The administrator must be
	 * logged in.
	 * 
	 * @param id
	 *            id/email for new customer
	 * @param password
	 *            new customer's password
	 * @param num
	 *            number associated with this customer
	 * @throws IllegalStateException
	 *             if the database is full or the administrator is not logged in
	 * @throws IllegalArgumentException
	 *             if customer with given id is already in the database
	 * 
	 * @see edu.ncsu.csc216.flix_2.customer.CustomerAccountManager#addNewCustomer
	 *      (java.lang.String, java.lang.String, int)
	 */
	@Override
	public void addNewCustomer(String id, String password, int num) {
		// TODO Auto-generated method stub

		// Admin is not logged in!!!
		if (!isAdminLoggedIn()) {
			throw new IllegalStateException("Access denied.");
		} else {
			// Add customer (IllegalArgumentException will propagate through if
			// something is wrong)
			customerList.addNewCustomer(id, password, num);
		}

	}

	/**
	 * Cancel a customer account.
	 * 
	 * @param id
	 *            id/username of the customer to cancel
	 * @throws IllegalStateException
	 *             if the administrator is not logged in
	 * @throws IllegalArgumentException
	 *             if no matching account is found
	 * 
	 * @see edu.ncsu.csc216.flix_2.customer.CustomerAccountManager#cancelAccount(java.lang.String)
	 */
	@Override
	public void cancelAccount(String id) {
		// TODO Auto-generated method stub
		// Admin is not logged in!!!
		if (!isAdminLoggedIn()) {
			throw new IllegalStateException("Access denied.");
		} else {
			// Add customer (IllegalArgumentException will propagate through if
			// something is wrong)
			customerList.cancelAccount(id);
		}
	}

	/**
	 * List all customer accounts.
	 * 
	 * @return string of customer usernames separated by newlines
	 * 
	 * @see edu.ncsu.csc216.flix_2.customer.CustomerAccountManager#listAcounts()
	 */
	@Override
	public String listAcounts() {
		// TODO Auto-generated method stub
		return customerList.listAccounts();
	}

}
