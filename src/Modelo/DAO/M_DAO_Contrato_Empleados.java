package Modelo.DAO;
import Modelo.VO.M_VO_Contrato_Empleados;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class M_DAO_Contrato_Empleados {
    M_DAO_Conexion conec = new M_DAO_Conexion();
    private Connection cnn;

    public ResultSet Consulta_Contrato_Empleados(String opcion) {
        cnn = conec.conexion();
        ResultSet rs = null;
        String consulta = "SELECT contemp_Id,serv_Nombre,contS_FechaContrato,emp_Nombre\n" +
            "FROM tblcontratosempleados \n" +
            "INNER JOIN tblempleado ON tblcontratosempleados.contemp_FK_idEmpleado=tblempleado.emp_Id\n" +
            "INNER JOIN tblcontratoservicio ON tblcontratosempleados.contemp_FK_idContratoServicio=tblcontratosempleados.contemp_FK_idContratoServicio\n" +
            "INNER JOIN tblservicios ON tblcontratoservicio.contS_FK_idServicio=tblservicios.serv_Id;";
        if ("Contrato_Empleados".equals(opcion)) {
            consulta = "";
        }

        if ("Contrato_Servicios".equals(opcion)) {
            consulta = "SELECT * FROM tblcontratoservicio;";
        }
        if ("Empleados".equals(opcion)) {
            consulta = "SELECT * FROM tblempleado;";
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
        String consulta = "SELECT contemp_Id,contemp_FK_idContratoServicio,contemp_FK_idEmpleado\n" +
                    "FROM tblcontratosempleados INNER JOIN tblcontratoservicio ON tblcontratosempleados.contemp_FK_idContratoServicio=tblcontratosempleados.contemp_FK_idContratoServicio\n" +
                    "INNER JOIN tblempleado ON tblcontratosempleados.contemp_FK_idEmpleado=tblempleado.emp_Id\n" +
                    "WHERE tblcontratosempleados.contemp_Id='"+ids+"';";
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
    public int Insertar_Contrato_Empleados(M_VO_Contrato_Empleados x) {
        int r = 0;
        String Insert="INSERT INTO tblcontratosempleados (contemp_FK_idContratoServicio,contemp_FK_idEmpleado)\n" +
                      "VALUES ('"+x.getContemp_FK_idContratoServicio()+"','"+x.getContemp_FK_idEmpleado()+"');";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return r;
        }
    }

    public int Actualizar_Contrato_Empleados(M_VO_Contrato_Empleados x) {
        int r = 0;
        String Actualizar = "UPDATE tblcontratosempleados SET contemp_FK_idContratoServicio = '"+x.getContemp_FK_idContratoServicio()+"',\n" +
                      "contemp_FK_idEmpleado = '"+x.getContemp_FK_idEmpleado()+"' WHERE contemp_Id = '"+x.getContemp_Id()+"';";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }   
    public int Eliminar_Contrato_Empleado(M_VO_Contrato_Empleados x) {
        int r = 0;
        String Delete="DELETE FROM tblcontratosempleados WHERE contemp_Id = '"+x.getContemp_Id()+"';";
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
