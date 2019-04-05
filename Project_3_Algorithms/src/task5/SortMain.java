package task5;

import java.util.Random;

public class SortMain {
    public static void main(String[] args) {
        int size=1000;   //elements to sort
        double[] elapsedSeconds = new double[3];

       /*____BEST_CASE_____*/

        int[] best = new int[size];
        for (int i = 0; i < size; i++)
            best[i] = i;

        //printArray(best);

        long tStart = System.currentTimeMillis();
        //BubbleSort.bubbleSort(best);
        //HeapSort.sort(best);
        //QuickSort.sort(best,0,size-1);
        //ShellSort.sort(best);
        CycleSort.cycleSort(best,size);

        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        elapsedSeconds[0] = (double) tDelta / 1000.000;

        /*____WORST_CASE_____*/

        int[] worst = new int[size];
        for (int i = size - 1, j = 0; i >= 0; i--, j++)
            worst[i] = j;

        tStart = System.currentTimeMillis();
        //BubbleSort.bubbleSort(worst);
        //HeapSort.sort(worst);
        //QuickSort.sort(worst,0,size-1);
       //ShellSort.sort(worst);
        CycleSort.cycleSort(worst,size);

        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds[1] = (double) tDelta / 1000.000;

        /*____AVERAGE_CASE_____*/

        Random random = new Random();
        int[] average = new int[size];
        for (int i = 0; i < size; i++)
            average[i] = random.nextInt(size);

        tStart = System.currentTimeMillis();
        //BubbleSort.bubbleSort(average);
        //HeapSort.sort(average);
        //QuickSort.sort(average,0,size-1);
        //ShellSort.sort(average);
        CycleSort.cycleSort(average,size);

        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds[2] = (double) tDelta / 1000.000;

        //printArray(average);

        System.out.println("Best case: " + elapsedSeconds[0] + "ms");
        System.out.println("Worst case: " + elapsedSeconds[1] + "ms");
        System.out.println("Average case: " + elapsedSeconds[2] + "ms");
    }
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}
