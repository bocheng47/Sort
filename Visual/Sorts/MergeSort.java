package Visual.Sorts;

import Visual.SortingVisualizer;
import Visual.VisualizerFrame;

public class MergeSort implements Runnable{
	
	private Integer[] toBeSorted;
	private VisualizerFrame frame;
	
	public MergeSort(Integer[] toBeSorted, VisualizerFrame frame) {
		this.toBeSorted = toBeSorted;
		this.frame = frame;
	}
	public void run() {
		mergeSort(toBeSorted, toBeSorted.length);
		SortingVisualizer.isSorting=false;
	}
	public void merge(Integer[] a, Integer[] l, Integer[] r, int left, int right) {
			  
	    int i = 0, j = 0, k = 0;
	    while (i < left && j < right) {
	        if (l[i] <= r[j]) {
	            a[k++] = l[i++];
	        }
	        else {
	            a[k++] = r[j++];
	        }
	    }
	    while (i < left) {
	        a[k++] = l[i++];
	    }
	    while (j < right) {
	        a[k++] = r[j++];
	    }
	    frame.reDrawArray(a, k, i, j);
		try {
			Thread.sleep(SortingVisualizer.sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void mergeSort(Integer[] a, int n) {
	    if (n < 2) {
	        return;
	    }
	    int mid = n / 2;
	    Integer[] l = new Integer[mid];
	    Integer[] r = new Integer[n - mid];
	 
	    for (int i = 0; i < mid; i++) {
	        l[i] = a[i];
	    }
	    for (int i = mid; i < n; i++) {
	        r[i - mid] = a[i];
	    }
	    mergeSort(l, mid);
	    mergeSort(r, n - mid);
	 
	    merge(a, l, r, mid, n - mid);
	}
}
