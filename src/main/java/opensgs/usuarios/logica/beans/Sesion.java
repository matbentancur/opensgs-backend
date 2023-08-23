package opensgs.usuarios.logica.beans;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import opensgs.enums.Elemento;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.servicios.ServicioFechaHora;
import opensgs.usuarios.datatypes.DtSesion;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(
        name = "sesion",
        schema = "usuarios"
)
public class Sesion extends OpenSGSBean {

    @Column(nullable = false, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 128, max = 128)
    private String codigo;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    @NotNull
    private Usuario usuario;

    public Sesion() {
    }

    public Sesion(Usuario usuario) {
        this.codigo = RandomStringUtils.randomAlphanumeric(128);
        this.usuario = usuario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public DtSesion getDtSesion() {
        return new DtSesion(
                codigo,
                usuario.getDtUsuarioSesion(),
                this.getId(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getCreacion())
        );
    }

    @Override
    public DtSesion getDtOpenSGSBean() {
        return this.getDtSesion();
    }

    @Override
    public DtSesion getDataType() {
        return this.getDtSesion();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.Sesion;
    }

    @Override
    public String toString() {
        return "Sesion{" + "codigo=" + codigo + ", usuario=" + usuario + '}';
    }

}
