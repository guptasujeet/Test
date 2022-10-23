package test.q1.q5;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

class Result {

    public static int lonelyinteger(List<Integer> list) {
        // Write your code here
        Map<Integer, List<Integer>> collect = list.stream().collect(groupingBy(a -> a));

        for (Map.Entry<Integer, List<Integer>> integerListEntry : collect.entrySet()) {
            if (integerListEntry.getValue().size() == 1) {
                return integerListEntry.getKey();
            }
        }
        return -1;
    }

    public static long flippingBits(long n) {
        // Write your code here
        n = ~n;
        String s = Integer.toBinaryString((int) n);

        return Long.parseLong(s, 2);
    }

    public static int diagonalDifference(List<List<Integer>> arr) {
        // Write your code here

        int size = arr.size();

        int sumD1 = 0;
        int sumD2 = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0, k = size - 1; j < size; j++, k--) {
                if (i == j) {
                    sumD1 += arr.get(i).get(j);
                    sumD2 += arr.get(i).get(k);
                }
            }
        }

        return Math.abs(sumD1 - sumD2);
    }

    public static List<Integer> countingSort(List<Integer> arr) {
        int size = arr.size();
        //100 is fixed in the problem statement
        int[] counter = new int[100];
        for (Integer integer : arr) {
            counter[integer]++;
        }
        return Arrays.stream(counter).boxed().collect(Collectors.toList());
    }

    public static String pangrams(String s) {
        // Write your code here
        int[] charCounter = new int[26];
        char[] input = s.replaceAll(" ", "").toLowerCase().toCharArray();
        for (char chr : input) {
            int index = chr - 'a';
            charCounter[index]++;
        }

        for (int i = 0; i < charCounter.length; i++) {
            if (charCounter[i] == 0) {
                return "not pangram";
            }
        }
        return "pangram";
    }

    public static String twoArrays(int k, List<Integer> A, List<Integer> B) {
        // Write your code here
        Collections.sort(A);
        Collections.sort(B);
        Collections.reverse(B);

        int size = A.size();

        for (int i = 0; i < size; i++) {
            if (A.get(i) + B.get(i) < k) {
                return "NO";
            }
        }
        return "YES";
    }

    public static int birthday(List<Integer> s, int d, int m) {
        if (m == 1) {
            if (d == s.get(0)) {
                return 1;
            }
        }
        // Write your code here
        int waysToDivide = 0;
        int limit = s.size() - m + 1;
        for (int i = 0; i < limit; i++) {
            long segmentSum = 0;
            for (int j = i; j < (i + m); j++) {
                segmentSum += s.get(j);
            }
            if (segmentSum == d) {
                waysToDivide++;
            }
        }
        return waysToDivide;
    }

    public static int flippingMatrix(List<List<Integer>> matrix) {
        int size = matrix.size();

        Integer[][] arr = new Integer[size][size];
        for (int i = 0; i < size; i++) {
            arr[i] = matrix.get(i).toArray(new Integer[0]);
        }
        int mid = matrix.size() / 2;
        int sum = 0;
        for (int i = 0; i < mid; i++) {
            for (int j = 0; j < mid; j++) {
                sum += (int)
                        Math.max(
                                Math.max(arr[i][j], arr[i][2 * mid - j - 1]),
                                Math.max(arr[2 * mid - i - 1][j], arr[2 * mid - i - 1][2 * mid - j - 1])
                        );
            }
        }
        return sum;
    }

    public static int sockMerchant(int n, List<Integer> ar) {
        // Write your code here
        int pair = 0;
        Collections.sort(ar);
        for (int i = 0; i < n - 1; ) {
            if (ar.get(i).equals(ar.get(i + 1))) {
                pair++;
                i = i + 2;
            } else {
                i = i + 1;
            }
        }
        return pair;

    }

    public static void findZigZagSequence(int[] a, int n) {
        Arrays.sort(a);
        int mid = (n) / 2;
        int temp = a[mid];
        a[mid] = a[n - 1];
        a[n - 1] = temp;

        int st = mid + 1;
        int ed = n - 2;
        while (st < ed) {
            temp = a[st];
            a[st] = a[ed];
            a[ed] = temp;
            st = st + 1;
            ed = ed - 1;
        }
        for (int i = 0; i < n; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(a[i]);
        }
        System.out.println();
    }

    public static int pageCount(int n, int p) {
        // Write your code here
        int mid = n / 2;

        if (p <= mid) {
            return p / 2;
        } else {
            return (n - p) / 2;
        }

    }

    public static int towerBreakers2(int n, int m) {
        // Write your code here
        int[] towersWithHeight = new int[n];
        for (int i = 0; i < n; i++) {
            towersWithHeight[i] = m;
        }

        int playerTurn = 1;
        boolean hasMove;
        while (true) {
            hasMove = false;
            for (int j = 0; j < n; j++) {
                int height = towersWithHeight[j];
                int reducedTo = 0;
                if (height == 2 || (height % 2 == 1 && height != 1)) {
                    reducedTo = 1;
                }
                if (height % 2 == 0) {
                    int tempHeight = height;
                    while (tempHeight % 2 == 0) {
                        tempHeight = tempHeight / 2;
                    }
                    reducedTo = tempHeight;
                }
                if (reducedTo != 0) {
                    towersWithHeight[j] = reducedTo;
                    hasMove = true;
                    break;
                }
            }
            playerTurn = playerTurn == 1 ? 2 : 1;
            if (!hasMove) {
                break;
            }
        }
        return playerTurn;
    }

    public static int towerBreakers(int n, int m) {
        if (n % 2 == 0 || m == 1) {
            return 2;
        }
        return 1;
    }

    public static String caesarCipher2(String s, int k) {
        int count = 26;
        // Write your code here
        List<Character> lowerCase = new ArrayList<>();
        List<Character> upperCase = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lowerCase.add((char) ('a' + i));
            upperCase.add((char) ('A' + i));
        }

        for (int i = 0; i < k; i++) {
            Character lower = lowerCase.remove(0);
            lowerCase.add(lower);
            Character upper = upperCase.remove(0);
            upperCase.add(upper);
        }
        Map<Character, Character> mapping = new HashMap<>();
        for (int i = 0; i < count; i++) {
            mapping.put((char) ('a' + i), lowerCase.get(i));
            mapping.put((char) ('A' + i), upperCase.get(i));
        }
        char[] strChars = s.toCharArray();
        for (int i = 0; i < strChars.length; i++) {
            char strChar = strChars[i];
            if (Character.isLetter(strChar)) {
                strChars[i] = mapping.get(strChar);
            }
        }
        return new String(strChars);
    }

    public static String caesarCipher(String s, int k) {
        k = k % 26;
        char[] strChars = s.toCharArray();
        for (int i = 0; i < strChars.length; i++) {
            char strChar = strChars[i];
            if (Character.isLetter(strChar)) {
                if (Character.toLowerCase(strChar) + k > 'z') {
                    strChars[i] = (char) (strChars[i] - 26 + k);
                } else {
                    strChars[i] = (char) (strChars[i] + k);
                }

            }
        }
        return new String(strChars);
    }


    public static int maxMin(int k, List<Integer> arr) {
        Collections.sort(arr);
        int min = Integer.MAX_VALUE;
        int size = arr.size();
        for (int i = 0; i < size - k + 1; i++) {
            min = Math.min(min, arr.get(i + k - 1) - arr.get(i));
        }
        return min;
    }

    public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
        // Write your code here

        List<Integer> answerArray = new ArrayList<>();

        List<List<Integer>> arr = new ArrayList<>(n);
        int lastAnswer = 0;

        for (int i = 0; i < n; i++) {
            arr.add(new ArrayList<>());
        }

        for (int i = 0; i < queries.size(); i++) {
            List<Integer> query = queries.get(i);
            int queryType = query.get(0);
            int x = query.get(1);
            int y = query.get(2);
            int idx = ((x ^ lastAnswer) % n);
            if (queryType == 1) {
                arr.get(idx).add(y);
            } else {
                lastAnswer = arr.get(idx).get((y % (arr.get(idx)).size()));
                answerArray.add(lastAnswer);
            }
        }

        return answerArray;

    }


    public static String gridChallenge(List<String> grid) {
        // Write your code here
        int size = grid.size();
        char[][] charGrid = new char[size][];
        int columnLength = -1;
        for (int i = 0; i < size; i++) {
            String row = grid.get(i);
            char[] chars = row.toCharArray();
            Arrays.sort(chars);
            charGrid[i] = chars;
            columnLength = chars.length;
        }

        for (int column = 0; column < columnLength; column++) {
            char[] oneRow = charGrid[column];
            for (int row = 0; row < oneRow.length - 1; row++) {
                if (charGrid[row][column] > charGrid[row + 1][column]) {
                    return "NO";
                }
            }
        }
        return "YES";
    }

    public static int[] month = new int[15];

    public static void updateLeapYear(int year) {
        if (year % 400 == 0) {
            month[2] = 29;
        } else if (year % 100 == 0) {
            month[2] = 29;
        } else if (year % 4 == 0) {
            month[2] = 29;
        } else {
            month[2] = 28;
        }
    }

    public static void storeMonth() {
        month[1] = 31;
        month[2] = 28;
        month[3] = 31;
        month[4] = 30;
        month[5] = 31;
        month[6] = 30;
        month[7] = 31;
        month[8] = 31;
        month[9] = 30;
        month[10] = 31;
        month[11] = 30;
        month[12] = 31;
    }

    public static int findPrimeDates(int d1, int m1, int y1, int d2, int m2, int y2) {
        storeMonth();

        int result = 0;

        while (true) {
            int x = d1;
            x = x * 100 + m1;
            x = x * 10000 + y1;
            if (x % 4 == 0 || x % 7 == 0) {
                result = result + 1;
            }
            if (d1 == d2 && m1 == m2 && y1 == y2) {
                break;
            }
            updateLeapYear(y1);
            d1 = d1 + 1;
            if (d1 > month[m1]) {
                m1 = m1 + 1;
                d1 = 1;
                if (m1 > 12) {
                    y1 = y1 + 1;
                    m1 = 1;
                }
            }
        }
        return result;
    }

    public static String balancedSums(List<Integer> arr) {
        if (arr.size() == 1) {
            return "YES";
        }
        // Write your code here
        int size = arr.size();
        int leftSum = 0;
        int rightSum = arr.stream().skip(1).mapToInt(Integer::intValue).sum();

        for (int i = 0; i < size - 1; i++) {
            if (leftSum == rightSum) {
                return "YES";
            } else {
                leftSum += arr.get(i);
                rightSum -= arr.get(i + 1);
            }
        }
        return "NO";
    }


    public static int superDigit(String n, int k) {
        // Write your code here
        char[] nums = n.toCharArray();
        long sum = 0;
        for (char num : nums) {
            sum += Integer.parseInt("" + num);
        }
        sum = sum * k;
        if (sum < 10) {
            return (int) sum;
        }
        return superDigit(String.valueOf(sum), 1);
    }

    public static String counterGame(long n) {

        String player1 = "Louise";
        String player2 = "Richard";

        String binary = Long.toBinaryString(n);

        // The number of 1's (until the last) represent the number
        // of turns / subtractions until the result is a power of 2
        long turns = binary.chars().filter(ch -> ch == '1').count() - 1;

        // Add to turns the number of zeros ofter the last 1
        // which will be the turns / div by 2 until 1 is reached
        turns += binary.length() - (binary.lastIndexOf("1") + 1);

        // If the number of turns is even, player 2 will win
        return turns % 2 == 0 ? player2 : player1;

    }

    public static String counterGame2(long n) {
        // Write your code here

        return counterGameWithPlayer(n, "Richard");
    }

    public static String counterGameWithPlayer(long n, String nextPlayer) {
        // Write your code here
        if (n == 0 || n == 1) {
            return nextPlayer;
        }
        nextPlayer = "Richard".equals(nextPlayer) ? "Louise" : "Richard";
        if (isPowerOf2(n)) {
            return counterGameWithPlayer(n / 2, nextPlayer);
        } else {
            long toSubtract = lowerPowerOf2(n);
            return counterGameWithPlayer(n - toSubtract, nextPlayer);
        }
    }

    public static boolean isPowerOf2(long n) {
        return n != 0 && (n & (n - 1)) == 0;
    }

    public static long lowerPowerOf2(long n) {
        int count = 0;
        while (n != 0) {
            n = n >> 1;
            count += 1;
        }
        return 1 << (count - 1);
    }

    public static long sumXor(long n) {
        if (n == 0) {
            return 1;
        }

        String binaryString = Long.toBinaryString(n);
        int zeroCount = 0;
        for (char c : binaryString.toCharArray()) {
            if ('0' == c) {
                zeroCount++;
            }
        }
        return (long) Math.pow(2, zeroCount);
    }

    public static int palindromeIndex(String s) {
        // Write your code here
        int size = s.length();
        if (size == 1) {
            return -1;
        }
        int mid = size / 2;
        int res = -1;
        char[] chars = s.toCharArray();
        for (int i = 0, j = (size - 1); i < mid; ) {
            if (chars[i] == chars[j]) {
                i++;
                j--;
            } else {
                String excludingFront = s.substring(i + 1, j + 1);
                if (isPalindrome(excludingFront)) {
                    return i;
                } else {
                    return j;
                }
            }
        }
        return res;
    }

    public static boolean isPalindrome(String s) {
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    public static int gcdOf(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcdOf(b, a % b);
    }

    public static int lcmOf(int a, int b) {
        return (a * b) / gcdOf(a, b);
    }

    public static int getTotalX(List<Integer> a, List<Integer> b) {
        // Write your code here
        int lcm = a.get(0);
        for (int i = 1; i < a.size(); i++) {
            lcm = lcmOf(lcm, a.get(i));
        }

        int gcd = b.get(0);
        for (int i = 1; i < b.size(); i++) {
            gcd = gcdOf(gcd, b.get(i));
        }

        int count = 0;
        for (int i = lcm, j = 2; i <= gcd; i = lcm * j, j++) {
            if (gcd % i == 0) {
                count++;
            }
        }
        return count;
    }

    public static int anagram(String s) {

        int size = s.length();
        if (size % 2 == 1) {
            return -1;
        }
        int mid = size / 2;
        String s1 = s.substring(0, mid);
        String s2 = s.substring(mid, size);
        char[] chars = s1.toCharArray();
        for (char aChar : chars) {
            s2 = s2.replaceFirst(aChar + "", "");
        }
        return s2.length();
    }

    public static List<String> bomberMan(int n, List<String> grid) {
        // Write your code here
        char[][] gridChars = new char[grid.size()][];
        for (int i = 0; i < grid.size(); i++) {
            gridChars[i] = grid.get(i).toCharArray();
        }
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 2) {
                replace(gridChars);
            }
            if (i % 3 == 0) {
                clearBomb(gridChars);
            }
        }
        List<String> solution = new ArrayList<>();
        for (char[] gridChar : gridChars) {
            solution.add(new String(gridChar));
        }
        return solution;
    }

    private static void clearBomb(char[][] grid) {
        int iBoundary = grid.length - 1;
        int jBoundary = grid[0].length - 1;
        for (int i = 0; i < grid.length; i++) {
            char[] row = grid[i];
            for (int j = 0; j < row.length; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '.';
                    if (i < iBoundary) {
                        if (grid[i + 1][j] != '1') {
                            grid[i + 1][j] = '.';
                        }
                    }
                    if (i > 0) {
                        if (grid[i - 1][j] != '1') {
                            grid[i - 1][j] = '.';
                        }
                    }
                    if (j < jBoundary) {
                        if (grid[i][j + 1] != '1') {
                            grid[i][j + 1] = '.';
                        }
                    }
                    if (j > 0) {
                        if (grid[i][j - 1] != '1') {
                            grid[i][j - 1] = '.';
                        }
                    }
                }
            }
        }
        //System.out.println("Clearing Bomb");
        //printGrid(grid);
    }

    public static void replace(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            char[] row = grid[i];
            for (int j = 0; j < row.length; j++) {
                if (grid[i][j] == 'O') {
                    grid[i][j] = '1';
                }
                if (grid[i][j] == '.') {
                    grid[i][j] = 'O';
                }
            }
        }
        //System.out.println("Replacing Grid");
        //printGrid(grid);
    }

    public static void printGrid(char[][] grid) {
        System.out.println("-----------");
        for (int i = 0; i < grid.length; i++) {
            System.out.println(Arrays.toString(grid[i]));
        }
        System.out.println("-----------");
    }


    public static int[] sort012(int[] arr) {
        int i = 0, j = arr.length - 1;
        int last0 = 0, last2 = arr.length - 1;
        while (i < j) {
            while (arr[i] == 0 && i < j) {
                i++;
            }
            last0 = i;
            while (arr[i] == 1 && i < j) {
                i++;
            }
            swap(arr, i, last2);
            while (arr[j] == 2 && i < j) {
                j--;
            }
            last2 = j;
            while (arr[j] == 1 && i < j) {
                j--;
            }
            swap(arr, j, last0);
        }
        return arr;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void minimumBribes2(List<Integer> q) {
        int size = q.size();
        int bribes = 0;
        boolean tooChaotic = false;
        int i = 0;
        int j = 1;
        while (j < size) {
            int first = q.get(i);
            int second = q.get(j);
            if (second < first) {
                int perBribe = Math.abs(first - second);
                if (perBribe > 2) {
                    tooChaotic = true;
                } else {
                    bribes += perBribe;
                }
            }
            i++;
            j++;
        }
        if (tooChaotic) {
            System.out.println("Too chaotic");
        } else {
            System.out.println(bribes);
        }
    }

    public static void minimumBribes3(List<Integer> q) {
        int size = q.size();
        int bribes = 0;
        boolean tooChaotic = false;
        int i = 0;
        while (i < size) {
            while (q.get(i) == i + 1) {
                i++;
            }
            int first = q.get(i);
            int second = q.get(i + 1);
            if (first != i + 1) {
                int perBribe = Math.abs(first - second);
                if (perBribe > 2) {
                    tooChaotic = true;
                    break;
                } else {
                    bribes++;
                    q.remove(i);
                }
            }
            i++;
        }
        if (tooChaotic) {
            System.out.println("Too chaotic");
        } else {
            System.out.println(bribes);
        }
    }

    private static void swapNext(List<Integer> q, int i) {
        int temp = q.get(i);
        q.set(i, q.get(i + 1));
        q.set(i + 1, temp);
    }

    public static void minimumBribes(List<Integer> q) {
        // Write your code here
        int bribes = 0;
        boolean tooChaotic = false;
        outer:
        for (int i = q.size() - 1; i >= 0; i--) {
            int target = i + 1;
            int currBribe = 0, targetIdx = i;
            while (q.get(targetIdx) != target) {
                if (++currBribe > 2) {
                    tooChaotic = true;
                    break outer;
                }
                targetIdx--;
            }
            bribes += currBribe;
            q.remove(targetIdx);
        }
        if (tooChaotic) {
            System.out.println("Too chaotic");
        } else {
            System.out.println(bribes);
        }
    }

    public static String isValid(String s) {
        int size = s.length();

        if (size == 0 || size == 1 || size == 2) {
            return "YES";
        }

        // Write your code here

        int[] counts = new int[26];
        for (int i = 0; i < size; i++) {
            char charAt = s.charAt(i);
            int index = charAt - 'a';
            counts[index]++;
        }

        int toMatch = 0;
        boolean oneRemovalNotCounted = true;
        boolean isMatching = false;

        int j = 0;
        while (toMatch == 0 && counts[j] != 0) {
            toMatch = counts[j];
            j++;
        }

        for (int i = j - 1; i < 26; i++) {
            if (counts[i] != 0) {
                if (toMatch == counts[i]) {
                    isMatching = true;
                } else if (oneRemovalNotCounted && Math.abs((toMatch - counts[i])) == 1) {
                    oneRemovalNotCounted = false;
                } else {
                    isMatching = false;
                }
            }
        }

        if (isMatching) {
            return "YES";
        } else {
            return "NO";
        }
    }

}

