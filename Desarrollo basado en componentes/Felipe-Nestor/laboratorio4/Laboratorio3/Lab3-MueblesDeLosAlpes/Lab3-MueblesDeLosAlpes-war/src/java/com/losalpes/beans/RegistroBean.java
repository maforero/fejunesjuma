/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ RegistroBean.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles los Alpes
 * Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.losalpes.beans;

import com.losalpes.entities.Ciudad;
import com.losalpes.entities.Pais;
import com.losalpes.entities.Profesion;
import com.losalpes.entities.TipoDocumento;
import com.losalpes.entities.TipoUsuario;
import com.losalpes.entities.Usuario;
import com.losalpes.excepciones.OperacionInvalidaException;
import com.losalpes.servicios.IServicioRegistroMockLocal;
import com.losalpes.servicios.ServicioRegistroMock;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

/**
 * Managed Bean encargado de la administración de los usuarios del sistema
 * @author Juan Sebastián Urrego
 */
public class RegistroBean implements Serializable
{

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    
    /**
     * Relación con la interfaz que provee los servicios de administración de usuarios
     */
    @EJB
    private IServicioRegistroMockLocal usuarioServices;

    /**
     * Mensaje utilizado para mostrar información importante al usuario.
     */
    private String mensaje;
    
    /**
     * Referencia al objeto que se está mostrando al cliente. En este objeto se almacenan los datos del cliente.
     */
    private Usuario usuario;

    /**
     * País seleccionado en el registro
     */
    private Pais pais;

    /**
     * Nombre de la ciudad que se encuentra seleccionada actualmente
     */
    private String ciudad;

    /**
     * Lista con los países del sistema
     */
    private List<Pais> paises;

    /**
     * Lista con las ciudades pertenecientes al país actual
     */
    private List<Ciudad> ciudades;

    /**
     * Muestra la ventana de estado
     */
    private boolean mostrarVentana;

    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------

    /*
     * Constructor sin argumentos de la clase
     */
    public RegistroBean() 
    {
        paises = new ArrayList<Pais>();
        ciudades = new ArrayList<Ciudad>();   
        mostrarVentana=false;
        usuario = new Usuario();

        ArrayList<Ciudad> array = new ArrayList<Ciudad>();
        array.add(new Ciudad("Bogotá"));
        array.add(new Ciudad("Cali"));
        array.add(new Ciudad("Cartagena"));
        array.add(new Ciudad("Medellín"));
        array.add(new Ciudad("Santa Marta"));
        pais = new Pais("Colombia", array);
        paises.add(pais);

        ArrayList<Ciudad> array2 = new ArrayList<Ciudad>();
        array2.add(new Ciudad("Atlanta"));
        array2.add(new Ciudad("Chicago"));
        array2.add(new Ciudad("Miami"));
        array2.add(new Ciudad("New York"));
        array2.add(new Ciudad("Washington D.C"));      

        paises.add(new Pais("Estados Unidos", array2));

        ArrayList<Ciudad> array3 = new ArrayList<Ciudad>();
        array3.add(new Ciudad("Cambridge"));
        array3.add(new Ciudad("Canterbury"));
        array3.add(new Ciudad("Liverpool"));
        array3.add(new Ciudad("Manchester"));
        array3.add(new Ciudad("Oxford"));

        paises.add(new Pais("Inglaterra", array3));

        ciudades = pais.getCiudades();
    }

    //-----------------------------------------------------------
    // Getter y setters
    //-----------------------------------------------------------

    /**
     * Devuelve el usuario actual.
     * @return usuario Usuario actual
     */
    public Usuario getUsuario()
    {
        return usuario;
    }

    /**
     * Modifica el usuario actual
     * @param usuario Nuevo usuario
     */
    public void setUsuario(Usuario usuario)
    {
        this.usuario = usuario;
    }

    /**
     * Método que devuelve los tipos de documentos a ser visualizados
     * @return sitems Tipos de documentos
     */
    public SelectItem[] getTiposDocumentos()
    {
        TipoDocumento[] tipos = TipoDocumento.values();
        SelectItem[] sitems = new SelectItem[tipos.length];
        for (int i = 0; i < sitems.length; i++) {
            sitems[i] = new SelectItem(tipos[i]);
        }
        return sitems;
    }

    /**
     * Método que devuelve las posibles profesiones de un cliente
     * @return sitems Profesiones posibles
     */
    public SelectItem[] getProfesiones()
    {
        Profesion[] tipos = Profesion.values();
        SelectItem[] sitems = new SelectItem[tipos.length];
        for (int i = 0; i < sitems.length; i++) {
            sitems[i] = new SelectItem(tipos[i]);
        }
        return sitems;
    }

    /**
     * Método que devuelve los países del sistema
     * @return sitems Países del sistema
     */
    public SelectItem[] getPaises()
    {
        SelectItem[] sitems = new SelectItem[paises.size()];
        for (int i = 0; i < sitems.length; i++) {
            sitems[i] = new SelectItem(paises.get(i).getNombre());
        }
        return sitems;
    }

