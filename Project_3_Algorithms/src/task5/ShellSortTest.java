package task5;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Random;

import static org.junit.Assert.*;

public class ShellSortTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(4000);

    @Test
    public void sort() {
    }

    @Test
    public void TestShellSort() {
        int size = 100000;
        int[] best = new int[size];
        for (int i = 0; i < size; i++)
            best[i] = i;
        ShellSort.sort(best);

    }

    @Test
    public void TestShellSort2() {
        int size = 10000;
        int[] worst = new int[size];
        for (int i = size - 1, j = 0; i >= 0; i--, j++)
            worst[i] = j;
        ShellSort.sort(worst);


    }


    @Test
    public void TestShellSort3() {
        int size = 10000;
        Random random = new Random();
        int[] average = new int[size];
        for (int i = 0; i < size; i++)
            average[i] = random.nextInt(size);
        ShellSort.sort(average);

    }
}