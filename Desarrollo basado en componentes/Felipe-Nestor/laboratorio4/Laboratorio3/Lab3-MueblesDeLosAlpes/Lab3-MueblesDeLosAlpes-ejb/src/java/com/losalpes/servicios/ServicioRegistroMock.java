/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ ServicioRegistroMock.java
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
import com.losalpes.entities.Ciudad;
import com.losalpes.entities.Usuario;
import com.losalpes.excepciones.OperacionInvalidaException;
import com.losalpes.servicios.persistencia.IServicioPersistenciaCiudadLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Implementación de los servicios de registro de un cliente en el sistema
 * @author Juan Sebastián Urrego
 */
@Stateless
public class ServicioRegistroMock implements IServicioRegistroMockRemote, IServicioRegistroMockLocal
{
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    
    /**
     * Interface con referencia al servicio de persistencia en el sistema
     */
    @EJB
    private IServicioPersistenciaMockLocal persistencia;

    @EJB
    private IServicioPersistenciaCiudadLocal persistenciaCiudad;
    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------

    /**
     * Constructor de la clase sin argumentos
     */
     public ServicioRegistroMock()
     {

     }

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------

    /**
     * Verifica y registra un usuario en el sistema
     * @param u Usuario a persistir
     */
    @Override
    public void registrar(Usuario u)throws OperacionInvalidaException
    {
        try
        {
            if(u.getDocumento()!=0)
            {
                Ciudad cuidad = persistenciaCiudad.obtenerCiudadPorNombre(u.getCiudad().getNombre());
                u.setCiudad(cuidad);
                persistencia.create(u);
            }
            else
            {
                throw new OperacionInvalidaException("El número de documento no es válido");
            }
        }
        catch (OperacionInvalidaException ex)
        {
            throw new OperacionInvalidaException(ex.getMessage());
        }
    }

    /**
     * Elimina un cliente del sistema dado su login
     * @param login Login del cliente
     * @throws OperacionInvalidaException Excepción que es lanzada en caso de ocurrir un error
     */
    @Override
    public void eliminarCliente(String login) throws OperacionInvalidaException
    {
        try
        {
        Usuario u=(Usuario) persistencia.findById(Usuario.class, login);
        persistencia.delete(u);
        }
        catch(OperacionInvalidaException e)
        {
            throw new OperacionInvalidaException("Ocurrió un error al momento de eliminar");
        }
    }

    /**
     * Devuelve los clientes del sistema
     * @return clientes Lista con todos los clientes del sistema
     */
    @Override
    public List<Usuario> darClientes()
    {
        return(List<Usuario>) persistencia.findAll(Usuario.class);
    }

}
