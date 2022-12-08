/**********************************************************\
 |                                                          |
 | Made by: Adittya Hasan (Frankenstein404)                 |
 |                                                          |
 | Android plugin loader for create online update based     |
 | loader                                                   |
 |                                                          |
 | Credits:                                                 |
 |      Frankenstein(Adittya)                               |
 |      Original X Ninja Cheats                             |
 |                                                          |
 | Code Authors: Adittya <theadittyaadi@icloud.com>         |
 | LastModified: Sep 10, 2022                               |
 |                                                          |
 \**********************************************************/
package com.skullshooter.ssloader.app.loader;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MachineLearning {
    /**
     * It invokes a method on an object, and returns the result
     *
     * @param clazz The class that contains the method you want to invoke.
     * @param obj the object to invoke the method on
     * @param methodName the name of the method you want to invoke
     * @param args the arguments to be passed to the method
     * @return The return value of the method.
     */
    static Object invokeMethod(Class<?> clazz, Object obj, String methodName, Object[] args, Class<?>... parameterTypes){
        try {
            Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
            method.setAccessible(true);
            return method.invoke(obj, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * It invokes a method on a class.
     *
     * @param className The class name of the object you want to invoke the method on.
     * @param obj the object on which to call the method
     * @param methodName The name of the method to be invoked.
     * @param args the parameters of the method
     * @return The return value of the method.
     */
    public static Object invokeMethod(String className, Object obj, String methodName, Object[] args, Class<?>... parameterTypes){
        try {
            if (parameterTypes == null) {
                parameterTypes = new Class[0];
            }
            if (args == null) {
                args = new Object[0];
            }
            Class<?> clazz = Class.forName(className);
            return invokeMethod(clazz, obj, methodName, args, parameterTypes);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get the value of a field from an object, even if it's private.
     *
     * @param clazz The class of the object you want to get the field value from.
     * @param obj the object to be operated on
     * @param fieldName The name of the field you want to get the value of.
     * @return The value of the field.
     */
    static Object getFieldValue(Class<?> clazz, Object obj, String fieldName){
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * It returns the value of the field with the given name in the given object
     *
     * @param className The class name of the object.
     * @param obj the object to get the field value from
     * @param fieldName The name of the field you want to get the value of.
     * @return The value of the field.
     */
    public static Object getFieldValue(String className, Object obj, String fieldName){
        try {
            Class<?> clazz = Class.forName(className);
            return getFieldValue(clazz, obj, fieldName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Set the value of the field named fieldName in the object obj to the value value.
     *
     * @param clazz The class of the object you want to modify.
     * @param obj the object to be modified
     * @param fieldName The name of the field you want to set the value of.
     * @param value the value to be set
     */
    static void setFieldValue(Class<?> clazz, Object obj, String fieldName, Object value){
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the value of the field with the given name in the given object to the given value.
     *
     * @param className The class name of the object.
     * @param obj the object whose field you want to modify
     * @param fieldName The name of the field you want to set the value of.
     * @param value the value to be set
     */
    public static void setFieldValue(String className, Object obj, String fieldName, Object value){
        try {
            Class<?> clazz = Class.forName(className);
            setFieldValue(clazz, obj, fieldName, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
