package com.johnny.reflection.reflection05;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 通过反射获取泛型
 *
 * @author johnnyhao
 */
public class Reflection05 {

    public Map<String, List<String>> test(Map<String, Object> map, List<String> list) {
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        // 获取方法
        Method method = Class.forName("com.johnny.reflection.reflection05.Reflection05").getMethod("test", Map.class, List.class);

        // 获取方法带泛型的参数
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        System.out.println(Arrays.toString(genericParameterTypes));

        for (Type genericParameterType : genericParameterTypes) {
            if (genericParameterType instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) genericParameterType).getActualTypeArguments();
                System.out.println(Arrays.toString(actualTypeArguments));
            }
        }

        // 获取方法带泛型的返回类型
        Type genericReturnType = method.getGenericReturnType();
        System.out.println(genericReturnType);

        if (genericReturnType instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
            System.out.println(Arrays.toString(actualTypeArguments));
        }
    }
}