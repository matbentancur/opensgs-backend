package opensgs.sistema.datatypes;

import java.util.ArrayList;
import java.util.List;
import opensgs.datatypes.DtOpenSGSManagedBean;

public final class DtNotificacion extends DtOpenSGSManagedBean {

    private String nombre;
    private String operacion;
    private String elemento;
    private String destino;
    private String alcance;
    private DtPlantillaCorreo dtPlantillaCorreo;
    private List<DtDestinatario> dtDestinatarios = new ArrayList<>();

    public DtNotificacion() {
    }

    public DtNotificacion(String nombre, String operacion, String elemento, String destino, String alcance, DtPlantillaCorreo dtPlantillaCorreo, boolean activo, boolean borrado, boolean administrable, String modificado, Long id, String creacion) {
        super(activo, borrado, administrable, modificado, id, creacion);
        this.nombre = nombre;
        this.operacion = operacion;
        this.elemento = elemento;
        this.destino = destino;
        this.alcance = alcance;
        this.dtPlantillaCorreo = dtPlantillaCorreo;
    }

    public DtNotificacion(String nombre, String operacion, String elemento, String destino, String alcance, DtPlantillaCorreo dtPlantillaCorreo, boolean activo, boolean borrado, boolean administrable, String modificado, Long id, String creacion, List<DtDestinatario> dtDestinatarios) {
        super(activo, borrado, administrable, modificado, id, creacion);
        this.nombre = nombre;
        this.operacion = operacion;
        this.elemento = elemento;
        this.destino = destino;
        this.alcance = alcance;
        this.dtPlantillaCorreo = dtPlantillaCorreo;
        this.dtDestinatarios = dtDestinatarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    public DtPlantillaCorreo getDtPlantillaCorreo() {
        return dtPlantillaCorreo;
    }

    public void setDtPlantillaCorreo(DtPlantillaCorreo dtPlantillaCorreo) {
        this.dtPlantillaCorreo = dtPlantillaCorreo;
    }

    public List<DtDestinatario> getDtDestinatarios() {
        return dtDestinatarios;
    }

    public void setDtDestinatarios(List<DtDestinatario> dtDestinatarios) {
        this.dtDestinatarios = dtDestinatarios;
    }

    @Override
    public String getClassName() {
        return "Notificacion";
    }

    @Override
    public String toString() {
        return super.toString() + "DtNotificacion{" + "nombre=" + nombre + ", operacion=" + operacion + ", elemento=" + elemento + ", destino=" + destino + ", alcance=" + alcance + ", dtPlantillaCorreo=" + dtPlantillaCorreo + ", dtDestinatarios=" + dtDestinatarios + '}';
    }

}
