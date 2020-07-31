package Controlador;
import Modelo.DAO.M_DAO_Cursos_Publicados;
import Modelo.VO.M_VO_Cursos_Publicados;
import Vista.V_ComboBox;
import Vista.V_Cursos_Publicado;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import com.toedter.calendar.JDateChooser;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;

public class C_Cursos_Publicado implements ActionListener {
    private M_VO_Cursos_Publicados vocursosPublicados;
    private M_DAO_Cursos_Publicados daocursosPublicados;
    private V_Cursos_Publicado frm;
    private V_ComboBox tabla;
    private boolean TipoAccion=true;
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    public C_Cursos_Publicado(M_DAO_Cursos_Publicados daocursosPublicados, V_Cursos_Publicado frm,M_VO_Cursos_Publicados vocursosPublicados,V_ComboBox tabla) {
        this.daocursosPublicados = daocursosPublicados;
        this.frm = frm;
        this.vocursosPublicados = vocursosPublicados;
        this.tabla = tabla;
        llenaGrid();

        this.frm.btnguardar.addActionListener(this);
        this.frm.btnActualizar.addActionListener(this);
        this.frm.btneliminar.addActionListener(this);
        this.frm.btnVerCursos.addActionListener(this);
        this.frm.btnVerEmpleados1.addActionListener(this);
        this.frm.btncancelar.addActionListener(this);
        
        this.frm.txtCurso.addActionListener(this);
        this.frm.txtCupo.addActionListener(this);
        this.frm.txtLugar.addActionListener(this);
        this.frm.txtHora.addActionListener(this);
        this.frm.txtEmpleado.addActionListener(this);
        this.frm.lblId.setVisible(false);
        this.frm.lblIdCurso.setVisible(false);
        this.frm.lblIdEmpleado.setVisible(false);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm.btnguardar) {
            Guardar_datos();
        }
        if (e.getSource() == frm.btnVerCursos) {
            Abreformulario_Tabla("Cursos");
        }
        if (e.getSource() == frm.btnVerEmpleados1) {
            Abreformulario_Tabla("Empleados");
        }
        if (e.getSource() == frm.btnActualizar) {
            Pasar_Datos();
        }
        if (e.getSource() == frm.btncancelar) {
            LimpiarCajas();
        }
        if (e.getSource() == frm.btneliminar) {
            Eliminar_CursosPublicado();
        }
        
    }
    
    public void llenaGrid() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.frm.tblCursosPublicados.getModel();
            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            ResultSet rs = daocursosPublicados.Consulta_CursosPublicados("CursosPublicados");
            while (rs.next()) {
                String Id = String.valueOf(rs.getInt("curP_Id"));
                String Curso = rs.getString("cur_Nombre");
                String FechaInicio = rs.getString("curP_FechaInicio");
                String FechaFin = rs.getString("curP_FechaFin");
                String Cupo = rs.getString("curP_Cupo");
                String CupoDisponible = rs.getString("curP_CupoDisponible");
                String Lugar = rs.getString("curP_Lugar");
                String Hora = rs.getString("curP_Hora");
                String Empleado = rs.getString("emp_Nombre");
                
                modelo.addRow(new Object[]{Id, Curso,FechaInicio, FechaFin,Cupo,CupoDisponible,Lugar,Hora,Empleado});
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void Pasar_Datos(){
        int row = frm.tblCursosPublicados.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            Sacar_Ides(Integer.valueOf(frm.tblCursosPublicados.getValueAt(frm.tblCursosPublicados.getSelectedRow(), 0).toString()));           
            String Curso = frm.tblCursosPublicados.getValueAt(frm.tblCursosPublicados.getSelectedRow(), 1).toString();
            Date FechaInicio = Date.valueOf(frm.tblCursosPublicados.getValueAt(frm.tblCursosPublicados.getSelectedRow(), 2).toString());
            Date FechaFin = Date.valueOf(frm.tblCursosPublicados.getValueAt(frm.tblCursosPublicados.getSelectedRow(), 3).toString());
            String Cupo = frm.tblCursosPublicados.getValueAt(frm.tblCursosPublicados.getSelectedRow(), 4).toString();
            String CupoDisponible = frm.tblCursosPublicados.getValueAt(frm.tblCursosPublicados.getSelectedRow(), 5).toString();
            String Lugar = frm.tblCursosPublicados.getValueAt(frm.tblCursosPublicados.getSelectedRow(), 6).toString();
            String Hora = frm.tblCursosPublicados.getValueAt(frm.tblCursosPublicados.getSelectedRow(), 7).toString();
            String Empleado = frm.tblCursosPublicados.getValueAt(frm.tblCursosPublicados.getSelectedRow(), 8).toString();
            
            frm.txtCurso.setText(Curso);
            frm.txtFechaInicio.setDate(FechaInicio);
            frm.txtFechaFin.setDate(FechaFin);
            frm.txtCupo.setText(Cupo);
            frm.txtLugar.setText(Lugar);
            frm.txtHora.setText(Hora);
            frm.txtEmpleado.setText(Empleado);

            frm.lbl_accion.setText("Actualizar curso publicado");
            TipoAccion=false;
            
        }
    }
    
    public void Sacar_Ides(int dato) {
        try {
            System.out.println("eL DATO ES " + dato);
            ResultSet rs = daocursosPublicados.Consulta_IDES(dato);

            while (rs.next()) {
                String idCursoPublicado = String.valueOf(rs.getInt("curP_Id"));
                String FKidCurso = rs.getString("cur_Id");
                String FKidEmpleado = rs.getString("emp_Id");
                frm.lblId.setText(idCursoPublicado);
                frm.lblIdCurso.setText(FKidCurso);
                frm.lblIdEmpleado.setText(FKidEmpleado);
            }
        } catch (Exception e) {

            System.out.println(e);
        }
    }
    
    public void Guardar_datos(){
        if (TipoAccion==true) {
            Insertar_CursoPublicado();
        }else if(TipoAccion==false){
            Actualizar_CursoPublicado();
        }    
    }
    
    public void LimpiarCajas() {
        frm.txtCurso.setText("");
        frm.txtFechaInicio.setDate(null);
        frm.txtFechaFin.setDate(null);
        frm.txtLugar.setText("");
        frm.txtHora.setText("");
        frm.txtCupo.setText("");
        frm.txtEmpleado.setText("");
        frm.lblId.setText("Id");
        frm.lblIdCurso.setText("IdCur");
        frm.lblIdEmpleado.setText("IdEmpl");
        frm.lbl_accion.setText("Nuevo curso publicado");
        
        TipoAccion=true;
    }
    
    public int Insertar_CursoPublicado() {
        int Curso = Integer.valueOf(frm.lblIdCurso.getText());
        String FechaInicio =getfecha( this.frm.txtFechaInicio);
        String FechaFin =getfecha( this.frm.txtFechaFin);
        String Cupo = frm.txtCupo.getText();
        String Lugar = frm.txtLugar.getText();
        String Hora = frm.txtHora.getText();
        int Empleado = Integer.valueOf(frm.lblIdEmpleado.getText());
                       
        vocursosPublicados.setCurP_FK_IdCurso(Curso);
        vocursosPublicados.setCurP_FechaInicio(FechaInicio);
        vocursosPublicados.setCurP_FechaFin(FechaFin);
        vocursosPublicados.setCurP_Cupo(Cupo);
        vocursosPublicados.setCurP_CupoDisponible(Cupo);
        vocursosPublicados.setCurP_Lugar(Lugar);
        vocursosPublicados.setCurP_Hora(Hora);
        vocursosPublicados.setCurP_FK_idEmpleado(Empleado);
        
        int res = 0;

        if ("".equals(Curso)||"".equals(FechaInicio)||"".equals(FechaFin)||"".equals(Cupo)||"".equals(Lugar)||"".equals(Hora)||"".equals(Empleado)) {
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {

            try {

                res = daocursosPublicados.Insertar_Cursos_Publicados(vocursosPublicados);

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
    
    public int Actualizar_CursoPublicado() {
        int idCursoPublicado=Integer.valueOf(frm.lblId.getText());
        int Curso = Integer.valueOf(frm.lblIdCurso.getText());
        String FechaInicio =getfecha( this.frm.txtFechaInicio);
        String FechaFin =getfecha( this.frm.txtFechaFin);
        String Cupo = frm.txtCupo.getText();
        String Lugar = frm.txtLugar.getText();
        String Hora = frm.txtHora.getText();
        int Empleado = Integer.valueOf(frm.lblIdEmpleado.getText());
               
        int res = 0;
        int row = this.frm.tblCursosPublicados.getSelectedRow();
        
        vocursosPublicados.setCurP_Id(idCursoPublicado);
        vocursosPublicados.setCurP_FK_IdCurso(Curso);
        vocursosPublicados.setCurP_FechaInicio(FechaInicio);
        vocursosPublicados.setCurP_FechaFin(FechaFin);
        vocursosPublicados.setCurP_Cupo(Cupo);
        vocursosPublicados.setCurP_CupoDisponible(Cupo);
        vocursosPublicados.setCurP_Lugar(Lugar);
        vocursosPublicados.setCurP_Hora(Hora);
        vocursosPublicados.setCurP_FK_idEmpleado(Empleado);
        

        if ("".equals(Curso)||"".equals(FechaInicio)||"".equals(FechaFin)||"".equals(Cupo)||"".equals(Lugar)||"".equals(Hora)||"".equals(Empleado)) {
                JOptionPane.showMessageDialog(null, "Campos vacios");
            } else {

                try {

                    res = daocursosPublicados.Actualizar_Cursos_Publicados(vocursosPublicados);

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
    
    public void Eliminar_CursosPublicado() {
        int row = frm.tblCursosPublicados.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            int dato = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar ?");
            if (dato == 0) {
                DefaultTableModel mode = (DefaultTableModel) frm.tblCursosPublicados.getModel();
                int clave = Integer.valueOf(mode.getValueAt(row, 0).toString());
                int r;
                vocursosPublicados.setCurP_Id(clave);
                r = daocursosPublicados.Eliminar_Cursos(vocursosPublicados);
                if (r != 0) {
                    JOptionPane.showMessageDialog(null, "El Registro se eliminó correctamente");
                    llenaGrid();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Eliminar el Registro");
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

                    if ("Cursos".equals(tabla.veri.getText())) {
                        String IdCurso = tabla.tblComboBox.getValueAt(tabla.tblComboBox.getSelectedRow(), 0).toString();
                        String Curso = tabla.tblComboBox.getValueAt(tabla.tblComboBox.getSelectedRow(), 1).toString();
                        frm.txtCurso.setText(Curso);
                        frm.lblIdCurso.setText(IdCurso);

                    }
                    if ("Empleados".equals(tabla.veri.getText())) {
                        String IdEmpleado = tabla.tblComboBox.getValueAt(tabla.tblComboBox.getSelectedRow(), 0).toString();
                        String Empleado = tabla.tblComboBox.getValueAt(tabla.tblComboBox.getSelectedRow(), 1).toString();
                        frm.txtEmpleado.setText(Empleado);
                        frm.lblIdEmpleado.setText(IdEmpleado);

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
            ResultSet rs = daocursosPublicados.Consulta_CursosPublicados(valor);
            if (valor.equals("Cursos")) {
                while (rs.next()) {
                    String A1 = String.valueOf(rs.getInt("cur_Id"));
                    String A2 = rs.getString("cur_Nombre");
                    modelo.addRow(new Object[]{A1, A2});
                }
            }
            if (valor.equals("Empleados")) {
                while (rs.next()) {
                    String A1 = String.valueOf(rs.getInt("emp_Id"));
                    String A2 = rs.getString("emp_Nombre");
                    String A3 = rs.getString("emp_ApellidoP");
                    modelo.addRow(new Object[]{A1, A2 +" "+ A3});
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
