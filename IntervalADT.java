/**
 * THIS INTERFACE IS PROVIDED TO STUDENTS AND WILL NOT BE SUBMITTED<br />
 * -- DO NOT EDIT THIS INTERFACE
 * 
 * <p>This type describes the required methods of your required
 * <code>Interval</code> type. Note: that it implements <code>Comparator</code> 
 * so that two intervals can be compared with each other.</p>
 * 
 *  <p>You must define your class <code>Interval</code> so that it implements this
 * interface.</p>
 * 
 * <p>This interval can be used to represent various things. For example, in
 * scheduling problem, this will represent the start and end dates for an
 * assignment. This Interval will be stored as a data member inside our
 * IntervalTree.</p>
 *  
 * <p>Note: there are no <i>setter</i> methods for the data members of this type,
 * therefore the data must be passed in as arguments to the constructor and 
 * saved as data members accordingly. The <code>Interval</code> class that you 
 * define that implements this ADT, must include a constructor of the form:</p>
 * 
 * <pre>public Interval(T start, T end, String label)</pre>
 * 
 * @param <T>
 *            the template param for start/end fields.
 */
public interface IntervalADT<T extends Comparable<T>> extends Comparable<IntervalADT<T>> {

	/** Returns the start value (must be Comparable<T>) of the interval. */
	public T getStart();

	/** Returns the end value (must be Comparable<T>) of the interval. */
	public T getEnd();

	/** Returns the label for the interval. */
	public String getLabel();

	/**
	 * Return true if this interval overlaps with the other interval. 
	 * 
	 * <p>Note: two intervals [a, b], [c, d] will NOT overlap if either b &lt; c or d
	 * &lt; a. </p>
	 * 
	 * <p>In all other cases, they will overlap.</p>
	 * 
	 * @param other target interval to compare for overlap
	 * @return true if it overlaps, false otherwise.
	 * @throws IllegalArgumentException
	 *             if the other interval is null.
	 */
	public boolean overlaps(IntervalADT<T> other);

	/**
	 * Returns true if given point lies inside the interval.
	 * 
	 * @param point
	 *            to search
	 * @return true if it contains the point
	 */
	public boolean contains(T point);


	/**
	 * Compares this interval with the other and return a negative value 
	 * if this interval comes before the "other" interval.  Intervals 
	 * are compared first on their start time.  The end time is only considered
	 * if the start time is the same.
	 * 
	 * <p>For example, if start times are different:</p>
	 * 
	 * <pre>
	 * [0,1] compared to [2,3]: returns -1 because 0 is before 2
	 * [2,3] compared to [0,1]: return 1 because 2 is after 0
	 * [0,4] compared to [2,3]: return -1 because 0 is before 2
	 * [2,3] compared to [0,4]: return 1 because 2 is after 0
	 * [0,3] compared to [2,4]: return -1 because 0 is before 2
	 * [2,4] compared to [0,3]: return 1 because 2 is after 0
	 * </pre>
	 * 
	 * <p>If start times are the same, compare based on end time:</p>
	 * <pre>
	 * [0,3] compared to [0,4]: return -1 because start is same and 3 is before 4
	 * [0,4] compared to [0,3]: return 1 because start is same and 4 is after 3</pre>
	 * 
	 * <p>If start times and end times are same, return 0</p>
	 * <pre>[0,4] compared to [0,4]: return 0</pre>
	 *
	 * @param other
	 *            the second interval to which compare this interval with
	 *            
	 * @return negative if this interval's comes before the other interval, 
	 * positive if this interval comes after the other interval,
	 * and 0 if the intervals are the same.  See above for details.
	 */
	public int compareTo(IntervalADT<T> other);

	/**
	 * Returns a specific string representation of the interval. It must return
	 * the interval in this form.
	 * 
	 *  <p>For example: If the interval's label is p1 and the start is 24 and the end is 45,
	 *  then the string returned is:</p>
	 *  
	 *  <pre>p1 [24, 45]</pre>
	 */
	@Override
	public String toString();
}
