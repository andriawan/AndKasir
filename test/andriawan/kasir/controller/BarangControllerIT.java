/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.controller;

import andriawan.kasir.model.Barang;
import andriawan.kasir.view.EditorBarangForm;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andriawan
 */
public class BarangControllerIT {
    
    public BarangControllerIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllBarang method, of class BarangController.
     */
    @Test
    public void testGetAllBarang() throws Exception {
        System.out.println("getAllBarang");
        BarangController instance = new BarangController();
        List<Barang> expResult = null;
        List<Barang> result = instance.getAllBarang();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBarang method, of class BarangController.
     */
    @Test
    public void testGetBarang() throws Exception {
        System.out.println("getBarang");
        int kodeBarang = 0;
        BarangController instance = new BarangController();
        Barang expResult = null;
        Barang result = instance.getBarang(kodeBarang);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of multiSearch method, of class BarangController.
     */
    @Test
    public void testMultiSearch() throws Exception {
        System.out.println("multiSearch");
        String a = "";
        String b = "";
        String c = "";
        String d = "";
        BarangController instance = new BarangController();
        List<Barang> expResult = null;
        List<Barang> result = instance.multiSearch(a, b, c, d);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInsertFormInstance method, of class BarangController.
     */
    @Test
    public void testGetInsertFormInstance() {
        System.out.println("getInsertFormInstance");
        EditorBarangForm expResult = null;
        EditorBarangForm result = BarangController.getInsertFormInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUpdateFormInstance method, of class BarangController.
     */
    @Test
    public void testGetUpdateFormInstance() {
        System.out.println("getUpdateFormInstance");
        EditorBarangForm expResult = null;
        EditorBarangForm result = BarangController.getUpdateFormInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertBarang method, of class BarangController.
     */
    @Test
    public void testInsertBarang() throws Exception {
        System.out.println("insertBarang");
        Barang br = null;
        BarangController instance = new BarangController();
        instance.insertBarang(br);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateBarang method, of class BarangController.
     */
    @Test
    public void testUpdateBarang() throws Exception {
        System.out.println("updateBarang");
        Barang br = null;
        BarangController instance = new BarangController();
        instance.updateBarang(br);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateStok method, of class BarangController.
     */
    @Test
    public void testUpdateStok() throws Exception {
        System.out.println("updateStok");
        Barang br = null;
        int jumlah = 0;
        BarangController instance = new BarangController();
        instance.updateStok(br, jumlah);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hapusBarang method, of class BarangController.
     */
    @Test
    public void testHapusBarang() throws Exception {
        System.out.println("hapusBarang");
        int kode = 0;
        BarangController instance = new BarangController();
        instance.hapusBarang(kode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
