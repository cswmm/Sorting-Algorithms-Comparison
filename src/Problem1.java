/*
Name: Nathan Durrant
Student id: 017121078
Email: nathan.durrant@sjsu.edu
 */

import java.util.Arrays;

public class Problem1 {

    public static void main(String[] args) {

        //Code to test runtime of each sorting alg

        //Create an array of random integers. Param is the size of the array
        //Right now, it is 10, meaning it will create an array of random ints that has 10 elements
        int a[] = randomArr(75);

        //Clone the array so both sorting algorithms are given the same input
        int b[] = a.clone();

        //Variables to record system time
        long start;
        long end;

        //Measure insertion sort on array a
        start = System.nanoTime();
        insertionSort(a);
        end = System.nanoTime();
        System.out.println(end - start);

        //Measure merge sort on array b
        start = System.nanoTime();
        mergeSort(b, 0, b.length-1);
        end = System.nanoTime();
        System.out.println(end - start);
    }


    public static void insertionSort(int[] arr) {
        //Create variable min to store min of array
        int min;

        //Just plain old insertion sort. Start by iterating through each element
        for (int i = 0; i < arr.length; i++) {
            //Initialize min as first element in array
            min = arr[i];
            //Check each element left in array. Find if any subsequent element is less than element i
            for (int j = i + 1; j < arr.length; j++) {
                //If an element is less than min, swap it with the current min
                if (min > arr[j]) {
                    arr[i] = arr[j];
                    arr[j] = min;
                    min = arr[i];
                }
            }
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
    public static void merge(int[] arr, int a, int b, int c) {

        //Store the size of the left half of array/subarray as n1
        //Computed by subtracting middle index from beginning, adding one to include middle
        int n1 = b - a + 1;

        //Store right half of array/subarray to be merged as n2
        //End index minus middle
        int n2 = c - b;

        //Create new arrays with according sizes
        //arr1 will store left subarray to be merged
        int arr1[] = new int[n1];
        //arr2 will store right subarray to be merged
        int arr2[] = new int[n2];
        //arrout will be the sorted array of the two sub arrays
        int arrout[] = new int[n1 + n2];

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
        //k is the index of the output. This is initialized to left most index of subarray passed into this merge function (a)
        int k = a;

        //We are merging two sorted arrays into one
        //Goes through each array arr1 and arr2
        while (i1 < n1 && i2 < n2) {
            //Compares leftmost element in arr1 and arr2. Moves smaller of the two into the output array
            if (arr1[i1] < arr2[i2]) {
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

    public static void mergeSort(int[] arr, int first, int last) {
        //Base case: if the array is 1 element, it is sorted
        if (last - first != 0){
            //Otherwise, we must sort recursively

            // store midpoint
            int mid = (first + last) / 2;

            //Divide and conquer - call mergesort recursively on the left half of array/subarray
            mergeSort(arr, first, mid);

            //Then call mergesort recursively on the right half of array/subarray
            mergeSort(arr, mid + 1, last);

            //Finally, call the merge function. Merge the left and right half of array/subarray
            merge(arr, first, mid, last);
        }
    }

    //Method to produce a random array of any given size
    public static int[] randomArr(int size) {
        //Create empty array of desired size
        int arr[] = new int[size];

        //Set each element to a random number from 1-100
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 100 + 1);
        }

        return arr;
    }
}
