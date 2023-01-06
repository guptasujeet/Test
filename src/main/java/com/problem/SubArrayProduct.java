package com.problem;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//VERY OLD , check if correct or not
//update it with correct code if not
public class SubArrayProduct {

    public static void main(String[] args) {
        int[] data1 = {1, 2, 3};
        int[] data2 = {1, 2, 3, 4};
        System.out.println(subArrayProductLessThanK(data1, 6));
        System.out.println(subArrayProductLessThanK(data2, 6));
    }


    private static int subArrayProductLessThanK(int[] data, int k) {

        Set<DataContainer> subArrays = new HashSet<>();

        for (int i = 0; i < data.length; i++) {
            for (int j = i; j < data.length; j++) {
                int len = j - i + 1;
                int[] temp = new int[len];
                System.arraycopy(data, i, temp, 0, len);
                subArrays.add(new DataContainer(temp));
            }
        }

        subArrays.removeIf(subData -> subData.getDataProduct() >= k);

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
