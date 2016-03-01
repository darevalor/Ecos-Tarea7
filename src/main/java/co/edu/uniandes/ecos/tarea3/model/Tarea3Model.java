/*
* Class Name: Tarea3Model                                                         
* Name:       Daniel Arevalo                                                                      
* Date:       14/02/2016     
* Version:    1.0
*/
package co.edu.uniandes.ecos.tarea3.model;

/**
 * Clase encargada de guardar los resultados de un caso de estudio
 * @author Daniel
 */
public class Tarea3Model {

    private Double b0;
    private Double b1;
    private Double r;
    private Double yk;
    private int numberTest;
    private String resultadosHtml;
    
    public Tarea3Model(){
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
                + "<td>Test "+numberTest+"</td>"
                + "<td>"+this.b0+"</td>"
                + "<td>"+this.b1+"</td>"
                + "<td>"+this.r+"</td>"
                + "<td>"+Math.pow(this.r, 2)+"</td>"
                + "<td>"+this.yk+"</td>"
                + "</tr>";
        return resultadosHtml;
    }

}
