package com.problem;

//https://www.interviewbit.com/problems/order-of-people-heights/

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OrderOfPeopleHeight {

    public ArrayList<Integer> order(ArrayList<Integer> A, ArrayList<Integer> B) {

        ArrayList<Pair> list = new ArrayList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            list.add(new Pair(A.get(i), B.get(i)));
            ans.add(Integer.MAX_VALUE);
        }


        Collections.sort(list, Comparator.comparing(Pair::getHeight).thenComparing(Pair::getInfront));

        for (int i = 0; i < A.size(); i++) {
            int pos = 0;
            Pair pair = list.get(i);
            int infront = pair.infront;
            for (int j = 0; j < ans.size(); j++) {
                if (ans.get(i) >= pair.height) {
                    pos++;
                    if (pos - 1 == infront) {
                        ans.set(pos - 1, pair.height);
                        break;
                    }
                } else {
                    ans.set(pos, pair.height);
                    break;
                }

            }

        }

        return ans;

    }

    public static void main(String[] args) {

        OrderOfPeopleHeight height = new OrderOfPeopleHeight();


        System.out.println(height.order(Lists.newArrayList(86, 77), Lists.newArrayList(0, 1)));
    }


    static class Pair {
        int height;
        int infront;

        Pair(int height, int infornt) {
            this.height = height;
            this.infront = infornt;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getInfront() {
            return infront;
        }

        public void setInfront(int infront) {
            this.infront = infront;
        }
    }


}
