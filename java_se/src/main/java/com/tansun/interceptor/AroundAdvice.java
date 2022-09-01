package com.tansun.interceptor;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 利用的是spring-aop包 中的 MethodInterceptor
 */

public class AroundAdvice implements MethodInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AroundAdvice.class);

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        // 获取目标类
        Class<?> declaringClass = methodInvocation.getMethod().getDeclaringClass();
        // 获取目标方法
        String methodName = methodInvocation.getMethod().getName();
        logger.info("AOP target Method:[{},{}]",declaringClass,methodName);


        // 获取目标方法参数
        Object[] arguments = methodInvocation.getArguments();
        if (null != arguments && arguments.length > 0){
            StringBuffer args = new StringBuffer("AOP target Method args:[");
            for (Object obj : arguments) {
                String arg = getArgsValue(args, obj);
                args.append(obj.getClass() + ":" + arg + "\0");
            }
            args.append("]");
            logger.info(args.toString());
        }

        //执行目标方法
        methodInvocation.proceed();

        return null;
    }

    /**
     * 获取参数类型及参数值
     * @param args
     * @param obj
     * @return
     */
    private String getArgsValue(StringBuffer args, Object obj){
        if (null ==args || null == obj)
            new IllegalArgumentException("AroundAdvice Class IllegalArgumentException ");
        if (obj instanceof String){
            return (String)obj;
        }
        if (obj instanceof Byte){
            return String.valueOf(((Byte) obj).byteValue());
        }
        if (obj instanceof Short){
            return String.valueOf(((Short) obj).shortValue());
        }
        if (obj instanceof Integer){
            return String.valueOf(((Integer) obj).intValue());
        }
        if (obj instanceof Long){
            return String.valueOf(((Long) obj).longValue());
        }
        if (obj instanceof Float){
            return String.valueOf(((Float) obj).floatValue());
        }
        if (obj instanceof Double){
            return String.valueOf(((Double) obj).doubleValue());
        }
        if (obj instanceof Character){
            return String.valueOf(((Character) obj).charValue());
        }
        if (obj instanceof Boolean){
            return String.valueOf(((Boolean) obj).booleanValue());
        }

        return obj.toString();
    }

}
