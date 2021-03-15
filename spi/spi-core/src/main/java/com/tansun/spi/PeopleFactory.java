package com.tansun.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class PeopleFactory {

    public void invoke(){
        // 通过ServiceLoader服务加载器 加载。。。
        ServiceLoader<People> service = ServiceLoader.load(People.class);
        // 通过迭代器迭代
        Iterator<People> peopleIterator = service.iterator();
        boolean notFound = true;
        while (peopleIterator.hasNext()){
            if (notFound){
                // 让布尔值notFound只修改一次
                notFound = false;
            }
            People people = peopleIterator.next();
            // 调用目标方法
            String people1 = people.getPeople();
            String peopleType = people.getPeopleType();
            System.out.println("spi-core----目标接口实现方法::getPeople()=="+people1);
            System.out.println("spi-core----目标接口实现方法::peopleType()=="+peopleType);
        }
        if (notFound){
            throw new RuntimeException("未找到spi-interface具体实现");
        }
    }
}
