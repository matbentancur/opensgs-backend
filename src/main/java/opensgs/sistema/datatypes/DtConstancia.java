package opensgs.sistema.datatypes;

import opensgs.datatypes.DtOpenSGSManagedBean;

public final class DtConstancia extends DtOpenSGSManagedBean {

    private String nombre;
    private String constanciaTipo;
    private DtArchivo dtArchivo;
    private DtAplicacion dtAplicacion;

    public DtConstancia() {
    }

    public DtConstancia(String nombre, String constanciaTipo, DtArchivo dtArchivo, DtAplicacion dtAplicacion, boolean activo, boolean borrado, boolean administrable, String modificado, Long id, String creacion) {
        super(activo, borrado, administrable, modificado, id, creacion);
        this.nombre = nombre;
        this.constanciaTipo = constanciaTipo;
        this.dtArchivo = dtArchivo;
        this.dtAplicacion = dtAplicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getConstanciaTipo() {
        return constanciaTipo;
    }

    public void setConstanciaTipo(String constanciaTipo) {
        this.constanciaTipo = constanciaTipo;
    }

    public DtArchivo getDtArchivo() {
        return dtArchivo;
    }

    public void setDtArchivo(DtArchivo dtArchivo) {
        this.dtArchivo = dtArchivo;
    }

    public DtAplicacion getDtAplicacion() {
        return dtAplicacion;
    }

    public void setDtAplicacion(DtAplicacion dtAplicacion) {
        this.dtAplicacion = dtAplicacion;
    }

    @Override
    public String getClassName() {
        return "Constancia";
    }

    @Override
    public String toString() {
        return super.toString() + "DtConstancia{" + "nombre=" + nombre + ", constanciaTipo=" + constanciaTipo + ", dtArchivo=" + dtArchivo + ", dtAplicacion=" + dtAplicacion + '}';
    }

}
