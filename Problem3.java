package fortinet;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class Problem3 {
	public boolean separate(List<Integer> list, int k){
		   // Your resolution 
		   // Time complexity: O(n^n)
		   // Space complexity: O(n)
		   if (list.size() == 0 && k == 0)
			   return true;
		   if (list.size() == 0 || k == 0 || k > list.size())
			   return false;
		   long total = 0;
		   for (int i: list)
			   total += i;
		   if (total%k != 0)
			   return false;
		   long sum = total/k;
		   boolean[] visited = new boolean[list.size()];
		   Collections.sort(list);
		   return separateHelper(list, 0, sum, visited, k);
		}
	
	private boolean separateHelper(List<Integer> list, long cursum, long sum, boolean[] visited, int k){
		   if (k < 0)
			   return false;
		   boolean visitAll = true;
	       for (int i = 0; i < list.size(); i++) {
	    	  if (!visited[i]) { 
	    		  visitAll=false;
	    		  if (list.get(i) >= 0 && sum-cursum < list.get(i)) //since the list is sorted, when the rest sum is smaller that the current number and the number is positive, we just return false
	    			  return false;
	    		  visited[i] = true;
	    		  boolean res = false;
	    		  if (cursum+list.get(i) == sum) // when the current sum added with current number is equal to target sum, we reset cursum to 0 and decrease the rest group number k by 1
	    			  res = separateHelper(list, 0, sum, visited, k-1);
	    		  else
	    			  res = separateHelper(list, cursum+list.get(i), sum, visited, k);
	    		  if (res)
	    			  return true;
	    		  visited[i]=false;
	    	  }
	       }
	       if (visitAll && cursum == 0 && k == 0)// when we have all the list elements visited and cursum is reset to 0 and we have found k groups, we can return true 
	    	   return true;
	       return false;
	}
	
	// JUnit test cases 
	@Test
	public void testSeparateTrue() {
		List<Integer> list = Arrays.asList(1,1,2,3,3,3,5);
		assertTrue(separate(list, 1));
		assertTrue(separate(list, 2)); //[1,1,2,5],[3,3,3]
		assertTrue(separate(list, 3)); //[1,5],[1,2,3],[3,3]
		
		list = Arrays.asList(-6,-5,-4,-3,-2,-2,-1,-1);
		assertTrue(separate(list, 3)); //[-6,-2],[-5,-3],[-4,-2,-1,-1]
		assertTrue(separate(list, 4)); //[-6],[-5,-1],[-4,-2],[-3,-2,-1]
		
		list = Arrays.asList(-5,-4,-3,-2,1,2,2,3,4,6);
		assertTrue(separate(list, 2)); //[-5,-4,1,4,6],[-3,-2,2,2,3]
		assertTrue(separate(list, 4)); //[-5,6],[-4,1,4],[-3,2,2],[-2,3]
	}
	
	@Test
	public void testSeparateFalse() {
		List<Integer> list = Arrays.asList(1,1,2,3);
		assertFalse(separate(list, 2));
		assertFalse(separate(list, 3));
		assertFalse(separate(list, 4));
		assertFalse(separate(list, 5));
		
		list = Arrays.asList(-3,-2,-2,-1,-1);
		assertFalse(separate(list, 2));
		assertFalse(separate(list, 4));
		
		list = Arrays.asList(-5,-4,2,3,4);
		assertFalse(separate(list, 3));
		assertFalse(separate(list, 4));
	}
}
