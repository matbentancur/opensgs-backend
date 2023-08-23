package opensgs.sistema.logica.beans;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
import opensgs.sistema.datatypes.DtPaginaAplicacion;
import opensgs.sistema.persistencia.manejadores.ManejadorPlantillaAplicacion;
import opensgs.solicitudes.enums.EsctructuraDatos;
import opensgs.solicitudes.logica.servicios.ServicioPaginaSolicitud;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(
        name = "paginaAplicacion",
        schema = "sistema"
)
public class PaginaAplicacion extends OpenSGSManagedBean {

    @Column(unique = true, nullable = false, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 128)
    private String nombre;

    @Column(nullable = false, length = 256)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 256)
    private String titulo;

    @Column(nullable = true, columnDefinition = "varchar")
    @Size(min = 0, max = 1000)
    private String textoInicial;

    @Column(nullable = true, columnDefinition = "varchar")
    @Size(min = 0, max = 1000)
    private String textoFinal;

    @Column(nullable = false)
    @NotNull
    @Positive
    @Max(100)
    private Integer pagina;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 128)
    @NotNull
    private EsctructuraDatos esctructuraDatos;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    @NotNull
    private PlantillaAplicacion plantillaAplicacion;

    public PaginaAplicacion() {
    }

    public PaginaAplicacion(DtOpenSGSBean dtOpenSGSBean) {
        this((DtPaginaAplicacion) dtOpenSGSBean);
    }

    public PaginaAplicacion(DtPaginaAplicacion dtPaginaAplicacion) {
        super();
        this.nombre = dtPaginaAplicacion.getNombre();
        this.titulo = dtPaginaAplicacion.getTitulo();
        this.textoInicial = dtPaginaAplicacion.getTextoInicial();
        this.textoFinal = dtPaginaAplicacion.getTextoFinal();
        this.pagina = dtPaginaAplicacion.getPagina();
        this.esctructuraDatos = ServicioPaginaSolicitud.getInstance().parseEsctructuraDatos(dtPaginaAplicacion.getEsctructuraDatos());
        this.plantillaAplicacion = ManejadorPlantillaAplicacion.getInstance().obtenerPlantillaAplicacion(dtPaginaAplicacion.getDtPlantillaAplicacion());
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

    public String getTextoInicial() {
        return textoInicial;
    }

    public void setTextoInicial(String textoInicial) {
        this.textoInicial = textoInicial;
    }

    public String getTextoFinal() {
        return textoFinal;
    }

    public void setTextoFinal(String textoFinal) {
        this.textoFinal = textoFinal;
    }

    public Integer getPagina() {
        return pagina;
    }

    public void setPagina(Integer pagina) {
        this.pagina = pagina;
    }

    public EsctructuraDatos getEsctructuraDatos() {
        return esctructuraDatos;
    }

    public void setEsctructuraDatos(EsctructuraDatos esctructuraDatos) {
        this.esctructuraDatos = esctructuraDatos;
    }

    public PlantillaAplicacion getPlantillaAplicacion() {
        return plantillaAplicacion;
    }

    public void setPlantillaAplicacion(PlantillaAplicacion plantillaAplicacion) {
        this.plantillaAplicacion = plantillaAplicacion;
    }

    @Override
    public void modificar(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        DtPaginaAplicacion dtPaginaAplicacion = (DtPaginaAplicacion) dtOpenSGSManagedBean;
        this.nombre = dtPaginaAplicacion.getNombre();
        this.titulo = dtPaginaAplicacion.getTitulo();
        this.textoInicial = dtPaginaAplicacion.getTextoInicial();
        this.textoFinal = dtPaginaAplicacion.getTextoFinal();
        this.pagina = dtPaginaAplicacion.getPagina();
        this.esctructuraDatos = ServicioPaginaSolicitud.getInstance().parseEsctructuraDatos(dtPaginaAplicacion.getEsctructuraDatos());
    }

    @Override
    public boolean agregarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    @Override
    public boolean quitarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    public DtPaginaAplicacion getDtPaginaAplicacion() {
        return new DtPaginaAplicacion(
                nombre,
                titulo,
                textoInicial,
                textoFinal,
                pagina,
                esctructuraDatos.toString(),
                plantillaAplicacion.getDtPlantillaAplicacion(),
                this.isActivo(),
                this.isBorrado(),
                this.isAdministrable(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getModificado()),
                this.getId(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getCreacion())
        );
    }

    @Override
    public DtPaginaAplicacion getDtOpenSGSBean() {
        return this.getDtPaginaAplicacion();
    }

    @Override
    public DtPaginaAplicacion getDataType() {
        return this.getDtPaginaAplicacion();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.PaginaAplicacion;
    }

    @Override
    public String toString() {
        return super.toString() + "PaginaAplicacion{" + "nombre=" + nombre + ", titulo=" + titulo + ", textoInicial=" + textoInicial + ", textoFinal=" + textoFinal + ", pagina=" + pagina + ", esctructuraDatos=" + esctructuraDatos + ", plantillaAplicacion=" + plantillaAplicacion + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.nombre);
        hash = 53 * hash + Objects.hashCode(this.titulo);
        hash = 53 * hash + Objects.hashCode(this.textoInicial);
        hash = 53 * hash + Objects.hashCode(this.textoFinal);
        hash = 53 * hash + Objects.hashCode(this.pagina);
        hash = 53 * hash + Objects.hashCode(this.esctructuraDatos);
        hash = 53 * hash + Objects.hashCode(this.plantillaAplicacion);
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
        final PaginaAplicacion other = (PaginaAplicacion) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.textoInicial, other.textoInicial)) {
            return false;
        }
        if (!Objects.equals(this.textoFinal, other.textoFinal)) {
            return false;
        }
        if (!Objects.equals(this.pagina, other.pagina)) {
            return false;
        }
        if (this.esctructuraDatos != other.esctructuraDatos) {
            return false;
        }
        return Objects.equals(this.plantillaAplicacion, other.plantillaAplicacion);
    }

}
