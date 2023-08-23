package opensgs.sistema.datatypes;

import opensgs.datatypes.DtOpenSGSManagedBean;

public abstract class DtConstanciaElemento extends DtOpenSGSManagedBean {

    private String nombre;
    private Float posicionX;
    private Float posicionY;
    private DtConstancia dtConstancia;

    public DtConstanciaElemento() {
    }

    public DtConstanciaElemento(String nombre, Float posicionX, Float posicionY, DtConstancia dtConstancia, boolean activo, boolean borrado, boolean administrable, String modificado, Long id, String creacion) {
        super(activo, borrado, administrable, modificado, id, creacion);
        this.nombre = nombre;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.dtConstancia = dtConstancia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(Float posicionX) {
        this.posicionX = posicionX;
    }

    public Float getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(Float posicionY) {
        this.posicionY = posicionY;
    }

    public DtConstancia getDtConstancia() {
        return dtConstancia;
    }

    public void setDtConstancia(DtConstancia dtConstancia) {
        this.dtConstancia = dtConstancia;
    }

    @Override
    public String toString() {
        return super.toString() + "DtConstanciaElemento{" + "nombre=" + nombre + ", posicionX=" + posicionX + ", posicionY=" + posicionY + ", dtConstancia=" + dtConstancia + '}';
    }

}
