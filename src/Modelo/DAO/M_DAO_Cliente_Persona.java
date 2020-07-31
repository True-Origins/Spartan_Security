
package Modelo.DAO;
import Modelo.VO.M_VO_Cliente_Persona;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class M_DAO_Cliente_Persona {
    M_DAO_Conexion conec = new M_DAO_Conexion();
    private Connection cnn;

    public ResultSet Consulta_Cliente_Persona() {
        cnn = conec.conexion();
        ResultSet rs = null;
        String consulta = "SELECT * FROM tblclientepersona";
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

    public int Insertar_Cliente_Persona(M_VO_Cliente_Persona x) {
        int r = 0;
        String Insert="INSERT INTO tblclientepersona (clP_Nombre,clP_ApellidoP,clP_ApellidoM,clP_Correo,clP_Password,clP_Telefono) VALUES ('"+x.getClP_Nombre()+"','"+x.getClP_ApellidoP()+"','"+x.getClP_ApellidoM()+"','"+x.getClP_Correo()+"','"+x.getClP_Password()+"','"+x.getClP_Telefono()+"');";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return r;
        }
    }

    public int Actualizar_Cliente_Persona(M_VO_Cliente_Persona x) {
        int r = 0;
        String Actualizar = "UPDATE tblclientepersona SET clP_Nombre = '"+x.getClP_Nombre()+"',clP_ApellidoP = '"+x.getClP_ApellidoP()+"',clP_ApellidoM = '"+x.getClP_ApellidoM()+"',clP_Correo = '"+x.getClP_Correo()+"',clP_Password = '"+x.getClP_Password()+"',clP_Telefono = '"+x.getClP_Telefono()+"' WHERE clP_Id = '"+x.getClP_Id()+"';";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return r;
        }
    }

    public int Eliminar_Cliente_Persona(M_VO_Cliente_Persona x) {
        int r = 0;
        String Delete="DELETE FROM tblclientepersona WHERE clP_Id = '"+x.getClP_Id()+"';";
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
