/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017 
// PROJECT:          Program 4
// FILE:             IntervalTree.java
//
// TEAM:    Team 35 Java Badgers - P4
// Authors: Michael Yang, Kendra Raczek
// Author1: Michael Yang, yang363@wisc.edu, yang363, LEC 001
// Author2: Kendra Raczek, raczek@wisc.edu, raczek, LEC 001
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.List;
import java.util.ArrayList;

/**
 * An implementation of the IntervalTreeADT interface. This implementation 
 * defines the structure of an IntervalTree type. It supports modifying and 
 * inserting intervals as data items into nodes of this tree. 
 * See IntervalTreeADT.java for a description of each method. 
 * 
 * @param <T> A Comparable type that can be used to indicate the start 
 * and end times of an interval.
 *
 * <p>Bugs: None that we are aware of
 *
 * @author Michael Yang, Kendra Raczek
 *
 */
public class IntervalTree<T extends Comparable<T>> implements IntervalTreeADT<T> {
	//root node
	private IntervalNode<T> root;
	
	/**
	* Constructs a new interval tree instance. It resets the root.
	*/
	public IntervalTree() {
		this.root = null;
	}
	
	/**
	* Constructs a new interval tree instance. It initializes the root.
	* 
	* @param root: root of the tree
	*/
	public IntervalTree(IntervalNode<T> root) {
		this.root = root;
	}
	
	/**
	* Accesses root of an IntervalTree object
	* 
	* @return start date
	*/
	@Override
	public IntervalNode<T> getRoot() {
		return this.root;
	}
	
   /**
	* Inserts an Interval into the tree.
	* 
	* @param interval the interval (item) to insert in the tree.
	* @throws IllegalArgumentException if interval is null or is found 
	* to be a duplicate of an existing interval in this tree.            
	*/
	@Override
	public void insert(IntervalADT<T> interval)
					throws IllegalArgumentException {
		root = insertHelper(root, interval);
	}
	
	 /**
	 * This method helper call a recursive helper function with root node.
	 * Traverse the tree using the binary search algorithm. Then we use the 
	 * comparator from Interval and create a new IntervalNode to store the 
	 * new interval when we reach the end of the tree.
	 *
	 * @param node the interval node that is currently being checked.
	 * @param interval the interval (item) to insert in the tree.
	 * @throws IllegalArgumentException if interval is null or is found 
	 * 			to be a duplicate of an existing interval in this tree.  
	 * @return IntervalNode that is being inserted
	 */
	private IntervalNode<T> insertHelper(IntervalNode<T> node, 
					     IntervalADT<T> interval) throws IllegalArgumentException {
		if (node == null) {
			return new IntervalNode<T>(interval);
		}
		if (interval.compareTo(node.getInterval()) == 0 || interval == null) {
			throw new IllegalArgumentException();
		}
		if (interval.compareTo(node.getInterval()) < 0) {
			node.setLeftNode(insertHelper(node.getLeftNode(), interval));
			if (node.getLeftNode().getMaxEnd().compareTo(node.getMaxEnd()) > 0) {
				node.setMaxEnd(node.getLeftNode().getMaxEnd());
			}
			return node;
		}
		else {
			node.setRightNode(insertHelper(node.getRightNode(), interval));
			if (node.getRightNode().getMaxEnd().compareTo(node.getMaxEnd()) > 0) {
				node.setMaxEnd(node.getRightNode().getMaxEnd());
			}
			return node;
		}
	}

	 /**
	 * Deletes an Interval from the tree.
	 * 
	 * @param interval the interval to delete.
	 * @throws IllegalArgumentException if the interval is null.
	 * @throws IntervalNotFoundException
	 *             if the interval is not null, but is not found in the tree.
	 */
	@Override
	public void delete(IntervalADT<T> interval)
					throws IntervalNotFoundException, IllegalArgumentException {
		root = deleteHelper(root, interval);
	}

