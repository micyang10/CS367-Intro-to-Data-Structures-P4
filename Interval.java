
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
