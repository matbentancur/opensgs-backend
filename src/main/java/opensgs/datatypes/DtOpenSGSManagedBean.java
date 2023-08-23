package opensgs.datatypes;

public abstract class DtOpenSGSManagedBean extends DtOpenSGSBean {

    private boolean activo;
    private boolean borrado;
    private boolean administrable;
    private String modificado;

    public DtOpenSGSManagedBean() {
    }

    public DtOpenSGSManagedBean(boolean activo, boolean borrado, boolean administrable, String modificado, Long id, String creacion) {
        super(id, creacion);
        this.activo = activo;
        this.borrado = borrado;
        this.administrable = administrable;
        this.modificado = modificado;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public boolean isAdministrable() {
        return administrable;
    }

    public void setAdministrable(boolean administrable) {
        this.administrable = administrable;
    }

    public String getModificado() {
        return modificado;
    }

    public void setModificado(String modificado) {
        this.modificado = modificado;
    }

    @Override
    public String toString() {
        return super.toString() + "DtOpenSGSManagedBean{" + "activo=" + activo + ", borrado=" + borrado + ", administrable=" + administrable + ", modificado=" + modificado + '}';
    }

}
