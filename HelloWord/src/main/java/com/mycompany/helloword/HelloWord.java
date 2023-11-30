/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.helloword;

import java.util.Arrays;

/**
 *
 * @author hudsonteixeira
 */
public class HelloWord {
    static int [] convertirMatrixToArray(int[][] matrix){
    int newArray[] = new int[matrix.length*matrix[0].length];
    for(int i = 0; i < matrix.length; i++) {
        int[] row = matrix[i];
        for(int j = 0; j < row.length; j++) {
            int number = matrix[i][j];
            newArray[i*row.length+j] = number;
        }
    }
    return newArray;
    }
    static void printHistogram(int[] arr, int n) {
    int maxEle = Arrays.stream(arr).max().getAsInt();
    for (int i = maxEle; i >= 0; i--) {
      System.out.format("%2d | ", i);
       
      // if array of element is greater
      // then array it print x
      for (int j = 0; j < n; j++) {
        if (arr[j] >= i) {
          System.out.print(" x ");
        } else {
          System.out.print(" ");
        }
      }
      System.out.println();
    }
    for (int i = 0; i < n + 3; i++) {
      System.out.print("---");
    }
 
    System.out.println();
    System.out.print("     ");
 
    for (int i = 0; i < n; i++) {
      System.out.format("%2d ", arr[i]);
    }
  }
    public static void main(String[] args) {
        PmgReader pmgReader = new PmgReader();
        int[][] data2D = pmgReader.pmgread("./ImagesTestPGM/brain.pgm");
        int[] arrayImage = convertirMatrixToArray(data2D);
        printHistogram(arrayImage,256);
    }
}
