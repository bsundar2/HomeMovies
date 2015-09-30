/**
 * 
 */
package edu.ncsu.csc216.flix_2.list_util;

/**
 * A linked list class containing an inner node class that serves as the
 * individual nodes in the list
 * 
 * @author Denis
 * @param <T>
 *            Accepts any object into the list
 */
public class MultiPurposeList<T> {

	/* The head of the list points to the front of the list */
	private Node head;
	/*
	 * Traverses through the list elements. A "cursor" to the list, pointing to
	 * the "next" element to be visited.
	 */
	private Node iterator;

	/**
	 * Constructs an empty list.
	 */
	public MultiPurposeList() {
		head = null;
		resetIterator();
	}

	/**
	 * Sets iterator to point to the first element in the list.
	 */
	public void resetIterator() {
		iterator = head;
	}

	/**
	 * True whenever iterator is pointing to a list element.
	 * 
	 * @return next True if the iterator.next is pointing to the next element
	 *         false if it is not.
	 */
	public boolean hasNext() {
		/*
		 * boolean next = false;
		 * 
		 * if (head != null && iterator.next != null) { next = true; } return
		 * next;
		 */
		return iterator != null;
	}

	/**
	 * Returns the element iterator is pointing to and moves iterator to point
	 * to the next element in the list. Returns null if iterator is not pointing
	 * to an element.
	 * 
	 * @return data The data field of the current element in the iterator
	 */
	public T next() {

		// T data = iterator.data;
		T data = null;
		/*
		 * if (iterator == null) { data = null; } else { if (iterator == head) {
		 * data = iterator.data; } else { data = iterator.next.data; iterator =
		 * iterator.next; } } //
		 */
		if (iterator != null) {
			data = iterator.data;
			iterator = iterator.next;
		}
		return data;
	}

	/**
	 * Adds an element (second parameter) at the given position. If the position
	 * is negative, the element is added to the front of the list. If the
	 * position is greater than the length of the list, the element is added to
	 * the end of the list.
	 * 
	 * @param pos
	 *            the position in the list to add the element in
	 * @param element
	 *            The element that need to be added to a certain position in the
	 *            list
	 */
	public void addItem(int pos, T element) {
		Node current = head;
		int size = size();

		// negative position
		if (pos <= 0) {
			current = new Node(element, head);
			head = current;
			resetIterator();
		}
		// position greater than list size
		else if (pos >= size) {
			addToRear(element);
		}
		// valid position
		else {
			// current = head;
			for (int i = 1; i < pos; i++) {
				current = current.next;
			}
			Node newNode = new Node(element, current.next);
			current.next = newNode;
		}
	}

	/**
	 * Determines if the list is empty or not
	 * 
	 * @return empty True if the list contains no items False if it does.
	 */
	public boolean isEmpty() {
		boolean empty = true;
		if (head != null) {
			empty = false;
		}
		return empty;

	}

	/**
	 * Returns the element at the given position, or null if the position is out
	 * of range
	 * 
	 * @param pos
	 *            The position of the element of interest
	 * @return The element at the position specified
	 */
	public T lookAtItemN(int pos) {
		T element = null;
		Node current = head;
		int size = size();

		// Check if the position is valid
		if (!(pos >= size) && !(pos < 0)) {
			for (int i = 0; i < pos; i++) {
				current = current.next;
			}
			element = current.data;
		}
		return element;

	}

	/**
	 * Adds an element to the rear of the list.
	 * 
	 * @param element
	 *            The element to add to the rear of the list
	 */
	public void addToRear(T element) {
		Node current = head;

		// check if the list is empty
		if (current == null) {
			current = new Node(element, head);
			head = current;
			resetIterator();
		} else {
			// Loop until a null reference is found indicating the end of the
			// list
			while (current.next != null) {
				current = current.next;
			}
			current.next = new Node(element);
		}
	}

	/**
	 * Removes and returns the element in the given position, or null if the
	 * position is out of range.
	 * 
	 * @param pos
	 *            The position of the element to remove
	 * @return Returns the object at the requested position
	 */
	public T remove(int pos) {
		T element = null;
		Node current = head;
		int size = size();

		// check if the position is valid
		if (!(pos < 0 || pos >= size)) {
			// Special case: removing the first element
			if (pos == 0) {
				element = head.data;
				head = head.next;
			}
			// remove from elsewhere in the list
			else {
				for (int i = 0; i < pos - 1; i++) { // loop to right before the
													// element to remove
					current = current.next;
				}
				// return the element
				element = current.next.data;
				// remove the node
				current.next = current.next.next;
			}
		}
		return element;
	}

	/**
	 * Moves the element at the given position ahead one position in the list.
	 * Does nothing if the element is already at the front of the list or if the
	 * position is out of range.
	 * 
	 * @param pos
	 *            The initial position of the element to move ahead one position
	 */
	public void moveAheadOne(int pos) {
		int size = size();
		// Ignore the 0 position, less then the size
		if (pos > 0 && pos < size) {
			addItem(pos - 1, remove(pos));
		}
	}

	/**
	 * Returns the number of elements in the list.
	 * 
	 * @return size The current size of the list
	 */
	public int size() {
		int size = 0;
		Node current = head;
		// Loop until a null reference is found indicating the end of the list
		if (current != null) {
			size++;
			while (current.next != null) {
				current = current.next;
				size++;
			}
		}
		return size;
	}

	/**
	 * Node class describes a node in the list. Each node has data and a pointer
	 * to the next node in the list (null if its at the end)
	 * 
	 * @author Denis
	 * 
	 */
	private class Node {
		/* The data element in the node */
		public T data;
		/* The pinter to the next node in the list */
		public Node next;

		/**
		 * The constructor for the Node with only the element argument sets the
		 * data field to the element and sets the next field to null
		 * 
		 * @param element
		 *            The data that is stored in the node
		 */
		public Node(T element) {
			this.data = element;
			this.next = null;
		}

		/**
		 * The constructor for the Node with both the element and node arguments
		 * sets the data field to the element and sets the next field to the
		 * node parameter
		 * 
		 * @param element
		 *            The data that is stored in the node
		 * @param node
		 *            The pointer to the next node
		 */
		public Node(T element, Node node) {
			this.data = element;
			this.next = node;
		}
	}
}
