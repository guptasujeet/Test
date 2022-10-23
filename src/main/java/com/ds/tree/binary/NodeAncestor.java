package com.ds.tree.binary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class NodeAncestor {

    public static TreeNode getLowestCommonAncestor(TreeNode node, TreeNode n1, TreeNode n2) {
        if (node == null) {
            return null;
        }

        if (node.data.equals(n1.data) || node.data.equals(n2.data)) {
            return node;
        }

        TreeNode leftAns = getLowestCommonAncestor(node.left, n1, n2);
        TreeNode rightAns = getLowestCommonAncestor(node.right, n1, n2);

        if (leftAns != null && rightAns != null) {
            return node;
        } else if (leftAns != null && rightAns == null) {
            return leftAns;
        } else if (leftAns == null && rightAns != null) {
            return rightAns;
        } else {
            return null;
        }
    }


    public static TreeNode getKthAncestor(TreeNode root, TreeNode searchNode, AtomicInteger k) {
        TreeNode result = solveForKthAncestor(root, searchNode, k);
        if (result == null) {
            return null;
        }
        //looks like below not needed as if true or false result is returned without any change
        /*if (result.data.equals(root.data)) {
            //returning top
            return result;
        }*/
        return result;
    }

    public static TreeNode solveForKthAncestor(TreeNode node, TreeNode searchNode, AtomicInteger k) {
        if (node == null) {
            return null;
        }

        if (node.data.equals(searchNode.data)) {
            return node;
        }

        TreeNode leftVal = solveForKthAncestor(node.left, searchNode, k);
        TreeNode rightVal = solveForKthAncestor(node.right, searchNode, k);


        if (leftVal != null && rightVal == null) {
            int level = k.decrementAndGet();
            if (level <= 0) {
                k.set(Integer.MAX_VALUE);
                return node;
            }
            return leftVal;
        }

        if (leftVal == null && rightVal != null) {
            int level = k.decrementAndGet();
            if (level <= 0) {
                k.set(Integer.MAX_VALUE);
                return node;
            }
            return rightVal;
        }

        return null;

    }


    public static TreeNode getLowestCommonAncestor2(TreeNode node, TreeNode n1, TreeNode n2) {

        List<String> ancestor1 = new ArrayList<>(Collections.singleton(n1.data));
        List<String> ancestor2 = new ArrayList<>(Collections.singleton(n2.data));
        getAncestor(node, n1, ancestor1);
        getAncestor(node, n2, ancestor2);

        String ans = "";
        int size = ancestor1.size();
        for (int i = 0; i < size; i++) {
            String a1 = ancestor1.get(i);
            if (ancestor2.contains(a1)) {
                ans = a1;
                break;
            }
        }
        return new TreeNode(ans);
    }

    public static boolean getAncestor(TreeNode node, TreeNode search, List<String> ancestors) {
        if (node == null) {
            return false;
        }
        if (node.data.equals(search.data)) {
            return true;
        }
        if (node.left != null) {
            if (getAncestor(node.left, search, ancestors)) {
                ancestors.add(node.data);
                return true;
            }
        }
        if (node.right != null) {
            if (getAncestor(node.right, search, ancestors)) {
                ancestors.add(node.data);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {


    }
}
