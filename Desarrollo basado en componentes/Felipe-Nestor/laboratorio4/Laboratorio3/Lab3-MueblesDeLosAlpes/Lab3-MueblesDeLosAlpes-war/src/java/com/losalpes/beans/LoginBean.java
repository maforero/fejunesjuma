/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ LoginBean.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 * Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.losalpes.beans;

import com.losalpes.entities.TipoUsuario;
import com.losalpes.entities.Usuario;
import com.losalpes.excepciones.AutenticacionException;
import com.losalpes.servicios.IServicioSeguridadMockLocal;
import com.losalpes.servicios.ServicioSeguridadMock;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 * Managed bean encargado de la autenticación en el sistema
 * @author Juan Sebastián Urrego
 */
public class LoginBean implements Serializable
{

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    
    /**
     * Nombre del usuario
     */
    private String usuario;

    /**
     * Contraseña del usuario
     */
    private String contraseña;

    /**
     * Usuario de la sesión
     */
    private Usuario sesion;

    /**
     * Relación con la interfaz adecuada para la autenticación de usuarios
     */
    @EJB
    private IServicioSeguridadMockLocal servicio;

    /**
     * Mensaje de error
     */
    private String mensajeError;

    /**
     * Determina si existe error o no
     */
    private boolean error;

    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------

    /**
     * Constructor de la clase
     */
    public LoginBean()
    {
        error=false;
    }

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------

    /**
     * Realiza la autenticación de un usuario que desea entrar al sistema
     * @return tipoUsuario Devuelve el tipo de usuario
     */
    public String ingresar()
    {      
        try
        {
            sesion = servicio.ingresar(usuario, contraseña);
            if (sesion.getTipoUsuario() == TipoUsuario.Administrador)
            {
                return "administrador";
            }
            else
            {
                return "cliente";
            }
        }
        catch (AutenticacionException ex)
        {
            error=true;
            mensajeError=ex.getMessage();
            return "login";
        }
    }

    //-----------------------------------------------------------
    // Getters y setters
    //-----------------------------------------------------------

    /**
     * Devuelve el nombre del usuario
     * @return usuario Nombre del usuario
     */
    public String getUsuario()
    {
        return usuario;
    }

    /**
     * Modifica el nombre del usuario
     * @param usuario Nuevo nombre del usuario
     */
    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    /**
     * Devuelve la contraseña del usuario
     * @return contraseña Contraseña del usuario
     */
    public String getContraseña()
    {
        return contraseña;
    }

    /**
     * Modifica la contraseña de un usuario
     * @param contraseña Nueva contraseña
     */
    public void setContraseña(String contraseña)
    {
        this.contraseña = contraseña;
    }

    /**
     * Usuario a quien pertenece la sesión
     * @return sesion Usuario a quien pertenece la sesión
     */
    public Usuario getSesion()
    {
        return sesion;
    }

    /**
     * Modifica el usuario de la sesión
     * @param sesion Nuevo usuario
     */
    public void setSesion(Usuario sesion)
    {
        this.sesion = sesion;
    }

    /**
     * Devuelve el estado de la autenticación (si es error o no)
     * @return error Estado de autenticación
     */
    public boolean isError()
    {
        return error;
    }

    /**
     * Modifica el estado de error
     * @param error Nuevo estado
     */
    public void setError(boolean error)
    {
        this.error = error;
    }

    /**
     * Devuelve un mensaje de error
     * @return mensaje Mensaje de error
     */
    public String getMensajeError()
    {
        return mensajeError;
    }

    /**
     * Modifica el mensaje de error
     * @param mensajeError Nuevo mensaje de error
     */
    public void setMensajeError(String mensajeError)
    {
        this.mensajeError = mensajeError;
    }

    /**
     * Cierra el panel de error
     */
    public void cerrarPanelError()
    {
        error=false;
        mensajeError=null;
    }

    /**
     * Realiza un logout de la sesión del cliente
     * @return login Devuelve a la página de inicio
     */
    public String logout()
    {
        sesion=null;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("loginBean");
        return "login";
    }
}
