package com.company;

import com.company.service.IntegerList;
import com.company.service.impl.IntegerListImpl;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        IntegerList sl = new IntegerListImpl();
        System.out.println("Печатаем созданный массив:");
        System.out.println("Получаем: " + sl.getAll() + "\n");

        System.out.println("Добавляем методом элементы:");
        System.out.println(sl.add(123));
        System.out.println(sl.add(456));
        System.out.println(sl.add(789));
        System.out.println(sl.add(101112));
        System.out.println("Получаем: " + sl.getAll() + "\n");

        System.out.println("Добавляем методом элементы со сдвигом вправо:");
        System.out.println(sl.add(0, 131415));
        System.out.println("Получаем: " + sl.getAll() + "\n");

        System.out.println("Заменяем значение одного индекса:");
        System.out.println(sl.set(0, 0));
        System.out.println("Получаем: " + sl.getAll() + "\n");

        System.out.println("Удаляем значение по содержимому:");
        System.out.println(sl.removeItem(789));
        System.out.println("Получаем: " + sl.getAll() + "\n");

        System.out.println("Удаляем значение по индексу:");
        System.out.println(sl.removeIndex(0));
        System.out.println("Получаем: " + sl.getAll() + "\n");

        System.out.println("Проверяем существует ли элемент:");
        System.out.println(sl.contains(sl.toArray(), Integer.valueOf(456)));
        System.out.println("Получаем: " + sl.getAll() + "\n");

        System.out.println("Поиск элемента с начала:");
        System.out.println(sl.indexOf(0));
        System.out.println("Получаем: " + sl.getAll() + "\n");

        System.out.println("Поиск элемента с конца:");
        sl.add(0, 0);
        System.out.println(sl.lastIndexOf(0));
        System.out.println("Получаем: " + sl.getAll() + "\n");

        System.out.println("Получаем элемент по индексу:");
        System.out.println(sl.get(1));
        System.out.println("Получаем: " + sl.getAll() + "\n");

        System.out.println("Создаём новый объект и добавляем в него элементы:");
        IntegerList slNew = new IntegerListImpl();
        System.out.println(slNew.add(0));
        System.out.println(slNew.add(123));
        System.out.println(slNew.add(456));
        System.out.println(slNew.add(101112));
        System.out.println("Проверяем равны ли объекты:");
        System.out.println(sl.equals(slNew));
        slNew = sl;
        System.out.println(sl.equals(slNew) + "\n");

        System.out.println("Фактическое количество элементов в списке:");
        System.out.println(sl.size() + "\n");

        System.out.println("Возвращаем true, если элементов в списке нет:");
        System.out.println(sl.isEmpty());
        IntegerList qwerty = new IntegerListImpl();
        System.out.println(qwerty.isEmpty() + "\n");

        System.out.println("Удаляем все элементы из списка:");
        sl.clear();
        System.out.println("Получаем: " + sl.getAll() + "\n");

        System.out.println("Получаем массив из списка:");
        sl.add(7);
        System.out.println(Arrays.toString(sl.toArray()) + "\n");

        System.out.println("Добавляем методом элементы:");
        System.out.println(sl.add(2));
        System.out.println(sl.add(4));
        System.out.println(sl.add(1));
        System.out.println(sl.add(3));
        System.out.println(sl.add(5));
        System.out.println(sl.add(8));
        System.out.println(sl.add(6));
        System.out.println(sl.add(9));
        System.out.println(sl.add(10));
        System.out.println(sl.add(21));
        long start = System.currentTimeMillis();
        Integer[] integers = sl.sort(sl.toArray());
        System.out.println("Время на сортировку: " + (System.currentTimeMillis() - start));
        System.out.println("Получаем: " + Arrays.toString(integers) + "\n");

        System.out.println("Проверяем сортировку:");
        long start1 = System.currentTimeMillis();
        sl.testSort();
        System.out.println("Время на сортировку: " + (System.currentTimeMillis() - start1) + "\n");

        System.out.println("Проверяем сортировку + бинарный поиск:");
        long start2 = System.currentTimeMillis();
        System.out.println(sl.contains(sl.testSort(), Integer.valueOf(100_000)));
        System.out.println("Время на сортировку: " + (System.currentTimeMillis() - start2) + "\n");
    }

}
