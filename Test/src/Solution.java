
public class Solution {
	public ListNode addTwoNumbers(ListNode l1,ListNode l2){
		if(l1 == null) return l2;
		if(l2 == null) return l1;
		ListNode lastList = new ListNode(0);
		ListNode ret = new ListNode(0);
		while(l1 != null && l2 != null){
			ret = new ListNode(0);
			ret.val = (l1.val + l2.val)%10;
			ret.next = ret;//new ListNode(0);
			lastList = ret;
			l1 = l1.next;
			l2 = l2.next;
		}
		if(l1 != null){//l1还有的话，加在其后即可。
			ret.next = l1;
		}
		if(l2 != null){
			ret.next = l2;
		}
		return ret;
	}
	public static void main(String[] args){
		ListNode l1 = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);
		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(4);
		ListNode listNode = new Solution().addTwoNumbers(l1, l2);
		System.out.println(listNode.next.next.val);
	}
}
