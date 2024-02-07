import java.util.Comparator;
import java.util.Iterator;

public class NLNode<T> {

	private NLNode<T> parent; //parent node of a child
	private ListNodes<NLNode<T>> children; //list of children nodes of a parent node
	private T data; //data stored inside a tree node

	/**
	 * Constructor method for a NLNode with generic type T.
	 * Sets parent node to null, data stored in the node to null,
	 * and initializes a new ListNodes for the list of children
	 */
	public NLNode() {
		this.parent = null;
		this.data = null;
		this.children = new ListNodes<NLNode<T>>();
	}

	/**
	 * 
	 * Constructor method for a NLNode with generic type T.
	 * Sets parent node to p, data stored in the node to d,
	 * and initializes a new ListNodes for the list of children
	 * 
	 * @param d (data)
	 * @param p (parent)
	 */
	public NLNode(T d, NLNode<T> p) {
		this.children = new ListNodes<NLNode<T>>();
		this.data = d;
		this.parent = p;

	}

	/**
	 * Set the parent of a given NLNode
	 * 
	 * @param parent
	 */
	public void setParent(NLNode<T> parent) {
		this.parent = parent;
	}

	
	/**
	 * Get the parent of a given NLNode
	 * 
	 * @return parent
	 */
	public NLNode<T> getParent() {
		return parent;
	}

	
	/**
	 * Set the parent of the new child node to this NLNode
	 * Add the new child node to the list of children for this parent
	 * 
	 * @param newChild
	 */
	public void addChild(NLNode<T> newChild) {
		newChild.setParent(this);
		children.add(newChild);
	}

	/**
	 * Get the list of children of the given NLNode
	 * 
	 * @return list of children
	 */
	public Iterator<NLNode<T>> getChildren() {
		return children.getList();
	}

	/**
	 * Get the list of children of the given NLNode, but with a sorter
	 * 
	 * @param sorter
	 * @return sorted list of children
	 */
	public Iterator<NLNode<T>> getChildren(Comparator<NLNode<T>> sorter) {
		return children.sortedList(sorter);
	}

	/**
	 * Get the data stored in a NLNode
	 * 
	 * @return data
	 */
	public T getData() {
		return data;
	}

	/**
	 * Set the data stored in a NLNode
	 * 
	 * @param d (data)
	 */
	public void setData(T d) {
		this.data = d;
	}

}
