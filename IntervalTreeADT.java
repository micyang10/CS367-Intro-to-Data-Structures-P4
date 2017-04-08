import java.util.List;

/**
 * THIS INTERFACE IS PROVIDED TO STUDENTS AND WILL NOT BE SUBMITTED<br />
 * -- DO NOT EDIT THIS INTERFACE
 * 
 * <p>This interface defines the structure of an <code>IntervalTree</code> type. 
 * It supports modifying and inserting intervals as data items into 
 * nodes of this tree.</p>
 * 
 * <p>You must create a class named <code>IntervalTree</code> that implements
 * this interface. Your class <code>IntervalTree</code> must have following:</p>
 * 
 * <p>You may (and are encouraged to) define private helper methods as needed.
 * An example: <code>deleteHelper</code> is included to help give you an idea.</p>
 *	
 * <p>A default no-arg constructor that properly initialized the IntervalTree.</p>
 * <pre>public IntervalTree()</pre>
 *
 * <p>A constructor that constructs a tree the given node as its root node.</p>
 * <pre>public IntervalTree(IntervalNode&lt;T&gt; root)</pre>
 *
 * @param <T> A Comparable type that can be used to indicate the start 
 * and end times of an interval.
 */
public interface IntervalTreeADT<T extends Comparable<T>> {

	/** Returns the root node of this IntervalTree. */
	public IntervalNode<T> getRoot();

	/**
	 * Inserts an <code>Interval</code> in the tree.
	 * 
	 * <p>Each <code>Interval</code> is stored as the data item of an
	 * <code>IntervalNode</code>.  The position of the new IntervalNode 
	 * will be the position found using the binary search algorithm.
	 * This is the same algorithm presented in BST readings and lecture
	 * examples. 
	 * 
	 * <p>Tip: Call a recursive helper function with root node.
	 * In that call, traverse the tree using the binary search algorithm.
	 * Use the comparator defined in <code>Interval</code> and create a new
	 * IntervalNode to store the new <i>interval</i> item when you reach 
	 * the end of the tree.</p>
	 * 
	 * <p>This method must also check and possibly update the maxEnd 
	 * in the IntervalNode. Recall, that <b>maxEnd</b> of a node represents 
	 * the maximum end of current node and all descendant nodes.</p>
	 * 
	 * <p>Note: the key for comparison here will be the compareTo method defined
	 *  in interval class. You will use this for the interval stored in the node to
	 *  compare it with the input interval.</p>
	 * 
	 * <p>If the start and end of the given interval match an existing 
	 * interval, throw an IllegalArgumentException.</p>
	 *  
	 * @param interval the interval (item) to insert in the tree.
	 * @throws IllegalArgumentException if interval is null or is found 
	 * to be a duplicate of an existing interval in this tree.            
	 */
	public void insert(IntervalADT<T> interval) throws IllegalArgumentException;

	/**
	 * Delete the node containing the specified interval in the tree.
	 * Delete operations must also update the maxEnd of interval nodes
	 * that change as a result of deletion.  
	 *  
	 * <p>Tip: call <code>deleteHelper(root)</code> with the root node.</p>
	 * 
	 * @throws IllegalArgumentException if interval is null
	 * @throws IntervalNotFoundException if the interval does not exist.
	 */
	public void delete(IntervalADT<T> interval) throws IntervalNotFoundException, IllegalArgumentException;
	
