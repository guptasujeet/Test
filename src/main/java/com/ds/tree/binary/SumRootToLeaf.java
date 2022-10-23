package com.ds.tree.binary;

import java.util.ArrayList;
import java.util.List;

//https://www.interviewbit.com/problems/sum-root-to-leaf-numbers/
public class SumRootToLeaf {

    public static int sumNumbers(TreeNode root) {
        List<String> paths = new ArrayList<>();

        populateAllRootToLeafPath(root, paths, "");

        int sum = 0;
        for (String path : paths) {
            sum += Integer.valueOf(path);
        }

        return sum % 1003;

    }


    private static void populateAllRootToLeafPath(TreeNode node, List<String> paths, String currentPath) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            paths.add(currentPath + node.data);
            return;
        }

        populateAllRootToLeafPath(node.left, paths, currentPath + node.data);
        populateAllRootToLeafPath(node.right, paths, currentPath + node.data);

    }

}
