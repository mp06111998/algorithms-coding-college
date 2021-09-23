package aps2.hashmap;

/**
 * Hash map with open addressing.
 */
public class HashMapOpenAddressing {
	private Element table[]; // table content, if element is not present, use Integer.MIN_VALUE for Element's key
	private HashFunction.HashingMethod h;
	private CollisionProbeSequence c;
	
	public static enum CollisionProbeSequence {
		LinearProbing,    // new h(k) = (h(k) + i) mod m
		QuadraticProbing, // new h(k) = (h(k) + i^2) mod m
		DoubleHashing     // new h(k) = (h(k) + i*h(k)) mod m
	};
	
	public HashMapOpenAddressing(int m, HashFunction.HashingMethod h, CollisionProbeSequence c) {
		this.table = new Element[m];
		this.h = h;
		this.c = c;
		
		// init empty slot as MIN_VALUE
		for (int i=0; i<m; i++) {
			table[i] = new Element(Integer.MIN_VALUE, "");
		}
	}

	public Element[] getTable() {
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

		if(contains(k) == true){
			return false;
		}
		else{
			Element e = new Element(k,v);
			table[el] = e;
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
		throw new UnsupportedOperationException("You need to implement this function!");
	}

	/**public int getT(int k){
		if(h.equals(HashFunction.HashingMethod.KnuthMethod)){
			return HashFunction.KnuthMethod(k, table.length);
		}
		else{
			return HashFunction.DivisionMethod(k, table.length);
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

		for(int i = 0; i<table.length;i++){
			if(table[el].key == k){
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

		for(int i = 0; i<table.length;i++){
			if(table[el].key == k){
				return table[el].value;
			}
		}
		return null;
	}
}

