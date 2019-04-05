package task5;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Random;

import static org.junit.Assert.*;

public class QuickSortTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(4000);

    @Test
    public void partition() {
    }

    @Test
    public void sort() {
    }

    @Test
    public void TestQuickSort() {
        int size = 1000;
        int[] best = new int[size];
        for (int i = 0; i < size; i++)
            best[i] = i;
        QuickSort.sort(best,0,size-1);

    }

    @Test
    public void TestQuickSort2() {
        int size = 100;
        int[] worst = new int[size];
        for (int i = size - 1, j = 0; i >= 0; i--, j++)
            worst[i] = j;
        QuickSort.sort(worst,0,size-1);


    }


    @Test
    public void TestQuickSort3() {
        int size = 10000;
        Random random = new Random();
        int[] average = new int[size];
        for (int i = 0; i < size; i++)
            average[i] = random.nextInt(size);
        QuickSort.sort(average,0,size-1);

    }
}