public class Solution {

    public static void main(String[] args) {
        System.out.println(Result.isValid("abcdefghhgfedecba"));
        System.out.println(Result.isValid("abcc"));
        System.out.println(Result.isValid("aabbcd"));
        Result.minimumBribes(Lists.newArrayList(1, 2, 5, 3, 7, 8, 6, 4));
        Result.minimumBribes(Lists.newArrayList(2, 1, 5, 3, 4));
        Result.minimumBribes(Lists.newArrayList(2, 5, 1, 3, 4));
    }

    public static void main22(String[] args) {

        //System.out.println(Arrays.toString(Result.sort012(new int[]{0, 2, 1, 0, 1, 2, 1, 1})));
        //System.out.println(Arrays.toString(Result.sort012(new int[]{0, 2, 0, 0, 1, 2, 1, 0})));
        //System.out.println(Arrays.toString(Result.sort012(new int[]{0, 2, 1, 0, 1, 2, 1, 1})));
        //System.out.println(Arrays.toString(Result.sort012(new int[]{2, 2, 1, 0, 1, 2, 1, 2})));
        List<String> grid = Lists.newArrayList(
                ".......",
                "...O...",
                "....O..",
                ".......",
                "OO.....",
                "OO.....");
        System.out.println(Result.bomberMan(3, grid));
    }

