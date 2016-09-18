package com.leetcode.solution;

import java.util.HashSet;

//Longest Substring Without Repeating Characters 2016-09-14
/*Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.*/
public class Question3 {
	public int lengthOfLongestSubstring(String s){
		if(null == s || s.equals("")){
			return 0;
		}
		int maxLen = 1;
		int currentLen = 1;
		int repeat = 0;
		for(int i = 1;i<s.length();i++){
			repeat = s.substring(i-currentLen,i).indexOf(s.substring(i,i+1));
			if(repeat == -1){
				currentLen++;
			}else{
				currentLen = currentLen - repeat;
			}
			if(currentLen > maxLen)
				maxLen = currentLen;
		}
		return maxLen;
	}
	
	
	
	
	
	
	
	
	/*public int lengthOfLongestSubstring(String s) {//error
	       if(s == null || s.equals("")){
	        	return 0;
	        }
	        int result = 1;
	        int start = 0;
	        int end = s.length() - 1;
	        int max = 0;
	        for(int i = 1;i<s.length();i++){
	            if(s.substring(start,i).contains(s.substring(i,i+1))){//ÐÞ¸ÄÎªindexOf == -1
	                max = i - start;
	                start = i;
	            }
	            if(max > result){
	                result = max;
	            }
	        }
	        System.out.println(start);
	        if(start < end){
	        	result = (end - start + 1) < result ? result : (end - start + 1);
	        }
	        if(start == 0){
	        	result = end - start;
	        }
	        return result;
	    }*/
	/*public int lengthOfLongestSubstring(String s) {
		int maxLen = 1,currentLen = 1, len = s.length(),repeat = 0;
		for(int i = 1;i < len ;i++){
			repeat = s.substring(i-currentLen,currentLen).indexOf(s.substring(i,i+1));
			if(repeat == -1){
				currentLen++ ;
			}
			else{
				currentLen -= repeat;
			}
			if(maxLen < currentLen){
				maxLen = currentLen;
			}
		}
		return maxLen;
	}*/
	public static void main(String[] args){
		String s = "abcabc";//"bbbb";aabc;abcabcbb;dvdf
		System.out.println(new Question3().lengthOfLongestSubstring(s));
	}
}
