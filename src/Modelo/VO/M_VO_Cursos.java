
package Modelo.VO;

public class M_VO_Cursos {
    private int cur_Id;
    private String cur_Nombre;
    private String cur_Descripcion;
    private int cur_DuracionDias;
    private float cur_Precio;
    private int cur_FK_categoria;
    private int cur_Activo;

    public int getCur_Id() {
        return cur_Id;
    }

    public void setCur_Id(int cur_Id) {
        this.cur_Id = cur_Id;
    }

    public String getCur_Nombre() {
        return cur_Nombre;
    }

    public void setCur_Nombre(String cur_Nombre) {
        this.cur_Nombre = cur_Nombre;
    }

    public String getCur_Descripcion() {
        return cur_Descripcion;
    }

    public void setCur_Descripcion(String cur_Descripcion) {
        this.cur_Descripcion = cur_Descripcion;
    }

    public int getCur_DuracionDias() {
        return cur_DuracionDias;
    }

    public void setCur_DuracionDias(int cur_DuracionDias) {
        this.cur_DuracionDias = cur_DuracionDias;
    }

    public float getCur_Precio() {
        return cur_Precio;
    }

    public void setCur_Precio(float cur_Precio) {
        this.cur_Precio = cur_Precio;
    }

    public int getCur_FK_categoria() {
        return cur_FK_categoria;
    }

    public void setCur_FK_categoria(int cur_FK_categoria) {
        this.cur_FK_categoria = cur_FK_categoria;
    }

    public int getCur_Activo() {
        return cur_Activo;
    }

    public void setCur_Activo(int cur_Activo) {
        this.cur_Activo = cur_Activo;
    }
    
    
}
