package aps2.hashmap;

import java.util.LinkedList;

/**
 * Hash map employing chaining on collisions.
 */
public class HashMapChaining {
	private LinkedList<Element> table[];
	private HashFunction.HashingMethod h;
	
	public HashMapChaining(int m, HashFunction.HashingMethod h) {
		this.h = h;
		this.table = new LinkedList[m];
		for (int i=0; i<table.length; i++) {
			table[i] = new LinkedList<Element>();
		}
	}
	
	public LinkedList<Element>[] getTable() {
		return this.table;
	}
	
	/**
	 * If the element doesn't exist yet, inserts it into the set.
	 * 
	 * @param k Element key
	 * @param v Element value
	 * @return true, if element was added; false otherwise.
	 */
	public boolean add(int k, String v) {
		int el;
		if(h.equals(HashFunction.HashingMethod.DivisionMethod)){
			el = HashFunction.DivisionMethod(k, table.length);
		}
		else{
			el = HashFunction.KnuthMethod(k, table.length);
		}

		if(contains(k) == true){return false;}
		else{
			Element e = new Element(k,v);
			table[el].add(e);
			return true;
		}
	}

	/**
	 * Removes the element from the set.
	 * 
	 * @param k Element key
	 * @return true, if the element was removed; otherwise false
	 */
	public boolean remove(int k) {
		int el;
		if(h.equals(HashFunction.HashingMethod.DivisionMethod)){
			el = HashFunction.DivisionMethod(k, table.length);
		}
		else{
			el = HashFunction.KnuthMethod(k, table.length);
		}

		for(int i = 0; i<table[el].size();i++){
			if(table[el].get(i).key == k){
				table[el].remove(i);
				return true;
			}
		}
		return false;
	}

	/**public int getT(int k){
		if(h.equals(HashFunction.HashingMethod.KnuthMethod)){
			return HashFunction.KnuthMethod(k, getTable().length);
		}
		else{
			return HashFunction.DivisionMethod(k, getTable().length);
		}
	}**/

	/**
	 * Finds the element.
	 * 
	 * @param k Element key
	 * @return true, if the element was found; false otherwise.
	 */
	public boolean contains(int k) {
		int el;
		if(h.equals(HashFunction.HashingMethod.DivisionMethod)){
			el = HashFunction.DivisionMethod(k, table.length);
		}
		else{
			el = HashFunction.KnuthMethod(k, table.length);
		}

		for(int i = 0; i<table[el].size();i++){
			if(table[el].get(i).key == k){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Maps the given key to its value, if the key exists in the hash map.
	 * 
	 * @param k Element key
	 * @return The value for the given key or null, if such a key does not exist.
	 */
	public String get(int k) {
		int el;
		if(h.equals(HashFunction.HashingMethod.DivisionMethod)){
			el = HashFunction.DivisionMethod(k, table.length);
		}
		else{
			el = HashFunction.KnuthMethod(k, table.length);
		}

		for(int i = 0; i<table[el].size();i++){
			if(table[el].get(i).key == k){
				return table[el].get(i).value;
			}
		}
		return null;
	}
}

