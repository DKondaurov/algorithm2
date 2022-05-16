package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        final IntegerListImpl integerList = new IntegerListImpl();

        int[] arr = generateRandomArray();
        int[] arr2 = Arrays.copyOf(arr, 100_000);
        int[] arr3 = Arrays.copyOf(arr, 100_000);

        System.out.print("Пузырьковая сортировка = ");
        long start = System.currentTimeMillis();
        sortBubble(arr);
        System.out.println(System.currentTimeMillis() - start);

        System.out.print("Сортировка выбором = ");
        start = System.currentTimeMillis();
        sortSelection(arr3);
        System.out.println(System.currentTimeMillis() - start);

        System.out.print("Сортировка вставкой = ");
        start = System.currentTimeMillis();
        sortInsertion(arr2);
        System.out.println(System.currentTimeMillis() - start);

    }

    public static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }


    public static int[] generateRandomArray() {
        java.util.Random random = new java.util.Random();
        int[] arr = new int[100_000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100_000) + 100_000;
        }
        return arr;
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    public static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
}
