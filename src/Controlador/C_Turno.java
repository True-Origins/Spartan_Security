package Controlador;
import Modelo.DAO.M_DAO_Turno;
import Modelo.VO.M_VO_Turno;
import Vista.V_Turno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class C_Turno implements ActionListener{
    private boolean TipoAccion=true;
    private V_Turno frm;
    private M_VO_Turno voTurno;
    private M_DAO_Turno daoTurno;
    public C_Turno(M_DAO_Turno daoTurno,V_Turno frm,M_VO_Turno voTurno){
        this.daoTurno=daoTurno;
        this.frm = frm;
        this.voTurno=voTurno;
        llenaGrid();
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnactualizar.addActionListener(this);
        this.frm.btneliminar.addActionListener(this);
        this.frm.btncancelar.addActionListener(this);
        this.frm.txtTurno.addActionListener(this);
        this.frm.txtEntrada.addActionListener(this);
        this.frm.txtSalida.addActionListener(this);
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
            Eliminar_Socursal();
        }
        if (e.getSource() == frm.btncancelar) {
            LimpiarCajas();
        }
    }
    public void llenaGrid(){
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.frm.tblTurno.getModel();
            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            ResultSet rs = daoTurno.Consulta_Turno();
            while (rs.next()) {
                String id = String.valueOf(rs.getInt("tur_Id"));
                String turno = rs.getString("tur_Nombre");
                String entrada = rs.getString("tur_HoraEntrada");
                String salida = rs.getString("tur_HoraSalida");
                modelo.addRow(new Object[]{id, turno,entrada,salida});               
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void Pasar_Datos_Actualizar(){
        int row = frm.tblTurno.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una columna por favor");
        } else {
            String ID = frm.tblTurno.getValueAt(frm.tblTurno.getSelectedRow(), 0).toString();
            String turno = frm.tblTurno.getValueAt(frm.tblTurno.getSelectedRow(), 1).toString();
            String entrada = frm.tblTurno.getValueAt(frm.tblTurno.getSelectedRow(), 2).toString();
            String salida = frm.tblTurno.getValueAt(frm.tblTurno.getSelectedRow(), 3).toString();
            frm.txtTurno.setText(turno);
            frm.txtEntrada.setText(entrada);
            frm.txtSalida.setText(salida);
            frm.lbl_id.setText(ID);
            frm.lbl_accion.setText("Actualizar turno");
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
        String turno = frm.txtTurno.getText();
        String entrada = frm.txtEntrada.getText();
        String salida = frm.txtSalida.getText();
        voTurno.setTur_Nombre(turno);
        voTurno.setTur_HoraEntrada(entrada);
        voTurno.setTur_HoraSalida(salida);
        int res = 0;
        if ("".equals(turno)||"".equals(entrada) ||"".equals(salida)) {
            JOptionPane.showMessageDialog(null, "Por favor de llenar los campos vacios");
        } else {
            try {
                res = daoTurno.Insertar_Turno(voTurno);
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
        String turno = frm.txtTurno.getText();
        String entrada = frm.txtEntrada.getText();
        String salida = frm.txtSalida.getText();
        int res = 0;
        int row = this.frm.tblTurno.getSelectedRow();
        voTurno.setTur_Id(Integer.valueOf(id));
        voTurno.setTur_Nombre(turno);
        voTurno.setTur_HoraEntrada(entrada);
        voTurno.setTur_HoraSalida(salida);
        if ("".equals(id) || "".equals(turno)|| "".equals(entrada) || "".equals(salida)) {
                JOptionPane.showMessageDialog(null, "Por favor de llenar los campos vacios");
            } else {
                try {
                    res = daoTurno.Actualizar_Turno(voTurno);
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
        int row = frm.tblTurno.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna por favor");
        } else {
            int dato = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar ?");
            if (dato == 0) {
                DefaultTableModel mode = (DefaultTableModel) frm.tblTurno.getModel();
                int clave = Integer.valueOf(mode.getValueAt(row, 0).toString());
                int r;
                voTurno.setTur_Id(clave);
                r = daoTurno.Eliminar_Turno(voTurno);

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
        frm.txtTurno.setText("");
        frm.txtEntrada.setText("");
        frm.txtSalida.setText("");
        frm.lbl_id.setText("ID");
        frm.lbl_accion.setText("Nuevo turno");
        TipoAccion=true;
    }
    
}
