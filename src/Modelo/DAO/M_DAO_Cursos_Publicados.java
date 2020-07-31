
package Modelo.DAO;


import Modelo.VO.M_VO_Cursos_Publicados;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class M_DAO_Cursos_Publicados {
    M_DAO_Conexion conec = new M_DAO_Conexion();
    private Connection cnn;
    
    public ResultSet Consulta_CursosPublicados(String opcion) {
        cnn = conec.conexion();
        ResultSet rs = null;
        String consulta = " ";
        if ("CursosPublicados".equals(opcion)) {
            consulta = "SELECT curP_Id,cur_Nombre,curP_FechaInicio,curP_FechaFin,curP_Cupo,curP_CupoDisponible,curP_Lugar,curP_Hora, emp_Nombre\n" +
                       "FROM tblcursospublicados INNER JOIN tblcursos ON tblcursospublicados.curP_FK_IdCurso=tblcursos.cur_Id\n" +
                       "INNER JOIN tblempleado ON tblcursospublicados.curP_FK_idEmpleado=tblempleado.emp_Id;";
        }

        if ("Cursos".equals(opcion)) {
            consulta = "SELECT * FROM tblcursos;";
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
        String consulta = "SELECT curP_Id,cur_Id, emp_Id\n" +
                        "FROM tblcursospublicados INNER JOIN tblcursos ON tblcursospublicados.curP_FK_IdCurso=tblcursos.cur_Id\n" +
                        "INNER JOIN tblempleado ON tblcursospublicados.curP_FK_idEmpleado=tblempleado.emp_Id\n" +
                        "WHERE tblcursospublicados.curP_Id='"+ids+"';";
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
    public int Insertar_Cursos_Publicados(M_VO_Cursos_Publicados x) {
        int r = 0;
        String Insert="INSERT INTO tblcursospublicados (curP_FK_IdCurso,curP_FechaInicio,curP_FechaFin,curP_Cupo,curP_CupoDisponible,curP_Lugar,curP_Hora,curP_FK_idEmpleado)\n" +
                    "VALUES ('"+x.getCurP_FK_IdCurso()+"','"+x.getCurP_FechaInicio()+"','"+x.getCurP_FechaFin()+"','"+x.getCurP_Cupo()+"','"+x.getCurP_CupoDisponible()+"','"+x.getCurP_Lugar()+"','"+x.getCurP_Hora()+"','"+x.getCurP_FK_idEmpleado()+"');";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return r;
        }
    }

    public int Actualizar_Cursos_Publicados(M_VO_Cursos_Publicados x) {
        int r = 0;
        String Actualizar = "UPDATE tblcursospublicados\n" +
            "SET curP_FK_IdCurso = '"+x.getCurP_FK_IdCurso()+"',curP_FechaInicio = '"+x.getCurP_FechaInicio()+"',curP_FechaFin = '"+x.getCurP_FechaFin()+"',\n" +
            "curP_Cupo = '"+x.getCurP_Cupo()+"',curP_CupoDisponible = '"+x.getCurP_CupoDisponible()+"',curP_Lugar = '"+x.getCurP_Lugar()+"',curP_Hora = '"+x.getCurP_Hora()+"',\n" +
            "curP_FK_idEmpleado = '"+x.getCurP_FK_idEmpleado()+"' WHERE curP_Id = '"+x.getCurP_Id()+"';";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }
    public int Eliminar_Cursos(M_VO_Cursos_Publicados x) {
        int r = 0;
        String Delete="DELETE FROM tblcursospublicados WHERE curP_Id = '"+x.getCurP_Id()+"';";
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
