/*
* Class Name: Calcular                                                         
* Name:       Daniel Arevalo                                                                      
* Date:       14/02/2016     
* Version:    1.0
 */
package co.edu.uniandes.ecos.tarea7.util;

import java.util.LinkedList;

/**
 * Clase encargada de realizar los calculos necesarios para la regresion lineal
 * y la correlacion
 *
 * @author Daniel
 */
public class Calcular {

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
     *
     * @param listaX primer conjunto de valores
     * @param listaY segundo conjunto de valores
     * @return Double con el valor calculado
     * @throws NumberFormatException
     */
    public Double calcularB1(LinkedList<Double> listaX, LinkedList<Double> listaY) throws NumberFormatException {
        if (listaX.size() != listaY.size()) {
            throw new NumberFormatException("Las listas no tienen el mismo tamanio");
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
     *
     * @param listaX primer conjunto de valores
     * @param listaY segundo conjunto de valores
     * @param b1 valor que puede ser calculado llamando al metodo calcularB1
     * @return Double con el valor calculado
     * @throws NumberFormatException
     */
    public Double calcularB0(LinkedList<Double> listaX, LinkedList<Double> listaY, Double b1) throws NumberFormatException {
        if (listaX.size() != listaY.size()) {
            throw new NumberFormatException("Las listas no tienen el mismo tamanio");
        }

        Double promedioX = calcularPromedio(listaX);
        Double promedioY = calcularPromedio(listaY);

        Double b0 = operateBinary(promedioY, operateBinary(b1, promedioX, multiplication), substraction);

        return b0;
    }

    /**
     * Se encarga de calcular el coeficiente r(x,y)
     *
     * @param listaX primer conjunto de valores
     * @param listaY segundo conjunto de valores
     * @return Double con el valor calculado
     * @throws NumberFormatException
     */
    public Double calcularR(LinkedList<Double> listaX, LinkedList<Double> listaY) throws NumberFormatException {
        if (listaX.size() != listaY.size()) {
            throw new NumberFormatException("Las listas no tienen el mismo tamanio");
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
     *
     * @param b0 se puede obtener llamando al metodo calcularB0
     * @param b1 se puede obtener llamando al metodo calcularB1
     * @param xK tamaño de proxy estimado
     * @return Double con el resultado calculado
     * @throws NumberFormatException
     */
    public Double calcularYk(Double b0, Double b1, Double xK) throws NumberFormatException {
        return operateBinary(b0, operateBinary(b1, xK, multiplication), addition);
    }

    /**
     * Se encarga de calcular el promedio de una lista de valores
     *
     * @param listaValores lista de valores
     * @return Double con el promedio
     * @throws NumberFormatException
     */
    public Double calcularPromedio(LinkedList<Double> listaValores) throws NumberFormatException {
        Double sumatoria = new Double(0);

        for (Double valor : listaValores) {
            sumatoria = operateBinary(sumatoria, valor, addition);
        }

        return operateBinary(sumatoria, listaValores.size(), division);
    }

    /**
     * Se encarga de calcular la sumatoria de una lista de valores
     *
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
     *
     * @param listaX primera lista de valores
     * @param listaY segunda lista de valores
     * @return Double con la sumatoria de los productos
     * @throws NumberFormatException
     */
    private Double calcularSumatoriaProductos(LinkedList<Double> listaX, LinkedList<Double> listaY) throws NumberFormatException {
        if (listaX.size() != listaY.size()) {
            throw new NumberFormatException("Las listas no tienen el mismo tamanio");
        }

        Double sumatoria = new Double(0);
        for (int i = 0; i < listaX.size(); i++) {
            sumatoria = operateBinary(sumatoria, operateBinary(listaX.get(i), listaY.get(i), multiplication), addition);
        }
        return sumatoria;
    }

    /**
     * Se encarga de calcular la sumatoria de la potencia de una lista de
     * valores
     *
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
     *
     * @param a valor x de la expresion
     * @param b valor y de la expresion
     * @param op nombre de implementacion que se desea ejecutar
     * @return double con el resultado del calculo
     */
    private double operateBinary(double a, double b, DoubleMath op) {
        return op.operation(a, b);
    }

    /**
     * Se encarga de calcular la funcion de X de un segmento
     *
     * @param xi ancho acumulado del segmento
     * @param dof grados de libertad
     * @return Double con el valor de la funcion
     */
    public Double funcionX(Double xi, Double dof) {
        Double functionX;
        Double gammaFunction;
        Double gammaFunctionNE;
        boolean par = ((dof % 2) == 0);

        if (!par) {
            gammaFunctionNE = factorial(((dof / 2) - 1));
            gammaFunction = factorial(((dof + 1) / 2) - 1);
        } else {
            gammaFunctionNE = factorial(((dof + 1) / 2) - 1);
            gammaFunction = factorial(((dof / 2) - 1));
        }

        gammaFunctionNE *= Math.sqrt(Math.PI);

        functionX = ((par ? gammaFunctionNE : gammaFunction)
                / (Math.pow((dof * Math.PI), 0.5) * (par ? gammaFunction : gammaFunctionNE)))
                * Math.pow((1 + ((Math.pow(xi, 2)) / dof)), ((-1 * (dof + 1)) / 2));

        return functionX;
    }

    /**
     * Calcula el factorial de un numero dado
     *
     * @param numero numero al que se calculara el factorial
     * @return Double con el resultado
     */
    public Double factorial(Double numero) {
        Double resultado = new Double(1);

        if (numero > 0) {
            resultado = numero * factorial(numero - 1);
        }

        return resultado;
    }

    /**
     * Calcula el valor intermedio
     *
     * @param ancho ancho del segmento
     * @param multiplicador multiplicador segun el segmento
     * @param funcion funcion del segmento
     * @return Double con el valor intermedio
     */
    public Double valorIntermedio(Double ancho, Double multiplicador, Double funcion) {
        return (ancho / 3) * multiplicador * funcion;
    }

    /**
     * Calcula el margen de error
     *
     * @param apxValue
     * @param exactValue
     * @return
     */
    public Double marginOfError(Double apxValue, Double exactValue) {
        return Math.abs(apxValue - exactValue) / exactValue;
    }

    /**
     * Calcula el valor de X
     *
     * @param r correlacion
     * @param n numero de datos
     * @return
     */
    public Double getX(Double r, Double n) {
        return (Math.abs(r) * Math.sqrt(n - 2)) / Math.sqrt((1 - Math.pow(r, 2)));
    }

    /**
     * Calcula la significancia
     *
     * @param p probabilidad p
     * @return significancia
     */
    public Double significancia(Double p) {
        System.out.println("P: " + p);
        return (1 - 2 * p);
    }

    /**
     * Calcula la desviacion estandar de un grupo de datos
     *
     * @param listaX Lista de valores X
     * @param listaY Lista de valores Y
     * @param b0 Parametro de regresion lineal
     * @param b1 Parametro de regresion lineal
     * @return desviacion estandar
     * @throws NumberFormatException
     */
    public Double desviacionEstandar(LinkedList<Double> listaX, LinkedList<Double> listaY, Double b0, Double b1) throws NumberFormatException {
        if (listaX.size() != listaY.size()) {
            throw new NumberFormatException("Las listas no tienen el mismo tamanio");
        }

        Double sumatoria = new Double(0);
        for (int i = 0; i < listaX.size(); i++) {
            sumatoria += Math.pow((listaY.get(i) - b0 - (b1 * listaX.get(i))), 2);
        }

        return Math.sqrt((1 / (new Double(listaX.size()) - 2)) * sumatoria);
    }

    /**
     * Calcula el rango de prediccion
     * @param desvEst Desviacion estandar
     * @param xAvg Promedio de valores X
     * @param xK Proxy de tamaño estimado
     * @param x 
     * @param listaX grupo de datos X
     * @return
     */
    public Double rango(Double desvEst, Double xAvg, Double xK, Double x, LinkedList<Double> listaX) {
        Double sumatoria = new Double(0);

        for (Double v : listaX) {
            sumatoria = +Math.pow((v - xAvg), 2);
        }

        return (x * desvEst * Math.sqrt((1 + (1 / new Double(listaX.size()))) + (Math.pow((xK - xAvg), 2) / sumatoria)));
    }
}
