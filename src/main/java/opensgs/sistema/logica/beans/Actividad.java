package opensgs.sistema.logica.beans;

import opensgs.enums.Operacion;
import opensgs.enums.Elemento;
import opensgs.usuarios.logica.beans.Usuario;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.servicios.ServicioEnum;
import opensgs.logica.servicios.ServicioFechaHora;
import opensgs.sistema.datatypes.DtActividad;
import opensgs.usuarios.persistencia.manejadores.ManejadorUsuario;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(
        name = "actividad",
        schema = "sistema"
)
public class Actividad extends OpenSGSBean {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 128)
    @NotNull
    private Operacion operacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 128)
    @NotNull
    private Elemento elemento;

    @Column(nullable = false)
    @NotNull
    @Positive
    private Long identificador;

    @Column(nullable = false, columnDefinition = "varchar")
    @NotNull
    @Size(min = 1)
    private String descripcion;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    @NotNull
    private Usuario usuario;

    public Actividad() {
    }

    public Actividad(DtOpenSGSBean dtOpenSGSBean) {
        this((DtActividad) dtOpenSGSBean);
    }

    public Actividad(DtActividad dtActividad) {
        super();
        this.operacion = ServicioEnum.getInstance().parseEnum(Operacion.class, dtActividad.getOperacion());
        this.elemento = ServicioEnum.getInstance().parseEnum(Elemento.class, dtActividad.getElemento());
        this.identificador = dtActividad.getIdentificador();
        this.descripcion = dtActividad.getDescripcion();
        this.usuario = ManejadorUsuario.getInstance().obtenerUsuario(dtActividad.getDtUsuario());
    }

    public Actividad(Operacion operacion, OpenSGSBean openSGSBean, Usuario usuario) {
        super();
        this.operacion = operacion;
        this.elemento = openSGSBean.getOpenSGSElemento();
        this.identificador = openSGSBean.getId();
        this.descripcion = openSGSBean.toString();
        this.usuario = usuario;
    }

    public Actividad(Operacion operacion, OpenSGSBean openSGSBean, String descripcion, Usuario usuario) {
        super();
        this.operacion = operacion;
        this.elemento = openSGSBean.getOpenSGSElemento();
        this.identificador = openSGSBean.getId();
        this.descripcion = descripcion;
        this.usuario = usuario;
    }

    public Operacion getOperacion() {
        return operacion;
    }

    public void setOperacion(Operacion operacion) {
        this.operacion = operacion;
    }

    public Elemento getElemento() {
        return elemento;
    }

    public void setElemento(Elemento elemento) {
        this.elemento = elemento;
    }

    public Long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public DtActividad getDtActividad() {
        return new DtActividad(
                operacion.toString(),
                elemento.toString(),
                identificador,
                descripcion,
                usuario.getDtUsuario(),
                this.getId(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getCreacion())
        );
    }

    @Override
    public DtActividad getDtOpenSGSBean() {
        return this.getDtActividad();
    }

    @Override
    public DtActividad getDataType() {
        return this.getDtActividad();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.Actividad;
    }

    @Override
    public String toString() {
        return super.toString() + "Actividad{" + "operacion=" + operacion + ", elemento=" + elemento + ", identificador=" + identificador + ", descripcion=" + descripcion + ", usuario=" + usuario + '}';
    }

}
