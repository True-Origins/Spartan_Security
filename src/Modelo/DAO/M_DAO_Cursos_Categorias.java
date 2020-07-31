package Modelo.DAO;
import Modelo.VO.M_VO_Cursos_Categoria;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class M_DAO_Cursos_Categorias {
    M_DAO_Conexion conec = new M_DAO_Conexion();
    private Connection cnn;

    public ResultSet Consulta_Categoria_Curso() {
        cnn = conec.conexion();
        ResultSet rs = null;
        String consulta = "SELECT * FROM tblcursoscategoria";
        try {
            Statement stm;
            stm = (Statement) cnn.createStatement();
            rs = stm.executeQuery(consulta);
            return rs;
        } catch (Exception e) {
            System.out.println("Error al hacer la consulta");
        }
        return null;
    }

    public int Insertar_Categoria_Curso(M_VO_Cursos_Categoria x) {
        int r = 0;
        String Insert="INSERT INTO tblcursoscategoria (curC_Nombre,curC_Descripcion) VALUES ('"+x.getCurC_Nombre()+"','"+x.getCurC_Descripcion()+"');";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int Actualizar_Categoria_Curso(M_VO_Cursos_Categoria x) {
        int r = 0;
        String Actualizar = "UPDATE tblcursoscategoria SET curC_Nombre = '"+x.getCurC_Nombre()+"',curC_Descripcion = '"+x.getCurC_Descripcion()+"' WHERE curC_Id = '"+x.getCurC_Id()+"';";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int Eliminar_Categorias_cursos(M_VO_Cursos_Categoria x) {
        int r = 0;
        String Delete="DELETE FROM tblcursoscategoria WHERE curC_Id = '"+x.getCurC_Id()+"';";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Delete);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }
}