	/** 
	 * Recursive helper method for the delete operation.  
	 * 
	 * <p>Note: the maxEnd of some interval nodes may also need to change
	 * as a result of an interval's deletion.</p>
	 * 
	 * <p>Note: the key for comparison here is the start of the interval
	 * stored at each IntervalNode.</p>
	 * 
	 * <p>Tip: write a non-recursive helper method that recalculates maxEnd for 
	 * any node based on the maxEnd of its child nodes</p>
	 * 
	 * <pre>      private T recalculateMaxEnd(IntervalNode&lt;T&gt; nodeToRecalculate)</pre>
	 * 
	 * <h3>Pseudo-code for this deleteHelper method:</h3>
	 *
 	 * <ul>
     * <li>If node is null, throw IntervalNotFoundException</li>
 	 * <li>If interval is found in this node, delete it and replace it 
 	 * with leftMost in right subtree.  There are two cases:
 	 * 
 	 * <ol><li>If right child exists
 	 *     <ol><li>Replace the node's interval with the in-order successor interval. 
 	 *     <br />Tip: Be sure to code the and use the <code>getSuccessor</code> method for <code>IntervalNode</code> class.</li>
 	 *         <li>Call deleteHelper() on the in-order successor node of the right subtree.</li>
	 *         <li>Update the new maxEnd.</li>
	 *         <li>Return the node.</li>
	 *     </ol>
	 *     </li>
	 *     
	 *     <li>If right child doesn't exist, return the left child</li>
	 * </ol>
     * 
	 * <li>If interval is in the right subtree,
	 *      <ol>
	 *	    <li>Set right child to result of calling deleteHelper on right child.</li>
	 *	    <li>Update the maxEnd if necessary. </li>
	 *      <li>Return the node.</li>
	 *      </ol>
	 *      </li>
	 *
	 * <li>If interval is in the left subtree.
	 *      <ol>
	 *	    <li>Set left child to result of calling deleteHelper on left child.</li>
	 *	    <li>Update the maxEnd if necessary. </li>
	 *      <li>Return the node.</li>
	 *      </ol>
	 *      </li>
	 *  </ul>
     *
	 * @param node the interval node that is currently being checked.
	 * 
	 * @param interval the interval to delete.
	 * 
	 * @throws IllegalArgumentException if the interval is null.
	 * 
	 * @throws IntervalNotFoundException
	 *             if the interval is not null, but is not found in the tree.
	 * 
	 * @return Root of the tree after deleting the specified interval.
	 */
	public IntervalNode<T> deleteHelper(IntervalNode<T> node, IntervalADT<T> interval) 
					throws IntervalNotFoundException, IllegalArgumentException;
	
	/**
	 * Find and return a list of all intervals that overlap with the given interval. 
	 * 
	 * <p>Tip: Define a helper method for the recursive call and call it with root, 
	 * the interval, and an empty list.  Then, return the list that has been built.</p>
	 * 
	 * <pre>   private void findOverlappingHelper(IntervalNode node, IntervalADT interval, List<IntervalADT<T>> result)</pre>
	 * 
	 * <p>Pseudo-code for such a recursive findingOverlappingHelper method.</p>
	 * 
	 * <ol>
	 * <li>if node is null, return</li>
	 * <li>if node interval overlaps with the given input interval, add it to the result.</li>
	 * <li>if left subtree's max is greater than or equal to the interval's start, call findOverlappingHelper in the left subtree.</li>
	 * <li>if right subtree's max is greater than or equal to the interval's start, call call findOverlappingHelper in the rightSubtree.</li>
	 * </ol>
	 *  
	 * @param interval the interval to search for overlapping
	 * 
	 * @return list of intervals that overlap with the input interval.
	 */
	public List<IntervalADT<T>> findOverlapping(IntervalADT<T> interval);

	/**
	 * Search and return a list of all intervals containing a given point. 
	 * This method may return an empty list. 
	 * 
	 * <p>For example: if the intervals stored in the tree are:</p>
	 * <pre>
	 * p1 [5, 10]
	 * p2 [2, 18]
	 * p3 [12, 30]</pre>
	 * 
	 * <p>and the input point is 16, it will return a list containing the intervals:</p>
	 * <pre>
	 * p2 [2, 18]
	 * p3 [12, 30]</pre>
	 * 
	 * @throws IllegalArgumentException if point is null
	 * 
	 * @param point
	 *            input point to search for.
	 * @return List of intervals containing the point.
	 */
	public List<IntervalADT<T>> searchPoint(T point);

	/**
	 * Get the size of the interval tree. The size is the total number of
	 * nodes present in the tree. 
	 * 
	 * <p>Tip: Define and call a recursive helper function to calculate this.</p>
	 * 
	 * @return int number of nodes in the tree.
	 */
	public int getSize();

	/**
	 * Return the height of the interval tree at the root of the tree. 
	 * 
	 * <p>Tip: Define and call a recursive helper function to calculate this for a given node.</p>
	 * 
	 * @return the height of the interval tree
	 */
	public int getHeight();

	/**
	 * Returns true if the tree contains an exact match for the start and end of the given interval.
	 * The label is not considered for this operation.
	 *  
	 * <p>Tip: Define and call a recursive helper function to call with root node 
	 * and the target interval.</p>
	 * 
	 * @param interval
	 * 				target interval for which to search the tree for. 
	 * @return boolean 
	 * 			   	representing if the tree contains the interval.
	 *
	 * @throws IllegalArgumentException
	 *             	if interval is null.
	 * 
	 */
	public boolean contains(IntervalADT<T> interval);
	
	/**
	 * Print the statistics of the tree in the below format
	 * <pre>
	 *	-----------------------------------------
	 *	Height: 2
	 *	Size: 3
	 *	-----------------------------------------
	 *	</pre> 
	 */
	public void printStats();
}
