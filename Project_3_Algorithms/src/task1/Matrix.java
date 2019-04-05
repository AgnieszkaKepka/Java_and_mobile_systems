package task1;

import java.util.Random;

public class Matrix {
    private int width, high;
    public Integer[][] IntegerArr;

public Matrix(int x,int y){
    width = x;
    high = y;
    IntegerArr = new Integer[width][high];
    }

    public Integer Get(int x, int y) throws IndexOutOfBoundsException {
        if (x >= width || y >= high) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        return IntegerArr[x][y];
    }

    public void RandomFill(int min, int max) {
        Random random = new Random();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < high; j++) {
                IntegerArr[i][j] = min + random.nextInt(max - min);
            }
        }
        return;
    }

    public Matrix addMatrix(Matrix matrix) throws IllegalArgumentException {

        if (high == matrix.high) {
            if (width == matrix.width) {
                Matrix newMatrix = new Matrix(width, high);
                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < high; j++) {
                        newMatrix.IntegerArr[i][j] = IntegerArr[i][j] + matrix.IntegerArr[i][j];
                    }
                }
                return newMatrix;
            }
        }
        throw new IllegalArgumentException();
    }

    public void Print(){
        System.out.println();
        for(int i = 0; i< width; i++){
            for(int j = 0; j< high; j++){
                System.out.print(" " + IntegerArr[i][j]);
            }
            System.out.println();
        }
    }
}
