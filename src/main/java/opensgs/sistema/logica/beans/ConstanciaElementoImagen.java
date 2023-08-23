package opensgs.sistema.logica.beans;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.enums.Elemento;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.servicios.ServicioFechaHora;
import opensgs.sistema.datatypes.DtConstanciaElementoImagen;
import opensgs.sistema.persistencia.manejadores.ManejadorArchivo;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(
        name = "constanciaElementoImagen",
        schema = "sistema"
)
public class ConstanciaElementoImagen extends ConstanciaElemento {

    @Column(nullable = false)
    @NotNull
    @Positive
    private Float ancho;

    @Column(nullable = false)
    @NotNull
    @Positive
    private Float alto;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    private Archivo archivo;

    public ConstanciaElementoImagen() {
    }

    public ConstanciaElementoImagen(DtOpenSGSBean dtOpenSGSBean) {
        this((DtConstanciaElementoImagen) dtOpenSGSBean);
    }

    public ConstanciaElementoImagen(DtConstanciaElementoImagen dtConstanciaElementoImagen) {
        super(dtConstanciaElementoImagen);
        this.ancho = dtConstanciaElementoImagen.getAncho();
        this.alto = dtConstanciaElementoImagen.getAlto();
        this.archivo = ManejadorArchivo.getInstance().obtenerArchivo(dtConstanciaElementoImagen.getDtArchivo());
    }

    public Float getAncho() {
        return ancho;
    }

    public void setAncho(Float ancho) {
        this.ancho = ancho;
    }

    public Float getAlto() {
        return alto;
    }

    public void setAlto(Float alto) {
        this.alto = alto;
    }

    public Archivo getArchivo() {
        return archivo;
    }

    public void setArchivo(Archivo archivo) {
        this.archivo = archivo;
    }

    @Override
    public void modificar(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        DtConstanciaElementoImagen dtConstanciaElementoImagen = (DtConstanciaElementoImagen) dtOpenSGSManagedBean;
        super.modificar(dtOpenSGSManagedBean);
        this.ancho = dtConstanciaElementoImagen.getAncho();
        this.alto = dtConstanciaElementoImagen.getAlto();
        this.archivo = ManejadorArchivo.getInstance().obtenerArchivo(dtConstanciaElementoImagen.getDtArchivo());
    }

    @Override
    public boolean agregarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    @Override
    public boolean quitarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    public DtConstanciaElementoImagen getDtConstanciaElementoImagen() {
        return new DtConstanciaElementoImagen(
                ancho,
                alto,
                archivo.getDtArchivo(),
                this.getNombre(),
                this.getPosicionX(),
                this.getPosicionY(),
                this.getConstancia().getDtConstancia(),
                this.isActivo(),
                this.isBorrado(),
                this.isAdministrable(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getModificado()),
                this.getId(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getCreacion())
        );
    }

    @Override
    public DtConstanciaElementoImagen getDtOpenSGSBean() {
        return this.getDtConstanciaElementoImagen();
    }
    
    @Override
    public DtConstanciaElementoImagen getDataType() {
        return this.getDtConstanciaElementoImagen();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.ConstanciaElementoImagen;
    }

    @Override
    public String toString() {
        return super.toString() + "ConstanciaElementoImagen{" + "ancho=" + ancho + ", alto=" + alto + ", archivo=" + archivo + '}';
    }

}
