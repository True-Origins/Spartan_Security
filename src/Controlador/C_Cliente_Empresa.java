package Controlador;
import Modelo.DAO.M_DAO_Cliente_Empresa;
import Modelo.VO.M_VO_Cliente_Empresa;
import Vista.V_Cliente_Empresa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class C_Cliente_Empresa implements ActionListener{
    private boolean TipoAccion=true;
    M_DAO_Cliente_Empresa daoEmpresa;
    M_VO_Cliente_Empresa voEmpresa;
    V_Cliente_Empresa frm;
    public C_Cliente_Empresa(M_DAO_Cliente_Empresa daoEmpresa, V_Cliente_Empresa frm,M_VO_Cliente_Empresa voEmpresa){
        this.daoEmpresa=daoEmpresa;
        this.frm=frm;
        this.voEmpresa=voEmpresa;
        llenaGrid();
        this.frm.btnguardar.addActionListener(this);
        this.frm.btnactualizar.addActionListener(this);
        this.frm.btneliminar.addActionListener(this);
        this.frm.btncancelar.addActionListener(this);
        this.frm.txtNombre.addActionListener(this);
        this.frm.txtRFC.addActionListener(this);
        this.frm.txtdireccion.addActionListener(this);
        this.frm.txtcorreo.addActionListener(this);
        this.frm.txttelefono.addActionListener(this); 
        this.frm.txtContacto.addActionListener(this);
        this.frm.txtContactoCargo.addActionListener(this);
        this.frm.txtAmbito.addActionListener(this);
        this.frm.txtReferencia.addActionListener(this);
          
        this.frm.lbl_id.setVisible(false);        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm.btnguardar) {
            Guardar_datos();
        }
        if (e.getSource() == frm.btnactualizar) {
            Pasar_Datos_Actualizar();
        }
        if (e.getSource() == frm.btneliminar) {
            Eliminar_Cliente_Empresa();
        }
        if (e.getSource() == frm.btncancelar) {
            LimpiarCajas();
        }
    }
    public void llenaGrid(){
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.frm.tblClienteEmpresa.getModel();
            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            ResultSet rs =daoEmpresa.Consulta_Cliente_Empresa();
            while (rs.next()) {
                String id = String.valueOf(rs.getInt("clE_Id"));
                String nombre = rs.getString("clE_NombreEmpresa");
                String rfc = rs.getString("clE_RFC");
                String direccion = rs.getString("clE_Direccion");
                String correo = rs.getString("clE_Correo");
                String telefono = rs.getString("clE_Telefono");
                String contactonombre = rs.getString("clE_ContactoNombre");
                String contactocargo = rs.getString("clE_ContactoCargo");
                String ambito = rs.getString("clE_AmbitoEmpresa");
                String referencias = rs.getString("clE_Referencias");
                modelo.addRow(new Object[]{id, nombre,rfc,direccion,correo,telefono,contactonombre,contactocargo,ambito,referencias});               
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void Pasar_Datos_Actualizar(){
        int row = frm.tblClienteEmpresa.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            String ID = frm.tblClienteEmpresa.getValueAt(frm.tblClienteEmpresa.getSelectedRow(), 0).toString();
            String nombre = frm.tblClienteEmpresa.getValueAt(frm.tblClienteEmpresa.getSelectedRow(), 1).toString();
            String rfc = frm.tblClienteEmpresa.getValueAt(frm.tblClienteEmpresa.getSelectedRow(), 2).toString();
            String direccion = frm.tblClienteEmpresa.getValueAt(frm.tblClienteEmpresa.getSelectedRow(), 3).toString();
            String correo = frm.tblClienteEmpresa.getValueAt(frm.tblClienteEmpresa.getSelectedRow(), 4).toString();
            String telefono = frm.tblClienteEmpresa.getValueAt(frm.tblClienteEmpresa.getSelectedRow(), 5).toString();
            String contactonombre = frm.tblClienteEmpresa.getValueAt(frm.tblClienteEmpresa.getSelectedRow(), 6).toString();
            String contactocargo = frm.tblClienteEmpresa.getValueAt(frm.tblClienteEmpresa.getSelectedRow(), 7).toString();
            String ambito = frm.tblClienteEmpresa.getValueAt(frm.tblClienteEmpresa.getSelectedRow(), 8).toString();
            String referencias = frm.tblClienteEmpresa.getValueAt(frm.tblClienteEmpresa.getSelectedRow(), 9).toString();
            frm.txtNombre.setText(nombre);
            frm.txtRFC.setText(rfc);
            frm.txtdireccion.setText(direccion);
            frm.txtcorreo.setText(correo);
            frm.txttelefono.setText(telefono);
            frm.txtContacto.setText(contactonombre);
            frm.txtContactoCargo.setText(contactocargo);
            frm.txtAmbito.setText(ambito);
            frm.txtReferencia.setText(referencias);
            frm.lbl_id.setText(ID);
            frm.lbl_accion.setText("Actualizar cliente empresa");
            TipoAccion=false;            
        }
    }
    public void Guardar_datos(){
        if (TipoAccion==true) {
            Insertar_Empresa();
        }else if(TipoAccion==false){
            Actualizar_Empresa();
        }    
    }
   public int Insertar_Empresa() {
        String nombre = frm.txtNombre.getText();
        String rfc = frm.txtRFC.getText();
        String direccion = frm.txtdireccion.getText();
        String correo = frm.txtcorreo.getText();
        String telefono = frm.txttelefono.getText();
        String contactopersona = frm.txtContacto.getText();
        String contactocargo = frm.txtContactoCargo.getText();
        String ambito = frm.txtAmbito.getText();
        String referencia = frm.txtReferencia.getText();       
        
        voEmpresa.setClE_NombreEmpresa(nombre);
        voEmpresa.setClE_RFC(rfc);
        voEmpresa.setClE_Direccion(direccion);
        voEmpresa.setClE_Correo(correo);
        voEmpresa.setClE_Telefono(telefono);
        voEmpresa.setClE_ContactoNombre(contactopersona);
        voEmpresa.setClE_ContactoCargo(contactocargo);
        voEmpresa.setClE_AmbitoEmpresa(ambito);
        voEmpresa.setClE_Referencias(referencia);
        
        int res = 0;
        if ("".equals(nombre)||"".equals(rfc) || "".equals(direccion)||"".equals(correo) || "".equals(telefono)||"".equals(contactopersona)||"".equals(contactocargo) || "".equals(ambito)||"".equals(referencia)) {
            JOptionPane.showMessageDialog(null, "Por favor de llenar los campos vacios");
        } else {
            try {
                res = daoEmpresa.Insertar_Cliente_Empresa(voEmpresa);
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
    public int Actualizar_Empresa() {
        String id_Soc = frm.lbl_id.getText();
        String nombre = frm.txtNombre.getText();
        String rfc = frm.txtRFC.getText();
        String direccion = frm.txtdireccion.getText();
        String correo = frm.txtcorreo.getText();
        String telefono = frm.txttelefono.getText();
        String contactopersona = frm.txtContacto.getText();
        String contactocargo = frm.txtContactoCargo.getText();
        String ambito = frm.txtAmbito.getText();
        String referencia = frm.txtReferencia.getText(); 
        int res = 0;
        int row = this.frm.tblClienteEmpresa.getSelectedRow();
        voEmpresa.setClE_Id(Integer.valueOf(id_Soc));
        voEmpresa.setClE_NombreEmpresa(nombre);
        voEmpresa.setClE_RFC(rfc);
        voEmpresa.setClE_Direccion(direccion);
        voEmpresa.setClE_Correo(correo);
        voEmpresa.setClE_Telefono(telefono);
        voEmpresa.setClE_ContactoNombre(contactopersona);
        voEmpresa.setClE_ContactoCargo(contactocargo);
        voEmpresa.setClE_AmbitoEmpresa(ambito);
        voEmpresa.setClE_Referencias(referencia);
        if ("".equals(id_Soc)|| "".equals(nombre)||"".equals(rfc) || "".equals(direccion)||"".equals(correo) || "".equals(telefono)||"".equals(contactopersona)||"".equals(contactocargo) || "".equals(ambito)||"".equals(referencia)) {
                JOptionPane.showMessageDialog(null, "Por favor de llenar los campos vacios");
            } else {
                try {
                    res = daoEmpresa.Actualizar_Cliente_Empresa(voEmpresa);
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
    public void Eliminar_Cliente_Empresa() {
        int row = frm.tblClienteEmpresa.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna por favor");
        } else {
            int dato = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar ?");
            if (dato == 0) {
                DefaultTableModel mode = (DefaultTableModel) frm.tblClienteEmpresa.getModel();
                int clave = Integer.valueOf(mode.getValueAt(row, 0).toString());
                int r;
                voEmpresa.setClE_Id(clave);
                r = daoEmpresa.Eliminar_Cliente_Empresa(voEmpresa);

                if (r != 0) {

                    JOptionPane.showMessageDialog(null, "El se eliminó correctamente");
                    llenaGrid();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                }
            }

        }
    }
    public void LimpiarCajas() {
        frm.txtNombre.setText("");
        frm.txtRFC.setText("");
        frm.txtdireccion.setText("");
        frm.txtcorreo.setText("");
        frm.txttelefono.setText("");
        frm.txtContacto.setText("");
        frm.txtContactoCargo.setText("");
        frm.txtAmbito.setText("");
        frm.txtReferencia.setText("");
        frm.lbl_id.setText("ID");
        frm.lbl_accion.setText("Nuevo cliente empresa");
        TipoAccion=true;
    }    
}
