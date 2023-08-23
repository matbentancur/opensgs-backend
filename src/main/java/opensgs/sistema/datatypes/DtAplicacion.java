package opensgs.sistema.datatypes;

import java.util.ArrayList;
import java.util.List;
import opensgs.datatypes.DtOpenSGSManagedBean;

public final class DtAplicacion extends DtOpenSGSManagedBean {

    private String nombre;
    private String titulo;
    private String apertura;
    private String cierre;
    private String contacto;
    private Integer solicitudesPorUsuario;
    private Integer solicitudesTotal;
    private List<DtAnuncio> dtAnuncios = new ArrayList<>();
    private List<DtNotificacion> dtNotificaciones = new ArrayList<>();
    private List<DtPreguntaFrecuente> dtPreguntasFrecuentes = new ArrayList<>();
    private List<DtReporte> dtReportes = new ArrayList<>();
    private List<DtArchivo> dtArchivos = new ArrayList<>();
    private DtPlantillaAplicacion dtPlantillaAplicacion;

    public DtAplicacion() {
    }

    public DtAplicacion(String nombre, String titulo, String apertura, String cierre, String contacto, boolean activo, boolean borrado, boolean administrable, String modificado, Long id, String creacion) {
        super(activo, borrado, administrable, modificado, id, creacion);
        this.nombre = nombre;
        this.titulo = titulo;
        this.apertura = apertura;
        this.cierre = cierre;
        this.contacto = contacto;
    }

    public DtAplicacion(String nombre, String titulo, String apertura, String cierre, String contacto, DtPlantillaAplicacion dtPlantillaAplicacion, boolean activo, boolean borrado, boolean administrable, String modificado, Long id, String creacion) {
        super(activo, borrado, administrable, modificado, id, creacion);
        this.nombre = nombre;
        this.titulo = titulo;
        this.apertura = apertura;
        this.cierre = cierre;
        this.contacto = contacto;
        this.dtPlantillaAplicacion = dtPlantillaAplicacion;
    }

    public DtAplicacion(String nombre, String titulo, String apertura, String cierre, String contacto, Integer solicitudesPorUsuario, Integer solicitudesTotal, DtPlantillaAplicacion dtPlantillaAplicacion, boolean activo, boolean borrado, boolean administrable, String modificado, Long id, String creacion) {
        super(activo, borrado, administrable, modificado, id, creacion);
        this.nombre = nombre;
        this.titulo = titulo;
        this.apertura = apertura;
        this.cierre = cierre;
        this.contacto = contacto;
        this.solicitudesPorUsuario = solicitudesPorUsuario;
        this.solicitudesTotal = solicitudesTotal;
        this.dtPlantillaAplicacion = dtPlantillaAplicacion;
    }

    public DtAplicacion(String nombre, String titulo, String apertura, String cierre, String contacto, Integer solicitudesPorUsuario, Integer solicitudesTotal, DtPlantillaAplicacion dtPlantillaAplicacion, boolean activo, boolean borrado, boolean administrable, String modificado, Long id, String creacion, List<DtAnuncio> dtAnuncios, List<DtNotificacion> dtNotificaciones, List<DtPreguntaFrecuente> dtPreguntasFrecuentes, List<DtReporte> dtReportes, List<DtArchivo> dtArchivos) {
        super(activo, borrado, administrable, modificado, id, creacion);
        this.nombre = nombre;
        this.titulo = titulo;
        this.apertura = apertura;
        this.cierre = cierre;
        this.contacto = contacto;
        this.solicitudesPorUsuario = solicitudesPorUsuario;
        this.solicitudesTotal = solicitudesTotal;
        this.dtPlantillaAplicacion = dtPlantillaAplicacion;
        this.dtAnuncios = dtAnuncios;
        this.dtNotificaciones = dtNotificaciones;
        this.dtPreguntasFrecuentes = dtPreguntasFrecuentes;
        this.dtReportes = dtReportes;
        this.dtArchivos = dtArchivos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getApertura() {
        return apertura;
    }

    public void setApertura(String apertura) {
        this.apertura = apertura;
    }

    public String getCierre() {
        return cierre;
    }

    public void setCierre(String cierre) {
        this.cierre = cierre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public Integer getSolicitudesPorUsuario() {
        return solicitudesPorUsuario;
    }

    public void setSolicitudesPorUsuario(Integer solicitudesPorUsuario) {
        this.solicitudesPorUsuario = solicitudesPorUsuario;
    }

    public Integer getSolicitudesTotal() {
        return solicitudesTotal;
    }

    public void setSolicitudesTotal(Integer solicitudesTotal) {
        this.solicitudesTotal = solicitudesTotal;
    }

    public List<DtAnuncio> getDtAnuncios() {
        return dtAnuncios;
    }

    public void setDtAnuncios(List<DtAnuncio> dtAnuncios) {
        this.dtAnuncios = dtAnuncios;
    }

    public List<DtNotificacion> getDtNotificaciones() {
        return dtNotificaciones;
    }

    public void setDtNotificaciones(List<DtNotificacion> dtNotificaciones) {
        this.dtNotificaciones = dtNotificaciones;
    }

    public List<DtPreguntaFrecuente> getDtPreguntasFrecuentes() {
        return dtPreguntasFrecuentes;
    }

    public void setDtPreguntasFrecuentes(List<DtPreguntaFrecuente> dtPreguntasFrecuentes) {
        this.dtPreguntasFrecuentes = dtPreguntasFrecuentes;
    }

    public List<DtReporte> getDtReportes() {
        return dtReportes;
    }

    public void setDtReportes(List<DtReporte> dtReportes) {
        this.dtReportes = dtReportes;
    }

    public List<DtArchivo> getDtArchivos() {
        return dtArchivos;
    }

    public void setDtArchivos(List<DtArchivo> dtArchivos) {
        this.dtArchivos = dtArchivos;
    }

    public DtPlantillaAplicacion getDtPlantillaAplicacion() {
        return dtPlantillaAplicacion;
    }

    public void setDtPlantillaAplicacion(DtPlantillaAplicacion dtPlantillaAplicacion) {
        this.dtPlantillaAplicacion = dtPlantillaAplicacion;
    }

    @Override
    public String getClassName() {
        return "Aplicacion";
    }

    @Override
    public String toString() {
        return super.toString() + "DtAplicacion{" + "nombre=" + nombre + ", titulo=" + titulo + ", apertura=" + apertura + ", cierre=" + cierre + ", contacto=" + contacto + ", solicitudesPorUsuario=" + solicitudesPorUsuario + ", solicitudesTotal=" + solicitudesTotal + ", dtAnuncios=" + dtAnuncios + ", dtNotificaciones=" + dtNotificaciones + ", dtPreguntasFrecuentes=" + dtPreguntasFrecuentes + ", dtReportes=" + dtReportes + ", dtArchivos=" + dtArchivos + ", dtPlantillaAplicacion=" + dtPlantillaAplicacion + '}';
    }

}
