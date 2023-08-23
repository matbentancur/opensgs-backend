package opensgs.logica.beans;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import opensgs.datatypes.DtOpenSGSManagedBean;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public abstract class OpenSGSManagedBean extends OpenSGSBean {

    @Column(nullable = false)
    private boolean activo;

    @Column(nullable = false)
    private boolean borrado;

    @Column(nullable = false)
    private boolean administrable;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date modificado;

    public OpenSGSManagedBean() {
        super();
        this.activo = true;
        this.borrado = false;
        this.administrable = true;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public boolean isAdministrable() {
        return administrable;
    }

    public void setAdministrable(boolean administrable) {
        this.administrable = administrable;
    }

    public Date getModificado() {
        return modificado;
    }

    public void setModificado(Date modificado) {
        this.modificado = modificado;
    }

    public void activar() {
        this.setActivo(true);
    }

    public void desactivar() {
        this.setActivo(false);
    }

    public void borrar() {
        this.setBorrado(true);
    }

    public void restaurar() {
        this.setBorrado(false);

    }

    public abstract void modificar(DtOpenSGSManagedBean dtOpenSGSManagedBean);

    public abstract boolean agregarListOpenSGSBean(OpenSGSBean openSGSBean);

    public abstract boolean quitarListOpenSGSBean(OpenSGSBean openSGSBean);

    @Override
    public String toString() {
        return super.toString() + "OpenSGSManagedBean{" + "activo=" + activo + ", borrado=" + borrado + ", administrable=" + administrable + ", modificado=" + modificado + '}';
    }

}
