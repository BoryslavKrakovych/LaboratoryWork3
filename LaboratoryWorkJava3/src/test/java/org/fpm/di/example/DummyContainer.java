package org.fpm.di.example;

import org.fpm.di.Container;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public class DummyContainer implements Container {
    DummyBinder dummyBinder;
    public DummyContainer(DummyBinder dummyBinder) {
        this.dummyBinder = dummyBinder;
    }
    @Override
    public <T> T getComponent(Class<T> clazz) {
        T type = null;
        T Instance = dummyBinder.getInstance(clazz);
        if(Instance != null) {
            return Instance;
        }
        Class<? extends T> Extended = dummyBinder.getImplementation(clazz);
        if(Extended!= null){
            return getComponent((Class<T>)dummyBinder.Implementaion.get(clazz));}
        for(Constructor<?> constructor: clazz.getConstructors()) {
            if (constructor.isAnnotationPresent(Inject.class)) {
                try {
                    type = (T) constructor.newInstance(getComponent(constructor.getParameterTypes()[0]));
                } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            if (type == null) {
                try {
                    type = clazz.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            if (clazz.isAnnotationPresent(Singleton.class)) {
                dummyBinder.bind(clazz, type);
            }
        }
        return type;
        }
}
