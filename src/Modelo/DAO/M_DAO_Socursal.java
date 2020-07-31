package Modelo.DAO;
import Modelo.VO.M_VO_Socursal;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class M_DAO_Socursal {
    M_DAO_Conexion conec = new M_DAO_Conexion();
    private Connection cnn;

    public ResultSet Consulta_Socursal() {
        cnn = conec.conexion();
        ResultSet rs = null;
        String consulta = "SELECT * FROM tblsucursal";
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

    public int Insertar_Socursal(M_VO_Socursal x) {
        int r = 0;
        String Insert="INSERT INTO tblsucursal (sucur_Nombre,sucur_Direccion) VALUES ('"+x.getSucur_Nombr()+"','"+x.getSucur_Direccion()+"');";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return r;
        }
    }

    public int Actualizar_Socursal(M_VO_Socursal x) {
        int r = 0;
        String Actualizar = "UPDATE tblsucursal SET sucur_Nombre = '"+x.getSucur_Nombr()+"',sucur_Direccion = '"+x.getSucur_Direccion()+"' WHERE sucur_Id = '"+x.getSucur_Id()+"';";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return r;
        }
    }

    public int Eliminar_Socursal(M_VO_Socursal x) {
        int r = 0;
        String Delete="DELETE FROM tblsucursal WHERE sucur_Id = '"+x.getSucur_Id()+"';";
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
