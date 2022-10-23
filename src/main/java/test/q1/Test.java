package test.q1;

import java.util.Arrays;
import java.util.LinkedList;

public class Test {

    public static int pow(int x, int n, int d) {

        long ans = ((long) Math.pow(Math.abs(x), n));
        int ans1 = (int) ans % d;
        if (ans1 == 0) {
            return 0;
        } else {
            return d - ans1;
        }
    }

    static char[] reverseWords(char[] arr) {
        mirrorReverse(arr, 0, arr.length - 1);

        int start = 0;
        for (int i = 0; i < arr.length; i++) {
            char chr = arr[i];
            if (chr == ' ') {
                mirrorReverse(arr, start, i - 1);
                start = i + 1;
            }
        }
        mirrorReverse(arr, start, arr.length - 1);

        return arr;
    }

    private static void mirrorReverse(char[] arr, int start, int end) {
        char temp;
        while (start < end) {
            temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            end--;
            start++;
        }
    }

    static char[] reverseWords3(char[] arr) {

        LinkedList<Character> stack1 = new LinkedList<>();
        LinkedList<Character> stack2 = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
            stack1.push(arr[i]);
        }

        int i = 0;
        while (!stack1.isEmpty()) {
            Character chr = stack1.poll();
            if (chr == ' ') {
                while (!stack2.isEmpty()) {
                    arr[i++] = stack2.pop();
                }
                arr[i++] = ' ';
            } else {
                stack2.push(chr);
            }
        }
        while (!stack2.isEmpty()) {
            arr[i++] = stack2.pop();
        }

        return arr;
    }

    static char[] reverseWords2(char[] arr) {
        // your code goes here
        String[] splits = new String(arr).split(" ");
        StringBuilder builder = new StringBuilder();
        for (int i = splits.length - 1; i >= 0; i--) {
            builder.append(splits[i]).append(' ');
        }
        return builder.toString().trim().toCharArray();
    }

    public static void main(String[] args) {

        System.out.println(pow(0, 0, 1));

        char[] arr = new char[]{'p', 'e', 'r', 'f', 'e', 'c', 't', ' ',
                'm', 'a', 'k', 'e', 's', ' ',
                'p', 'r', 'a', 'c', 't', 'i', 'c', 'e'};

        char[] arrCopy = new char[]{'p', 'e', 'r', 'f', 'e', 'c', 't', ' ',
                'm', 'a', 'k', 'e', 's', ' ',
                'p', 'r', 'a', 'c', 't', 'i', 'c', 'e'};

        System.out.println(Arrays.toString(reverseWords2(arr)));
        System.out.println(Arrays.toString(reverseWords3(arr)));
        System.out.println(Arrays.toString(reverseWords(arrCopy)));


    }

}
