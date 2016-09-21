package com.leetcode.solution;

//Excel Sheet Column Number

//Given a column title as appear in an Excel sheet, return its corresponding column number.
/*
A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28 
*/
public class Question171 {
 
	public int titleToNumber(String s) {
		if(s == null || s.isEmpty()) return 0;
		int result = 0;
		int length = s.length();
		for(int i = 0;i<s.length();i++){
			if(length == 1){
				result += s.charAt(i) -64;
			}else{
				result += (s.charAt(i)-64) * length*26; 
			}
			length--;
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(new Question171().titleToNumber("AB"));
//		System.out.println('A'-64);
	}

}
