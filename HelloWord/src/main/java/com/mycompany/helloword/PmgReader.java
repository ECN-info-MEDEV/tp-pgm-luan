/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.helloword;
import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/**
 *
 * @author hudsonteixeira
 */

public class PmgReader {
    private int[][] img;

    public void pmgread(String filePath){
        try{
            InputStream fileInputStream = new FileInputStream(filePath);
            Scanner scan = new Scanner(fileInputStream);
            // Discard the magic number
            scan.nextLine();
            // Discard the comment line
            scan.nextLine();
            // Read pic width, height and max value
            int picWidth = scan.nextInt();
            int picHeight = scan.nextInt();
            int maxvalue = scan.nextInt();

            fileInputStream.close();

             // Now parse the file as binary data
             fileInputStream = new FileInputStream(filePath);
             DataInputStream dis = new DataInputStream(fileInputStream);

             // look for 4 lines (i.e.: the header) and discard them
             int numnewlines = 4;
             while (numnewlines > 0) {
                 char c;
                 do {
                     c = (char)(dis.readUnsignedByte());
                 } while (c != '\n');
                 numnewlines--;
             }

             // read the image data
             int[][] data2D = new int[picHeight][picWidth];
             for (int row = 0; row < picHeight; row++) {
                 for (int col = 0; col < picWidth; col++) {
                     data2D[row][col] = dis.readUnsignedByte();
                 }
             }
             this.img = data2D;
        } catch (Exception e) {    
            System.out.println("error ");
        }
    }
    
    public void histogramme(JPanel frame){
        Map<Integer, Integer> mapHistory = new TreeMap<Integer, Integer>();
        for (int c = 0; c < img.length; c++) {
            for (int r = 0; r < img[c].length; r++) {
                int value = img[c][r];
                int amount = 0;
                if (mapHistory.containsKey(value)) {
                    amount = mapHistory.get(value);
                    amount++;
                } else {
                    amount = 1;
                }
                mapHistory.put(value, amount);
            }
        }
        System.out.println(mapHistory.size());
        frame.add(new JScrollPane(new Graph(mapHistory)));
        frame.setVisible(true);
    }
}

