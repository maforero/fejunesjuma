/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package anotaciones;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

/**
 * Clase encargada de crear el log de metodos
 *
 * @author NCRUZ
 */
public class Logger {

    private static final String LOG_FILE = "logMetodos.log";
    private static Logger instance;

    private Logger() {
    }

    /**
     * Metodo que retorna una instancia unica para esta clase
     * Singleton
     * 
     * @return Logger singleton instance
     */
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    /**
     * Metodo encargado de ingresar al archivo de log logMetodos.log los metodos
     * ejecutados, con el fomato dd-MM-yyyy hh:mm:ss - <clase>.<metodo>(<parametros>)
     * 
     * @param clase del metodo ejecutado
     * @param metodo que se ejecuta
     * @param types de paramametros del metodo ejecutado
     */
    public void logMetodo(String clase, String metodo, Class<?> types[]) {
        PrintWriter writter = null;
        try {
            writter = escribirLog(writter, clase, metodo, types);
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarWritter(writter);
        }
    }

    /**
     * Escribe un registro especifico en el log
     * 
     * @param writer outputstream donde se escribe el log
     * @param clase donde se encuentra el metodo ejecutado
     * @param metodo que se ejecuta
     * @param types de parametros del metodo ejecutado
     * @return printWritter para permitir cerra el stream
     * @throws FileNotFoundException si no encuentra el archivo
     * @throws IOException si ocurre algun error escribiendo el log
     */
    private PrintWriter escribirLog(PrintWriter writer, String clase, String metodo, Class<?> types[]) throws FileNotFoundException, IOException {
        writer = new PrintWriter(new FileWriter(LOG_FILE, true));
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        writer.print(format.format(new Date()));
        writer.print(" - ");
        writer.print(clase);
        writer.print(".");
        writer.print(metodo);
        writer.print("(");

        if (types != null) {
            for (int i = 0; i < types.length; i++) {
                writer.print(types[i]);
                if (i < types.length - 1) {
                    writer.print(",");
                }
            }
        }
        writer.print(")");
        writer.println();
        writer.flush();
        return writer;
    }

    /**
     * Metodo que cierra el writter
     * @param writter 
     */
    private void cerrarWritter(PrintWriter writter) {
        if (writter != null) {
            writter.close();
        }
    }
}
