package opensgs.sistema.datatypes;

import opensgs.datatypes.DtOpenSGSManagedBean;

public class DtServidor extends DtOpenSGSManagedBean {

    private String nombre;
    private String servidor;
    private Integer puerto;
    private String usuario;
    private String password;

    public DtServidor() {
    }

    public DtServidor(String nombre, String servidor, Integer puerto, String usuario, String password, Long id, boolean activo, boolean borrado, boolean administrable, String creacion, String modificado) {
        super(activo, borrado, administrable, modificado, id, creacion);
        this.nombre = nombre;
        this.servidor = servidor;
        this.puerto = puerto;
        this.usuario = usuario;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public Integer getPuerto() {
        return puerto;
    }

    public void setPuerto(Integer puerto) {
        this.puerto = puerto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getClassName() {
        return "Servidor";
    }

    @Override
    public String toString() {
        return super.toString() + "DtServidor{" + "nombre=" + nombre + ", servidor=" + servidor + ", puerto=" + puerto + ", usuario=" + usuario + ", password=" + password + '}';
    }

}
