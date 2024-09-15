package org.example;

public class GenericMethod {
    /**
     * swaps elements of given two indices for any type of array
     * @param array input array of any type T
     * @param index1 index of element to be swapped
     * @param index2 index of element to be swapped
     * @param <T> type of array
     */
    public <T> void swap(T[] array, int index1, int index2) {
        T change = array[index1];
        array[index1] = array[index2];
        array[index2] = change;
    }
}
