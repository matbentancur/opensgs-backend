package opensgs.sistema.logica.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.enums.Elemento;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.logica.servicios.ServicioFechaHora;
import opensgs.logica.servicios.ServicioListas;
import opensgs.sistema.datatypes.DtAnuncio;
import opensgs.sistema.datatypes.DtAplicacion;
import opensgs.sistema.datatypes.DtArchivo;
import opensgs.sistema.datatypes.DtConstancia;
import opensgs.sistema.datatypes.DtNotificacion;
import opensgs.sistema.datatypes.DtPreguntaFrecuente;
import opensgs.sistema.datatypes.DtReporte;
import opensgs.sistema.persistencia.manejadores.ManejadorPlantillaAplicacion;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(
        name = "aplicacion",
        schema = "sistema"
)
public class Aplicacion extends OpenSGSManagedBean {

    @Column(unique = true, nullable = false, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 128)
    private String nombre;

    @Column(nullable = false, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 256)
    private String titulo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @NotNull
    private Date apertura;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @NotNull
    private Date cierre;

    @Column(nullable = false, length = 128)
    @NotNull
    @Size(min = 5, max = 128)
    @Email
    private String contacto;

    @Column(nullable = false)
    @NotNull
    @Positive
    @Max(100)
    private Integer solicitudesPorUsuario;

    @Column(nullable = false)
    @NotNull
    @Positive
    @Max(10000)
    private Integer solicitudesTotal;

