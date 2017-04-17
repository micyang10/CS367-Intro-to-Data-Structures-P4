/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017 
// PROJECT:          Program 4
// FILE:             Interval.java
//
// TEAM:    Team 35 Java Badgers - P4
// Authors: Michael Yang, Kendra Raczek
// Author1: Michael Yang, yang363@wisc.edu, yang363, LEC 001
// Author2: Kendra Raczek, raczek@wisc.edu, raczek, LEC 001
//
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * An implementation of the IntervalADT interface. This implementation 
 * can represent targets. See IntervalADT.java for a description 
 * of each method. 
 * <p>Bugs: None that we are aware of
 *
 * @author Michael Yang, Kendra Raczek
 */
public class Interval<T extends Comparable<T>> implements IntervalADT<T> {

	// start date of an assignment
	T start;
	// end date of an assignment
	T end;
	// name of assignment
	String label;

	/**
	* Constructs new Interval instance, initializing start date, end date, and
	* label fields from parameters.
	* 
	* @param start first date the assignment is available
	* @param end last date the assignment is available
	* @param label the name of the assignment
	* @return newly constructed Interval object
	*/
	public Interval(T start, T end, String label) {
		this.start = start;
		this.end = end;
		this.label = label;
	}

	/**
	* Accesses start date of an Interval object
	* 
	* @return start date
	*/
	@Override
	public T getStart() {
		return start;
	}

	/**
	* Accesses end date of an Interval object
	* 
	* @return end date
	*/
	@Override
	public T getEnd() {
		return end;
	}
    
	/**
	* Accesses label of an Interval assignment
	* 
	* @return assignment label
	*/
	@Override
	public String getLabel() {
		return label;
	}

	/**
	* Compares two Intervals and checks whether or not the span of dates overlap
	* each other
	* 
	* @param other the other Interval object that is being compared
	* @return boolean value whether or not the two Intervals overlap
	*/
	@Override
	public boolean overlaps(IntervalADT<T> other) {
        	if (this.getStart().compareTo(other.getEnd()) <= 0 
           		&& this.getEnd().compareTo(other.getStart()) >= 0) {
            		return true;
        	}
        	return false;
	}

	/**
	 * Checks whether a certain date point lies within the Interval period
	 * 
	 * @param point the date to check 
	 * @return boolean value whether or not the point lies within the Interval
	 */
	@Override
	public boolean contains(T point) {
        	if (this.getEnd().compareTo(point) >= 0 && 
        		this.getStart().compareTo(point) <= 0) {
            		return true;
        	}
        	return false;
    	}

	/**
	 * Compares two Intervals and assesses which occurs first chronologically.
	 * Intervals are first compared by their start dates, and then their end 
	 * dates if start dates are equal.
	 * 
	 * @param other the second interval to which compare this interval with
	 * @return -1 if this interval's comes before the other interval, 
	 * 1 if this interval comes after the other interval,
	 * and 0 if the intervals are the same.
	 */
	@Override
	public int compareTo(IntervalADT<T> other) {
    		// first compares start values
		if (this.getStart().compareTo(other.getStart()) < 0) {
			return -1;
		} else if (this.getStart().compareTo(other.getStart()) > 0) {
			return 1;
		}
		
		// if start values equal, then compares end values
		else if (this.getEnd().compareTo(other.getEnd()) < 0) {
			return -1;
		} else if (this.getEnd().compareTo(other.getEnd()) > 0) {
			return 1;
		}
		
		// if both start and end values equal
		else {
			return 0;
		}	
	} // end of compareTo(IntervalADT<T> other) method
    
	/**
	 * Returns properly formatted String containing Interval's label, 
	 * start date, and end date.
	 * 
	 * @return String representation of an Interval
	 */
	@Override
	public String toString() {
		String s = this.label + " [" + this.start + ", " + this.end + "]";
		return s;
	} // end of toString() method
	
} // end of Interval class
