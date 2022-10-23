package com.ds.stack;

import java.util.Stack;

//https://www.youtube.com/watch?v=OpwYmEBcPh0&list=PLDzeHZWIZsTrhXYYtx4z8-u8zA-DzuVsj&index=6
//https://www.codingninjas.com/codestudio/problems/design-a-stack-that-supports-getmin-in-o-1-time-and-o-1-extra-space_842465
public class SpecialStack {
    // Define the data members.


    private Stack<Integer> stack = new Stack<>();
    private int mini = Integer.MAX_VALUE;

    /*----------------- Public Functions of SpecialStack -----------------*/

    void push(int data) {
        // Implement the push() function.
        if (data < mini) {
            stack.push(2 * data - mini);
            mini = data;
        } else {
            stack.push(data);
        }
    }

    int pop() {
        if (stack.isEmpty()) {
            return -1;
        }
        // Implement the pop() function.
        Integer top = stack.pop();
        if (top < mini) {
            int temp = mini;
            mini = 2 * mini - top;
            return temp;
        }
        return top;
    }


    int top() {
        if (stack.isEmpty()) {
            return -1;
        }
        int top = stack.peek();
        if (top < mini) {
            return mini;
        }
        return top;
    }

    boolean isEmpty() {
        return stack.isEmpty();
    }

    int getMin() {
        if (stack.isEmpty()) {
            return -1;
        }
        return mini;
    }


    public static void main(String[] args) {
        SpecialStack specialStack = new SpecialStack();

        specialStack.push(270);
        specialStack.push(202);
        specialStack.push(-320);
        System.out.println(specialStack.getMin());
        System.out.println(specialStack.isEmpty());
        specialStack.push(-752);
        System.out.println(specialStack.top());
        specialStack.push(31);
        System.out.println(specialStack.pop());
        specialStack.push(-87);
        System.out.println(specialStack.top());
        System.out.println(specialStack.pop());
        specialStack.push(-939);
        specialStack.push(-982);
        System.out.println(specialStack.getMin());
        System.out.println(specialStack.pop());
        System.out.println(specialStack.getMin());
        System.out.println(specialStack.top());
        System.out.println(specialStack.getMin());
    }
}
