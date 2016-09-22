package com.leetcode.solution;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

//Majority Element
/*Given an array of size n, find the majority element. 
The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty
and the majority element always exist in the array.*/
public class Question169 {

	public int majorityElement(int[] nums) {
		/*HashMap<Integer,Integer> hashMap = new HashMap<Integer,Integer>();
		for(int i = 0;i<nums.length;i++){
			if(hashMap.get(nums[i]) != null){
				hashMap.put(nums[i], hashMap.get(nums[i])+1);
			}else{
				hashMap.put(nums[i],1);
			}
		}
		Iterator<Entry<Integer,Integer>> iter = hashMap.entrySet().iterator();
		while(iter.hasNext()){
			Entry<Integer,Integer> entry = iter.next();
			int value = entry.getValue();
			if(value >= nums.length/2.0){
				return entry.getKey();
			}
		}
		return -1;*/
		int candidate = 0;
		int count = 0;
		for(int i = 0;i<nums.length;i++){
			if(count == 0){
				candidate = nums[i];
				count++;
			}else{
				if(nums[i] == candidate){
					count++;
				}else{
					count--;
				}
			}
		}
		return candidate;
	}
	 
	public static void main(String[] args) {
		System.out.println(new Question169().majorityElement(new int[]{2,3,3,3,2}));
	}

}
