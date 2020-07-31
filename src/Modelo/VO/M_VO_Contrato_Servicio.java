package Modelo.VO;

public class M_VO_Contrato_Servicio {
    private int contS_Id;
    private String contS_FechaContrato;
    private int contS_FK_idServicio;
    private int contS_FK_idClienteEmpresa;
    private int ontS_FK_idMedioPago;

    public int getContS_Id() {
        return contS_Id;
    }

    public void setContS_Id(int contS_Id) {
        this.contS_Id = contS_Id;
    }

    public String getContS_FechaContrato() {
        return contS_FechaContrato;
    }

    public void setContS_FechaContrato(String contS_FechaContrato) {
        this.contS_FechaContrato = contS_FechaContrato;
    }

    public int getContS_FK_idServicio() {
        return contS_FK_idServicio;
    }

    public void setContS_FK_idServicio(int contS_FK_idServicio) {
        this.contS_FK_idServicio = contS_FK_idServicio;
    }

    public int getContS_FK_idClienteEmpresa() {
        return contS_FK_idClienteEmpresa;
    }

    public void setContS_FK_idClienteEmpresa(int contS_FK_idClienteEmpresa) {
        this.contS_FK_idClienteEmpresa = contS_FK_idClienteEmpresa;
    }

    public int getOntS_FK_idMedioPago() {
        return ontS_FK_idMedioPago;
    }

    public void setOntS_FK_idMedioPago(int ontS_FK_idMedioPago) {
        this.ontS_FK_idMedioPago = ontS_FK_idMedioPago;
    }
    

}
