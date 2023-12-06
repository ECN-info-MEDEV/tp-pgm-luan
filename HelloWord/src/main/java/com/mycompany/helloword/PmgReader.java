/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.helloword;
import java.awt.BorderLayout;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import javax.swing.JFrame;
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
            System.out.println("picWidth: " + picWidth);
            System.out.println("picWidth: " + picHeight);
            System.out.println("maxvalue: " + maxvalue);


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
             img = new int[picHeight][picWidth];
             for (int row = 0; row < picHeight; row++) {
                 for (int col = 0; col < picWidth; col++) {
                     img[row][col] = dis.readUnsignedByte();
                 }
             }
        } catch (Exception e) {    
            System.out.println("error ");
        }
    }
    
    public void histogramme(JPanel panel) {
        Map<Integer, Integer> mapHistory = new HashMap<>();
        for (int i = 1; i <= 255; i++) {
            mapHistory.put(i, 0);
        }

        // Actualizar los valores reales del histograma
        for (int[] row : img) {
            for (int value : row) {
                if (value >= 1 && value <= 255) {
                    mapHistory.put(value, mapHistory.get(value) + 1);
                }
            }
        }

        System.out.println("Cantidad de valores diferentes: " + mapHistory.size());

        panel.setLayout(new BorderLayout());
        panel.add(new JScrollPane(new Graph(mapHistory))); // Suponiendo que Graph recibe el mapa y muestra el histograma
       // Ajustar el tamaño del frame automáticamente// Definir acción al cerrar la ventana
        panel.setVisible(true);
    }
}

