package aps2.binomialheap;

import java.util.Vector;

/**
 * This class is an implementation of the Binomial min-heap.
 */
public class BinomialHeap {
	Vector<BinomialNode> data; // list of root nodes
	int n;                     // number of elements
	
	BinomialHeap(){
		data = new Vector<BinomialNode>();
	}
	
	/**
	 * Inserts a new key to the binomial heap and consolidates the heap.
	 * Duplicates are allowed.
	 * 
	 * @param key Key to be inserted
	 * @return True, if the insertion was successful; False otherwise.
	 */
	public boolean insert(int key) {
		BinomialNode voz = new BinomialNode(key);
		if(key!=0){
			n++;
			data.add(voz);
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the minimum element in the binomial heap. If the heap is empty,
	 * return the maximum integer value.
	 * 
	 * @return The minimum element in the heap or the maximum integer value, if the heap is empty.
	 */
	public int getMin() {
		if(data.isEmpty()){return Integer.MAX_VALUE;}
		int min = data.get(0).getKey();
		for (int i = 1; i<n; i++){
			if(data.get(i).getKey()<min){
				min = data.get(i).getKey();
			}
		}
		return min;
	}
	
	/**
	 * Find and removes the minimum element in the binomial heap and
	 * consolidates the heap.
	 * 
	 * @return True, if the element was deleted; False otherwise.
	 */
	public boolean delMin() {
		int min = getMin();
		if(min==Integer.MAX_VALUE || data.isEmpty()){return false;}
		BinomialNode node = data.get(0);
		for (int i = 1;i<n;i++){
			if(data.get(i).getKey()==min){
				node = data.get(i);
				break;
			}
		}
		data.remove(node);
		n--;
		return true;
	}
	
	/**
	 * Merges two binomial trees.
	 * 
	 * @param t1 The first tree
	 * @param t2 The second tree
	 * @return Returns the new parent tree
	 */
	public static BinomialNode mergeTrees(BinomialNode t1, BinomialNode t2) {
		BinomialNode pravi;
		if(t1.getDegree() == t2.getDegree()){
			if(t1.getKey()<t2.getKey()){
				pravi = t1;
				pravi.addChild(t2);
			}
			else if(t1.getKey()>t2.getKey()){
				pravi = t2;
				pravi.addChild(t1);
			}
			else{
				pravi = new BinomialNode(0);
			}
		}
		else{pravi = new BinomialNode(0);}
		return pravi;
	}
	
	/**
	 * This function consolidates the binomial heap ie. merges the binomial
	 * trees with the same degree into a single one.
	 * 
	 * @return True, if changes were made to the list of root nodes; False otherwise.
	 */
	private boolean consolidate() {
		throw new UnsupportedOperationException("You need to implement this function!");
	}
}

