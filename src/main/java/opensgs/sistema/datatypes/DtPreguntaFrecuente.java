package opensgs.sistema.datatypes;

import opensgs.datatypes.DtOpenSGSManagedBean;

public final class DtPreguntaFrecuente extends DtOpenSGSManagedBean {

    private String nombre;
    private Integer orden;
    private String pregunta;
    private String respuesta;
    private String alcance;

    public DtPreguntaFrecuente() {
    }

    public DtPreguntaFrecuente(String nombre, Integer orden, String pregunta, String respuesta, String alcance, boolean activo, boolean borrado, boolean administrable, String modificado, Long id, String creacion) {
        super(activo, borrado, administrable, modificado, id, creacion);
        this.nombre = nombre;
        this.orden = orden;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.alcance = alcance;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    @Override
    public String getClassName() {
        return "PreguntaFrecuente";
    }

    @Override
    public String toString() {
        return super.toString() + "DtPreguntaFrecuente{" + "nombre=" + nombre + ", orden=" + orden + ", pregunta=" + pregunta + ", respuesta=" + respuesta + ", alcance=" + alcance + '}';
    }

}
