package com.rhwayfun.mockito;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chubin on 2017/4/16.
 */
public class Mockito {

    private static Map<Invocation, Object> MOCK_RESULTS = new HashMap<>();

    private static Invocation lastInvocation;

    public static <T> T mock(Class<T> t){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(t);
        enhancer.setCallback(new MockMethodInterceptor());
        return (T) enhancer.create();
    }

    public static <T> When<T> when(T t){
        return new When<T>();
    }

    public static class When<T>{
        public void thenReturn(T obj){
            MOCK_RESULTS.put(lastInvocation, obj);
        }
    }

    private static class MockMethodInterceptor implements MethodInterceptor{

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            Invocation invocation = new Invocation(o, method, objects, methodProxy);

            lastInvocation = invocation;

            if (MOCK_RESULTS.containsKey(invocation)){
                return MOCK_RESULTS.get(invocation);
            }

            return null;
        }
    }

}
