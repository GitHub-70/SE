package com.tansun.annotation.validateannotation;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @ClassName BaseEntity
 * @Description 利用自定义注解 @ParamsCheck 实现赋予 entity 类的属性默认值
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/9 10:00
 * @Copyright © 2020 阿里巴巴
 */
public class BaseEntity implements Serializable {

    /* *
     * @Author 吴槐
     * @Description 利用反射判断 属性是否标记  @ParamsCheck 注解
     *              如果标记，则判空处理，并赋予默认值
     * @Date 10:06 2022/9/9
     * @Param 
     * @return void
     *       
     **/
    public BaseEntity validate() throws IllegalAccessException, InstantiationException {
        Field[] fields = this.getClass().getDeclaredFields();
        BaseEntity baseEntity = this.getClass().newInstance();
        for (Field field : fields){
            ParamsCheck paramsCheck = field.getAnnotation(ParamsCheck.class);
            // 未标记该注解，跳出当前循环
            if (null == paramsCheck){
               continue;
            }
            // 检验标记为True 对属性进判空
            if (paramsCheck.require()){
                // 获取当前属性的值
                field.setAccessible(true);

                Object val = field.get(baseEntity);
                if (null == val){
                    Class<?> fieldType = field.getType();
                    String classObj = fieldType.getName();
                    String className = classObj.substring(classObj.lastIndexOf(".") + 1);
                    if("String".equals(className)){
                        field.set(baseEntity," ");
                    } else if("Integer".equals(className) || "int".equals(className)){
                        field.set(baseEntity,0);
                    } else if("BigDecimal".equals(className)){
                        field.set(baseEntity, new BigDecimal(0));
                    } else if("Date".equals(className)){
                        field.set(baseEntity, new Date());
                    } else if("List".equals(className)){
                        field.set(baseEntity, new ArrayList<>());
                    } else if("Map".equals(className)){
                        field.set(baseEntity, new HashMap<>());
                    } else if("Double".equals(className) || "double".equals(className)){
                        field.set(baseEntity, new Double(0.0));
                    } else if("Long".equals(className) || "long".equals(className)){
                        field.set(baseEntity, new Long(0));
                    } else if("Float".equals(className) || "float".equals(className)){
                        field.set(baseEntity, new Float(0.0));
                    } else if("byte".equals(className) || "Byte".equals(className)){
                        field.set(baseEntity, new Byte[1]);
                    } else if("short".equals(className) || "Short".equals(className)){
                        field.set(baseEntity, new Short[2]);
                    } else {
                        field.set(baseEntity, fieldType.getClass().newInstance());
                    }

                }

            }
        }
        return baseEntity;
    }
}
