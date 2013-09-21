/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ PojoTest.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 * Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.losalpes.pojos;

import com.losalpes.entities.ExperienciaVendedor;
import com.losalpes.entities.Mueble;
import com.losalpes.entities.Usuario;
import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.PojoValidator;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.NoPublicFieldsRule;
import com.openpojo.validation.rule.impl.NoStaticExceptFinalRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Clase encargada de realizar pruebas unitarias
 * @author Juan Sebastián Urrego
 */
public class PojoTest
{

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    /**
     * Listado de clases a validar como pojos
     */
    private List<PojoClass> pojoClasses;

    /**
     * Entidad encargada de validar los pojos
     */
    private PojoValidator pojoValidator;

    //-----------------------------------------------------------
    // Métodos de inicialización y terminación
    //-----------------------------------------------------------

    /**
     * Crea el escenario de pruebas
     */
    @Before
    public void setUp()
    {
        //Inicialización de los POJO´s
        pojoClasses=new ArrayList<PojoClass>();
        pojoClasses.add(PojoClassFactory.getPojoClass(Mueble.class));
        pojoClasses.add(PojoClassFactory.getPojoClass(Usuario.class));
        pojoClasses.add(PojoClassFactory.getPojoClass(ExperienciaVendedor.class));

        pojoValidator = new PojoValidator();

        //Crea las reglas para validar la estructura de los POJO´s
        pojoValidator.addRule(new NoPublicFieldsRule());
        pojoValidator.addRule(new NoStaticExceptFinalRule());
        pojoValidator.addRule(new GetterMustExistRule());
        pojoValidator.addRule(new SetterMustExistRule());

        //Crea las pruebas para validar el comportamiento de los POJO´s
        pojoValidator.addTester(new SetterTester());
        pojoValidator.addTester(new GetterTester());
    }
    
    //-----------------------------------------------------------
    // Métodos de prueba
    //-----------------------------------------------------------

    /**
     * Prueba que todas las clases sean pojos válidos
     */
    @Test
    public void testPojoStructureAndBehavior()
    {
        for (PojoClass pojoClass : pojoClasses)
        {
            pojoValidator.runValidation(pojoClass);
        }
    }
}
