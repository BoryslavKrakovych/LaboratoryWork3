package org.fpm.di.example;

import javax.inject.Inject;

public class Test2 {
    private final ExtendedTest Extendedtesting;

    @Inject
    public Test2(ExtendedTest Extendedtest) {
        this.Extendedtesting = Extendedtest;
    }


    public ExtendedTest getExtendedtesting() {return Extendedtesting;}
}
