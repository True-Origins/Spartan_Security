package Modelo.VO;

public class M_VO_Contrato_Empleados {
    private int contemp_Id;
    private int contemp_FK_idContratoServicio;
    private int contemp_FK_idEmpleado;

    public int getContemp_Id() {
        return contemp_Id;
    }

    public void setContemp_Id(int contemp_Id) {
        this.contemp_Id = contemp_Id;
    }

    public int getContemp_FK_idContratoServicio() {
        return contemp_FK_idContratoServicio;
    }

    public void setContemp_FK_idContratoServicio(int contemp_FK_idContratoServicio) {
        this.contemp_FK_idContratoServicio = contemp_FK_idContratoServicio;
    }

    public int getContemp_FK_idEmpleado() {
        return contemp_FK_idEmpleado;
    }

    public void setContemp_FK_idEmpleado(int contemp_FK_idEmpleado) {
        this.contemp_FK_idEmpleado = contemp_FK_idEmpleado;
    }
    
}
