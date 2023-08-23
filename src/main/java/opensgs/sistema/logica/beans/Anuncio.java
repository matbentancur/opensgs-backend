package opensgs.sistema.logica.beans;

import opensgs.sistema.datatypes.DtAnuncio;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.enums.Alcance;
import opensgs.enums.Elemento;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.logica.servicios.ServicioEnum;
import opensgs.logica.servicios.ServicioFechaHora;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(
        name = "anuncio",
        schema = "sistema"
)
public class Anuncio extends OpenSGSManagedBean {

    @Column(nullable = false, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 128)
    private String nombre;

    @Column(nullable = false, columnDefinition = "varchar")
    @NotNull
    @NotBlank
    @Size(min = 1)
    private String texto;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @NotNull
//    @FutureOrPresent
    private Date inicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @NotNull
//    @Future
    private Date fin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 128)
    @NotNull
    private Alcance alcance;

    public Anuncio() {
    }

    public Anuncio(DtOpenSGSBean dtOpenSGSBean) {
        this((DtAnuncio) dtOpenSGSBean);
    }

    public Anuncio(DtAnuncio dtAnuncio) {
        super();
        this.nombre = dtAnuncio.getNombre();
        this.texto = dtAnuncio.getTexto();
        this.inicio = ServicioFechaHora.getInstance().stringToDateTime(dtAnuncio.getInicio());
        this.fin = ServicioFechaHora.getInstance().stringToDateTime(dtAnuncio.getFin());
        this.alcance = ServicioEnum.getInstance().parseEnum(Alcance.class, dtAnuncio.getAlcance());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Alcance getAlcance() {
        return alcance;
    }

    public void setAlcance(Alcance alcance) {
        this.alcance = alcance;
    }

    @Override
    public void modificar(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        DtAnuncio dtAnuncio = (DtAnuncio) dtOpenSGSManagedBean;
        this.nombre = dtAnuncio.getNombre();
        this.texto = dtAnuncio.getTexto();
        this.inicio = ServicioFechaHora.getInstance().stringToDateTime(dtAnuncio.getInicio());
        this.fin = ServicioFechaHora.getInstance().stringToDateTime(dtAnuncio.getFin());
        this.alcance = ServicioEnum.getInstance().parseEnum(Alcance.class, dtAnuncio.getAlcance());
    }

    @Override
    public boolean agregarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    @Override
    public boolean quitarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    public DtAnuncio getDtAnuncio() {
        String inicioString = ServicioFechaHora.getInstance().dateTimeToString(inicio);
        String finString = ServicioFechaHora.getInstance().dateTimeToString(fin);
        return new DtAnuncio(
                nombre,
                texto,
                inicioString,
                finString,
                alcance.toString(),
                this.isActivo(),
                this.isBorrado(),
                this.isAdministrable(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getModificado()),
                this.getId(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getCreacion())
        );
    }

    @Override
    public DtAnuncio getDtOpenSGSBean() {
        return this.getDtAnuncio();
    }

    @Override
    public DtAnuncio getDataType() {
        return this.getDtAnuncio();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.Anuncio;
    }

    @Override
    public String toString() {
        return super.toString() + "Anuncio{" + "nombre=" + nombre + ", texto=" + texto + ", inicio=" + inicio + ", fin=" + fin + ", alcance=" + alcance + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.nombre);
        hash = 43 * hash + Objects.hashCode(this.texto);
        hash = 43 * hash + Objects.hashCode(this.inicio);
        hash = 43 * hash + Objects.hashCode(this.fin);
        hash = 43 * hash + Objects.hashCode(this.alcance);
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
        final Anuncio other = (Anuncio) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.texto, other.texto)) {
            return false;
        }
        if (!Objects.equals(this.inicio, other.inicio)) {
            return false;
        }
        if (!Objects.equals(this.fin, other.fin)) {
            return false;
        }
        return this.alcance == other.alcance;
    }

}
