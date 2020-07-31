
package Modelo.DAO;
import Modelo.VO.M_VO_Puesto;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class M_DAO_Puesto {
     M_DAO_Conexion conec = new M_DAO_Conexion();
    private Connection cnn;

    public ResultSet Consulta_Puesto() {
        cnn = conec.conexion();
        ResultSet rs = null;
        String consulta = "SELECT * FROM tblpuesto";
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

    public int Insertar_Puesto(M_VO_Puesto x) {
        int r = 0;
        String Insert="INSERT INTO tblpuesto(psto_Nombre,psto_Sueldo) VALUES ('"+x.getPsto_Nombre()+"','"+x.getPsto_Sueldo()+"');";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return r;
        }
    }

    public int Actualizar_Puesto(M_VO_Puesto x) {
        int r = 0;
        String Actualizar = "UPDATE tblpuesto SET psto_Nombre = '"+x.getPsto_Nombre()+"',psto_Sueldo = '"+x.getPsto_Sueldo()+"' WHERE psto_Id = '"+x.getPsto_Id()+"';";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return r;
        }
    }

    public int Eliminar_Puesto(M_VO_Puesto x) {
        int r = 0;
        String Delete="DELETE FROM tblpuesto WHERE psto_Id = '"+x.getPsto_Id()+"';";
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
