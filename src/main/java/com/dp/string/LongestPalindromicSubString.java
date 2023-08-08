package com.dp.string;

import com.util.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/longest-palindromic-substring/
public class LongestPalindromicSubString {

    private int globalStart = 0;
    private int globalMax = -1;
    private String globalAns = "";

    public String longestPalindrome(String s) {
        globalStart = 0;
        globalAns = "";
        if (s.length() == 1) {
            return s;
        }

        int[][] memory = new int[s.length()][s.length()];
        for (int[] ints : memory) {
            Arrays.fill(ints, -1);
        }
        int len = longestPalindromeRec(s, 0, s.length() - 1, memory);
        //return len;
        System.out.println("global ans -> " + globalAns);
        return s.substring(globalStart, globalStart + len);
    }


    //first is start, 2nd is length
    public int longestPalindromeRec(String str, int start, int end, int[][] memory) {
        if (start > end || start < 0) {
            return 0;
        }
        if (start == end) {
            return 1;
        }

        if (memory[start][end] != -1) {
            return memory[start][end];
        }

        if (str.charAt(start) == str.charAt(end)) {
            int remainingLength = (end - start) - 1;
            int ans = longestPalindromeRec(str, start + 1, end - 1, memory);
            //check if remaining length is same as return value
            if (ans == remainingLength) {
                return 2 + ans;
            }
        }
        int ans1 = longestPalindromeRec(str, start, end - 1, memory);
        int ans2 = longestPalindromeRec(str, start + 1, end, memory);
        /*if (ans1 > ans2) {
            globalStart = start;
        } else {
            globalStart = start + 1;
        }*/
        int maxAns = Math.max(ans1, ans2);
        memory[start][end] = maxAns;
        return maxAns;
    }


    //first is start, 2nd is length
    public Pair<Integer, Integer> longestPalindromeRec2(String str, int start, int end) {
        if (start > end || start < 0) {
            return new Pair<>(start, 0);
        }
        if (start == end) {
            return new Pair<>(start, 1);
        }

        if (str.charAt(start) == str.charAt(end)) {
            int remainingLength = (end - start) - 1;
            Pair<Integer, Integer> ans = longestPalindromeRec2(str, start + 1, end - 1);
            //check if remaining length is same as return value
            if (ans.getSecond() == remainingLength) {
                globalStart = start;
                return new Pair<>(start, 2 + ans.getSecond());
            }
        }
        Pair<Integer, Integer> ans1 = longestPalindromeRec2(str, start, end - 1);
        Pair<Integer, Integer> ans2 = longestPalindromeRec2(str, start + 1, end);
        if (ans1.getSecond() > ans2.getSecond()) {
            return ans1;
        } else {
            return ans2;
        }
    }

