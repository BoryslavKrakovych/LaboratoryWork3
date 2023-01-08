package org.fpm.di.example;

import org.fpm.di.Container;
import org.fpm.di.Environment;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

public class Example {

    private Container container;

    @Before
    public void setUp() {
        Environment env = new DummyEnvironment();
        container = env.configure(new MyConfiguration());
    }

    @Test
    public void shouldInjectSingleton() {
        assertSame(container.getComponent(MySingleton.class), container.getComponent(MySingleton.class));
    }

    @Test
    public void shouldInjectPrototype() {
        assertNotSame(container.getComponent(MyPrototype.class), container.getComponent(MyPrototype.class));
    }

    @Test
    public void shouldBuildInjectionGraph() {
        /*
        binder.bind(A.class, B.class);
        binder.bind(B.class, new B());
        */
        final B bAsSingleton = container.getComponent(B.class);
        assertSame(container.getComponent(A.class), bAsSingleton);
        assertSame(container.getComponent(B.class), bAsSingleton);
    }

    @Test
    public void shouldBuildInjectDependencies() {
        final UseA hasADependency = container.getComponent(UseA.class);
        assertSame(hasADependency.getDependency(), container.getComponent(B.class));
    }

    @Test
    public void shouldBuildInjectDependencies1(){
        final Test1 testing1 = container.getComponent(Test1.class);
        assertSame(container.getComponent(Test1.class), testing1);
    }

    @Test
    public void shouldBuildInjectDependencies2(){
        final Test2 testing1 = container.getComponent(Test2.class);
        assertSame(testing1.getExtendedtesting(),container.getComponent(Test1.class));
    }


    @Test
    public void shouldBuildInjectDependencies3(){
        final Test1 testing1 = container.getComponent(Test1.class);
        final Test1 testing2 = container.getComponent(Test1.class);
        assertSame(testing1, testing2);
    }

    @Test
    public void shouldBuildInjectDependencies4(){
        final Test1 testing1 = container.getComponent(Test1.class);
        assertSame(container.getComponent(ExtendedTest.class), testing1);
    }
    

}
