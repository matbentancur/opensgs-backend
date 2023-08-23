package opensgs.sistema.datatypes;

public final class DtServidorCorreo extends DtServidor{

    private String seguridad;
    private String identificacion;
    private String desdeCorreo;
    private String desdeNombre;

    public DtServidorCorreo() {
    }

    public DtServidorCorreo(String seguridad, String identificacion, String desdeCorreo, String desdeNombre, String nombre, String servidor, Integer puerto, String usuario, String password, Long id, boolean activo, boolean borrado, boolean administrable, String creacion, String modificado) {
        super(nombre, servidor, puerto, usuario, password, id, activo, borrado, administrable, creacion, modificado);
        this.seguridad = seguridad;
        this.identificacion = identificacion;
        this.desdeCorreo = desdeCorreo;
        this.desdeNombre = desdeNombre;
    }

    public String getSeguridad() {
        return seguridad;
    }

    public void setSeguridad(String seguridad) {
        this.seguridad = seguridad;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getDesdeCorreo() {
        return desdeCorreo;
    }

    public void setDesdeCorreo(String desdeCorreo) {
        this.desdeCorreo = desdeCorreo;
    }

    public String getDesdeNombre() {
        return desdeNombre;
    }

    public void setDesdeNombre(String desdeNombre) {
        this.desdeNombre = desdeNombre;
    }
    
    @Override
    public String getClassName() {
        return "ServidorCorreo";
    }

    @Override
    public String toString() {
        return super.toString() + "DtServidorCorreo{" + "seguridad=" + seguridad + ", identificacion=" + identificacion + ", desdeCorreo=" + desdeCorreo + ", desdeNombre=" + desdeNombre + '}';
    }

}
