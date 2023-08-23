package opensgs.solicitudes.paginas.datatypes.cep;

import opensgs.sistema.datatypes.DtPaginaAplicacion;
import opensgs.solicitudes.datatypes.DtPaginaSolicitud;

public class DtEducacionCEP extends DtPaginaSolicitud {

    private String ultimoNivelEducativo;
    private String tituloGrado;
    private Integer anioRecibio;
    private String institucionTitulo;

    public DtEducacionCEP() {
    }

    public DtEducacionCEP(String ultimoNivelEducativo, String tituloGrado, Integer anioRecibio, String institucionTitulo, DtPaginaAplicacion dtPaginaAplicacion, boolean activo, boolean borrado, boolean administrable, String modificado, Long id, String creacion) {
        super(dtPaginaAplicacion, activo, borrado, administrable, modificado, id, creacion);
        this.ultimoNivelEducativo = ultimoNivelEducativo;
        this.tituloGrado = tituloGrado;
        this.anioRecibio = anioRecibio;
        this.institucionTitulo = institucionTitulo;
    }

    public String getUltimoNivelEducativo() {
        return ultimoNivelEducativo;
    }

    public void setUltimoNivelEducativo(String ultimoNivelEducativo) {
        this.ultimoNivelEducativo = ultimoNivelEducativo;
    }

    public String getTituloGrado() {
        return tituloGrado;
    }

    public void setTituloGrado(String tituloGrado) {
        this.tituloGrado = tituloGrado;
    }

    public Integer getAnioRecibio() {
        return anioRecibio;
    }

    public void setAnioRecibio(Integer anioRecibio) {
        this.anioRecibio = anioRecibio;
    }

    public String getInstitucionTitulo() {
        return institucionTitulo;
    }

    public void setInstitucionTitulo(String institucionTitulo) {
        this.institucionTitulo = institucionTitulo;
    }

    @Override
    public String getClassName() {
        return "EducacionCEP";
    }

    @Override
    public String toString() {
        return super.toString() + "DtEducacionCEP{" + "ultimoNivelEducativo=" + ultimoNivelEducativo + ", tituloGrado=" + tituloGrado + ", anioRecibio=" + anioRecibio + ", institucionTitulo=" + institucionTitulo + '}';
    }

}
