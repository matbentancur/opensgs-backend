package opensgs.sistema.logica.beans;

import java.util.Objects;
import opensgs.sistema.datatypes.DtReporte;
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
        name = "reporte",
        schema = "sistema"
)
public class Reporte extends OpenSGSManagedBean {

    @Column(unique = true, nullable = false, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 128)
    private String nombre;

    @Column(nullable = false, columnDefinition = "varchar")
    @NotNull
    @NotBlank
    @Size(min = 1)
    private String cabezal;

    @Column(nullable = false, columnDefinition = "varchar")
    @NotNull
    @NotBlank
    @Size(min = 1)
    private String sentenciaSQL;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 128)
    @NotNull
    private Alcance alcance;

    public Reporte() {
    }

    public Reporte(DtOpenSGSBean dtOpenSGSBean) {
        this((DtReporte) dtOpenSGSBean);
    }

    public Reporte(DtReporte dtReporte) {
        super();
        this.nombre = dtReporte.getNombre();
        this.cabezal = dtReporte.getCabezal();
        this.sentenciaSQL = dtReporte.getSentenciaSQL();
        this.alcance = ServicioEnum.getInstance().parseEnum(Alcance.class, dtReporte.getAlcance());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCabezal() {
        return cabezal;
    }

    public void setCabezal(String cabezal) {
        this.cabezal = cabezal;
    }

    public String getSentenciaSQL() {
        return sentenciaSQL;
    }

    public void setSentenciaSQL(String sentenciaSQL) {
        this.sentenciaSQL = sentenciaSQL;
    }

    public Alcance getAlcance() {
        return alcance;
    }

    public void setAlcance(Alcance alcance) {
        this.alcance = alcance;
    }

    @Override
    public void modificar(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        DtReporte dtReporte = (DtReporte) dtOpenSGSManagedBean;
        this.nombre = dtReporte.getNombre();
        this.cabezal = dtReporte.getCabezal();
        this.sentenciaSQL = dtReporte.getSentenciaSQL();
        this.alcance = ServicioEnum.getInstance().parseEnum(Alcance.class, dtReporte.getAlcance());
    }

    @Override
    public boolean agregarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    @Override
    public boolean quitarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    public DtReporte getDtReporte() {
        return new DtReporte(
                nombre,
                cabezal,
                sentenciaSQL,
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
    public DtReporte getDtOpenSGSBean() {
        return this.getDtReporte();
    }

    @Override
    public DtReporte getDataType() {
        return this.getDtReporte();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.Reporte;
    }

    @Override
    public String toString() {
        return super.toString() + "Reporte{" + "nombre=" + nombre + ", cabezal=" + cabezal + ", sentenciaSQL=" + sentenciaSQL + ", alcance=" + alcance + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.nombre);
        hash = 17 * hash + Objects.hashCode(this.cabezal);
        hash = 17 * hash + Objects.hashCode(this.sentenciaSQL);
        hash = 17 * hash + Objects.hashCode(this.alcance);
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
        final Reporte other = (Reporte) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.cabezal, other.cabezal)) {
            return false;
        }
        if (!Objects.equals(this.sentenciaSQL, other.sentenciaSQL)) {
            return false;
        }
        return this.alcance == other.alcance;
    }

}
