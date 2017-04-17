/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017 
// PROJECT:          Program 4
// FILE:             IntervalNode.java
//
// TEAM:    Team 35 Java Badgers - P4
// Authors: Michael Yang, Kendra Raczek
// Author1: Michael Yang, yang363@wisc.edu, yang363, LEC 001
// Author2: Kendra Raczek, raczek@wisc.edu, raczek, LEC 001
//
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * This class defines the IntervalNode for the IntervalTree. This node has three
 * components: 1) interval - the data that we want to store in this node 2)
 * maxEnd - this represents the maximum end of any interval stored in the tree
 * rooted at this node 3) leftNode and rightNode - the left and right node
 * references in the IntervalTree.
 * 
 * This class will be used while constructing the IntervalTree.
 *
 * @param <T>
 *            the template parameter for the data field - interval.
 */
public class IntervalNode<T extends Comparable<T>> {
	
	// Interval stored in the node.
	private IntervalADT<T> interval;

	// Each node stores the maxEnd of the interval in its subtree.
	private T maxEnd;

	// LeftNode and RightNode.
	private IntervalNode<T> leftNode, rightNode;

	/**
	 * Constructor to create a new IntervalNode. Set the interval data structure
	 * present as member variable above and maxEnd associated with the interval.
	 * Hint: Use interval.getEnd() to get the end of the interval. Note: In your
	 * intervalTree, this will get updated subsequently.
	 * 
	 * @param interval
	 *            the interval data member.
	 */
	public IntervalNode(IntervalADT<T> interval) {
		this.interval = interval;
		this.maxEnd = interval.getEnd();
	} //end of IntervalNode(interval) constructor

	/**
	 * Returns the next in-order successor of the BST. Hint: Return left-most
	 * node in the right subtree. Return null if there is no rightNode.
	 *
	 * @return in-order successor node
	 */
	public IntervalNode<T> getSuccessor() {
	    if (this.rightNode == null) {
	        return null;
	    }
	    IntervalNode<T> n = this.rightNode;
	    while (n.getLeftNode() != null) {
	        n = n.getLeftNode();
	    }
	    return n;
	} //end of getSuccessor() method

	/**
	 * Returns the interval associated with the node.
	 * 
	 * @return the interval data field.
	 */
	public IntervalADT<T> getInterval() {
		return interval;
	} //end of getInterval() method

	/**
	 * Setter for the interval.
	 * 
	 * @param interval
	 *            the interval to be set at this node.
	 */
	public void setInterval(IntervalADT<T> interval) {
		this.interval = interval;
	} //end of setInterval(interval) method

	/**
	 * Setter for the maxEnd. This represents the maximum end point associated
	 * in any interval stored at the subtree rooted at this node (inclusive of
	 * this node).
	 * 
	 * @param maxEnd
	 *            the maxEnd associated with this node.
	 *
	 */
	public void setMaxEnd(T maxEnd) {
		this.maxEnd = maxEnd;
	} //end of setMaxEnd(maxEnd) method

	/**
	 * Getter for the maxEnd member variable.
	 * 
	 * @return the maxEnd.
	 */
	public T getMaxEnd() {
		return maxEnd;
	} //end of getMaxEnd() method

	/**
	 * Getter for the leftNode reference.
	 *
	 * @return the reference of leftNode.
	 */
	public IntervalNode<T> getLeftNode() {
		return leftNode;
	} //end of getLeftNode() method

	/**
	 * Setter for the leftNode of this node.
	 * 
	 * @param leftNode
	 *            the left node.
	 */
	public void setLeftNode(IntervalNode<T> leftNode) {
		this.leftNode = leftNode;
	} //end of setLeftNode(leftNode) method

	/**
	 * Getter for the rightNode of this node.
	 * 
	 * @return the rightNode.
	 */
	public IntervalNode<T> getRightNode() {
		return rightNode;
	} //end of getRightNode() method

	/**
	 * Setter for the rightNode of this node.
	 * 
	 * @param rightNode
	 *            the rightNode of this node.
	 */
	public void setRightNode(IntervalNode<T> rightNode) {
		this.rightNode = rightNode;
	} //end of setRightNode(rightNode) method
	
} //end of IntervalNode class
