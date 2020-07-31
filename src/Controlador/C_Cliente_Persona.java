package Controlador;
import Modelo.DAO.M_DAO_Cliente_Persona;
import Modelo.VO.M_VO_Cliente_Persona;
import Vista.V_Cliente_Persona;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class C_Cliente_Persona implements ActionListener{
    private boolean TipoAccion=true;
    private M_DAO_Cliente_Persona daoPersona;
    private M_VO_Cliente_Persona voPersona;
    private V_Cliente_Persona frm;
    public C_Cliente_Persona(M_DAO_Cliente_Persona daoPersona,V_Cliente_Persona frm,M_VO_Cliente_Persona voPersona){
        this.daoPersona=daoPersona;
        this.frm=frm;
        this.voPersona=voPersona;
        llenaGrid();
        this.frm.btnguardar.addActionListener(this);
        this.frm.btnactualizar.addActionListener(this);
        this.frm.btneliminar.addActionListener(this);
        this.frm.btncancelar.addActionListener(this);
        this.frm.txtNombre.addActionListener(this);
        this.frm.txtApellidoPaterno.addActionListener(this);
        this.frm.txtApellidoMaterno.addActionListener(this);
        this.frm.txtcorreo.addActionListener(this);
        this.frm.txtpassword.addActionListener(this);
        this.frm.txttelefono.addActionListener(this);        
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
            Eliminar_Persona();
        }
        if (e.getSource() == frm.btncancelar) {
            LimpiarCajas();
        }
    }
    public void llenaGrid(){
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.frm.tblClientePersona.getModel();
            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            ResultSet rs = daoPersona.Consulta_Cliente_Persona();
            while (rs.next()) {
                String id = String.valueOf(rs.getInt("clP_Id"));
                String nombre = rs.getString("clP_Nombre");
                String apellidop = rs.getString("clP_ApellidoP");
                String apellidom = rs.getString("clP_ApellidoM");
                String correo = rs.getString("clP_Correo");
                String contra = rs.getString("clP_Password");
                String telefono = rs.getString("clP_Telefono");
                modelo.addRow(new Object[]{id, nombre,apellidop,apellidom,correo,contra,telefono});               
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void Pasar_Datos_Actualizar(){
        int row = frm.tblClientePersona.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            String ID = frm.tblClientePersona.getValueAt(frm.tblClientePersona.getSelectedRow(), 0).toString();
            String nombre = frm.tblClientePersona.getValueAt(frm.tblClientePersona.getSelectedRow(), 1).toString();
            String ap = frm.tblClientePersona.getValueAt(frm.tblClientePersona.getSelectedRow(), 2).toString();
            String am = frm.tblClientePersona.getValueAt(frm.tblClientePersona.getSelectedRow(), 3).toString();
            String correo = frm.tblClientePersona.getValueAt(frm.tblClientePersona.getSelectedRow(), 4).toString();
            String contra = frm.tblClientePersona.getValueAt(frm.tblClientePersona.getSelectedRow(), 5).toString();
            String telefono = frm.tblClientePersona.getValueAt(frm.tblClientePersona.getSelectedRow(), 6).toString();

            frm.txtNombre.setText(nombre);
            frm.txtApellidoPaterno.setText(ap);
            frm.txtApellidoMaterno.setText(am);
            frm.txtcorreo.setText(correo);
            frm.txtpassword.setText(contra);
            frm.txttelefono.setText(telefono);
            frm.lbl_id.setText(ID);
            frm.lbl_accion.setText("Actualizar cliente persona");
            TipoAccion=false;            
        }
    }
    public void Guardar_datos(){
        if (TipoAccion==true) {
            Insertar_Persona();
        }else if(TipoAccion==false){
            Actualizar_Persona();
        }    
    }
    public int Insertar_Persona() {
        String nombre = frm.txtNombre.getText();
        String ap = frm.txtApellidoPaterno.getText();
        String am = frm.txtApellidoMaterno.getText();
        String correo = frm.txtcorreo.getText();
        String contra = frm.txtpassword.getText();
        String telefono = frm.txttelefono.getText();
        voPersona.setClP_Nombre(nombre);
        voPersona.setClP_ApellidoP(ap);
        voPersona.setClP_ApellidoM(am);
        voPersona.setClP_Correo(correo);
        voPersona.setClP_Password(contra);
        voPersona.setClP_Telefono(telefono);
        int res = 0;
        if ("".equals(nombre)||"".equals(ap) || "".equals(am)||"".equals(correo) || "".equals(contra)||"".equals(telefono)) {
            JOptionPane.showMessageDialog(null, "Por favor de llenar los campos vacios");
        } else {
            try {
                res = daoPersona.Insertar_Cliente_Persona(voPersona);
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
    public int Actualizar_Persona() {
        String id_Soc = frm.lbl_id.getText();
        String nombre = frm.txtNombre.getText();
        String ap = frm.txtApellidoPaterno.getText();
        String am = frm.txtApellidoMaterno.getText();
        String correo = frm.txtcorreo.getText();
        String contra = frm.txtpassword.getText();
        String telefono = frm.txttelefono.getText();
        int res = 0;
        int row = this.frm.tblClientePersona.getSelectedRow();
        voPersona.setClP_Id(Integer.valueOf(id_Soc));
        voPersona.setClP_Nombre(nombre);
        voPersona.setClP_ApellidoP(ap);
        voPersona.setClP_ApellidoM(am);
        voPersona.setClP_Correo(correo);
        voPersona.setClP_Password(contra);
        voPersona.setClP_Telefono(telefono);
        if ("".equals(id_Soc) ||"".equals(nombre)||"".equals(ap) || "".equals(am)||"".equals(correo) || "".equals(contra)||"".equals(telefono)) {
                JOptionPane.showMessageDialog(null, "Por favor de llenar los campos vacios");
            } else {
                try {
                    res = daoPersona.Actualizar_Cliente_Persona(voPersona);
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
    public void Eliminar_Persona() {
        int row = frm.tblClientePersona.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna por favor");
        } else {
            int dato = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar ?");
            if (dato == 0) {
                DefaultTableModel mode = (DefaultTableModel) frm.tblClientePersona.getModel();
                int clave = Integer.valueOf(mode.getValueAt(row, 0).toString());
                int r;
                voPersona.setClP_Id(clave);
                r = daoPersona.Eliminar_Cliente_Persona(voPersona);

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
        frm.txtApellidoPaterno.setText("");
        frm.txtApellidoMaterno.setText("");
        frm.txtcorreo.setText("");
        frm.txtpassword.setText("");
        frm.txttelefono.setText("");
        frm.lbl_id.setText("ID");
        frm.lbl_accion.setText("Nuevo cliente persona");
        TipoAccion=true;
    }

}
