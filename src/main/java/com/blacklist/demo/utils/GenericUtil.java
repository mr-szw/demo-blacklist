package com.blacklist.demo.utils;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author lili
 */
public class GenericUtil {

    private GenericUtil() {
        throw new IllegalStateException();
    }

    public static Class<?> getGenericType(Type type) {
        if (type instanceof Class) {
            return (Class<?>) type;
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterized = (ParameterizedType) type;
            return (Class<?>) parameterized.getRawType();
        }
        if (type instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType) type).getGenericComponentType();
            return Array.newInstance(getGenericType(componentType), 0).getClass();
        }
        if (type instanceof TypeVariable) {
            TypeVariable variable = (TypeVariable) type;
            return getGenericType(variable.getBounds()[0]);
        }
        if (type instanceof WildcardType) {
            WildcardType wildcard = (WildcardType) type;
            return getGenericType(wildcard.getUpperBounds()[0]);
        }
        // never reach here
        throw new UnsupportedOperationException();
    }

    public static Type[] getVariableType(Class<?> clazz, Class<?> target) {
        if (!target.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException();
        }
        return getVariableType0(clazz, target);
    }

    private static Type[] getVariableType0(Class<?> clazz, Class<?> target) {
        // none
        TypeVariable<?>[] vars = target.getTypeParameters();
        if (vars.length == 0) {
            return new Type[0];
        }

        // all parameterized types
        List<ParameterizedType> superGenerics = new LinkedList<>();

        Type superclass = clazz.getGenericSuperclass();
        if (superclass != null) {
            if (superclass instanceof ParameterizedType) {
                superGenerics.add((ParameterizedType) superclass);
            }
        }
        Type[] superInterfaces = clazz.getGenericInterfaces();
        for (Type eachInterface : superInterfaces) {
            if (eachInterface instanceof ParameterizedType) {
                superGenerics.add((ParameterizedType) eachInterface);
            }
        }

        // resolve target
        for (ParameterizedType parameterized : superGenerics) {
            Class<?> superInterface = getGenericType(parameterized);
            if (target.isAssignableFrom(superInterface)) {
                return getVariableTypeImpl(parameterized, target);
            }
        }

        // raw type
        Type[] ret = new Type[vars.length];
        Arrays.fill(ret, Object.class);
        return ret;
    }

    private static Type[] getVariableTypeImpl(ParameterizedType type, Class<?> target) {
        Class<?> clazz = getGenericType(type);
        if (target == clazz) {
            return type.getActualTypeArguments();
        }

        // 递归
        Type[] types = getVariableType0(clazz, target);
        return resolveVariableType(type, types);
    }

    private static Type[] resolveVariableType(ParameterizedType parameterized, Type[] types) {
        Class<?> clazz = getGenericType(parameterized);
        TypeVariable<?>[] parameters = clazz.getTypeParameters();
        Type[] arguments = parameterized.getActualTypeArguments();

        // 泛型参数
        Map<TypeVariable, Type> dict = new HashMap<>();
        for (int i = 0; i < parameters.length; i++) {
            dict.put(parameters[i], arguments[i]);
        }

        // 解析为实际类型
        Type[] ret = new Type[types.length];
        for (int i = 0; i < types.length; i++) {
            Type type = types[i];
            ret[i] = type;
            if (type instanceof TypeVariable) {
                ret[i] = dict.get(type);
            }
        }

        return ret;
    }

}
