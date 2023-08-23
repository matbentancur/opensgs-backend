package opensgs.sistema.logica.beans;

import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.enums.Elemento;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.servicios.ServicioFechaHora;
import opensgs.sistema.datatypes.DtDestinatario;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(
        name = "destinatario",
        schema = "sistema"
)
public class Destinatario extends OpenSGSBean {

    @Column(unique = true, nullable = false, length = 128)
    @NotNull
    @Size(min = 5, max = 128)
    @Email
    private String email;

    public Destinatario() {
    }

    public Destinatario(DtOpenSGSBean dtOpenSGSBean) {
        this((DtDestinatario) dtOpenSGSBean);
    }

    public Destinatario(DtDestinatario dtDestinatario) {
        super();
        this.email = dtDestinatario.getEmail();
    }

    public Destinatario(String email) {
        super();
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DtDestinatario getDtDestinatario() {
        return new DtDestinatario(
                email,
                this.getId(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getCreacion())
        );
    }

    @Override
    public DtDestinatario getDtOpenSGSBean() {
        return this.getDtDestinatario();
    }

    @Override
    public DtDestinatario getDataType() {
        return this.getDtDestinatario();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.Destinatario;
    }

    @Override
    public String toString() {
        return super.toString() + "Destinatario{" + "email=" + email + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.email);
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
        final Destinatario other = (Destinatario) obj;
        return Objects.equals(this.email, other.email);
    }

}
