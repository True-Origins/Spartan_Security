
package Modelo.VO;

public class M_VO_Cursos_Publicados {
  private int curP_Id;
  private int curP_FK_IdCurso;

    public int getCurP_Id() {
        return curP_Id;
    }

    public void setCurP_Id(int curP_Id) {
        this.curP_Id = curP_Id;
    }

    public int getCurP_FK_IdCurso() {
        return curP_FK_IdCurso;
    }

    public void setCurP_FK_IdCurso(int curP_FK_IdCurso) {
        this.curP_FK_IdCurso = curP_FK_IdCurso;
    }

    public String getCurP_FechaInicio() {
        return curP_FechaInicio;
    }

    public void setCurP_FechaInicio(String curP_FechaInicio) {
        this.curP_FechaInicio = curP_FechaInicio;
    }

    public String getCurP_FechaFin() {
        return curP_FechaFin;
    }

    public void setCurP_FechaFin(String curP_FechaFin) {
        this.curP_FechaFin = curP_FechaFin;
    }

    public String getCurP_Cupo() {
        return curP_Cupo;
    }

    public void setCurP_Cupo(String curP_Cupo) {
        this.curP_Cupo = curP_Cupo;
    }

    public String getCurP_CupoDisponible() {
        return curP_CupoDisponible;
    }

    public void setCurP_CupoDisponible(String curP_CupoDisponible) {
        this.curP_CupoDisponible = curP_CupoDisponible;
    }

    public String getCurP_Lugar() {
        return curP_Lugar;
    }

    public void setCurP_Lugar(String curP_Lugar) {
        this.curP_Lugar = curP_Lugar;
    }

    public String getCurP_Hora() {
        return curP_Hora;
    }

    public void setCurP_Hora(String curP_Hora) {
        this.curP_Hora = curP_Hora;
    }

    public int getCurP_FK_idEmpleado() {
        return curP_FK_idEmpleado;
    }

    public void setCurP_FK_idEmpleado(int curP_FK_idEmpleado) {
        this.curP_FK_idEmpleado = curP_FK_idEmpleado;
    }
  private String curP_FechaInicio;
  private String curP_FechaFin;
  private String curP_Cupo;
  private String curP_CupoDisponible;
  private String curP_Lugar;
  private String curP_Hora;
  private int curP_FK_idEmpleado;
}
