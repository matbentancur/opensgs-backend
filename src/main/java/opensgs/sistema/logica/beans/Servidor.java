package opensgs.sistema.logica.beans;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.logica.servicios.ServicioFechaHora;
import opensgs.sistema.datatypes.DtServidor;
import opensgs.logica.servicios.ServicioPassword;

@MappedSuperclass
public abstract class Servidor extends OpenSGSManagedBean {

    @Column(unique = true, nullable = false, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 128)
    private String nombre;

    @Column(nullable = false)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 128)
    private String servidor;

    @Column(nullable = false)
    @NotNull
    @Positive
    @Max(65535)
    private Integer puerto;

    @Column(nullable = false, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 128)
    private String usuario;

    @Column(nullable = false, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 128)
    private String password;

    public Servidor() {
    }

    public Servidor(DtOpenSGSBean dtOpenSGSBean) {
        this((DtServidor) dtOpenSGSBean);
    }

    public Servidor(DtServidor dtServidor) {
        super();
        this.nombre = dtServidor.getNombre();
        this.servidor = dtServidor.getServidor();
        this.puerto = dtServidor.getPuerto();
        this.usuario = dtServidor.getUsuario();
        this.password = dtServidor.getPassword();
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
    public void modificar(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        DtServidor dtServidor = (DtServidor) dtOpenSGSManagedBean;
        this.nombre = dtServidor.getNombre();
        this.servidor = dtServidor.getServidor();
        this.puerto = dtServidor.getPuerto();
        this.usuario = dtServidor.getUsuario();
        this.password = dtServidor.getPassword();
        this.encriptarPassword();
    }

    public void encriptarPassword() {
        this.password = ServicioPassword.getInstance().encriptarPassword(password, "iSDrn3MFmXTqu8p");
    }

    public void desencriptarPassword() {
        this.password = ServicioPassword.getInstance().desencriptarPassword(password, "iSDrn3MFmXTqu8p");
    }

    public DtServidor getDtServidor() {
        return new DtServidor(
                nombre,
                servidor,
                puerto,
                usuario,
                password,
                this.getId(),
                this.isActivo(),
                this.isBorrado(),
                this.isAdministrable(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getCreacion()),
                ServicioFechaHora.getInstance().dateTimeToString(this.getModificado())
        );
    }

    @Override
    public String toString() {
        return super.toString() + "Servidor{" + "nombre=" + nombre + ", servidor=" + servidor + ", puerto=" + puerto + ", usuario=" + usuario + ", password=" + password + '}';
    }

}
