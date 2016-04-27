/*
* Class Name: Tarea7Controller                                                         
* Name:       Daniel Arevalo                                                                      
* Date:       26/04/2016     
* Version:    1.0
 */
package co.edu.uniandes.ecos.tarea7.controller;

import co.edu.uniandes.ecos.tarea7.model.TDistributionModel;
import co.edu.uniandes.ecos.tarea7.model.Tarea7Model;
import co.edu.uniandes.ecos.tarea7.util.Calcular;
import co.edu.uniandes.ecos.tarea7.util.Constantes;
import co.edu.uniandes.ecos.tarea7.util.ProcesadorArchivos;
import java.io.IOException;
import java.util.ArrayList;
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
 *
 * @author Daniel
 */
public class Tarea7Controller {

    private final static Logger LOGGER = Logger.getLogger(Tarea7Controller.class.getCanonicalName());
    private final Calcular calcular = new Calcular();

    /**
     * Se encarga de instanciar a la clase ProcesadorArchivos para cargar el
     * archivo seleccionado por el usuario
     *
     * @param request
     * @param response
     * @return String con el codigo html que contiene los resultados de los
     * calculos
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
        } catch (IOException | ServletException ex ) {
            LOGGER.log(Level.SEVERE, null, ex);
            return ex.getMessage();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
    }

    /**
     * Se encarga de ejecutar los casos de calculo entre los grupos de datos
     *
     * @param grupoDatos
     * @return String con el codigo html con los resultados de los casos
     */
    private String ejecutarCasos(LinkedList<LinkedList<String>> grupoDatos) {
        LinkedList<Double> listaX;
        LinkedList<Double> listaY;
        StringBuilder tableHtml = new StringBuilder();

        //Caso 1: Estimated Proxy Size Vs Actual Added and Modified Size
        listaX = obtenerListaValores(grupoDatos, Constantes.ESTIMATED_PROXY_SIZE);
        listaY = obtenerListaValores(grupoDatos, Constantes.ACTUAL_ADDED_MODIFIED_SIZE);
        Tarea7Model caso1 = calcularRegresionYCorrelacion(listaX, listaY, Constantes.XK_1);
        caso1.setNumberTest(1);

        //Caso 2: Estimated Proxy Size Vs Actual Development Hours
        listaX = obtenerListaValores(grupoDatos, Constantes.ESTIMATED_PROXY_SIZE);
        listaY = obtenerListaValores(grupoDatos, Constantes.ACTUAL_DEVELOPMENT_HOURS);
        Tarea7Model caso2 = calcularRegresionYCorrelacion(listaX, listaY, Constantes.XK_1);
        caso2.setNumberTest(2);

        //Caso 3: Actual Added and Modified Size Vs Plan Added and Modified size
        listaX = obtenerListaValores(grupoDatos, Constantes.PLAN_ADDED_MODIFIED_SIZE);
        listaY = obtenerListaValores(grupoDatos, Constantes.ACTUAL_ADDED_MODIFIED_SIZE);
        Tarea7Model caso3 = calcularRegresionYCorrelacion(listaX, listaY, Constantes.XK_2);
        caso3.setNumberTest(3);

        //Caso 4: Actual Added and Modified Size Vs Actual Development Hours
        listaX = obtenerListaValores(grupoDatos, Constantes.PLAN_ADDED_MODIFIED_SIZE);
        listaY = obtenerListaValores(grupoDatos, Constantes.ACTUAL_DEVELOPMENT_HOURS);
        Tarea7Model caso4 = calcularRegresionYCorrelacion(listaX, listaY, Constantes.XK_2);
        caso4.setNumberTest(4);

        tableHtml.append("<table border='1'>")
                .append("<tr>")
                .append("<td>Test</td>")
                .append("<td>r(x,y)</td>")
                .append("<td>r * r</td>")
                .append("<td>Significance</td>")
                .append("<td>B0</td>")
                .append("<td>B1</td>")
                .append("<td>Yk</td>")
                .append("<td>Range</td>")
                .append("<td>UPI (70%) -</td>")
                .append("<td>LPI (70%)</td>")
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
     *
     * @param listaX primer grupo de datos
     * @param listaY segundo grupo de datos
     * @return Tarea7Model con los resultados de los calculos
     */
    private Tarea7Model calcularRegresionYCorrelacion(LinkedList<Double> listaX, LinkedList<Double> listaY, Double xK) {
        Tarea7Model resultados = new Tarea7Model();
        resultados.setB1(calcular.calcularB1(listaX, listaY));
        resultados.setB0(calcular.calcularB0(listaX, listaY, resultados.getB1()));
        resultados.setR(calcular.calcularR(listaX, listaY));
        resultados.setYk(calcular.calcularYk(resultados.getB0(), resultados.getB1(), xK));
        resultados.setxK(xK);
        resultados.setX(calcular.getX(resultados.getR(), new Double(listaX.size())));
        resultados.setxAvg(calcular.calcularPromedio(listaX));
        resultados.setDegreesOfFreedom(new Double(listaX.size() - 2));
        resultados.setStandardDeviation(calcular.desviacionEstandar(listaX, listaY, resultados.getB0(), resultados.getB1()));
        resultados.setRange(calcular.rango(resultados.getStandardDeviation(), resultados.getxAvg(),
                resultados.getxK(), buscarX(Constantes.P_70, new Double(listaX.size() - 2)), listaX));
        resultados.setUpi(resultados.getYk() + resultados.getRange());
        resultados.setLpi(resultados.getYk() - resultados.getRange());
        
        return calcularIntegral(resultados);
    }

    /**
     * Retorna una lista de valores segun el titulo de la columna
     *
     * @param grupoDatos conjunto de datos cargados del archivo seleccionado por
     * el usuario.
     * @param titulo iniciales del titulo de la columna del grupo de datos que
     * se esta buscando
     * @return Lista con valores de tipo double
     * @throws NumberFormatException dado el caso en que algun valor no sea
     * numerico
     */
    private LinkedList<Double> obtenerListaValores(LinkedList<LinkedList<String>> grupoDatos, String titulo) throws NumberFormatException {
        LinkedList<Double> listaRetorno = new LinkedList<>();
        int cont = 0;
        for (LinkedList<String> lista : grupoDatos) {
            if (lista.get(0).contains(titulo)) {
                for (String valor : lista) {
                    if (cont > 0) {
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

    /**
     * Se encarga procesar los calculos para la integracion numerica
     *
     * @return String con el codigo html con los resultados
     */
    private Tarea7Model calcularIntegral(Tarea7Model tarea7Model) {
        Double sumatoriaValoresIntermedios;
        Double error = new Double("1");
        tarea7Model.setNumberOfSegments(Constantes.NUMBER_OF_SEGMENTS);

        do {
            sumatoriaValoresIntermedios = new Double(0);
            tarea7Model = setUpIntermediateValues(tarea7Model);
            for (TDistributionModel tdm : tarea7Model.getIntermediateValues()) {

                tdm.setFunctionX(calcular.funcionX(tdm.getXi(), tarea7Model.getDegreesOfFreedom()));
                sumatoriaValoresIntermedios += calcular.valorIntermedio(tarea7Model.getSegmentWidth(), tdm.getMultiplier(), tdm.getFunctionX());
            }

            if (tarea7Model.getIntegralValue() != null && !tarea7Model.getIntegralValue().equals(new Double(0))) {
                error = calcular.marginOfError(sumatoriaValoresIntermedios, tarea7Model.getIntegralValue());
            }
            tarea7Model.setNumberOfSegments(tarea7Model.getNumberOfSegments() * 2);

            tarea7Model.setIntegralValue(sumatoriaValoresIntermedios);

        } while (error >= (Constantes.ERROR));

        tarea7Model.setSignificance(calcular.significancia(tarea7Model.getIntegralValue()));

        return tarea7Model;
    }

    private Tarea7Model setUpIntermediateValues(Tarea7Model tarea7Model) {
        tarea7Model.setIntermediateValues(new ArrayList<>());
        tarea7Model.setSegmentWidth(tarea7Model.getX() / tarea7Model.getNumberOfSegments());

        for (int i = 0; i <= tarea7Model.getNumberOfSegments(); i++) {
            TDistributionModel distributionModel = new TDistributionModel();

            if (i == 0 || i == tarea7Model.getNumberOfSegments()) {
                distributionModel.setMultiplier(new Double(1));
            } else if ((i % 2) == 1) {
                distributionModel.setMultiplier(new Double(4));
            } else {
                distributionModel.setMultiplier(new Double(2));
            }
            distributionModel.setXi(tarea7Model.getSegmentWidth() * i);

            tarea7Model.getIntermediateValues().add(distributionModel);
        }
        return tarea7Model;
    }

    /**
     * Se encarga de buscar el valor X dado un valor P y un valor dof
     *
     * @return String con el codigo html con los resultados
     */
    private Double buscarX(Double p, Double dof) {
        Double sumatoriaValoresIntermedios;
        Double error;
        Double d = new Double("0.5");
        String estadoAnterior = "";
        String estadoActual = "";

        Tarea7Model tarea7Model = new Tarea7Model();
        tarea7Model.setIntegralValue(p);
        tarea7Model.setDegreesOfFreedom(dof);
        tarea7Model.setX(new Double("1"));
        tarea7Model.setNumberOfSegments(Constantes.NUMBER_OF_SEGMENTS);

        do {
            sumatoriaValoresIntermedios = new Double(0);
            tarea7Model = setUpIntermediateValues(tarea7Model);

            for (TDistributionModel tdm : tarea7Model.getIntermediateValues()) {

                tdm.setFunctionX(calcular.funcionX(tdm.getXi(), tarea7Model.getDegreesOfFreedom()));
                sumatoriaValoresIntermedios += calcular.valorIntermedio(tarea7Model.getSegmentWidth(), tdm.getMultiplier(), tdm.getFunctionX());
            }

            error = calcular.marginOfError(sumatoriaValoresIntermedios, tarea7Model.getIntegralValue());
            if (sumatoriaValoresIntermedios < tarea7Model.getIntegralValue() && !error.equals(Constantes.ERROR)) {
                tarea7Model.setX(tarea7Model.getX() + d);
                estadoAnterior = estadoActual.equals("") ? "suma" : estadoActual;
                estadoActual = "suma";
            } else if (sumatoriaValoresIntermedios > tarea7Model.getIntegralValue() && !error.equals(Constantes.ERROR)) {
                tarea7Model.setX(tarea7Model.getX() - d);
                estadoAnterior = estadoActual.equals("") ? "resta" : estadoActual;
                estadoActual = "resta";
            }

            if (!estadoAnterior.equals(estadoActual)) {
                d = d / 2;
            }

        } while (error >= (Constantes.ERROR));
        return tarea7Model.getX();
    }
}
