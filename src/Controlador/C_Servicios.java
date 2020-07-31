package Controlador;
import Modelo.DAO.M_DAO_Servicios;
import Modelo.VO.M_VO_Servicios;
import Vista.V_ComboBox;
import Vista.V_Servicios;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class C_Servicios implements ActionListener{
    private M_VO_Servicios voServicios;
    private M_DAO_Servicios daoServicios;
    private V_Servicios frm;
    private V_ComboBox tabla;
    private boolean TipoAccion=true;
    public C_Servicios(M_DAO_Servicios daoServicios, V_Servicios frm,M_VO_Servicios voServicios,V_ComboBox tabla) {
        this.daoServicios = daoServicios;
        this.frm = frm;
        this.voServicios = voServicios;
        this.tabla = tabla;
        llenaGrid();
        this.frm.btn_guardar.addActionListener(this);
        this.frm.btnactualizar.addActionListener(this);
        this.frm.btneliminar.addActionListener(this);
        this.frm.btnTipoServicio.addActionListener(this);
        this.frm.btncancelar.addActionListener(this);
        
        this.frm.txtServicio.addActionListener(this);
        this.frm.txtDescripcion.addActionListener(this);
        this.frm.txtPrecio.addActionListener(this);
        this.frm.txtTipoServicio.addActionListener(this);
        this.frm.lblIDS2.setVisible(false);
        this.frm.lblIDTipoSer.setVisible(false); 
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm.btn_guardar) {
            Guardar_datos();
        }
        if (e.getSource() == frm.btneliminar) {
            Eliminar_Servicio();
        }
        if (e.getSource() == frm.btnTipoServicio) {
            Abreformulario_Tabla("Tipo_Servicios");
        }
        if (e.getSource() == frm.btnactualizar) {
            Pasar_Datos();
        }
        if (e.getSource() == frm.btncancelar) {
            LimpiarCajas();
        }

    }
    public void llenaGrid() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.frm.tblServicios.getModel();
            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            ResultSet rs = daoServicios.Consulta_Servicios("Servicios");
            while (rs.next()) {
                String id = String.valueOf(rs.getInt("serv_Id"));
                String Nombre = rs.getString("serv_Nombre");
                String descripcion = rs.getString("serv_Descripcion");
                String Precio = rs.getString("serv_Precio");
                String tiposer = rs.getString("tserv_Nombre");

                modelo.addRow(new Object[]{id, Nombre,descripcion, Precio,tiposer});
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public int Insertar_Servicio() {
        String nombre = frm.txtServicio.getText();
        String descripcion = frm.txtDescripcion.getText();
        float precio =Float.valueOf(frm.txtPrecio.getText()); 
        int tipoServicio = Integer.valueOf(frm.lblIDTipoSer.getText());
        String tiSe=frm.txtTipoServicio.getText();
        voServicios.setServ_Nombre(nombre);
        voServicios.setServ_Descripcion(descripcion);
        voServicios.setServ_Precio(precio);
        voServicios.setServ_FK_idTipoServ(tipoServicio);
        int res = 0;

        if ("".equals(nombre)||"".equals(descripcion)||"".equals(precio)||"".equals(precio)||"".equals(tiSe)) {
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {

            try {

                res = daoServicios.Insertar_Servivios(voServicios);

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
    
    public void Guardar_datos(){
        if (TipoAccion==true) {
            Insertar_Servicio();
        }else if(TipoAccion==false){
            Actualizar_Servicio();
        }    
    }
    
    public void LimpiarCajas() {
        frm.txtServicio.setText("");
        frm.txtDescripcion.setText("");
        frm.txtPrecio.setText("");
        frm.txtTipoServicio.setText("");
        frm.lblIDS2.setText("idser");
        frm.lblIDTipoSer.setText("idtiposerv");
        frm.lbl_accion.setText("Nuevo servicio");
        TipoAccion=true;
    }
    
    public int Actualizar_Servicio() {
        int id = Integer.valueOf(frm.lblIDS2.getText());
        String nombre = frm.txtServicio.getText();
        String descripcion = frm.txtDescripcion.getText();
        float precio =Integer.valueOf(frm.txtPrecio.getText()); 
        int tipoServicio = Integer.valueOf(frm.lblIDTipoSer.getText());
        String tiSe=frm.txtTipoServicio.getText();

               
        int res = 0;
        int row = this.frm.tblServicios.getSelectedRow();
        voServicios.setServ_Id(id);
        voServicios.setServ_Nombre(nombre);
        voServicios.setServ_Descripcion(descripcion);
        voServicios.setServ_Precio(precio);
        voServicios.setServ_FK_idTipoServ(tipoServicio);

        if ("".equals(nombre)||"".equals(descripcion)||"".equals(precio)||"".equals(precio)||"".equals(tiSe)) {
                JOptionPane.showMessageDialog(null, "Campos vacios");
            } else {

                try {

                    res = daoServicios.Actualizar_Servicios(voServicios);

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
    
    public void Eliminar_Servicio() {
        int row = frm.tblServicios.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna por favor");
        } else {
            int dato = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar ?");
            if (dato == 0) {
                DefaultTableModel mode = (DefaultTableModel) frm.tblServicios.getModel();
                int clave = Integer.valueOf(mode.getValueAt(row, 0).toString());
                int r;
                voServicios.setServ_Id(clave);
                r = daoServicios.Eliminar_Servicios(voServicios);

                if (r != 0) {

                    JOptionPane.showMessageDialog(null, "El se eliminó correctamente");
                    llenaGrid();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                }
            }

        }
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

                    if ("Tipo_Servicios".equals(tabla.veri.getText())) {
                        String id = tabla.tblComboBox.getValueAt(tabla.tblComboBox.getSelectedRow(), 0).toString();
                        String tiposerv = tabla.tblComboBox.getValueAt(tabla.tblComboBox.getSelectedRow(), 1).toString();
                        frm.lblIDTipoSer.setText(id);
                        frm.txtTipoServicio.setText(tiposerv);

                    }
                    tabla.dispose();
                }
            }
        }
        );
        llenaGrid();
        tabla.setVisible(true);
    }
    
    public void llenaTABLAS(String valor) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.tabla.tblComboBox.getModel();
            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            ResultSet rs = daoServicios.Consulta_Servicios(valor);
            if (valor.equals("Tipo_Servicios")) {
                while (rs.next()) {
                    String A1 = String.valueOf(rs.getInt("tserv_Id"));
                    String A2 = rs.getString("tserv_Nombre");
                    modelo.addRow(new Object[]{A1, A2});
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void Pasar_Datos(){
        int row = frm.tblServicios.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            Sacar_Ides(Integer.valueOf(frm.tblServicios.getValueAt(frm.tblServicios.getSelectedRow(), 0).toString()));           
            String nombre = frm.tblServicios.getValueAt(frm.tblServicios.getSelectedRow(), 1).toString();
            String descripcion = frm.tblServicios.getValueAt(frm.tblServicios.getSelectedRow(), 2).toString();
            String precio = frm.tblServicios.getValueAt(frm.tblServicios.getSelectedRow(), 3).toString();
            String tiposervicio = frm.tblServicios.getValueAt(frm.tblServicios.getSelectedRow(), 4).toString();
            
            frm.txtServicio.setText(nombre);
            frm.txtDescripcion.setText(descripcion);
            frm.txtPrecio.setText(precio);
            frm.txtTipoServicio.setText(tiposervicio);

            frm.lbl_accion.setText("Actualizar servicios");
            TipoAccion=false;
            
        }
    }
    
    public void Sacar_Ides(int dato) {
        try {
            System.out.println("eL DATO ES " + dato);
            ResultSet rs = daoServicios.Consulta_IDES(dato);

            while (rs.next()) {
                String idCurso = String.valueOf(rs.getInt("serv_Id"));
                String idCategoria = rs.getString("tserv_Id");
                frm.lblIDS2.setText(idCurso);
                frm.lblIDTipoSer.setText(idCategoria);
            }
        } catch (Exception e) {

            System.out.println(e);
        }
    }
}
