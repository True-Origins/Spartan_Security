
package Modelo.DAO;
import Modelo.VO.M_VO_Turno;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class M_DAO_Turno {
    M_DAO_Conexion conec = new M_DAO_Conexion();
    private Connection cnn;

    public ResultSet Consulta_Turno() {
        cnn = conec.conexion();
        ResultSet rs = null;
        String consulta = "SELECT * FROM tblturno";
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

    public int Insertar_Turno(M_VO_Turno x) {
        int r = 0;
        String Insert="INSERT INTO tblturno (tur_Nombre,tur_HoraEntrada,tur_HoraSalida) VALUES ('"+x.getTur_Nombre()+"','"+x.getTur_HoraEntrada()+"','"+x.getTur_HoraSalida()+"');";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return r;
        }
    }

    public int Actualizar_Turno(M_VO_Turno x) {
        int r = 0;
        String Actualizar = "UPDATE tblturno SET tur_Nombre = '"+x.getTur_Nombre()+"',tur_HoraEntrada = '"+x.getTur_HoraEntrada()+"',tur_HoraSalida = '"+x.getTur_HoraSalida()+"' WHERE tur_Id = '"+x.getTur_Id()+"';";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return r;
        }
    }

    public int Eliminar_Turno(M_VO_Turno x) {
        int r = 0;
        String Delete="DELETE FROM tblturno WHERE tur_Id = '"+x.getTur_Id()+"';";
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
