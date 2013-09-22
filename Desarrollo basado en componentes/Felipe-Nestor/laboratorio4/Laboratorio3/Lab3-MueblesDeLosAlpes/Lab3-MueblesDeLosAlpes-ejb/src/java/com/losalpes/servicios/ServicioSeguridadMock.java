/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ ServicioSeguridadMock.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 * Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.losalpes.servicios;

import com.losalpes.servicios.persistencia.IServicioPersistenciaMockLocal;
import com.losalpes.entities.Usuario;
import com.losalpes.excepciones.AutenticacionException;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Clase que se encarga de la autenticación de un usuario en el sistema
 * @author Juan Sebastián Urrego
 */
@Stateless
public class ServicioSeguridadMock implements IServicioSeguridadMockLocal
{

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    /**
     * Interface con referencia al servicio de persistencia en el sistema
     */
    @EJB
    private IServicioPersistenciaMockLocal persistencia;

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------

    /**
     * Constructor sin argumentos de la clase
     */
    public ServicioSeguridadMock()
    {

    }

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------

    /**
     * Registra el ingreso de un usuario al sistema.
     * @param nombre Login del usuario que quiere ingresar al sistema.
     * @param contraseña Contraseña del usuario que quiere ingresar al sistema.
     * @return usuario Retorna el objeto que contiene la información del usuario que ingreso al sistema.
     */
    @Override
    public Usuario ingresar(String nombre, String contraseña) throws AutenticacionException
    {
   
        Usuario u = (Usuario) persistencia.findById(Usuario.class, nombre);

        if (u != null)
        {
            if (u.getLogin().equals(nombre) && u.getContraseña().equals(contraseña))
            {
                return u;
            } 
            else
            {
                throw new AutenticacionException("La contraseña no es válida. Por favor, asegúrate de que el bloqueo de mayúsculas no está activado e inténtalo de nuevo.");
            }
        } 
        else
        {
            throw new AutenticacionException("El nombre de usuario proporcionado no pertenece a ninguna cuenta.");
        }
    }
}
