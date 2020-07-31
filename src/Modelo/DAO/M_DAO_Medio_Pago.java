
package Modelo.DAO;
import Modelo.VO.M_VO_Medio_pago;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class M_DAO_Medio_Pago {
    M_DAO_Conexion conec = new M_DAO_Conexion();
    private Connection cnn;

    public ResultSet Consulta_Medio_Pago() {
        cnn = conec.conexion();
        ResultSet rs = null;
        String consulta = "SELECT * FROM tblmediopago";
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
    public int Insertar_Medio_pago(M_VO_Medio_pago x) {
        int r = 0;
        String Insert="INSERT INTO tblmediopago(medP_Nombre,medP_AplicaPeriodo) VALUES ('"+x.getMedP_Nombre()+"','"+x.getMedP_AplicaPeriodo()+"');";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return r;
        }
    }
    public int Actualizar_Medio_pago(M_VO_Medio_pago x) {
        int r = 0;
        String Actualizar = "UPDATE tblmediopago SET medP_Nombre = '"+x.getMedP_Nombre()+"', medP_AplicaPeriodo = '"+x.getMedP_AplicaPeriodo()+"' WHERE medP_id = '"+x.getMedP_id()+"';";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return r;
        }
    }
    public int Eliminar_Medio_pago(M_VO_Medio_pago x) {
        int r = 0;
        String Delete="DELETE FROM tblmediopago WHERE medP_id = '"+x.getMedP_id()+"';";
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
