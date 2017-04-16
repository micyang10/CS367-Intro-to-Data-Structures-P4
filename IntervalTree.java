import java.util.List;

public class IntervalTree<T extends Comparable<T>> implements IntervalTreeADT<T> {
	
	private IntervalNode<T> root;
	private List<IntervalADT<T>> list;
	
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
		if (interval == null) {
			throw new IllegalArgumentException();
		}
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
		if (node.getLeftNode().compareTo(interval) == 0) {
			if (node.getLeftNode() == null && node.getRightNode() == null) {
				return null;
			}
			else if (node.getLeftNode() != null && node.getRightNode() == null) {
				return node.getLeftNode();
			}
			else if (node.getLeftNode() == null && node.getRightNode() != null) {
				return node.getRightNode();
			}
			else {
				node.setInterval(node.getSuccessor().getInterval());
				deleteHelper(node.getRightNode(), node.getInterval());
			}
		}
		else if (node.getInterval().compareTo(interval) > 0) {
			node.setLeftNode(deleteHelper(node.getLeftNode(), interval));
		}
		else if (node.getInterval().compareTo(interval) < 0) {
			node.setRightNode(deleteHelper(node.getRightNode(), interval));
		}
		if (node.getMaxEnd().compareTo(interval.getEnd()) == 0) {
			T end = node.getInterval().getEnd();
			if (node.getLeftNode() == null && node.getRightNode() == null) {
				node.setMaxEnd(end);
			}
			else if (node.getLeftNode() != null && node.getRightNode() == null) {
				if (node.getLeftNode().getMaxEnd().compareTo(end) > 0) {
					node.setMaxEnd(node.getLeftNode().getMaxEnd());
				}
				else {
					node.setMaxEnd(end);
				}
			}
			else if (node.getLeftNode() == null && node.getRightNode() != null) {
				if (node.getRightNode().getMaxEnd().compareTo(end) > 0) {
					node.setMaxEnd(node.getRightNode().getMaxEnd());
				}
				else {
					node.setMaxEnd(end);
				}
			}
			else {
				if (node.getLeftNode().getMaxEnd().compareTo(end) > 0) {
					if (node.getLeftNode().getMaxEnd().compareTo
					    (node.getRightNode().getMaxEnd()) > 0) {
						node.setMaxEnd(node.getLeftNode().getMaxEnd());
					}
					else {
						node.setMaxEnd(node.getRightNode().getMaxEnd());
					}
				}
				else {
					if (node.getRightNode().getMaxEnd().compareTo(end) > 0) {
						node.setMaxEnd(node.getRightNode().getMaxEnd());
					}
					else {
						node.setMaxEnd(end);
					}
				}
			}
		}
	}

	@Override
	public List<IntervalADT<T>> findOverlapping(
					IntervalADT<T> interval) {
		if (interval == null) {
			throw newe IllegalArgumentException();
		}
		list = new LinkedList<IntervalADT<T>>();
		findOverlappingHelper(root, interval, list);
		return list;
	}

	private void findOverlappingHelper(IntervalNode<T> node, IntervalADT<T> interval, 
					   List<IntervalADT<T>> result) {
		if (node == null) {
			return
		}
		if (node.getInterval().overlaps(interval)) {
			list.add(root.getInterval());
		}
		if (node.getLeftNode().getMaxEnd().compareTo(interval.getStart()) >= 0) {
			findOverlappingHelper(node.getLeftNode(), interval, result);
		}
		if (node.getRightNode().getMaxEnd().compareTo(interval.getStart()) >= 0) {
			findOverlappingHelper(node.getRightNode(), interval, result);
		}
	}

	@Override
	public List<IntervalADT<T>> searchPoint(T point) {
		if (point == null) {
			throw newe IllegalArgumentException();
		}
		list = new LinkedList<IntervalADT<T>>();
		searchPointHelper(root, interval, list);
		return list;
	}

	private void searchPointHelper(IntervalNode<T> node, T point, List<IntervalADT<T>> result) {
		if (node == null) {
			return;
		}
		else {
			searchPointHelper(node.getLeftNode(), point, list);
			if (point.compareTo(node.getInterval().getStart()) >= 0) {
				searchPointHelper(node.getRightNode(), point, list);
			}
			if (node.getInterval().contains(point)) {
				list.add(root.getInterval());
			}
		}
	}

	@Override
	public int getSize() {
		 return getSize(root);
	}
	
	private int getSizeHelper(IntervalNode<T> node) {
		if (node == null) {
			return 0;
		}
		else {
			return (getSizeHelper(node.getLeftNode()) + 1 + getSizeHelper(node.getRightNode()));
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
		return Math.max(node.getLeftNode()), getHeightHelper(node.getRightNode()) + 1;
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
