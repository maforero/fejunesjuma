/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelMuebles.java,v 1.1 2010/05/27 16:49:35 ga.sotelo69 Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 2.1
 *
 * Ejercicio: Taller 1 - anotaciones
 * Autor: German Augusto Sotelo - 31-may-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package interfaz;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mundo.ManejadorMuebles;
import mundo.Mueble;

/**
 * Panel encargado de mostrar los muebles
 * @author Germán Sotelo
 */
public class PanelMuebles extends JPanel implements ActionListener{

    static final String NUEVO = "NUEVO";

    static final String ELIMINAR = "ELIMINAR";

    static final String REINICIAR = "REINICIAR";

    /**
     * Manejador de los muebles
     */
    private ManejadorMuebles manager;

    /**
     * Entidad encargada de contener el modelo de la tabla de muebles
     */
    private myTableModel dtmMuebles = new myTableModel(new String[]{"Id","Nombre","Precio"});

    /**
     * Entidad encarga de visualizar la tabla de muebles
     */
    private JTable tablaMuebles;

    /**
     * Método constructor del panel encargado de mostrar los muebles de MLA
     * @param manager Entidad encargada de manejar la lista de muebles de MLA
     */
    public PanelMuebles(ManejadorMuebles manager) {
        super(new BorderLayout());
        this.manager=manager;

        tablaMuebles = new JTable(dtmMuebles);

        JScrollPane jspMuebles = new JScrollPane(tablaMuebles);
        add(jspMuebles,BorderLayout.CENTER);

        JPanel opcionesMueble = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx=0;
        gbc.gridy=-1;

        gbc.insets=new Insets(10,10,10,10);
        gbc.fill=GridBagConstraints.BOTH;
        gbc.anchor=GridBagConstraints.NORTH;

        JButton nuevoMueble = new JButton("Nuevo");
        nuevoMueble.addActionListener(this);
        nuevoMueble.setActionCommand(NUEVO);
        JButton eliminarMueble = new JButton("Eliminar");
        eliminarMueble.addActionListener(this);
        eliminarMueble.setActionCommand(ELIMINAR);
        JButton reiniciarmueble = new JButton("Reiniciar");
        reiniciarmueble.addActionListener(this);
        reiniciarmueble.setActionCommand(REINICIAR);
        opcionesMueble.add(nuevoMueble,gbc);
        opcionesMueble.add(eliminarMueble,gbc);
        opcionesMueble.add(reiniciarmueble,gbc);

        JPanel temporal = new JPanel(new BorderLayout());
        temporal.add(opcionesMueble,BorderLayout.NORTH);
        add(temporal,BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(NUEVO)){
            // Se crea un nuevo mueble y se añade al modelo de la tabla de vendedores
            Mueble c = manager.nuevoMueble();
            if (c == null) {
                return;
            }
            Object[] data = {c.getId(),""+c.getNombre(),c.getPrecio()};
            dtmMuebles.addRow(data);
        }
        else if(e.getActionCommand().equals(ELIMINAR)){
            int s = tablaMuebles.getSelectedRow();
            if(s!=-1){
                // Se obtiene el ID del mueble seleccionado y luego se elimina
                manager.eliminarMueble((Integer)dtmMuebles.getValueAt(s,0));
                dtmMuebles.removeRow(s);
                tablaMuebles.revalidate();
            }
        }
        else if(e.getActionCommand().equals(REINICIAR)){
            int s = tablaMuebles.getSelectedRow();
            if(s!=-1){
                // Se obtiene el ID del mueble seleccionado y luego se reinicia
                Mueble m = manager.findMueble((Integer)dtmMuebles.getValueAt(s,0));
                m.reiniciar();
                dtmMuebles.setValueAt(m.getId(),s,0);
                dtmMuebles.setValueAt(m.getNombre(),s,1);
                dtmMuebles.setValueAt(m.getPrecio(),s,2);
            }
        }
    }

    /**
     * Clase encargada de manejar el modelo de la tabla de muebles
     */
    public class myTableModel extends DefaultTableModel{
        public myTableModel(String[] columns) {
            super(columns,0);
        }

        @Override
        public void setValueAt(Object aValue, int row, int column) {
            Mueble mueble = manager.findMueble((Integer)dtmMuebles.getValueAt(row,0));
            if(column==1){
                mueble.setNombre(""+aValue);
                super.setValueAt(mueble.getNombre(), row, column);
            }
            else if(column==2){
                try{
                    mueble.setPrecio(Double.parseDouble(""+aValue));
                    super.setValueAt(mueble.getPrecio(), row, column);
                }catch(Exception e){
                }
            }
        }
    }
}
