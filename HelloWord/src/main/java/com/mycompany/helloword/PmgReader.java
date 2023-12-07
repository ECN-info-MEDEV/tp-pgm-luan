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
import javax.swing.SwingUtilities;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 *
 * @author hudsonteixeira
 */

public class PmgReader {

    private int[][] img;
    private int picWidth;
    private int picHeight;
    private Graph graphSaved;

    public int[][] getImg() {
        return img;
    }

    public void setImg(int[][] img) {
        this.img = img;
    }

    
    public Graph getGraphSaved() {
        return graphSaved;
    }

    public void setGraphSaved(Graph graphSaved) {
        this.graphSaved = graphSaved;
    }

    public void pmgread(String filePath) {
        try {
            InputStream fileInputStream = new FileInputStream(filePath);
            Scanner scan = new Scanner(fileInputStream);
            // Discard the magic number
            scan.nextLine();
            // Discard the comment line
            scan.nextLine();
            // Read pic width, height and max value
            picWidth = scan.nextInt();
            picHeight = scan.nextInt();
            int maxvalue = scan.nextInt();
            System.out.println("picWidth: " + picWidth);
            System.out.println("picWidth: " + picHeight);
            System.out.println("maxvalue: " + maxvalue);

            // read the image data
            img = new int[picHeight][picWidth];
            for (int row = 0; row < picHeight; row++) {
                for (int col = 0; col < picWidth; col++) {
                    img[row][col] = scan.nextInt();
                }

            }

        } catch (Exception e) {
            System.out.println("error ");
        }
    }

    public void histogramme(JPanel panel) {
        Map<Integer, Integer> mapHistory = new HashMap<>();
        for (int i = 0; i <= 255; i++) {
            mapHistory.put(i, 0);
        }

        // Actualizar los valores reales del histograma
        for (int i = 0; i < picHeight; i++) {
            for (int j = 0; j < picWidth; j++) {
                mapHistory.put(img[i][j], mapHistory.get(img[i][j]) + 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : mapHistory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        panel.setLayout(new BorderLayout());
        Graph graph = new Graph(mapHistory);
        panel.add(new JScrollPane(graph)); // Suponiendo que Graph recibe el mapa y muestra el histograma
        // Ajustar el tamaño del frame automáticamente// Definir acción al cerrar la ventana
        panel.setVisible(true);
        SwingUtilities.invokeLater(() -> {
            this.graphSaved = graph;
        });
    }

    //seuillage
    public void transformMatriz(int x) { 
        for (int i = 0; i < this.img.length; i++) {
            for (int j = 0; j < this.img[i].length; j++) {
                if (this.img[i][j] <= x) {
                    this.img[i][j] = 0;
                } else if (this.img[i][j] > x) {
                    this.img[i][j] = 255;
                }
            }
        }
    }
    
      public boolean verifySameImage(int[][] img2){
        boolean imageVerifier = true;
        if(this.picWidth != img2[0].length || this.picHeight != img2.length){
            imageVerifier = false;
        } else{
            for (int i = 0; i < picHeight; i++) {
                for (int j = 0; j < picWidth; j++) {
                    if(this.img[i][j] != img2[i][j]){
                        imageVerifier = false;
                    }
                }
            }
        }
        return imageVerifier;
    }

      

}
