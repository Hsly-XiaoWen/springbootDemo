package com.tuandai.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 肖文 on 2018/5/21
 * Java Class与反射相关的一些工具类
 */
public class ClassUtils {

    private static Logger logger = LoggerFactory.getLogger(ClassUtils.class);


    /**
     * 获取类加载器
     */
    public static ClassLoader classLoader;


    /**
     *  获取类加载器
     * @return
     */
    public static ClassLoader getContextClassLoader() {
        return classLoader != null ?
                classLoader : Thread.currentThread().getContextClassLoader();
    }
    /**
     * 加载指定的类
     *
     * @param className 需要加载的类
     * @return 加载后的类
     */
    public static Class loadClass(String className) {
        Class theClass = null;
        if (CheckUtils.isBlank(className)) {
            return theClass;
        }
        try {
            theClass = Class.forName(className);
        } catch (ClassNotFoundException e1) {
            logger.error("load class error:" + e1.getMessage());
            e1.printStackTrace();
        }
        return theClass;
    }

    /**
     * 获取指定类的全部属性字段
     *
     * @param className    需要获取的类名
     * @param extendsField 是否获取接口或父类中的公共属性
     * @return 属性字段数组
     */
    public static String[] getField(String className, boolean extendsField) {
        Class clazz = loadClass(className);
        if (clazz == null) {
            return null;
        }
        //获得某个类的所有的公共（public）的字段，包括父类中的字段
        Field[] fields = clazz.getFields();
        Set<String> set = new HashSet<>();
        if (fields != null) {
            for (Field f : fields) {
                set.add(f.getName());
            }
        }
        if (extendsField) {
            //获得某个类的所有声明的字段，即包括public、private和proteced，但是不包括父类的申明字段。
            Field[] topField = clazz.getDeclaredFields();
            if (topField != null) {
                for (Field f : topField) {
                    set.add(f.getName());
                }
            }
        }
        return set.toArray(new String[set.size()]);
    }

    /**
     * 获取类中的公共属性
     *
     * @param className    需要获取的类名
     * @param extendsField 是否获取接口或父类中的公共属性
     * @return 属性字段数组
     */
    public static String[] getPublicField(String className, boolean extendsField) {
        Class clazz = loadClass(className);
        if (clazz == null) {
            return null;
        }
        Set<String> set = new HashSet<>();
        Field[] fields = clazz.getDeclaredFields();
        if (fields != null) {
            for (Field f : fields) {
                String modifier = Modifier.toString(f.getModifiers());
                if (modifier.startsWith("public")) {
                    set.add(f.getName());
                }
            }
        }
        if (extendsField) {
            Field[] fieldz = clazz.getFields();
            if (fieldz != null) {
                for (Field f : fieldz) {
                    set.add(f.getName());
                }
            }
        }
        return set.toArray(new String[set.size()]);
    }

    /**
     * 获取类中定义的protected类型的属性字段
     *
     * @param className 需要获取的类名
     * @return protected类型的属性字段数组
     */
    public final static String[] getProtectedField(String className) {
        Class clazz = loadClass(className);
        if (clazz == null) {
            return null;
        }
        Set<String> set = new HashSet<>();
        Field[] fields = clazz.getDeclaredFields();
        if (fields != null) {
            for (Field f : fields) {
                String modifier = Modifier.toString(f.getModifiers());
                if (modifier.startsWith("protected")) {
                    set.add(f.getName());
                }
            }
        }
        return set.toArray(new String[set.size()]);
    }

    /**
     * 获取类中定义的private类型的属性字段
     *
     * @param className 需要获取的类名
     * @return private类型的属性字段数组
     */
    public static String[] getPrivateField(String className) {
        Class clazz = loadClass(className);
        if (clazz == null) {
            return null;
        }
        Set<String> set = new HashSet<>();
        Field[] fields = clazz.getDeclaredFields();
        if (fields != null) {
            for (Field f : fields) {
                String modifier = Modifier.toString(f.getModifiers());
                if (modifier.startsWith("private")) {
                    set.add(f.getName());
                }
            }
        }
        return set.toArray(new String[set.size()]);
    }

    /**
     * 获取对象的全部public类型方法
     *
     * @param className     需要获取的类名
     * @param extendsMethod 是否获取继承来的方法
     * @return 方法名数组
     */
    public final static String[] getPublicMethod(String className, boolean extendsMethod) {
        Class clazz = loadClass(className);
        if (clazz == null) {
            return null;
        }
        Method[] methods;
        if (extendsMethod) {
            methods = clazz.getMethods();
        } else {
            methods = clazz.getDeclaredMethods();
        }
        Set<String> set = new HashSet<>();
        if (methods != null) {
            for (Method f : methods) {
                String modifier = Modifier.toString(f.getModifiers());
                if (modifier.startsWith("public")) {
                    set.add(f.getName());
                }
            }
        }
        return set.toArray(new String[set.size()]);
    }


    /**
     * 获取对象的全部protected类型方法
     *
     * @param className     需要获取的类名
     * @param extendsMethod 是否获取继承来的方法
     * @return 方法名数组
     */
    public final static String[] getProtectedMethod(String className, boolean extendsMethod) {
        Class clazz = loadClass(className);
        if (clazz == null) {
            return null;
        }
        Method[] methods;
        if (extendsMethod) {
            methods = clazz.getMethods();
        } else {
            methods = clazz.getDeclaredMethods();
        }
        Set<String> set = new HashSet<>();
        if (methods != null) {
            for (Method f : methods) {
                String modifier = Modifier.toString(f.getModifiers());
                if (modifier.startsWith("protected")) {
                    set.add(f.getName());
                }
            }
        }
        return set.toArray(new String[set.size()]);
    }

    /**
     * 获取对象的全部private类型方法
     *
     * @param className 需要获取的类名
     * @return 方法名数组
     */
    public final static String[] getPrivateMethod(String className) {
        Class clazz = loadClass(className);
        if (clazz == null) {
            return null;
        }
        Method[] methods = clazz.getDeclaredMethods();
        Set<String> set = new HashSet<>();
        if (methods != null) {
            for (Method f : methods) {
                String modifier = Modifier.toString(f.getModifiers());
                if (modifier.startsWith("private")) {
                    set.add(f.getName());
                }
            }
        }
        return set.toArray(new String[set.size()]);
    }

    /**
     * 获取对象的全部方法
     *
     * @param className     需要获取的类名
     * @param extendsMethod 是否获取继承来的方法
     * @return 方法名数组
     */
    public final static String[] getMethod(String className, boolean extendsMethod) {
        Class clazz = loadClass(className);
        if (clazz == null) {
            return null;
        }
        Method[] methods;
        if (extendsMethod) {
            methods = clazz.getMethods();
        } else {
            methods = clazz.getDeclaredMethods();
        }
        Set<String> set = new HashSet<>();
        if (methods != null) {
            for (Method f : methods) {
                set.add(f.getName());
            }
        }
        return set.toArray(new String[set.size()]);
    }

}
