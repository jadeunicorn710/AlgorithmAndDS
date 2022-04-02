// Sorting algorithms

// 1. Bubble Sort (TC: O(n^2), SC: O(1), In-place, Stable)
public static int[] BubbleSort(int[] inputArray) {
	// Copy input array
	int[] arr = Arrays.copyOf(inputArray, inputArray.length);
	int n = arr.length;

	// n-1 bubble rounds
	for(int i = 0; i < n - 1; i++) {
		// For each bubble round, set an indicator 
		// to check if remaining array is already sorted
		boolean isSorted = true;
		// For each round, loop through remaining array and sort
		for(int j = 0; j < n - 1 - i; j++) {
			if(arr[j] > arr[j + 1]) {
				int temp = arr[j];
				arr[j] = arr[j +1];
				arr[j + 1] = temp;
				// change sort indicator to false
				isSorted = false;
			}
		}

		// true isSorted indicated remaining array is sorted
		if (isSorted) {
			break;
		}
	}
	return arr;
}


// 2. Selection Sort (TC: O(n^2), SC: O(1), In-place, Unstable)
public static int[] SelectionSort(int[] inputArray) {
	// Copy input array
	int[] arr = Arrays.copyOf(inputArray, inputArray.length);
	int n = arr.length;
	// n-1 selection rounds
	for(int i = 0; i < n - 1; i++) {
		// initialize index of minimum
		int min = i;
		//n-i comparisons each round
		for(int j = i + 1; j < n; j++) {
			if(arr[j] < arr[min]) {
				// update index of minimum
				min = j;
			}
		}
		// swap values at min and i
		if(i != min) {
			int temp = arr[i];
			arr[i] = arr[min];
			arr[min] = temp;
		}
	}
	return arr;
}


