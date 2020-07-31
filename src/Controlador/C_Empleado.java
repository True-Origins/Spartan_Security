package Controlador;

import Modelo.DAO.M_DAO_Empleado;
import Modelo.VO.M_VO_Empleado;
import Vista.V_Empleado;
import Vista.V_ComboBox;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



public class C_Empleado implements ActionListener{
    private M_VO_Empleado voempleado;
    private M_DAO_Empleado daoempleado;
    private V_Empleado frm;
    private V_ComboBox tabla;
    private boolean TipoAccion=true;

    public C_Empleado(M_DAO_Empleado daoempleado, V_Empleado frm,M_VO_Empleado voempleado,V_ComboBox tabla) {
        this.daoempleado = daoempleado;
        this.frm = frm;
        this.voempleado = voempleado;
        this.tabla = tabla;
        llenaGrid();
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnactualizar.addActionListener(this);
        this.frm.btneliminar.addActionListener(this);
        this.frm.btnPuesto.addActionListener(this);
        this.frm.btnTurno.addActionListener(this);
        this.frm.btnSucursal.addActionListener(this);
        this.frm.btnCancelar.addActionListener(this);
        this.frm.txtNombre.addActionListener(this);
        this.frm.txtApellidoPaterno.addActionListener(this);
        this.frm.txtApellidoMaterno.addActionListener(this);
        this.frm.txttelefono.addActionListener(this);
        this.frm.txtdireccion.addActionListener(this);
        this.frm.txtcorreo.addActionListener(this);
        this.frm.txtpuesto.addActionListener(this);
        this.frm.txtturno.addActionListener(this);
        this.frm.txtsocursal.addActionListener(this);
        this.frm.txtpassword.addActionListener(this);
        this.frm.lbl_id.setVisible(false);
        this.frm.lblPuesto.setVisible(false);
        this.frm.lblTurno.setVisible(false);
        this.frm.lblSucursal.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm.btnGuardar) {
            Guardar_datos();
        }
        if (e.getSource() == frm.btneliminar) {
            Eliminar_Empleado();
        }
        if (e.getSource() == frm.btnPuesto) {
            Abreformulario_Tabla("Puesto");
        }
        if (e.getSource() == frm.btnTurno) {
            Abreformulario_Tabla("Turno");
        }
        if (e.getSource() == frm.btnSucursal) {
            Abreformulario_Tabla("Sucursal");
        }
        if (e.getSource() == frm.btnactualizar) {
            Pasar_Datos();
        }
        if (e.getSource() == frm.btnCancelar) {
            LimpiarCajas();
        }

    }

    public void llenaGrid() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.frm.tbl_empleado.getModel();
            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            ResultSet rs = daoempleado.Consulta_Empleado("Empleado");
            while (rs.next()) {
                String ID_Area = String.valueOf(rs.getInt("emp_Id"));
                String Nombre = rs.getString("emp_Nombre");
                String ApellidoP = rs.getString("emp_ApellidoP");
                String Apellidom = rs.getString("emp_ApellidoM");
                String Telefono = rs.getString("emp_Telefono");
                String Direccion = rs.getString("emp_Direccion");
                String Correo = rs.getString("emp_Correo");
                String Puesto = rs.getString("psto_Nombre");
                String turno = rs.getString("tur_Nombre");
                String soursal = rs.getString("sucur_Nombre");
                String pass = rs.getString("emp_Contrasena");
                modelo.addRow(new Object[]{ID_Area, Nombre,ApellidoP,Apellidom,Telefono,Direccion,Correo,Puesto,turno,soursal,pass});
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int Insertar_Empleado() {
        String nombre = frm.txtNombre.getText();
        String apellidopa = frm.txtApellidoPaterno.getText();
        String apellidoma = frm.txtApellidoMaterno.getText();
        String telefono = frm.txttelefono.getText();
        String direccion = frm.txtdireccion.getText();
        String correo = frm.txtcorreo.getText();
        int puesto = Integer.valueOf(frm.lblPuesto.getText());
        int turno = Integer.valueOf(frm.lblTurno.getText());
        int socursal = Integer.valueOf(frm.lblSucursal.getText());
        String password = frm.txtpassword.getText();
        JOptionPane.showMessageDialog(null, " vacios");
        voempleado.setEmp_Nombre(nombre);
        voempleado.setEmp_ApellidoP(apellidopa);
        voempleado.setEmp_ApellidoM(apellidoma);
        voempleado.setEmp_Telefono(telefono);
        voempleado.setEmp_Direccion(direccion);
        voempleado.setEmp_Correo(correo);
        voempleado.setEmp_FK_Puesto(puesto);
        voempleado.setEmp_FK_Turno(turno);
        voempleado.setEmp_FK_idSucursal(socursal);
        voempleado.setEmp_Contrasena(password);
        int res = 0;
         
        if ("".equals(nombre)||"".equals(apellidopa)||"".equals(apellidoma)||"".equals(telefono)||"".equals(direccion)||"".equals(correo)||"".equals(password)) {
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {

            try {

                res = daoempleado.Insertar_Empleado(voempleado);

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
            Insertar_Empleado();
        }else if(TipoAccion==false){
            Actualizar_Empleado();
        }    
    }
    public void Pasar_Datos(){
        int row = frm.tbl_empleado.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            Sacar_Ides(Integer.valueOf(frm.tbl_empleado.getValueAt(frm.tbl_empleado.getSelectedRow(), 0).toString()));           
            String nombre = frm.tbl_empleado.getValueAt(frm.tbl_empleado.getSelectedRow(), 1).toString();
            String ap = frm.tbl_empleado.getValueAt(frm.tbl_empleado.getSelectedRow(), 2).toString();
            String am = frm.tbl_empleado.getValueAt(frm.tbl_empleado.getSelectedRow(), 3).toString();
            String telefono = frm.tbl_empleado.getValueAt(frm.tbl_empleado.getSelectedRow(), 4).toString();
            String direccion = frm.tbl_empleado.getValueAt(frm.tbl_empleado.getSelectedRow(), 5).toString();
            String correo = frm.tbl_empleado.getValueAt(frm.tbl_empleado.getSelectedRow(), 6).toString();
            String puesto = frm.tbl_empleado.getValueAt(frm.tbl_empleado.getSelectedRow(), 7).toString();
            String turno = frm.tbl_empleado.getValueAt(frm.tbl_empleado.getSelectedRow(), 8).toString();
            String sucursal = frm.tbl_empleado.getValueAt(frm.tbl_empleado.getSelectedRow(), 9).toString();
            String contra = frm.tbl_empleado.getValueAt(frm.tbl_empleado.getSelectedRow(), 10).toString();
            
            frm.txtNombre.setText(nombre);
            frm.txtApellidoPaterno.setText(ap);
            frm.txtApellidoMaterno.setText(am);
            frm.txttelefono.setText(telefono);
            frm.txtdireccion.setText(direccion);
            frm.txtcorreo.setText(correo);
            frm.txtpuesto.setText(puesto);
            frm.txtturno.setText(turno);
            frm.txtsocursal.setText(sucursal);
            frm.txtpassword.setText(contra);

            frm.lblAccion.setText("Actualizar empleado");
            TipoAccion=false;
            
        }
    }
    public void Sacar_Ides(int dato) {
        try {
            System.out.println("eL DATO ES " + dato);
            ResultSet rs = daoempleado.Consulta_IDES(dato);

            while (rs.next()) {
                String idEmpleado = String.valueOf(rs.getInt("emp_Id"));
                String idPuesto = rs.getString("emp_FK_Puesto");
                String idTurno = rs.getString("emp_FK_Turno");
                String idSucursal = rs.getString("emp_FK_idSucursal");
                frm.lbl_id.setText(idEmpleado);
                frm.lblPuesto.setText(idPuesto);
                frm.lblTurno.setText(idTurno);
                frm.lblSucursal.setText(idSucursal);
            }
        } catch (Exception e) {

            System.out.println(e);
        }
    }
    public int Actualizar_Empleado() {
        int id = Integer.valueOf(frm.lbl_id.getText());
        String nombre = frm.txtNombre.getText();
        String apellidopa = frm.txtApellidoPaterno.getText();
        String apellidoma = frm.txtApellidoMaterno.getText();
        String telefono = frm.txttelefono.getText();
        String direccion = frm.txtdireccion.getText();
        String correo = frm.txtcorreo.getText();
        int puesto = Integer.valueOf(frm.lblPuesto.getText());
        int turno = Integer.valueOf(frm.lblTurno.getText());
        int socursal = Integer.valueOf(frm.lblSucursal.getText());
        String password = frm.txtpassword.getText();
               
        int res = 0;
        int row = this.frm.tbl_empleado.getSelectedRow();
        voempleado.setEmp_Id(id);
        voempleado.setEmp_Nombre(nombre);
        voempleado.setEmp_ApellidoP(apellidopa);
        voempleado.setEmp_ApellidoM(apellidoma);
        voempleado.setEmp_Telefono(telefono);
        voempleado.setEmp_Direccion(direccion);
        voempleado.setEmp_Correo(correo);
        voempleado.setEmp_FK_Puesto(puesto);
        voempleado.setEmp_FK_Turno(turno);
        voempleado.setEmp_FK_idSucursal(socursal);
        voempleado.setEmp_Contrasena(password);

        if ("".equals(nombre)||"".equals(apellidopa)||"".equals(apellidoma)||"".equals(telefono)||"".equals(direccion)||"".equals(correo)||"".equals(password)) {
                JOptionPane.showMessageDialog(null, "Campos vacios");
            } else {

                try {

                    res = daoempleado.Actualizar_Empleado(voempleado);

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
    public void Eliminar_Empleado() {
        int row = frm.tbl_empleado.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            int dato = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar ?");
            if (dato == 0) {
                DefaultTableModel mode = (DefaultTableModel) frm.tbl_empleado.getModel();
                int clave = Integer.valueOf(mode.getValueAt(row, 0).toString());
                int r;
                voempleado.setEmp_Id(clave);
                r = daoempleado.Eliminar_Empleado(voempleado);
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
        frm.txtNombre.setText("");
        frm.txtApellidoPaterno.setText("");
        frm.txtApellidoMaterno.setText("");
        frm.txttelefono.setText("");
        frm.txtdireccion.setText("");
        frm.txtcorreo.setText("");
        frm.txtpuesto.setText("");
        frm.txtturno.setText("");
        frm.txtsocursal.setText("");
        frm.txtpassword.setText("");
        frm.lbl_id.setText("ID");
        frm.lblPuesto.setText("idPu");
        frm.lblTurno.setText("idTu");
        frm.lblSucursal.setText("idSu");
        frm.lblAccion.setText("Nuevo empleado");
        TipoAccion=true;
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

                    if ("Puesto".equals(tabla.veri.getText())) {
                        String ID_PERSONA = tabla.tblComboBox.getValueAt(tabla.tblComboBox.getSelectedRow(), 0).toString();
                        String PERSONA = tabla.tblComboBox.getValueAt(tabla.tblComboBox.getSelectedRow(), 1).toString();
                        frm.txtpuesto.setText(PERSONA);
                        frm.lblPuesto.setText(ID_PERSONA);

                    }

                    if (tabla.veri.getText().equals("Turno")) {
                        String ID_Area = tabla.tblComboBox.getValueAt(tabla.tblComboBox.getSelectedRow(), 0).toString();
                        String AREA = tabla.tblComboBox.getValueAt(tabla.tblComboBox.getSelectedRow(), 1).toString();
                        frm.txtturno.setText(AREA);
                        frm.lblTurno.setText(ID_Area);
                    }

                    if (tabla.veri.getText().equals("Sucursal")) {
                        String ID_Puesto = tabla.tblComboBox.getValueAt(tabla.tblComboBox.getSelectedRow(), 0).toString();
                        String Puesto = tabla.tblComboBox.getValueAt(tabla.tblComboBox.getSelectedRow(), 1).toString();
                        frm.txtsocursal.setText(Puesto);
                        frm.lblSucursal.setText(ID_Puesto);
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
            ResultSet rs = daoempleado.Consulta_Empleado(valor);
            if (valor.equals("Puesto")) {
                while (rs.next()) {
                    String A1 = String.valueOf(rs.getInt("psto_Id"));
                    String A2 = rs.getString("psto_Nombre");
                    modelo.addRow(new Object[]{A1, A2});
                }
            }
            if (valor.equals("Turno")) {
                while (rs.next()) {
                    String A1 = String.valueOf(rs.getInt("tur_Id"));
                    String A2 = rs.getString("tur_Nombre");
                    modelo.addRow(new Object[]{A1, A2 });
                }
            }
            if (valor.equals("Sucursal")) {
                while (rs.next()) {
                    String A1 = String.valueOf(rs.getInt("sucur_Id"));
                    String A2 = rs.getString("sucur_Nombre");
                    modelo.addRow(new Object[]{A1, A2});
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}