    public static void main21(String[] args) {
        /*System.out.println(Result.anagram("aaabbb"));
        System.out.println(Result.anagram("ab"));
        System.out.println(Result.anagram("abc"));
        System.out.println(Result.anagram("mnop"));
        System.out.println(Result.anagram("xyyx"));*/
        System.out.println(Result.anagram("xaxbbbxx"));
    }

    public static void main20(String[] args) {
        //System.out.println(Result.gcdOf(10, 25));
        //System.out.println(Result.lcmOf(10, 25));
        System.out.println(Result.getTotalX(Lists.newArrayList(2, 6), Lists.newArrayList(24, 36)));
        System.out.println(Result.getTotalX(Lists.newArrayList(2, 4), Lists.newArrayList(16, 32, 96)));
    }

    public static void main19(String[] args) {
        System.out.println(Result.palindromeIndex("bcbc"));
        System.out.println(Result.palindromeIndex("aaab"));
        System.out.println(Result.palindromeIndex("aaa"));
        System.out.println(Result.palindromeIndex("baa"));
        System.out.println(Result.palindromeIndex("baada"));
        //System.out.println(Result.superDigit("148", 3));
        //System.out.println(Result.lowerPowerOf2(9));
        //System.out.println(Result.lowerPowerOf2(132));
        //System.out.println(Result.counterGame(660570904136157L));
        //System.out.println(Result.sumXor(0));

    }

