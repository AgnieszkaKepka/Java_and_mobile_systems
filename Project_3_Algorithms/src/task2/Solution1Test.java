package task2;

import com.Main;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Solution1Test extends TestCase {

    @org.junit.Test(expected=ValueException.class)
    public void testSolution1() {

        List<Integer> a = Main.CreateRandomList(200);
        a.add(1000001);
        try{
            Solution1.solution1(a);
        }catch (BigSizeException | EmptyException | ValueException exc){
            exc.printStackTrace();
        }
    }

    @org.junit.Test(expected = BigSizeException.class)
    public void testSolution2(){
        List<Integer> a = Main.CreateRandomList(200000);
        for(int i=0;i<=1000000;i++){
            a.add(0);
        }
        try{
            Solution1.solution1(a);
        }catch(BigSizeException | EmptyException | ValueException exc){
            exc.printStackTrace();
        }
    }

    @org.junit.Test(expected = EmptyException.class)
    public void testSolution3(){
        int[] tab=new int[0];
        List<Integer> a = Main.CreateRandomList(200);

        try{
            Solution1.solution1(a);
        }catch (BigSizeException | EmptyException | ValueException exc){
            exc.printStackTrace();
        }
    }

    @org.junit.Test(expected = EmptyException.class)
    public void testSolution4(){
        int[] tab=new int[0];
        List<Integer> a = new ArrayList<>();

        try{
            Solution1.solution1(a);
        }catch (BigSizeException | EmptyException | ValueException exc){
            exc.printStackTrace();
        }
    }



}
