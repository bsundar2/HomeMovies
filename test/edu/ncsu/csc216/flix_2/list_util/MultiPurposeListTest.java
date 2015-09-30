/**
 * 
 */
package edu.ncsu.csc216.flix_2.list_util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.flix_2.inventory.Movie;

/**
 * Test File for the MultiPurposeList Class
 * 
 * @author Denis
 * 
 */
public class MultiPurposeListTest {
	/* An instance of list of integers */
	MultiPurposeList<Integer> intList;
	/* Sample data to put into the list */
	Integer data1 = new Integer(0);
	/* Sample data to put into the list */
	Integer data2 = new Integer(1);
	/* Sample data to put into the list */
	Integer data3 = new Integer(2);
	/* Sample data to put into the list */
	Integer data4 = new Integer(99);

	/**
	 * Sets up the testing forMultiPurposeListTest
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		intList = new MultiPurposeList<Integer>();
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.list_util.MultiPurposeList#MultiPurposeList()}
	 * .
	 */
	@Test
	public void testMultiPurposeList() {
		MultiPurposeList<Integer> testList = new MultiPurposeList<Integer>();
		assertTrue(testList.isEmpty());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.list_util.MultiPurposeList#resetIterator()}
	 * .
	 */
	@Test
	public void testResetIterator() {
		intList.addItem(0, data1);
		intList.addItem(1, data2);
		assertEquals(data1, intList.next());
		intList.resetIterator();
		assertEquals(data1, intList.next());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.list_util.MultiPurposeList#hasNext()}.
	 */
	@Test
	public void testHasNext() {
		assertFalse(intList.hasNext());
		intList.addItem(0, data1);
		assertTrue(intList.hasNext());
		intList.addItem(1, data2);
		assertTrue(intList.hasNext());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.list_util.MultiPurposeList#next()}.
	 */
	@Test
	public void testNext() {
		assertEquals(null, intList.next());

		intList.addItem(0, data1);
		assertEquals(data1, intList.next());

		intList.addItem(1, data2);
		intList.resetIterator();
		assertEquals(data1, intList.next());
		assertEquals(data2, intList.next());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.list_util.MultiPurposeList#addItem(int, java.lang.Object)}
	 * .
	 */
	@Test
	public void testAddItem() {
		MultiPurposeList<Integer> intList2 = new MultiPurposeList<Integer>();

		/* Using the first list */
		// Empty list size 0
		assertEquals(0, intList.size());
		// Add first item
		intList.addItem(0, data1);
		// Size 1
		assertEquals(1, intList.size());
		// Add 2nd item
		intList.addItem(0, data2);
		// Size 2
		assertEquals(2, intList.size());

		intList.resetIterator();
		// first value is data2
		assertEquals(data2, intList.next());
		// second value is data1
		assertEquals(data1, intList.next());

		/* Using the second list */
		// Empty list size 0
		assertEquals(0, intList2.size());

		// Add to a negative position
		intList2.addItem(-3, data1);
		// Size 1
		assertEquals(1, intList2.size());

		// Add to the position 1
		intList2.addItem(1, data2);
		// Size 2
		assertEquals(2, intList2.size());

		// Add to a greater than the list size position
		intList2.addItem(99, data3);
		// Size 3
		assertEquals(3, intList2.size());

		// first value is data1
		assertEquals(data1, intList2.next());
		// second value is data2
		assertEquals(data2, intList2.next());
		assertEquals(data3, intList2.next());

		// Add to the position 1
		intList2.addItem(1, data4);
		// Size 4
		assertEquals(4, intList2.size());

		intList2.resetIterator();
		assertEquals(data1, intList2.next());
		assertEquals(data4, intList2.next());
		assertEquals(data2, intList2.next());
		// assertEquals(data4, intList2.next());// Is this right???? or should
		// this
		// be in idx=1 spot where data 2
		// is????
		assertEquals(data3, intList2.next());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.list_util.MultiPurposeList#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(intList.isEmpty());
		intList.addItem(1, data1);
		intList.remove(0);
		assertEquals(0, intList.size());
		assertTrue(intList.isEmpty());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.list_util.MultiPurposeList#lookAtItemN(int)}
	 * .
	 */
	@Test
	public void testLookAtItemN() {
		assertEquals(null, intList.lookAtItemN(-1));
		
		assertEquals(null, intList.lookAtItemN(0));
		assertEquals(null, intList.lookAtItemN(1));

		intList.addItem(0, data2);
		assertEquals(data2, intList.lookAtItemN(0));
		assertEquals(null, intList.lookAtItemN(1));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.list_util.MultiPurposeList#addToRear(java.lang.Object)}
	 * .
	 */
	@Test
	public void testAddToRear() {
		// Add data1
		intList.addToRear(data1);
		assertEquals(1, intList.size());
		intList.resetIterator();
		assertEquals(data1, intList.next());

		// add data2
		intList.addToRear(data2);
		intList.resetIterator();
		assertEquals(2, intList.size());
		assertEquals(data1, intList.next());
		assertEquals(data2, intList.next());

		// Try adding movies
		MultiPurposeList<Movie> movieList = new MultiPurposeList<Movie>();

		// add movie
		Movie mov1 = new Movie("2 Beasts of the Southern Wild\n");
		movieList.addToRear(mov1);
		movieList.resetIterator();
		assertEquals(mov1, movieList.next());

		// add another movie
		Movie mov2 = new Movie("3 Promised Land");
		movieList.addToRear(mov2);
		movieList.resetIterator();
		assertEquals(mov1, movieList.next());
		assertEquals(mov2, movieList.next());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.list_util.MultiPurposeList#remove(int)}.
	 */
	@Test
	public void testRemove() {
		assertEquals(0, intList.size());
		intList.addItem(1, data1);
		assertEquals(1, intList.size());
		intList.remove(1);
		assertEquals(1, intList.size());
		intList.remove(0);
		assertEquals(0, intList.size());

		intList.addItem(0, data1);
		intList.addItem(1, data2);
		intList.addItem(2, data3);
		assertEquals(3, intList.size());
		intList.remove(1);
		assertEquals(2, intList.size());

		intList.resetIterator();
		assertEquals(data1, intList.next());
		assertEquals(data3, intList.next());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.list_util.MultiPurposeList#moveAheadOne(int)}
	 * .
	 */
	@Test
	public void testMoveAheadOne() {
		// Empty List (size = 0)
		assertEquals(null, intList.lookAtItemN(0));
		assertEquals(0, intList.size());
		intList.moveAheadOne(0);
		assertEquals(0, intList.size());
		intList.moveAheadOne(1);
		assertEquals(0, intList.size());
		assertEquals(null, intList.lookAtItemN(0));

		// Add an item (size = 1)
		assertEquals(null, intList.lookAtItemN(0));
		intList.addItem(0, data1);
		assertEquals(data1, intList.lookAtItemN(0));
		assertEquals(1, intList.size());
		intList.moveAheadOne(0);
		assertEquals(1, intList.size());
		intList.moveAheadOne(1);
		assertEquals(1, intList.size());
		assertEquals(data1, intList.lookAtItemN(0));

		// Add another item (size = 2)
		intList.addItem(1, data2);
		assertEquals(data1, intList.lookAtItemN(0));
		assertEquals(data2, intList.lookAtItemN(1));
		assertEquals(2, intList.size());
		intList.moveAheadOne(0);
		assertEquals(2, intList.size());
		assertEquals(data1, intList.lookAtItemN(0));
		assertEquals(data2, intList.lookAtItemN(1));
		intList.moveAheadOne(1);
		assertEquals(2, intList.size());
		assertEquals(data2, intList.lookAtItemN(0));
		assertEquals(data1, intList.lookAtItemN(1));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.flix_2.list_util.MultiPurposeList#size()}.
	 */
	@Test
	public void testSize() {
		assertEquals(0, intList.size());
		intList.addItem(1, data1);
		assertEquals(1, intList.size());
		intList.remove(0);
		assertEquals(0, intList.size());
	}

}
