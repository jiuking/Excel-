package com.leetcode.solution;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

//Ransom Note

/*canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true*/

public class Question383 {
	public boolean canConstruct(String ransomNote, String magazine) {
		HashMap<Character,Integer> ranHash = new HashMap<>();
		HashMap<Character,Integer> magaHash = new HashMap<>();
		for(int i = 0;i<ransomNote.length();i++){
			if(ranHash.get(ransomNote.charAt(i)) != null){
				ranHash.put(ransomNote.charAt(i),ranHash.get(ransomNote.charAt(i)) + 1);
			}else{
				ranHash.put(ransomNote.charAt(i),1);
			}
		}
		for(int i = 0;i<magazine.length();i++){
			if(magaHash.get(magazine.charAt(i)) != null){
				magaHash.put(magazine.charAt(i),magaHash.get(magazine.charAt(i)) + 1);
			}else{
				magaHash.put(magazine.charAt(i),1);
			}
		}
		Iterator<Entry<Character, Integer>> it = ranHash.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String,Integer> entry = (Entry) it.next();
			if(magaHash.get(entry.getKey()) == null || magaHash.get(entry.getKey()) < entry.getValue()){
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(new Question383().canConstruct("fffbfg","effjfggbffjdgbjjhhdegh"));
		System.out.println(new Question383().canConstruct("cc","ab"));
		System.out.println('a'^'a');
	}

}
