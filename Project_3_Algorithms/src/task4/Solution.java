package task4;

public class Solution {
    public static int[] solution(float[] arr, float target)throws NoSolutionException{

        for(int i=0; i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]+arr[j]==target)     //te dwa elem dadzą w sumie target
                    return new int[]{i,j};

            }
        }
        throw new NoSolutionException("No pair of the numbers sums to the target");
    }
}
