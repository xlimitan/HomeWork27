package com.homework27.homework27.IntegerList;

import com.homework27.homework27.Exceptions.InvalidIndexException;
import com.homework27.homework27.Exceptions.NullItemException;
import com.homework27.homework27.Exceptions.StorageIsFullException;

import java.util.Arrays;

import static java.lang.System.arraycopy;

public class IntegerListImpl implements IntegerList {

    private Integer[] storage;

    private int size;


    public IntegerListImpl() {
        storage = new Integer[10];
    }

    public IntegerListImpl(int initSize){
        storage = new Integer[initSize];
    }

    @Override
    public Integer add(Integer item) {
        growIfNeeded();
        validateItem(item);
        storage[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        validateItem(item);
        growIfNeeded();
        validateIndex(index);

        if (index == size){
            storage[size++] = item;
            return item;
        }
        arraycopy(storage, index, storage,index +1, size - index);
        storage[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        storage[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int index = indexOf(item);
//        if(index == -1){
//            throw new Main.Exceptions.ElementNotFoundException();
//        }
//        if(index!=size){
//            arraycopy(storage, index +1, storage, index, size - index);
//        }
//        size--;
        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        Integer item = storage[index];

        if(index!=size){
            arraycopy(storage, index +1, storage, index, size - index);
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] storageCopy = toArray();
        sortSelection(storageCopy);
        return binarySearch(storageCopy,item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            Integer s = storage[i];
            if(s.equals(item)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size -1; i >= 0; i--) {
            Integer s = storage[i];
            if(s.equals(item)){
                return i;
            }
        }
        return 1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return storage[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(),otherList.toArray());
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
    size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(storage, size);
    }


    private void validateItem(Integer item) {
        if(item == null) {
            throw new NullItemException();
        }
    }

    private void growIfNeeded(){
        if(size==storage.length){
            grow();
        }
    }

    private void validateIndex(int index){
        if(index < 0 || index > size){
            throw new InvalidIndexException();
        }
    }

    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        Integer tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

//    public static void sortBubble(Integer[] arr) {
//        for (int i = 0; i < arr.length - 1; i++) {
//            for (int j = 0; j < arr.length - 1 - i; j++) {
//                if (arr[j] > arr[j + 1]) {
//                    swapElements(arr, j, j + 1);
//                }
//            }
//        }
//    }

    public static void sortSelection(Integer[] arr) {
        quickSort(arr,0, arr.length -1);
    }

    public static void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swapElements1(arr, i, j);
            }
        }
        swapElements1(arr, i + 1, end);
        return i + 1;
    }
    private static void swapElements1(Integer[] arr, int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

//    public static void sortInsertion(Integer[] arr) {
//        for (int i = 1; i < arr.length; i++) {
//            Integer temp = arr[i];
//            int j = i;
//            while (j > 0 && arr[j - 1] >= temp) {
//                arr[j] = arr[j - 1];
//                j--;
//            }
//            arr[j] = temp;
//        }
//    }

    public static Integer[] generateRandomArray() {
        java.util.Random random = new java.util.Random();
        Integer[] arr = new Integer[100_000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100_000) + 100_000;
        }
        return arr;
    }

    public static boolean binarySearch(Integer[] arr, Integer item) {
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

    private void grow(){
        storage = Arrays.copyOf(storage, size + size / 2);
    }

}
