package aps2.bstmap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of the (unbalanced) Binary Search Tree set node.
 */
public class BSTMapNode {
	private static int counter;
	private BSTMapNode left, right, parent;
	private int key;
	private String value;

	public BSTMapNode(BSTMapNode l, BSTMapNode r, BSTMapNode p,
			int key, String value) {
		super();
		this.left = l;
		this.right = r;
		this.parent = p;
		this.key = key;
		this.value = value;
	}

	public BSTMapNode getLeft() {
		return left;
	}

	public void setLeft(BSTMapNode left) {
		this.left = left;
	}

	public BSTMapNode getRight() {
		return right;
	}

	public void setRight(BSTMapNode right) {
		this.right = right;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public int compare(BSTMapNode node) {
		counter++;
		return node.key-this.key;
	}

	public int getCounter() {
		return counter;
	}

	public void resetCounter() {
		counter = 0;
	}

	/**
	 * If the element doesn't exist yet, adds the given element to the subtree.
	 * 
	 * @param element Given key/value wrapped inside an empty BSTNode instance
	 * @return true, if the element was added; false otherwise.
	 */
	public boolean add(BSTMapNode element) {
		if(element.key < this.key){
			if(this.left == null){
				this.left = element;
				return true;
			}
			else{
				this.left.add(element);
			}
		}
		else{
			if(this.right == null){
				this.right = element;
				return true;
			}
			else{
				this.right.add(element);
			}
		}
		return false;
	}
	
	/**
	 * Finds and removes the element with the given key from the subtree.
	 * 
	 * @param element Given key wrapped inside an empty BSTNode instance
	 * @return true, if the element was found and removed; false otherwise.
	 */
	public boolean remove(BSTMapNode element) {
		if(this.key == element.key){
			if(this.left == null && this.right == null){
				if(this.parent.left.key == element.key) {
					this.parent.left = null;
				}
				else{
					this.parent.right = null;
				}
			}
			else{
				//sicer v desnem (levem) poddrevesu poiˇsˇcemo najveˇcji (najmanjˇsi)
				//element in ga vstavimo na mesto izbrisanega drevesa;
				BSTMapNode trenutni = this;
				BSTMapNode zdej = this;
				if(this.right != null){
					zdej = this.right.findMin();
					zdej.right = trenutni.right;
					zdej.left = trenutni.left;
					zdej.parent = trenutni.parent;
					if(zdej.parent.right.key == element.key){
						zdej.parent.right = zdej;
					}
					else if(zdej.parent.left.key == element.key){
						zdej.parent.left = zdej;
					}
					zdej.left.parent = zdej;
					zdej.right.parent = zdej;

					if(zdej.right.findMin().parent.left != null){
						zdej.right.findMin().parent.left = null;
					}
					else{
						zdej.right.findMin().parent.right = null;
					}
				}
				else{
					zdej = this.left.findMax();
					zdej.right = trenutni.right;
					zdej.left = trenutni.left;
					zdej.parent = trenutni.parent;
					if(zdej.parent.right.key == element.key){
						zdej.parent.right = zdej;
					}
					else if(zdej.parent.left.key == element.key){
						zdej.parent.left = zdej;
					}
					zdej.left.parent = zdej;
					zdej.right.parent = zdej;

					if(zdej.left.findMax().parent.left != null){
						zdej.left.findMax().parent.left = null;
					}
					else{
						zdej.left.findMax().parent.right = null;
					}
				}
			}
		}
		else if(element.key < this.key){
			if(this.left == null){
				return false;
			}
			else{
				this.left.remove(element);
			}
		}
		else{
			if(this.right == null){
				return false;
			}
			else{
				this.right.remove(element);
			}
		}
		return false;
	}

	public BSTMapNode findMax() {
		BSTMapNode zacasni = this;
		while(zacasni.right != null) {
			zacasni = zacasni.right;
		}
		return zacasni;
	}

	/**
	 * Checks whether the element with the given key exists in the subtree.
	 * 
	 * @param element Query key wrapped inside an empty BSTNode instance
	 * @return true, if an element with the given key is contained in the subtree; false otherwise.
	 */
	public boolean contains(BSTMapNode element) {
		counter++;
		if(this.key == element.key){
			return true;
		}
		else if(element.key < this.key){
			if(this.left == null){
				return false;
			}
			else{
				return this.left.contains(element);
			}
		}
		else{
			if(this.right == null){
				return false;
			}
			else{
				return this.right.contains(element);
			}
		}
	}
	
	/**
	 * Maps the given key to its value.
	 * 
	 * @param element Query key wrapped inside an empty BSTNode instance
	 * @return String value of the given key; null, if an element with the given key does not exist in the subtree.
	 */
	public String get(BSTMapNode element) {
		if(this.key == element.key){
			return this.value;
		}
		else if(element.key < this.key){
			if(this.left == null){
				return null;
			}
			else{
				return this.left.get(element);
			}
		}
		else{
			if(this.right == null){
				return null;
			}
			else{
				return this.right.get(element);
			}
		}
	}

	/**
	 * Finds the smallest element in the subtree.
	 * 
	 * @return Smallest element in the subtree
	 */
	public BSTMapNode findMin() {
		BSTMapNode zacasni = this;
		while(zacasni.left != null) {
			zacasni = zacasni.left;
		}
		return zacasni;
	}
	
	/**
	 * Depth-first pre-order traversal of the BST.
	 * 
	 * @return List of keys stored in BST obtained by pre-order traversing the tree.
	 */
	List<Integer> traversePreOrder() {
		List<Integer> list = new ArrayList<>();
		list.add(this.key);
		if(this.left != null){
			list.addAll(this.left.traversePreOrder());
		}
		if(this.right != null){
			list.addAll(this.right.traversePreOrder());
		}
		return list;
	}

	/**
	 * Depth-first in-order traversal of the BST.
	 * 
	 * @return List of keys stored in BST obtained by in-order traversing the tree.
	 */
	List<Integer> traverseInOrder() {
		List<Integer> list = new ArrayList<>();
		if(this.left != null){
			list.addAll(this.left.traverseInOrder());
		}
		list.add(this.key);
		if(this.right != null){
			list.addAll(this.right.traverseInOrder());
		}
		return list;
	}

	/**
	 * Depth-first post-order traversal of the BST.
	 * 
	 * @return List of keys stored in BST obtained by post-order traversing the tree.
	 */
	List<Integer> traversePostOrder() {
		List<Integer> list = new ArrayList<>();
		if(this.left != null){
			list.addAll(this.left.traversePostOrder());
		}
		if(this.right != null){
			list.addAll(this.right.traversePostOrder());
		}
		list.add(this.key);
		return list;
	}

	/**
	 * Breadth-first (or level-order) traversal of the BST.
	 * 
	 * @return List of keys stored in BST obtained by breadth-first traversal of the tree.
	 */
	List<Integer> traverseLevelOrder() {
		throw new UnsupportedOperationException("You need to implement this function!");
	}
}
