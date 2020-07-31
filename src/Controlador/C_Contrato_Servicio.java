package Controlador;
import Modelo.DAO.M_DAO_Contrato_Servicios;
import Modelo.VO.M_VO_Contrato_Servicio;
import Vista.V_ComboBox;
import Vista.V_Contrato_Servicio;
import com.toedter.calendar.JDateChooser;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class C_Contrato_Servicio implements ActionListener{
    private M_VO_Contrato_Servicio voContratoServicio;
    private M_DAO_Contrato_Servicios daoContratoServicio;
    private V_Contrato_Servicio frm;
    private V_ComboBox tabla;
    private boolean TipoAccion=true;
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    public C_Contrato_Servicio(M_DAO_Contrato_Servicios daoContratoServicio,V_Contrato_Servicio frm,M_VO_Contrato_Servicio voContratoServicio,V_ComboBox tabla){
        this.daoContratoServicio=daoContratoServicio;
        this.frm=frm;
        this.voContratoServicio=voContratoServicio;
        this.tabla=tabla;
        llenaGrid();
        this.frm.btnguardar.addActionListener(this);
        this.frm.btnactualizar.addActionListener(this);
        this.frm.btneliminar.addActionListener(this);
        this.frm.btnClienteEmpresa.addActionListener(this);
        this.frm.btnMedioPago.addActionListener(this);
        this.frm.btnServicio.addActionListener(this);
        this.frm.btncancelar.addActionListener(this);
        this.frm.txtMedioPago.addActionListener(this);
        this.frm.txtServicio.addActionListener(this);
        this.frm.txtClienteEmpresa.addActionListener(this);
        this.frm.lblContratoServicio.setVisible(false);
        this.frm.lblClientEmpre.setVisible(false);
        this.frm.lblServicio.setVisible(false);
        this.frm.lblMedPago.setVisible(false);
    }
        @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm.btnguardar) {
            Guardar_datos();
        }
        if (e.getSource() == frm.btnServicio) {
            Abreformulario_Tabla("Servicios");
        }
        if (e.getSource() == frm.btnClienteEmpresa) {
            Abreformulario_Tabla("Cliente_Empresa");
        }
        if (e.getSource() == frm.btnMedioPago) {
            Abreformulario_Tabla("Medio_Pago");
        }
        if (e.getSource() == frm.btncancelar) {
            LimpiarCajas();
        }          
        if (e.getSource() == frm.btnactualizar) {
            Pasar_Datos();
        }
        if (e.getSource() == frm.btneliminar) {
            Eliminar_Contrato_Servicio();
        }
    }
    public void llenaGrid() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.frm.tblContratoServicios.getModel();
            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            ResultSet rs = daoContratoServicio.Consulta_Contaro_Servicios("Contrato_Servicios");
            while (rs.next()) {
                String Id = String.valueOf(rs.getInt("contS_Id"));
                String fecha = rs.getString("contS_FechaContrato");
                String servicio = rs.getString("serv_Nombre");
                String ClienteEmpresa = rs.getString("clE_NombreEmpresa");
                String MedioPago = rs.getString("medP_Nombre");

                modelo.addRow(new Object[]{Id, fecha,servicio, ClienteEmpresa,MedioPago});
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void Pasar_Datos(){
        int row = frm.tblContratoServicios.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            Sacar_Ides(Integer.valueOf(frm.tblContratoServicios.getValueAt(frm.tblContratoServicios.getSelectedRow(), 0).toString()));           
            Date FechaContrato = Date.valueOf(frm.tblContratoServicios.getValueAt(frm.tblContratoServicios.getSelectedRow(), 1).toString());
            String IdServicios = frm.tblContratoServicios.getValueAt(frm.tblContratoServicios.getSelectedRow(), 2).toString();
            String IdClienteEmpresa = frm.tblContratoServicios.getValueAt(frm.tblContratoServicios.getSelectedRow(), 3).toString();
            String IdMedioPago = frm.tblContratoServicios.getValueAt(frm.tblContratoServicios.getSelectedRow(), 4).toString();

            frm.txtFecha.setDate(FechaContrato);
            frm.txtServicio.setText(IdServicios);
            frm.txtClienteEmpresa.setText(IdClienteEmpresa);
            frm.txtMedioPago.setText(IdMedioPago);

            frm.lbl_accion.setText("Actualizar contrato servicio");
            TipoAccion=false;
            
        }
    }
    public void Sacar_Ides(int dato) {
        try {
            ResultSet rs = daoContratoServicio.Consulta_IDES(dato);

            while (rs.next()) {
                String idCS = String.valueOf(rs.getInt("contS_Id"));
                String FKidServicio = rs.getString("serv_Id");
                String FKidClienteEmpresa = rs.getString("clE_Id");
                String FKidMedioPago = rs.getString("medP_id");
                frm.lblContratoServicio.setText(idCS);
                frm.lblServicio.setText(FKidServicio);
                frm.lblClientEmpre.setText(FKidClienteEmpresa);
                frm.lblMedPago.setText(FKidMedioPago);
            }
        } catch (Exception e) {

            System.out.println(e);
        }
    }
    public void Guardar_datos(){
        if (TipoAccion==true) {
            Insertar_Contrato_Servicio();
        }else if(TipoAccion==false){
            Actualizar_Contrato_Servicio();
        }    
    }
    
    public void LimpiarCajas() {
        frm.txtFecha.setDate(null);
        frm.txtServicio.setText("");
        frm.txtClienteEmpresa.setText("");
        frm.txtMedioPago.setText("");
        frm.lblContratoServicio.setText("idContratoServicio");
        frm.lblServicio.setText("IdServicio");
        frm.lblClientEmpre.setText("IdClienteEmpre");
        frm.lblMedPago.setText("IdMedioPago");
        frm.lbl_accion.setText("Nuevo contrato servicio");
        TipoAccion=true;
    }
    
    public int Insertar_Contrato_Servicio() {
        String fecha =getfecha( this.frm.txtFecha);
        int idServicio = Integer.valueOf(frm.lblServicio.getText());
        int idClienteEmpresa = Integer.valueOf(frm.lblClientEmpre.getText());
        int idMedioDePago = Integer.valueOf(frm.lblMedPago.getText());
        String ser=frm.txtServicio.getText();
        String cliem=frm.txtClienteEmpresa.getText();
        String Medio=frm.txtMedioPago.getText();
        voContratoServicio.setContS_FechaContrato(fecha);
        voContratoServicio.setContS_FK_idServicio(idServicio);
        voContratoServicio.setContS_FK_idClienteEmpresa(idClienteEmpresa);
        voContratoServicio.setOntS_FK_idMedioPago(idMedioDePago);
        int res = 0;
        if ("".equals(ser)||"".equals(cliem)||"".equals(Medio)) {
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {
            try {
                res = daoContratoServicio.Insertar_Servivios(voContratoServicio);
                if (res != 0) {
                    JOptionPane.showMessageDialog(null, "Datos Correctamente Insertados");
                    llenaGrid();
                    LimpiarCajas();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Insertar Datos");
                }
            } catch (Exception e) {
                System.out.print("Error: " + e);
            }
        }
        return res;
    }
    public int Actualizar_Contrato_Servicio() {
        int idContratoServicio=Integer.valueOf(frm.lblContratoServicio.getText());
        String fecha =getfecha(this.frm.txtFecha); 
        int idServicio = Integer.valueOf(frm.lblServicio.getText());
        int idClienteEmpresa = Integer.valueOf(frm.lblClientEmpre.getText());
        int idMedioDePago = Integer.valueOf(frm.lblMedPago.getText());
        String ser=frm.txtServicio.getText();
        String cliem=frm.txtClienteEmpresa.getText();
        String Medio=frm.txtMedioPago.getText();    
        int res = 0;
        int row = this.frm.tblContratoServicios.getSelectedRow();
        voContratoServicio.setContS_Id(idContratoServicio);
        voContratoServicio.setContS_FechaContrato(fecha);
        voContratoServicio.setContS_FK_idServicio(idServicio);
        voContratoServicio.setContS_FK_idClienteEmpresa(idClienteEmpresa);
        voContratoServicio.setOntS_FK_idMedioPago(idMedioDePago);
        
        if ("".equals(ser)||"".equals(cliem)||"".equals(Medio)) {
                JOptionPane.showMessageDialog(null, "Campos vacios");
            } else {
                try {
                    res = daoContratoServicio.Actualizar_Servicios(voContratoServicio);
                    if (res != 0) {
                        JOptionPane.showMessageDialog(null, "Datos Correctamente Actualizados");
                        llenaGrid();
                        LimpiarCajas();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al Actualizar Datos");
                    }
                } catch (Exception e) {
                    System.out.print(e);
                }
            }        
        return res;
    }
    public void Abreformulario_Tabla(String tipo) {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(tabla);
        tabla = new V_ComboBox(f, true);

        tabla.setTitle("Tabla " + tipo);
        tabla.veri.setText(tipo);
        llenaTABLAS(tipo);
        this.tabla.tblComboBox.addMouseListener(
                new MouseAdapter() {
            public void mouseClicked(MouseEvent evnt) {

                if (evnt.getClickCount() == 2) {

                    if ("Servicios".equals(tabla.veri.getText())) {
                        String idServicio = tabla.tblComboBox.getValueAt(tabla.tblComboBox.getSelectedRow(), 0).toString();
                        String Servicio = tabla.tblComboBox.getValueAt(tabla.tblComboBox.getSelectedRow(), 1).toString();
                        frm.txtServicio.setText(Servicio);
                        frm.lblServicio.setText(idServicio);

                    }
                    if ("Cliente_Empresa".equals(tabla.veri.getText())) {
                        String idClienteEmpresa = tabla.tblComboBox.getValueAt(tabla.tblComboBox.getSelectedRow(), 0).toString();
                        String ClienteEmpresa = tabla.tblComboBox.getValueAt(tabla.tblComboBox.getSelectedRow(), 1).toString();
                        frm.txtClienteEmpresa.setText(ClienteEmpresa);
                        frm.lblClientEmpre.setText(idClienteEmpresa);

                    }
                    if ("Medio_Pago".equals(tabla.veri.getText())) {
                        String idMedioPago = tabla.tblComboBox.getValueAt(tabla.tblComboBox.getSelectedRow(), 0).toString();
                        String MedioPago = tabla.tblComboBox.getValueAt(tabla.tblComboBox.getSelectedRow(), 1).toString();
                        frm.txtMedioPago.setText(MedioPago);
                        frm.lblMedPago.setText(idMedioPago);

                    }                    
                    tabla.dispose();
                }
            }
        }
        );
        llenaGrid();
        tabla.setVisible(true);
    }
    public void Eliminar_Contrato_Servicio() {
        int row = frm.tblContratoServicios.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            int dato = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar ?");
            if (dato == 0) {
                DefaultTableModel mode = (DefaultTableModel) frm.tblContratoServicios.getModel();
                int clave = Integer.valueOf(mode.getValueAt(row, 0).toString());
                int r;
                voContratoServicio.setContS_Id(clave);
                r = daoContratoServicio.Eliminar_Contrato_Servicios(voContratoServicio);
                if (r != 0) {
                    JOptionPane.showMessageDialog(null, "El Registro se eliminó correctamente");
                    llenaGrid();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Eliminar el Registro");
                }
            }
        }
    }
    public void llenaTABLAS(String valor) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.tabla.tblComboBox.getModel();
            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            ResultSet rs = daoContratoServicio.Consulta_Contaro_Servicios(valor);
            if (valor.equals("Servicios")) {
                while (rs.next()) {
                    String A1 = String.valueOf(rs.getInt("serv_Id"));
                    String A2 = rs.getString("serv_Nombre");
                    modelo.addRow(new Object[]{A1, A2});
                }
            }
            if (valor.equals("Cliente_Empresa")) {
                while (rs.next()) {
                    String A1 = String.valueOf(rs.getInt("clE_Id"));
                    String A2 = rs.getString("clE_NombreEmpresa");
                    modelo.addRow(new Object[]{A1, A2});
                }
            }
            if (valor.equals("Medio_Pago")) {
                while (rs.next()) {
                    String A1 = String.valueOf(rs.getInt("medP_id"));
                    String A2 = rs.getString("medP_Nombre");
                    modelo.addRow(new Object[]{A1, A2 });
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String getfecha(JDateChooser f) {
        if (f.getDate() != null) {
            return formato.format(f.getDate());
        } else {
            return null;
        }
    }
}
