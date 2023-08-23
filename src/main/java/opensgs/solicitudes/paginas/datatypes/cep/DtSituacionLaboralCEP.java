package opensgs.solicitudes.paginas.datatypes.cep;

import opensgs.sistema.datatypes.DtPaginaAplicacion;
import opensgs.solicitudes.datatypes.DtPaginaSolicitud;

public class DtSituacionLaboralCEP extends DtPaginaSolicitud {

    private String situacion;
    private String departamentoTrabaja;
    private String lugarTrabajo;

    public DtSituacionLaboralCEP() {
    }

    public DtSituacionLaboralCEP(String situacion, String departamentoTrabaja, String lugarTrabajo, DtPaginaAplicacion dtPaginaAplicacion, boolean activo, boolean borrado, boolean administrable, String modificado, Long id, String creacion) {
        super(dtPaginaAplicacion, activo, borrado, administrable, modificado, id, creacion);
        this.situacion = situacion;
        this.departamentoTrabaja = departamentoTrabaja;
        this.lugarTrabajo = lugarTrabajo;
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

    @Override
    public String getClassName() {
        return "SituacionLaboralCEP";
    }

    @Override
    public String toString() {
        return super.toString() + "DtSituacionLaboralCEP{" + "situacion=" + situacion + ", departamentoTrabaja=" + departamentoTrabaja + ", lugarTrabajo=" + lugarTrabajo + '}';
    }

}
