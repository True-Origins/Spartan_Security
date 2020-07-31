package Controlador;
import Modelo.DAO.M_DAO_Cursos_Categorias;
import Modelo.VO.M_VO_Cursos_Categoria;
import Vista.V_Cursos_Categoria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class C_Cursos_Categorias implements ActionListener{
    private boolean TipoAccion=true;
    private M_VO_Cursos_Categoria voC_Categoria;
    private M_DAO_Cursos_Categorias daoC_Categoria;
    private V_Cursos_Categoria frm;
    public C_Cursos_Categorias(M_DAO_Cursos_Categorias daoC_Categoria, V_Cursos_Categoria frm,M_VO_Cursos_Categoria voC_Categoria) {
        this.daoC_Categoria = daoC_Categoria;
        this.frm = frm;
        this.voC_Categoria = voC_Categoria;
        llenaGrid();
        this.frm.btnguardar.addActionListener(this);
        this.frm.btnactualizar.addActionListener(this);
        this.frm.btneliminar.addActionListener(this);
        this.frm.btncancelar.addActionListener(this);

        this.frm.txtDescripcion.addActionListener(this);
        this.frm.txtNombreCategria.addActionListener(this);
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
            Eliminar_Cursos_Categorias();
        }
        if (e.getSource() == frm.btncancelar) {
            LimpiarCajas();
        }
    }
    public void llenaGrid() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.frm.tbl_Curso_Categoria.getModel();
            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            ResultSet rs = daoC_Categoria.Consulta_Categoria_Curso();
            while (rs.next()) {
                String id_c_categoria = String.valueOf(rs.getInt("curC_Id"));
                String c_categoria = rs.getString("curC_Nombre");
                String descripcion = rs.getString("curC_Descripcion");
                modelo.addRow(new Object[]{id_c_categoria, c_categoria,descripcion});
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void Guardar_datos(){
        if (TipoAccion==true) {
            Insertar_Curso_Categorias();
        }else if(TipoAccion==false){
            Actualizar_Curso_Categoria();
        }    
    }
    public void Pasar_Datos_Actualizar(){
        int row = frm.tbl_Curso_Categoria.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            String ID = frm.tbl_Curso_Categoria.getValueAt(frm.tbl_Curso_Categoria.getSelectedRow(), 0).toString();
            String C_Categoria = frm.tbl_Curso_Categoria.getValueAt(frm.tbl_Curso_Categoria.getSelectedRow(), 1).toString();
            String Descripcion = frm.tbl_Curso_Categoria.getValueAt(frm.tbl_Curso_Categoria.getSelectedRow(), 2).toString();
            frm.txtNombreCategria.setText(C_Categoria);
            frm.txtDescripcion.setText(Descripcion);
            frm.lbl_id.setText(ID);
            frm.lbl_accion.setText("Actualzar cursos categorias");
            TipoAccion=false;
            
        }
    }
    public int Insertar_Curso_Categorias() {
        String C_Curso = frm.txtNombreCategria.getText();
        String Descripcion = frm.txtDescripcion.getText();
        voC_Categoria.setCurC_Nombre(C_Curso);
        voC_Categoria.setCurC_Descripcion(Descripcion);
        int res = 0;
        if ("".equals(C_Curso)||"".equals(Descripcion)) {
            JOptionPane.showMessageDialog(null, "Por favor de llenar los campos vacios");
        } else {
            try {
                res = daoC_Categoria.Insertar_Categoria_Curso(voC_Categoria);
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

    public int Actualizar_Curso_Categoria() {
        String ID_A = frm.lbl_id.getText();
        String C_Curso = frm.txtNombreCategria.getText();
         String Descripcion = frm.txtDescripcion.getText();
        int res = 0;
        int row = this.frm.tbl_Curso_Categoria.getSelectedRow();        
        voC_Categoria.setCurC_Id(Integer.valueOf(ID_A));
        voC_Categoria.setCurC_Nombre(C_Curso);
        voC_Categoria.setCurC_Descripcion(Descripcion);
        if ("".equals(ID_A) || "".equals(C_Curso) || "".equals(Descripcion)) {
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {
                try {
                    res = daoC_Categoria.Actualizar_Categoria_Curso(voC_Categoria);
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

    public void Eliminar_Cursos_Categorias() {
        int row = frm.tbl_Curso_Categoria.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una Columna!!");
        } else {
            int dato = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar ?");
            if (dato == 0) {
                DefaultTableModel mode = (DefaultTableModel) frm.tbl_Curso_Categoria.getModel();
                int clave = Integer.valueOf(mode.getValueAt(row, 0).toString());
                int r;
                voC_Categoria.setCurC_Id(clave);
                r = daoC_Categoria.Eliminar_Categorias_cursos(voC_Categoria);
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

        frm.txtNombreCategria.setText("");
        frm.txtDescripcion.setText("");
        frm.lbl_id.setText("ID");
        frm.lbl_accion.setText("Nuevo curso categoria");
        TipoAccion=true;
    }
}
