package Controlador;
import Modelo.DAO.M_DAO_Conexion;
import Modelo.DAO.M_DAO_Login;
import Modelo.VO.M_VO_Login;
import Vista.V_Conexion;
import Vista.V_Login;
import java.io.IOException;
public class C_Controlador {
 //MODELOS
    M_DAO_Conexion Modelo_conexion;
    M_DAO_Login Modelo_Login;

    V_Login Login;
    V_Conexion Datos_server;

    public C_Controlador(M_DAO_Conexion Modelo_conexion, V_Login Login, V_Conexion Datos_server) {
        this.Modelo_conexion = Modelo_conexion;
        this.Login = Login;
        this.Datos_server = Datos_server;
    }

    public static void main(String args[]) throws IOException {
        M_DAO_Conexion Modelo_I = new M_DAO_Conexion();
        V_Login Login_I = new V_Login();
        V_Conexion Datos_server_I = new V_Conexion();
        C_Controlador Ctrl = new C_Controlador(Modelo_I, Login_I, Datos_server_I);

        Ctrl.Iniciar();

    }

    public void Iniciar() throws IOException {
        Datos_server.setTitle("Datos del Servidor"); //Agra el titulo

        Datos_server.setLocationRelativeTo(null); // Agrega posisicon

        Modelo_conexion.conexion(); // Invoca el medoto conexion del MODELO -- Recibe el formulario de datos servidor.

        if (Modelo_conexion.getState() == true) {
            Modelo_Login = new M_DAO_Login();
            M_VO_Login vo_login = new M_VO_Login();
            C_Login Abre = new C_Login(Login, Modelo_Login, vo_login);
            Login.setVisible(true);
        }

        if (Modelo_conexion.getState() == false) {

            C_Conexion DS = new C_Conexion(Modelo_conexion, Datos_server);

            Datos_server.setVisible(true);

        }
    }
    
}
