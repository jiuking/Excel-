package com.leetcode.solution;

import java.util.HashMap;

//Two Sum 2019-09-13
/*Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].*/
public class Question1 {
	//时间复杂度可以优化。
	/*public int[] twoSum(int[] nums, int target) {
		int[] result = new int[2];
		for(int i = 0;i<nums.length;i++){
			for(int j = i;j<nums.length;j++){
				if(nums[i] + nums[j] == target){
					result[0] = i;
					result[1] = j;
				}
			}
		}
		return result;
	}*/
	
	public int[] twoSum(int[] nums,int target){
		int[] result = new int[2];
		HashMap<Integer,Integer> hashMap = new HashMap<>();
		for(int i = 0;i<nums.length;i++){
			if(hashMap.get(nums[i]) == null){
				hashMap.put(nums[i], i);
			}
			Integer temp = target - nums[i];
			if(hashMap.get(temp) != null){
				result[0] = hashMap.get(temp);
				result[1] = hashMap.get(nums[i]);
			}
		}
		System.out.println(result[0]);
		System.out.println(result[1]);
		return result;
	}
	
	public static void main(String[] args){
		int[] nums = {2, 7, 11, 15};
		new Question1().twoSum(nums, 9);
	}
}