    public static void main18(String[] args) {
        System.out.println(Result.balancedSums(Lists.newArrayList(1)));
        //System.out.println(Result.balancedSums(Lists.newArrayList(6, 7, 8, 5, 3, 2, 1, 9, 6)));
        //System.out.println(Result.balancedSums(Lists.newArrayList(2, 0, 0, 0)));
        //System.out.println(Result.balancedSums(Lists.newArrayList(1, 2, 3)));
    }

    public static void main17(String[] args) {
        System.out.println(Result.findPrimeDates(1, 1, 7580, 26, 7, 9834));
    }

    public static void main16(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("hjk");
        list.add("mpq");
        list.add("rtv");

        System.out.println(Result.gridChallenge(list));
    }

    public static void main15(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("ade");
        list.add("efg");

        System.out.println(Result.gridChallenge(list));
    }

    public static void main14(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(ImmutableList.of(1, 0, 5));
        list.add(ImmutableList.of(1, 1, 7));
        list.add(ImmutableList.of(1, 0, 3));
        list.add(ImmutableList.of(2, 1, 0));
        list.add(ImmutableList.of(2, 1, 1));

        System.out.println(Result.dynamicArray(2, list));
    }

    public static void main13(String[] args) {
        //System.out.println(Result.towerBreakers(2, 6));
        //System.out.println(Result.towerBreakers(1, 4));
        //System.out.println(Result.towerBreakers(1, 7));
        //System.out.println(Result.towerBreakers(3, 7));
        //System.out.println(Result.caesarCipher("middle-Outz", 2));
        System.out.println(Result.maxMin(2, Lists.newArrayList(1, 2, 1, 2, 1)));
    }

