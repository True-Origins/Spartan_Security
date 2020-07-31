package Controlador;

import Modelo.DAO.M_DAO_Puesto;
import Modelo.VO.M_VO_Puesto;
import Vista.V_Puesto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class C_Puesto implements ActionListener{
    private boolean TipoAccion=true;
    private V_Puesto frm;
    private M_VO_Puesto voPuesto;
    private M_DAO_Puesto daoPuesto;
    public C_Puesto(M_DAO_Puesto daoPuesto,V_Puesto frm,M_VO_Puesto voPuesto){
        this.daoPuesto=daoPuesto;
        this.frm = frm;
        this.voPuesto=voPuesto;
        llenaGrid();
        this.frm.btn_guardar.addActionListener(this);
        this.frm.btnactualizar.addActionListener(this);
        this.frm.btneliminar.addActionListener(this);
        this.frm.btncancelar.addActionListener(this);
        this.frm.txtPuesto.addActionListener(this);
        this.frm.txtSueldo.addActionListener(this);
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
            DefaultTableModel modelo = (DefaultTableModel) this.frm.tblpuesto.getModel();
            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            ResultSet rs = daoPuesto.Consulta_Puesto();
            while (rs.next()) {
                String id = String.valueOf(rs.getInt("psto_Id"));
                String puesto = rs.getString("psto_Nombre");
                String sueldo = rs.getString("psto_Sueldo");
                modelo.addRow(new Object[]{id, puesto,sueldo});               
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void Pasar_Datos_Actualizar(){
        int row = frm.tblpuesto.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna");
        } else {
            String ID = frm.tblpuesto.getValueAt(frm.tblpuesto.getSelectedRow(), 0).toString();
            String puesto = frm.tblpuesto.getValueAt(frm.tblpuesto.getSelectedRow(), 1).toString();
            String sueldo = frm.tblpuesto.getValueAt(frm.tblpuesto.getSelectedRow(), 2).toString();
            frm.txtPuesto.setText(puesto);
            frm.txtSueldo.setText(sueldo);
            frm.lbl_id.setText(ID);
            frm.lbl_accion.setText("Actualizar puesto");
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
        String puesto = frm.txtPuesto.getText();
        float saldo =  Integer.valueOf(frm.txtSueldo.getText());
        voPuesto.setPsto_Nombre(puesto);
        voPuesto.setPsto_Sueldo(saldo);
        int res = 0;
        if ("".equals(puesto)||"".equals(saldo)) {
            JOptionPane.showMessageDialog(null, "Por favor de llenar los campos vacios");
        } else {
            try {
                res = daoPuesto.Insertar_Puesto(voPuesto);
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
        String id = frm.lbl_id.getText();
        String puesto = frm.txtPuesto.getText();
        String sueldo = frm.txtSueldo.getText();
        int res = 0;
        int row = this.frm.tblpuesto.getSelectedRow();
        voPuesto.setPsto_Id(Integer.valueOf(id));
        voPuesto.setPsto_Nombre(puesto);
        voPuesto.setPsto_Sueldo(Integer.valueOf(sueldo));
        if ("".equals(id) || "".equals(puesto)|| "".equals(sueldo)) {
                JOptionPane.showMessageDialog(null, "Por favor de llenar los campos vacios");
            } else {
                try {
                    res = daoPuesto.Actualizar_Puesto(voPuesto);
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
        int row = frm.tblpuesto.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna por favor");
        } else {
            int dato = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar ?");
            if (dato == 0) {
                DefaultTableModel mode = (DefaultTableModel) frm.tblpuesto.getModel();
                int clave = Integer.valueOf(mode.getValueAt(row, 0).toString());
                int r;
                voPuesto.setPsto_Id(clave);
                r = daoPuesto.Eliminar_Puesto(voPuesto);

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
        frm.txtPuesto.setText("");
        frm.txtSueldo.setText("");
        frm.lbl_id.setText("ID");
        frm.lbl_accion.setText("Nuevo puesto");
        TipoAccion=true;
    }
}