    /**
     * Método que retorna los tipos de documentos a ser visualizados para que el cliente escoja.
     * @return
     */
    public SelectItem[] getCiudades()
    {

        SelectItem[] sitems = new SelectItem[ciudades.size()];
        for (int i = 0; i < sitems.length; i++) {
            sitems[i] = new SelectItem(ciudades.get(i).getNombre());
        }

        return sitems;
    }

    /**
     * Método que retorna los tipos de usuario a ser visualizados 
     * @return
     */
    public SelectItem[] getTiposUsuario()
    {

        TipoUsuario[] tipos = TipoUsuario.values();
        SelectItem[] sitems = new SelectItem[tipos.length];
        for (int i = 0; i < sitems.length; i++) {
            sitems[i] = new SelectItem(tipos[i]);
        }
        return sitems;
    }

    /**
     * Devuelve el nombre del país que se encuentra seleccionado
     * @return
     */
    public String getPais()
    {
        return pais.getNombre();
    }

    /**
     * Modifica, dado el nombre, el objeto país de la clase
     * @param nombre Nombre del país a seleccionar
     */
    public void setPais(String nombre)
    {

        for (int i = 0; i < paises.size(); i++)
        {
            if (paises.get(i).getNombre().equals(nombre))
            {
                pais = paises.get(i);
                return;
            }
        }
    }

    /**
     * Devuelve el mensaje que contiene información sobre algún tipo de estado
     * @return mensaje Mensaje a devolver
     */
    public String getMensaje()
    {
        return mensaje;
    }

    /**
     * Devuelve el nombre de la ciudad seleccionada actualmente
     * @return ciudad Nombre de la ciudad
     */
    public String getCiudad()
    {
        return ciudad;
    }

    /**
     * Modifica el nombre de la ciudad seleccionada actualmente y actualiza el objeto
     * @param nombre Nuevo nommbre de ciudad
     */
    public void setCiudad(String nombre)
    {
        ciudad = nombre;
        usuario.setCiudad(new Ciudad(nombre));
    }

    /**
     * Devuelve el estado para mostrar o no la ventana popUp
     * @return mostrarVentana Estado para mostrar o no ventana
     */
    public boolean isMostrarVentana()
    {
        return mostrarVentana;
    }

    /**
     * Modifica el estado para mostrar la ventana popUp
     * @param mostrarVentana Nuevo estado
     */
    public void setMostrarVentana(boolean mostrarVentana)
    {
        this.mostrarVentana = mostrarVentana;
    }

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------

    /**
     * Value change listener que dado un cambio en los países actualiza las ciudades
     * @param event Evento de cambio
     */
    public void cambioPais(ValueChangeEvent event)
    {
        setPais(event.getNewValue().toString());
        ciudades = pais.getCiudades();
    }
    
    /**
     * Crea un usuario con los datos proporcionados por el cliente.
     * @return login Redirecciona a la página principal
     */
    public String registrarUsuario()
    {
        try
        {
            if(usuario.getTipoUsuario() == null)
            {
                usuario.setTipoUsuario(TipoUsuario.Cliente);
            }
            usuarioServices.registrar(usuario);          
            mensaje = "Su cuenta ha sido creada exitosamente.";
            mostrarVentana = true;
            destroyBean();            
            return "login";
        } 
        catch (Exception e)
        {
            mensaje = "Ocurrió un error al momento de crear su cuenta.";
            
            mostrarVentana = true;
            return "registro";
        }
    }

        /**
     * Crea un usuario con los datos proporcionados por el cliente.
     * @return login Redirecciona a la página principal
     */
    public void registrarAdministrador()
    {
        try
        {

            usuarioServices.registrar(usuario);
            mensaje = "Su cuenta ha sido creada exitósamente.";
            mostrarVentana = true;
            limpiar();
           
        }
        catch (Exception e)
        {
            mensaje = "Ocurrió un error al momento de crear su cuenta.";

            mostrarVentana = true;
            
        }
    }


    public List<Usuario> getClientes()
    {
        return usuarioServices.darClientes();
    }

    public void eliminar(ActionEvent evento) throws OperacionInvalidaException
    {
      
        try
        {
            FacesContext context = FacesContext.getCurrentInstance();
            Map map = context.getExternalContext().getRequestParameterMap();
            String clientId = (String) map.get("clientId");

            usuarioServices.eliminarCliente(clientId);

        }
        catch(OperacionInvalidaException e)
        {
            throw new OperacionInvalidaException(e.getMessage());
            
        }
    
    }
    /**
     * Método que remueve este bean del contexto.
     */
    public void destroyBean()
    {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("registroBean");
    }

    /**
     * Cierra la ventana popUp
     * @return login Devuelve a la ventana de inicio
     */
    public String cerrarVentana()
    {
        mostrarVentana=false;
        return "login";
    }

    /**
     * Elimina la información del usuario
     */
    public void limpiar()
    {
        usuario=new Usuario();
    }

}
