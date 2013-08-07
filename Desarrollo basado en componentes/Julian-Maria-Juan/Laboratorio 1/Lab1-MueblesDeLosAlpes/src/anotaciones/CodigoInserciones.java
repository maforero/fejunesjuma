/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CodigoInserciones.java,v 1.1 2010/05/27 16:49:35 ga.sotelo69 Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 2.1
 *
 * Ejercicio: Taller 1 - anotaciones
 * Autor: German Augusto Sotelo - 31-may-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package anotaciones;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mundo.InitException;


/**
 * Clase encargada de contener el código de las inserciones
 * El nombre de cada método debe corresponder con el de la clase que representa
 * @author Germán Sotelo
 */
public class CodigoInserciones
{

    /**
     * Método invocado en la insercion de código hecha a los métodos anotados con @Init
     * @param instance Instancia desde que se invoca este método
     * @param claseRepresentada Clase a la que representa el proxý que hizo la invocación del método
     * @param annotacion Informacion de la anotación hecha sobre el método que invoca a este
     * @param method Método que fua anotado con @Init
     */
    public static void Init(Object instance, Class claseRepresentada, Annotation annotacion, Method method){
        Init c = (Init) annotacion;
        for (Field f : claseRepresentada.getDeclaredFields()) {
            try {
                //Solo se inicializan aquellos atributos que no tengan la anotación @NoInit presenta
                if (!f.isAnnotationPresent(NoInit.class)) {
                    f.setAccessible(true);
                    if (f.getType().equals(Integer.TYPE))
                    {
                        f.set(instance, c.Integer());
                    } else if (f.getType().equals(Double.TYPE))
                    {
                        f.set(instance, c.Double());
                    } else if (f.getType().equals(Character.TYPE))
                    {
                        f.set(instance, c.Char());
                    } else if (f.getType().equals(String.class))
                    {
                        System.out.println(instance);
                        System.out.println(c);
                        f.set(instance, c.String());
                    } else if (f.getType().equals(Boolean.TYPE))
                    {
                        f.set(instance, c.Boolean());
                    } else if (f.getType().equals(Float.TYPE))
                    {
                        f.set(instance, c.Float());
                    } else if (f.getType().equals(Long.TYPE))
                    {
                        f.set(instance, c.Long());
                    }
                }
            } 
            catch (Exception e)
            {
                e.printStackTrace();
                throw new InitException();
            }
        }
    }
    
    /**
     * Este método se invocado por la inserción de código realizada en los 
     * métodos anotados con @Log
     * @param instance Instancia desde que se invoca este método
     * @param claseRepresentada Clase a la que representa el proxý que hizo la invocación del método
     * @param annotacion Informacion de la anotación hecha sobre el método que invoca a este
     * @param method Método que fua anotado con @Log
     */
    public static void Log(Object instance, Class claseRepresentada, Annotation annotacion, Method method) {        
        Calendar fechaActual = Calendar.getInstance();
        List<String> logActual = new ArrayList<String>();
        File logFile = new File("./LogFile.txt");
        if (logFile.exists()) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader("./LogFile.txt"));
                for(String line;(line=br.readLine())!=null;){
                    logActual.add(line);
                }
                br.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CodigoInserciones.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex){
                Logger.getLogger(CodigoInserciones.class.getName()).log(Level.SEVERE, null, ex);
            } 
            finally {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(CodigoInserciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }                
        logActual.add(fechaActual.getTime().toString() + 
                " >> Se ejecuto el método " + method.getName() + 
                " de la clase " + claseRepresentada.getName());
        
        PrintWriter pw = null;
        try {
            pw = new PrintWriter("./LogFile.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CodigoInserciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (String log : logActual) {
            if(pw!=null)
                pw.println(log);            
        }
        pw.close();        
    }
    
    /**
     * Este método se invocado por la inserción de código realizada en los 
     * métodos anotados con @Validator
     * @param instance
     * @param claseRepresentada
     * @param annotacion
     * @param method 
     */
    public static void Validator(Object instance, Class claseRepresentada, Annotation annotacion, Method method){
        try {
           Field currentField = claseRepresentada.getDeclaredField("precio");
           currentField.setAccessible(true);
           Class fieldType = currentField.getType();
           
              Double value = currentField.getDouble(instance);
             
              if(value.equals(0.0)){
                  JOptionPane.showMessageDialog(null, "No se puede reiniciar el campo ");
                  throw new RuntimeException("No se pudo reiniciar el campo");
              }
           
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(CodigoInserciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CodigoInserciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(CodigoInserciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(CodigoInserciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
