package Modelo.VO;

public class M_VO_Servicios {
    private int serv_Id;
    private String serv_Nombre;
    private String serv_Descripcion;
    private float serv_Precio;
    private int serv_FK_idTipoServ;

    public int getServ_Id() {
        return serv_Id;
    }

    public void setServ_Id(int serv_Id) {
        this.serv_Id = serv_Id;
    }

    public String getServ_Nombre() {
        return serv_Nombre;
    }

    public void setServ_Nombre(String serv_Nombre) {
        this.serv_Nombre = serv_Nombre;
    }

    public String getServ_Descripcion() {
        return serv_Descripcion;
    }

    public void setServ_Descripcion(String serv_Descripcion) {
        this.serv_Descripcion = serv_Descripcion;
    }

    public float getServ_Precio() {
        return serv_Precio;
    }

    public void setServ_Precio(float serv_Precio) {
        this.serv_Precio = serv_Precio;
    }

    public int getServ_FK_idTipoServ() {
        return serv_FK_idTipoServ;
    }

    public void setServ_FK_idTipoServ(int serv_FK_idTipoServ) {
        this.serv_FK_idTipoServ = serv_FK_idTipoServ;
    }
    
}
