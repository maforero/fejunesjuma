/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package anotaciones;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

/**
 *
 * @author Felipe
 */
public class Logger {

    private static final String LOG_FILE = "logMetodos.log";
    private static Logger instance;

    private Logger() {
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

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

    private void cerrarWritter(PrintWriter writter) {
        if (writter != null) {
            writter.close();
        }
    }
}
