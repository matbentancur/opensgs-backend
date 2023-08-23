package opensgs.sistema.datatypes;

import opensgs.datatypes.DtOpenSGSManagedBean;

public final class DtArchivo extends DtOpenSGSManagedBean {

    private String nombre;
    private String titulo;
    private String mime;
    private String extension;
    private Long peso;
    private String ubicacion;
    private String alcanceArchivo;

    public DtArchivo() {
    }

    public DtArchivo(String nombre, String titulo, String mime, String extension, Long peso, String ubicacion, String alcanceArchivo, boolean activo, boolean borrado, boolean administrable, String modificado, Long id, String creacion) {
        super(activo, borrado, administrable, modificado, id, creacion);
        this.nombre = nombre;
        this.titulo = titulo;
        this.mime = mime;
        this.extension = extension;
        this.peso = peso;
        this.ubicacion = ubicacion;
        this.alcanceArchivo = alcanceArchivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Long getPeso() {
        return peso;
    }

    public void setPeso(Long peso) {
        this.peso = peso;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getAlcanceArchivo() {
        return alcanceArchivo;
    }

    public void setAlcanceArchivo(String alcanceArchivo) {
        this.alcanceArchivo = alcanceArchivo;
    }

    @Override
    public String getClassName() {
        return "Archivo";
    }

    @Override
    public String toString() {
        return super.toString() + "DtArchivo{" + "nombre=" + nombre + ", titulo=" + titulo + ", mime=" + mime + ", extension=" + extension + ", peso=" + peso + ", ubicacion=" + ubicacion + ", alcanceArchivo=" + alcanceArchivo + '}';
    }

}
