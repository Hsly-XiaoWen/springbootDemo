package com.juemuren.invocation;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class JdkProxyUtils {

    public static Object newProxyInstance(Object o) throws Exception {
        StringBuilder builder = new StringBuilder();

        Class<?> clazz = o.getClass().getInterfaces()[0];
        String name = clazz.getSimpleName();

        builder.append("package com.juemuren.invocation;");
        builder.append("import ").append(clazz.getName()).append(";");

        String main = "public class $Proxy implements " + name + " {" +
                "private " + name + " target;";
        builder.append(main);

        String struct = "public $Proxy(" + name + " target) {"
                + "this.target = target;}";
        builder.append(struct);

        Method method = clazz.getMethod("sayHello");
        String methodName = method.getName();
        String methodType = method.getReturnType().getSimpleName();

        Class<?>[] parameterTypes = method.getParameterTypes();

        String params = "";
        String params1 = "";
        for (int i = 0; i < parameterTypes.length; i++) {
            String paramType = parameterTypes[i].getSimpleName();
            if (i == parameterTypes.length - 1) {
                params += paramType + " p" + i;
                params1 += "p" + i;
            } else {
                params += paramType + " p" + i + ",";
                params1 += "p" + i + ",";
            }
        }

        String methodContent = "public " + methodType + " " + methodName + "(" + params + ")" +
                "{ System.out.println(\"Hello java\");" +
                "target." + methodName + "(" + params1 + ");" +
                "return \"xiaowen\";}}";

        builder.append(methodContent);

        File file = new File("D:\\$Proxy.java");
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter writer = new FileWriter(file);
        writer.write(builder.toString());
        writer.flush();
        writer.close();

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);

        Iterable<? extends JavaFileObject> units = manager.getJavaFileObjects(file);
        JavaCompiler.CompilationTask t = compiler.getTask(null, manager, null, null, null, units);
        t.call();
        manager.close();

        URL[] urls = new URL[]{new URL("file:D:\\\\")};
        URLClassLoader classLoader = new URLClassLoader(urls);
        Class<?> clazz1 = classLoader.loadClass("com.juemuren.invocation.$Proxy");
        Constructor<?> constructor = clazz1.getConstructor(clazz);
        Object result = constructor.newInstance(o);
        return result;
    }

    class MyClassLoader extends ClassLoader {
        private File classPathfile;

        public MyClassLoader() {
            String classpth = MyClassLoader.class.getResource("").getPath();
            classPathfile = new File(classpth);
        }

        @Override
        public Class<?> findClass(String name) throws ClassNotFoundException {
            String className = MyClassLoader.class.getPackage().getName() + "." + name;
            if (classPathfile != null) {
                File file = new File(classPathfile, name + ".class");
                FileInputStream fileInputStream = null;
                ByteArrayOutputStream outputStream = null;
                try {
                    fileInputStream = new FileInputStream(file);
                    outputStream = new ByteArrayOutputStream();
                    byte[] buff = new byte[1024];
                    int len;
                    while ((len = fileInputStream.read(buff)) != -1) {
                        outputStream.write(buff, 0, len);
                    }
                    return defineClass(className, outputStream.toByteArray(), 0, outputStream.size());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (null != fileInputStream) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    if (null != outputStream) {
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }
            return null;
        }
    }
}
