/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.tarea7.util;

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
    private Double x;
    private Double dof;
    private Double xi;
    private Calcular instance;

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
        x = new Double("1.1");
        dof = new Double("9");
        xi = new Double("0.22");
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
        
        instance = new Calcular();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of calcularB1 method, of class Calcular.
     */
    @Test
    public void testCalcularB1() {
        Double expResult = new Double("1.7279");
        Double result = instance.calcularB1(listaX, listaY);
        assertEquals(expResult, result, delta);
    }

    /**
     * Test of calcularB0 method, of class Calcular.
     */
    @Test
    public void testCalcularB0() {
        Double b1 = instance.calcularB1(listaX, listaY);
        Double expResult = new Double("-22.55");
        Double result = instance.calcularB0(listaX, listaY, b1);
        assertEquals(expResult, result, delta);
    }

    /**
     * Test of calcularR method, of class Calcular.
     */
    @Test
    public void testCalcularR() {
        Double expResult = new Double("0.9545");
        Double result = instance.calcularR(listaX, listaY);
        assertEquals(expResult, result, delta);
    }

    /**
     * Test of calcularYk method, of class Calcular.
     */
    @Test
    public void testCalcularYk() {
        Double b1 = instance.calcularB1(listaX, listaY);
        Double b0 = instance.calcularB0(listaX, listaY, b1);
        Double xK = new Double("386");
        
        Double expResult = new Double("644.429");
        Double result = instance.calcularYk(b0, b1, xK);
        assertEquals(expResult, result, delta);
    }

    /**
     * Test of calcularPromedio method, of class Calcular.
     */
    @Test
    public void testCalcularPromedio() {
        Double expResult = new Double("382.80");
        Double result = instance.calcularPromedio(listaX);
        assertEquals(expResult, result, delta);
    }

    /**
     * Test of funcionX method, of class Calcular.
     */
    @Test
    public void testFuncionX() {
        Double expResult = new Double("0.37");
        Double result = instance.funcionX(xi,dof);
        assertEquals(expResult, result, delta);
    }

    /**
     * Test of factorial method, of class Calcular.
     */
    @Test
    public void testFactorial() {
        Double expResult = new Double("24");
        Double result = instance.factorial(new Double("4"));
        assertEquals(expResult, result);
    }

    /**
     * Test of valorIntermedio method, of class Calcular.
     */
    @Test
    public void testValorIntermedio() {
        Double expResult = new Double("0.0277");
        Double result = instance.valorIntermedio(new Double("0.11"), new Double(2), new Double("0.37777"));
        assertEquals(expResult, result, delta);
    }

    /**
     * Test of marginOfError method, of class Calcular.
     */
    @Test
    public void testMarginOfError() {
        Double apxValue = new Double("1");
        Double exactValue = new Double("2");
        Double expResult = new Double("0.5");
        Double result = instance.marginOfError(apxValue, exactValue);
        assertEquals(expResult, result);
    }

    /**
     * Test of getX method, of class Calcular.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        Double r = new Double("0.9545");
        Double n = new Double("10");
        Double expResult = new Double("9.0527");
        Double result = instance.getX(r, n);
        assertEquals(expResult, result, delta);
    }

    /**
     * Test of significancia method, of class Calcular.
     */
    @Test
    public void testSignificancia() {
        Double p = new Double("0.499991104273253");
        Double expResult = new Double("1.77915E-05");
        Double result = instance.significancia(p);
        assertEquals(expResult, result, delta);
    }

    /**
     * Test of desviacionEstandar method, of class Calcular.
     */
    @Test
    public void testDesviacionEstandar() {
        Double b0 = new Double("-22.552");
        Double b1 = new Double("1.728");
        Double expResult = new Double("197.895");
        Double result = instance.desviacionEstandar(listaX, listaY, b0, b1);
        assertEquals(expResult, result, delta);
    }

    /**
     * Test of rango method, of class Calcular.
     */
    @Test
    public void testRango() {
        Double desvEst = new Double("197.895");;
        Double xAvg = new Double("382.80");;
        Double xK = new Double("386");;
        Double x = new Double("1.108");;
        Double expResult = new Double("229.97");
        Double result = instance.rango(desvEst, xAvg, xK, x, listaX);
        assertEquals(expResult, result, delta);
    }

}
