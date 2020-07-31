
package Controlador;
import Modelo.DAO.M_DAO_Tipo_Servicio;
import Modelo.VO.M_VO_Tipo_Servicio;
import Vista.V_Tipo_Servicios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class C_Tipo_Servicios implements ActionListener{
    private boolean TipoAccion=true;
    private M_VO_Tipo_Servicio voTipo_Servicios;
    private M_DAO_Tipo_Servicio daoTipo_Servicios;
    private V_Tipo_Servicios frm;
    
    public C_Tipo_Servicios(M_DAO_Tipo_Servicio daoTipo_Servicios, V_Tipo_Servicios frm,M_VO_Tipo_Servicio voTipo_Servicios) {
        this.daoTipo_Servicios = daoTipo_Servicios;
        this.frm = frm;
        this.voTipo_Servicios = voTipo_Servicios;
        llenaGrid();
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnactualizar.addActionListener(this);
        this.frm.btneliminar.addActionListener(this);
        this.frm.btncancelar.addActionListener(this);

        this.frm.txtDescripcion.addActionListener(this);
        this.frm.txtTipoServicio.addActionListener(this);
        this.frm.lbl_id.setVisible(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm.btnGuardar) {
            Guardar_datos();
        }
        if (e.getSource() == frm.btnactualizar) {
            Pasar_Datos_Actualizar();
        }
        if (e.getSource() == frm.btneliminar) {
            Eliminar_Tipo_Servicios();
        }
        if (e.getSource() == frm.btncancelar) {
            LimpiarCajas();
        }
    }
    public void llenaGrid() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.frm.tblTipo_Servicios.getModel();
            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            ResultSet rs = daoTipo_Servicios.Consulta_Tipo_Servicio();
            while (rs.next()) {
                String id = String.valueOf(rs.getInt("tserv_Id"));
                String tipose = rs.getString("tserv_Nombre");
                String descripcion = rs.getString("tserv_Descripcion");
                modelo.addRow(new Object[]{id, tipose,descripcion});
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void Guardar_datos(){
        if (TipoAccion==true) {
            Insertar_Tipo_Servicios();
        }else if(TipoAccion==false){
            Actualizar_Tipo_Servicios();
        }    
    }
    public void Pasar_Datos_Actualizar(){
        int row = frm.tblTipo_Servicios.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            String ID = frm.tblTipo_Servicios.getValueAt(frm.tblTipo_Servicios.getSelectedRow(), 0).toString();
            String Tiposer = frm.tblTipo_Servicios.getValueAt(frm.tblTipo_Servicios.getSelectedRow(), 1).toString();
            String Descripcion = frm.tblTipo_Servicios.getValueAt(frm.tblTipo_Servicios.getSelectedRow(), 2).toString();
            frm.txtTipoServicio.setText(Tiposer);
            frm.txtDescripcion.setText(Descripcion);
            frm.lbl_id.setText(ID);
            frm.lbl_accion.setText("Actualzar tipo de servicios");
            TipoAccion=false;
            
        }
    }
    public int Insertar_Tipo_Servicios() {
        String TipoSer = frm.txtTipoServicio.getText();
        String Descripcion = frm.txtDescripcion.getText();
        voTipo_Servicios.setTserv_Nombre(TipoSer);
        voTipo_Servicios.setTserv_Descripcion(Descripcion);
        int res = 0;
        if ("".equals(TipoSer)||"".equals(Descripcion)) {
            JOptionPane.showMessageDialog(null, "Por favor de llenar los campos vacios");
        } else {
            try {
                res = daoTipo_Servicios.Insertar_Tipo_Servicio(voTipo_Servicios);
                if (res != 0) {
                    JOptionPane.showMessageDialog(null, "Se guardó correctamente");
                    llenaGrid();
                    LimpiarCajas();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar los datos");
                }
            } catch (Exception e) {
                System.out.print("Error: " + e);
            }
        }
        return res;
    }

    public int Actualizar_Tipo_Servicios() {
        String ID_A = frm.lbl_id.getText();
        String TipoSer = frm.txtTipoServicio.getText();
         String Descripcion = frm.txtDescripcion.getText();
        int res = 0;
        int row = this.frm.tblTipo_Servicios.getSelectedRow();        
        voTipo_Servicios.setTserv_Id(Integer.valueOf(ID_A));
        voTipo_Servicios.setTserv_Nombre(TipoSer);
        voTipo_Servicios.setTserv_Descripcion(Descripcion);
        if ("".equals(ID_A) || "".equals(TipoSer) || "".equals(Descripcion)) {
            JOptionPane.showMessageDialog(null, "Por favor de llenar los campos vacios");
        } else {
                try {
                    res = daoTipo_Servicios.Actualizar_Tipo_Servicio(voTipo_Servicios);
                    if (res != 0) {
                        JOptionPane.showMessageDialog(null, "Se actualizó correctamente");
                        llenaGrid();
                        LimpiarCajas();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al actualizar los datos");
                    }
                } catch (Exception e) {
                    System.out.print(e);
                }
            }
        return res;
    }

    public void Eliminar_Tipo_Servicios() {
        int row = frm.tblTipo_Servicios.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            int dato = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar ?");
            if (dato == 0) {
                DefaultTableModel mode = (DefaultTableModel) frm.tblTipo_Servicios.getModel();
                int clave = Integer.valueOf(mode.getValueAt(row, 0).toString());
                int r;
                voTipo_Servicios.setTserv_Id(clave);
                r = daoTipo_Servicios.Eliminar_Tipo_Servicio(voTipo_Servicios);
                if (r != 0) {
                    JOptionPane.showMessageDialog(null, "El Registro se eliminó correctamente");
                    llenaGrid();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Eliminar el Registro");
                }
            }
        }
    }

    public void LimpiarCajas() {

        frm.txtTipoServicio.setText("");
        frm.txtDescripcion.setText("");
        frm.lbl_id.setText("ID");
        frm.lbl_accion.setText("Nuevo tipo de servicios");
        TipoAccion=true;
    }
}
