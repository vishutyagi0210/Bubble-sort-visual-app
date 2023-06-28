package com.example.binarysearchvisual;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchAlgo {
    private int matrixRow = 5;
    private int matrixCol = 5;
    private int [][] matrix = new int[5][5];
    private int[][]  sortedMatrixRowWise;
    private int[][]  sortedMatrixColWise;
    ButtonFunctions buttonFunctions = new ButtonFunctions();

    //these reference variables are used for storing data regarding animations.
    private Queue<HashMap<Integer , Integer>> rows = new LinkedList<>();
    private Queue<HashMap<Integer , Integer>> cols = new LinkedList<>();

    private HashMap<Integer, Integer> colDataForAnim = new HashMap<>();
    private HashMap<Integer , Integer> rowDataForAnim = new HashMap<>();
    public Queue<HashMap<Integer , Integer>> getQueueRow(){
        return rows;
    }

    public Queue<HashMap<Integer , Integer>> getQueueCol(){
        return cols;
    }



    public void setMatrix(int btnId ,int value){
        int position = buttonFunctions.whichButtonId(btnId);
        matrix[position/5][position%5] = value;
    }
    public void sortRowWise(int value){
        int row = value;
        sortedMatrixRowWise = new int[5][5];
        for(int i = 0; i <matrixRow-1; i++){
            // taking record for animations.
            rowDataForAnim = new HashMap<>();
            for(int col = 0; col < matrixRow-i-1; col++){
                if(matrix[row][col] > matrix[row][col+1]){
                    int temp = matrix[row][col];
                    matrix[row][col] = matrix[row][col+1];
                    matrix[row][col+1] = temp;
                    //pushing 1 if swapping done.
                    rowDataForAnim.put(col , 1);
                }else{
                    //pushing 0 if swapping not takes place.
                    rowDataForAnim.put(col , 0);
                }
            }
            //inserting entire hashmap into stack for the single row.
            rows.add(rowDataForAnim);
        }
        for(int a = 0; a<5; a++){
            for(int b = 0; b<5; b++){
                sortedMatrixRowWise[a][b] = matrix[a][b];
            }
        }
    }

    public void sortColWise(int value){
        int col = value;
        for(int i = 0; i<matrixCol-1; i++){
            colDataForAnim = new HashMap<>();
            for(int row = 0; row < matrixCol-i-1; row++){
                if(matrix[row][col] > matrix[row+1][col]){
                    int temp = matrix[row][col];
                    matrix[row][col] = matrix[row+1][col];
                    matrix[row+1][col] = temp;
                    colDataForAnim.put(row , 1);
                }else{
                    colDataForAnim.put(row , 0);
                }
            }
            cols.add(colDataForAnim);
        }
    }


    public int getBinarySearchResult(int toFind){
        int ansIndex = findTheValue(0 , 0+24/2 , 24 , toFind);
        return ansIndex;
    }

    public int findTheValue(int low , int mid , int high , int toFind){

    }

    public int getItemAtIndexFromRowWiseSortedMatrix(int row , int col){
        return sortedMatrixRowWise[row][col];
    }
    public int getItemAtIndexFromOriginalMatrix(int row , int col){
        return matrix[row][col];
    }
}
