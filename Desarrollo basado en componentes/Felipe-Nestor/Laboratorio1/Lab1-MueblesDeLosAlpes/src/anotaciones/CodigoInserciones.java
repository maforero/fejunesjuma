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


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Level;


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
    public static void Init(Object instance, Class claseRepresentada, Annotation annotacion, Method method) throws Exception {
        Init c = (Init) annotacion;
        for (Field f : claseRepresentada.getDeclaredFields()) {
            try {
                
                if(!f.isAnnotationPresent(NoInit.class))
                {
                
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
                throw new Exception(e.getMessage());
            }
        }
    }
    
    public static void Log(Object instance, Class claseRepresentada, Annotation annotacion, Method method) {
        Logger.getInstance().logMetodo(instance.getClass().getSuperclass().getCanonicalName(), method.getName(), method.getParameterTypes());
    }
    
    public static void Invoke(Object instance, Class claseRepresentada, Annotation annotacion, Method method) {
        Invoke c = (Invoke) annotacion;
        Class<? extends Invokable> invoker = c.preInvoke();
        try {
            Invokable invokeable = invoker.newInstance();
            invokeable.invoke();
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CodigoInserciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CodigoInserciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
