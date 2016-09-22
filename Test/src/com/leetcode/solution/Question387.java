package com.leetcode.solution;

//First Unique Character in a String
/*Given a string, find the first non-repeating character in it and return it's index.
If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.*/
public class Question387 {

	public int firstUniqChar(String s) {
        if(null == s||s.isEmpty()) return -1;
        for(int i = 0;i<s.length()-1;i++){
        	String temp = String.valueOf(s.charAt(i));
        	if(!s.substring(0, i).contains(temp)&&!s.substring(i+1).contains(temp))
        		return i;
        	/*if(!s.substring(i+1).contains(String.valueOf(s.charAt(i)))){
        		return i;
        	}*/
        }
        return s.substring(0,s.length()-1).contains(String.valueOf(s.charAt(s.length() - 1))) ? -1: s.length() - 1;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new Question387().firstUniqChar("aaaa"));
//		System.out.println("leetcode".substring(0,1));
		int[] a = new int[26];
		a[1] = a[1]+2;
		System.out.println(a[1]);
	}

}
