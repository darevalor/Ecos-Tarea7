/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.tarea3.util;

import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel
 */
public class CalcularTest {

    private LinkedList<Double> listaX;
    private LinkedList<Double> listaY;
    private Double delta;

    public CalcularTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        delta = new Double("0.1");
        listaX = new LinkedList<>();
        listaX.add(new Double(130));
        listaX.add(new Double(650));
        listaX.add(new Double(99));
        listaX.add(new Double(150));
        listaX.add(new Double(128));
        listaX.add(new Double(302));
        listaX.add(new Double(95));
        listaX.add(new Double(945));
        listaX.add(new Double(368));
        listaX.add(new Double(961));
        
        listaY = new LinkedList<>();
        listaY.add(new Double(186));
        listaY.add(new Double(699));
        listaY.add(new Double(132));
        listaY.add(new Double(272));
        listaY.add(new Double(291));
        listaY.add(new Double(331));
        listaY.add(new Double(199));
        listaY.add(new Double(1890));
        listaY.add(new Double(788));
        listaY.add(new Double(1601));
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of calcularB1 method, of class Calcular.
     */
    @Test
    public void testCalcularB1() {
        System.out.println("calcularB1");
//        LinkedList<Double> listaX = null;
//        LinkedList<Double> listaY = null;
        Calcular instance = new Calcular();
        Double expResult = new Double("1.7279");
        Double result = instance.calcularB1(listaX, listaY);
        assertEquals(expResult, result, delta);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of calcularB0 method, of class Calcular.
     */
    @Test
    public void testCalcularB0() {
        System.out.println("calcularB0");
//        LinkedList<Double> listaX = null;
//        LinkedList<Double> listaY = null;
        
        Calcular instance = new Calcular();
        Double b1 = instance.calcularB1(listaX, listaY);
        Double expResult = new Double("-22.55");
        Double result = instance.calcularB0(listaX, listaY, b1);
        assertEquals(expResult, result, delta);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of calcularR method, of class Calcular.
     */
    @Test
    public void testCalcularR() {
        System.out.println("calcularR");
//        LinkedList<Double> listaX = null;
//        LinkedList<Double> listaY = null;
        Calcular instance = new Calcular();
        Double expResult = new Double("0.9545");
        Double result = instance.calcularR(listaX, listaY);
        assertEquals(expResult, result, delta);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of calcularYk method, of class Calcular.
     */
    @Test
    public void testCalcularYk() {
        System.out.println("calcularYk");
        Calcular instance = new Calcular();
        Double b1 = instance.calcularB1(listaX, listaY);
        Double b0 = instance.calcularB0(listaX, listaY, b1);
        
        Double expResult = new Double("644.429");
        Double result = instance.calcularYk(b0, b1);
        assertEquals(expResult, result, delta);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

}
