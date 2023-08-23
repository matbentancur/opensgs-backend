package opensgs.sistema.datatypes;

import opensgs.datatypes.DtOpenSGSManagedBean;

public final class DtPlantillaCorreo extends DtOpenSGSManagedBean{
    
    private String nombre;
    private String asunto;
    private String texto;
    private String adjunto;

    public DtPlantillaCorreo() {
    }

    public DtPlantillaCorreo(String nombre, String asunto, String texto, String adjunto, Long id, boolean activo, boolean borrado, boolean administrable, String creacion, String modificado) {
        super(activo, borrado, administrable, modificado, id, creacion);
        this.nombre = nombre;
        this.asunto = asunto;
        this.texto = texto;
        this.adjunto = adjunto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(String adjunto) {
        this.adjunto = adjunto;
    }
    
    @Override
    public String getClassName() {
        return "PlantillaCorreo";
    }

    @Override
    public String toString() {
        return super.toString() + "DtPlantillaCorreo{" + "nombre=" + nombre + ", asunto=" + asunto + ", texto=" + texto + ", adjunto=" + adjunto + '}';
    }
    
}
