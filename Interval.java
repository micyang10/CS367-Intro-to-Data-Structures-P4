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

    T start;
    T end;
    String label;

    public Interval(T start, T end, String label) {
        this.start = start;
        this.end = end;
        this.label = label;
    }

    @Override
    public T getStart() {
        return start;
    }

    @Override
    public T getEnd() {
        return end;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public boolean overlaps(IntervalADT<T> other) {
        if (this.getStart().compareTo(other.getEnd()) <= 0 
           && this.getEnd().compareTo(other.getStart()) >= 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T point) {
        if (this.getEnd().compareTo(point) >= 0 && this.getStart().compareTo(point) <= 0) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(IntervalADT<T> other) {
        // first compares start values
    	if (this.getStart().compareTo(other.getStart()) < 0) {
    		return -1;
    	} 
		else if (this.getStart().compareTo(other.getStart()) > 0) {
			return 1;
		}
		
		// if start values equal, then compares end values
		else if (this.getEnd().compareTo(other.getEnd()) < 0) {
			return -1;
		} 
		else if (this.getEnd().compareTo(other.getEnd()) > 0) {
			return 1;
		}
		
		// if both start and end values equal
		else {
			return 0;
		}
    }
	
    @Override
    public String toString() {
        String s = this.label + " [" + this.start + ", " + this.end + "]";
        return s;
    }
}
