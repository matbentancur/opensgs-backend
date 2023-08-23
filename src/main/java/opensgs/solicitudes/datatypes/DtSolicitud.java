package opensgs.solicitudes.datatypes;

import java.util.ArrayList;
import java.util.List;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.sistema.datatypes.DtAplicacion;
import opensgs.usuarios.datatypes.DtUsuario;

public final class DtSolicitud extends DtOpenSGSManagedBean {

    private boolean acordado;
    private String acordadoFecha;
    private boolean entregado;
    private String entregadoFecha;
    private boolean financiado;
    private String financiadoFecha;
    private boolean cursado;
    private String cursadoFecha;
    private boolean aprobado;
    private String aprobadoFecha;
    private DtAplicacion dtAplicacion;
    private DtUsuario dtUsuario;
    private List<DtPaginaSolicitud> dtPaginasSolicitud = new ArrayList<>();

    public DtSolicitud() {
    }

    public DtSolicitud(boolean acordado, String acordadoFecha, boolean entregado, String entregadoFecha, boolean financiado, String financiadoFecha, boolean cursado, String cursadoFecha, boolean aprobado, String aprobadoFecha, DtAplicacion dtAplicacion, DtUsuario dtUsuario, boolean activo, boolean borrado, boolean administrable, String modificado, Long id, String creacion) {
        super(activo, borrado, administrable, modificado, id, creacion);
        this.acordado = acordado;
        this.acordadoFecha = acordadoFecha;
        this.entregado = entregado;
        this.entregadoFecha = entregadoFecha;
        this.financiado = financiado;
        this.financiadoFecha = financiadoFecha;
        this.cursado = cursado;
        this.cursadoFecha = cursadoFecha;
        this.aprobado = aprobado;
        this.aprobadoFecha = aprobadoFecha;
        this.dtAplicacion = dtAplicacion;
        this.dtUsuario = dtUsuario;
    }

    public DtSolicitud(boolean acordado, String acordadoFecha, boolean entregado, String entregadoFecha, boolean financiado, String financiadoFecha, boolean cursado, String cursadoFecha, boolean aprobado, String aprobadoFecha, DtAplicacion dtAplicacion, DtUsuario dtUsuario, boolean activo, boolean borrado, boolean administrable, String modificado, Long id, String creacion, List<DtPaginaSolicitud> dtPaginaSolicitud) {
        super(activo, borrado, administrable, modificado, id, creacion);
        this.acordado = acordado;
        this.acordadoFecha = acordadoFecha;
        this.entregado = entregado;
        this.entregadoFecha = entregadoFecha;
        this.financiado = financiado;
        this.financiadoFecha = financiadoFecha;
        this.cursado = cursado;
        this.cursadoFecha = cursadoFecha;
        this.aprobado = aprobado;
        this.aprobadoFecha = aprobadoFecha;
        this.dtAplicacion = dtAplicacion;
        this.dtUsuario = dtUsuario;
        this.dtPaginasSolicitud = dtPaginaSolicitud;
    }

    public boolean isAcordado() {
        return acordado;
    }

    public void setAcordado(boolean acordado) {
        this.acordado = acordado;
    }

    public String getAcordadoFecha() {
        return acordadoFecha;
    }

    public void setAcordadoFecha(String acordadoFecha) {
        this.acordadoFecha = acordadoFecha;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    public String getEntregadoFecha() {
        return entregadoFecha;
    }

    public void setEntregadoFecha(String entregadoFecha) {
        this.entregadoFecha = entregadoFecha;
    }

    public boolean isFinanciado() {
        return financiado;
    }

    public void setFinanciado(boolean financiado) {
        this.financiado = financiado;
    }

    public String getFinanciadoFecha() {
        return financiadoFecha;
    }

    public void setFinanciadoFecha(String financiadoFecha) {
        this.financiadoFecha = financiadoFecha;
    }

    public boolean isCursado() {
        return cursado;
    }

    public void setCursado(boolean cursado) {
        this.cursado = cursado;
    }

    public String getCursadoFecha() {
        return cursadoFecha;
    }

    public void setCursadoFecha(String cursadoFecha) {
        this.cursadoFecha = cursadoFecha;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public String getAprobadoFecha() {
        return aprobadoFecha;
    }

    public void setAprobadoFecha(String aprobadoFecha) {
        this.aprobadoFecha = aprobadoFecha;
    }

    public DtAplicacion getDtAplicacion() {
        return dtAplicacion;
    }

    public void setDtAplicacion(DtAplicacion dtAplicacion) {
        this.dtAplicacion = dtAplicacion;
    }

    public DtUsuario getDtUsuario() {
        return dtUsuario;
    }

    public void setDtUsuario(DtUsuario dtUsuario) {
        this.dtUsuario = dtUsuario;
    }

    public List<DtPaginaSolicitud> getDtPaginasSolicitud() {
        return dtPaginasSolicitud;
    }

    public void setDtPaginasSolicitud(List<DtPaginaSolicitud> dtPaginasSolicitud) {
        this.dtPaginasSolicitud = dtPaginasSolicitud;
    }

    @Override
    public String getClassName() {
        return "Solicitud";
    }
    
    public DtEstadoSolicitud getDtEstadoSolicitud() {
        return new DtEstadoSolicitud(
                this.getId(),
                entregado,
                financiado,
                cursado,
                aprobado
        );
    }

    @Override
    public String toString() {
        return super.toString() + "DtSolicitud{" + "acordado=" + acordado + ", acordadoFecha=" + acordadoFecha + ", entregado=" + entregado + ", entregadoFecha=" + entregadoFecha + ", financiado=" + financiado + ", financiadoFecha=" + financiadoFecha + ", cursado=" + cursado + ", cursadoFecha=" + cursadoFecha + ", aprobado=" + aprobado + ", aprobadoFecha=" + aprobadoFecha + ", dtAplicacion=" + dtAplicacion + ", dtUsuario=" + dtUsuario + ", dtPaginasSolicitud=" + dtPaginasSolicitud + '}';
    }

}
