package opensgs.sistema.datatypes;

public final class DtServidorAutenticacion extends DtServidor{
    
    private String tipo;

    public DtServidorAutenticacion() {
    }

    public DtServidorAutenticacion(String tipo, String nombre, String servidor, Integer puerto, String usuario, String password, Long id, boolean activo, boolean borrado, boolean administrable, String creacion, String modificado) {
        super(nombre, servidor, puerto, usuario, password, id, activo, borrado, administrable, creacion, modificado);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public String getClassName() {
        return "ServidorAutenticacion";
    }

    @Override
    public String toString() {
        return super.toString() + "DtServidorAutenticacion{" + "tipo=" + tipo + '}';
    }

}