package opensgs.solicitudes.paginas.beans.cep;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.enums.Elemento;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.servicios.ServicioFechaHora;
import opensgs.solicitudes.datatypes.DtPaginaSolicitud;
import opensgs.solicitudes.logica.beans.PaginaSolicitud;
import opensgs.solicitudes.paginas.datatypes.cep.DtSituacionLaboralCEP;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(
        name = "situacionLaboralCEP",
        schema = "cep"
)
public class SituacionLaboralCEP extends PaginaSolicitud {

    @Column(nullable = true, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 2, max = 128)
    private String situacion;

    @Column(nullable = true, length = 128)
    @Size(min = 1, max = 128)
    private String departamentoTrabaja;

    @Column(nullable = true, length = 128)
    @Size(min = 1, max = 128)
    private String lugarTrabajo;

    public SituacionLaboralCEP() {
    }

    public SituacionLaboralCEP(DtPaginaSolicitud dtPaginaSolicitud) {
        this((DtSituacionLaboralCEP) dtPaginaSolicitud);
    }

    public SituacionLaboralCEP(DtSituacionLaboralCEP dtSituacionLaboralCEP) {
        super();
        this.situacion = dtSituacionLaboralCEP.getSituacion();
        this.departamentoTrabaja = dtSituacionLaboralCEP.getDepartamentoTrabaja();
        this.lugarTrabajo = dtSituacionLaboralCEP.getLugarTrabajo();
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public String getDepartamentoTrabaja() {
        return departamentoTrabaja;
    }

    public void setDepartamentoTrabaja(String departamentoTrabaja) {
        this.departamentoTrabaja = departamentoTrabaja;
    }

    public String getLugarTrabajo() {
        return lugarTrabajo;
    }

    public void setLugarTrabajo(String lugarTrabajo) {
        this.lugarTrabajo = lugarTrabajo;
    }

    public DtSituacionLaboralCEP getDtSituacionLaboralCEP() {
        return new DtSituacionLaboralCEP(
                situacion,
                departamentoTrabaja,
                lugarTrabajo,
                this.getPaginaAplicacion().getDtPaginaAplicacion(),
                this.isActivo(),
                this.isBorrado(),
                this.isAdministrable(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getModificado()),
                this.getId(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getCreacion())
        );
    }

    @Override
    public DtSituacionLaboralCEP getDtOpenSGSBean() {
        return this.getDtSituacionLaboralCEP();
    }

    @Override
    public DtSituacionLaboralCEP getDataType() {
        return this.getDtSituacionLaboralCEP();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.PaginaSolicitud;
    }

    @Override
    public void modificar(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        DtSituacionLaboralCEP dtSituacionLaboralCEP = (DtSituacionLaboralCEP) dtOpenSGSManagedBean;
        this.situacion = dtSituacionLaboralCEP.getSituacion();
        this.departamentoTrabaja = dtSituacionLaboralCEP.getDepartamentoTrabaja();
        this.lugarTrabajo = dtSituacionLaboralCEP.getLugarTrabajo();
    }

    @Override
    public boolean agregarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    @Override
    public boolean quitarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

}
