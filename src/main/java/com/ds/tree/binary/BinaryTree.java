package com.ds.tree.binary;

import com.ds.tree.MinTimeToBurnTree;
import com.ds.tree.binary.traversal.BoundaryTraversal;
import com.ds.tree.binary.traversal.MorrisInOrderTraversal;
import com.ds.tree.binary.traversal.VerticalOrderTraversal;
import com.ds.tree.binary.traversal.ZigZagTraversal;
import com.ds.tree.bst.LargestBSTInBinaryTree;
import com.util.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class BinaryTree {

    public TreeNode root;


    public BinaryTree(TreeNode root) {
        this.root = root;
    }


    public int getHeight() {
        return getHeightOfNode(root);
    }

    private int getHeightOfNode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeightOfNode(root.left);
        int rightHeight = getHeightOfNode(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }


    public void printInTraversalData() {
        System.out.println("In Order -> ");
        printInTraversalData(root);
        System.out.println();
    }

    private void printInTraversalData(TreeNode node) {
        if (node == null) {
            return;
        }
        printInTraversalData(node.left);
        System.out.print(node.data + " -> ");
        printInTraversalData(node.right);
    }

    public void printPreTraversalData() {
        System.out.println("Pre Order -> ");
        printPreTraversalData(root);
        System.out.println();
    }

    private void printPreTraversalData(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " -> ");
        printPreTraversalData(node.left);
        printPreTraversalData(node.right);
    }

    public void printPostTraversalData() {
        System.out.println("Post Order -> ");
        printPostTraversalData(root);
        System.out.println();
    }

    private void printPostTraversalData(TreeNode node) {
        if (node == null) {
            return;
        }
        printPostTraversalData(node.left);
        printPostTraversalData(node.right);
        System.out.print(node.data + " -> ");
    }


    public void printLevelOrderTraversal() {
        System.out.println("Level Order -> ");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.print(current.data + " -> ");
            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }

        System.out.println();
    }


    public List<String> getLeftViewOfTree() {
        Queue<Pair<Integer, TreeNode>> queue = new LinkedList<>();
        queue.add(Pair.of(0, root));
        Integer currentLevel = -1;
        List<String> leftViewData = new LinkedList<>();
        while (!queue.isEmpty()) {
            Pair<Integer, TreeNode> current = queue.poll();
            Integer nodeLevel = current.getKey();
            TreeNode currentNode = current.getValue();
            if (nodeLevel > currentLevel) {
                currentLevel = nodeLevel;
                leftViewData.add(currentNode.data);
            }
            //System.out.print(currentNode.data + " -> ");
            if (currentNode.left != null) {
                queue.add(Pair.of((nodeLevel + 1), currentNode.left));
            }

            if (currentNode.right != null) {
                queue.add(Pair.of((nodeLevel + 1), currentNode.right));
            }
        }

        //System.out.println();
        return leftViewData;
    }

    public boolean isBalanced() {
        return isBalanced(root).getFirst();
    }

    private Pair<Boolean, Integer> isBalanced(TreeNode node) {
        if (node == null) {
            return Pair.of(true, 0);
        }
        Pair<Boolean, Integer> leftPair = isBalanced(node.left);
        Pair<Boolean, Integer> rightPair = isBalanced(node.right);

        boolean isBalanced = Math.abs(leftPair.getSecond() - rightPair.getSecond()) <= 1;
        int height = Math.max(leftPair.getSecond(), rightPair.getSecond()) + 1;
        if (leftPair.getFirst() && rightPair.getFirst() && isBalanced) {
            return Pair.of(true, height);
        } else {
            return Pair.of(false, height);
        }
    }

    public boolean isSumTree() {
        return isSumTree(root).getFirst();
    }

    private Pair<Boolean, Integer> isSumTree(TreeNode node) {
        if (node == null) {
            return Pair.of(true, 0);
        }
        int data = Integer.valueOf(node.data);
        if (node.left == null && node.right == null) {
            return Pair.of(true, data);
        }

        Pair<Boolean, Integer> leftPair = isSumTree(node.left);
        Pair<Boolean, Integer> rightPair = isSumTree(node.right);

        int leftSum = leftPair.getSecond();
        int rightSum = rightPair.getSecond();


        boolean isSumEqual = data == leftSum + rightSum;

        if (isSumEqual && leftPair.getFirst() && rightPair.getFirst()) {
            return Pair.of(true, 2 * data);
        } else {
            return Pair.of(false, leftSum + rightSum);
        }

    }

    public int getDiameter() {
        return getDiameter(root).getFirst();
    }

    private Pair<Integer, Integer> getDiameter(TreeNode node) {
        if (node == null) {
            return Pair.of(0, 0);
        }

        Pair<Integer, Integer> leftPair = getDiameter(node.left);
        Pair<Integer, Integer> rightPair = getDiameter(node.right);
        int leftDiameter = leftPair.getFirst();
        int rightDiameter = rightPair.getFirst();

        int leftHeight = leftPair.getSecond();
        int rightHeight = rightPair.getSecond();

        int bothSideDiameter = leftHeight + 1 + rightHeight;

        int diameter = Math.max(leftDiameter, Math.max(rightDiameter, bothSideDiameter));
        return Pair.of(diameter, Math.max(leftHeight, rightHeight) + 1);
    }

    public List<String> getZigZagTraversal() {
        return ZigZagTraversal.zigZag(root);
    }

    public List<String> getBoundaryTraversal() {
        return BoundaryTraversal.boundaryTraversal(root);
    }


    public List<String> getVerticalTraversal() {
        return VerticalOrderTraversal.verticalOrderTraversal(root);
    }

    public int getLongestPathSum() {
        return LongestPathSum.getLongestPathSum(root).getFirst();
    }

    public TreeNode getLowestCommonAncestor(TreeNode node1, TreeNode node2) {
        return NodeAncestor.getLowestCommonAncestor(root, node1, node2);
    }

    public TreeNode getKthAncestor(TreeNode searchNode, int kAncestor) {
        return NodeAncestor.getKthAncestor(root, searchNode, new AtomicInteger(kAncestor));
    }

    public int getMaximumSumNonAdjacentNode() {
        return MaximumSumTreeAdjacentNodeNotAllowed.getMaximumSumNonAdjacentNode(root);
    }

    public int getTimeToBurnNode(String burnNode) {
        return MinTimeToBurnTree.timeToBurn(root, burnNode);
    }

    public List<String> morrisInOrderTraversal() {
        return MorrisInOrderTraversal.morrisInOrderTraversal(root);
    }

    public int largestBSTLengthInBinaryTree() {
        return new LargestBSTInBinaryTree().largestBSTLength(root);
    }

    public int getSumKPath(int sumK) {
        return SumKPath.sumKPath(root, sumK);
    }

    public int sumRootToLeaf() {
        return SumRootToLeaf.sumNumbers(root);
    }

    @Override
    public String toString() {
        return BinaryTreePrinter.toStringRep(root);
    }

}
