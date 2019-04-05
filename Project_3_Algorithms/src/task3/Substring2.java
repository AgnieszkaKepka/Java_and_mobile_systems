package task3;

public class Substring2 {
    public static int substring2(String a, String b) {
        if(a.length()>b.length()){
            return -1;
           // throw new Exception("a is longer than b");
        }
        char [] tabA=a.toCharArray();
        char [] tabB=b.toCharArray();

        int index=-1;
        for(int i=0; i<=a.length();i++){
            if(i==a.length())
                System.out.println(" ");
               // throw new Exception("b isn't substring of a");
            if(tabA[i]==tabB[0]){
                index=i;
                break;
            }
        }
        int containAmound=(index==0) ? 0:1;
        for (int i=0;i<b.length();i++){
            if(tabA[(index+i)%a.length()]==tabB[i]){
                if((index+i)%a.length()==0)
                    containAmound++;
            }
            else{
                System.out.println(" ");
               // throw new Exception("b isn't substring of a");
            }
        }
        return containAmound;

    }
}
