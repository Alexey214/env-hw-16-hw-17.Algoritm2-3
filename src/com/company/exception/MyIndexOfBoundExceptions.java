package com.company.exception;

public class MyIndexOfBoundExceptions extends RuntimeException {
    public MyIndexOfBoundExceptions() {
        System.out.println("Указанный индекс выходит за пределы фактического количества элементов или массива");
    }
}
