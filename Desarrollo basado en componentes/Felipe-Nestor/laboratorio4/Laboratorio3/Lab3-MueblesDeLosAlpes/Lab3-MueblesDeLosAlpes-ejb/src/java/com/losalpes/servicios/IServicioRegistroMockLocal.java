/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ IServicioRegistroMockLocal.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 * Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.losalpes.servicios;

import com.losalpes.entities.Usuario;
import com.losalpes.excepciones.OperacionInvalidaException;
import java.util.List;
import javax.ejb.Local;

/**
 * Contrato funcional de los servicios de autenticación en el sistema
 * @author Juan Sebastián Urrego
 */
@Local
public interface IServicioRegistroMockLocal
{

    /**
     * Verifica y registra un usuario en el sistema
     * @param u Usuario a persistir
     */
    public void registrar(Usuario u)throws OperacionInvalidaException;

    /**
     * Elimina un cliente del sistema dado su login
     * @param login Login único del usuario
     * @throws OperacionInvalidaException Excepción en caso de error operacional
     */
    public void eliminarCliente(String login) throws OperacionInvalidaException;

    /**
     * Devuelve todos los clientes del sistema
     * @return usuarios Lista con todos los usuarios del sistema
     */
    public List<Usuario> darClientes();
   
}
