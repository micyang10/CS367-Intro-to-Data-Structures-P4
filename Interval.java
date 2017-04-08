
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
        // TODO Auto-generated method stub
    }

}
