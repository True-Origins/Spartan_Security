package Modelo.DAO;
import Modelo.VO.M_VO_Servicios;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class M_DAO_Servicios {
    M_DAO_Conexion conec = new M_DAO_Conexion();
    private Connection cnn;

    public ResultSet Consulta_Servicios(String opcion) {
        cnn = conec.conexion();
        ResultSet rs = null;
        String consulta = " ";
        if ("Servicios".equals(opcion)) {
            consulta = "SELECT serv_Id,serv_Nombre,serv_Descripcion,serv_Precio,tserv_Nombre\n" +
                    "FROM tblservicios INNER JOIN tbltiposervicio ON tblservicios.serv_FK_idTipoServ=tbltiposervicio.tserv_Id;";
        }

        if ("Tipo_Servicios".equals(opcion)) {
            consulta = "SELECT * FROM tbltiposervicio;";
        }
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
    public ResultSet Consulta_IDES(int ids) {
        cnn = conec.conexion();
        ResultSet rs = null;
        String consulta = "SELECT serv_Id,tserv_Id \n" +
                "FROM tblservicios INNER JOIN tbltiposervicio ON tblservicios.serv_FK_idTipoServ=tbltiposervicio.tserv_Id\n" +
                "WHERE tblservicios.serv_Id='"+ids+"';";
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
    public int Insertar_Servivios(M_VO_Servicios x) {
        int r = 0;
        String Insert="INSERT INTO tblservicios (serv_Nombre,serv_Descripcion,serv_Precio,serv_FK_idTipoServ)\n" +
                      "VALUES ('"+x.getServ_Nombre()+"','"+x.getServ_Descripcion()+"','"+x.getServ_Precio()+"','"+x.getServ_FK_idTipoServ()+"');";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return r;
        }
    }

    public int Actualizar_Servicios(M_VO_Servicios x) {
        int r = 0;
        String Actualizar = "UPDATE tblservicios SET serv_Nombre = '"+x.getServ_Nombre()+"',serv_Descripcion = '"+x.getServ_Descripcion()+"',\n" +
        "serv_Precio = '"+x.getServ_Precio()+"',serv_FK_idTipoServ = '"+x.getServ_FK_idTipoServ()+"' WHERE `serv_Id` = '"+x.getServ_Id()+"';";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }   
    public int Eliminar_Servicios(M_VO_Servicios x) {
        int r = 0;
        String Delete="DELETE FROM tblservicios WHERE serv_Id = '"+x.getServ_Id()+"';";
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
