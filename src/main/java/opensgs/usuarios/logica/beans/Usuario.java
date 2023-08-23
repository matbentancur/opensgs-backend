package opensgs.usuarios.logica.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import opensgs.usuarios.datatypes.DtUsuario;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import opensgs.usuarios.datatypes.DtRegistrarUsuario;
import opensgs.usuarios.enums.DocumentoTipo;
import opensgs.logica.servicios.ServicioPassword;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.enums.Elemento;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.logica.servicios.ServicioEnum;
import opensgs.logica.servicios.ServicioFechaHora;
import opensgs.logica.servicios.ServicioListas;
import opensgs.sistema.logica.beans.Aplicacion;
import opensgs.usuarios.datatypes.DtPerfilAplicacion;
import opensgs.usuarios.persistencia.manejadores.ManejadorPerfil;
import opensgs.usuarios.persistencia.manejadores.ManejadorPerfilAplicacion;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(
        name = "usuario",
        schema = "usuarios"
)
public class Usuario extends OpenSGSManagedBean {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 64)
    @NotNull
    private DocumentoTipo documentoTipo;

    @Column(unique = true, nullable = false, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 7, max = 128)
    private String documento;

    @Column(unique = true, nullable = false, length = 128)
    @NotNull
    @Size(min = 5, max = 128)
    @Email
    private String email;

    @Column(nullable = false, length = 128)
    private String clave;

    @Column(nullable = false, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 2, max = 128)
    private String nombres;

    @Column(nullable = false, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 2, max = 128)
    private String apellidos;

    @Column(nullable = false, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 8, max = 128)
    private String telefono;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    @NotNull
    private Perfil perfil;

    @JoinTable(schema = "usuarios")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<PerfilAplicacion> perfilesAplicacion = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(DtOpenSGSBean dtOpenSGSBean) {
        this((DtUsuario) dtOpenSGSBean);
    }

    public Usuario(DtUsuario dtUsuario) {
        super();
        this.documentoTipo = ServicioEnum.getInstance().parseEnum(DocumentoTipo.class, dtUsuario.getDocumentoTipo());
        this.documento = dtUsuario.getDocumento();
        this.email = dtUsuario.getEmail();
        this.getRandomPassword();
        this.cifrarPassword();
        this.nombres = dtUsuario.getNombres();
        this.apellidos = dtUsuario.getApellidos();
        this.telefono = dtUsuario.getTelefono();
        this.perfil = ManejadorPerfil.getInstance().obtenerPerfil(dtUsuario.getDtPerfil());
    }

    public Usuario(DtRegistrarUsuario dtRegistrarUsuario) {
        this.setActivo(true);
        this.setBorrado(false);
        this.setAdministrable(true);
        this.documentoTipo = ServicioEnum.getInstance().parseEnum(DocumentoTipo.class, dtRegistrarUsuario.getDocumentoTipo());
        this.documento = dtRegistrarUsuario.getDocumento();
        this.email = dtRegistrarUsuario.getEmail();
        this.clave = dtRegistrarUsuario.getClave();
        this.nombres = dtRegistrarUsuario.getNombres();
        this.apellidos = dtRegistrarUsuario.getApellidos();
        this.telefono = dtRegistrarUsuario.getTelefono();
        this.perfil = ManejadorPerfil.getInstance().obtenerPerfil("nombre", "Usuario");
    }

    public DocumentoTipo getDocumentoTipo() {
        return documentoTipo;
    }

    public void setDocumentoTipo(DocumentoTipo documentoTipo) {
        this.documentoTipo = documentoTipo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public List<PerfilAplicacion> getPerfilesAplicacion() {
        return perfilesAplicacion;
    }

    public void setPerfilesAplicacion(List<PerfilAplicacion> perfilesAplicacion) {
        this.perfilesAplicacion = perfilesAplicacion;
    }

    public boolean agregarPerfilAplicacion(Perfil perfil, Aplicacion aplicacion) {
        PerfilAplicacion perfilAplicacion = ManejadorPerfilAplicacion.getInstance().obtenerPerfilAplicacion(perfil, aplicacion);
        return this.agregarListOpenSGSBean(perfilAplicacion);
    }

    public boolean quitarPerfilAplicacion(Perfil perfil, Aplicacion aplicacion) {
        PerfilAplicacion perfilAplicacion = ManejadorPerfilAplicacion.getInstance().obtenerPerfilAplicacion(perfil, aplicacion);
        return this.quitarListOpenSGSBean(perfilAplicacion);
    }

    public boolean buscarPerfilAplicacion(PerfilAplicacion perfilAplicacion) {
        return this.perfilesAplicacion.contains(perfilAplicacion);
    }

    public boolean buscarPerfilAplicacion(Aplicacion aplicacion) {
        for (PerfilAplicacion perfilAplicacion : perfilesAplicacion) {
            if(perfilAplicacion.getAplicacion().equals(aplicacion)){
                return true;
            }
        }
        return false;
    }

    public boolean buscarPermisoEnPerfilAplicacion(Permiso permiso) {
        for (PerfilAplicacion perfilAplicacion : perfilesAplicacion) {
            if(perfilAplicacion.getPerfil().buscarPermiso(permiso)){
                return true;
            }
        }
        return false;
    }

    public PerfilAplicacion obtenerPerfilAplicacion(Aplicacion aplicacion) {
        for (PerfilAplicacion perfilAplicacion : perfilesAplicacion) {
            if(perfilAplicacion.getAplicacion().equals(aplicacion)){
                return perfilAplicacion;
            }
        }
        return null;
    }

    @Override
    public void modificar(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        DtUsuario dtUsuario = (DtUsuario) dtOpenSGSManagedBean;
        this.documentoTipo = ServicioEnum.getInstance().parseEnum(DocumentoTipo.class, dtUsuario.getDocumentoTipo());
        this.documento = dtUsuario.getDocumento();
        this.email = dtUsuario.getEmail();
        this.nombres = dtUsuario.getNombres();
        this.apellidos = dtUsuario.getApellidos();
        this.telefono = dtUsuario.getTelefono();
        this.perfil = ManejadorPerfil.getInstance().obtenerPerfil(dtUsuario.getDtPerfil());
    }

    @Override
    public boolean agregarListOpenSGSBean(OpenSGSBean openSGSBean) {
        PerfilAplicacion perfilAplicacion = (PerfilAplicacion) openSGSBean;
        return ServicioListas.getInstance().agregarElemento(perfilesAplicacion, perfilAplicacion);
    }

    @Override
    public boolean quitarListOpenSGSBean(OpenSGSBean openSGSBean) {
        PerfilAplicacion perfilAplicacion = (PerfilAplicacion) openSGSBean;
        return ServicioListas.getInstance().quitarElemento(perfilesAplicacion, perfilAplicacion);
    }

    public boolean modificarMisDatos(DtUsuario dtUsuario) {
        if (this.isAdministrable()) {
            this.email = dtUsuario.getEmail();
            this.nombres = dtUsuario.getNombres();
            this.apellidos = dtUsuario.getApellidos();
            this.telefono = dtUsuario.getTelefono();
            return true;
        }
        return false;
    }

    public void modificarMiClave(String clave) {
        this.clave = clave;
        this.cifrarPassword();
    }

    public void cifrarPassword() {
        this.clave = ServicioPassword.getInstance().hashPassword(this.clave);
    }

    private void getRandomPassword() {
        this.clave = ServicioPassword.getInstance().getRandomPassword(20);
    }

    public DtUsuario getDtUsuario() {
        List<DtPerfilAplicacion> listaDtPerfilesAplicacion = ServicioListas.getInstance().beanToDataType(perfilesAplicacion);

        return new DtUsuario(
                documentoTipo.toString(),
                documento,
                email,
                nombres,
                apellidos,
                telefono,
                this.getPerfil().getDtPerfil(),
                this.isActivo(),
                this.isBorrado(),
                this.isAdministrable(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getModificado()),
                this.getId(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getCreacion()),
                listaDtPerfilesAplicacion
        );
    }

    public DtUsuario getDtUsuarioSesion() {
        List<DtPerfilAplicacion> listaDtPerfilesAplicacion = new ArrayList<>();

        if (perfilesAplicacion != null) {
            if (!perfilesAplicacion.isEmpty()) {
                for (PerfilAplicacion perfilAplicacion : perfilesAplicacion) {
                    listaDtPerfilesAplicacion.add(perfilAplicacion.getDtPerfilAplicacionSesion());
                }
            }
        }

        return new DtUsuario(
                documentoTipo.toString(),
                documento,
                email,
                nombres,
                apellidos,
                telefono,
                this.getPerfil().getDtPerfil(),
                this.isActivo(),
                this.isBorrado(),
                this.isAdministrable(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getModificado()),
                this.getId(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getCreacion()),
                listaDtPerfilesAplicacion
        );
    }

    @Override
    public DtOpenSGSBean getDtOpenSGSBean() {
        return this.getDtUsuario();
    }

    @Override
    public DtOpenSGSBean getDataType() {
        return this.getDtUsuario();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.Usuario;
    }

    @Override
    public String toString() {
        return super.toString() + "Usuario{" + "documentoTipo=" + documentoTipo + ", documento=" + documento + ", email=" + email + ", clave=" + clave + ", nombres=" + nombres + ", apellidos=" + apellidos + ", telefono=" + telefono + ", perfil=" + perfil + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.documentoTipo);
        hash = 97 * hash + Objects.hashCode(this.documento);
        hash = 97 * hash + Objects.hashCode(this.email);
        hash = 97 * hash + Objects.hashCode(this.clave);
        hash = 97 * hash + Objects.hashCode(this.nombres);
        hash = 97 * hash + Objects.hashCode(this.apellidos);
        hash = 97 * hash + Objects.hashCode(this.telefono);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.documento, other.documento)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.clave, other.clave)) {
            return false;
        }
        if (!Objects.equals(this.nombres, other.nombres)) {
            return false;
        }
        if (!Objects.equals(this.apellidos, other.apellidos)) {
            return false;
        }
        if (!Objects.equals(this.telefono, other.telefono)) {
            return false;
        }
        return this.documentoTipo == other.documentoTipo;
    }

}
