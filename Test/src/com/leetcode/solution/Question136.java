package com.leetcode.solution;

import java.util.HashSet;

//Single Number

/*Given an array of integers,2 3 2 3 9
every element appears twice except for one.
Find that single one.*/
public class Question136 {
	
	/*public int singleNumber(int[] nums) {
		if(nums.length == 0) return 0;
		HashSet<Integer> hashSet = new HashSet<>();
		for(int i = 0;i<nums.length;i++){
			if(hashSet.contains(nums[i])){
				hashSet.remove(nums[i]);
			}else{
				hashSet.add(nums[i]);
			}
		}
		return hashSet.iterator().next();
	}*/
	public int singleNumber(int[] nums){
		int result = 0;
		for(int i = 0 ;i<nums.length;i++){
			result = result ^ nums[i];
		}
		return result;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(new Question136().singleNumber(new int[]{2,2,3}));
		System.out.println(2^2);
	}

}
