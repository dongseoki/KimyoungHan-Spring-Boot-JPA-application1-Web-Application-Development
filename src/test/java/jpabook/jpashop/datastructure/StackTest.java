package jpabook.jpashop.datastructure;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {
    @Test
    public void putTest(){
        //given
        Stack st = new Stack();

        //when
        st.put(1);

        //then
        Assert.assertEquals(st.size(), 1);
    }

    @Test
    public void popTest(){
        //given
        Stack st = new Stack();
        st.put(1);
        st.put(2);

        //when
        int data =  st.pop();

        //then
        Assert.assertEquals(data, 2);
        Assert.assertEquals(st.size(), 1);
    }

    @Test
    public void topTest(){
        //given
        Stack st = new Stack();
        st.put(1);
        st.put(2);

        //when
        int data =  st.top();

        //then
        Assert.assertEquals(data, 2);
        Assert.assertEquals(st.size(), 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void popFailTest(){
        Stack st = new Stack();
        st.pop();
        Assert.fail();// 여기까지 오면 안됨.
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void topFailTest(){
        Stack st = new Stack();
        st.pop();
        Assert.fail();// 여기까지 오면 안됨.
    }
}