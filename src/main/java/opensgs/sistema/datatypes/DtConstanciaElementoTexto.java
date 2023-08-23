package opensgs.sistema.datatypes;

public final class DtConstanciaElementoTexto extends DtConstanciaElemento {

    private String texto;
    private Float fuenteTamanio;
    private String fuenteTipo;

    public DtConstanciaElementoTexto() {
    }

    public DtConstanciaElementoTexto(String texto, Float fuenteTamanio, String fuenteTipo, String nombre, Float posicionX, Float posicionY, DtConstancia dtConstancia, boolean activo, boolean borrado, boolean administrable, String modificado, Long id, String creacion) {
        super(nombre, posicionX, posicionY, dtConstancia, activo, borrado, administrable, modificado, id, creacion);
        this.texto = texto;
        this.fuenteTamanio = fuenteTamanio;
        this.fuenteTipo = fuenteTipo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Float getFuenteTamanio() {
        return fuenteTamanio;
    }

    public void setFuenteTamanio(Float fuenteTamanio) {
        this.fuenteTamanio = fuenteTamanio;
    }

    public String getFuenteTipo() {
        return fuenteTipo;
    }

    public void setFuenteTipo(String fuenteTipo) {
        this.fuenteTipo = fuenteTipo;
    }

    @Override
    public String getClassName() {
        return "ConstanciaElementoTexto";
    }

    @Override
    public String toString() {
        return super.toString() + "DtConstanciaElementoTexto{" + "texto=" + texto + ", fuenteTamanio=" + fuenteTamanio + ", fuenteTipo=" + fuenteTipo + '}';
    }

}
