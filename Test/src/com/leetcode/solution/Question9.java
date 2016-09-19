package com.leetcode.solution;

//Palindrome Number
public class Question9 {

	public boolean isPalindrome(int x) {
		int root = x;
		int palindromeNum = 0;
		while(x>0){
			palindromeNum = palindromeNum * 10 + x % 10;
			x = x / 10;
		}
		return palindromeNum == root;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new Question9().isPalindrome(2147483647));
	}

}
