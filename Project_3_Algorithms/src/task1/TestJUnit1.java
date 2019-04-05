package task1;

import org.junit.Test;

public class TestJUnit1  {
    @org.junit.Test
    public void testGet() throws IndexOutOfBoundsException{
        Matrix testMatrix = new Matrix(3,3);
        testMatrix.RandomFill(1, 6);
        try{
            testMatrix.Get(2, 5);
        } catch(IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }

    @Test
    public void TestAdd() {
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
