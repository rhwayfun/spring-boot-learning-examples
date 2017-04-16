package com.rhwayfun.mockito;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by chubin on 2017/4/16.
 */
public class Invocation {

    private final Object mock;

    private final Method method;

    private final Object[] agrs;

    private final MethodProxy proxy;

    public Invocation(Object mock, Method method, Object[] agrs, MethodProxy proxy) {
        this.mock = mock;
        this.method = method;
        this.agrs = copyArgs(agrs);
        this.proxy = proxy;
    }

    private Object[] copyArgs(Object[] args){
        Object[] newArgs = new Object[args.length];
        System.arraycopy(args, 0, newArgs, 0, args.length);
        return newArgs;
    }

    public Object callRealMethod() {
        try {
            return this.proxy.invokeSuper(mock, agrs);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invocation that = (Invocation) o;

        return this.method.equals(that.method) && this.proxy.equals(that.proxy) && Arrays.deepEquals(this.agrs, that.agrs);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
