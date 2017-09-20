package fortinet;

import static org.junit.Assert.*;

import org.junit.Test;

public class Problem1 {
	    class Node {
		    Node next;
		    int value;
		}
		// Your resolution 
		// Time complexity:  O(n)
		// Space complexity:  O(1)
		public Node reverse(Node head){
			if (head == null)
				return null;
			Node prev = null;
			Node cur = head;
			while (cur != null) {
				Node post = cur.next;
				cur.next = prev;
				prev = cur;
				cur = post;
			}
			return prev;
		}
		
		// JUnit test cases 
		private boolean equals(Node head1, Node head2){
		    while (head1 != null && head2 != null) {
		        if (head1.value != head2.value)
		        	return false;
		        head1 = head1.next;
		        head2 = head2.next;
		    }
		    return head1 == null && head2 == null;
		}
		
		@Test
		public void testReverseOneNode() {
			Node head = new Node();
			head.value = 1;
			Node expectedHead = new Node();
			expectedHead.value = 1;
			
			assertTrue(equals(expectedHead, reverse(head)));
		}
		
		@Test
		public void testReverseMultiNodes() {
			Node head = new Node();
			head.value = 1;
			Node temp = head;
			temp.next = new Node();
			temp.next.value = 2;
			temp = temp.next;
			temp.next = new Node();
			temp.next.value = 3;
			
			Node expectedHead = new Node();
			expectedHead.value = 3;
			temp = expectedHead;
			temp.next = new Node();
			temp.next.value = 2;
			temp = temp.next;
			temp.next = new Node();
			temp.next.value = 1;
			
			assertTrue(equals(expectedHead, reverse(head)));
		}
}
