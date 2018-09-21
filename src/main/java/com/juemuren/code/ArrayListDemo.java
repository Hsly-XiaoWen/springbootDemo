package com.juemuren.code;

import java.io.Serializable;
import java.util.*;

/**
 * Created by 肖文 on 2018/7/7
 */
public class ArrayListDemo<E> extends AbstractList<E> implements Serializable {

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
    public ArrayListDemo(Collection<? extends E> collection) {
        this.elementData = collection.toArray();
        if ((size = this.elementData.length) != 0) {
            if (this.elementData.getClass() != Object[].class) {
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

    /**
     * 扩容前判断
     * @param minCapacity
     */
    private void ensureCapacityInternal(int minCapacity) {
        if (elementData == EMPTY_ELEMENT_DATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    /**
     * 扩容
     * @param minCapacity
     */
    private void grow(int minCapacity) {
        int oldCapacity = this.elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - oldCapacity < 0) {
            newCapacity = minCapacity;
        }
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            newCapacity = hugeCapacity(minCapacity);
        }
        Arrays.copyOf(elementData, newCapacity);
    }

    private int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }
        return minCapacity > MAX_ARRAY_SIZE ?
                Integer.MAX_VALUE : MAX_ARRAY_SIZE;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contain(Object object) {
        if (object == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return true;
                }
            }
        } else {
            for (int i=0;i<size;i++) {
                if (object.equals(elementData[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    public E get(int index) {
        this.checkIndex(index);
        return (E) elementData[index];
    }

    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    E elementData(int index) {
        return (E) elementData[index];
    }

    /**
     * 修改指定位置value，返回原来值
     * @param index
     * @param element
     * @return
     */
    public E set(int index, E element) {
        this.checkIndex(index);
        E oldValue = (E) elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    /**
     * 在集合后面添加数据
     * @param element
     * @return
     */
    public boolean add(E element) {
        //保证容量足够
        ensureCapacityInternal(size + 1);
        elementData[size++] = element;
        return true;
    }

    /**
     * 指定位置添加数据
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        checkIndex(index);
        ensureCapacityInternal(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = e;
        size++;
    }

    /**
     * 移除指定index的数值
     * @param index
     * @return
     */
    public E remove(int index) {
        checkIndex(index);
        E value = elementData(index);
        int num = size - index - 1;
        if (num > 0) {
            System.arraycopy(elementData,index+1,elementData,index,num);
        }
        elementData[size--] = null;
        return value;
    }

    /**
     * 移除对象
     * @param object
     * @return
     */
    public boolean remove(Object object) {
        if (object == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    fastRemove(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == object) {
                    fastRemove(i);
                    return true;
                }
            }
        }
        return false;
    }

    private void fastRemove(int index) {
        modCount++;
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        elementData[--size] = null; // 设置为null,方便GC回收
    }

    /**
     * 清空集合
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    /**
     * 往集合添加集合
     * @param e
     * @return
     */
    public boolean addAll(Collection<? extends E> e) {
        Object[] a = e.toArray();
        ensureCapacityInternal(size + a.length);
        System.arraycopy(a,0,elementData,size,a.length);
        size += a.length;
        return a.length != 0;
    }

    public boolean addAll(int index, Collection<? extends E> e) {
        checkIndex(index);
        Object[] a = e.toArray();
        int numSize = a.length;
        ensureCapacityInternal(size + numSize);
        int numMove = size - numSize;
        if (numMove > 0) {
            System.arraycopy(elementData,index,elementData,index+numSize,numMove);
        }
        System.arraycopy(a,0,elementData,index,numSize);
        size += numSize;
        return numSize != 0;
    }

    public void removeRange(int fromIndex, int toIndex) {
        int numMove = size - toIndex;
        System.arraycopy(elementData, toIndex, elementData, fromIndex, numMove);

        int newSize = size - (toIndex-fromIndex);
        for (int i = newSize; i < size; i++) {
            elementData[i] = null;
        }
        size = newSize;
    }

    Map<String, String> map = new HashMap<>();
}
