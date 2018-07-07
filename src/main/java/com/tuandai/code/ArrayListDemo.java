package com.tuandai.code;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by 肖文 on 2018/7/7
 */
public class ArrayListDemo implements Serializable {

    private static final long serialVersionUID = 1L;

    //默认ArrayList初始化大小
    private static final int DEFAULT_CAPACITY = 10;
    //初始化空白数组
    private static final Object[] EMPTY_ELEMENT_DATA = {};

    //集合实际存储数组
    transient Object[] elementData;
    //集合大小
    private int size;
    private transient int modCount = 0;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * 无参构造方法，创建一个空的数组
     */
    public ArrayListDemo() {
        this.elementData = EMPTY_ELEMENT_DATA;
    }

    /**
     * 创建一个有初始容量的空ArrayList
     *
     * @param initialCapacity
     */
    public ArrayListDemo(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity:" + initialCapacity);
        }
    }

    /**
     * 创建一个包含collection的ArrayList
     * 将集合的元素放到ArrayList集合
     *
     * @param collection
     */
    public ArrayListDemo(Collection collection) {
        this.elementData = collection.toArray();
        if ((size = this.elementData.length) != 0) {
            if (this.elementData.getClass() != Object.class) {
                this.elementData = Arrays.copyOf(elementData, size, Object[].class);
            }
        } else {
            this.elementData = EMPTY_ELEMENT_DATA;
        }
    }

    /**
     * 将数组缓冲大小调整为实际ArrayList存储元素的大小
     * 以减少空间资源浪费
     */
    public void trimToSize() {
        modCount++;
        if (size < this.elementData.length) {
            this.elementData = (size == 0)
                    ? EMPTY_ELEMENT_DATA
                    : Arrays.copyOf(elementData, size);
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = this.elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - oldCapacity < 0) {
            newCapacity = minCapacity;
        }
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
//            newCapacity=
        }
    }
}
