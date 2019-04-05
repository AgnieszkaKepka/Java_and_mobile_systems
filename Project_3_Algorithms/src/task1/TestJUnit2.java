package task1;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class TestJUnit2 {
    @org.junit.Test
    public void testGet(){
        Matrix testMatrix = new Matrix(4,4);
        testMatrix.RandomFill(1, 6);
        try{
            assertSame(testMatrix.IntegerArr[3][3],testMatrix.Get(3,3));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void randomFill() {
        Matrix testMatrix=new Matrix(3,2); //co tam jest nie jest nullem
        testMatrix.RandomFill(3,6);
        assertNotNull(testMatrix.IntegerArr);
    }

    @Test
    public void add() {
        Matrix testMatrix1=new Matrix (3,3); //czy wyrzuca wyjatek przy rozmiarach tablicy
        Matrix testMatrix2=new Matrix (4,4);
        try{
            testMatrix1.addMatrix(testMatrix2);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
