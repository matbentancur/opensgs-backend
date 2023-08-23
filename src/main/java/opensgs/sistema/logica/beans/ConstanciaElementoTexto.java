package opensgs.sistema.logica.beans;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.enums.Elemento;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.servicios.ServicioEnum;
import opensgs.logica.servicios.ServicioFechaHora;
import opensgs.sistema.datatypes.DtConstanciaElementoTexto;
import opensgs.sistema.enums.FuenteTipo;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(
        name = "constanciaElementoTexto",
        schema = "sistema"
)
public class ConstanciaElementoTexto extends ConstanciaElemento {

    @Column(nullable = false)
    @NotNull
    @NotBlank
    @Size(min = 1)
    private String texto;

    @Column(nullable = false)
    @NotNull
    @Positive
    @Min(2)
    @Max(370)
    private Float fuenteTamanio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 128)
    @NotNull
    private FuenteTipo fuenteTipo;

    public ConstanciaElementoTexto() {
    }

    public ConstanciaElementoTexto(DtOpenSGSBean dtOpenSGSBean) {
        this((DtConstanciaElementoTexto) dtOpenSGSBean);
    }

    public ConstanciaElementoTexto(DtConstanciaElementoTexto dtConstanciaElementoTexto) {
        super(dtConstanciaElementoTexto);
        this.texto = dtConstanciaElementoTexto.getTexto();
        this.fuenteTamanio = dtConstanciaElementoTexto.getFuenteTamanio();
        this.fuenteTipo = ServicioEnum.getInstance().parseEnum(FuenteTipo.class, dtConstanciaElementoTexto.getFuenteTipo());
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Float getFuenteTamanio() {
        return fuenteTamanio;
    }

    public void setFuenteTamanio(Float fuenteTamanio) {
        this.fuenteTamanio = fuenteTamanio;
    }

    public FuenteTipo getFuenteTipo() {
        return fuenteTipo;
    }

    public void setFuenteTipo(FuenteTipo fuenteTipo) {
        this.fuenteTipo = fuenteTipo;
    }

    @Override
    public void modificar(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        DtConstanciaElementoTexto dtConstanciaElementoTexto = (DtConstanciaElementoTexto) dtOpenSGSManagedBean;
        super.modificar(dtOpenSGSManagedBean);
        this.texto = dtConstanciaElementoTexto.getTexto();
        this.fuenteTamanio = dtConstanciaElementoTexto.getFuenteTamanio();
        this.fuenteTipo = ServicioEnum.getInstance().parseEnum(FuenteTipo.class, dtConstanciaElementoTexto.getFuenteTipo());
    }

    @Override
    public boolean agregarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    @Override
    public boolean quitarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    public DtConstanciaElementoTexto getDtConstanciaElementoTexto() {
        return new DtConstanciaElementoTexto(
                texto,
                fuenteTamanio,
                fuenteTipo.toString(),
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
    public DtConstanciaElementoTexto getDtOpenSGSBean() {
        return this.getDtConstanciaElementoTexto();
    }

    @Override
    public DtConstanciaElementoTexto getDataType() {
        return this.getDtConstanciaElementoTexto();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.ConstanciaElementoTexto;
    }

    @Override
    public String toString() {
        return super.toString() + "ConstanciaElementoTexto{" + "texto=" + texto + ", fuenteTamanio=" + fuenteTamanio + ", fuenteTipo=" + fuenteTipo + '}';
    }

}
