/*
* Class Name: ProcesadorArchivos                                                         
* Name:       Daniel Arevalo                                                                      
* Date:       26/04/2016     
* Version:    1.0
*/
package co.edu.uniandes.ecos.tarea7.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;

/**
 * Esta clase tiene como objetivo leer el archivo cargado y sustraer el grupo de datos
 * @author Daniel
 */
public class ProcesadorArchivos {

    /**
     * Retorna el grupo de datos de tipo String
     * @param rutaArchivo
     * @return Lista de LinkedList de tipo String 
     * @throws Exception
     */
    public static LinkedList<LinkedList<String>> obtenerGrupoDeDatos(String rutaArchivo) throws Exception {
        LinkedList<LinkedList<String>> listaRetorno = new LinkedList<>();

        BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
        try {
            String linea = br.readLine();
            while (linea != null) {

                String[] vector = linea.split(";");

                for (int i = 0; i < vector.length; i++) {
                    
                    if (listaRetorno.size() == i) {
                        listaRetorno.add(new LinkedList<>());
                    }
                    listaRetorno.get(i).add(vector[i]);
                }
                linea = br.readLine();
            }
        } finally {
            br.close();
        }
        return listaRetorno;
    }

    /**
     * Guarda el archivo seleccionado por el usuario en el servidor
     * @param input
     * @return String con la ruta del archivo
     * @throws Exception 
     */
    public static String guardarArchivoEnServidor(InputStream input) throws Exception {
        OutputStream out = null;
        String extension = ".txt";
        String fileName = "grupoDatos";
        String pathFile = "/tmp/";
        String pathFileName;
        int count = 1;
        File file;
        do{
            pathFileName = pathFile + fileName + extension;
            file = new File(pathFileName);
            fileName = fileName + count;
            count++;
        }while(file.exists());
        
        try {
            out = new FileOutputStream(pathFileName);

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = input.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } finally {
            if (out != null) {
                out.close();
            }
            if (input != null) {
                input.close();
            }
        }

        return pathFileName;
    }
}
