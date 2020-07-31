
package Modelo.DAO;
import Modelo.VO.M_VO_Cliente_Empresa;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class M_DAO_Cliente_Empresa {
     M_DAO_Conexion conec = new M_DAO_Conexion();
    private Connection cnn;

    public ResultSet Consulta_Cliente_Empresa() {
        cnn = conec.conexion();
        ResultSet rs = null;
        String consulta = "SELECT * FROM tblclienteempresa";
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

    public int Insertar_Cliente_Empresa(M_VO_Cliente_Empresa x) {
        int r = 0;
        String Insert="INSERT INTO tblclienteempresa(clE_NombreEmpresa,clE_RFC,clE_Direccion,clE_Correo,clE_Telefono,clE_ContactoNombre,clE_ContactoCargo,clE_AmbitoEmpresa,clE_Referencias) VALUES ('"+x.getClE_NombreEmpresa()+"','"+x.getClE_RFC()+"','"+x.getClE_Direccion()+"','"+x.getClE_Correo()+"','"+x.getClE_Telefono()+"','"+x.getClE_ContactoNombre()+"','"+x.getClE_ContactoCargo()+"','"+x.getClE_AmbitoEmpresa()+"','"+x.getClE_Referencias()+"');";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return r;
        }
    }

    public int Actualizar_Cliente_Empresa(M_VO_Cliente_Empresa x) {
        int r = 0;
        String Actualizar = "UPDATE tblclienteempresa SET clE_NombreEmpresa = '"+x.getClE_NombreEmpresa()+"',clE_RFC = '"+x.getClE_RFC()+"',clE_Direccion = '"+x.getClE_Direccion()+"',clE_Correo = '"+x.getClE_Correo()+"',clE_Telefono = '"+x.getClE_Telefono()+"', clE_ContactoNombre = '"+x.getClE_ContactoNombre()+"',clE_ContactoCargo = '"+x.getClE_ContactoCargo()+"',clE_AmbitoEmpresa = '"+x.getClE_AmbitoEmpresa()+"',clE_Referencias = '"+x.getClE_Referencias()+"' WHERE clE_Id = '"+x.getClE_Id()+"';";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return r;
        }
    }

    public int Eliminar_Cliente_Empresa(M_VO_Cliente_Empresa x) {
        int r = 0;
        String Delete="DELETE FROM tblclienteempresa WHERE clE_Id = '"+x.getClE_Id()+"';";
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
