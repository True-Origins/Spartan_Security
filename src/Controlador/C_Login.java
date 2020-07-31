
package Controlador;

import Modelo.DAO.M_DAO_Login;
import Modelo.VO.M_VO_Login;
import Vista.V_Principal;
import Vista.V_Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class C_Login implements ActionListener{
    V_Login Vista_login;
    M_DAO_Login Modelo_login;

    V_Principal MDI;
    C_Principal ctrl_mdi;
    M_VO_Login vo_login;

    private String  nick=null, contra=null;

    public C_Login(V_Login Vista_login, M_DAO_Login Modelo_login,M_VO_Login vo_login) {
        this.Vista_login = Vista_login;
        this.Modelo_login = Modelo_login;
        this.vo_login = vo_login;
        this.Vista_login.Btn_Start.addActionListener(this);
        this.Vista_login.btn_Exit.addActionListener(this);
        this.Vista_login.txt_pass.addActionListener(this);
        this.Vista_login.txt_User.addActionListener(this);
    }

    public void Logeo() {

        String Pass = Vista_login.txt_pass.getText();
        String User = Vista_login.txt_User.getText();
        
        vo_login.setPass(Pass);
        vo_login.setUser(User);
        if ("".equals(User) || "".equals(Pass)) {
            JOptionPane.showMessageDialog(null, "Campos vacios");
        } else {

            try {
                ResultSet rs = Modelo_login.Iniciar_sesion(vo_login);

                if (rs.next()) {

   
                    nick = rs.getString("emp_Nombre");
                    contra = rs.getString("emp_Contrasena");
                    JOptionPane.showMessageDialog(null, "Bienvenido ");
                    
                    MDI = new V_Principal();
                    
                    MDI.setExtendedState(MDI.MAXIMIZED_BOTH);
         
                    ctrl_mdi = new C_Principal(MDI);
                   
                    MDI.setVisible(true);
                    try {
                        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                            if ("Windows".equals(info.getName())) {
                                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                break;
                            }
                        }
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(V_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                    Vista_login.dispose();

                } else {
                    JOptionPane.showMessageDialog(null, "No Existe nungun usuaer");
                }

            } catch (Exception e) {
               System.out.println("errorLOG: " + e);
            }

        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Vista_login.Btn_Start) {
            Logeo();
        }

        if (e.getSource() == Vista_login.btn_Exit) {
            System.exit(0);
            JOptionPane.showMessageDialog(null, "Datos: ");
        }

    }
}
