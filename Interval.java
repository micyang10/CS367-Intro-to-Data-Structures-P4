
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
        if (start.compareTo(other.getEnd()) <= 0 
           && end.compareTo(other.getStart()) >= 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T point) {
        if (start.compareTo(point) <= 0 && point.compareTo(end) <= 0) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(IntervalADT<T> other) {
        // first checks if start values are equal
		if (start.compareTo(other.getStart()) < 0) {
			return -1;
		} else if (start.compareTo(other.getStart()) > 0) {
			return 1;
		}
		
		// if start values equal, then checks if end values are equal
		else if (end.compareTo(other.getEnd()) < 0) {
			return -1;
		} else if (end.compareTo(other.getEnd()) > 0) {
			return 1;
		}
		
		// if both start and end values equal
		else {
			return 0;
		}
    }
}