    //not optimized
    public String longestPalindrome3(String s) {

        Map<String, Boolean> cache = new HashMap<>();

        String maxPalindromeSubstring = "";
        int length = s.length();
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                String subStr = s.substring(i, j + 1);
                boolean isPalindrome = checkPalindrome(subStr, cache);
                if (isPalindrome) {
                    if (subStr.length() > maxPalindromeSubstring.length()) {
                        maxPalindromeSubstring = subStr;
                    }
                }
            }
        }

        return maxPalindromeSubstring;

    }


    private boolean checkPalindrome(String str, Map<String, Boolean> cache) {

        if (cache.getOrDefault(str, Boolean.FALSE)) {
            return true;
        }

        int length = str.length();
        int left;
        int right;
        int mid = length / 2;
        if (length % 2 == 0) {
            left = mid - 1;
            right = mid;
        } else {
            left = mid - 1;
            right = mid + 1;
        }

        boolean ans = true;
        //check within string
        while (left >= 0 && right < length) {
            if (!(str.charAt(left) == str.charAt(right))) {
                ans = false;
                break;
            }
            left--;
            right++;
        }

        cache.put(str, ans);

        return ans;

    }


    //fastest way , copied solution from leetcode answers
    public String longestPalindrome2(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }


    public static void main(String[] args) {

        LongestPalindromicSubString palindromicSubString = new LongestPalindromicSubString();

        System.out.println(palindromicSubString.longestPalindrome("babad")); //bab
        System.out.println(palindromicSubString.longestPalindrome2("babad")); //bab
        System.out.println(palindromicSubString.longestPalindrome("cbbd")); //bb
        System.out.println(palindromicSubString.longestPalindrome2("cbbd")); //bb
        System.out.println(palindromicSubString.longestPalindrome("a"));  //a
        System.out.println(palindromicSubString.longestPalindrome2("a"));  //a
        System.out.println(palindromicSubString.longestPalindrome("zudfweormatjycujjirzjpyrmaxurectxrtqedmmgergwdvjmjtstdhcihacqnothgttgqfywcpgnuvwglvfiuxteopoyizgehkwuvvkqxbnufkcbodlhdmbqyghkojrgokpwdhtdrwmvdegwycecrgjvuexlguayzcammupgeskrvpthrmwqaqsdcgycdupykppiyhwzwcplivjnnvwhqkkxildtyjltklcokcrgqnnwzzeuqioyahqpuskkpbxhvzvqyhlegmoviogzwuiqahiouhnecjwysmtarjjdjqdrkljawzasriouuiqkcwwqsxifbndjmyprdozhwaoibpqrthpcjphgsfbeqrqqoqiqqdicvybzxhklehzzapbvcyleljawowluqgxxwlrymzojshlwkmzwpixgfjljkmwdtjeabgyrpbqyyykmoaqdambpkyyvukalbrzoyoufjqeftniddsfqnilxlplselqatdgjziphvrbokofvuerpsvqmzakbyzxtxvyanvjpfyvyiivqusfrsufjanmfibgrkwtiuoykiavpbqeyfsuteuxxjiyxvlvgmehycdvxdorpepmsinvmyzeqeiikajopqedyopirmhymozernxzaueljjrhcsofwyddkpnvcvzixdjknikyhzmstvbducjcoyoeoaqruuewclzqqqxzpgykrkygxnmlsrjudoaejxkipkgmcoqtxhelvsizgdwdyjwuumazxfstoaxeqqxoqezakdqjwpkrbldpcbbxexquqrznavcrprnydufsidakvrpuzgfisdxreldbqfizngtrilnbqboxwmwienlkmmiuifrvytukcqcpeqdwwucymgvyrektsnfijdcdoawbcwkkjkqwzffnuqituihjaklvthulmcjrhqcyzvekzqlxgddjoir"));  //
        System.out.println(palindromicSubString.longestPalindrome2("zudfweormatjycujjirzjpyrmaxurectxrtqedmmgergwdvjmjtstdhcihacqnothgttgqfywcpgnuvwglvfiuxteopoyizgehkwuvvkqxbnufkcbodlhdmbqyghkojrgokpwdhtdrwmvdegwycecrgjvuexlguayzcammupgeskrvpthrmwqaqsdcgycdupykppiyhwzwcplivjnnvwhqkkxildtyjltklcokcrgqnnwzzeuqioyahqpuskkpbxhvzvqyhlegmoviogzwuiqahiouhnecjwysmtarjjdjqdrkljawzasriouuiqkcwwqsxifbndjmyprdozhwaoibpqrthpcjphgsfbeqrqqoqiqqdicvybzxhklehzzapbvcyleljawowluqgxxwlrymzojshlwkmzwpixgfjljkmwdtjeabgyrpbqyyykmoaqdambpkyyvukalbrzoyoufjqeftniddsfqnilxlplselqatdgjziphvrbokofvuerpsvqmzakbyzxtxvyanvjpfyvyiivqusfrsufjanmfibgrkwtiuoykiavpbqeyfsuteuxxjiyxvlvgmehycdvxdorpepmsinvmyzeqeiikajopqedyopirmhymozernxzaueljjrhcsofwyddkpnvcvzixdjknikyhzmstvbducjcoyoeoaqruuewclzqqqxzpgykrkygxnmlsrjudoaejxkipkgmcoqtxhelvsizgdwdyjwuumazxfstoaxeqqxoqezakdqjwpkrbldpcbbxexquqrznavcrprnydufsidakvrpuzgfisdxreldbqfizngtrilnbqboxwmwienlkmmiuifrvytukcqcpeqdwwucymgvyrektsnfijdcdoawbcwkkjkqwzffnuqituihjaklvthulmcjrhqcyzvekzqlxgddjoir"));  //

    }

}
