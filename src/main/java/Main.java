/*
* Class Name: Main                                                         
* Name:       Daniel Arevalo                                                                      
* Date:       26/04/2016     
* Version:    1.0
 */
import co.edu.uniandes.ecos.tarea7.controller.Tarea7Controller;
import java.util.HashMap;
import java.util.Map;
import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;

/**
 * Clase principal del programa
 */
public class Main {

    /**
     * Metodo principal que inicia la aplicacion
     *
     * @param args
     */
    public static void main(String[] args) {
        Tarea7Controller controller = new Tarea7Controller();

        port(Integer.valueOf(System.getenv("PORT")));
        staticFileLocation("/public");

        post("/upload", (req, res) -> {
            return controller.cargarArchivo(req, res);
        });

        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Hello World!");

            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());

    }
}
