public class Solution
{
	public ListNode reverseList(ListNode head)
	{
		if(head == null || head.next == null)
			return head;
		ListNode p = head;
		ListNode q = p.next;
		while(p != null && q != null)
		{
			ListNode temp = q.next;
			q.next = p;
			p = q;
			if(temp != null)
				q = temp;
			else
				break;
		}
		head.next = null;
		return p;
	}
}