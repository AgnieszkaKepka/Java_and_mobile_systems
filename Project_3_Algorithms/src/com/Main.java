package com;

import task1.Matrix;
import task2.Solution1;
import task3.Substring2;
import task5.SortMain;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws Exception {

        System.out.println();
        Scanner scanner = new Scanner(System.in);
        boolean noExit = true;
        while (noExit) {
            System.out.println("Select task: \n1. Task 1\n2. Task 2\n3. Task 3\n4. Task 4\n5. Task 5\n6. Exit");
            int select = scanner.nextInt();

            switch (select) {
                case 1:
                    Matrix matrix = new Matrix(3,5);
                    Matrix matrix2= new Matrix(3,5);
                    matrix.RandomFill(0,15);
                    matrix2.RandomFill(0,4);
                    Matrix matrix3 = matrix.addMatrix(matrix2);
                  /*  for (Integer item:matrix
                    ) {
                        System.out.print(item+ ", \n");
                    }
                  */
                    System.out.print("matrix"); matrix.Print();
                    System.out.print("matrix2");matrix2.Print();
                    System.out.print("matrix3");matrix3.Print();

                    break;
                case 2:
                    List<Integer> myList = CreateRandomList(200);
                    System.out.print("solution=" + Solution1.solution1(myList)+ "\n");
                    break;
                case 3:
                    try {
                       // System.out.print("numbers of repetitions =" + Substring2.substring2("abcd","abcdabcdabcd")+"\n");
                        System.out.print("numbers of repetitions =" + Substring2.substring2("abcd","ab")+"\n");
                    }catch (Exception exception){
                    }
                    //System.out.print("numbers of repetitions =" + Substring.substring("abcd","abcdabcdabcd")+"\n");
                    break;
                case 4:
                    float[] arr = new float[]{2,7,11,15};
                    int[] res = task4.Solution.solution(arr,13);
                    System.out.print("index : " + res[0] + "," + res[1]);
                    break;
                case 5:
                    SortMain.main(null);
                    break;
                case 6:
                    noExit = false;
                    break;
            }

        }
        System.exit(0);



    }
    public static List<Integer> CreateRandomList(int size) {
        Random random = new Random();
        List<Integer> list = new ArrayList<Integer>();

        for(int i=0; i<size; i++)
            list.add(-100+random.nextInt(200));     // min  granica max-min
        return list;
    }

}
