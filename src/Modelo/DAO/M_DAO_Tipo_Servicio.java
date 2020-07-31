
package Modelo.DAO;
import Modelo.VO.M_VO_Tipo_Servicio;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class M_DAO_Tipo_Servicio {
    M_DAO_Conexion conec = new M_DAO_Conexion();
    private Connection cnn;

    public ResultSet Consulta_Tipo_Servicio() {
        cnn = conec.conexion();
        ResultSet rs = null;
        String consulta = "SELECT * FROM tbltiposervicio";
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

    public int Insertar_Tipo_Servicio(M_VO_Tipo_Servicio x) {
        int r = 0;
        String Insert="INSERT INTO tbltiposervicio(tserv_Nombre,tserv_Descripcion) VALUES ('"+x.getTserv_Nombre()+"','"+x.getTserv_Descripcion()+"');";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return r;
        }
    }

    public int Actualizar_Tipo_Servicio(M_VO_Tipo_Servicio x) {
        int r = 0;
        String Actualizar="UPDATE tbltiposervicio SET tserv_Nombre = '"+x.getTserv_Nombre()+"',tserv_Descripcion = '"+x.getTserv_Descripcion()+"' WHERE tserv_Id = '"+x.getTserv_Id()+"';";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return r;
        }
    }

    public int Eliminar_Tipo_Servicio(M_VO_Tipo_Servicio x) {
        int r = 0;
        String Delete="DELETE FROM tbltiposervicio WHERE tserv_Id = '"+x.getTserv_Id()+"';";
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
