/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ ServicioCatalogoMockTest.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 * Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.losalpes.servicios;

import com.losalpes.entities.TipoMueble;
import com.losalpes.entities.Mueble;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Clase encargada de realizar pruebas unitarias
 * @author Juan Sebastián Urrego
 */
public class ServicioCatalogoMockTest
{

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    /**
     * Arreglo de muebles de prueba
     */
    private ArrayList<Mueble> muebles;

    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------

    /**
     * Constructor sin argumentos
     */
    public ServicioCatalogoMockTest()
    {
        
    }

    //-----------------------------------------------------------
    // Métodos de inicialización y terminación
    //-----------------------------------------------------------

    /**
     * Método que se ejecuta antes de comenzar la prueba unitaria
     * Se encarga de inicializar todo lo necesario para la prueba
     */
    @Before
    public void setUp() 
    {

        //Inicializa arreglo de prueba
        muebles=new ArrayList<Mueble>();

        //Agrega los muebles del sistema
        muebles.add(new Mueble(1L, "Silla clásica", "Una confortable silla con estilo del siglo XIX.", TipoMueble.Interior, 45, "", 123));
        muebles.add(new Mueble(2L, "Silla moderna", "Lo último en la moda de interiores. Esta silla le brindará la comodidad e innovación que busca", TipoMueble.Interior, 50, "", 5464));
        muebles.add(new Mueble(3L, "Mesa de jardín", "Una bella mesa para comidas y reuniones al aire libre.", TipoMueble.Exterior, 100, "", 4568));
        muebles.add(new Mueble(5L, "Sillón new wave", "Innovador y cómodo. No existen mejores palabras para describir este hermoso sillón.", TipoMueble.Interior, 60, "", 5655));
        muebles.add(new Mueble(6L, "Mesa para comedor", "Sus cenas no tendrán mejor acompañante. Una mesa para seis personas con un hermoso diseño clásico.", TipoMueble.Interior, 70, "", 1345));
        muebles.add(new Mueble(7L, "Cama king", "Una hermosa cama hecha en cedro para dos personas. Sus sueños no volveran a ser iguales.", TipoMueble.Interior, 50, "", 63358));
        muebles.add(new Mueble(8L, "Silla de jardín", "Una bella silla para comidas y reuniones al aire libre.", TipoMueble.Exterior, 65, "", 678));
        muebles.add(new Mueble(9L, "Camarote junior", "Con diseño moderno. Sus hijos ahora podrán tener unos felices sueños.", TipoMueble.Interior, 85, "", 56565));


    }

    /**
     * Método que se ejecuta después de haber ejecutado la prueba
     */
    @After
    public void tearDown()
    {
        
    }

    //-----------------------------------------------------------
    // Métodos de prueba
    //-----------------------------------------------------------

    /**
     * Prueba para agregar un mueble en el sistema
     */
    @Test
    public void testAgregarMueble()
    {
        System.out.println("agregarMueble");
        Mueble mueble = new Mueble(9L,"SillaTest","Una confortable silla.",TipoMueble.Interior,60,"",5342);
        ServicioCatalogoMock instance = new ServicioCatalogoMock();
        int actual=instance.darMuebles().size();
        instance.agregarMueble(mueble);
        int esperado=instance.darMuebles().size();
        assertEquals(esperado,actual+1);
    }

    /**
     * Prueba para obtener todos los muebles del sistema
     */
    @Test
    public void testDarMuebles()
    {
        System.out.println("darMuebles");
        ServicioCatalogoMock instance = new ServicioCatalogoMock();
        List result = instance.darMuebles();
        assertNotNull(result);          
    }

}