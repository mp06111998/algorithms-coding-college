package aps2.suffixarray;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SuffixArrayIndex {
	private String text; // input string
	private int[] SA;    // suffix array

	public int[] getSuffixArray() { return SA; }
	
	SuffixArrayIndex(String text){
		this.text = text;
		this.SA = new int[text.length()];
		construct();
	}
	
	/**
	 * Constructs the suffix array corresponding to the text in expected
	 * O(n log n) suffix comparisons.
	 */
	private void construct() {
		String[] zacc = new String[text.length()];
		String zac = "";

		for(int i = zacc.length-1; i >= 0; i--){
			zac = text.charAt(i) + zac;
			zacc[i] = zac;
		}

        String[] prava = zacc;
        Arrays.sort(prava);

        int st=0;
        for(String beseda : prava){
            SA[st]=text.length()-beseda.length();
            st++;
        }
	}
	
	/**
	 * Returns True, if the suffix at pos1 is lexicographically before
	 * the suffix at pos2.
	 * 
	 * @param int pos1
	 * @param int pos2
	 * @return boolean
	 */
	public boolean suffixSuffixCompare(int pos1, int pos2) {
        String[] zacc = new String[text.length()];
        String zac = "";

        for(int i = zacc.length-1; i >= 0; i--){
            zac = text.charAt(i) + zac;
            zacc[i] = zac;
        }

        String[] prava = zacc;
        Arrays.sort(prava);

        return prava[pos1].compareTo(prava[pos2]) > 0;
	}
	
	/**
	 * Return True, if the query string is lexicographically lesser or
	 * equal to the suffix string at pos1.
	 * 
	 * @param String query The query string
	 * @param int pos2 Position of the suffix
	 * @return boolean
	 */
	public boolean stringSuffixCompare(String query, int pos2) {
        String[] zacc = new String[text.length()];
        String zac = "";

        for(int i = zacc.length-1; i >= 0; i--){
            zac = text.charAt(i) + zac;
            zacc[i] = zac;
        }

        String[] prava = zacc;
        Arrays.sort(prava);

        return query.compareTo(prava[pos2]) >= 0;
	}
	
	/**
	 * Returns the positions of the given substring in the text using binary
	 * search. The empty query is located at all positions in the text.
	 * 
	 * @param String query The query substring
	 * @return A set of positions where the query is located in the text
	 */
	public Set<Integer> locate(String query) {
        HashSet<Integer> tabela = new HashSet<>();
        for (int i = 0; (i = text.indexOf(query, i + 1)) != -1; i++) {
            tabela.add(i);
            if(text.charAt(i)==text.charAt(i+1)){tabela.add(i+1);}
        }
        return tabela;

	}
	
	/**
	 * Returns the longest substring in the text which repeats at least 2 times
	 * by examining the suffix array.
	 * 
	 * @return The longest repeated substring in the text
	 */
	public String longestRepeatedSubstring() {
		throw new UnsupportedOperationException("You need to implement this function!");
	}
	
	/**
	 * Calculates the length of the longest common prefix of two suffixes.
	 * 
	 * @param int pos1 Position of the first suffix in the text
	 * @param int pos2 Position of the second suffix in the text
	 * @return The number of characters in the common prefix of the first and the second suffix
	 */
	public int longestCommonPrefixLen(int pos1, int pos2) {
        String[] tabela = new String[]{this.text.substring(pos1),this.text.substring(pos2)};
        Arrays.sort(tabela);

        String zac = tabela[0];
        int i = 0;
        while (i < Math.min(zac.length(), tabela[tabela.length-1].length()) && zac.charAt(i) == tabela[tabela.length-1].charAt(i) ) {
            i++;
        }

        return zac.substring(0, i).length();
	}
}

