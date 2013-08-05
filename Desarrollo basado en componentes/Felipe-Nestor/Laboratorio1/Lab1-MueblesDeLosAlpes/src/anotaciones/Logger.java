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

    public void logMetodo(String clase, String metodo) {
        PrintWriter writter = null;
        try {
            writter = escribirLog(writter, clase, metodo);
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            cerrarWritter(writter);
        }
    }

    private PrintWriter escribirLog(PrintWriter writter, String clase, String metodo) throws FileNotFoundException, IOException {
        writter = new PrintWriter(new FileWriter(LOG_FILE, true));
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        writter.print(format.format(new Date()));
        writter.print(" - ");
        writter.print(clase);
        writter.print(".");
        writter.print(metodo);
        writter.println();
        writter.flush();
        return writter;
    }

    private void cerrarWritter(PrintWriter writter) {
        if (writter != null) {
            writter.close();
        }
    }
}
