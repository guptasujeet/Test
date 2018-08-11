package com.problem;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SubArrayProduct {

    public static void main(String[] args) {
        //int[] data = {1, 2, 3};
        int[] data = {1, 2, 3, 4};
        int totalNum = subArrayProductLessThanK(data, 6);
        System.out.println(totalNum);
    }


    private static int subArrayProductLessThanK(int[] data, int k) {

        Set<DataContainer> subArrays = new HashSet<>();

        for (int i = 0; i < data.length; i++) {
            for (int j = i; j < data.length; j++) {
                int len = j - i + 1;
                int temp[] = new int[len];
                System.arraycopy(data, i, temp, 0, len);
                subArrays.add(new DataContainer(temp));
            }
        }

        Iterator<DataContainer> iterator = subArrays.iterator();

        while (iterator.hasNext()) {
            DataContainer subData = iterator.next();
            if (subData.getDataProduct() >= k) {
                iterator.remove();
            }
        }

        //java 8
        //subArrays.removeIf(subData -> subData.getDataProduct() >= k);

        return subArrays.size();
    }


    static class DataContainer {
        int[] data;

        public DataContainer(int[] data) {
            this.data = data;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            DataContainer that = (DataContainer) o;

            return Arrays.equals(data, that.data);
        }

        public int getDataProduct() {
            int product = 1;
            for (int i = 0; i < data.length; i++) {
                product = product * data[i];
            }
            return product;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(data);
        }

        @Override
        public String toString() {
            return "{" + Arrays.toString(data) + '}';
        }
    }

}
