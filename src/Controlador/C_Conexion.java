package Controlador;
import Modelo.DAO.M_DAO_Conexion;
import Vista.V_Conexion;
import Vista.V_Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class C_Conexion implements ActionListener {
    M_DAO_Conexion Modelo_Conectar; // MODELO conexion
    V_Conexion frm_datos; // VISTA de formularo datos servidor

    public C_Conexion(M_DAO_Conexion Modelo_Conectar, V_Conexion frm_datos) {
        this.Modelo_Conectar = Modelo_Conectar; // Instancia el MODELO.
        this.frm_datos = frm_datos; // Instancia el FORMULARIO Datos del servidor.
        this.frm_datos.txt_base.addActionListener(this);  // Agrega accion del boton
        this.frm_datos.txt_pass.addActionListener(this);  // Agrega accion del boton
        this.frm_datos.txt_user.addActionListener(this);  // Agrega accion del boton
        this.frm_datos.btnconectar.addActionListener(this); // Agrega accion del boton
        this.frm_datos.btncancelar.addActionListener(this); // Agrega accion del boton
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frm_datos.btnconectar) {
            try {
                Conectar();
            } catch (IOException ex) {
                Logger.getLogger(C_Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == frm_datos.btncancelar) { 
            System.exit(0);
        }
    }

    public void Conectar() throws IOException {
        if ("".equals(frm_datos.txt_user.getText()) || "".equals(frm_datos.txt_pass.getText()) || "".equals(frm_datos.txt_base.getText())) {

            JOptionPane.showMessageDialog(null, "Por favor llenar los campos vacios");
        } else {
            
            Modelo_Conectar.crearArchivo(frm_datos.txt_user.getText(), frm_datos.txt_pass.getText(), frm_datos.txt_base.getText());
            frm_datos.dispose();
            Modelo_Conectar.leerArchivo();

            M_DAO_Conexion Modelo_I = new M_DAO_Conexion();
            V_Login Login_I = new V_Login();
            V_Conexion Datos_server_I = new V_Conexion();
            C_Controlador Ctrl = new C_Controlador(Modelo_I, Login_I, Datos_server_I);

            Ctrl.Iniciar();
        }

    }

    public void Conectar2() {
        Modelo_Conectar.leerArchivo();
    }
}
