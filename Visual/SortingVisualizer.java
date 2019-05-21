package Visual;

import java.util.ArrayList;
import java.util.Collections;

import Visual.Sorts.*;

public class SortingVisualizer {
	
	private static Thread sortingThread;

	public static VisualizerFrame frame;
	public static Integer[] toBeSorted;
	public static boolean isSorting = false;
	public static int sortDataCount = 50;
	public static int sleep = 20;
	public static int blockWidth;
	
	public static void main(String[] args) {
		frame = new VisualizerFrame();
		resetArray();
	}
	
	public static void resetArray(){
		// 建立一個sorted Array ，長度等於使用者所選的值
		toBeSorted = new Integer[sortDataCount];
		blockWidth = (int) Math.max(Math.floor(500/sortDataCount), 1);
		for(int i = 0; i<toBeSorted.length; i++){
			toBeSorted[i] = i;
		}
			
		// 打亂此 array (進行shuffle)
		ArrayList<Integer> shuffleThis = new ArrayList<>();
		for (int i = 0; i < toBeSorted.length; i++) {
			shuffleThis.add(toBeSorted[i]);
		}
		
		Collections.shuffle(shuffleThis);
		toBeSorted = shuffleThis.toArray(toBeSorted);
			
		frame.preDrawArray(toBeSorted);
	}
	
	public static void startSort(String type){
		
		if (sortingThread == null || !isSorting){
			
			resetArray();
			
			isSorting = true;

			switch(type){
			case "Bubble":
				sortingThread = new Thread(new BubbleSort(toBeSorted, frame, false));
				break;

			case "Selection":
				sortingThread = new Thread(new SelectionSort(toBeSorted, frame, false));
				break;

			case "Insertion":
				sortingThread = new Thread(new InsertionSort(toBeSorted, frame, false));
				break;
				
			case "Merge":
				sortingThread = new Thread(new MergeSort(toBeSorted, frame));
				break;
				
			case "Bubble(fast)":
				sortingThread = new Thread(new BubbleSort(toBeSorted, frame, true));
				break;

			case "Selection(fast)":
				sortingThread = new Thread(new SelectionSort(toBeSorted, frame, true));
				break;

			case "Insertion(fast)":
				sortingThread = new Thread(new InsertionSort(toBeSorted, frame, true));
				break;
			default:
				isSorting = false;
				return;
			}
			
			sortingThread.start();
			
		}
		
	}

}
