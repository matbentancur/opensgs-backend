package opensgs.sistema.datatypes;

import opensgs.datatypes.DtOpenSGSManagedBean;

public class DtAnuncio extends DtOpenSGSManagedBean {

    private String nombre;
    private String texto;
    private String inicio;
    private String fin;
    private String alcance;

    public DtAnuncio() {
    }

    public DtAnuncio(String nombre, String texto, String inicio, String fin, String alcance, boolean activo, boolean borrado, boolean administrable, String modificado, Long id, String creacion) {
        super(activo, borrado, administrable, modificado, id, creacion);
        this.nombre = nombre;
        this.texto = texto;
        this.inicio = inicio;
        this.fin = fin;
        this.alcance = alcance;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    @Override
    public String getClassName() {
        return "Anuncio";
    }

    @Override
    public String toString() {
        return super.toString() + "DtAnuncio{" + "nombre=" + nombre + ", texto=" + texto + ", inicio=" + inicio + ", fin=" + fin + ", alcance=" + alcance + '}';
    }

}