	/**
	 * This method helper call a recursive helper function with root node.
	 * Traverse the tree using the binary search algorithm. Then we use the 
	 * comparator from Interval and delete the specified interval.
	 *
	 * @param node the interval node that is currently being checked.
	 * @param interval the interval (item) to insert in the tree.
	 * @throws IllegalArgumentException if interval is null or is found 
	 * to be a duplicate of an existing interval in this tree.  
	 * @throws IntervalNotFoundException
	 *             if the interval is not null, but is not found in the tree.
	 * @return IntervalNode that is being deleted
	 */
	@Override
	public IntervalNode<T> deleteHelper(IntervalNode<T> node,
					IntervalADT<T> interval)
					throws IntervalNotFoundException, IllegalArgumentException {
		if (interval == null) {
			throw new IllegalArgumentException();
		}
		if (node == null) {
			throw new IntervalNotFoundException(interval.toString());
		}
		if (node.getInterval().compareTo(interval) == 0) {
			if (node.getRightNode() != null) {
				IntervalADT<T> successorVal = node.getSuccessor().getInterval();
				node.setInterval(successorVal);
				deleteHelper(node.getRightNode(), successorVal);
				node.setMaxEnd(updateMaxEnd(node));
				return node;
			}
			else {
				return node.getLeftNode();
			}
		}
		if (node.getInterval().compareTo(interval) < 0) {
			node.setRightNode(deleteHelper(node.getRightNode(), interval));
			node.setMaxEnd(updateMaxEnd(node));
			return node;
		}
		else {
			node.setLeftNode(deleteHelper(node.getLeftNode(), interval));
			node.setMaxEnd(updateMaxEnd(node));
			return node;
		}
	}
	
	/** 
	 * This method updates maxEnd of some interval nodes, as a result of an 
	 * interval's deletion. maxEnd is recalculated for any node based on the 
	 * maxEnd of its child nodes.
	 *
	 * @param nodeRecalc node tested for updating maxEnd
	 * @return new maxEnd
	 */
	private T updateMaxEnd(IntervalNode<T> nodeRecalc) {
		T endNode = nodeRecalc.getInterval().getEnd();
		nodeRecalc.setMaxEnd(endNode);
		if (nodeRecalc.getLeftNode() == null && nodeRecalc.getRightNode() == null) {
			return nodeRecalc.getMaxEnd();
		}
		if (nodeRecalc.getLeftNode() == null) {
			if (nodeRecalc.getMaxEnd().
					compareTo(nodeRecalc.getRightNode().getMaxEnd()) < 0) {
					return nodeRecalc.getRightNode().getMaxEnd();
			}
			else {
				return nodeRecalc.getMaxEnd();
			}
		}
		if (nodeRecalc.getRightNode() == null) {
			if (nodeRecalc.getMaxEnd().
					compareTo(nodeRecalc.getLeftNode().getMaxEnd()) < 0) {
					return nodeRecalc.getLeftNode().getMaxEnd();
			}
			else {
				return nodeRecalc.getMaxEnd();
			}
		}
		T maxEndVal = nodeRecalc.getLeftNode().getMaxEnd();
		
		if (maxEndVal.compareTo
					    (nodeRecalc.getRightNode().getMaxEnd()) < 0) {
			maxEndVal = nodeRecalc.getRightNode().getMaxEnd();
		}
		if (maxEndVal.compareTo(nodeRecalc.getMaxEnd()) < 0) {
			maxEndVal = nodeRecalc.getMaxEnd();
		}
		return maxEndVal;
	}

	/**
	 * Find and return a list of all intervals that overlap with the given interval. 
	 *  
	 * @param interval the interval to search for overlapping
	 * @return list of intervals that overlap with the input interval.
	 */
	@Override
	public List<IntervalADT<T>> findOverlapping(
					IntervalADT<T> interval) {
		if (interval == null) {
			throw new IllegalArgumentException();
		}
		ArrayList<IntervalADT<T>> list = new ArrayList<IntervalADT<T>>();
		return findOverlappingHelper(root, interval, list);
	}

	/**
	 * Recursive helper method  that builds a list of Intervals that overlap 
	 * the given Interval.
	 * 
	 * @param node the IntervalNode to start search
	 * @param interval the interval to search for overlapping
	 * @param list of intervals that overlap with the input interval
	 * @return list of intervals that overlap with the input interval
	 */
	private List<IntervalADT<T>> findOverlappingHelper(IntervalNode<T> node, 
			IntervalADT<T> interval, List<IntervalADT<T>> list) {
		if (node == null) {
			return list;
		}
		if (node.getInterval().overlaps(interval)) {
			list.add(node.getInterval());
		}
		list = findOverlappingHelper(node.getLeftNode(), interval, list);
		list = findOverlappingHelper(node.getRightNode(), interval, list);
		return list;
	}
	
