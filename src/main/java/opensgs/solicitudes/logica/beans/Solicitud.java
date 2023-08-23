package opensgs.solicitudes.logica.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.enums.Elemento;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.logica.servicios.ServicioFechaHora;
import opensgs.logica.servicios.ServicioListas;
import opensgs.solicitudes.datatypes.DtSolicitud;
import opensgs.sistema.logica.beans.Aplicacion;
import opensgs.sistema.persistencia.manejadores.ManejadorAplicacion;
import opensgs.solicitudes.datatypes.DtPaginaSolicitud;
import opensgs.usuarios.logica.beans.Usuario;
import opensgs.usuarios.persistencia.manejadores.ManejadorUsuario;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(
        name = "solicitud",
        schema = "solicitudes"
)
public class Solicitud extends OpenSGSManagedBean {

    @Column(nullable = false)
    private boolean acordado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date acordadoFecha;

    @Column(nullable = false)
    private boolean entregado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date entregadoFecha;

    @Column(nullable = false)
    private boolean financiado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date financiadoFecha;

    @Column(nullable = false)
    private boolean cursado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date cursadoFecha;

    @Column(nullable = false)
    private boolean aprobado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date aprobadoFecha;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    private Aplicacion aplicacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    private Usuario usuario;

    @JoinTable(schema = "solicitudes")
    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    private List<PaginaSolicitud> paginasSolicitud = new ArrayList<>();

    public Solicitud() {
    }

    public Solicitud(DtOpenSGSBean dtOpenSGSBean) {
        this((DtSolicitud) dtOpenSGSBean);
    }

    public Solicitud(DtSolicitud dtSolicitud) {
        super();
        this.acordado = dtSolicitud.isAcordado();
        this.acordadoFecha = ServicioFechaHora.getInstance().stringToDateTime(dtSolicitud.getAcordadoFecha());
        this.entregado = dtSolicitud.isEntregado();
        this.entregadoFecha = ServicioFechaHora.getInstance().stringToDateTime(dtSolicitud.getEntregadoFecha());
        this.financiado = dtSolicitud.isFinanciado();
        this.financiadoFecha = ServicioFechaHora.getInstance().stringToDateTime(dtSolicitud.getFinanciadoFecha());
        this.cursado = dtSolicitud.isCursado();
        this.cursadoFecha = ServicioFechaHora.getInstance().stringToDateTime(dtSolicitud.getCursadoFecha());
        this.aprobado = dtSolicitud.isAprobado();
        this.aprobadoFecha = ServicioFechaHora.getInstance().stringToDateTime(dtSolicitud.getAprobadoFecha());
        this.aplicacion = ManejadorAplicacion.getInstance().obtenerAplicacion(dtSolicitud.getDtAplicacion());
        this.usuario = ManejadorUsuario.getInstance().obtenerUsuario(dtSolicitud.getDtUsuario());
        this.paginasSolicitud = ServicioListas.getInstance().dataTypeToBean(dtSolicitud.getDtPaginasSolicitud());
    }

    public boolean isAcordado() {
        return acordado;
    }

    public void setAcordado(boolean acordado) {
        this.acordado = acordado;
    }

    public Date getAcordadoFecha() {
        return acordadoFecha;
    }