// 3. Insert Sort (TC: O(n^2), SC: O(1), In-place, Stable)
public static int[] InsertSort(int[] inputArray) {
	// Copy input array
	int[] arr = Arrays.copyOf(inputArray, inputArray.length);
	int n = arr.length;

	// Start scanning from the second element
	for(int i = 1; i < n; i++) {
		// get current element to be inserted
		int temp = arr[i];
		// compare from right end of the sorted subarray
		int j = i;
		while(j > 0 && temp < arr[j - 1]) {
			// shift arr[j] to right by one
			arr[j] = arr[j - 1];
			// update index for inserting
			j--;
		}
		// insert temp at j
		if(j != i) {
			arr[j] = temp;
		}
		return arr;
	}


// 4. Shell Sort (TC: O(n*(logn)^2), SC: O(1), In-place, Unstable)
public static int[] ShellSort(int[] inputArray) {
	// Copy input array
	int[] arr = Arrays.copyOf(inputArray, inputArray.length);
	int n = arr.length;

	// Get interval
	int gap = 1;
	while(gap < n) {
		gap = gap * 3 + 1;
	}

	while(gap > 0) {
		for(int i = gap; i < n; i++) {
			// get start and end of incremented pair
			int temp = arr[i];
			int j = i - gap;

			// sort the incremented pair
			while(j >= 0 && arr[j] > temp) {
				// place start to end
				arr[j + gap] = arr[j];
				// update j
				j -= gap;
			}
			// place temp at j+gap
			arr[j + gap] = temp;
		}
		// udpate gap
		gap = gap / 3;
	}
	return arr;
}


// 5. Merge Sort (TC: O(n*logn), SC: O(n), Out-place, Stable)
public static int[] MergeSort(int[] inputArray) {
	// Copy input array
	int[] arr = Arrays.copyOf(inputArray, inputArray.length);
	int n = arr.length;

	int[] tempArr = new int[n];
	sort(arr, tempArr, 0, n - 1);
	return arr;

	// Sort part
	private static void sort(int[] arr, int[] tempArr, int startIndex, int endIndex) {
		if(endIndex <= startIndex) {
			return;
		}
		// Get middle index
		middleIndex = startIndex + (endIndex - startIndex) / 2;

		// Divide
		sort(arr, tempArr, startIndex, middleIndex);
		sort(arr, tempArr, middleIndex, endIndex);

		// Merge
		merge(arr, tempArr, startIndex, middleIndex, endIndex);
	}

	// Merge part
	private static void merge(int[] arr, int[] tempArr, int startIndex, int middleIndex, int endIndex) {
		// Copy array elements over
		for(int s = startIndex; s <= endIndex; s++) {
			tempArr[s] = arr[s];
		}
		// Set 2 pointers at start of each subarray
		int left = startIndex;
		int right = middleIndex + 1;
		// Loop throught array and store larger element
		for(int k = startIndex; k <= endIndex; k++) {
			if(left > middleIndex) {
				// Left subarry is sorted
				arr[k] = tempArr[right++];
			}
			else if(right > endIndex) {
				// Right subarray is sorted
				arr[k] = tempArr[left++];
			}
			else if(tempArr[right] < tempArr[left]) {
				//Get element in right subarray and move index right
				arr[k] = tempArr[right++];
			}
			else {
				// Get element in left subarray and move index right
				arr[k] = tempArr[left++];
			}
		}
	}
}


// 6. Quick Sort (TC: O(n^2), SC: O(logn), In-place, Unstable)
public static int[] QuickSort(int[] inputArray) {
	// Copy input array
	int[] arr = Arrays.copyOf(inputArray, inputArray.length);
	int n = arr.length;

	sort(arr, 0, n - 1);
	return arr;

	// Sort Part
	private static void sort(int[] arr, int startIndex, int endIndex) {
		if(endIndex <= startIndex) {
			return;
		}

		// Divide and sort
		int pivotIndex = partition(arr, startIndex, endIndex);
		sort(arr, startIndex, pivotIndex - 1);
		sort(arr, pivotIndex + 1, endIndex);
	}

	// Partition part [One Pointer]
	private static int partition(int[] arr, int startIndex, int endIndex) {
		// Initialize pivot
		int pivot = arr[startIndex];
		// Initialize mark index
		int mark = startIndex;

		for(int i = startIndex + 1; i <= endIndex; i++) {
			if(arr[i] < pivot) {
				// Increase mark and swap i and mark
				mark++;
				swap(arr, i, index);
			}
		}
		// Swap pivot and mark
		swap(arr, startIndex, mark);
		// Return pivot index
		return mark;
	}

	// Partition part [Two Pointers]
	private static int partition(int[] arr, int startIndex, int endIndex) {
		// Initialize pointers and pivot
		int left = startIndex;
		int right = endIndex;
		int pivot = arr[startIndex];

		while(true) {
			// Scan from left to right
			while(arr[left] <= pivot) {
				left++;
				if(left == right) {
					break;
				}
			}

			// Scan from right to left
			while(pivot < arr[right]) {
				right--;
				if(left == right) {
					break;
				}
			}

			// Break when two pointers meet
			if(left >= right) {
				break;
			}

			// Otherwise, swap left and right
			swap(arr, left, right);
		}

		// Insert pivot into array
		swap(arr, startIndex, right);
		return right;
	}

	// Swap function
	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}



// 7. Heap Sort (TC: O(n*logn), SC: O(1), In-place, Unstable)
public static int[] HeapSort(int[] inputArray) {
	// Copy input array
	int[] arr = Arrays.copyOf(inputArray, inputArray.length);
	int n = arr.length;

	// Build heap
	buildHeap(arr, n);

	for(int i = n - 1; i > 0; i--) {
		// Swap heap top and last element
		swap(arr, 0, i);
		// Reduce array length by 1 to hide last element in heap
		n--;
		// Sink heap top to lift maximum element to top
		sink(arr, 0, n);
	}

	// Build heap part
	private static void buildHeap(int[] arr, int n) {
		for(int i = n / 2; i >= 0; i--) {
			sink(arr, i, n);
		}
	}

	// Sink part
	private static void sink(int[] arr, int index, int n) {
		// left child index
		int leftChild = 2 * index + 1;
		// right child index
		int rightChild = 2 * index + 2;
		// node index to be adjusted
		int present = index;

		// Sink left
		if(leftChild < n && arr[leftChild] > arr[present]) {
			present = leftChild;
		}

		// Sink right
		if(rightChild < n && arr[rightChild] > arr[present]) {
			present = rightChild;
		}

		// swap present and index if not equal
		if(present != index) {
			swap(arr, present, index)

			// Further sink
			sink(arr, present, length);
		}
	}
}


// 8. Counting Sort (TC: O(n+k), SC: O(k), Out-place, Stable)
public static int[] CountingSort(int[] inputArray) {
	// Copy input array
	int[] arr = Arrays.copyOf(inputArray, inputArray.length);
	int n = arr.length;	

	// Get max value in array
	int max = arr[0];
	for(int i = 1; i < n; i++) {
		if(arr[i] > max) {
			max = arr[i];
		}
	}

	// Initialize counting array
	int[] countArr = new int[max + 1];

	// Loop through array and count occruance
	for(int i = 0; i < n; i++) {
		countArr[[arr[i]]]++;
		arr[i] = 0;
	}

	// Sort
	int index = 0;
	for(int i = 0; i < countArr.length; i++) {
		if(countArr[i] > 0) {
			arr[index++] = i;
		}
	}
}



// 9. Bucket Sort (TC: O(n^2), SC: O(n+k), Out-place, Stable)
public static int[] BucketSort(int[] inputArray) {
	// Copy input array
	int[] arr = Arrays.copyOf(inputArray, inputArray.length);
	int n = arr.length;
	// Get max and minvalue in array
	int max = arr[0];
	int min = arr[0];
	for(int i = 1; i < n; i++) {
		if(arr[i] > max) {
			max = arr[i];
		}
		else if(arr[i] < min) {
			min = arr[i];
		}
	}

	// Get difference
	int diff = max - min;

	// Define bucket list
	ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>();
	for(int i = 0; i < n; i++) {
		bucketList.add(new ArrayList<>());
	}

	// Define storage intervals in each bucket
	float section = (float) diff / (float) (n - 1);

	// Store elements into buckets
	for(int i = 0; i < n; i++) {
		// Get index of bucket based on element
		int num = (int) (arr[i] / section) - 1;
		if(num < 0) {
			num = 0;
		}
		// add into bucket
		bucketList.get(num).add(arr[i]);
	}	

	// Sort within bucket
	for(int i = 0; i < bucketList.size(); i++) {
		Collections.sort(bucketList.get(i));
	}

	// Write into original array
	int index = 0;
	for(ArrayList<Integer> arrayList : bucketList) {
		for(int value : arrayList) {
			arr[index] = value;
			index++;
		}
	}
}


// 10. Radix Sort (TC: O(n*k), SC: O(n+k), Out-place, Stable)
public static int[] RadixSort(int[] inputArray) {
	// Copy input array
	int[] arr = Arrays.copyOf(inputArray, inputArray.length);
	int n = arr.length;

	// Get max value in array
	int max = arr[0];
	for(int i = 1; i < n; i++) {
		if(arr[i] > max) {
			max = arr[i];
		}
	}

	// Get current sort location
	int location = 1;

	// Define bucketlist
	ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>();
	// Length is 10 to store 0-9
	for(int i = 0; i < 10; i++) {
		bucketList.add(new ArrayList());
	}

	while(true) {
		// Check is sorting is done
		int dd = (int)Math.pow(10, (location - 1));
		if(max < dd) {
			break;
		}

		// Store elements into buckets
		for(int i = 0; i < n; i++) {
			// Calculate mod and place into corresponding bucket
			int number = ((arr[i] / dd) % 10);
			bucketList.get(number).add(arr[i]);
		}

		// Write into original array
		int nn = 0;
		for(int i = 0; i < 10; i++) {
			int size = bucketList.get(i).size();
			for(int ii = 0; ii < size; ii++) {
				arr[nn++] = bucketList.get(i).get(ii);
			}
			bucketList.get(i).clear();
		}
		location++;
	}

}














