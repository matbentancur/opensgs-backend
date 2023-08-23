package opensgs.sistema.logica.beans;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import opensgs.sistema.datatypes.DtSoporte;

public class Soporte {

    @NotNull
    @Size(min = 5, max = 128)
    @Email
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 128)
    private String nombres;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 128)
    private String apellidos;

    @NotNull
    @NotBlank
    @Size(min = 8, max = 128)
    private String telefono;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 1024)
    private String descripcion;

    public Soporte() {
    }

    public Soporte(DtSoporte dtSoporte) {
        this.email = dtSoporte.getEmail();
        this.nombres = dtSoporte.getNombres();
        this.apellidos = dtSoporte.getApellidos();
        this.telefono = dtSoporte.getTelefono();
        this.descripcion = dtSoporte.getDescripcion();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public DtSoporte getDtSoporte() {
        return new DtSoporte(
                email,
                nombres,
                apellidos,
                telefono,
                descripcion
        );
    }

    @Override
    public String toString() {
        return "Soporte{" + "email=" + email + ", nombres=" + nombres + ", apellidos=" + apellidos + ", telefono=" + telefono + ", descripcion=" + descripcion + '}';
    }

}
