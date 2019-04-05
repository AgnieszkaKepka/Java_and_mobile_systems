package task2;
import java.util.List;


public class Solution1 {

    public static int solution1(List<Integer> a) throws BigSizeException,ValueException,EmptyException{
        int[] tab = new int[1000000];

        if(a.size() == 0) {
            throw new EmptyException("element not exists"); }

        if(a.size()>1E5) {
            throw new BigSizeException("too big size");
        }

        for ( int element: a
        ) {
            if(element>0) {
                if(element>1000000)
                    throw new ValueException("too big value");
                tab[element]++;
            }
        }

        for (int i=1; i<1000000; i++) {
            if (tab[i] == 0)
                return i;
        }
        return -1;
    }
}
