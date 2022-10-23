package com.ds.heap;

public class MaxHeap {

    private int[] data = new int[100];
    private int size;

    MaxHeap() {
        data[size++] = Integer.MIN_VALUE;
    }

    MaxHeap(int[] data) {
        this();
        for (int i = 1; i < data.length; i++) {
            this.data[i] = data[i];
        }
        this.size = data.length;
        int lastNonLeafNode = size / 2;
        //no need to process leaf node , hence taking last non leaf node
        // since heap is CBT we can use this easily
        for (int i = lastNonLeafNode; i > 0; i--) {
            heapify(i, size);
        }
    }


    void add(int element) {
        //step 1 add element
        data[size] = element;

        int index = size;
        int parent = size / 2;
        //step 2move it to correct location
        while (parent >= 1) {
            if (data[parent] < data[index]) {
                swap(index, parent);
            }
            index = parent;
            parent = parent / 2;
        }
        size++;
    }


    //delete means deleting from top
    void delete() {
        if (size == 1) {
            //nothing to delete as, starting index is 1 not 0
            return;
        }

        //swap first node with last node
        data[1] = data[--size];

        int index = 1;

        while (index < size) {
            int left = 2 * index;
            int right = 2 * index + 1;

            if (left < size && data[index] < data[left]) {
                swap(index, left);
                index = left;
            } else if (right < size && data[index] < data[right]) {
                swap(index, right);
                index = right;
            } else {
                return;
            }
        }

    }


    void heapify(int index, int size) {
        int largestIndex = index;
        int left = 2 * index;
        int right = 2 * index + 1;


        if (left <= size && right <= size) {
            int largestSideIndex;
            if (data[right] > data[left]) {
                largestSideIndex = right;
            } else {
                largestSideIndex = left;
            }
            if (data[index] < data[largestSideIndex]) {
                largestIndex = largestSideIndex;
            }
        } else if (left <= size && data[index] < data[left]) {
            largestIndex = left;
        } else if (right <= size && data[index] < data[right]) {
            largestIndex = right;
        }

        if (largestIndex != index) {
            swap(index, largestIndex);
            heapify(largestIndex, size);
        }

    }

    private void swap(int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }


    public void sort() {
        if (size == 1) {
            return;
        }

        int tempSize = size - 1;

        while (tempSize > 1) {
            swap(1, tempSize);
            tempSize--;
            heapify(1, tempSize);
        }
    }


    @Override
    public String toString() {
        String string = "";
        for (int i = 1; i < size; i++) {
            string += " " + data[i];
        }
        return string;
    }
}
