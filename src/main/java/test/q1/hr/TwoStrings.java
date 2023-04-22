package test.q1.hr;

import com.google.common.collect.Lists;

import java.util.List;

public class TwoStrings {

    public static void commonSubstring(List<String> a, List<String> b) {
        int size = a.size();
        for (int i = 0; i < size; i++) {
            boolean hasCommon = hasCommon(a.get(i), b.get(i));
            if (hasCommon) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean hasCommon(String s1, String s2) {
        int s1Length = s1.length();
        int s2Length = s2.length();
        int loopLength = Math.min(s1Length, s2Length);
        for (int i = 0; i < loopLength; i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        List<String> list1 = Lists.newArrayList("ab", "cd", "ef");
        List<String> list2 = Lists.newArrayList("af", "ee", "ef");

        commonSubstring(list1, list2);
    }

}
