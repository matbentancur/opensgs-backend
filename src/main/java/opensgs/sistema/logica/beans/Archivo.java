package opensgs.sistema.logica.beans;

import java.util.Objects;
import opensgs.sistema.datatypes.DtArchivo;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.sistema.enums.AlcanceArchivo;
import opensgs.enums.Elemento;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.logica.servicios.ServicioEnum;
import opensgs.logica.servicios.ServicioFechaHora;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(
        name = "archivo",
        schema = "sistema"
)
public class Archivo extends OpenSGSManagedBean {

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

    @Column(nullable = false, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 128)
    private String mime;

    @Column(nullable = false, length = 16)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 16)
    private String extension;

    @Column(nullable = false)
    @NotNull
    @Positive
    @Max(524288000)
    private Long peso;

    @Column(nullable = false, columnDefinition = "varchar")
    @NotNull
    @NotBlank
    private String ubicacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 128)
    @NotNull
    private AlcanceArchivo alcanceArchivo;

    public Archivo() {
    }

    public Archivo(DtOpenSGSBean dtOpenSGSBean) {
        this((DtArchivo) dtOpenSGSBean);
    }

    public Archivo(DtArchivo dtArchivo) {
        super();
        this.nombre = dtArchivo.getNombre();
        this.titulo = dtArchivo.getTitulo();
        this.mime = dtArchivo.getMime();
        this.extension = dtArchivo.getExtension();
        this.peso = dtArchivo.getPeso();
        this.ubicacion = dtArchivo.getUbicacion();
        this.alcanceArchivo = ServicioEnum.getInstance().parseEnum(AlcanceArchivo.class, dtArchivo.getAlcanceArchivo());
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

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Long getPeso() {
        return peso;
    }

    public void setPeso(Long peso) {
        this.peso = peso;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public AlcanceArchivo getAlcanceArchivo() {
        return alcanceArchivo;
    }

    public void setAlcanceArchivo(AlcanceArchivo alcanceArchivo) {
        this.alcanceArchivo = alcanceArchivo;
    }

    public boolean esPublico() {
        switch (this.getAlcanceArchivo()) {
            case GLOBAL:
                return true;
            case SISTEMA:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void modificar(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        DtArchivo dtArchivo = (DtArchivo) dtOpenSGSManagedBean;
        this.nombre = dtArchivo.getNombre();
        this.titulo = dtArchivo.getTitulo();
        this.alcanceArchivo = ServicioEnum.getInstance().parseEnum(AlcanceArchivo.class, dtArchivo.getAlcanceArchivo());
    }

    @Override
    public boolean agregarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    @Override
    public boolean quitarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    public DtArchivo getDtArchivo() {
        return new DtArchivo(
                nombre,
                titulo,
                mime,
                extension,
                peso,
                ubicacion,
                alcanceArchivo.toString(),
                this.isActivo(),
                this.isBorrado(),
                this.isAdministrable(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getModificado()),
                this.getId(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getCreacion())
        );
    }

    @Override
    public DtArchivo getDtOpenSGSBean() {
        return this.getDtArchivo();
    }

    @Override
    public DtArchivo getDataType() {
        return this.getDtArchivo();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.Archivo;
    }

    @Override
    public String toString() {
        return super.toString() + "Archivo{" + "nombre=" + nombre + ", titulo=" + titulo + ", mime=" + mime + ", extension=" + extension + ", peso=" + peso + ", ubicacion=" + ubicacion + ", alcanceArchivo=" + alcanceArchivo + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.nombre);
        hash = 97 * hash + Objects.hashCode(this.titulo);
        hash = 97 * hash + Objects.hashCode(this.mime);
        hash = 97 * hash + Objects.hashCode(this.extension);
        hash = 97 * hash + Objects.hashCode(this.peso);
        hash = 97 * hash + Objects.hashCode(this.ubicacion);
        hash = 97 * hash + Objects.hashCode(this.alcanceArchivo);
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
        final Archivo other = (Archivo) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.mime, other.mime)) {
            return false;
        }
        if (!Objects.equals(this.extension, other.extension)) {
            return false;
        }
        if (!Objects.equals(this.ubicacion, other.ubicacion)) {
            return false;
        }
        if (!Objects.equals(this.peso, other.peso)) {
            return false;
        }
        return this.alcanceArchivo == other.alcanceArchivo;
    }

}
