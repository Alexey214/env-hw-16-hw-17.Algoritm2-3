package com.company.exception;

public class ElementNotFound extends RuntimeException {
    public ElementNotFound() {
        System.out.println("Запрашиваемый элемент отсутствует в базе данных");
    }
}
