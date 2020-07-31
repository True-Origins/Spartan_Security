
package Modelo.DAO;

import Modelo.VO.M_VO_Login;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
public class M_DAO_Login {
    M_DAO_Conexion conec = new M_DAO_Conexion();

    private Connection cnn;

    public ResultSet Iniciar_sesion(M_VO_Login x) {
        cnn = conec.conexion();
        ResultSet rs = null;
        String consulta="SELECT * FROM tblempleado WHERE emp_Nombre='"+x.getUser()+"' AND emp_Contrasena='"+x.getPass()+"';";
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
}
