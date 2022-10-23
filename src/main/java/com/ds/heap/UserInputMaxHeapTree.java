package com.ds.heap;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class UserInputMaxHeapTree {


    public static void main(String[] args) {

        //  10, 6, 20, 4, 8, 15, 25, 7, 12, -1

        //MaxHeap heap = makeMaxHeap();
        MaxHeap heap = createMaxHeap("10, 6, 20, 4, 8, 15, 25, 7, 12, -1");

        System.out.println(heap);

        heap.delete();

        System.out.println("After Delete -> \n" + heap);


        MaxHeap heap2 = new MaxHeap(new int[]{Integer.MIN_VALUE, 10, 6, 20, 4, 8, 15, 25, 7, 12});

        System.out.println("New Heap -> " + heap2);

        //heap2.delete();

        //System.out.println(heap2);

        heap2.sort();

        System.out.println("After Sort -> \n" + heap2);


    }


    public static MaxHeap makeMaxHeap() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the elements");
        String data = scanner.nextLine();
        return createMaxHeap(data);
    }

    public static MaxHeap createMaxHeap(String nodes) {
        MaxHeap heap = new MaxHeap();
        String[] split = StringUtils.split(nodes, ",");

        for (String singleData : split) {
            int val = Integer.parseInt(singleData.trim());
            if (val != -1) {
                heap.add(val);
            }

        }
        return heap;
    }

}
