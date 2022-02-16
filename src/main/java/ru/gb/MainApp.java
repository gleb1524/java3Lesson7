package ru.gb;

public class MainApp {
    public static void main(String[] args) {

    }
    @BeforeSuite
    public static void method1(){
        System.out.println("method1");
    }

    @AfterSuite
    public static void method2(){
        System.out.println("method2");
    }

    @Test(value = 8)
    public static void method3(){
        System.out.println("method3");
    }

    @Test(value = 7)
    public static void method4(){System.out.println("method4");}
    @Test(value = 9)
    public static void method5(){System.out.println("method5");}
}
