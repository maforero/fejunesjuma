/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.RegistroVenta;
import com.losalpes.entities.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author feliperojas
 */
@Local
public interface IServicioReportesLocal {
    
    public List<RegistroVenta> historialComprasCliente(Usuario usuario);
}
