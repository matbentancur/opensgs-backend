package opensgs.sistema.datatypes;

import opensgs.datatypes.DtOpenSGSBean;
import opensgs.usuarios.datatypes.DtUsuario;

public final class DtActividad extends DtOpenSGSBean {

    private String operacion;
    private String elemento;
    private Long identificador;
    private String descripcion;
    private DtUsuario dtUsuario;

    public DtActividad() {
    }

    public DtActividad(String operacion, String elemento, Long identificador, String descripcion, DtUsuario dtUsuario, Long id, String creacion) {
        super(id, creacion);
        this.operacion = operacion;
        this.elemento = elemento;
        this.identificador = identificador;
        this.descripcion = descripcion;
        this.dtUsuario = dtUsuario;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public Long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public DtUsuario getDtUsuario() {
        return dtUsuario;
    }

    public void setDtUsuario(DtUsuario dtUsuario) {
        this.dtUsuario = dtUsuario;
    }

    @Override
    public String getClassName() {
        return "Actividad";
    }

    @Override
    public String toString() {
        return super.toString() + "DtActividad{" + "operacion=" + operacion + ", elemento=" + elemento + ", identificador=" + identificador + ", descripcion=" + descripcion + ", dtUsuario=" + dtUsuario + '}';
    }

}
