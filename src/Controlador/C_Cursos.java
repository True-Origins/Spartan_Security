
package Controlador;

import Modelo.DAO.M_DAO_Cursos;
import Modelo.VO.M_VO_Cursos;
import Vista.V_ComboBox;
import Vista.V_Cursos;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class C_Cursos implements ActionListener{
    private M_VO_Cursos vocursos;
    private M_DAO_Cursos daocursos;
    private V_Cursos frm;
    private V_ComboBox tabla;
    private boolean TipoAccion=true;
    
    public C_Cursos(M_DAO_Cursos daocursos, V_Cursos frm,M_VO_Cursos vocursos,V_ComboBox tabla) {
        this.daocursos = daocursos;
        this.frm = frm;
        this.vocursos = vocursos;
        this.tabla = tabla;
        llenaGrid();
        llenaGrid_Inactivos();
        this.frm.btn_guardar.addActionListener(this);
        this.frm.Btn_Actualizar.addActionListener(this);
        this.frm.btnCategoria.addActionListener(this);
        this.frm.btnDesactivarCurso.addActionListener(this);
        this.frm.btncancelar.addActionListener(this);
        this.frm.btnActivarCurso.addActionListener(this);
        
        this.frm.txtNombre.addActionListener(this);
        this.frm.txtDescripcion.addActionListener(this);
        this.frm.txtDuracionDias.addActionListener(this);
        this.frm.txtPrecio.addActionListener(this);
        this.frm.txtCategoria.addActionListener(this);
        this.frm.lbl_id.setVisible(false);
        this.frm.lblCategoria.setVisible(false);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm.btn_guardar) {
            Guardar_datos();
        }
        if (e.getSource() == frm.btnCategoria) {
            Abreformulario_Tabla("Categoria");
        }
        if (e.getSource() == frm.Btn_Actualizar) {
            Pasar_Datos();
        }
        if (e.getSource() == frm.btnDesactivarCurso) {
            Desactivar_Curso();
        }
        if (e.getSource() == frm.btnActivarCurso) {
            Activar_Curso();
        }
        if (e.getSource() == frm.btncancelar) {
            LimpiarCajas();
        }
    }
    
    public void Activar_Curso(){
        Sacar_Ides(Integer.valueOf(frm.tblCursosInactivos.getValueAt(frm.tblCursosInactivos.getSelectedRow(), 0).toString()));
        
        int id = Integer.valueOf(frm.lbl_id.getText());
        int row = this.frm.tblCursosActivos.getSelectedRow();
        vocursos.setCur_Id(id);
        
        int res = 0;
        try {

                    res = daocursos.Activar_Cursos(vocursos);

                    if (res != 0) {
                        JOptionPane.showMessageDialog(null, "Datos Correctamente Actualizados");
                        llenaGrid();
                        llenaGrid_Inactivos();
                        LimpiarCajas();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al Actualizar Datos");
                    }

                } catch (Exception e) {
                    System.out.print(e);
                }
    }
    
    public void Desactivar_Curso(){
        Sacar_Ides(Integer.valueOf(frm.tblCursosActivos.getValueAt(frm.tblCursosActivos.getSelectedRow(), 0).toString()));
        
        int id = Integer.valueOf(frm.lbl_id.getText());
        int row = this.frm.tblCursosActivos.getSelectedRow();
        vocursos.setCur_Id(id);
        
        int res = 0;
        try {

                    res = daocursos.Desactivar_Cursos(vocursos);

                    if (res != 0) {
                        JOptionPane.showMessageDialog(null, "Datos Correctamente Actualizados");
                        llenaGrid();
                        llenaGrid_Inactivos();
                        LimpiarCajas();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al Actualizar Datos");
                    }

                } catch (Exception e) {
                    System.out.print(e);
                }
    }
    
    public void Pasar_Datos(){
        int row = frm.tblCursosActivos.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            Sacar_Ides(Integer.valueOf(frm.tblCursosActivos.getValueAt(frm.tblCursosActivos.getSelectedRow(), 0).toString()));           
            String nombre = frm.tblCursosActivos.getValueAt(frm.tblCursosActivos.getSelectedRow(), 1).toString();
            String descripcion = frm.tblCursosActivos.getValueAt(frm.tblCursosActivos.getSelectedRow(), 2).toString();
            String duraciondias = frm.tblCursosActivos.getValueAt(frm.tblCursosActivos.getSelectedRow(), 3).toString();
            String precio = frm.tblCursosActivos.getValueAt(frm.tblCursosActivos.getSelectedRow(), 4).toString();
            String categoria = frm.tblCursosActivos.getValueAt(frm.tblCursosActivos.getSelectedRow(), 5).toString();
            
            frm.txtNombre.setText(nombre);
            frm.txtDescripcion.setText(descripcion);
            frm.txtDuracionDias.setText(duraciondias);
            frm.txtPrecio.setText(precio);
            frm.txtCategoria.setText(categoria);

            frm.lbl_accion.setText("Actualizar curso");
            TipoAccion=false;
            
        }
    }
    
    public void Sacar_Ides(int dato) {
        try {
            System.out.println("eL DATO ES " + dato);
            ResultSet rs = daocursos.Consulta_IDES(dato);

            while (rs.next()) {
                String idCurso = String.valueOf(rs.getInt("cur_Id"));
                String idCategoria = rs.getString("cur_FK_categoria");
                frm.lbl_id.setText(idCurso);
                frm.lblCategoria.setText(idCategoria);
            }
        } catch (Exception e) {

            System.out.println(e);
        }
    }
    
    public void llenaGrid() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.frm.tblCursosActivos.getModel();
            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            ResultSet rs = daocursos.Consulta_Cursos("Cursos");
            while (rs.next()) {
                String ID_Area = String.valueOf(rs.getInt("cur_Id"));
                String Nombre = rs.getString("cur_Nombre");
                String Descripcion = rs.getString("cur_Descripcion");
                String DuracionDias = rs.getString("cur_DuracionDias");
                String Precio = rs.getString("cur_Precio");
                String Categoria = rs.getString("curC_Nombre");
                String Activo = rs.getString("cur_Activo");
                modelo.addRow(new Object[]{ID_Area, Nombre,Descripcion, DuracionDias,Precio,Categoria});
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void llenaGrid_Inactivos() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.frm.tblCursosInactivos.getModel();
            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            ResultSet rs = daocursos.Consulta_CursosInactivos("Cursos");
            while (rs.next()) {
                String ID_Area = String.valueOf(rs.getInt("cur_Id"));
                String Nombre = rs.getString("cur_Nombre");
                String Descripcion = rs.getString("cur_Descripcion");
                String DuracionDias = rs.getString("cur_DuracionDias");
                String Precio = rs.getString("cur_Precio");
                String Categoria = rs.getString("curC_Nombre");
                String Activo = rs.getString("cur_Activo");
                modelo.addRow(new Object[]{ID_Area, Nombre,Descripcion, DuracionDias,Precio,Categoria});
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public int Insertar_Curso() {
        String nombre = frm.txtNombre.getText();
        String descripcion = frm.txtDescripcion.getText();
        int duraciondias = Integer.valueOf(frm.txtDuracionDias.getText());
        float precio = Float.valueOf(frm.txtPrecio.getText());
        int categoria = Integer.valueOf(frm.lblCategoria.getText());
               
        vocursos.setCur_Nombre(nombre);
        vocursos.setCur_Descripcion(descripcion);
        vocursos.setCur_DuracionDias(duraciondias);
        vocursos.setCur_Precio(precio);
        vocursos.setCur_FK_categoria(categoria);
        int res = 0;

        if ("".equals(nombre)||"".equals(descripcion)||"".equals(duraciondias)||"".equals(precio)||"".equals(categoria)) {
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {

            try {

                res = daocursos.Insertar_Cursos(vocursos);

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
            Insertar_Curso();
        }else if(TipoAccion==false){
            Actualizar_Curso();
        }    
    }
    
    public void LimpiarCajas() {
        frm.txtNombre.setText("");
        frm.txtDescripcion.setText("");
        frm.txtDuracionDias.setText("");
        frm.txtPrecio.setText("");
        frm.txtCategoria.setText("");
        frm.lbl_id.setText("ID");
        frm.lblCategoria.setText("idCat");
        frm.lbl_accion.setText("Nuevo curso");
        TipoAccion=true;
    }
    
    public int Actualizar_Curso() {
        int id = Integer.valueOf(frm.lbl_id.getText());
        String nombre = frm.txtNombre.getText();
        String descripcion = frm.txtDescripcion.getText();
        int duraciondias = Integer.valueOf(frm.txtDuracionDias.getText());
        float precio = Float.valueOf(frm.txtPrecio.getText());
        int categoria = Integer.valueOf(frm.lblCategoria.getText());
               
        int res = 0;
        int row = this.frm.tblCursosActivos.getSelectedRow();
        vocursos.setCur_Id(id);
        vocursos.setCur_Nombre(nombre);
        vocursos.setCur_Descripcion(descripcion);
        vocursos.setCur_DuracionDias(duraciondias);
        vocursos.setCur_Precio(precio);
        vocursos.setCur_FK_categoria(categoria);

        if ("".equals(nombre)||"".equals(descripcion)||"".equals(duraciondias)||"".equals(precio)||"".equals(categoria)) {
                JOptionPane.showMessageDialog(null, "Campos vacios");
            } else {

                try {

                    res = daocursos.Actualizar_Cursos(vocursos);

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

                    if ("Categoria".equals(tabla.veri.getText())) {
                        String ID_Categoria = tabla.tblComboBox.getValueAt(tabla.tblComboBox.getSelectedRow(), 0).toString();
                        String Categoria = tabla.tblComboBox.getValueAt(tabla.tblComboBox.getSelectedRow(), 1).toString();
                        frm.txtCategoria.setText(Categoria);
                        frm.lblCategoria.setText(ID_Categoria);

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
            ResultSet rs = daocursos.Consulta_Cursos(valor);
            if (valor.equals("Categoria")) {
                while (rs.next()) {
                    String A1 = String.valueOf(rs.getInt("curC_Id"));
                    String A2 = rs.getString("curC_Nombre");
                    modelo.addRow(new Object[]{A1, A2});
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
