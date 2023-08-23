package opensgs.usuarios.logica.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.usuarios.enums.AlcancePerfil;
import opensgs.enums.Elemento;
import opensgs.enums.Operacion;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.logica.servicios.ServicioEnum;
import opensgs.logica.servicios.ServicioFechaHora;
import opensgs.logica.servicios.ServicioListas;
import opensgs.usuarios.datatypes.DtPerfil;
import opensgs.usuarios.datatypes.DtPermiso;
import opensgs.usuarios.persistencia.manejadores.ManejadorPermiso;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(
        name = "perfil",
        schema = "usuarios"
)
public class Perfil extends OpenSGSManagedBean {

    @Column(unique = true, nullable = false, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 128)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 128)
    @NotNull
    private AlcancePerfil alcancePerfil;

    @JoinTable(schema = "usuarios")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Permiso> permisos = new ArrayList<>();

    public Perfil() {
    }

    public Perfil(DtOpenSGSBean dtOpenSGSBean) {
        this((DtPerfil) dtOpenSGSBean);
    }

    public Perfil(DtPerfil dtPerfil) {
        super();
        this.nombre = dtPerfil.getNombre();
        this.alcancePerfil = ServicioEnum.getInstance().parseEnum(AlcancePerfil.class, dtPerfil.getAlcancePerfil());
        this.permisos = ServicioListas.getInstance().dataTypeToBean(dtPerfil.getDtPermisos());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public AlcancePerfil getAlcancePerfil() {
        return alcancePerfil;
    }

    public void setAlcancePerfil(AlcancePerfil alcancePerfil) {
        this.alcancePerfil = alcancePerfil;
    }

    public List<Permiso> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<Permiso> permisos) {
        this.permisos = permisos;
    }

    public boolean agregarPermiso(Elemento elemento, Operacion operacion) {
        Permiso permiso = ManejadorPermiso.getInstance().obtenerPermiso(elemento, operacion);
        return this.agregarListOpenSGSBean(permiso);
    }

    public boolean quitarPermiso(Elemento elemento, Operacion operacion) {
        Permiso permiso = ManejadorPermiso.getInstance().obtenerPermiso(elemento, operacion);
        return this.quitarListOpenSGSBean(permiso);
    }

    public boolean buscarPermiso(Permiso permiso) {
        return this.permisos.contains(permiso);
    }

    public boolean esAdministrador() {
        if (this.getNombre().equalsIgnoreCase("Administrador")) {
            return true;
        } else if (this.getNombre().equalsIgnoreCase("Sistema")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean esSistema() {
        return this.getNombre().equalsIgnoreCase("Sistema");
    }

    @Override
    public void modificar(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        DtPerfil dtPerfil = (DtPerfil) dtOpenSGSManagedBean;
        this.nombre = dtPerfil.getNombre();
    }

    @Override
    public boolean agregarListOpenSGSBean(OpenSGSBean openSGSBean) {
        Permiso permiso = (Permiso) openSGSBean;
        return ServicioListas.getInstance().agregarElemento(permisos, permiso);
    }

    @Override
    public boolean quitarListOpenSGSBean(OpenSGSBean openSGSBean) {
        Permiso permiso = (Permiso) openSGSBean;
        return ServicioListas.getInstance().quitarElemento(permisos, permiso);
    }

    public DtPerfil getDtPerfil() {
        List<DtPermiso> listaDtPermiso = ServicioListas.getInstance().beanToDataType(permisos);

        return new DtPerfil(
                nombre,
                alcancePerfil.toString(),
                this.isActivo(),
                this.isBorrado(),
                this.isAdministrable(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getModificado()),
                this.getId(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getCreacion()),
                listaDtPermiso
        );
    }

    @Override
    public DtPerfil getDtOpenSGSBean() {
        return this.getDtPerfil();
    }

    @Override
    public DtPerfil getDataType() {
        return this.getDtPerfil();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.Perfil;
    }

    @Override
    public String toString() {
        return super.toString() + "Perfil{" + "nombre=" + nombre + ", alcancePerfil=" + alcancePerfil + ", permisos=" + permisos + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.nombre);
        hash = 59 * hash + Objects.hashCode(this.alcancePerfil);
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
        final Perfil other = (Perfil) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return this.alcancePerfil == other.alcancePerfil;
    }

}
