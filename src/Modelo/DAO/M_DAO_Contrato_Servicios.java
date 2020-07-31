package Modelo.DAO;
import Modelo.VO.M_VO_Contrato_Servicio;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class M_DAO_Contrato_Servicios {
    M_DAO_Conexion conec = new M_DAO_Conexion();
    private Connection cnn;

    public ResultSet Consulta_Contaro_Servicios(String opcion) {
        cnn = conec.conexion();
        ResultSet rs = null;
        String consulta = " ";
        if ("Contrato_Servicios".equals(opcion)) {
            consulta = "SELECT contS_Id,contS_FechaContrato,serv_Nombre,clE_NombreEmpresa,medP_Nombre\n" +
                "FROM tblcontratoservicio INNER JOIN tblservicios ON tblcontratoservicio.contS_FK_idServicio=tblservicios.serv_Id\n" +
                "INNER JOIN tblclienteempresa ON tblcontratoservicio.contS_FK_idClienteEmpresa=tblclienteempresa.clE_Id\n" +
                "INNER JOIN tblmediopago ON tblcontratoservicio.contS_FK_idMedioPago=tblmediopago.medP_id;";
        }

        if ("Servicios".equals(opcion)) {
            consulta = "SELECT * FROM tblservicios;";
        }
        if ("Cliente_Empresa".equals(opcion)) {
            consulta = "SELECT * FROM tblclienteempresa;";
        }
        if ("Medio_Pago".equals(opcion)) {
            consulta = "SELECT * FROM tblmediopago;";
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
        String consulta = "SELECT contS_Id,serv_Id,clE_Id,medP_id\n" +
                    "FROM tblcontratoservicio INNER JOIN tblservicios ON tblcontratoservicio.contS_FK_idServicio=tblservicios.serv_Id\n" +
                    "INNER JOIN tblclienteempresa ON tblcontratoservicio.contS_FK_idClienteEmpresa=tblclienteempresa.clE_Id\n" +
                    "INNER JOIN tblmediopago ON tblcontratoservicio.contS_FK_idMedioPago=tblmediopago.medP_id\n" +
                    "WHERE tblcontratoservicio.contS_Id ='"+ids+"';";
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
    public int Insertar_Servivios(M_VO_Contrato_Servicio x) {
        int r = 0;
        String Insert="INSERT INTO tblcontratoservicio (contS_FechaContrato,contS_FK_idServicio,contS_FK_idClienteEmpresa,contS_FK_idMedioPago)\n" +
                "VALUES ('"+x.getContS_FechaContrato()+"','"+x.getContS_FK_idServicio()+"','"+x.getContS_FK_idClienteEmpresa()+"','"+x.getOntS_FK_idMedioPago()+"');";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return r;
        }
    }

    public int Actualizar_Servicios(M_VO_Contrato_Servicio x) {
        int r = 0;
        String Actualizar = "UPDATE tblcontratoservicio SET contS_FechaContrato= '"+x.getContS_FechaContrato()+"', contS_FK_idServicio = '"+x.getContS_FK_idServicio()+"',\n" +
            "contS_FK_idClienteEmpresa = '"+x.getContS_FK_idClienteEmpresa()+"',contS_FK_idMedioPago = '"+x.getOntS_FK_idMedioPago()+"'\n" +
            "WHERE contS_Id = '"+x.getContS_Id()+"';";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }   
    public int Eliminar_Contrato_Servicios(M_VO_Contrato_Servicio x) {
        int r = 0;
        String Delete="DELETE FROM tblcontratoservicio WHERE contS_Id = '"+x.getContS_Id()+"';";
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
