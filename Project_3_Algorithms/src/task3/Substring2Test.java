package task3;

import org.junit.Test;

import static org.junit.Assert.*;

public class Substring2Test {

    @Test
    public void incorrect() {

        Substring2 sub=new Substring2();
        assertEquals(-1,sub.substring2("abc","cv"));
    }

    @Test
    public void correct() {

        Substring2 sub=new Substring2();
        assertSame(1,sub.substring2("abc","abcdabcdabcd"));
    }


}