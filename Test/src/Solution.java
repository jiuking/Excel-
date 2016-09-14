
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
		ListNode l1 = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);
//		l1.next.next.next = new ListNode(9);
		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(6);
		l2.next.next.next = new ListNode(9);
		ListNode listNode = new Solution().addTwoNumbers(l1, l2);
		System.out.println(listNode.val);
		System.out.println(listNode.next.val);
		System.out.println(listNode.next.next.val);
		System.out.println(listNode.next.next.next.val);
	}
}
