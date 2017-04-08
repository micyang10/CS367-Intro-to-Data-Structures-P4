
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
        return start.compareTo(other.getEnd()) <= 0 
            && end.compareTo(other.getStart()) >= 0;
    }

    @Override
    public boolean contains(T point) {
        return start.compareTo(point) <= 0 
            && point.compareTo(end) <= 0;
    }

    @Override
    public int compareTo(IntervalADT<T> other) {
        // TODO Auto-generated method stub
    }

}
