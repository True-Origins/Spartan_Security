package Modelo.DAO;

import Controlador.C_Controlador;
import Controlador.C_Login;
import Vista.V_Conexion;
import Vista.V_Login;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class M_DAO_Conexion {
    private static Connection conect = null;
    private static Boolean state = false;

    public Boolean getState() {
        return state;
    }
    private static String cade = "";
    private static String usuario = "";
    private static String passwor = "";

    public static Connection conexion() {

        if (conect == null) {
             leerArchivo();
        }

        return conect;
    }
    public void crearArchivo(String usu, String con, String basedatos) {
        FileWriter flwriter = null;
        try {
            flwriter = new FileWriter("src\\servidor.txt");
            BufferedWriter bfwriter = new BufferedWriter(flwriter);
            bfwriter.write("jdbc:mysql://localhost/" + basedatos + "," + usu + "," + con);
            bfwriter.close();
            System.out.println("Archivo creado satisfactoriamente..");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (flwriter != null) {
                try {
                    flwriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static boolean leerArchivo() {
        File file = new File("src\\servidor.txt");
        if (file.exists() == true) {
            Scanner scanner;
            try {
                scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String linea = scanner.nextLine();
                    Scanner delimitar = new Scanner(linea);			
                    delimitar.useDelimiter("\\s*,\\s*");
                    cade = delimitar.next();
                    usuario = delimitar.next();
                    passwor = delimitar.next();
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                Class.forName("org.gjt.mm.mysql.Driver");
                conect = DriverManager.getConnection(cade, usuario, passwor);

                state = true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error9: " + e);
                System.out.println("err: " + e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se localizo el Archivo con los datos del servidor..");
        }
        return state;
    }    
}
