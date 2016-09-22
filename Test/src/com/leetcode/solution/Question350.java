package com.leetcode.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Intersection of Two Arrays II
/*Given two arrays, write a function to compute their intersection.
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].*/
public class Question350 {

	public int[] intersect(int[] nums1, int[] nums2) {
		/*int[] result; error
		int[] origin;
		List<Integer> returnRe = new ArrayList<>();
		if(nums1.length > nums2.length){
			result = nums2;
			origin = nums1;
		}else{
			result = nums1;
			origin = nums2;
		}
		int temp  = -1;
		for(int i = 0 ;i<result.length;i++){
			for(int j = 0;j<origin.length;j++){
				if(origin[j] == result[i] && temp != j){
					returnRe.add(result[i]);
					temp = j;
					break;
				}
			}
		}
		int[] re = new int[returnRe.size()];
        for(int i = 0;i<returnRe.size();i++){
        	re[i] = returnRe.get(i);
        }
        return re;*/
		HashMap<Integer,Integer> map = new HashMap<>();
		for(int i = 0;i<nums1.length;i++){
			if(map.containsKey(nums1[i]))
				map.put(nums1[i], map.get(nums1[i])+1);
			else
				map.put(nums1[i],1);
		}
		List<Integer> list = new ArrayList<>();
		for(int i = 0 ;i<nums2.length;i++){
			if(map.containsKey(nums2[i])){
				if(map.get(nums2[i])>0){
					list.add(nums2[i]);
					map.put(nums2[i],map.get(nums2[i])-1);
				}
			}
		}
		int[] result = new int[list.size()];
		for(int i = 0;i<list.size();i++){
			result[i] = list.get(i);
		}
		return result;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		System.out.println(new Question350().intersect(new int[]{1,2,3,4,5}, new int[]{1,3,1}).length);		
		System.out.println(new Question350().intersect(new int[]{61,24,20,58,95,53,17,32,45,85,70,20,83,62,35,89,5,95,12,86,58,77,30,64,46,13,5,92,67,40,20,38,31,18,89,85,7,30,67,34,62,35,47,98,3,41,53,26,66,40,54,44,57,46,70,60,4,63,82,42,65,59,17,98,29,72,1,96,82,66,98,6,92,31,43,81,88,60,10,55,66,82,0,79,11,81},
				new int[]{7,8,49,89,2,7,73,88,45,15,34,92,84,38,85,34,16,6,99,0,2,36,68,52,73,50,77,44,61,48}).length);
	}

}
