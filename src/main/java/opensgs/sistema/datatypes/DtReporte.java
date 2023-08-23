package opensgs.sistema.datatypes;

import opensgs.datatypes.DtOpenSGSManagedBean;

public final class DtReporte extends DtOpenSGSManagedBean {

    private String nombre;
    private String cabezal;
    private String sentenciaSQL;
    private String alcance;

    public DtReporte() {
    }

    public DtReporte(String nombre, String cabezal, String sentenciaSQL, String alcance, boolean activo, boolean borrado, boolean administrable, String modificado, Long id, String creacion) {
        super(activo, borrado, administrable, modificado, id, creacion);
        this.nombre = nombre;
        this.cabezal = cabezal;
        this.sentenciaSQL = sentenciaSQL;
        this.alcance = alcance;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCabezal() {
        return cabezal;
    }

    public void setCabezal(String cabezal) {
        this.cabezal = cabezal;
    }

    public String getSentenciaSQL() {
        return sentenciaSQL;
    }

    public void setSentenciaSQL(String sentenciaSQL) {
        this.sentenciaSQL = sentenciaSQL;
    }

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    @Override
    public String getClassName() {
        return "Reporte";
    }

    @Override
    public String toString() {
        return super.toString() + "DtReporte{" + "nombre=" + nombre + ", cabezal=" + cabezal + ", sentenciaSQL=" + sentenciaSQL + ", alcance=" + alcance + '}';
    }

}
