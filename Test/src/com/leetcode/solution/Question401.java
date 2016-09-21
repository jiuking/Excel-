package com.leetcode.solution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//Binary Watch

/*For example, the above binary watch reads "3:25".

Given a non-negative integer n which represents the number of LEDs that are currently on,
return all possible times the watch could represent.

Input: n = 1
Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
*/

public class Question401 {
	
	public List<String> readBinaryWatch(int num) {  
        List<String> list = new ArrayList<String>();  
          
        int[] hours = {1,2,4,8};   
        boolean[] hvisited = new boolean[hours.length];  
          
        int[] mins = {1,2,4,8,16,32};  
        boolean[] mvisited = new boolean[mins.length];  
          
        HashSet<String> hashset = new HashSet<String>();  
        collect(list,  
        hours, 0, 0, 0, hvisited,  
        mins, 0, 0, 0, mvisited,  
        num, hashset);  
        return list;  
    }  
	
	public void collect(List<String> list,  
            int[] hours, int hindex, int hnum, int hcount, boolean[] hvisited,  
            int[] mins, int mindex, int mnum, int mcount, boolean[] mvisited,  
            int num, HashSet<String> hashset) {  
		if(hcount + mcount == num) {  
			StringBuilder sb = new StringBuilder();  
			sb.append( hnum == 0 ? "0:" : hnum +":");  
			sb.append(mnum == 0 ? "00" : mnum >=10 ? mnum : "0"+mnum);  
			String res = sb.toString();  
			if(!hashset.contains(res) && hnum <=11 && mnum <= 59){  
			    hashset.add(res);  
			    list.add(res);  
			}  
			return;  
			}  
			for(int i=hindex; i<hours.length; i++){  
			for(int j=mindex; j<mins.length; j++){  
			    if(!hvisited[i] ){  
			        hvisited[i] = true;  
			        hnum += hours[i];  
			        hcount++;  
			        if(hcount + mcount <= num){  
			            collect(list, hours, i, hnum, hcount, hvisited, mins, j, mnum,mcount, mvisited, num, hashset);  
			        }  
			        hcount--;  
			        hnum -= hours[i];  
			        hvisited[i] = false;  
			    }  
			    
			    if(!mvisited[j]){  
			        mvisited[j] = true;  
			        mnum += mins[j];  
			        mcount++;  
			        if(hcount + mcount <= num){  
			            collect(list, hours, i, hnum, hcount, hvisited, mins, j, mnum,mcount, mvisited, num, hashset);  
			        }  
			        mcount--;  
			        mnum -= mins[j];  
			        mvisited[j] = false;  
			    }  
			}  
		}  
	}  
	public List<String> readBinaryWatch1(int num) {
		List<String> list = new ArrayList<String>();  
		if(num < 0) return list;  
		for(int h=0; h<12; h++){  
			for(int m=0; m<60; m++){  
				if(Integer.bitCount(h) + Integer.bitCount(m) == num){  
					list.add(String.format("%d:%02d",h,m));  
	            }  
	        }  
	    }  
	    return list; 
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new Question401().readBinaryWatch1(1));
	}

}
