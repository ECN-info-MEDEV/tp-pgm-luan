/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.helloword;

/**
 *
 * @author hudsonteixeira
 */
public class HelloWord {

    public static void main(String[] args) {
        PmgReader pmgReader = new PmgReader();
        pmgReader.pmgread("./ImagesTestPGM/brain.pgm");
    }
}
