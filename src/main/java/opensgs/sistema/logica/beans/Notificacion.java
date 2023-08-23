package opensgs.sistema.logica.beans;

import opensgs.sistema.datatypes.DtNotificacion;
import opensgs.enums.Elemento;
import opensgs.enums.Operacion;
import opensgs.sistema.enums.NotificacionDestino;
import opensgs.sistema.persistencia.manejadores.ManejadorPlantillaCorreo;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.enums.Alcance;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.logica.servicios.ServicioEnum;
import opensgs.logica.servicios.ServicioFechaHora;
import opensgs.logica.servicios.ServicioListas;
import opensgs.sistema.datatypes.DtDestinatario;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(
        name = "notificacion",
        schema = "sistema"
)

public class Notificacion extends OpenSGSManagedBean {

    @Column(nullable = false, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 128)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 128)
    @NotNull
    private Operacion operacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 128)
    @NotNull
    private Elemento elemento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 128)
    @NotNull
    private NotificacionDestino destino;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 128)
    @NotNull
    private Alcance alcance;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    @NotNull
    private PlantillaCorreo plantillaCorreo;

    @JoinTable(schema = "sistema")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    private List<Destinatario> destinatarios = new ArrayList<>();

    public Notificacion() {
    }

    public Notificacion(DtOpenSGSBean dtOpenSGSBean) {
        this((DtNotificacion) dtOpenSGSBean);
    }

    public Notificacion(DtNotificacion dtNotificacion) {
        super();
        this.nombre = dtNotificacion.getNombre();
        this.operacion = ServicioEnum.getInstance().parseEnum(Operacion.class, dtNotificacion.getOperacion());
        this.elemento = ServicioEnum.getInstance().parseEnum(Elemento.class, dtNotificacion.getElemento());
        this.destino = ServicioEnum.getInstance().parseEnum(NotificacionDestino.class, dtNotificacion.getDestino());
        this.alcance = ServicioEnum.getInstance().parseEnum(Alcance.class, dtNotificacion.getAlcance());
        this.plantillaCorreo = ManejadorPlantillaCorreo.getInstance().obtenerPlantillaCorreo(dtNotificacion.getDtPlantillaCorreo());
        this.destinatarios = ServicioListas.getInstance().dataTypeToBean(dtNotificacion.getDtDestinatarios());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public NotificacionDestino getDestino() {
        return destino;
    }

    public void setDestino(NotificacionDestino destino) {
        this.destino = destino;
    }

    public Alcance getAlcance() {
        return alcance;
    }

    public void setAlcance(Alcance alcance) {
        this.alcance = alcance;
    }

    public PlantillaCorreo getPlantillaCorreo() {
        return plantillaCorreo;
    }

    public void setPlantillaCorreo(PlantillaCorreo plantillaCorreo) {
        this.plantillaCorreo = plantillaCorreo;
    }

    public List<Destinatario> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(List<Destinatario> destinatarios) {
        this.destinatarios = destinatarios;
    }

    @Override
    public void modificar(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        DtNotificacion dtNotificacion = (DtNotificacion) dtOpenSGSManagedBean;
        this.nombre = dtNotificacion.getNombre();
        this.operacion = ServicioEnum.getInstance().parseEnum(Operacion.class, dtNotificacion.getOperacion());
        this.elemento = ServicioEnum.getInstance().parseEnum(Elemento.class, dtNotificacion.getElemento());
        this.destino = ServicioEnum.getInstance().parseEnum(NotificacionDestino.class, dtNotificacion.getDestino());
        this.alcance = ServicioEnum.getInstance().parseEnum(Alcance.class, dtNotificacion.getAlcance());
        this.plantillaCorreo = ManejadorPlantillaCorreo.getInstance().obtenerPlantillaCorreo(dtNotificacion.getDtPlantillaCorreo());
    }

    @Override
    public boolean agregarListOpenSGSBean(OpenSGSBean openSGSBean) {
        Destinatario destinatario = (Destinatario) openSGSBean;
        return ServicioListas.getInstance().agregarElemento(destinatarios, destinatario);
    }

    @Override
    public boolean quitarListOpenSGSBean(OpenSGSBean openSGSBean) {
        Destinatario destinatario = (Destinatario) openSGSBean;
        return ServicioListas.getInstance().quitarElemento(destinatarios, destinatario);
    }

    public DtNotificacion getDtNotificacion() {
        List<DtDestinatario> listaDtDestinatario = new ArrayList<>();
        for (Destinatario destinatario : this.destinatarios) {
            listaDtDestinatario.add(destinatario.getDtDestinatario());
        }
        return new DtNotificacion(
                nombre,
                operacion.toString(),
                elemento.toString(),
                destino.toString(),
                alcance.toString(),
                plantillaCorreo.getDtPlantillaCorreo(),
                this.isActivo(),
                this.isBorrado(),
                this.isAdministrable(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getModificado()),
                this.getId(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getCreacion()),
                listaDtDestinatario
        );
    }

    @Override
    public DtNotificacion getDtOpenSGSBean() {
        return this.getDtNotificacion();
    }

    @Override
    public DtNotificacion getDataType() {
        return this.getDtNotificacion();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.Notificacion;
    }

    @Override
    public String toString() {
        return super.toString() + "Notificacion{" + "nombre=" + nombre + ", operacion=" + operacion + ", elemento=" + elemento + ", destino=" + destino + ", alcance=" + alcance + ", plantillaCorreo=" + plantillaCorreo + ", destinatarios=" + destinatarios + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.nombre);
        hash = 73 * hash + Objects.hashCode(this.operacion);
        hash = 73 * hash + Objects.hashCode(this.elemento);
        hash = 73 * hash + Objects.hashCode(this.destino);
        hash = 73 * hash + Objects.hashCode(this.alcance);
        hash = 73 * hash + Objects.hashCode(this.plantillaCorreo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Notificacion other = (Notificacion) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (this.operacion != other.operacion) {
            return false;
        }
        if (this.elemento != other.elemento) {
            return false;
        }
        if (this.destino != other.destino) {
            return false;
        }
        if (this.alcance != other.alcance) {
            return false;
        }
        return Objects.equals(this.plantillaCorreo, other.plantillaCorreo);
    }

}
