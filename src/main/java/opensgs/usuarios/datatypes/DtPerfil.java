package opensgs.usuarios.datatypes;

import java.util.ArrayList;
import java.util.List;
import opensgs.datatypes.DtOpenSGSManagedBean;

public final class DtPerfil extends DtOpenSGSManagedBean {

    private String nombre;
    private String alcancePerfil;
    private List<DtPermiso> dtPermisos = new ArrayList<>();

    public DtPerfil() {
    }

    public DtPerfil(String nombre, String alcancePerfil, boolean activo, boolean borrado, boolean administrable, String modificado, Long id, String creacion, List<DtPermiso> dtPermisos) {
        super(activo, borrado, administrable, modificado, id, creacion);
        this.nombre = nombre;
        this.alcancePerfil = alcancePerfil;
        this.dtPermisos = dtPermisos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlcancePerfil() {
        return alcancePerfil;
    }

    public void setAlcancePerfil(String alcancePerfil) {
        this.alcancePerfil = alcancePerfil;
    }

    public List<DtPermiso> getDtPermisos() {
        return dtPermisos;
    }

    public void setDtPermisos(List<DtPermiso> dtPermisos) {
        this.dtPermisos = dtPermisos;
    }

    public void agregarDtPermiso(DtPermiso dtPermiso) {
        this.dtPermisos.add(dtPermiso);
    }

    public void quitarDtPermiso(DtPermiso dtPermiso) {
        this.dtPermisos.remove(dtPermiso);
    }

    @Override
    public String getClassName() {
        return "Perfil";
    }

    @Override
    public String toString() {
        return super.toString() + "DtPerfil{" + "nombre=" + nombre + ", alcancePerfil=" + alcancePerfil + ", dtPermisos=" + dtPermisos + '}';
    }

}
