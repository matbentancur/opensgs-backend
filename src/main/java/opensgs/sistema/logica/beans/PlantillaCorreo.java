package opensgs.sistema.logica.beans;

import java.util.Objects;
import opensgs.sistema.datatypes.DtPlantillaCorreo;
import opensgs.sistema.enums.PlantillaCorreoAdjunto;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.enums.Elemento;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.logica.servicios.ServicioEnum;
import opensgs.logica.servicios.ServicioFechaHora;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(
        name = "plantillaCorreo",
        schema = "sistema"
)
public class PlantillaCorreo extends OpenSGSManagedBean {

    @Column(unique = true, nullable = false, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 128)
    private String nombre;

    @Column(nullable = false, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 128)
    private String asunto;

    @Column(nullable = false, columnDefinition = "varchar")
    @NotNull
    @NotBlank
    @Size(min = 1)
    private String texto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 128)
    @NotNull
    private PlantillaCorreoAdjunto adjunto;

    public PlantillaCorreo() {
    }

    public PlantillaCorreo(DtOpenSGSBean dtOpenSGSBean) {
        this((DtPlantillaCorreo) dtOpenSGSBean);
    }

    public PlantillaCorreo(DtPlantillaCorreo dtPlantillaCorreo) {
        super();
        this.nombre = dtPlantillaCorreo.getNombre();
        this.asunto = dtPlantillaCorreo.getAsunto();
        this.texto = dtPlantillaCorreo.getTexto();
        this.adjunto = ServicioEnum.getInstance().parseEnum(PlantillaCorreoAdjunto.class, dtPlantillaCorreo.getAdjunto());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public PlantillaCorreoAdjunto getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(PlantillaCorreoAdjunto adjunto) {
        this.adjunto = adjunto;
    }

    @Override
    public void modificar(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        DtPlantillaCorreo dtPlantillaCorreo = (DtPlantillaCorreo) dtOpenSGSManagedBean;
        this.nombre = dtPlantillaCorreo.getNombre();
        this.asunto = dtPlantillaCorreo.getAsunto();
        this.texto = dtPlantillaCorreo.getTexto();
        this.adjunto = ServicioEnum.getInstance().parseEnum(PlantillaCorreoAdjunto.class, dtPlantillaCorreo.getAdjunto());
    }

    @Override
    public boolean agregarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    @Override
    public boolean quitarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    public DtPlantillaCorreo getDtPlantillaCorreo() {
        return new DtPlantillaCorreo(
                nombre,
                asunto,
                texto,
                adjunto.toString(),
                this.getId(),
                this.isActivo(),
                this.isBorrado(),
                this.isAdministrable(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getCreacion()),
                ServicioFechaHora.getInstance().dateTimeToString(this.getModificado())
        );
    }

    @Override
    public DtPlantillaCorreo getDtOpenSGSBean() {
        return this.getDtPlantillaCorreo();
    }

    @Override
    public DtPlantillaCorreo getDataType() {
        return this.getDtPlantillaCorreo();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.PlantillaCorreo;
    }

    @Override
    public String toString() {
        return super.toString() + "PlantillaCorreo{" + "nombre=" + nombre + ", asunto=" + asunto + ", texto=" + texto + ", adjunto=" + adjunto + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.nombre);
        hash = 29 * hash + Objects.hashCode(this.asunto);
        hash = 29 * hash + Objects.hashCode(this.texto);
        hash = 29 * hash + Objects.hashCode(this.adjunto);
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
        final PlantillaCorreo other = (PlantillaCorreo) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.asunto, other.asunto)) {
            return false;
        }
        if (!Objects.equals(this.texto, other.texto)) {
            return false;
        }
        return this.adjunto == other.adjunto;
    }

}
