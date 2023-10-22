/*
Name: Nathan Durrant
Student id: 017121078
Email: nathan.durrant@sjsu.edu
 */

import java.sql.Date;
import java.util.Arrays;

public class PersonSorter {

	// Constants representing sorting mode by the field of class person.
	public static final int BY_LAST_NAME 		= 0;
	public static final int BY_DATE_OF_BIRTH 	= 1;

	public static Person[] sortBy(Person[] A, int byValue) {
		switch (byValue) {
		case BY_LAST_NAME:
			return sortByLastName(A);
		case BY_DATE_OF_BIRTH:
			return sortByDateOfBirth(A);
		default:
			return A;
		}
	}
	
	//////////////////////////////////////////////////////////////////////////
	// TODO: Implement the two sorting methods                            //
	// 		 Note that A.clone always creates first a copy of the input     //
	//////////////////////////////////////////////////////////////////////////
	
	private static Person[] sortByDateOfBirth(Person[] A) {
		Person [] P = A.clone();
		//Call merge sort to sort by date of birth
		mergeSort(P, 0, A.length-1);
		return P;
	}

	private static Person[] sortByLastName(Person[] A) {
		Person [] P = A.clone();
		//Insertion sort can be done iteratively, so it is put directly into this method

		// min stores the first alphabetical last name
		// temp will be used to swap objects in the array
		String min;
		Person temp;
		for (int i = 0; i < P.length; i++) {
			//Initialize min as the date of the first element in array
			min = P[i].getLastName();
			//Check each element left in array. Find the minimum
			for (int j = i + 1; j < P.length; j++) {
				//If an element is less than min, i.e. if a date is before min, swap it with the current min
				if (min.compareTo(P[j].getLastName()) > 0){
					temp = P[i];
					P[i] = P[j];
					P[j] = temp;
					min = P[i].getLastName();
				}
			}
		}
		return P;
	}

	public static void mergeSort(Person[] arr, int first, int last) {
		//Base case: if the array is 1 element, it is sorted
		if (last - first == 0) {
			return;
		} else {
			//Otherwise, store midpoint
			int mid = (first + last) / 2;
			//Divide and conquer - call mergesort recursively on the left half of array/subarray
			mergeSort(arr, first, mid);
			//Then call mergesort recursively on the right half of array/subarray
			mergeSort(arr, mid + 1, last);
			//Finally, call the merge function. Merge the left and right half of array/subarray
			merge(arr, first, mid, last);
		}
	}

	/*
    Method that will "merge" or combine two sorted arrays into one sorted array
    This is what we are going to recursively call in mergesort

    Params-
    The array that contains both subarrays to be merged
    a- "Beginning"- index of the beginning of the first array
    b- "Middle"- this element is included in the first subarray, but all elements after are the second subarray to be sorted
    c- "end"- index of the end of the second array
     */
	public static void merge(Person[] arr, int a, int b, int c) {
		//Store the size of the left half of array/subarray as n1
		int n1 = b - a + 1;
		//Store right half of array/subarray to be merged as n2
		int n2 = c - b;

		//Create new arrays with according sizes
		//arr1 will store left subarray to be merged
		Person arr1[] = new Person[n1];
		//arr2 will store right subarray to be merged
		Person arr2[] = new Person[n2];
		//arrout will be the sorted array of the two given subarrays
		Person arrout[] = new Person[n1 + n2];

		//Copy elements from start to midpoint on arr1
		for (int i = 0; i < n1; ++i) {
			arr1[i] = arr[a + i];
		}
		//Copy elements from midpoint to end on arr2
		for (int i = 0; i < n2; ++i) {
			arr2[i] = arr[b + 1 + i];
		}

		//Initialize Variables
		//i1 will be the index of arr1
		int i1 = 0;
		//i2 will be the index of arr2
		int i2 = 0;
		//k is the index of the output. This is initialized to left most index of subarray passed into this merge function(a)
		int k = a;

		//d1 will be the date of birth of the first array's i-th element
		Date d1;
		//d2 will be the date of birth of the second array's i-th element
		Date d2;

		//We are merging two sorted arrays into one
		//Goes through each array arr1 and arr2
		while (i1 < n1 && i2 < n2) {
			d1 = arr1[i1].getDateOfBirth();
			d2 = arr2[i2].getDateOfBirth();
			//Compares leftmost element in arr1 and arr2. Moves object with earlier date of birth to the output array
			if (d1.compareTo(d2) < 0) {
				arr[k] = arr1[i1];
				++i1;
			} else {
				arr[k] = arr2[i2];
				++i2;
			}
			k++;
		}
		//Clear out remaining elements of the other array once one has been completely iterated through
		//Puts remaining elements at the end of output array
		for (; i1 < n1; ++i1, ++k) {
			arr[k] = arr1[i1];
		}
		for (; i2 < n2; ++i2, ++k) {
			arr[k] = arr2[i2];
		}
	}
	
	/*
	 * TODO: Complete this method
	 */
	public static Person[] sortByLastNameAndDateOfBirth(Person[] A) {
		Person [] P = A.clone();
		//Sort given array using quicksort
		quickSort(P, 0, P.length-1);
		return P;
	}

	public static void quickSort(Person[] A, int begin, int end){
		if (begin < end){
			// First partition the array with given indices

			// Store the index where all elements between it and beginning are less or equal to pivot
			// and all elements to the right are greater than pivot
			int q = partition(A, begin, end);

			// Recursively call quick sort on left and right partitions, excluding q
			quickSort(A, begin, q-1);
			quickSort(A, q+1, end);
		}
	}

	public static int partition(Person[] A, int begin, int end){
		//q is pointer to end of array of elements less than the pivot (A[end])
		//initialized to first index
		int q = begin;
		//temp for swapping objects later
		Person temp;
		for (int i=begin; i<=end; i++){
			//Compares last name and date of birth with pivot
			//If they have the same last name and the current person has an earlier date of birth, it is considered "less than"
			// => therefore swap current person with A[q] to put in the starting array of elements less than pivot
			if(A[end].getLastName().compareTo(A[i].getLastName()) == 0 &&
					A[i].getDateOfBirth().compareTo(A[end].getDateOfBirth()) <= 0){
				temp = A[i];
				A[i] = A[q];
				A[q] = temp;
				q++;
				//Otherwise, if the person has a last name that is less than pivot, they are put in
				// 		the starting array of elements less than pivot
			} else if(A[i].getLastName().compareTo(A[end].getLastName()) < 0){
				temp = A[i];
				A[i] = A[q];
				A[q] = temp;
				q++;
			}
		}
		return q-1;
	}
}
