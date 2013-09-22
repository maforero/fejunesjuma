/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios.persistencia;

import com.losalpes.entities.Ciudad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author feliperojas
 */
@Local
public interface IServicioPersistenciaCiudadLocal {
    
    public List<Ciudad> obtenerCiudades();
    
    public Ciudad obtenerCiudadPorNombre(String nombre);
}
