/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.tarea7.model;

/**
 *
 * @author Daniel
 */
public class TDistributionModel {

    private Double multiplier;
    private Double functionX;
    private Double xi;

    /**
     * Devuelve el multiplicador
     *
     * @return valor Double con el multiplicador
     */
    public Double getMultiplier() {
        return multiplier;
    }

    /**
     * Establece el multiplicador
     *
     * @param multiplier valor Double con el multiplicador
     */
    public void setMultiplier(Double multiplier) {
        this.multiplier = multiplier;
    }

    /**
     * Devuelve el valor de la funcion de X
     *
     * @return valor Double de la funcion de X
     */
    public Double getFunctionX() {
        return functionX;
    }

    /**
     * Establece el valor de la funcion de X
     *
     * @param functionX valor Double de la funcion de X
     */
    public void setFunctionX(Double functionX) {
        this.functionX = functionX;
    }

    /**
     * Devuelve el valor X de un segmento
     *
     * @return Double con el valor de X
     */
    public Double getXi() {
        return xi;
    }

    /**
     * Establece el valor X de un segmento
     *
     * @param xi Double con el valor de X
     */
    public void setXi(Double xi) {
        this.xi = xi;
    }

}
