package com.tansun.basic.innerclass;

/**
 * 利用内部类可以间接性的实现 多继承
 */

public class MainExample {

    /**
     * 内部类1继承ExampleOne
     */
    private class InnerOne extends ExampleOne {

        public String name() {

            return super.name();

        }

    }

    /**
     * 内部类2继承ExampleTwo
     */
    private class InnerTwo extends ExampleTwo {

        public int age() {

            return super.age();

        }

    }

    public String name() {

        return new InnerOne().name();

    }

    public int age() {

        return new InnerTwo().age();

    }

    public static void main(String[] args) {

        MainExample mi = new MainExample();

        System.out.println("姓名:" + mi.name());

        System.out.println("年龄:" + mi.age());

    }

}


class ExampleOne {

    public String name() {

        return "inner";

    }

}

class ExampleTwo {

    public int age() {

        return 25;

    }

}