    public static void main12(String[] args) {
        System.out.println(Result.pageCount(6, 5));
    }

    public static void main11(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        Result.findZigZagSequence(arr, arr.length);
    }

    public static void main10(String[] args) {
        ArrayList<Integer> socks = Lists.newArrayList(10, 20, 20, 10, 10, 30, 50, 10, 20);
        System.out.println(Result.sockMerchant(socks.size(), socks));
    }

    public static void main9(String[] args) {

        List<List<Integer>> list = new ArrayList<>();
        list.add(Lists.newArrayList(112, 42, 83, 119));
        list.add(Lists.newArrayList(56, 125, 56, 49));
        list.add(Lists.newArrayList(15, 78, 101, 43));
        list.add(Lists.newArrayList(62, 98, 114, 108));

        System.out.println(Result.flippingMatrix(list));
    }

    public static void main8(String[] args) {

        List<Integer> segment = Lists.newArrayList(2, 5, 1, 3, 4, 4, 3, 5, 1, 1, 2, 1, 4, 1, 3, 3, 4, 2, 1);
        int d = 18;
        int m = 7;
        System.out.println(Result.birthday(segment, d, m));
    }

    public static void main7(String[] args) {
        List<Integer> segment = Lists.newArrayList(4);
        int d = 4;
        int m = 1;
        System.out.println(Result.birthday(segment, d, m));
    }

    public static void main6(String[] args) {
        List<Integer> segment = Lists.newArrayList(2, 2, 1, 3, 2);
        int d = 4;
        int m = 2;
        System.out.println(Result.birthday(segment, d, m));
    }

    public static void main5(String[] args) {
        int k = 10;
        List<Integer> list1 = Lists.newArrayList(2, 1, 3);
        List<Integer> list2 = Lists.newArrayList(7, 8, 9);

        System.out.println(Result.twoArrays(k, list1, list2));
    }

    public static void main4(String[] args) {
        System.out.println(Result.pangrams("We promptly judged antique ivory buckles for the prize"));
    }

    public static void main3(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(ImmutableList.of(1, 2, 3));
        list.add(ImmutableList.of(4, 5, 6));
        list.add(ImmutableList.of(9, 8, 9));
        System.out.println(Result.diagonalDifference(list));

    }


    public static void main2(String[] args) {
        ImmutableList<Integer> list = ImmutableList.of(1, 2, 3, 4, 3, 2, 1);
        System.out.println(Result.lonelyinteger(list));
        long number = 9;
        System.out.println(Result.flippingBits(number));


    }

}
