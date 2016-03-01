/*
* Class Name: Tarea3Controller                                                         
* Name:       Daniel Arevalo                                                                      
* Date:       14/02/2016     
* Version:    1.0
*/
package co.edu.uniandes.ecos.tarea3.controller;

import co.edu.uniandes.ecos.tarea3.model.Tarea3Model;
import co.edu.uniandes.ecos.tarea3.util.Calcular;
import co.edu.uniandes.ecos.tarea3.util.Constantes;
import co.edu.uniandes.ecos.tarea3.util.ProcesadorArchivos;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import spark.Request;
import spark.Response;

/**
 * Clase encargada de atender las solicitudes del usuario
 * @author Daniel
 */
public class Tarea3Controller {

    private final static Logger LOGGER = Logger.getLogger(Tarea3Controller.class.getCanonicalName());

    /**
     * Se encarga de instanciar a la clase ProcesadorArchivos para cargar
     * el archivo seleccionado por el usuario
     * @param request
     * @param response
     * @return String con el codigo html que contiene los resultados 
     * de los calculos
     */
    public String cargarArchivo(Request request, Response response) {
        try {
            LinkedList<LinkedList<String>> grupoDatos;
                       
            MultipartConfigElement multipartConfigElement = new MultipartConfigElement("/tmp");
            request.raw().setAttribute("org.eclipse.multipartConfig", multipartConfigElement);
            
            Part part = request.raw().getPart("file");
            String ruta = ProcesadorArchivos.guardarArchivoEnServidor(part.getInputStream());
            grupoDatos = ProcesadorArchivos.obtenerGrupoDeDatos(ruta);
            
            return ejecutarCasos(grupoDatos);
        } catch (IOException | ServletException ex) {
            ex.printStackTrace();
            LOGGER.log(Level.SEVERE, null, ex);
            return ex.getMessage();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
    }
    
    /**
     * Se encarga de ejecutar los casos de calculo entre los grupos de datos
     * @param grupoDatos
     * @return String con el codigo html con los resultados de los casos
     */
    private String ejecutarCasos(LinkedList<LinkedList<String>> grupoDatos){
        LinkedList<Double> listaX;
        LinkedList<Double> listaY;
        StringBuilder tableHtml = new StringBuilder();
        
        //Caso 1: Estimated Proxy Size Vs Actual Added and Modified Size
        listaX = obtenerListaValores(grupoDatos, Constantes.ESTIMATED_PROXY_SIZE);
        listaY = obtenerListaValores(grupoDatos, Constantes.ACTUAL_ADDED_MODIFIED_SIZE);
        Tarea3Model caso1 = calcularRegresionYCorrelacion(listaX, listaY);
        caso1.setNumberTest(1);
        
        //Caso 2: Estimated Proxy Size Vs Actual Development Hours
        listaX = obtenerListaValores(grupoDatos, Constantes.ESTIMATED_PROXY_SIZE);
        listaY = obtenerListaValores(grupoDatos, Constantes.ACTUAL_DEVELOPMENT_HOURS);
        Tarea3Model caso2 = calcularRegresionYCorrelacion(listaX, listaY);
        caso2.setNumberTest(2);
        
        //Caso 3: Actual Added and Modified Size Vs Plan Added and Modified size
        listaX = obtenerListaValores(grupoDatos, Constantes.PLAN_ADDED_MODIFIED_SIZE);
        listaY = obtenerListaValores(grupoDatos, Constantes.ACTUAL_ADDED_MODIFIED_SIZE);
        Tarea3Model caso3 = calcularRegresionYCorrelacion(listaX, listaY);
        caso3.setNumberTest(3);
        
        //Caso 4: Actual Added and Modified Size Vs Actual Development Hours
        listaX = obtenerListaValores(grupoDatos, Constantes.PLAN_ADDED_MODIFIED_SIZE);
        listaY = obtenerListaValores(grupoDatos, Constantes.ACTUAL_DEVELOPMENT_HOURS);
        Tarea3Model caso4 = calcularRegresionYCorrelacion(listaX, listaY);
        caso4.setNumberTest(4);
        
        tableHtml.append("<table border='1'>")
                .append("<tr>")
                .append("<td>Test</td>")
                .append("<td>B0</td>")
                .append("<td>B1</td>")
                .append("<td>r(x,y)</td>")
                .append("<td>r * r</td>")
                .append("<td>Yk</td>")
                .append("</tr>")
                .append(caso1.getResultadosHtml())
                .append(caso2.getResultadosHtml())
                .append(caso3.getResultadosHtml())
                .append(caso4.getResultadosHtml())
                .append("</table>");
        return tableHtml.toString();
    }
    
    /**
     * Encargado de calcular la regresion lineal y correlacion de los grupos de
     * datos pasados como parametros
     * @param listaX primer grupo de datos
     * @param listaY segundo grupo de datos
     * @return Tarea3Model con los resultados de los calculos
     */
    private Tarea3Model calcularRegresionYCorrelacion(LinkedList<Double> listaX, LinkedList<Double> listaY){
        Tarea3Model resultados = new Tarea3Model();
        Calcular calcular = new Calcular();
        resultados.setB1(calcular.calcularB1(listaX, listaY));
        resultados.setB0(calcular.calcularB0(listaX, listaY, resultados.getB1()));
        resultados.setR(calcular.calcularR(listaX, listaY));
        resultados.setYk(calcular.calcularYk(resultados.getB0(), resultados.getB1()));
        return resultados;
    }
    
    /**
     * Retorna una lista de valores segun el titulo de la columna
     * @param grupoDatos conjunto de datos cargados del archivo seleccionado
     * por el usuario.
     * @param titulo iniciales del titulo de la columna del grupo de datos que
     * se esta buscando
     * @return Lista con valores de tipo double 
     * @throws NumberFormatException dado el caso en que algun valor no sea numerico
     */
    private LinkedList<Double> obtenerListaValores(LinkedList<LinkedList<String>> grupoDatos, String titulo)throws NumberFormatException{
        LinkedList<Double> listaRetorno = new LinkedList<>();
        int cont = 0;
        for(LinkedList<String> lista : grupoDatos){
            if(lista.get(0).contains(titulo)){
                for(String valor : lista){
                    if(cont > 0){
                        Double valorNumero = Double.parseDouble(valor.trim());
                        listaRetorno.add(valorNumero);
                    }
                    cont++;
                }
                break;
            }
        }
        return listaRetorno;
    }
}
