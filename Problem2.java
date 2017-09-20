package fortinet;

public class Problem2 {
	class MultiDimensionArray {
	    // This is a provided function, Assume it works
	    public static Long getValue(int... indexOfDimension) {
	        //... 
	        return value;
	    }

        /******* version 1 *******/
		 // lengthOfDeminsion: each dimension's length, assume it is valid: lengthOfDeminsion[i]>0.
	    public static Long sum(MultiDimensionArray mArray, int[] lengthOfDeminsion) { 
	   // Your resolution 
	   // Time complexity: O(product of each length of dimension*number of dimensions)
	   // Space complexity: O(1)
		    long sum = 0;
		    if (lengthOfDeminsion.length == 0)
		    	return sum;
		    int len = lengthOfDeminsion.length;
		    long num = 0;
		    long product = 1;
		    for(int i = 0; i < len; i++)
		    	product *= lengthOfDeminsion[i];
		    int averageLen = (int)Math.pow(Long.MAX_VALUE, 1.0/len); //average length of all the dimensions when their product fits into a Long value. (assumes each dimension's length is less or equal to averageLen)
		    int averageBinaryLen = (int)Math.ceil((Math.log(averageLen)/Math.log(2)));  //binary length of averageLen
		    long dimensionLen = 0;
		    for(int i = 0; i < len; i++){ //store the array lengthOfDeminsion in a long dimensionLen 
		    	dimensionLen |= lengthOfDeminsion[i];
		    	if (i<len-1)
		    	   dimensionLen <<= averageBinaryLen;
		    }
		    while (num < product) {
		    	long tempPro = product;
		    	long tempNum = num;
		    	for (int i = 0; i < len; i++) { //calculate the index of each dimension and store it in the lengthOfDeminsion array
		    		tempPro /= lengthOfDeminsion[i];
		    		lengthOfDeminsion[i] = (int) (tempNum/tempPro);
		    		tempNum = tempNum%tempPro;
		    	}
		        sum += mArray.getValue(lengthOfDeminsion);
		        for (int i = 0; i < len; i++) {
		        	lengthOfDeminsion[i] = (int)(dimensionLen>>((len-1-i)*averageBinaryLen))&((int)Math.pow(2, averageBinaryLen)-1); //reset the lengthOfDeminsion array to original
		        }
		        num++; 
		    }
		    return sum;
	    }
	    
	    /******* version 2 *******/
	    // lengthOfDeminsion: each dimension's length, assume it is valid: lengthOfDeminsion[i]>0.
	    public static Long sum(MultiDimensionArray mArray, int[] lengthOfDeminsion) { 
	   // Your resolution 
	   // Time complexity: O(product of each length of dimension*number of dimensions)
	   // Space complexity: O(m)
		    long sum = 0;
		    if (lengthOfDeminsion.length == 0)
		    	return sum;
		    int len = lengthOfDeminsion.length;
		    int[] index = new int[len]; //create an index array to track the index of each dimension
		    int i = len-1;
		    while (i >= 0) {
		        sum += mArray.getValue(index);
		        i = len-1;
		        while (i >= 0 && index[i] == lengthOfDeminsion[i]-1) {
		            i--;
		        }
		        if (i >= 0)
			        index[i]++;
		        for (int j=i+1; j<len; j++) {
		            index[j] = 0;
		        }  
		    }
		    return sum;
	    }
	    
	    /******* version 3 *******/
		 // lengthOfDeminsion: each dimension's length, assume it is valid: lengthOfDeminsion[i]>0.
		public static Long sum(MultiDimensionArray mArray, int[] lengthOfDeminsion) { 
		// Your resolution 
		// Time complexity: O(product of each length of dimension*number of dimensions)
		// Space complexity: O(m)
		    long sum = 0;
		    if (lengthOfDeminsion.length == 0)
		    	return sum;
		    int len = lengthOfDeminsion.length;
		    int[] index = new int[len]; //create an index array to track the index of each dimension
		    long num = 0;
		    long product = 1;
		    for (int i = 0; i < len; i++)
		    	product *= lengthOfDeminsion[i];
		    while (num < product) {
		    	long tempPro = product;
		    	long tempNum = num;
		    	for (int i = 0; i < len; i++) {
		    		tempPro /= lengthOfDeminsion[i];
		    		index[i] = (int) (tempNum/tempPro);
		    		tempNum = tempNum%tempPro;
		    	}
		        sum += mArray.getValue(index);
		        num++; 
		    }
		    return sum;
		}
    }
}
