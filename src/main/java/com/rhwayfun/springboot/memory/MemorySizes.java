package com.rhwayfun.springboot.memory;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Created by chubin on 2017/8/20.
 */
public class MemorySizes {
    private final Map primitiveSizes = new IdentityHashMap() {
        {
            put(boolean.class, new Integer(1));
            put(byte.class, new Integer(1));
            put(char.class, new Integer(2));
            put(short.class, new Integer(2));
            put(int.class, new Integer(4));
            put(float.class, new Integer(4));
            put(double.class, new Integer(8));
            put(long.class, new Integer(8));
        }
    };

    public int getPrimitiveFieldSize(Class clazz) {
        return ((Integer) primitiveSizes.get(clazz)).intValue();
    }

    public int getPrimitiveArrayElementSize(Class clazz) {
        return getPrimitiveFieldSize(clazz);
    }

    public int getPointerSize() {
        return 4;
    }

    public int getClassSize() {
        return 8;
    }
}
