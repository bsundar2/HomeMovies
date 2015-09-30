/**
 * 
 */
package edu.ncsu.csc216.flix_2.rental_system;

import edu.ncsu.csc216.flix_2.customer.Customer;
import edu.ncsu.csc216.flix_2.inventory.MovieDB;

/**
 * Represents the inventory part of the overall system in the context of a
 * single customer, implements RentalManager.
 * 
 * @author Denis
 * 
 */
public class DVDRentalSystem implements RentalManager {
	/* The customer currently logged into the system. */
	Customer currentCustomer;
	/* The database of movies in the system. */
	MovieDB inventory;

	/**
	 * Initializes the Movie Database inventory
	 * 
	 * @param filename
	 *            A String for the file name
	 * 
	 */
	public DVDRentalSystem(String filename) {
		inventory = new MovieDB(filename);
	}

	/**
	 * Traverse all items in the inventory.
	 * 
	 * @return the string representing the items in the inventory
	 * 
	 * @see edu.ncsu.csc216.flix_2.rental_system.RentalManager#showInventory()
	 */
	@Override
	public String showInventory() {
		return inventory.traverse();
	}

	/**
	 * Set the customer for the current context to a given value.
	 * 
	 * @param c
	 *            the new current customer
	 * @see edu.ncsu.csc216.flix_2.rental_system.RentalManager#setCustomer(edu.ncsu
	 *      .csc216.flix_2.customer.Customer)
	 */
	// DOES THIS REALLY THROW AN EXCEPTION????
	@Override
	public void setCustomer(Customer c) {
		//if (c == null) {
		//	throw new IllegalStateException("No customer is logged in.");
		//} else {
			this.currentCustomer = c;
		//}
	}

	/**
	 * Reserve the selected item for the reserve queue.
	 * 
	 * @param position
	 *            position of the selected item in the inventory
	 * @throws IllegalStateException
	 *             if no customer is logged in
	 * @throws IllegalArgumentException
	 *             if position is out of bounds
	 * @see edu.ncsu.csc216.flix_2.rental_system.RentalManager#addToCustomerQueue
	 *      (int)
	 */
	@Override
	public void addToCustomerQueue(int position) {
		if (currentCustomer == null) {
			throw new IllegalStateException("No customer is logged in.");
		} else {
			// Movie mov = inventory.findItemAt(position);
			// currentCustomer.reserve(mov);
			currentCustomer.reserve(inventory.findItemAt(position));
		}
	}

	/**
	 * Move the item in the given position up 1 in the reserve queue.
	 * 
	 * @param position
	 *            current position of item to move up one
	 * @throws IllegalStateException
	 *             if no customer is logged in
	 * @see edu.ncsu.csc216.flix_2.rental_system.RentalManager#reserveMoveAheadOne
	 *      (int)
	 */
	@Override
	public void reserveMoveAheadOne(int position) {
		if (currentCustomer == null) {
			throw new IllegalStateException("No customer is logged in.");
		} else {
			currentCustomer.moveAheadOneInReserves(position);
		}
	}

	/**
	 * Remove the item in the given position from the reserve queue.
	 * 
	 * @param position
	 *            position of the item in the queue
	 * @throws IllegalStateException
	 *             if no customer is logged in
	 * @throws IllegalArgumentException
	 *             if position is out of bounds
	 * @see edu.ncsu.csc216.flix_2.rental_system.RentalManager#removeSelectedFromReserves
	 *      (int)
	 */
	@Override
	public void removeSelectedFromReserves(int position) {
		if (currentCustomer == null) {
			throw new IllegalStateException("No customer is logged in.");
		} else {
			currentCustomer.unReserve(position);
		}

	}

	/**
	 * Traverse all items in the reserve queue.
	 * 
	 * @return string representation of items in the queue
	 * @throws IllegalStateException
	 *             if no customer is logged in
	 * @see edu.ncsu.csc216.flix_2.rental_system.RentalManager#traverseReserveQueue()
	 */
	@Override
	public String traverseReserveQueue() {
		String itemsInQueue = "";
		if (currentCustomer == null) {
			throw new IllegalStateException("No customer is logged in.");
		} else {
			itemsInQueue = currentCustomer.traverseReserveQueue();
		}
		return itemsInQueue;
	}

	/**
	 * Traverse all items in the reserve queue.
	 * 
	 * @return string representation of items at home
	 * @throws IllegalStateException
	 *             if no customer is logged in
	 * @see edu.ncsu.csc216.flix_2.rental_system.RentalManager#traverseAtHomeQueue()
	 */
	@Override
	public String traverseAtHomeQueue() {
		String itemsInQueue = "";
		if (currentCustomer == null) {
			throw new IllegalStateException("No customer is logged in.");
		} else {
			itemsInQueue = currentCustomer.traverseAtHomeQueue();
		}
		return itemsInQueue;
	}

	/**
	 * Return the selected item to the inventory.
	 * 
	 * @param position
	 *            location in the list of items at home of the item to return
	 * @throws IllegalStateException
	 *             if no customer is logged in
	 * @throws IllegalArgumentException
	 *             if position is out of bounds
	 * @see edu.ncsu.csc216.flix_2.rental_system.RentalManager#returnItemToInventory
	 *      (int)
	 */
	@Override
	public void returnItemToInventory(int position) {
		if (currentCustomer == null) {
			throw new IllegalStateException("No customer is logged in.");
		} else {
			currentCustomer.returnDVD(position);
		}
	}

}
