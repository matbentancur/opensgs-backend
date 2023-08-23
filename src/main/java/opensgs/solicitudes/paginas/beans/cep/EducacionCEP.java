package opensgs.solicitudes.paginas.beans.cep;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.enums.Elemento;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.servicios.ServicioFechaHora;
import opensgs.solicitudes.datatypes.DtPaginaSolicitud;
import opensgs.solicitudes.logica.beans.PaginaSolicitud;
import opensgs.solicitudes.paginas.datatypes.cep.DtEducacionCEP;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(
        name = "educacionCEP",
        schema = "cep"
)
public class EducacionCEP extends PaginaSolicitud {

    @Column(nullable = true, columnDefinition = "varchar")
    @NotNull
    @NotBlank
    @Size(min = 2, max = 256)
    private String ultimoNivelEducativo;

    @Column(nullable = true, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 2, max = 128)
    private String tituloGrado;

    @Column(nullable = true)
    @NotNull
    @Positive
    @Min(1900)
    @Max(2500)
    private Integer anioRecibio;

    @Column(nullable = true, length = 128)
    @Size(min = 2, max = 128)
    private String institucionTitulo;

    public EducacionCEP() {
    }

    public EducacionCEP(DtPaginaSolicitud dtPaginaSolicitud) {
        this((DtEducacionCEP) dtPaginaSolicitud);
    }

    public EducacionCEP(DtEducacionCEP dtEducacionCEP) {
        super();
        this.ultimoNivelEducativo = dtEducacionCEP.getUltimoNivelEducativo();
        this.tituloGrado = dtEducacionCEP.getTituloGrado();
        this.anioRecibio = dtEducacionCEP.getAnioRecibio();
        this.institucionTitulo = dtEducacionCEP.getInstitucionTitulo();
    }

    public String getUltimoNivelEducativo() {
        return ultimoNivelEducativo;
    }

    public void setUltimoNivelEducativo(String ultimoNivelEducativo) {
        this.ultimoNivelEducativo = ultimoNivelEducativo;
    }

    public String getTituloGrado() {
        return tituloGrado;
    }

    public void setTituloGrado(String tituloGrado) {
        this.tituloGrado = tituloGrado;
    }

    public Integer getAnioRecibio() {
        return anioRecibio;
    }

    public void setAnioRecibio(Integer anioRecibio) {
        this.anioRecibio = anioRecibio;
    }

    public String getInstitucionTitulo() {
        return institucionTitulo;
    }

    public void setInstitucionTitulo(String institucionTitulo) {
        this.institucionTitulo = institucionTitulo;
    }

    public DtEducacionCEP getDtEducacionCEP() {
        return new DtEducacionCEP(
                ultimoNivelEducativo,
                tituloGrado,
                anioRecibio,
                institucionTitulo,
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
    public DtEducacionCEP getDtOpenSGSBean() {
        return this.getDtEducacionCEP();
    }

    @Override
    public DtEducacionCEP getDataType() {
        return this.getDtEducacionCEP();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.PaginaSolicitud;
    }

    @Override
    public void modificar(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        DtEducacionCEP dtEducacionCEP = (DtEducacionCEP) dtOpenSGSManagedBean;
        this.ultimoNivelEducativo = dtEducacionCEP.getUltimoNivelEducativo();
        this.tituloGrado = dtEducacionCEP.getTituloGrado();
        this.anioRecibio = dtEducacionCEP.getAnioRecibio();
        this.institucionTitulo = dtEducacionCEP.getInstitucionTitulo();
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
