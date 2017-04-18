import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class Solution {
	/*public ListNode addTwoNumbers(ListNode l1,ListNode l2){
		if(l1 == null) return l2;
		if(l2 == null) return l1;
		ListNode ret = l1;  
        ListNode pre1 = new ListNode(0);  
        pre1.next = l1;  
          
        int flag = 0;  
        while (l1 != null && l2 != null) {  
            l1.val = l1.val + l2.val + flag;  
            flag = l1.val / 10;  
            l1.val = l1.val % 10;  
            pre1 = l1;  
            l1 = l1.next;  
            l2 = l2.next;  
        }  
		if(l1 != null){//l1还有的话，加在其后即可。
			pre1.next = l1;
		}
		if(l2 != null){
			pre1.next = l2;
			l1 = l2;
		}
		while(l1 != null){
			l1.val += flag;
			flag = l1.val/10;
			l1.val = l1.val%10;
			pre1 = l1;
			l1 = l1.next;
		}
		if(flag>0){
			ListNode node = new ListNode(1);
			pre1.next = node;
		}
		return ret;
	}*/
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
		if(l2 == null) return l1;
		ListNode ret = l1;
		ListNode lastNode = new ListNode(0);
		lastNode.next = l1;
		int flag = 0;
		while(l1 != null && l2 != null){
			l1.val = l1.val + l2.val + flag;
			flag = l1.val/10;
			l1.val = l1.val%10;
//			lastNode = l1;
			l1 = l1.next;
			l2 = l2.next;
		}
		
		if(l2 != null){
			lastNode.next = l2;
			l1 = l2;
		}
		while(l1 != null){
		    l1.val = l1.val + flag;
			flag = l1.val/10;
			l1.val = l1.val%10;
			lastNode = l1;
			l1 = l1.next;
		}
		if(flag >0){
		    ListNode endNode = new ListNode(1);
		    lastNode.next = endNode;
		}
		return ret;
    }
	public static void main(String[] args){
		/*ListNode l1 = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);
//		l1.next.next.next = new ListNode(9);
		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(6);
		l2.next.next.next = new ListNode(9);
		ListNode listNode = new Solution().addTwoNumbers(l1, l2);
		System.out.println(listNode.val);     110
		System.out.println(listNode.next.val);001
		System.out.println(listNode.next.next.val);
		System.out.println(listNode.next.next.next.val);*/
//		System.out.println(fizzBuzz1(15));2147483647
//		System.out.println(hammingDistance(3,1));
		System.out.println(findComplement(5));
		System.out.println(detectCapitalUse("leetcode"));
		System.out.println("abc".replace('a', '1').replace('1', '2'));
		System.out.println("121.SO201702313232".substring("121.SO201702313232".indexOf(".")+1));
	}
	
	public static List<String> fizzBuzz(int n) {//3 Fizz 5 Buzz 3*5 FizzBuzz
		List<String> result = new LinkedList<>();
		if(n<1)
			return result;
		for(int i = 1;i <= n;i++){
			if(i%3 == 0 && i%5 == 0){
				result.add("FizzBuzz");
			}else if(i % 3 == 0){
				result.add("Fizz");
			}else if(i % 5 == 0)
				result.add("Buzz");
			else
				result.add(Integer.toString(i));
		}
		return result;
	}
	public static List<String> fizzBuzz1(int n) {//3 Fizz 5 Buzz 3*5 FizzBuzz
		List<String> resultList= new LinkedList<>();
        if(n < 1){
            return resultList;
        }
        for(int i = 1;i<n+1;i++){
            String s = "";
            if(i%3 ==0){
                s = "Fizz";
                resultList.add(s);
            }
            if(i%5 == 0){
                s += "Buzz";
                resultList.add(s);
            }else{  
                resultList.add(i+"");
            }
        }
        return resultList;
	}
	
	public static int hammingDistance(int x, int y) {
		int result =  x^y;
		if(result == 0)
			return 0;
		for(int i = 1;;i++){
			result = result&(result - 1);
			if(result == 0)
				return i;
		}
    }
	
	 public static int findComplement(int num) {
	        for(int i = num;i>-1;i--){
	            if((i&num) == 0)
	                return i;
	        }
	        return 0;
	    }
	 
	 public static int findComplement1(int num){
		 int temp = num,mask = 1;
		 while(temp!=0){
			 temp >>= 1;
        	 mask <<= 1;
		 }
		 return (mask-1)^num;
	 }
	 
	 public static String[] findWords(String[] words) {
	     String[] a = null;
	     return a;
	 }
	 
	 public static boolean detectCapitalUse(String word) {//AS，As为Capital A-Z 65-90 a-z 97-122
		char[] temp = word.toCharArray();
		boolean flag = temp[0] - 65 < 26 ? true : false;//首字母大写为真
		int falg = 1;
		int i = 1;
		for(;i<temp.length;i++){//第一个或者全是大写才为Capital 或全小写
			if(flag && temp[i] - 65 > 26){//第一为大写存在小写情况
				falg++;
			}else if(!flag && temp[i] - 65 < 26){//第一个为小写，末段存在大写情况
				return false;
			}
		}
		if(falg == i || falg ==1){
			return true;
		}else{
			return false;
		}
	
	 }
}
