package com.company;

import java.util.Arrays;
import java.util.Objects;

public class IntegerListImpl implements IntegerList {
    private Integer[] storage;
    private int size;

    public IntegerListImpl() {
        this.storage = new Integer[10];
    }

    @Override
    public Integer add(Integer item) {
        if (size == storage.length) {
            storage = Arrays.copyOf(storage, (storage.length + (storage.length / 2)));
        }
        storage[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        validateIndex(index);
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = item;
        size++;
        return item;
    }


    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        storage[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        int removeItem = indexOf(item);
        if (removeItem != -1) {
            System.arraycopy(storage, removeItem + 1, storage, removeItem, size - 1);
            size--;
            return item;
        }
        throw new ItemNotFoundException();
    }

    @Override
    public Integer remove(int index) {
        if (index >= 0 && index <= size - 1) {
            Integer removeItem = storage[index];
            storage[index] = null;
            if (index != size - 1) {
                System.arraycopy(storage, index + 1, storage, index, size - index);
                size--;
                return removeItem;
            }
        }
        throw new ItemNotFoundException();
    }

    @Override
    public boolean contains(Integer item) {
        sort();
        return binarySearch(item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return storage[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        storage = new Integer[4];
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        Integer[] arr = new Integer[size];
        System.arraycopy(storage, 0, arr, 0, size);
        return arr;
    }
    @Override
    public int hashCode() {
        return Arrays.hashCode(storage);
    }

    private void validateIndex(int index) {
        if (index >= size - 1 && index <= 0) {
            throw new ItemNotFoundException();
        }
    }

    private void sort() {
        for (int i = 1; i < storage.length; i++) {
            Integer temp = storage[i];
            int j = i;
            while (j > 0 && storage[j - 1] >= temp) {
                storage[j] = storage[j - 1];
                j--;
            }
            storage[j] = temp;
        }
    }

    private boolean binarySearch(Integer item) {
        int min = 0;
        int max = storage.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == storage[mid]) {
                return true;
            }

            if (item < storage[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

}
