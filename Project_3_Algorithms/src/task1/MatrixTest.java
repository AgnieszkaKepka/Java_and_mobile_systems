package task1;

import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {


    @org.junit.Test
    public void testGet(){
        Matrix testMatrix = new Matrix(3,3);
        testMatrix.RandomFill(1, 6);
        try{
            testMatrix.Get(2, 5);
            } catch(IndexOutOfBoundsException e){
                e.printStackTrace();
            }
        }

    @org.junit.Test
    public void testGet2(){
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
        Matrix testMatrix=new Matrix(3,2);
        testMatrix.RandomFill(3,6);
        assertNotNull(testMatrix.IntegerArr);
    }

    @Test
    public void add() {
        Matrix testMatrix1=new Matrix (3,3);
        Matrix testMatrix2=new Matrix (4,4);
        try{
            testMatrix1.addMatrix(testMatrix2);
        }catch(IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    @Test
    public void TestAdd2() {
        Matrix testMatrix1=new Matrix (3,5);
        Matrix testMatrix2=new Matrix (3,5);
        testMatrix1.RandomFill(0,15);
        testMatrix2.RandomFill(0,4);
        try{
            Matrix testMatrix3=testMatrix2.addMatrix(testMatrix1);
        }catch(IllegalArgumentException e){
            e.printStackTrace();
        }
    }
}