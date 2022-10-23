package com.ds.tree.binary;

public class BinaryTreePrinter {

    public static String toStringRep(TreeNode node) {
        return toStringRep(node, "  ");
    }


    private static String toStringRep(TreeNode node, String padding) {
        if (node != null) {
            StringBuilder builder = new StringBuilder();
            builder.append("\n");
            builder.append(padding).append(" ").append(node.data);
            builder.append(toStringRep(node.left, padding + "--"));
            builder.append(toStringRep(node.right, padding + "--"));
            return builder.toString();
        }
        return "";
    }

}
