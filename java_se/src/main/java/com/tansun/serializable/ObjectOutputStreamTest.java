package com.tansun.serializable;


import java.io.*;

public class ObjectOutputStreamTest {
    public static void main(String[] args) throws IOException {
        //
        OutputStream outputStream = new FileOutputStream(new File("E:\\1.txt"));
        //
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        person person = new person();
        objectOutputStream.writeObject(person);
        objectOutputStream.close();
    }
}

class person implements Serializable{

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
