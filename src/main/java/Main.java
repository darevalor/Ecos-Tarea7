/*
* Class Name: Main                                                         
* Name:       Daniel Arevalo                                                                      
* Date:       14/02/2016     
* Version:    1.0
*/
import co.edu.uniandes.ecos.tarea3.controller.Tarea3Controller;
import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import com.heroku.sdk.jdbc.DatabaseUrl;
import static spark.Spark.get;

/**
 * Clase principal del programa
 */
public class Main {
    /**
     * Metodo principal que inicia la aplicacion
     * @param args 
     */
    public static void main(String[] args) {
        Tarea3Controller controller = new Tarea3Controller();
        
        port(Integer.valueOf(System.getenv("PORT")));
        staticFileLocation("/public");

        get("/hello", (req, res) -> "Hello World");

        post("/upload", (req, res) -> {
            return controller.cargarArchivo(req, res);
        });

        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Hello World!");

            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());

        get("/db", (req, res) -> {
            Connection connection = null;
            Map<String, Object> attributes = new HashMap<>();
            try {
                connection = DatabaseUrl.extract().getConnection();

                Statement stmt = connection.createStatement();
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
                stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
                ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

                ArrayList<String> output = new ArrayList<String>();
                while (rs.next()) {
                    output.add("Read from DB: " + rs.getTimestamp("tick"));
                }

                attributes.put("results", output);
                return new ModelAndView(attributes, "db.ftl");
            } catch (Exception e) {
                attributes.put("message", "There was an error: " + e);
                return new ModelAndView(attributes, "error.ftl");
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                    }
                }
            }
        }, new FreeMarkerEngine());

    }

}
