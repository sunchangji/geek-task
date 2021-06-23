package com.suncj.geektask.jmv01;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * @Classname XlClassLoader
 * @Description 自定义一个 Classloader加载一个 Hello.xlass 文件，执行 hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件
 * @Date 2021/6/23 2:47 下午
 * @Created by sunchangji
 */
public class XlClassLoader extends ClassLoader{
    //字节码类名
    private static final String className = "Hello";
    //方法名
    private static final String methodName = "hello";

    public static void main(String[] args) throws Exception {
        // 创建类加载器
        ClassLoader classLoader = new XlClassLoader();
        // 加载相应的类
        Class<?> clazz = classLoader.loadClass(className);
//        // 看看里面有些什么方法
//        for (Method m : clazz.getDeclaredMethods()) {
//            System.out.println(clazz.getSimpleName() + "." + m.getName());
//        }
        // 创建对象
        Object instance = clazz.getDeclaredConstructor().newInstance();
        // 调用实例方法
        Method method = clazz.getMethod(methodName);
        method.invoke(instance);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try(InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(name + ".xlass")) {
            assert inputStream != null;
            int length = inputStream.available();
            byte[] byteArray = new byte[length];
            int line = inputStream.read(byteArray);
            System.out.println(String.format("line:%s",line));
            // 转换
            byte[] classBytes = decodeClazz(byteArray);
            // 通知底层定义这个类
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
        }
    }

    /**
     * 解码
     * @param byteArray
     * @return
     */
    private byte[] decodeClazz(byte[] byteArray){
        byte[] decodeArray = new byte[byteArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            decodeArray[i] = (byte) (255 - byteArray[i]);
        }
        return decodeArray;
    }
}
