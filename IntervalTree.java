import java.util.List;
import java.util.ArrayList;

public class IntervalTree<T extends Comparable<T>> implements IntervalTreeADT<T> {
	
	private IntervalNode<T> root;
	
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
		root = insertHelper(root, interval);
	}
	
	private IntervalNode<T> insertHelper(IntervalNode<T> node, 
					     IntervalADT<T> interval) throws IllegalArgumentException {
		if (node == null) {
			return new IntervalNode<T>(interval);
		}
		if (interval.compareTo(node.getInterval()) == 0 || interval == null) {
			throw new IllegalArgumentException();
		}
		if (interval.compareTo(node.getInterval()) < 0) {
			node.setLeftNode(insertHelper(node.getLeftNode(), interval));
			if (node.getLeftNode().getMaxEnd().compareTo(node.getMaxEnd()) > 0) {
				node.setMaxEnd(node.getLeftNode().getMaxEnd());
			}
			return node;
		}
		else {
			node.setRightNode(insertHelper(node.getRightNode(), interval));
			if (node.getRightNode().getMaxEnd().compareTo(node.getMaxEnd()) > 0) {
				node.setMaxEnd(node.getRightNode().getMaxEnd());
			}
			return node;
		}
	}

	@Override
	public void delete(IntervalADT<T> interval)
					throws IntervalNotFoundException, IllegalArgumentException {
		root = deleteHelper(root, interval);
	}

	@Override
	public IntervalNode<T> deleteHelper(IntervalNode<T> node,
					IntervalADT<T> interval)
					throws IntervalNotFoundException, IllegalArgumentException {
		if (interval == null) {
			throw new IllegalArgumentException();
		}
		if (node == null) {
			throw new IntervalNotFoundException(interval.toString());
		}
		if (node.getInterval().compareTo(interval) == 0) {
			if (node.getRightNode() != null) {
				IntervalADT<T> successorVal = node.getSuccessor().getInterval();
				node.setInterval(successorVal);
				deleteHelper(node.getRightNode(), successorVal);
				node.setMaxEnd(updateMaxEnd(node));
				return node;
			}
			else {
				return node.getLeftNode();
			}
		}
		if (node.getInterval().compareTo(interval) < 0) {
			node.setRightNode(deleteHelper(node.getRightNode(), interval));
			node.setMaxEnd(updateMaxEnd(node));
			return node;
		}
		else {
			node.setLeftNode(deleteHelper(node.getLeftNode(), interval));
			node.setMaxEnd(updateMaxEnd(node));
			return node;
		}
	}
		
	private T updateMaxEnd(IntervalNode<T> nodeRecalc) {
		T endNode = nodeRecalc.getInterval().getEnd();
		nodeRecalc.setMaxEnd(endNode);
		if (nodeRecalc.getLeftNode() == null && nodeRecalc.getRightNode() == null) {
			return nodeRecalc.getMaxEnd();
		}
		if (nodeRecalc.getLeftNode() == null) {
			if (nodeRecalc.getMaxEnd().
					compareTo(nodeRecalc.getRightNode().getMaxEnd()) < 0) {
					return nodeRecalc.getRightNode().getMaxEnd();
			}
			else {
				return nodeRecalc.getMaxEnd();
			}
		}
		if (nodeRecalc.getRightNode() == null) {
			if (nodeRecalc.getMaxEnd().
					compareTo(nodeRecalc.getLeftNode().getMaxEnd()) < 0) {
					return nodeRecalc.getLeftNode().getMaxEnd();
			}
			else {
				return nodeRecalc.getMaxEnd();
			}
		}
		T maxEndVal = nodeRecalc.getLeftNode().getMaxEnd();
		
		if (maxEndVal.compareTo
					    (nodeRecalc.getRightNode().getMaxEnd()) < 0) {
			maxEndVal = nodeRecalc.getRightNode().getMaxEnd();
		}
		if (maxEndVal.compareTo(nodeRecalc.getMaxEnd()) < 0) {
			maxEndVal = nodeRecalc.getMaxEnd();
		}
		return maxEndVal;
}

	@Override
	public List<IntervalADT<T>> findOverlapping(
					IntervalADT<T> interval) {
		if (interval == null) {
			throw new IllegalArgumentException();
		}
		List<IntervalADT<T>> list = new ArrayList<IntervalADT<T>>();
		findOverlappingHelper(root, interval, list);
		return list;
	}

	private void findOverlappingHelper(IntervalNode<T> node, IntervalADT<T> interval, 
					   List<IntervalADT<T>> list) {
		if (node == null) {
			return;
		}
		if (node.getInterval().overlaps(interval)) {
			list.add(node.getInterval());
		}
		if (node.getLeftNode().getMaxEnd().compareTo(interval.getStart()) >= 0
				&& node.getLeftNode() != null) {
			findOverlappingHelper(node.getLeftNode(), interval, list);
		}
		if (node.getRightNode().getMaxEnd().compareTo(interval.getStart()) >= 0
				&& node.getRightNode() != null) {
			findOverlappingHelper(node.getRightNode(), interval, list);
		}
	}

	@Override
	public List<IntervalADT<T>> searchPoint(T point) {
		if (point == null) {
			throw new IllegalArgumentException();
		}
		List<IntervalADT<T>> list = new ArrayList<IntervalADT<T>>();
		searchPointHelper(root, point, list);
		return list;
	}

	private void searchPointHelper(IntervalNode<T> node, 
				       T point, List<IntervalADT<T>> list) {
		if (node == null) {
			return;
		}
		else {
			searchPointHelper(node.getLeftNode(), point, list);
			if (node.getInterval().contains(point)) {
				list.add(node.getInterval());
			}
			if (node.getLeftNode().getMaxEnd().compareTo(point) > 0
					&& node.getLeftNode() != null) {
				searchPointHelper(node.getLeftNode(), point, list);
			}
			if (node.getRightNode().getMaxEnd().compareTo(point) > 0
					&& node.getRightNode() != null) {
				searchPointHelper(node.getRightNode(), point, list);
			}
		}
	}

	@Override
	public int getSize() {
		 return getSizeHelper(root);
	}
	
	private int getSizeHelper(IntervalNode<T> node) {
		if (node == null) {
			return 0;
		}
		else {
			return (getSizeHelper(node.getLeftNode()) 
				+ 1 + getSizeHelper(node.getRightNode()));
		}
	}

	@Override
	public int getHeight() {
		return getHeightHelper(root);
	}
	
	private int getHeightHelper(IntervalNode<T> node) {
		if (node == null) {
	        	return 0;
		}
		int leftNodeHeight = getHeightHelper(node.getLeftNode());
		int rightNodeHeight = getHeightHelper(node.getRightNode());
		if (leftNodeHeight > rightNodeHeight) {
			return 1 + leftNodeHeight;
		}
		else {
			return 1 + rightNodeHeight;
		}
	}

	@Override
	public boolean contains(IntervalADT<T> interval) {
		if (interval == null) {
			throw new IllegalArgumentException();
		}
		return containsHelper(interval, root);
	}

	private boolean containsHelper(IntervalADT<T> interval, IntervalNode<T> node) {
		if (root == null) {
			return false;
		}
		if (node.getInterval().compareTo(interval) == 0) {
			return true;
		}
		if (node.getInterval().compareTo(interval) > 0) {
			return containsHelper(interval, node.getLeftNode());
		}
		else {
			return containsHelper(interval, node.getLeftNode());
		}
	}

	@Override
	public void printStats() {
		System.out.println("-----------------------------------------");
		System.out.println("Height: " + getHeight());
		System.out.println("Size: " + getSize());
		System.out.println("-----------------------------------------");
	}

}
