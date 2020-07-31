package Controlador;
import Modelo.DAO.M_DAO_Socursal;
import Modelo.VO.M_VO_Socursal;
import Vista.V_Sucursal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class C_Socursal implements ActionListener{
    private boolean TipoAccion=true;
    private V_Sucursal frm;
    private M_VO_Socursal voSocursal;
    private M_DAO_Socursal daoSocursal;
    public C_Socursal(M_DAO_Socursal daoSocursal,V_Sucursal frm,M_VO_Socursal voSocursal){
        this.daoSocursal=daoSocursal;
        this.frm = frm;
        this.voSocursal=voSocursal;
        llenaGrid();
        this.frm.btn_guardar.addActionListener(this);
        this.frm.btnactualizar.addActionListener(this);
        this.frm.btneliminar.addActionListener(this);
        this.frm.btncancelar.addActionListener(this);
        this.frm.txtNombreSocursal.addActionListener(this);
        this.frm.txtdireccion.addActionListener(this);
        this.frm.lbl_id.setVisible(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm.btn_guardar) {
            Guardar_datos();
        }
        if (e.getSource() == frm.btnactualizar) {
            Pasar_Datos_Actualizar();
        }
        if (e.getSource() == frm.btneliminar) {
            Eliminar_Socursal();
        }
        if (e.getSource() == frm.btncancelar) {
            LimpiarCajas();
        }
    }
    public void llenaGrid(){
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.frm.tbl_Socursal.getModel();
            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            ResultSet rs = daoSocursal.Consulta_Socursal();
            while (rs.next()) {
                String id_Socursal = String.valueOf(rs.getInt("sucur_Id"));
                String NombreSocursal = rs.getString("sucur_Nombre");
                String Direccion = rs.getString("sucur_Direccion");
                modelo.addRow(new Object[]{id_Socursal, NombreSocursal,Direccion});               
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void Pasar_Datos_Actualizar(){
        int row = frm.tbl_Socursal.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            String ID = frm.tbl_Socursal.getValueAt(frm.tbl_Socursal.getSelectedRow(), 0).toString();
            String NombreSocursal = frm.tbl_Socursal.getValueAt(frm.tbl_Socursal.getSelectedRow(), 1).toString();
            String Direccion = frm.tbl_Socursal.getValueAt(frm.tbl_Socursal.getSelectedRow(), 2).toString();
            
            frm.txtNombreSocursal.setText(NombreSocursal);
            frm.txtdireccion.setText(Direccion);
            frm.lbl_id.setText(ID);
            frm.lbl_accion.setText("Actualizar socursal");
            TipoAccion=false;
            
        }
    }
    public void Guardar_datos(){
        if (TipoAccion==true) {
            Insertar_Socursal();
        }else if(TipoAccion==false){
            Actualizar_Socursal();
        }    
    }
    public int Insertar_Socursal() {
        String NombreSocursal = frm.txtNombreSocursal.getText();
        String Direccion = frm.txtdireccion.getText();
        voSocursal.setSucur_Nombr(NombreSocursal);
        voSocursal.setSucur_Direccion(Direccion);
        int res = 0;
        if ("".equals(NombreSocursal)||"".equals(Direccion)) {
            JOptionPane.showMessageDialog(null, "Por favor de llenar los campos vacios");
        } else {
            try {
                res = daoSocursal.Insertar_Socursal(voSocursal);
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
    public int Actualizar_Socursal() {
        String id_Soc = frm.lbl_id.getText();
        String nombre_soc = frm.txtNombreSocursal.getText();
        String dir_soc = frm.txtdireccion.getText();
        int res = 0;
        int row = this.frm.tbl_Socursal.getSelectedRow();
        voSocursal.setSucur_Id(Integer.valueOf(id_Soc));
        voSocursal.setSucur_Nombr(nombre_soc);
        voSocursal.setSucur_Direccion(dir_soc);
        if ("".equals(id_Soc) || "".equals(nombre_soc)|| "".equals(dir_soc)) {
                JOptionPane.showMessageDialog(null, "Por favor de llenar los campos vacios");
            } else {
                try {
                    res = daoSocursal.Actualizar_Socursal(voSocursal);
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
    public void Eliminar_Socursal() {
        int row = frm.tbl_Socursal.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna por favor");
        } else {
            int dato = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar ?");
            if (dato == 0) {
                DefaultTableModel mode = (DefaultTableModel) frm.tbl_Socursal.getModel();
                int clave = Integer.valueOf(mode.getValueAt(row, 0).toString());
                int r;
                voSocursal.setSucur_Id(clave);
                r = daoSocursal.Eliminar_Socursal(voSocursal);

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
        frm.txtNombreSocursal.setText("");
        frm.txtdireccion.setText("");
        frm.lbl_id.setText("ID");
        frm.lbl_accion.setText("Nuevo socursal");
        TipoAccion=true;
    }
}
