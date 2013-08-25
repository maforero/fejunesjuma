/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Driver.java,v 1.1 2010/05/27 16:49:35 ga.sotelo69 Exp $
 * Universidad de los Andes (BogotÃ¡ - Colombia)
 * Departamento de IngenierÃ­a de Sistemas y ComputaciÃ³n
 * Licenciado bajo el esquema Academic Free License version 2.1
 *
 * Ejercicio: Taller 1 - anotaciones
 * Autor: German Augusto Sotelo - 31-may-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package anotaciones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Clase encargada de la instanciaciÃ³n y el manejo de las anotaciones
 * @author GermÃ¡n Sotelo
 */
public class Driver
{

    /**
     * Anotaciones que tienen inserciones de codigo. Toda anotaciÃ³n que tenga un mÃ©todo que la
     * represente en la clase CodigoInserciones debe estar en este arreglo para que sea efectiva
     */
    public static Class[] anotacionesInsercion = {Validator.class,Init.class, Log.class};

    /**
     * Estructura encargada de contener las clases y sus proxys
     */
    private static Map<Class, Class> proxys = new TreeMap<Class, Class>(new Comparator<Class>()
    {
        public int compare(Class o1, Class o2)
        {
            String h1 = "" + o1, h2 = "" + o2;
            return h1.compareTo(h2);
        }
    });

    /**
     * Permite saber si el mÃ©todo que se le pasa como parametro tiene inyecciones
     * de cÃ³digo o no
     * @param method
     * @return
     */
    private static boolean tieneInyeccion(Method method)
    {
        for(Class anotacion:anotacionesInsercion){
            if(method.isAnnotationPresent(anotacion))return true;
        }
        return false;
    }

    /**
     * Dado un tipo retorna un String que lo representa
     * @param tipo
     * @return
     */
    private static String getRealType(Class tipo){
        if(tipo.equals(Boolean.TYPE))return "Boolean.TYPE";
        if(tipo.equals(Double.TYPE))return "Double.TYPE";
        if(tipo.equals(Integer.TYPE))return "Integer.TYPE";
        if(tipo.equals(Short.TYPE))return "Short.TYPE";
        if(tipo.equals(Float.TYPE))return "Float.TYPE";
        if(tipo.equals(Long.TYPE))return "Long.TYPE";
        if(tipo.equals(Character.TYPE))return "Character.TYPE";
        return tipo.getSimpleName()+".class";
    }

