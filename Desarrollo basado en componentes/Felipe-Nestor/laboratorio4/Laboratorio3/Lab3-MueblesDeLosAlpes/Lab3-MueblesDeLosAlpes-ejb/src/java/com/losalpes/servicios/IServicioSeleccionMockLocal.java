/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Ciudad;
import com.losalpes.entities.Pais;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author feliperojas
 */
@Local
public interface IServicioSeleccionMockLocal {
    
    public List<Pais> obtenerPaises();
}
