package ru.gb;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class MyTests {
    public static void main(String[] args){
        start(MainApp.class);
    }
    public static void start(Class c) {
        Method[] methods = c.getDeclaredMethods();
        int afterSuiteCount = 0;
        int beforeSuiteCount = 0;
        for (Method method : methods) {
            if (method.getAnnotation(AfterSuite.class) != null) {
                afterSuiteCount++;
            }
            if (method.getAnnotation(BeforeSuite.class) != null) {
                beforeSuiteCount++;
            }
        }
        if (afterSuiteCount != 1 || beforeSuiteCount != 1) {
            throw new RuntimeException("Обязательно использовать AfterSuite и BeforeSuite один раз");
        } else {
            List<Method> listDeclaredMethods = Arrays.asList(methods);
            Map<Integer, Method> sortMap = new TreeMap<>();
            for (Method listDeclaredMethod : listDeclaredMethods) {
                if (listDeclaredMethod.getAnnotation(Test.class) != null) {
                    sortMap.put(listDeclaredMethod.getAnnotation(Test.class).value(), listDeclaredMethod);
                }
                if (listDeclaredMethod.getAnnotation(AfterSuite.class) != null) {
                    System.out.println(listDeclaredMethod.getAnnotation(AfterSuite.class).value());
                    sortMap.put(listDeclaredMethod.getAnnotation(AfterSuite.class).value(), listDeclaredMethod);
                }
                if (listDeclaredMethod.getAnnotation(BeforeSuite.class) != null) {
                    System.out.println(listDeclaredMethod.getAnnotation(BeforeSuite.class).value());
                    sortMap.put(listDeclaredMethod.getAnnotation(BeforeSuite.class).value(), listDeclaredMethod);
                }

            }

            for (Method value : sortMap.values()) {
                try {
                    value.invoke(c);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}
