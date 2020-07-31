package Modelo.DAO;

import Modelo.VO.M_VO_Empleado;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class M_DAO_Empleado {
    M_DAO_Conexion conec = new M_DAO_Conexion();
    private Connection cnn;

    public ResultSet Consulta_Empleado(String opcion) {
        cnn = conec.conexion();
        ResultSet rs = null;
        String consulta = " ";
        if ("Empleado".equals(opcion)) {
            consulta = "SELECT emp_Id,emp_Nombre,emp_ApellidoP,emp_ApellidoM,emp_Telefono,emp_Direccion,emp_Correo,psto_Nombre,tur_Nombre,sucur_Nombre,emp_Contrasena,\n" +
                       "emp_FK_Puesto,emp_FK_Turno,emp_FK_idSucursal FROM tblempleado INNER JOIN tblpuesto ON tblempleado.emp_FK_Puesto=tblpuesto.psto_Id\n" +
                       "INNER JOIN tblturno ON tblempleado.emp_FK_Turno=tblturno.tur_Id\n" +
                       "INNER JOIN tblsucursal ON tblempleado.emp_FK_idSucursal=tblsucursal.sucur_Id;";
        }
        if ("Puesto".equals(opcion)) {
            consulta = "SELECT * FROM tblpuesto; ";
        }

        if ("Turno".equals(opcion)) {
            consulta = "SELECT * FROM tblturno;";
        }

        if ("Sucursal".equals(opcion)) {
            consulta = "SELECT * FROM tblsucursal;";
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
        String consulta = "SELECT emp_Id,emp_FK_Puesto,emp_FK_Turno,emp_FK_idSucursal FROM tblempleado \n" +
                          "INNER JOIN tblpuesto ON tblempleado.emp_FK_Puesto=tblpuesto.psto_Id\n" +
                          "INNER JOIN tblturno ON tblempleado.emp_FK_Turno=tblturno.tur_Id\n" +
                          "INNER JOIN tblsucursal ON tblempleado.emp_FK_idSucursal=tblsucursal.sucur_Id\n" +
                          "where tblempleado.emp_Id='"+ids+"';";
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
    public int Insertar_Empleado(M_VO_Empleado x) {
        int r = 0;
        String Insert="INSERT INTO tblempleado (emp_Nombre,emp_ApellidoP,emp_ApellidoM,emp_Telefono,emp_Direccion,emp_Correo,emp_FK_Puesto,emp_FK_Turno,emp_FK_idSucursal,emp_Contrasena)\n" +
                      "VALUES ('"+x.getEmp_Nombre()+"','"+x.getEmp_ApellidoP()+"','"+x.getEmp_ApellidoM()+"','"+x.getEmp_Telefono()+"','"+x.getEmp_Direccion()+"','"+x.getEmp_Correo()+"','"+x.getEmp_FK_Puesto()+"',\n" +
                      "'"+x.getEmp_FK_Turno()+"','"+x.getEmp_FK_idSucursal()+"','"+x.getEmp_Contrasena()+"');";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return r;
        }
    }

    public int Actualizar_Empleado(M_VO_Empleado x) {
        int r = 0;
        String Actualizar = "UPDATE tblempleado SET emp_Nombre = '"+x.getEmp_Nombre()+"',emp_ApellidoP = '"+x.getEmp_ApellidoP()+"',emp_ApellidoM = '"+x.getEmp_ApellidoM()+"',emp_Telefono = '"+x.getEmp_Telefono()+"',\n" +
        "  emp_Direccion = '"+x.getEmp_Direccion()+"',emp_Correo = '"+x.getEmp_Correo()+"',emp_FK_Puesto = '"+x.getEmp_FK_Puesto()+"',emp_FK_Turno = '"+x.getEmp_FK_Turno()+"',\n" +
        "  emp_FK_idSucursal = '"+x.getEmp_FK_idSucursal()+"',emp_Contrasena = '"+x.getEmp_Contrasena()+"' WHERE emp_Id = '"+x.getEmp_Id()+"';";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int Eliminar_Empleado(M_VO_Empleado x) {
        int r = 0;
        String Delete="DELETE FROM tblempleado WHERE emp_Id = '"+x.getEmp_Id()+"';";
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
