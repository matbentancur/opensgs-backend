package opensgs.sistema.logica.beans;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.sistema.datatypes.DtConstanciaElemento;
import opensgs.sistema.persistencia.manejadores.ManejadorConstancia;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@MappedSuperclass
public abstract class ConstanciaElemento extends OpenSGSManagedBean {

    @Column(nullable = false, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 128)
    private String nombre;

    @Column(nullable = false)
    @NotNull
    private Float posicionX;

    @Column(nullable = false)
    @NotNull
    private Float posicionY;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    @NotNull
    private Constancia constancia;

    public ConstanciaElemento() {
    }

    public ConstanciaElemento(DtOpenSGSBean dtOpenSGSBean) {
        this((DtConstanciaElemento) dtOpenSGSBean);
    }

    public ConstanciaElemento(DtConstanciaElemento dtConstanciaElemento) {
        super();
        this.nombre = dtConstanciaElemento.getNombre();
        this.posicionX = dtConstanciaElemento.getPosicionX();
        this.posicionY = dtConstanciaElemento.getPosicionY();
        this.constancia = ManejadorConstancia.getInstance().obtenerConstancia(dtConstanciaElemento.getDtConstancia());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(Float posicionX) {
        this.posicionX = posicionX;
    }

    public Float getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(Float posicionY) {
        this.posicionY = posicionY;
    }

    public Constancia getConstancia() {
        return constancia;
    }

    public void setConstancia(Constancia constancia) {
        this.constancia = constancia;
    }

    @Override
    public void modificar(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        DtConstanciaElemento dtConstanciaElemento = (DtConstanciaElemento) dtOpenSGSManagedBean;
        this.nombre = dtConstanciaElemento.getNombre();
        this.posicionX = dtConstanciaElemento.getPosicionX();
        this.posicionY = dtConstanciaElemento.getPosicionY();
    }

    @Override
    public String toString() {
        return super.toString() + "ConstanciaElemento{" + "nombre=" + nombre + ", posicionX=" + posicionX + ", posicionY=" + posicionY + ", constancia=" + constancia + '}';
    }

}
