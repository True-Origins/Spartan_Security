package Modelo.VO;

public class M_VO_Empleado {
    private int emp_Id;
    private String emp_Nombre;
    private String emp_ApellidoP;
    private String emp_ApellidoM;
    private String emp_Telefono;
    private String emp_Direccion;
    private String emp_Correo;
    private int emp_FK_Puesto;
    private int emp_FK_Turno;
    private int emp_FK_idSucursal;
    private String emp_Contrasena;

    public int getEmp_Id() {
        return emp_Id;
    }

    public void setEmp_Id(int emp_Id) {
        this.emp_Id = emp_Id;
    }

    public String getEmp_Nombre() {
        return emp_Nombre;
    }

    public void setEmp_Nombre(String emp_Nombre) {
        this.emp_Nombre = emp_Nombre;
    }

    public String getEmp_ApellidoP() {
        return emp_ApellidoP;
    }

    public void setEmp_ApellidoP(String emp_ApellidoP) {
        this.emp_ApellidoP = emp_ApellidoP;
    }

    public String getEmp_ApellidoM() {
        return emp_ApellidoM;
    }

    public void setEmp_ApellidoM(String emp_ApellidoM) {
        this.emp_ApellidoM = emp_ApellidoM;
    }

    public String getEmp_Telefono() {
        return emp_Telefono;
    }

    public void setEmp_Telefono(String emp_Telefono) {
        this.emp_Telefono = emp_Telefono;
    }

    public String getEmp_Direccion() {
        return emp_Direccion;
    }

    public void setEmp_Direccion(String emp_Direccion) {
        this.emp_Direccion = emp_Direccion;
    }

    public String getEmp_Correo() {
        return emp_Correo;
    }

    public void setEmp_Correo(String emp_Correo) {
        this.emp_Correo = emp_Correo;
    }

    public int getEmp_FK_Puesto() {
        return emp_FK_Puesto;
    }

    public void setEmp_FK_Puesto(int emp_FK_Puesto) {
        this.emp_FK_Puesto = emp_FK_Puesto;
    }

    public int getEmp_FK_Turno() {
        return emp_FK_Turno;
    }

    public void setEmp_FK_Turno(int emp_FK_Turno) {
        this.emp_FK_Turno = emp_FK_Turno;
    }

    public int getEmp_FK_idSucursal() {
        return emp_FK_idSucursal;
    }

    public void setEmp_FK_idSucursal(int emp_FK_idSucursal) {
        this.emp_FK_idSucursal = emp_FK_idSucursal;
    }

    public String getEmp_Contrasena() {
        return emp_Contrasena;
    }

    public void setEmp_Contrasena(String emp_Contrasena) {
        this.emp_Contrasena = emp_Contrasena;
    }
    
}
