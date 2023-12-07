/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.helloword;

import javax.swing.JPanel;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hudsonteixeira
 */
public class PmgReaderTest {
    
    public PmgReaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of pmgread method, of class PmgReader.
     */
    @Test
    public void testPmgread() {
        System.out.println("pmgread");
        String filePath = "./ImagesTestPGM/test.pgm";
        PmgReader instance = new PmgReader();
        instance.pmgread(filePath);
        int[][] result = instance.getImg();
        int [][] expResult = {{160,60,53},{97,151,99},{67,36,77}};
        Assert.assertArrayEquals(expResult, result);
    }

    /**
     * Test of transformMatriz method, of class PmgReader.
     */
    @Test
    public void testTransformMatriz() {
        System.out.println("transformMatriz");
        int x = 150;
        PmgReader instance = new PmgReader();
        instance.setPicWidth(3);
        instance.setPicHeight(3);
        int[][] testImg = {{160,60,53},{97,151,99},{67,36,77}};
        instance.setImg(testImg);
        instance.transformMatriz(x);
        int[][] result = instance.getImgBinaire();
        int [][] expResult = {{255,0,0},{0,255,0},{0,0,0}};
        Assert.assertArrayEquals(expResult, result);
    }

    /**
     * Test of verifySameImage method, of class PmgReader.
     */
    @Test
    public void testVerifySameImage() {
        System.out.println("verifySameImage");
        int[][] img2 = {{160,60,53},{97,151,99},{67,36,77}};
        int[][] img3 = {{0,60,53},{97,151,99},{67,36,77}};
        PmgReader instance = new PmgReader();
        instance.setPicWidth(3);
        instance.setPicHeight(3);
        int[][] testImg = {{160,60,53},{97,151,99},{67,36,77}};
        instance.setImg(testImg);
        boolean expResult = true;
        boolean expResult2 = false;
        boolean result = instance.verifySameImage(img2);
        boolean result2 = instance.verifySameImage(img3);
        assertEquals(expResult, result);
        assertEquals(expResult2, result2);

    }

    /**
     * Test of saveImage method, of class PmgReader.
     */
    @Test  (expected=IllegalArgumentException.class)
    public void testSaveImage() {
        System.out.println("saveImage");
        PmgReader instance = new PmgReader();
        int[][] testImg = {{255,0,0},{0,255,0},{0,0,0}};
        instance.setImgBinaire(testImg);
        instance.saveImage();
        System.out.println("On a bien sauvé");

    }
    
}
