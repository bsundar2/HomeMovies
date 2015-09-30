/**
 * 
 */
package edu.ncsu.csc216.flix_2.customer;

/**
 * A database of Customers that provides the list operations required to support
 * the single-customer operations described in CustomerAccountManager. The upper
 * limit of the number of customers in the database is 20.
 * 
 * @author Denis
 * 
 */
public class CustomerDB {

	/**
	 * The maximum number of customers the system can support.
	 */
	public static final int MAX_SIZE = 20;
	/* The number of customers currently in the system. */
	private int size;
	/* The database of customers currently in the system. */
	private Customer[] list;

	/**
	 * Constructor for the CustomerDB class.
	 */
	public CustomerDB() {
		size = 0;
		list = new Customer[MAX_SIZE];
	}

	/**
	 * Returns the customer in the list whose username matches the first
	 * parameter and password matches the second. Throws an
	 * IllegalArgumentException if the username or password are null or if the
	 * password is incorrect or if the customer is not in the database.
	 * 
	 * @param username
	 *            The customer's username
	 * @param password
	 *            The customer's password
	 * @return customer the customer that matches the id (and the valid
	 *         password)
	 */
	public Customer verifyCustomer(String username, String password) {

		// throw exception if username or password is null
		if (username == null || password == null) {
			throw new IllegalArgumentException("The account doesn't exist.");
		} else {
			// traverse the list
			for (int i = 0; i < size; i++) {
				// check if customer hasn't been found yet, check if usernames
				// of
				// customers match, check if passwords of customers match
				if (list[i] != null
						&& username.compareToIgnoreCase(list[i].getId()) == 0
						&& list[i].verifyPassword(password)) {
					return list[i];
				}
			}
			// throw exception if passwords don't match
			// {
			throw new IllegalArgumentException("The account doesn't exist.");
		}

		/*
		 * Customer customer = null;
		 * 
		 * // throw exception if username or password is null if (username ==
		 * null || password == null) { throw new
		 * IllegalArgumentException("The account doesn't exist."); }
		 * 
		 * boolean found = false; System.out.println("verify customer, size:" +
		 * size); // traverse the list for (int i = 0; i < size; i++) { // check
		 * if customer hasn't been found yet, check if usernames of // customers
		 * match if (!found && username.equals(list[i].getId())) { // check if
		 * passwords of customers match if (list[i].verifyPassword(password)) {
		 * customer = list[i]; found = true; } // throw exception if passwords
		 * don't match else { throw new IllegalArgumentException(
		 * "The account doesn't exist."); } } }
		 * 
		 * // throw exception if customer's username wasn't found in the
		 * database if (customer == null || isNewCustomer(customer.getId())) {
		 * // if (isNewCustomer(customer.getId())) { throw new
		 * IllegalArgumentException("The account doesn't exist."); }
		 * 
		 * return customer;
		 */
	}

	/**
	 * Used only for testing. Returns a string of ids of customers in the list
	 * in the order the customers appear in the list. Successive ids are
	 * separated by newlines.
	 * 
	 * @return customerIds String of ids of customers in the list in the order
	 *         they appear
	 */
	public String listAccounts() {
		String customerIds = "";
		// traverse through the list
		for (int i = 0; i < size; i++) {
			customerIds += list[i].getId() + "\n";
		}
		return customerIds;
	}

	/**
	 * Adds a new customer to the list. Throws an IllegalStateException if the
	 * database is full. Throws anIllegalArgumentExceptionif there is whitespace
	 * in the username or password, or if the username or password are empty, or
	 * if there is already a customer in the database with the same username.
	 * 
	 * @param username
	 *            The customer's username
	 * @param password
	 *            The customer's password
	 * @param maxMovies
	 *            the maximum allowed movies at home
	 */

	public void addNewCustomer(String username, String password, int maxMovies) {

		// check if database is full
		if (size >= MAX_SIZE) {
			throw new IllegalStateException();
		}
		// not full we can add more customers
		else {
			// throw exception if there is whitespace in username or password
			// throw exception if username or password is empty
			if (username == null || password == null
					|| username.trim().length() == 0 || username.contains(" ")
					|| password.equals("") || password.trim().length() == 0) {
				throw new IllegalArgumentException();
			}
			// User provided valid data
			else {
				// check if customer is new
				if (!isNewCustomer(username)) {
					throw new IllegalArgumentException();
				} else {
					// Customer customer = new Customer(username, password,
					// maxMovies);
					// calls the private method
					insert(new Customer(username, password, maxMovies));
				}
			}
		}
	}

	/**
	 * Removes the customer with the given username from the list and returns
	 * any movies that customer has at home to the inventory. Throws an
	 * IllegalArgumentException if the account does not exist.
	 * 
	 * @param username
	 *            customer's username
	 */
	public void cancelAccount(String username) {
		int pos;
		if (!isNewCustomer(username)) {
			// find the account position in the array
			pos = findMatchingAccount(username);
			// close the account
			list[pos].closeAccount();
			// remove the account from the array by overwriting values
			for (int i = pos; i < size; i++) {
				list[i] = list[i + 1];
			}
			// make sure the last value is null and not a duplicate customer
			list[size - 1] = null;
			// decrement the size
			size--;
		} else {
			throw new IllegalArgumentException();
		}

	}

	/**
	 * Returns true if the customer does not exist and is new
	 * 
	 * @param username
	 *            customer's username
	 * @return true if the customer does not exist
	 */
	private boolean isNewCustomer(String username) {
		boolean newCustomer = true;

		// traverse through list to check if the customer exists
		System.out.println("size: " + size);
		for (int i = 0; i < size; i++) {
			if (username.equals(list[i].getId())) {
				newCustomer = false;
			}
		}
		return newCustomer;
	}

	/**
	 * Inserts a customer to the end of the list
	 * 
	 * @param customer
	 *            object of Customer class to be inserted
	 */
	private void insert(Customer customer) {
		// Check if the list is empty if so add at the first index
		if (size == 0) {
			list[0] = customer;
			// increment the size!
			size++;
		}
		// list is not empty need to add in alphabetical order
		else {
			boolean inserted = false;
			// int idx = 0;

			// Go thorough the list comparing the names until the name is
			// greater than the one compared against in alphabetical order
			for (int idx = 0; idx < size; idx++) {
				// if the movie passed in is alphabetically less (the next one
				// is alphabetically higher) than the one in the
				// next position insert it.
				if (0 < list[idx].compareToByName(customer)) {
					// if (0 < customer.compareToByName(list[idx])) {
					// from the position to be inserted move over the customers
					// one by one to make space for the new one
					for (int i = list.length - 1; i > idx; i--) {
						list[i] = list[i - 1];
					}
					// add the customer at the index
					list[idx] = customer;
					inserted = true;
					break;
				}
				// idx++;
			}

			// if didnt insert in the loop that means the its alphabetically
			// higher
			// then all so we add to the rear
			if (!inserted) {
				list[size] = customer;
			}
			// increment the size of the array
			size++;
		}
	}

	/**
	 * Searches the database if the customer already exists and returns the
	 * position of the customer
	 * 
	 * @param username
	 *            customer's username
	 * @return found returns position of the account if a matching account was
	 *         found
	 */
	private int findMatchingAccount(String username) {
		int pos = 0;

		for (int i = 0; i < size; i++) {
			if (username.equals(list[i].getId())) {
				pos = i;
			}
		}
		return pos;
	}
}
