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
		if (interval == null) {
			throw new IllegalArgumentException();
		}
		root = insertHelper(root, interval);
	}
	
	private IntervalNode<T> insertHelper(IntervalNode<T> node, 
					     IntervalADT<T> interval) {
		if (node == null) {
			return new IntervalNode<T> interval;
		}
		if (interval.compareTo(node.getInterval()) == 0)
			throw new IllegalArgumentException();
		}
		if (interval.compareTo(node.getInterval()) < 0) {
			IntervalNode<T> nodeNew = 
				insertHelper(node.getRightNode(), interval);
			node.setRightNode(nodeNew);
			if (nodeNew.getMaxEnd().compareTo.(root.getMaxEnd()) > 0) {
				root.setMaxEnd(nodeNew.getMaxEnd());
			}
		}
		else if (interval.compareTo(node.getInterval()) > 0) {
			IntervalNode<T> nodeNew = 
				insertHelper(node.getLeftNode(), interval);
			node.setLeftNode(nodeNew);
			if (nodeNew.getMaxEnd().compareTo.(root.getMaxEnd()) > 0) {
				root.setMaxEnd(nodeNew.getMaxEnd());
			}
		}
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
		 return getSize(root);
	}
	
	private int getSize(IntervalNode<T> node) {
		if (node == null) {
			return 0;
		}
		else {
			return (getSize(node.getLeftNode()) + 1 + getSize(node.getRightNode()));
		}
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
		System.out.println("-----------------------------------------");
		System.out.println("Height: " + getHeight());
		System.out.println("Size: " + getSize());
		System.out.println("-----------------------------------------");
	}

}
