package opensgs.sistema.datatypes;

public final class DtConstanciaElementoImagen extends DtConstanciaElemento {

    private Float ancho;
    private Float alto;
    private DtArchivo dtArchivo;

    public DtConstanciaElementoImagen() {
    }

    public DtConstanciaElementoImagen(Float ancho, Float alto, DtArchivo dtArchivo, String nombre, Float posicionX, Float posicionY, DtConstancia dtConstancia, boolean activo, boolean borrado, boolean administrable, String modificado, Long id, String creacion) {
        super(nombre, posicionX, posicionY, dtConstancia, activo, borrado, administrable, modificado, id, creacion);
        this.ancho = ancho;
        this.alto = alto;
        this.dtArchivo = dtArchivo;
    }

    public Float getAncho() {
        return ancho;
    }

    public void setAncho(Float ancho) {
        this.ancho = ancho;
    }

    public Float getAlto() {
        return alto;
    }

    public void setAlto(Float alto) {
        this.alto = alto;
    }

    public DtArchivo getDtArchivo() {
        return dtArchivo;
    }

    public void setDtArchivo(DtArchivo dtArchivo) {
        this.dtArchivo = dtArchivo;
    }

    @Override
    public String getClassName() {
        return "ConstanciaElementoImagen";
    }

    @Override
    public String toString() {
        return super.toString() + "DtConstanciaElementoImagen{" + "ancho=" + ancho + ", alto=" + alto + ", dtArchivo=" + dtArchivo + '}';
    }

}
