package opensgs.solicitudes.paginas.beans.cep;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.enums.Elemento;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.servicios.ServicioFechaHora;
import opensgs.solicitudes.datatypes.DtPaginaSolicitud;
import opensgs.solicitudes.logica.beans.PaginaSolicitud;
import opensgs.solicitudes.paginas.datatypes.cep.DtDifusionCEP;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(
        name = "difusionCEP",
        schema = "cep"
)
public class DifusionCEP extends PaginaSolicitud {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "medios",
            schema = "cep"
    )
    private List<String> medios = new ArrayList<>();

    public DifusionCEP() {
    }

    public DifusionCEP(DtPaginaSolicitud dtPaginaSolicitud) {
        this((DtDifusionCEP) dtPaginaSolicitud);
    }

    public DifusionCEP(DtDifusionCEP dtDifusionCEP) {
        super();
        this.medios = dtDifusionCEP.getMedios();
    }

    public List<String> getMedios() {
        return medios;
    }

    public void setMedios(List<String> medios) {
        this.medios = medios;
    }

    public DtDifusionCEP getDtDifusionCEP() {
        return new DtDifusionCEP(
                medios,
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
    public DtDifusionCEP getDtOpenSGSBean() {
        return this.getDtDifusionCEP();
    }

    @Override
    public DtDifusionCEP getDataType() {
        return this.getDtDifusionCEP();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.PaginaSolicitud;
    }

    @Override
    public void modificar(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        DtDifusionCEP dtDifusionCEP = (DtDifusionCEP) dtOpenSGSManagedBean;
        this.medios = dtDifusionCEP.getMedios();
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
