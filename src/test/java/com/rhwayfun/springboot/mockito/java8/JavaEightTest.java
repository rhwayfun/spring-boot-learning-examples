package com.rhwayfun.springboot.mockito.java8;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
/**
 * Created by chubin on 2017/4/16.
 */
public class JavaEightTest {

    @Test
    public void test() throws Exception{
        List<String> list = mock(List.class);
        // verify a list only had strings of a certain length added to it
        // note - this will only compile under Java 8
        list.add("abcdefg");
        list.add("a");
        list.add("b");
        verify(list, times(2)).add(argThat(string -> string.length() < 5));

        // Java 7 equivalent - not as neat
        //verify(list, times(2)).add(argThat(arg -> arg.length() < 5));

        // more complex Java 8 example - where you can specify complex verification behaviour functionally
        Target target = mock(Target.class);

        SourceArgs sourceArgs = new SourceArgs();
        SubObject obj = new SubObject();
        List<String> l = new ArrayList<>();
        l.add("expected");
        obj.setList(l);
        sourceArgs.setSubObject(obj);
        target.receiveComplexObject(sourceArgs);

        verify(target, times(1)).receiveComplexObject(argThat(o -> o.getSubObject().getList().get(0).equals("expected")));

        // this can also be used when defining the behaviour of a mock under different inputs
        // in this case if the input list was fewer than 3 items the mock returns null
        when(list.addAll(argThat(c -> c.size()<3))).thenReturn(true);
    }

    private static class Target{
        public void receiveComplexObject(SourceArgs soureArgs){
            System.out.println("Now: " + LocalDateTime.now() + "," + JSON.toJSONString(soureArgs));
        }
    }

    private static class SourceArgs{
        private SubObject subObject;

        public SubObject getSubObject() {
            return subObject;
        }

        public void setSubObject(SubObject subObject) {
            this.subObject = subObject;
        }
    }

    private static class SubObject{
        private List<String> list;

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }
    }
}
