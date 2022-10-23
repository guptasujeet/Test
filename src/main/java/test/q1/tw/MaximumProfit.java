package test.q1.tw;

import java.util.*;

public class MaximumProfit {

    public static long maximumProfit(List<Integer> inventory, long order) {

        TreeMap<Integer, Integer> sortedCount = new TreeMap<>(Comparator.reverseOrder());
        for (Integer item : inventory) {
            sortedCount.compute(item, (k, v) -> {
                if (v == null) {
                    v = 0;
                }
                return v + 1;
            });
        }

        long profit = 0;
        //at lest one order in question
        long currentOrder = 1;
        while (currentOrder <= order) {
            Map.Entry<Integer, Integer> invPriceAndCount = sortedCount.pollFirstEntry();
            int invPrice = invPriceAndCount.getKey();
            int invPriceCount = invPriceAndCount.getValue();
            if ((invPriceCount + currentOrder) > order) {
                for (int i = 1; i <= invPriceCount; i++) {
                    if (currentOrder <= order) {
                        profit = profit + invPrice;
                        currentOrder++;
                    }
                }
            } else {
                profit = profit + (invPrice * invPriceCount);
                currentOrder = currentOrder + invPriceCount;
                if (invPrice > 1) {
                    sortedCount.compute(invPrice - 1, (k, v) -> {
                        if (v == null) {
                            v = 0;
                        }
                        return v + invPriceCount;
                    });
                }
            }
        }

        return profit;
    }


    public static long maximumProfit2(List<Integer> inventory, long order) {
        PriorityQueue<Integer> profitList = new PriorityQueue<>(inventory.size(), Comparator.reverseOrder());
        profitList.addAll(inventory);

        long profit = 0;
        //at lest one order in question
        long currentOrder = 1;
        while (currentOrder <= order && !profitList.isEmpty()) {
            int invPrice = profitList.poll();
            int invPrice2 = profitList.peek() == null ? 0 : profitList.peek();
            //optimization
            while (invPrice >= invPrice2 && currentOrder <= order) {
                profit += invPrice;
                invPrice--;
                currentOrder++;
            }
            if (invPrice > 1) {
                profitList.add(invPrice);
            }
        }

        return profit;
    }


    public static void main(String[] args) {

        List<Integer> inventory1 = new ArrayList<>();
        inventory1.add(3);
        inventory1.add(5);

        System.out.println(maximumProfit(inventory1, 6)); //19

        List<Integer> inventory2 = new ArrayList<>();
        inventory2.add(2);
        inventory2.add(5);
        System.out.println(maximumProfit(inventory2, 4)); //14


        List<Integer> inventory3 = new ArrayList<>();
        inventory3.add(2);
        inventory3.add(5);
        inventory3.add(5);
        System.out.println(maximumProfit(inventory3, 4)); //18
        System.out.println(maximumProfit2(inventory3, 4)); //18

    }
}
