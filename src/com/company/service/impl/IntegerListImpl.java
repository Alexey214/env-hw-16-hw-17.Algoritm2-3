package com.company.service.impl;

import com.company.exception.ElementNotFound;
import com.company.exception.MyIllegalArgumentException;
import com.company.exception.MyIndexOfBoundExceptions;
import com.company.service.IntegerList;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class IntegerListImpl implements IntegerList {

    private Integer[] arr;

    public IntegerListImpl() {
        int size = 3;
        this.arr = new Integer[size];
    }

    @Override
    public Integer add(Integer item) {
        ifNull(item);
        grow();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                continue;

            } else {
                arr[i] = item;
                break;
            }
        }
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        ifNull(item);
        ifIndexOfBoundExceptions(index);
        Integer temp = item;
        if (arr[index] == null) {
            arr[index] = item;
            return item;
        } else {
            Integer buffer;
            grow();
            for (int i = index; i < arr.length; i++) {
                buffer = arr[i];
                arr[i] = item;
                item = buffer;
            }
        }
        return temp;
    }

    @Override
    public Integer set(int index, Integer item) {
        ifNull(item);
        ifIndexOfBoundExceptions(index);
        return arr[index] = item;
    }

    @Override
    public Integer removeItem(Integer item) {
        arr[find(item)] = null;
        return item;
    }

    @Override
    public Integer removeIndex(int index) {
        ifIndexOfBoundExceptions(index);
        if (arr[index] == null) {
            return null;
        }
        arr[index] = null;
        return arr[index];
    }

    @Override
    public boolean contains(int[] arr, Integer item) {
        ifNull(item);
        int[] arrTmp = sortInsertion(toArray());
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == arr[mid]) {
                return true;
            }

            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        int index;
        try {
            index = find(item);
        } catch (ElementNotFound e) {
            return -1;
        }
        return index;
    }

    @Override
    public int lastIndexOf(Integer item) {
        ifNull(item);
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == null) {
                continue;
            } else if (item.equals(arr[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        ifIndexOfBoundExceptions(index);
        return arr[index];
    }

    //За такие методы людей должны сжигать на костре, но я ничего лучше не смог придумать
    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new MyIllegalArgumentException();
        }
        if (this == otherList) {
            return true;
        }

        //из старого массива получаем новый и сразу очищаем его от ненужного мусора, а также сортируем
        String[] oldArr = this.getAll().replace("[", "")
                .replace("null", "")
                .replace("]", "")
                .replace(",", "")
                .split(" ");
        Arrays.sort(oldArr);

        //создаём переменную, равную "количеству заполненных значений и следом создаём новый массив с данным размером
        int tmpCount1 = 0;
        for (String s : oldArr) {
            if (!s.equals("")) {
                tmpCount1++;
            }
        }
        String[] correctOldArr = new String[tmpCount1];

        //копируем из старого массива данные в новый, избегая теперь пустых ячеек
        int stepUndo1 = 0;
        for (int i = 0; i < oldArr.length; i++) {
            if (oldArr[i].equals("")) {
                stepUndo1++;
                continue;
            }
            correctOldArr[i - stepUndo1] = oldArr[i];
        }

        //повторяем все аналогичные операции со вторым массивом (с которым сравниваем)
        String[] newArr = otherList.getAll().replace("[", "")
                .replace("]", "")
                .replace(",", "")
                .replace("null", "")
                .split(" ");
        Arrays.sort(newArr);

        int tmpCount2 = 0;
        for (String s : newArr) {
            if (!s.equals("")) {
                tmpCount2++;
            }
        }
        String[] correctNewArr = new String[tmpCount2];

        int stepUndo2 = 0;
        for (int i = 0; i < newArr.length; i++) {
            if (newArr[i].equals("")) {
                stepUndo2++;
                continue;
            }
            correctNewArr[i - stepUndo2] = newArr[i];
        }

        //сравниваем равны ли наши вновь созданные массивы, имеющие общий вид и порядок
        int count = 0;
        if (correctOldArr.length == correctNewArr.length) {
            for (int i = 0; i < correctOldArr.length; i++) {
                if (correctOldArr[i].equals(correctNewArr[i])) {
                    count++;
                }
            }
        }

        //все шаги прошли успешно, значит массивы равны
        if (count == correctOldArr.length) {
            return true;
        }

        return false;
    }

    @Override
    public int size() {
        int elem = 0;
        for (Integer integer : arr) {
            if (integer == null) {
                continue;
            } else {
                elem++;
            }
        }
        return elem;
    }

    @Override
    public boolean isEmpty() {
        int elem = 0;
        for (Integer integer : arr) {
            if (integer == null) {
                elem++;
                continue;
            }
        }
        return elem == arr.length;
    }

    @Override
    public void clear() {
        Arrays.fill(arr, null);
    }

    @Override
    public int[] toArray() {
        int[] correctArr = new int[size()];
        int stepUndo = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                stepUndo++;
                continue;
            }
            correctArr[i - stepUndo] = arr[i];
        }
        return correctArr;
    }

    @Override
    public Integer[] sort(int[] arr) {
        int[] intArray = sortInsertionWithRecursion(toArray(), arr.length);  //sortBubble - 14680мс, sortSelection - 3709мс, sortInsertion - 872мс
        Integer[] integerArray = IntStream.of(intArray).boxed().toArray(Integer[]::new);
        return integerArray;
    }

    //Служебные методы:

    private Integer[] grow() {
        if (arr.length == size()) {
            int size = (int) Math.round(size() * 1.5);
            arr = Arrays.copyOf(arr, size);
        }
        return arr;
    }

    private Integer ifNull(Integer item) {
        if (item != null) {
            return item;
        } else {
            throw new MyIllegalArgumentException();
        }
    }

    private int ifIndexOfBoundExceptions(int index) {
        if (index < arr.length && index >= 0) {
            return index;
        } else {
            throw new MyIndexOfBoundExceptions();
        }
    }

    @Override
    public int[] testSort() {
        int[] bigArr = generateRandomArray();
        return sortInsertionWithRecursion(bigArr, bigArr.length);
    }

    @Override
    //Создал дополнительный метод для проверки содержимого массива, чтобы проще контролировать процесс
    public String getAll() {
        return Arrays.toString(arr);
    }

    private int find(Integer item) {
        ifNull(item);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                continue;
            } else if (item.equals(arr[i])) {
                return i;
            }
        }
        throw new ElementNotFound();
    }

    private int[] generateRandomArray() {
        Random random = new Random();
        int[] arr = new int[10_000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100_000) + 1;
        }
        return arr;
    }

    private void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    private int[] sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
        return arr;
    }

    private int[] sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
        return arr;
    }

    private int[] sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
        return arr;
    }

    private int[] sortInsertionWithRecursion(int[] arr, int length) {
        if (length <= 1)
            return arr;
        sortInsertionWithRecursion(arr, length - 1);
        int last = arr[length - 1];
        int j = length - 2;
        while (j >= 0 && arr[j] > last) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = last;
        return arr;
    }

}
