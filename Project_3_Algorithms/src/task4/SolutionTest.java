package task4;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest extends TestCase {

    @org.junit.Test(expected=NoSolutionException.class)
    public void testSolution() {                  //pustaTablica
        float [] arr={};
        //float[] arr = new float[]{2,7,11,15};

        try{
            Solution.solution(arr,12);
        }catch(NoSolutionException exc){
            exc.printStackTrace();
        }
    }

    @org.junit.Test(expected=NoSolutionException.class)
    public void testSolution2() {

        float[] arr = new float[]{2,7,11,15};    //nie ma takich liczb co się zsumują do 12

        try{
            Solution.solution(arr,12);
        }catch(NoSolutionException exc){
            exc.printStackTrace();
        }
    }

    @org.junit.Test(expected=NoSolutionException.class)
    public void testSolution3() {

        float[] arr = new float[]{2,7,11,15};    //poprawne

        try{
            Solution.solution(arr,9);
        }catch(NoSolutionException exc){
            exc.printStackTrace();
        }
    }
}