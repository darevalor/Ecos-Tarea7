/*
* Class Name: Calcular                                                         
* Name:       Daniel Arevalo                                                                      
* Date:       14/02/2016     
* Version:    1.0
*/
package co.edu.uniandes.ecos.tarea3.util;

import java.util.LinkedList;

/**
 * Clase encargada de realizar los calculos necesarios para la regresion
 * lineal y la correlacion
 * @author Daniel
 */
public class Calcular {

    private static final double Xk = 386;

    interface DoubleMath {

        double operation(double a, double b);
    }

    DoubleMath addition = (a, b) -> a + b;
    DoubleMath substraction = (a, b) -> a - b;
    DoubleMath multiplication = (a, b) -> a * b;
    DoubleMath division = (a, b) -> a / b;
    DoubleMath pow = (a, b) -> Math.pow(a, b);

    /**
     * Se encarga de calcular el parametro de regresion lineal B1
     * @param listaX primer conjunto de valores
     * @param listaY segundo conjunto de valores
     * @return Double con el valor calculado
     * @throws NumberFormatException 
     */
    public Double calcularB1(LinkedList<Double> listaX, LinkedList<Double> listaY) throws NumberFormatException {
        if (listaX.size() != listaY.size()) {
            throw new NumberFormatException("Las listas no tienen el mismo tamaño");
        }

        Double n = new Double(listaX.size());
        Double promedioX = calcularPromedio(listaX);
        Double promedioY = calcularPromedio(listaY);

        Double b1 = operateBinary(operateBinary(calcularSumatoriaProductos(listaX, listaY), 
                (n * promedioX * promedioY), substraction), operateBinary(calcularSumatoriaPotencias(listaX), 
                        operateBinary(n, operateBinary(promedioX, 2, pow), multiplication), substraction), division);

        return b1;
    }

    /**
     * Se encarga de calcular el parametro de regresion lineal B0
     * @param listaX primer conjunto de valores
     * @param listaY segundo conjunto de valores
     * @param b1 valor que puede ser calculado llamando al metodo calcularB1
     * @return Double con el valor calculado
     * @throws NumberFormatException 
     */
    public Double calcularB0(LinkedList<Double> listaX, LinkedList<Double> listaY, Double b1) throws NumberFormatException {
        if (listaX.size() != listaY.size()) {
            throw new NumberFormatException("Las listas no tienen el mismo tamaño");
        }

        Double promedioX = calcularPromedio(listaX);
        Double promedioY = calcularPromedio(listaY);

        Double b0 = operateBinary(promedioY, operateBinary(b1, promedioX, multiplication), substraction);

        return b0;
    }

    /**
     * Se encarga de calcular el coeficiente r(x,y)
     * @param listaX primer conjunto de valores
     * @param listaY segundo conjunto de valores
     * @return Double con el valor calculado
     * @throws NumberFormatException 
     */
    public Double calcularR(LinkedList<Double> listaX, LinkedList<Double> listaY) throws NumberFormatException {
        if (listaX.size() != listaY.size()) {
            throw new NumberFormatException("Las listas no tienen el mismo tamaño");
        }

        Double n = new Double(listaX.size());

        Double r = operateBinary(operateBinary(operateBinary(n, calcularSumatoriaProductos(listaX, listaY), multiplication),
                operateBinary(calcularSumatoria(listaX), calcularSumatoria(listaY), multiplication), substraction),
                Math.sqrt(operateBinary(operateBinary(operateBinary(n, calcularSumatoriaPotencias(listaX), multiplication),
                        operateBinary(calcularSumatoria(listaX), 2, pow), substraction), operateBinary(operateBinary(n, calcularSumatoriaPotencias(listaY), multiplication),
                        operateBinary(calcularSumatoria(listaY), 2, pow), substraction), multiplication)), division);

        return r;
    }

    /**
     * Encargado de calcular el valor Yk
     * @param b0 se puede obtener llamando al metodo calcularB0
     * @param b1 se puede obtener llamando al metodo calcularB1
     * @return Double con el resultado calculado
     * @throws NumberFormatException 
     */
    public Double calcularYk(Double b0, Double b1) throws NumberFormatException {
        return operateBinary(b0, operateBinary(b1, Xk, multiplication), addition);
    }

    /**
     * Se encarga de calcular el promedio de una lista de valores
     * @param listaValores lista de valores
     * @return Double con el promedio
     * @throws NumberFormatException 
     */
    private Double calcularPromedio(LinkedList<Double> listaValores) throws NumberFormatException {
        Double sumatoria = new Double(0);

        for (Double valor : listaValores) {
            sumatoria = operateBinary(sumatoria, valor, addition);
        }

        return operateBinary(sumatoria, listaValores.size(), division);
    }

    /**
     * Se encarga de calcular la sumatoria de una lista de valores
     * @param listaValores lista de valores
     * @return Double con la sumatoria
     */
    private Double calcularSumatoria(LinkedList<Double> listaValores) {
        Double sumatoria = new Double(0);

        for (Double valor : listaValores) {
            sumatoria = operateBinary(sumatoria, valor, addition);
        }

        return sumatoria;
    }

    /**
     * Se encarga de calcular la sumatoria de los productos de dos grupos de
     * datos
     * @param listaX primera lista de valores
     * @param listaY segunda lista de valores
     * @return Double con la sumatoria de los productos
     * @throws NumberFormatException 
     */
    private Double calcularSumatoriaProductos(LinkedList<Double> listaX, LinkedList<Double> listaY) throws NumberFormatException {
        if (listaX.size() != listaY.size()) {
            throw new NumberFormatException("Las listas no tienen el mismo tamaño");
        }

        Double sumatoria = new Double(0);
        for (int i = 0; i < listaX.size(); i++) {
            sumatoria = operateBinary(sumatoria, operateBinary(listaX.get(i), listaY.get(i), multiplication), addition);
        }
        return sumatoria;
    }

    /**
     * Se encarga de calcular la sumatoria de la potencia de una lista de valores
     * @param listaValores lista de valores
     * @return Double con la sumatoria de potencias
     * @throws NumberFormatException 
     */
    private Double calcularSumatoriaPotencias(LinkedList<Double> listaValores) throws NumberFormatException {

        Double sumatoria = new Double(0);
        for (Double valor : listaValores) {
            sumatoria = operateBinary(sumatoria, operateBinary(valor, 2, pow), addition);
        }
        return sumatoria;
    }

    /**
     * Encargado con una expresion lambda para realizar distintos calculos
     * @param a valor x de la expresion
     * @param b valor y de la expresion
     * @param op nombre de implementacion que se desea ejecutar
     * @return double con el resultado del calculo
     */
    private double operateBinary(double a, double b, DoubleMath op) {
        return op.operation(a, b);
    }
}
