package com.walmart.t1;

import java.util.Arrays;
import java.util.EmptyStackException;

public class PowerOfTwoMaxHeap {

    private int[] heap;
    private int size;
    private int maxChildren;

    public PowerOfTwoMaxHeap(int maxChildren) {
        this.heap = new int[16];
        this.size = 0;
        this.maxChildren = maxChildren;
    }

    public void insert(int value) {
        // Check if the heap needs to be resized.
        if (size == heap.length) {
            heap = Arrays.copyOf(heap, heap.length * 2);
        }

        // Add the new element to the end of the heap.
        heap[size++] = value;

        // Maintain the heap property.
        heapifyUp(size - 1);
    }

    public int popMax() {
        // If the heap is empty, throw an exception.
        if (size == 0) {
            throw new EmptyStackException();
        }

        // Get the maximum element from the root of the heap.
        int max = heap[0];

        // Replace the root element with the last element in the heap.
        heap[0] = heap[size - 1];

        // Decrement the size of the heap.
        size--;

        // Maintain the heap property.
        heapifyDown(0);

        return max;
    }

    private void heapifyUp(int index) {
        // Get the parent index.
        int parentIndex = (index - 1) / maxChildren;

        // If the parent is less than the child, swap them.
        if (heap[parentIndex] < heap[index]) {
            swap(heap, parentIndex, index);

            // Recursively heapify up the parent.
            heapifyUp(parentIndex);
        }
    }

    private void heapifyDown(int index) {
        // Get the left and right child indices.
        int leftChildIndex = maxChildren * index + 1;
        int rightChildIndex = maxChildren * index + 2;

        // Find the largest child index.
        int largestChildIndex = index;
        if (leftChildIndex < size && heap[leftChildIndex] > heap[index]) {
            largestChildIndex = leftChildIndex;
        }
        if (rightChildIndex < size && heap[rightChildIndex] > heap[largestChildIndex]) {
            largestChildIndex = rightChildIndex;
        }

        // If the largest child is greater than the parent, swap them.
        if (largestChildIndex != index) {
            swap(heap, index, largestChildIndex);

            // Recursively heapify down the largest child.
            heapifyDown(largestChildIndex);
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        // Example usage:
        PowerOfTwoMaxHeap heap = new PowerOfTwoMaxHeap(2);
        heap.insert(10);
        heap.insert(5);
        heap.insert(15);
        heap.insert(3);

        System.out.println("Max: " + heap.popMax()); // Output: 15
        System.out.println("Max: " + heap.popMax()); // Output: 10
    }
}

