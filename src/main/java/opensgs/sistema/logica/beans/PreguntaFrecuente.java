package opensgs.sistema.logica.beans;

import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.enums.Alcance;
import opensgs.enums.Elemento;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.logica.servicios.ServicioEnum;
import opensgs.logica.servicios.ServicioFechaHora;
import opensgs.sistema.datatypes.DtPreguntaFrecuente;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(
        name = "preguntaFrecuente",
        schema = "sistema"
)
public class PreguntaFrecuente extends OpenSGSManagedBean {

    @Column(unique = true, nullable = false, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 128)
    private String nombre;

    @Column(nullable = false)
    @NotNull
    @Positive
    @Max(1000)
    private Integer orden;

    @Column(nullable = false, columnDefinition = "varchar")
    @NotNull
    @NotBlank
    @Size(min = 1)
    private String pregunta;

    @Column(nullable = false, columnDefinition = "varchar")
    @NotNull
    @NotBlank
    @Size(min = 1)
    private String respuesta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 128)
    @NotNull
    private Alcance alcance;

    public PreguntaFrecuente() {
    }

    public PreguntaFrecuente(DtOpenSGSBean dtOpenSGSBean) {
        this((DtPreguntaFrecuente) dtOpenSGSBean);
    }

    public PreguntaFrecuente(DtPreguntaFrecuente dtPreguntaFrecuente) {
        super();
        this.nombre = dtPreguntaFrecuente.getNombre();
        this.orden = dtPreguntaFrecuente.getOrden();
        this.pregunta = dtPreguntaFrecuente.getPregunta();
        this.respuesta = dtPreguntaFrecuente.getRespuesta();
        this.alcance = ServicioEnum.getInstance().parseEnum(Alcance.class, dtPreguntaFrecuente.getAlcance());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Alcance getAlcance() {
        return alcance;
    }

    public void setAlcance(Alcance alcance) {
        this.alcance = alcance;
    }

    @Override
    public void modificar(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        DtPreguntaFrecuente dtPreguntaFrecuente = (DtPreguntaFrecuente) dtOpenSGSManagedBean;
        this.nombre = dtPreguntaFrecuente.getNombre();
        this.orden = dtPreguntaFrecuente.getOrden();
        this.pregunta = dtPreguntaFrecuente.getPregunta();
        this.respuesta = dtPreguntaFrecuente.getRespuesta();
        this.alcance = ServicioEnum.getInstance().parseEnum(Alcance.class, dtPreguntaFrecuente.getAlcance());
    }

    @Override
    public boolean agregarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    @Override
    public boolean quitarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    public DtPreguntaFrecuente getDtPreguntaFrecuente() {
        return new DtPreguntaFrecuente(
                nombre,
                orden,
                pregunta,
                respuesta,
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
    public DtPreguntaFrecuente getDtOpenSGSBean() {
        return this.getDtPreguntaFrecuente();
    }

    @Override
    public DtPreguntaFrecuente getDataType() {
        return this.getDtPreguntaFrecuente();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.PreguntaFrecuente;
    }

    @Override
    public String toString() {
        return super.toString() + "PreguntaFrecuente{" + "nombre=" + nombre + ", orden=" + orden + ", pregunta=" + pregunta + ", respuesta=" + respuesta + ", alcance=" + alcance + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.nombre);
        hash = 29 * hash + Objects.hashCode(this.orden);
        hash = 29 * hash + Objects.hashCode(this.pregunta);
        hash = 29 * hash + Objects.hashCode(this.respuesta);
        hash = 29 * hash + Objects.hashCode(this.alcance);
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
        final PreguntaFrecuente other = (PreguntaFrecuente) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.pregunta, other.pregunta)) {
            return false;
        }
        if (!Objects.equals(this.respuesta, other.respuesta)) {
            return false;
        }
        if (!Objects.equals(this.orden, other.orden)) {
            return false;
        }
        return this.alcance == other.alcance;
    }

}
