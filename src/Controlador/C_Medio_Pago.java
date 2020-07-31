package Controlador;
import Modelo.DAO.M_DAO_Medio_Pago;
import Modelo.VO.M_VO_Medio_pago;
import Vista.V_Medio_Pago;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class C_Medio_Pago implements ActionListener{
    private boolean TipoAccion=true;
    private V_Medio_Pago frm;
    private M_VO_Medio_pago voMedioPago;
    private M_DAO_Medio_Pago daoMedioPago; 
    public C_Medio_Pago(M_DAO_Medio_Pago daoMedioPago,V_Medio_Pago frm,M_VO_Medio_pago voMedioPago){
        this.daoMedioPago=daoMedioPago;
        this.frm = frm;
        this.voMedioPago=voMedioPago;
        llenaGrid();
        this.frm.btnguardar.addActionListener(this);
        this.frm.btnactualizar.addActionListener(this);
        this.frm.btneliminar.addActionListener(this);
        this.frm.btncancelar.addActionListener(this);
        this.frm.txtMediopago.addActionListener(this);
        this.frm.txtAplicaperiodo.addActionListener(this);
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
            Eliminar();
        }
        if (e.getSource() == frm.btncancelar) {
            LimpiarCajas();
        }
    }
    public void llenaGrid(){
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.frm.tbl_Medio_Pago.getModel();
            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            ResultSet rs = daoMedioPago.Consulta_Medio_Pago();
            while (rs.next()) {
                String id = String.valueOf(rs.getInt("medP_id"));
                String mediopago = rs.getString("medP_Nombre");
                String aplica = rs.getString("medP_AplicaPeriodo");
                modelo.addRow(new Object[]{id, mediopago,aplica});               
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void Pasar_Datos_Actualizar(){
        int row = frm.tbl_Medio_Pago.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            String ID = frm.tbl_Medio_Pago.getValueAt(frm.tbl_Medio_Pago.getSelectedRow(), 0).toString();
            String mediopago = frm.tbl_Medio_Pago.getValueAt(frm.tbl_Medio_Pago.getSelectedRow(), 1).toString();
            String aplica = frm.tbl_Medio_Pago.getValueAt(frm.tbl_Medio_Pago.getSelectedRow(), 2).toString();
            frm.txtMediopago.setText(mediopago);
            frm.txtAplicaperiodo.setText(aplica);
            frm.lbl_id.setText(ID);
            frm.lbl_accion.setText("Actualizar medio pago");
            TipoAccion=false;
            
        }
    }
    public void Guardar_datos(){
        if (TipoAccion==true) {
            Insertar();
        }else if(TipoAccion==false){
            Actualizar();
        }
    
    }
    public int Insertar() {
        String medio_pago = frm.txtMediopago.getText();
        
        int aplicar = Integer.valueOf( frm.txtAplicaperiodo.getText());
        JOptionPane.showMessageDialog(null, "Por favor de llenar los campos vacios");
        String VAplicar =frm.txtMediopago.getText();
        voMedioPago.setMedP_Nombre(medio_pago);
        voMedioPago.setMedP_AplicaPeriodo(aplicar);
        int res = 0;
        if ("".equals(medio_pago)||"".equals(VAplicar)) {
            JOptionPane.showMessageDialog(null, "Por favor de llenar los campos vacios");
        } else {
            try {
                res = daoMedioPago.Insertar_Medio_pago(voMedioPago);
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
    public int Actualizar() {
        String id = frm.lbl_id.getText();
        String medio = frm.txtMediopago.getText();
        int aplica = Integer.valueOf( frm.txtAplicaperiodo.getText());
        int res = 0;
        int row = this.frm.tbl_Medio_Pago.getSelectedRow();
        voMedioPago.setMedP_id(Integer.valueOf(id));
        voMedioPago.setMedP_Nombre(medio);
        voMedioPago.setMedP_AplicaPeriodo(aplica);
        if ("".equals(id) || "".equals(medio)|| "".equals(aplica)) {
                JOptionPane.showMessageDialog(null, "Por favor de llenar los campos vacios");
            } else {
                try {
                    res = daoMedioPago.Actualizar_Medio_pago(voMedioPago);
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
    public void Eliminar() {
        int row = frm.tbl_Medio_Pago.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna por favor");
        } else {
            int dato = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar ?");
            if (dato == 0) {
                DefaultTableModel mode = (DefaultTableModel) frm.tbl_Medio_Pago.getModel();
                int clave = Integer.valueOf(mode.getValueAt(row, 0).toString());
                int r;
                voMedioPago.setMedP_id(clave);
                r = daoMedioPago.Eliminar_Medio_pago(voMedioPago);

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
        frm.txtMediopago.setText("");
        frm.txtAplicaperiodo.setText("");
        frm.lbl_id.setText("ID");
        frm.lbl_accion.setText("Nuevo socursal");
        TipoAccion=true;
    }
}
