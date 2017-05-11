/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.dao.impl;

import andriawan.kasir.model.DetailTransaksi;
import andriawan.kasir.model.Transaksi;
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
public class TransaksiDaoImplIT {
    
    public TransaksiDaoImplIT() {
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
     * Test of getAllTransaksi method, of class TransaksiDaoImpl.
     */
    @Test
    public void testGetAllTransaksi() {
        System.out.println("getAllTransaksi");
        TransaksiDaoImpl instance = new TransaksiDaoImpl();
        List<Transaksi> expResult = null;
        List<Transaksi> result = instance.getAllTransaksi();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDetailTransaksi method, of class TransaksiDaoImpl.
     */
    @Test
    public void testGetDetailTransaksi() {
        System.out.println("getDetailTransaksi");
        int kode = 0;
        TransaksiDaoImpl instance = new TransaksiDaoImpl();
        List<DetailTransaksi> expResult = null;
        List<DetailTransaksi> result = instance.getDetailTransaksi(kode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTransaksi method, of class TransaksiDaoImpl.
     */
    @Test
    public void testGetTransaksi() {
        System.out.println("getTransaksi");
        int kode = 0;
        TransaksiDaoImpl instance = new TransaksiDaoImpl();
        Transaksi expResult = null;
        Transaksi result = instance.getTransaksi(kode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateTransaksi method, of class TransaksiDaoImpl.
     */
    @Test
    public void testUpdateTransaksi() {
        System.out.println("updateTransaksi");
        Transaksi transaksi = null;
        int id = 0;
        TransaksiDaoImpl instance = new TransaksiDaoImpl();
        instance.updateTransaksi(transaksi, id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteTransaksi method, of class TransaksiDaoImpl.
     */
    @Test
    public void testDeleteTransaksi() {
        System.out.println("deleteTransaksi");
        int kodeTransaksi = 0;
        TransaksiDaoImpl instance = new TransaksiDaoImpl();
        instance.deleteTransaksi(kodeTransaksi);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertTransaksi method, of class TransaksiDaoImpl.
     */
    @Test
    public void testInsertTransaksi() {
        System.out.println("insertTransaksi");
        Transaksi transaksi = null;
        TransaksiDaoImpl instance = new TransaksiDaoImpl();
        instance.insertTransaksi(transaksi);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllTransaksiForTabel method, of class TransaksiDaoImpl.
     */
    @Test
    public void testGetAllTransaksiForTabel() {
        System.out.println("getAllTransaksiForTabel");
        TransaksiDaoImpl instance = new TransaksiDaoImpl();
        List<Transaksi> expResult = null;
        List<Transaksi> result = instance.getAllTransaksiForTabel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastRecord method, of class TransaksiDaoImpl.
     */
    @Test
    public void testGetLastRecord() {
        System.out.println("getLastRecord");
        TransaksiDaoImpl instance = new TransaksiDaoImpl();
        Transaksi expResult = null;
        Transaksi result = instance.getLastRecord();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertDetailTransaksi method, of class TransaksiDaoImpl.
     */
    @Test
    public void testInsertDetailTransaksi() {
        System.out.println("insertDetailTransaksi");
        DetailTransaksi detailTransaksi = null;
        TransaksiDaoImpl instance = new TransaksiDaoImpl();
        instance.insertDetailTransaksi(detailTransaksi);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
