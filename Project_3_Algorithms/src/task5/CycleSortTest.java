package task5;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Random;

import static org.junit.Assert.*;

public class CycleSortTest {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(4000);

    @Test
    public void TestCycleSort() {
        int size = 100000;
        int[] best = new int[size];
        for (int i = 0; i < size; i++)
            best[i] = i;
        CycleSort.cycleSort(best,size);

    }

    @Test
    public void TestCycyleSort2() {
        int size = 10000;
        int[] worst = new int[size];
        for (int i = size - 1, j = 0; i >= 0; i--, j++)
            worst[i] = j;
        CycleSort.cycleSort(worst,size);


    }


    @Test
    public void TestCycleSort3() {
        int size = 10000;
        Random random = new Random();
        int[] average = new int[size];
        for (int i = 0; i < size; i++)
            average[i] = random.nextInt(size);
        CycleSort.cycleSort(average,size);

    }
}