    @JoinTable(
            schema = "sistema",
            uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"aplicacion_id", "anuncios_id"}
                )
            }
    )
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    private List<Anuncio> anuncios = new ArrayList<>();

    @JoinTable(
            schema = "sistema",
            uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"aplicacion_id", "notificaciones_id"}
                )
            }
    )
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Notificacion> notificaciones = new ArrayList<>();

    @JoinTable(
            schema = "sistema",
            uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"aplicacion_id", "preguntasfrecuentes_id"}
                )
            }
    )
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<PreguntaFrecuente> preguntasFrecuentes = new ArrayList<>();

    @JoinTable(
            schema = "sistema",
            uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"aplicacion_id", "reportes_id"}
                )
            }
    )
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Reporte> reportes = new ArrayList<>();

    @JoinTable(
            schema = "sistema",
            uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"aplicacion_id", "archivos_id"}
                )
            }
    )
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Archivo> archivos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    @NotNull
    private PlantillaAplicacion plantillaAplicacion;

    public Aplicacion() {
    }

    public Aplicacion(DtOpenSGSBean dtOpenSGSBean) {
        this((DtAplicacion) dtOpenSGSBean);
    }

    public Aplicacion(DtAplicacion dtAplicacion) {
        super();
        this.nombre = dtAplicacion.getNombre();
        this.titulo = dtAplicacion.getTitulo();
        this.apertura = ServicioFechaHora.getInstance().stringToDateTime(dtAplicacion.getApertura());
        this.cierre = ServicioFechaHora.getInstance().stringToDateTime(dtAplicacion.getCierre());
        this.contacto = dtAplicacion.getContacto();
        this.solicitudesPorUsuario = dtAplicacion.getSolicitudesPorUsuario();
        this.solicitudesTotal = dtAplicacion.getSolicitudesTotal();
        this.anuncios = ServicioListas.getInstance().dataTypeToBean(dtAplicacion.getDtAnuncios());
        this.notificaciones = ServicioListas.getInstance().dataTypeToBean(dtAplicacion.getDtNotificaciones());
        this.preguntasFrecuentes = ServicioListas.getInstance().dataTypeToBean(dtAplicacion.getDtPreguntasFrecuentes());
        this.reportes = ServicioListas.getInstance().dataTypeToBean(dtAplicacion.getDtReportes());
        this.archivos = ServicioListas.getInstance().dataTypeToBean(dtAplicacion.getDtArchivos());
        this.plantillaAplicacion = ManejadorPlantillaAplicacion.getInstance().obtenerPlantillaAplicacion(dtAplicacion.getDtPlantillaAplicacion());
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

    public Date getApertura() {
        return apertura;
    }

    public void setApertura(Date apertura) {
        this.apertura = apertura;
    }

    public Date getCierre() {
        return cierre;
    }

    public void setCierre(Date cierre) {
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

    public List<Anuncio> getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(List<Anuncio> anuncios) {
        this.anuncios = anuncios;
    }

    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }

    public List<PreguntaFrecuente> getPreguntasFrecuentes() {
        return preguntasFrecuentes;
    }

    public void setPreguntasFrecuentes(List<PreguntaFrecuente> preguntasFrecuentes) {
        this.preguntasFrecuentes = preguntasFrecuentes;
    }

    public List<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }

    public List<Archivo> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<Archivo> archivos) {
        this.archivos = archivos;
    }

    public PlantillaAplicacion getPlantillaAplicacion() {
        return plantillaAplicacion;
    }

    public void setPlantillaAplicacion(PlantillaAplicacion plantillaAplicacion) {
        this.plantillaAplicacion = plantillaAplicacion;
    }

    @Override
    public void modificar(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        DtAplicacion dtAplicacion = (DtAplicacion) dtOpenSGSManagedBean;
        this.nombre = dtAplicacion.getNombre();
        this.titulo = dtAplicacion.getTitulo();
        this.apertura = ServicioFechaHora.getInstance().stringToDateTime(dtAplicacion.getApertura());
        this.cierre = ServicioFechaHora.getInstance().stringToDateTime(dtAplicacion.getCierre());
        this.contacto = dtAplicacion.getContacto();
        this.solicitudesPorUsuario = dtAplicacion.getSolicitudesPorUsuario();
        this.solicitudesTotal = dtAplicacion.getSolicitudesTotal();
        this.plantillaAplicacion = ManejadorPlantillaAplicacion.getInstance().obtenerPlantillaAplicacion(dtAplicacion.getDtPlantillaAplicacion());
    }

    @Override
    public boolean agregarListOpenSGSBean(OpenSGSBean openSGSBean) {
        switch (openSGSBean.getOpenSGSElemento()) {
            case Anuncio:
                Anuncio anuncio = (Anuncio) openSGSBean;
                return ServicioListas.getInstance().agregarElemento(anuncios, anuncio);
            case Notificacion:
                Notificacion notificacion = (Notificacion) openSGSBean;
                return ServicioListas.getInstance().agregarElemento(notificaciones, notificacion);
            case PreguntaFrecuente:
                PreguntaFrecuente preguntaFrecuente = (PreguntaFrecuente) openSGSBean;
                return ServicioListas.getInstance().agregarElemento(preguntasFrecuentes, preguntaFrecuente);
            case Reporte:
                Reporte reporte = (Reporte) openSGSBean;
                return ServicioListas.getInstance().agregarElemento(reportes, reporte);
            case Archivo:
                Archivo archivo = (Archivo) openSGSBean;
                return ServicioListas.getInstance().agregarElemento(archivos, archivo);
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean quitarListOpenSGSBean(OpenSGSBean openSGSBean) {
        switch (openSGSBean.getOpenSGSElemento()) {
            case Anuncio:
                Anuncio anuncio = (Anuncio) openSGSBean;
                return ServicioListas.getInstance().quitarElemento(anuncios, anuncio);
            case Notificacion:
                Notificacion notificacion = (Notificacion) openSGSBean;
                return ServicioListas.getInstance().quitarElemento(notificaciones, notificacion);
            case PreguntaFrecuente:
                PreguntaFrecuente preguntaFrecuente = (PreguntaFrecuente) openSGSBean;
                return ServicioListas.getInstance().quitarElemento(preguntasFrecuentes, preguntaFrecuente);
            case Reporte:
                Reporte reporte = (Reporte) openSGSBean;
                return ServicioListas.getInstance().quitarElemento(reportes, reporte);
            case Archivo:
                Archivo archivo = (Archivo) openSGSBean;
                return ServicioListas.getInstance().quitarElemento(archivos, archivo);
            default:
                break;
        }
        return false;
    }

    public DtAplicacion getDtAplicacion() {

        List<DtAnuncio> listaDtAnuncios = new ArrayList<>();
        for (Anuncio anuncio : this.anuncios) {
            listaDtAnuncios.add(anuncio.getDtAnuncio());
        }

        List<DtNotificacion> listaDtNotificaciones = new ArrayList<>();
        for (Notificacion notificacion : this.notificaciones) {
            listaDtNotificaciones.add(notificacion.getDtNotificacion());
        }

        List<DtPreguntaFrecuente> listaDtPreguntasFrecuentes = new ArrayList<>();
        for (PreguntaFrecuente preguntaFrecuente : this.preguntasFrecuentes) {
            listaDtPreguntasFrecuentes.add(preguntaFrecuente.getDtPreguntaFrecuente());
        }

        List<DtReporte> listaDtReportes = new ArrayList<>();
        for (Reporte reporte : this.reportes) {
            listaDtReportes.add(reporte.getDtReporte());
        }

        List<DtArchivo> listaDtArchivos = new ArrayList<>();
        for (Archivo archivo : this.archivos) {
            listaDtArchivos.add(archivo.getDtArchivo());
        }

        return new DtAplicacion(
                nombre,
                titulo,
                ServicioFechaHora.getInstance().dateTimeToString(apertura),
                ServicioFechaHora.getInstance().dateTimeToString(cierre),
                contacto,
                solicitudesPorUsuario,
                solicitudesTotal,
                plantillaAplicacion.getDtPlantillaAplicacion(),
                this.isActivo(),
                this.isBorrado(),
                this.isAdministrable(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getModificado()),
                this.getId(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getCreacion()),
                listaDtAnuncios,
                listaDtNotificaciones,
                listaDtPreguntasFrecuentes,
                listaDtReportes,
                listaDtArchivos
        );
    }

    public DtAplicacion getDatosBasicosAplicacion() {
        return new DtAplicacion(
                nombre,
                titulo,
                ServicioFechaHora.getInstance().dateTimeToString(apertura),
                ServicioFechaHora.getInstance().dateTimeToString(cierre),
                contacto,
                plantillaAplicacion.getDtPlantillaAplicacion(),
                this.isActivo(),
                this.isBorrado(),
                this.isAdministrable(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getModificado()),
                this.getId(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getCreacion())
        );
    }

    public DtAplicacion getDtAplicacionSesion() {
        return new DtAplicacion(
                nombre,
                titulo,
                ServicioFechaHora.getInstance().dateTimeToString(apertura),
                ServicioFechaHora.getInstance().dateTimeToString(cierre),
                contacto,
                this.isActivo(),
                this.isBorrado(),
                this.isAdministrable(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getModificado()),
                this.getId(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getCreacion())
        );
    }

    @Override
    public DtAplicacion getDtOpenSGSBean() {
        return this.getDtAplicacion();
    }

    @Override
    public DtAplicacion getDataType() {
        return this.getDtAplicacion();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.Aplicacion;
    }

    @Override
    public String toString() {
        return super.toString() + "Aplicacion{" + "nombre=" + nombre + ", titulo=" + titulo + ", apertura=" + apertura + ", cierre=" + cierre + ", contacto=" + contacto + ", solicitudesPorUsuario=" + solicitudesPorUsuario + ", solicitudesTotal=" + solicitudesTotal + ", anuncios=" + anuncios + ", notificaciones=" + notificaciones + ", preguntasFrecuentes=" + preguntasFrecuentes + ", reportes=" + reportes + ", archivos=" + archivos + ", plantillaAplicacion=" + plantillaAplicacion + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.nombre);
        hash = 79 * hash + Objects.hashCode(this.titulo);
        hash = 79 * hash + Objects.hashCode(this.apertura);
        hash = 79 * hash + Objects.hashCode(this.cierre);
        hash = 79 * hash + Objects.hashCode(this.contacto);
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
        final Aplicacion other = (Aplicacion) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.contacto, other.contacto)) {
            return false;
        }
        if (!Objects.equals(this.apertura, other.apertura)) {
            return false;
        }
        return Objects.equals(this.cierre, other.cierre);
    }

}
