package com.company.exception;

public class MyIllegalArgumentException extends RuntimeException {
    public MyIllegalArgumentException() {
        System.out.println("Передан \"null\" в качестве аргумента метода");
    }
}
