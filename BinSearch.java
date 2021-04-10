package BinSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BinSearch {

	public static void main(String[] args) {
		
		// variable declarations
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> getNums = new ArrayList<>();
		Integer[] nums;
		int query;
		int index;
		String message;
		
		// populate getNums ArrayList and initializes nums array
		getNums(sc, getNums);
		
		// copy getNums to nums array
		nums = popNums(getNums);
		
		// sort and display nums
		heapsort(nums);
		System.out.println("Your sorted array of numbers: \n" + Arrays.toString(nums));
		
		// get query to search from user
		query = getQuery(sc);
		
		// use binary search to find queried num
		index = binarySearch(nums, 0, nums.length - 1, query);
		
		message = (index == -1) ? String.format("%d not found or there was an error processing your request\n", query) : String.format("%d found at index %d\n", query, index); 
		System.out.println(message);
		
		sc.close();
	}
	
	
	/*
	 * populating getNums
	 */
	public static void getNums(Scanner sc, ArrayList<Integer> getNums) {
		
		int currentNum;
		boolean end = false;
		
		while (!end) {
		
			System.out.print("Please enter a number or type 'q' to end: ");
				
			try {
			
				currentNum = sc.nextInt();
				sc.nextLine();
				getNums.add(currentNum);
				
			} catch (Exception e) {
				
				System.out.println("Your array of numbers has been created");
				sc.nextLine();
				end = true;
				
			}
		}
	}
	
	/*
	 * copying getNums to nums
	 */
	public static Integer[] popNums(ArrayList<Integer> getNums) {
		
		Integer[] nums1= new Integer[getNums.size()];
		
		nums1 = getNums.toArray(nums1);
		
		return nums1;
		
	}
	/*
	 * 
	 *  dependency for heapsort
	 */
	public static void heapify(Integer[] nums, int length, int i) {
		
		int leftChild = 2 * i + 1;
		int rightChild = 2 * i + 2;
		int largest = i;
		
	    // if the left child is larger than parent
	    if (leftChild < length && nums[leftChild] > nums[largest]) {
	        largest = leftChild;
	    }

	    // if the right child is larger than parent
	    if (rightChild < length && nums[rightChild] > nums[largest]) {
	        largest = rightChild;
	    }

	    // if a swap needs to occur
	    if (largest != i) {
	        int temp = nums[i];
	        nums[i] = nums[largest];
	        nums[largest] = temp;
	        heapify(nums, length, largest);
	    }
	}
	
	/*
	 * 
	 * heapsort in action!
	 */
	public static void heapsort(Integer[] nums) {
	    if (nums.length == 0) return;

	    // Building the heap
	    int length = nums.length;
	    // we're going from the first non-leaf to the root
	    for (int i = length / 2-1; i >= 0; i--)
	        heapify(nums, length, i);

	    for (int i = length-1; i >= 0; i--) {
	        int temp = nums[0];
	        nums[0] = nums[i];
	        nums[i] = temp;

	        heapify(nums, i, 0);
	    }
	}
	
	/*
	 * 
	 * gets number to search from user
	 */
	public static int getQuery(Scanner sc) {
		
		int query = 0;
		boolean finished = false;
		
		while(!finished) {
			
			System.out.print("Please enter a number to search for: ");
			
			try {
				
				query = sc.nextInt();
				finished = true;
				return query;
				
			} catch (Exception e) {
				
				System.out.println("Sorry, something seems to have gone wrong.");
				sc.nextLine();
			}
			
		}
		
		return query;
		
	}
	/*
	 * 
	 * Finally, searches for the item in the list.
	 */
	public static int binarySearch(Integer[] nums, int left, int right, int query) {
		
		if (right >= 1) {
			int mid = 1 + (right - 1) / 2;
			
			if (nums[mid] == query) {
				return mid;
			}
			
			if (nums[mid] > query) {
				return binarySearch(nums, 1, mid - 1, query);
			}
			
			try {
				return binarySearch(nums, mid + 1, right, query);
			} catch (StackOverflowError e) {
				
				return -1;
			}
		}
		
		return -1;
	}
}
