package org.fpm.di.example;

import org.fpm.di.Binder;

import javax.inject.Singleton;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class DummyBinder implements Binder {
    public ArrayList<Class<?>> Classes= new ArrayList<>();
    public HashMap<Class<?>, Class<?>> Implementaion=new HashMap<>();
    public HashMap<Class<?>, Object> Instance=new HashMap<>();


    @Override
    public <T> void bind(Class<T> clazz) {Classes.add(clazz);}


    @Override
    public <T> void bind(Class<T> clazz, Class<? extends T> implementation) {Implementaion.put(clazz, implementation);}

    @Override
    public <T> void bind(Class<T> clazz, T instance) {Instance.put(clazz, instance);}

    public<T> Class<? extends T> getImplementation(Class<T> baseClass){
        if(Implementaion.containsKey(baseClass))
            return ((Class<T>)Implementaion.get(baseClass));
        return null;
    }

    public<T> T getInstance(Class<T> baseClass){
        if(Instance.containsKey(baseClass))
            return (T) Instance.get(baseClass);
        return null;
    }
}
