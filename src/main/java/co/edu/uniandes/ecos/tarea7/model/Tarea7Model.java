/*
* Class Name: Tarea7Model                                                         
* Name:       Daniel Arevalo                                                                      
* Date:       14/02/2016     
* Version:    1.0
*/
package co.edu.uniandes.ecos.tarea7.model;

import java.util.List;

/**
 * Clase encargada de guardar los resultados de un caso de estudio
 * @author Daniel
 */
public class Tarea7Model {

    private Double b0;
    private Double b1;
    private Double r;
    private Double yk;
    private int numberTest;
    private String resultadosHtml;
    private Double segmentWidth;
    private Double degreesOfFreedom;
    private Double integralValue;
    private Double x;
    private Double xK;
    private Double xAvg;
    private List<TDistributionModel> intermediateValues;
    private Double numberOfSegments;
    private Double significance;
    private Double range;
    private Double standardDeviation;
    private Double upi;
    private Double lpi;
    
    public Tarea7Model(){
    }

    /**
     * retorna el valor B0
     * @return 
     */
    public Double getB0() {
        return b0;
    }

    /**
     * Establece el valor B0
     * @param b0 
     */
    public void setB0(Double b0) {
        this.b0 = b0;
    }

    /**
     * retorna el valor B1
     * @return 
     */
    public Double getB1() {
        return b1;
    }

    /**
     * Establece el valor B1
     * @param b1
     */
    public void setB1(Double b1) {
        this.b1 = b1;
    }

    /**
     * Retorna el valor R
     * @return 
     */
    public Double getR() {
        return r;
    }

    /**
     * Establece el valor R
     * @param r 
     */
    public void setR(Double r) {
        this.r = r;
    }

    /**
     * Retorna el valor Yk
     * @return 
     */
    public Double getYk() {
        return yk;
    }

    /**
     * Establece el valor Yk
     * @param yk 
     */
    public void setYk(Double yk) {
        this.yk = yk;
    }

    /**
     * Retorna el valor numero de test
     * @return 
     */
    public int getNumberTest() {
        return numberTest;
    }

    /**
     * Establece el numero de test
     * @param numberTest 
     */
    public void setNumberTest(int numberTest) {
        this.numberTest = numberTest;
    }

    /**
     * Retorna el codigo html con los resultados
     * @return 
     */
    public String getResultadosHtml() {
        resultadosHtml = "<tr>"
                + "<td>"+numberTest+"</td>"
                + "<td>"+this.r+"</td>"
                + "<td>"+Math.pow(this.r, 2)+"</td>"
                + "<td>"+this.significance+"</td>"
                + "<td>"+this.b0+"</td>"
                + "<td>"+this.b1+"</td>"
                + "<td>"+this.yk+"</td>"
                + "<td>"+this.range+"</td>"
                + "<td>"+this.upi+"</td>"
                + "<td>"+this.lpi+"</td>"
                + "</tr>";
        return resultadosHtml;
    }
    
    /**
     * Devuelve el ancho del segmento
     *
     * @return valor Double con el ancho del segmento
     */
    public Double getSegmentWidth() {
        return segmentWidth;
    }

    /**
     * Establece el ancho del segmento
     *
     * @param segmentWidth valor Double con el ancho del segmento
     */
    public void setSegmentWidth(Double segmentWidth) {
        this.segmentWidth = segmentWidth;
    }

    /**
     * Devuelve los grados de libertad
     *
     * @return valor Double con los grados de libertad
     */
    public Double getDegreesOfFreedom() {
        return degreesOfFreedom;
    }

    /**
     * Establece los grados de libertad
     *
     * @param degreesOfFreedom valor Double con los grados de libertad
     */
    public void setDegreesOfFreedom(Double degreesOfFreedom) {
        this.degreesOfFreedom = degreesOfFreedom;
    }

    /**
     * Devuelve el valor integral
     *
     * @return Double con el valor Integral
     */
    public Double getIntegralValue() {
        return integralValue;
    }

    /**
     * Establece el valor integral
     *
     * @param integralValue Double con el valor Integral
     */
    public void setIntegralValue(Double integralValue) {
        this.integralValue = integralValue;
    }

    /**
     * Devuelve el valor intermedio
     *
     * @return Double con el valor intermedio
     */
    public List<TDistributionModel> getIntermediateValues() {
        return intermediateValues;
    }

    /**
     * Establece el valor intermedio
     *
     * @param intermediateValues Double con el valor intermedio
     */
    public void setIntermediateValues(List<TDistributionModel> intermediateValues) {
        this.intermediateValues = intermediateValues;
    }

    /**
     * Devuelve el rango maximo X
     *
     * @return valor Double con el rango maximo X
     */
    public Double getX() {
        return x;
    }

    /**
     * Establece el rango maximo X
     *
     * @param x valor Double con el rango maximo X
     */
    public void setX(Double x) {
        this.x = x;
    }

    /**
     * Retorna el numero de segmentos
     * @return numero de segmentos
     */
    public Double getNumberOfSegments() {
        return numberOfSegments;
    }

    /**
     * Establece el numero de segmentos
     * @param numberOfSegments numero de segmentos
     */
    public void setNumberOfSegments(Double numberOfSegments) {
        this.numberOfSegments = numberOfSegments;
    }

    /**
     * Retorna la significancia
     * @return significancia
     */
    public Double getSignificance() {
        return significance;
    }

    /**
     * Establece la significancia
     * @param significance significancia
     */
    public void setSignificance(Double significance) {
        this.significance = significance;
    }

    /**
     * Devuelve el rango de prediccion
     * @return 
     */
    public Double getRange() {
        return range;
    }

    /**
     * Establece el rango de prediccion
     * @param range 
     */
    public void setRange(Double range) {
        this.range = range;
    }

    /**
     * Retorna el promedio del grupo de datos X
     * @return Double con el promedio
     */
    public Double getxAvg() {
        return xAvg;
    }

    /**
     * Establece el promedio de un grupo de datos
     * @param xAvg 
     */
    public void setxAvg(Double xAvg) {
        this.xAvg = xAvg;
    }

    /**
     * Retorna la desviacion estandar
     * @return Double con la desviacion estandar
     */
    public Double getStandardDeviation() {
        return standardDeviation;
    }

    /**
     * Establece la desviacion estandar
     * @param standardDeviation 
     */
    public void setStandardDeviation(Double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    /**
     * Retorna el tamanio estimado X
     * @return Double con el tamanio estimado
     */
    public Double getxK() {
        return xK;
    }

    /**
     * Establece el tamanio estimado
     * @param xK 
     */
    public void setxK(Double xK) {
        this.xK = xK;
    }

    /**
     * Retorna el intervalo de prediccion superior
     * @return 
     */
    public Double getUpi() {
        return upi;
    }

    /**
     * Establece el intervalo de prediccion superior
     * @param upi 
     */
    public void setUpi(Double upi) {
        this.upi = upi;
    }

    /**
     * Retorna el intervalo de prediccion inferior
     * @return 
     */
    public Double getLpi() {
        return lpi;
    }

    /**
     * Establece el intervalo de prediccion inferior
     * @param lpi 
     */
    public void setLpi(Double lpi) {
        this.lpi = lpi;
    }
    
    
}
