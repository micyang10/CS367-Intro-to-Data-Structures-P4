import java.util.List;

public class IntervalTree<T extends Comparable<T>> implements IntervalTreeADT<T> {
	
	IntervalNode<T> root;
	
	public IntervalTree() {
		this.root = null;
	}
	
	public IntervalTree(IntervalNode<T> root) {
		this.root = root;
	}
	

	@Override
	public IntervalNode<T> getRoot() {
		return this.root;
	}

	@Override
	public void insert(IntervalADT<T> interval)
					throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(IntervalADT<T> interval)
					throws IntervalNotFoundException, IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public IntervalNode<T> deleteHelper(IntervalNode<T> node,
					IntervalADT<T> interval)
					throws IntervalNotFoundException, IllegalArgumentException {
		// TODO Auto-generated method stub
	}

	@Override
	public List<IntervalADT<T>> findOverlapping(
					IntervalADT<T> interval) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<IntervalADT<T>> searchPoint(T point) {
		// TODO Auto-generated method stub
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
	}

	@Override
	public int getHeight() {
	    if(this.root == null){
	        return 0;
	    }
	    else{
	        return getHeight(this.root);
	    }
	}
	private int getHeight(IntervalNode<T> node) {
	    if (node == null) {
	        return -1;
	    }

	    int left = getHeight(node.getLeftNode());
	    int right = getHeight(node.getRightNode());

	    if (left > right) {
	        return left + 1;
	    } else {
	        return right + 1;
	    }
	}

	@Override
	public boolean contains(IntervalADT<T> interval) {
		// TODO Auto-generated method stub
	}

	@Override
	public void printStats() {
		// TODO Auto-generated method stub

	}

}
