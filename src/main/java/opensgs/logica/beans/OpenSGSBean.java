package opensgs.logica.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.enums.Elemento;
import org.hibernate.annotations.CreationTimestamp;

@MappedSuperclass
public abstract class OpenSGSBean implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date creacion;

    public OpenSGSBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreacion() {
        return creacion;
    }

    public void setCreacion(Date creacion) {
        this.creacion = creacion;
    }

    public String getIdentificadorString() {
        return id.toString();
    }

    public abstract DtOpenSGSBean getDtOpenSGSBean();

    public abstract DtOpenSGSBean getDataType();

    public abstract Elemento getOpenSGSElemento();

    @Override
    public String toString() {
        return "OpenSGSBean{" + "id=" + id + ", creacion=" + creacion + '}';
    }
}
