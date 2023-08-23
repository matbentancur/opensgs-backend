package opensgs.solicitudes.paginas.datatypes.cep;

import java.util.ArrayList;
import java.util.List;
import opensgs.sistema.datatypes.DtPaginaAplicacion;
import opensgs.solicitudes.datatypes.DtPaginaSolicitud;

public class DtDifusionCEP extends DtPaginaSolicitud {

    private List<String> medios = new ArrayList<>();

    public DtDifusionCEP() {
    }

    public DtDifusionCEP(List<String> medios, DtPaginaAplicacion dtPaginaAplicacion, boolean activo, boolean borrado, boolean administrable, String modificado, Long id, String creacion) {
        super(dtPaginaAplicacion, activo, borrado, administrable, modificado, id, creacion);
        this.medios = medios;
    }

    public List<String> getMedios() {
        return medios;
    }

    public void setMedios(List<String> medios) {
        this.medios = medios;
    }

    @Override
    public String getClassName() {
        return "DifusionCEP";
    }

    @Override
    public String toString() {
        return super.toString() + "DtDifusionCEP{" + "medios=" + medios + '}';
    }

}