    public void setAcordadoFecha(Date acordadoFecha) {
        this.acordadoFecha = acordadoFecha;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    public Date getEntregadoFecha() {
        return entregadoFecha;
    }

    public void setEntregadoFecha(Date entregadoFecha) {
        this.entregadoFecha = entregadoFecha;
    }

    public boolean isFinanciado() {
        return financiado;
    }

    public void setFinanciado(boolean financiado) {
        this.financiado = financiado;
    }

    public Date getFinanciadoFecha() {
        return financiadoFecha;
    }

    public void setFinanciadoFecha(Date financiadoFecha) {
        this.financiadoFecha = financiadoFecha;
    }

    public boolean isCursado() {
        return cursado;
    }

    public void setCursado(boolean cursado) {
        this.cursado = cursado;
    }

    public Date getCursadoFecha() {
        return cursadoFecha;
    }

    public void setCursadoFecha(Date cursadoFecha) {
        this.cursadoFecha = cursadoFecha;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public Date getAprobadoFecha() {
        return aprobadoFecha;
    }

    public void setAprobadoFecha(Date aprobadoFecha) {
        this.aprobadoFecha = aprobadoFecha;
    }

    public Aplicacion getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<PaginaSolicitud> getPaginasSolicitud() {
        return paginasSolicitud;
    }

    public void setPaginasSolicitud(List<PaginaSolicitud> paginasSolicitud) {
        this.paginasSolicitud = paginasSolicitud;
    }

    @Override
    public void modificar(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        DtSolicitud dtSolicitud = (DtSolicitud) dtOpenSGSManagedBean;
        this.entregado = dtSolicitud.isEntregado();
        this.financiado = dtSolicitud.isFinanciado();
        this.cursado = dtSolicitud.isCursado();
        this.aprobado = dtSolicitud.isAprobado();
    }

    public void acordar(boolean b) {
        this.acordado = b;
        this.acordadoFecha = new Date();
    }

    public void entregar(boolean b) {
        this.entregado = b;
        this.entregadoFecha = new Date();
    }

    public void financiar(boolean b) {
        this.financiado = b;
        this.financiadoFecha = new Date();
    }

    public void cursar(boolean b) {
        this.cursado = b;
        this.cursadoFecha = new Date();
    }

    public void aprobar(boolean b) {
        this.aprobado = b;
        this.aprobadoFecha = new Date();
    }

    @Override
    public boolean agregarListOpenSGSBean(OpenSGSBean openSGSBean) {
        PaginaSolicitud paginaSolicitud = (PaginaSolicitud) openSGSBean;
        return ServicioListas.getInstance().agregarElemento(paginasSolicitud, paginaSolicitud);
    }

    @Override
    public boolean quitarListOpenSGSBean(OpenSGSBean openSGSBean) {
        PaginaSolicitud paginaSolicitud = (PaginaSolicitud) openSGSBean;
        return ServicioListas.getInstance().quitarElemento(paginasSolicitud, paginaSolicitud);
    }

    public DtSolicitud getDtSolicitud() {
        String acordadoFechaString = ServicioFechaHora.getInstance().dateTimeToString(acordadoFecha);
        String entregadoFechaString = ServicioFechaHora.getInstance().dateTimeToString(entregadoFecha);
        String financiadoFechaString = ServicioFechaHora.getInstance().dateTimeToString(financiadoFecha);
        String cursadoFechaString = ServicioFechaHora.getInstance().dateTimeToString(cursadoFecha);
        String aprobadoFechaString = ServicioFechaHora.getInstance().dateTimeToString(aprobadoFecha);

        List<DtPaginaSolicitud> listaDtPaginaSolicitud = ServicioListas.getInstance().beanToDataType(paginasSolicitud);

        return new DtSolicitud(
                acordado,
                acordadoFechaString,
                entregado,
                entregadoFechaString,
                financiado,
                financiadoFechaString,
                cursado,
                cursadoFechaString,
                aprobado,
                aprobadoFechaString,
                this.getAplicacion().getDtAplicacion(),
                this.getUsuario().getDtUsuario(),
                this.isActivo(),
                this.isBorrado(),
                this.isAdministrable(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getModificado()),
                this.getId(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getCreacion()),
                listaDtPaginaSolicitud
        );
    }

    @Override
    public DtSolicitud getDtOpenSGSBean() {
        return this.getDtSolicitud();
    }

    @Override
    public DtSolicitud getDataType() {
        return this.getDtSolicitud();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.Solicitud;
    }

    @Override
    public String toString() {
        return super.toString() + "Solicitud{" + "acordado=" + acordado + ", acordadoFecha=" + acordadoFecha + ", entregado=" + entregado + ", entregadoFecha=" + entregadoFecha + ", financiado=" + financiado + ", financiadoFecha=" + financiadoFecha + ", cursado=" + cursado + ", cursadoFecha=" + cursadoFecha + ", aprobado=" + aprobado + ", aprobadoFecha=" + aprobadoFecha + ", aplicacion=" + aplicacion + ", usuario=" + usuario + ", paginasSolicitud=" + paginasSolicitud + '}';
    }

}
