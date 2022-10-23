package com.ds.graph.matrixandgraph;


import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


//https://www.interviewbit.com/problems/capture-regions-on-board/
public class CaptureRegionOnBoard {


    public void solve(ArrayList<ArrayList<Character>> matrix) {

        Queue<Cell> queue = new LinkedList<>();
        int totalRows = matrix.size();
        int totalColums = matrix.get(0).size();

        //traverse boundy and put all boundary 0 in queue
        addBoundaryRowZeros(0, totalColums, queue, matrix);
        addBoundaryRowZeros(totalRows - 1, totalColums, queue, matrix);
        addBoundaryColZeros(0, totalRows, queue, matrix);
        addBoundaryColZeros(totalColums - 1, totalRows, queue, matrix);


        markAllBoundaryConnectedZero(matrix, queue, totalRows, totalColums);

        for (int row = 0; row < totalRows; row++) {
            for (int col = 0; col < totalColums; col++) {
                Character cellChar = matrix.get(row).get(col);
                if (cellChar.equals('0')) {
                    matrix.get(row).set(col, 'X');
                }
                if (cellChar.equals('#')) {
                    matrix.get(row).set(col, '0');
                }
            }
        }

    }

    private void markAllBoundaryConnectedZero(ArrayList<ArrayList<Character>> matrix, Queue<Cell> queue, int totalRows, int totalColumns) {
        while (!queue.isEmpty()) {

            Cell cell = queue.poll();
            findConnectedZeros(cell, matrix, queue, totalRows, totalColumns);
            matrix.get(cell.x).set(cell.y, '#');
        }
    }

    private void findConnectedZeros(Cell cell, ArrayList<ArrayList<Character>> matrix, Queue<Cell> queue,
                                    int totalRows, int totalColumns) {

        if (isWithinBoundary(cell.x + 1, cell.y, totalRows, totalColumns)) {
            if ('0' == matrix.get(cell.x + 1).get(cell.y)) {
                queue.add(new Cell(cell.x + 1, cell.y));
            }
        }
        if (isWithinBoundary(cell.x - 1, cell.y, totalRows, totalColumns)) {
            if ('0' == matrix.get(cell.x - 1).get(cell.y)) {
                queue.add(new Cell(cell.x - 1, cell.y));
            }
        }
        if (isWithinBoundary(cell.x, cell.y + 1, totalRows, totalColumns)) {
            if ('0' == matrix.get(cell.x).get(cell.y + 1)) {
                queue.add(new Cell(cell.x, cell.y + 1));
            }
        }
        if (isWithinBoundary(cell.x, cell.y - 1, totalRows, totalColumns)) {
            if ('0' == matrix.get(cell.x).get(cell.y - 1)) {
                queue.add(new Cell(cell.x, cell.y - 1));
            }
        }
    }

    private boolean isWithinBoundary(int currentRow, int currentCol, int totalRows, int totalColums) {

        if (currentRow < totalRows && currentRow >= 0 && currentCol < totalColums && currentCol >= 0) {
            return true;
        }
        return false;

    }

    private void addBoundaryRowZeros(int rowNum, int totalColums, Queue<Cell> queue, ArrayList<ArrayList<Character>> matrix) {
        ArrayList<Character> row = matrix.get(rowNum);
        for (int col = 0; col < totalColums; col++) {
            if ('0' == row.get(col)) {
                queue.add(new Cell(rowNum, col));
            }
        }
    }

    private void addBoundaryColZeros(int colNum, int totalRows, Queue<Cell> queue, ArrayList<ArrayList<Character>> matrix) {

        for (int row = 0; row < totalRows; row++) {
            if ('0' == matrix.get(row).get(colNum)) {
                queue.add(new Cell(row, colNum));
            }
        }
    }


    static class Cell {
        int x;
        int y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public static void main(String[] args) {
        CaptureRegionOnBoard region = new CaptureRegionOnBoard();

        ArrayList<ArrayList<Character>> matrix = new ArrayList<>();
        matrix.add(Lists.newArrayList('X', 'X', 'X'));
        matrix.add(Lists.newArrayList('X', '0', 'X'));
        matrix.add(Lists.newArrayList('X', 'X', 'X'));


        region.solve(matrix);

        for (ArrayList<Character> list : matrix) {
            System.out.println(list);
        }


        System.out.println("-------");


        ArrayList<ArrayList<Character>> matrix1 = new ArrayList<>();
        matrix1.add(Lists.newArrayList('X', 'X', 'X', 'X'));
        matrix1.add(Lists.newArrayList('X', '0', '0', 'X'));
        matrix1.add(Lists.newArrayList('X', 'X', '0', 'X'));
        matrix1.add(Lists.newArrayList('X', '0', 'X', 'X'));


        region.solve(matrix1);

        for (ArrayList<Character> list : matrix1) {
            System.out.println(list);
        }
    }
}
