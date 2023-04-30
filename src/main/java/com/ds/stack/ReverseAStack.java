package com.ds.stack;

import java.util.Stack;

public class ReverseAStack {


    private void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        Integer top = stack.pop();
        reverse(stack);
        insertAtBottom(stack, top);
    }


    private void insertAtBottom(Stack<Integer> stack, int number) {
        if (stack.isEmpty()) {
            stack.push(number);
            return;
        }

        int top = stack.pop();
        insertAtBottom(stack, number);
        stack.push(top);
    }


    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(4);
        stack.push(7);
        stack.push(9);


        System.out.println(stack);

        ReverseAStack reverse = new ReverseAStack();
        reverse.reverse(stack);
        //reverse.insertAtBottom(stack, 1);
        System.out.println(stack);

        reverse.reverse(stack);
        System.out.println(stack);

    }


}
