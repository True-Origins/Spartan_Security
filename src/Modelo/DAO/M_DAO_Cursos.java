
package Modelo.DAO;

import Modelo.VO.M_VO_Cursos;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class M_DAO_Cursos {
    M_DAO_Conexion conec = new M_DAO_Conexion();
    private Connection cnn;

    public ResultSet Consulta_Cursos(String opcion) {
        cnn = conec.conexion();
        ResultSet rs = null;
        String consulta = " ";
        if ("Cursos".equals(opcion)) {
            consulta = "SELECT cur_Id, cur_Nombre, cur_Descripcion, cur_DuracionDias, cur_Precio, cur_FK_categoria, cur_Activo, curC_Nombre FROM tblcursos\n" +
"		INNER JOIN tblcursoscategoria 	ON tblcursos.cur_FK_categoria=tblcursoscategoria.curC_Id WHERE cur_Activo='1'";
        }

        if ("Categoria".equals(opcion)) {
            consulta = "SELECT * FROM tblcursoscategoria;";
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
    
    public ResultSet Consulta_CursosInactivos(String opcion) {
        cnn = conec.conexion();
        ResultSet rs = null;
        String consulta = " ";
        if ("Cursos".equals(opcion)) {
            consulta = "SELECT cur_Id, cur_Nombre, cur_Descripcion, cur_DuracionDias, cur_Precio, cur_FK_categoria, cur_Activo, curC_Nombre FROM tblcursos\n" +
"		INNER JOIN tblcursoscategoria 	ON tblcursos.cur_FK_categoria=tblcursoscategoria.curC_Id WHERE cur_Activo='0'";
        }

        if ("Categoria".equals(opcion)) {
            consulta = "SELECT * FROM tblcursoscategoria;";
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
        String consulta = "SELECT cur_Id, cur_FK_categoria FROM tblcursos \n" +
            "INNER JOIN tblcursoscategoria ON tblcursos.cur_FK_categoria=tblcursoscategoria.curC_Id \n" +
            "WHERE tblcursos.cur_Id='"+ids+"'";
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
    public int Insertar_Cursos(M_VO_Cursos x) {
        int r = 0;
        String Insert="INSERT INTO tblcursos (cur_Nombre, cur_Descripcion, cur_DuracionDias, cur_Precio, cur_FK_categoria, cur_Activo)\n" +
                      "VALUES ('"+x.getCur_Nombre()+"','"+x.getCur_Descripcion()+"','"+x.getCur_DuracionDias()+"',\n" +
                      "'"+x.getCur_Precio()+"','"+x.getCur_FK_categoria()+"','1');";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Insert);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return r;
        }
    }

    public int Actualizar_Cursos(M_VO_Cursos x) {
        int r = 0;
        String Actualizar = "UPDATE tblcursos SET cur_Nombre = '"+x.getCur_Nombre()+"',cur_Descripcion = '"+x.getCur_Descripcion()+"',\n" +
        "  cur_DuracionDias = '"+x.getCur_DuracionDias()+"',cur_Precio = '"+x.getCur_Precio()+"',cur_FK_categoria = '"+x.getCur_FK_categoria()+"',\n" +
        "  cur_Activo = '1' WHERE cur_Id = '"+x.getCur_Id()+"';";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }
    
    public int Activar_Cursos(M_VO_Cursos x) {
        int r = 0;
        String Actualizar = "UPDATE tblcursos SET cur_Activo = '1' WHERE cur_Id = '"+x.getCur_Id()+"';";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }
    
    public int Desactivar_Cursos(M_VO_Cursos x) {
        int r = 0;
        String Actualizar = "UPDATE tblcursos SET cur_Activo = '0' WHERE cur_Id = '"+x.getCur_Id()+"';";
        try {
            Statement stm = (Statement) cnn.createStatement();
            r = stm.executeUpdate(Actualizar);
        } catch (Exception e) {
            System.out.println(e);
        } finally {

            return r;
        }
    }

    public int Eliminar_Cursos(M_VO_Cursos x) {
        int r = 0;
        String Delete="DELETE FROM tblcursos WHERE cur_Id = '"+x.getCur_Id()+"';";
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
