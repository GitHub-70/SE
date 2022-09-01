package com.tansun.basic;

/**
 * java间的传递是对象的内容传递，并非引用的地址传递
 * 所以不能改变对象的本身的地址值
 */
public class ValuePass {

    public static void main(String[] args) {

        String a = "a";
        String b = "b";
        String c = "c";
        // swap方法拿到的是副本，并没有将副本值 赋值 给原来的对象本身
        swap(a, b, c);
//        String temp = a;
//        a = b;
//        b = temp;
        System.out.println("a="+a +",b="+b + ",c="+c);

        System.out.println("-----------------------分割线--------------------");

        Person person1 = new Person("Angel", "22");
        Person person2 = new Person("banan","44");
        System.out.println("---打印出person1和person2的初始值---");
        System.out.println("person1:"+person1+",person2"+person2);
        System.out.println();
        swap1(person1, person2);
        System.out.println("---swap1方法结束后---");
        System.out.println("person1:"+person1+",person2"+person2);
        System.out.println();
        swap2(person1, person2);
        System.out.println("---swap2方法结束后---");
        System.out.println("person1:"+person1+",person2"+person2);

    }

    /**
     * 交换两个对象
     * @param person1
     * @param person2
     */
    private static void swap1(Person person1,Person person2){
        Person temp = person1;
        person1 = person2;
        person2 = temp;
        System.out.println("---调用swap1方法内部---");
        System.out.println("person1:"+person1+",person2"+person2);
        System.out.println();
    }


    /**
     * 交换两个对象的内容
     * @param person1
     * @param person2
     */
    private static void swap2(Person person1,Person person2){
        String tempName = person1.name;
        String tempAge = person1.age;
        person1.name = person2.name;
        person1.age = person2.age;
        person2.name = tempName;
        person2.age = tempAge;
        System.out.println("---调用swap2方法内部---");
        System.out.println("person1:"+person1+",person2"+person2);
        System.out.println();
    }


    /**
     * 该方法中被拷贝的对象进行了交换，但是并未改变原来的对象，
     * 而在方法结束后，拷贝的对象被释放掉了
     * @param testA
     * @param testB
     * @param testc
     */
    private static void swap(String testA, String testB, String testc){
        String temp = testA;
        testA = testB;
        testB = temp;
        testc = testc +"cc";
        System.out.println("testA="+testA +",testB="+testB);
    }

}

class Person{

    String name;
    String age;

    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}