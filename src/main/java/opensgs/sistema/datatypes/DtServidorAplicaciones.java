package opensgs.sistema.datatypes;

public final class DtServidorAplicaciones extends DtServidor{
    
    private String logPath;

    public DtServidorAplicaciones() {
    }

    public DtServidorAplicaciones(String logPath, String nombre, String servidor, Integer puerto, String usuario, String password, Long id, boolean activo, boolean borrado, boolean administrable, String creacion, String modificado) {
        super(nombre, servidor, puerto, usuario, password, id, activo, borrado, administrable, creacion, modificado);
        this.logPath = logPath;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }
    
    @Override
    public String getClassName() {
        return "ServidorAplicaciones";
    }

    @Override
    public String toString() {
        return super.toString() + "DtServidorAplicaciones{" + "logPath=" + logPath + '}';
    }

}