    /**
     * Crea un proxy de la clase que se le pasa como parÃ¡metro
     * @param objetivo
     * @return
     */
    private static Class crearProxy(Class objetivo) {
        Class proxy = proxys.get(objetivo);
        if(proxy!=null)return proxy;
        File classes = new File("./build/classes/"), classpa = new File("./src/");

        // Este archivo es el archivo .java del proxy
        File source = new File("./src/mundo/" + objetivo.getSimpleName() + "Proxy.java");
        File librerias = new File("./lib/");
        PrintWriter pw=null;
        try {
            // La insercion sÃ³lo se realiza si el objetivo es una clase            
            //Eliminar "!objetivo.isInterface()" para permitir las anotaciones en interfaces
            if (!objetivo.isEnum()) {
                boolean tieneInyecciones = false;
                
                 Class[] interfaces= objetivo.getInterfaces();
                 boolean tieneInterfaces = interfaces!=null && interfaces.length>0;
                
                pw = new PrintWriter(source);
                String paquete = objetivo.getPackage().getName();

                // Se agregan todas las importaciones necesarias
                pw.println("package "+paquete+";");
                BufferedReader br = new BufferedReader(new FileReader("./src/"+paquete.replace(".","/")+"/"+objetivo.getSimpleName()+".java"));
                for(String h;(h=br.readLine())!=null;){
                    h=h.trim();
                    if(h.startsWith("import"))pw.println(h);
                }
                pw.println("import anotaciones.*;");
                pw.println("import java.lang.reflect.*;");
                pw.println("import java.lang.annotation.Annotation;");

                br.close();

                // El proxy extiende del objetivo
                pw.println("public class " + objetivo.getSimpleName() + "Proxy extends " + objetivo.getSimpleName() + "{");

                // Se crea el constructor por defecto del proxy
                pw.println("public " + objetivo.getSimpleName() + "Proxy(){");
                pw.println("super();");
                pw.println("}");
                
                

                // Se revisan todos los mÃ©todos
                for (Method method : objetivo.getDeclaredMethods()) {
                    int modifi = method.getModifiers();
                    Class interfaz = null;
                    //Si la clase objetivo implementa una interfaz se recorren las interfaces
                    if(tieneInterfaces){
                        end:
                        for (Class in : interfaces) {
                            //Se recorren los métodos para ver si el método de la clase objetivo coincide con los métodos de la interfaz
                            for(Method methodI: in.getDeclaredMethods()){
                                if(method.getName().equals(methodI.getName())){
                                    method = methodI;
                                    interfaz = in;
                                    break end;
                                }
                            }
                        }
                    }
                    // Se verifica si tiene una anotaciÃ³n con inyecciÃ³n, los mÃ©todos estÃ¡ticos no se han contemplado
                    if(tieneInyeccion(method)&&!Modifier.isStatic(modifi)) {
                        // Se imprime la declaraciÃ³n del metodo
                        pw.print(Modifier.toString(modifi));
                        Class tipoRetorno = method.getReturnType();
                        pw.print(" "+tipoRetorno.getSimpleName());
                        pw.print(" "+method.getName()+"(");
                        Class[] para = method.getParameterTypes();
                        for(int e=0;e<para.length;e++)pw.print(para[e].getSimpleName()+" arg"+e+(e==para.length-1?"":","));
                        pw.println(")");
                        Class[] excep = method.getExceptionTypes();
                        if(excep.length>0)pw.print("throws ");
                        for(int e=0;e<excep.length;e++)pw.print(excep[e].getSimpleName()+(e==excep.length-1?"":","));
                        pw.println("{");

                        // Se verifica e inyecta el cÃ³digo de cada anotaciÃ³n presente
                        for(Class annotation:anotacionesInsercion){
                            if(method.isAnnotationPresent(annotation)){
                                pw.println("{try{");
                                if (tieneInterfaces) {
                                    //Lee la anotación desde la interfaz
                                    pw.print("Method meth = "+interfaz.getSimpleName()+".class.getMethod(\""+method.getName()+"\",new Class[]{");
                                } else {
                                    pw.print("Method meth = "+objetivo.getSimpleName()+".class.getMethod(\""+method.getName()+"\",new Class[]{");
                                }
                                for(int e=0;e<para.length;e++)pw.print(getRealType(para[e])+(e==para.length-1?"":","));
                                pw.println("});");
                                pw.println("Annotation an = meth.getAnnotation("+annotation.getName()+".class);");
                                pw.println("CodigoInserciones."+annotation.getSimpleName()+"(this,"+objetivo.getSimpleName()+".class,an,meth);");
                                pw.println("} catch (NoSuchMethodException ex) {");
                                pw.println("ex.printStackTrace();");
                                pw.println("} catch (SecurityException ex) {");
                                pw.println("ex.printStackTrace();");
                                pw.println("}}");

                            }
                        }

                        // Se llama al correspondiente mÃ©todo de la super clase
                        if(!tipoRetorno.equals(Void.TYPE))pw.print("return ");
                        pw.print("super."+method.getName()+"(");
                        for(int e=0;e<para.length;e++)pw.print("arg"+e+(e==para.length-1?"":","));
                        pw.println(");");

                        pw.println("}");
                        tieneInyecciones = true;
                    }
                }

                pw.println("}");
                pw.close();
                
                // Si el objetivo tiene inyecciones, el proxy se compila y se carga dinÃ¡micamente
                if (tieneInyecciones) {
                    String libs= "";
                    for(File jar :librerias.listFiles()){
                        libs+="\""+librerias.getCanonicalPath()+"\\"+jar.getName()+"\";";
                    }
                    // Se compila, para esto es necesario el JDK
                    Process b = Runtime.getRuntime().exec("javac -d \"" + classes.getCanonicalPath() + "\" -classpath "+libs+"\"" + classpa.getCanonicalPath() + "\" \"" + source.getCanonicalPath() + "\"");

                    BufferedReader error = new BufferedReader(new InputStreamReader(b.getErrorStream()));
                    for(String h;(h=error.readLine())!=null;)System.out.println(h);
                    error = new BufferedReader(new InputStreamReader(b.getInputStream()));
                    for(String h;(h=error.readLine())!=null;)System.out.println(h);
                    
                    source.delete();
                    
                    // Se carga el proxy
                    Class ret =  Class.forName(paquete+"." + objetivo.getSimpleName() + "Proxy");
                    if(ret!=null){
                        proxys.put(objetivo, ret);
                        return ret;
                    }
                    else{
                        proxys.put(objetivo, objetivo);
                        return objetivo;
                    }
                }else{
                    // Si el objetivo no tiene inyecciones no se usa proxy
                    source.delete();
                    proxys.put(objetivo, objetivo);
                    return objetivo;
                }

            }
            else {
                // Si el objetivo es una interfaz no se usa un proxy
                proxys.put(objetivo, objetivo);
                return objetivo;
            }
        } catch (Exception e) {
            // Si ocurre alguna excepciÃ³n se elimina el proxy y se utiliza al objetivo como su propio
            // proxy
            if(pw!=null) pw.close();
            source.delete();
        }
        proxys.put(objetivo, objetivo);
        return objetivo;
    }

    /**
     * MÃ©todo encargado de retornar una instancia de la clase pasada como parÃ¡metro
     * @param c clase a instanciar
     * @return
     */
    public static Object instanciar(Class c) {
        try {
            // Se carga el proxy de la clase
            Class implementacion = crearProxy(c);

            // Se busca su constructor por defecto
            Constructor constructor = implementacion.getConstructor();

            // Se invoca el constructor
            Object objeto = constructor.newInstance();
            
            // Se verifica si existen mÃ©todos anotados para validaciÃ³n de integridad
            // en este caso se invocan despues del constructor
            for (Method m : c.getDeclaredMethods()) {
                if (m.isAnnotationPresent(PostConstructor.class)) {
                    //El mÃ©todo debe ser public y no recibir parametros
                    m.setAccessible(true);
                    if (m.getParameterTypes() == null || m.getParameterTypes().length == 0) {
                        m.invoke(objeto);
                    }
                }
            }

            // Se verifica si la clase tiene la anotaciÃ³n Init
            if(c.isAnnotationPresent(Init.class)){
                // Se inicializan los atributos afectador por las anotaciones Init
                CodigoInserciones.Init(objeto,c,c.getAnnotation(Init.class),null);
            }
            
            for (Field f : c.getDeclaredFields()) {
                // Se instancian los atributos anotados con Cargar
                if (f.isAnnotationPresent(Cargar.class)) {
                    f.setAccessible(true);
                    f.set(objeto, instanciar(f.getType()));
                }
            }

            return objeto;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
