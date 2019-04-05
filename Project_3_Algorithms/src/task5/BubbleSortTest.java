package task5;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runners.model.TestTimedOutException;

import java.util.Random;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;

public class BubbleSortTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(4000);

    @Test
    public void TestBubbleSort() {
        int size = 100000;
        int[] best = new int[size];
        for (int i = 0; i < size; i++)
            best[i] = i;
        BubbleSort.bubbleSort(best);

    }


    @Test
    public void TestBubbleSort2() {
        int size = 10000;
        int[] worst = new int[size];
        for (int i = size - 1, j = 0; i >= 0; i--, j++)
            worst[i] = j;
        BubbleSort.bubbleSort(worst);

    }


    @Test
    public void TestBubbleSort3() {
        int size = 10000;
        Random random = new Random();
        int[] average = new int[size];
        for (int i = 0; i < size; i++)
            average[i] = random.nextInt(size);

        BubbleSort.bubbleSort(average);
    }
}


