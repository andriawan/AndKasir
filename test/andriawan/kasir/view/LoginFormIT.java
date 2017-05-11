/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package andriawan.kasir.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;
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
public class LoginFormIT {
    
    public LoginFormIT() {
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
     * Test of getHeaderLogin method, of class LoginForm.
     */
    @Test
    public void testGetHeaderLogin() {
        System.out.println("getHeaderLogin");
        LoginForm instance = new LoginForm();
        JLabel expResult = null;
        JLabel result = instance.getHeaderLogin();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHeaderLogin method, of class LoginForm.
     */
    @Test
    public void testSetHeaderLogin() {
        System.out.println("setHeaderLogin");
        JLabel headerLogin = null;
        LoginForm instance = new LoginForm();
        instance.setHeaderLogin(headerLogin);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHint method, of class LoginForm.
     */
    @Test
    public void testGetHint() {
        System.out.println("getHint");
        LoginForm instance = new LoginForm();
        JLabel expResult = null;
        JLabel result = instance.getHint();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHint method, of class LoginForm.
     */
    @Test
    public void testSetHint() {
        System.out.println("setHint");
        JLabel hint = null;
        LoginForm instance = new LoginForm();
        instance.setHint(hint);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLabelPass method, of class LoginForm.
     */
    @Test
    public void testGetLabelPass() {
        System.out.println("getLabelPass");
        LoginForm instance = new LoginForm();
        JLabel expResult = null;
        JLabel result = instance.getLabelPass();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLabelPass method, of class LoginForm.
     */
    @Test
    public void testSetLabelPass() {
        System.out.println("setLabelPass");
        JLabel labelPass = null;
        LoginForm instance = new LoginForm();
        instance.setLabelPass(labelPass);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLabelPengguna method, of class LoginForm.
     */
    @Test
    public void testGetLabelPengguna() {
        System.out.println("getLabelPengguna");
        LoginForm instance = new LoginForm();
        JLabel expResult = null;
        JLabel result = instance.getLabelPengguna();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLabelPengguna method, of class LoginForm.
     */
    @Test
    public void testSetLabelPengguna() {
        System.out.println("setLabelPengguna");
        JLabel labelPengguna = null;
        LoginForm instance = new LoginForm();
        instance.setLabelPengguna(labelPengguna);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLoginButton method, of class LoginForm.
     */
    @Test
    public void testGetLoginButton() {
        System.out.println("getLoginButton");
        LoginForm instance = new LoginForm();
        JButton expResult = null;
        JButton result = instance.getLoginButton();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLoginButton method, of class LoginForm.
     */
    @Test
    public void testSetLoginButton() {
        System.out.println("setLoginButton");
        JButton loginButton = null;
        LoginForm instance = new LoginForm();
        instance.setLoginButton(loginButton);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLoginPanel method, of class LoginForm.
     */
    @Test
    public void testGetLoginPanel() {
        System.out.println("getLoginPanel");
        LoginForm instance = new LoginForm();
        JPanel expResult = null;
        JPanel result = instance.getLoginPanel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLoginPanel method, of class LoginForm.
     */
    @Test
    public void testSetLoginPanel() {
        System.out.println("setLoginPanel");
        JPanel loginPanel = null;
        LoginForm instance = new LoginForm();
        instance.setLoginPanel(loginPanel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getToolBarLogin method, of class LoginForm.
     */
    @Test
    public void testGetToolBarLogin() {
        System.out.println("getToolBarLogin");
        LoginForm instance = new LoginForm();
        JToolBar expResult = null;
        JToolBar result = instance.getToolBarLogin();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setToolBarLogin method, of class LoginForm.
     */
    @Test
    public void testSetToolBarLogin() {
        System.out.println("setToolBarLogin");
        JToolBar toolBarLogin = null;
        LoginForm instance = new LoginForm();
        instance.setToolBarLogin(toolBarLogin);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTxtFieldPengguna method, of class LoginForm.
     */
    @Test
    public void testGetTxtFieldPengguna() {
        System.out.println("getTxtFieldPengguna");
        LoginForm instance = new LoginForm();
        JTextField expResult = null;
        JTextField result = instance.getTxtFieldPengguna();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTxtFieldPengguna method, of class LoginForm.
     */
    @Test
    public void testSetTxtFieldPengguna() {
        System.out.println("setTxtFieldPengguna");
        JTextField txtFieldPengguna = null;
        LoginForm instance = new LoginForm();
        instance.setTxtFieldPengguna(txtFieldPengguna);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTxtPass method, of class LoginForm.
     */
    @Test
    public void testGetTxtPass() {
        System.out.println("getTxtPass");
        LoginForm instance = new LoginForm();
        JPasswordField expResult = null;
        JPasswordField result = instance.getTxtPass();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTxtPass method, of class LoginForm.
     */
    @Test
    public void testSetTxtPass() {
        System.out.println("setTxtPass");
        JPasswordField txtPass = null;
        LoginForm instance = new LoginForm();
        instance.setTxtPass(txtPass);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
