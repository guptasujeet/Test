package com.ds.tree.binary.traversal;

import com.ds.tree.binary.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
    Morris traversal has O(1) space complexity
    Compared to recursive where space complexity is O(n)
 */
public class MorrisInOrderTraversal {


    public static List<String> morrisInOrderTraversalWorking(TreeNode root) {
        TreeNode current = root;

        List<String> traversal = new ArrayList<>();
        while (current != null) {
            //if left of current is null
            if (current.left == null) {
                traversal.add(current.data);
                current = current.right;
            } else {
                //if left of current is not null , find the predecessor
                //ie first got to left and then all the way to right
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }
                if (predecessor.right == null) {
                    predecessor.right = current;
                    current = current.left;
                } else {
                    predecessor.right = null;
                    traversal.add(current.data);
                    current = current.right;
                }
            }
        }

        return traversal;
    }


    //revision
    public static List<String> morrisInOrderTraversal(TreeNode root) {

        List<String> inOrder = new ArrayList<>();
        TreeNode current = root;

        while (current != null) {

            if (current.left == null) {
                inOrder.add(current.data);
                current = current.right;
            } else {
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }
                if (predecessor.right == null) {
                    predecessor.right = current;
                    current = current.left;
                } else {
                    predecessor.right = null;
                    inOrder.add(current.data);
                    current = current.right;
                }
            }

        }
        return inOrder;
    }

}