	/**
	 * Search and return a list of all intervals containing a given point. 
	 * This method may return an empty list. 
	 * 
	 * @throws IllegalArgumentException if point is null
	 * @param point input point to search for.
	 * @return List of intervals containing the point.
	 */
	@Override
	public List<IntervalADT<T>> searchPoint(T point) {
		if (point == null) {
			throw new IllegalArgumentException();
		}
		ArrayList<IntervalADT<T>> list = new ArrayList<IntervalADT<T>>();
		return searchPointHelper(root, point, list);
	} // end of searchPoint(T point) method

	/**
	 * Recursive helper method that traverses IntervalTree, checking whether
	 * each node contains the date point, and adding them to a list.
	 * 
	 * @param node the node to start search.
	 * @param point input point to search for.
	 * @return List of intervals containing the point.
	 * @return List of intervals containing the point.
	 */
	private List<IntervalADT<T>> searchPointHelper(IntervalNode<T> node, 
				       T point, List<IntervalADT<T>> list) {
		if (node == null) {
			return list;
		}
		IntervalADT<T> interval = node.getInterval();
		if (interval.contains(point)) {
			list.add(interval);
		}
		list = searchPointHelper(node.getLeftNode(), point, list);
		list = searchPointHelper(node.getRightNode(), point, list);
		return list;
	} // end of searchPointHelper(IntervalNode<T> node, 
	  // T point, List<IntervalADT<T>> list) method

	/**
	 * Returns the size of the interval tree. The size is the total number of
	 * nodes present in the tree. 
	 * 
	 * @return int number of nodes in the tree.
	 */
	@Override
	public int getSize() {
		 return getSizeHelper(root);
	} // end of getSize() method
	
	/**
	 * Recursive helper function that completely traverses the tree, 
	 * incrementing a count of the number of nodes in the tree.
	 * 
	 * @param node the node of which to begin counting
	 * @return int number of nodes in the tree.
	 */
	private int getSizeHelper(IntervalNode<T> node) {
		if (node == null) {
			return 0;
		}
		else {
			return (getSizeHelper(node.getLeftNode()) 
				+ 1 + getSizeHelper(node.getRightNode()));
		}
	} // end of getSizeHelper(IntervalNode<T> node)

	/**
	 * Return the height of the interval tree at the root of the tree. 
	 * 
	 * @return the height of the interval tree
	 */
	@Override
	public int getHeight() {
		return getHeightHelper(root);
	} // end of getHeight() method
	
	/**
	 * Recursive helper function that returns the height of a given node, 
	 * incrementing a count of each level that is traversed.
	 * 
	 * @return the height of the interval tree
	 */
	private int getHeightHelper(IntervalNode<T> node) {
		if (node == null) {
	        	return 0;
		}
		int leftNodeHeight = getHeightHelper(node.getLeftNode());
		int rightNodeHeight = getHeightHelper(node.getRightNode());
		if (leftNodeHeight > rightNodeHeight) {
			return 1 + leftNodeHeight;
		}
		else {
			return 1 + rightNodeHeight;
		}
	} // end of getHeightHelper(IntervalNode<T> node) method

	/**
	 * Returns true if the tree contains an exact match for the start and end 
	 * of the given interval.
	 * The label is not considered for this operation.
	 * 
	 * @param interval the target interval for which to search the tree for. 
	 * @return boolean representing if the tree contains the interval.
	 */
	@Override
	public boolean contains(IntervalADT<T> interval) {
		if (interval == null) {
			throw new IllegalArgumentException();
		}
		return containsHelper(interval, root);
	} // end of contains(IntervalADT<T> interval) method

	/**
	 * Recursive helper function that traverses nodes in the tree to find if the
	 * tree contains the given Interval. It uses a recursive helper function to
	 * call with root node and the target interval.
	 * 
	 * @param interval target interval for which to search the tree for. 
	 * @return node the Interval to search for in the tree.
	 */
	private boolean containsHelper(IntervalADT<T> interval, IntervalNode<T> node) {
		if (root == null) {
			return false;
		}
		if (node.getInterval().compareTo(interval) == 0) {
			return true;
		}
		if (node.getInterval().compareTo(interval) > 0) {
			return containsHelper(interval, node.getLeftNode());
		}
		else {
			return containsHelper(interval, node.getLeftNode());
		}
	} // end of containsHelper(interval, node) method

	/**
	 * Prints String height and size information of the IntervalTree in the
	 * proper format
	 */
	@Override
	public void printStats() {
		System.out.println("-----------------------------------------");
		System.out.println("Height: " + getHeight());
		System.out.println("Size: " + getSize());
		System.out.println("-----------------------------------------");
	} // end of printStats() method

} // end of IntervalTree class
