package opensgs.sistema.logica.beans;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.datatypes.DtOpenSGSManagedBean;
import opensgs.enums.Elemento;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.logica.servicios.ServicioFechaHora;
import opensgs.sistema.datatypes.DtPlantillaAplicacion;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(
        name = "plantillaAplicacion",
        schema = "sistema"
)
public class PlantillaAplicacion extends OpenSGSManagedBean {

    @Column(unique = true, nullable = false, length = 128)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 128)
    private String nombre;

    @Column(nullable = false, columnDefinition = "varchar")
    @NotNull
    @NotBlank
    @Size(min = 1, max = 1024)
    private String textoAcuerdo;

    @Column(nullable = false, columnDefinition = "varchar")
    @NotNull
    @NotBlank
    @Size(min = 1, max = 1024)
    private String textoEntrega;

    public PlantillaAplicacion() {
    }

    public PlantillaAplicacion(DtOpenSGSBean dtOpenSGSBean) {
        this((DtPlantillaAplicacion) dtOpenSGSBean);
    }

    public PlantillaAplicacion(DtPlantillaAplicacion dtPlantillaAplicacion) {
        super();
        this.nombre = dtPlantillaAplicacion.getNombre();
        this.textoAcuerdo = dtPlantillaAplicacion.getTextoAcuerdo();
        this.textoEntrega = dtPlantillaAplicacion.getTextoEntrega();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTextoAcuerdo() {
        return textoAcuerdo;
    }

    public void setTextoAcuerdo(String textoAcuerdo) {
        this.textoAcuerdo = textoAcuerdo;
    }

    public String getTextoEntrega() {
        return textoEntrega;
    }

    public void setTextoEntrega(String textoEntrega) {
        this.textoEntrega = textoEntrega;
    }

    @Override
    public void modificar(DtOpenSGSManagedBean dtOpenSGSManagedBean) {
        DtPlantillaAplicacion dtPlantillaAplicacion = (DtPlantillaAplicacion) dtOpenSGSManagedBean;
        this.nombre = dtPlantillaAplicacion.getNombre();
        this.textoAcuerdo = dtPlantillaAplicacion.getTextoAcuerdo();
        this.textoEntrega = dtPlantillaAplicacion.getTextoEntrega();
    }

    @Override
    public boolean agregarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    @Override
    public boolean quitarListOpenSGSBean(OpenSGSBean openSGSBean) {
        return false;
    }

    public DtPlantillaAplicacion getDtPlantillaAplicacion() {

        return new DtPlantillaAplicacion(
                nombre,
                textoAcuerdo,
                textoEntrega,
                this.isActivo(),
                this.isBorrado(),
                this.isAdministrable(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getModificado()),
                this.getId(),
                ServicioFechaHora.getInstance().dateTimeToString(this.getCreacion())
        );
    }

    @Override
    public DtOpenSGSBean getDtOpenSGSBean() {
        return this.getDtPlantillaAplicacion();
    }

    @Override
    public DtPlantillaAplicacion getDataType() {
        return this.getDtPlantillaAplicacion();
    }

    @Override
    public Elemento getOpenSGSElemento() {
        return Elemento.PlantillaAplicacion;
    }

    @Override
    public String toString() {
        return "PlantillaAplicacion{" + "nombre=" + nombre + ", textoAcuerdo=" + textoAcuerdo + ", textoEntrega=" + textoEntrega + '}';
    }

}
