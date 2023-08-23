package opensgs.datatypes;

import java.util.ArrayList;
import java.util.List;
import opensgs.enums.MensajeTipo;

public final class DtMensaje {

    private boolean exito;
    private List<String> mensajes;
    private MensajeTipo tipoMensaje;
    private Long openSGSBeanId;

    public DtMensaje() {
    }

    public DtMensaje(boolean exito, List<String> mensajes, MensajeTipo tipoMensaje) {
        this.exito = exito;
        this.mensajes = mensajes;
        this.tipoMensaje = tipoMensaje;
    }
    
    public DtMensaje(boolean exito, List<String> mensajes, MensajeTipo tipoMensaje, Long openSGSBeanId) {
        this.exito = exito;
        this.mensajes = mensajes;
        this.tipoMensaje = tipoMensaje;
        this.openSGSBeanId = openSGSBeanId;
    }

    public DtMensaje(boolean exito, String mensaje, MensajeTipo tipoMensaje) {
        this.exito = exito;
        this.mensajes = new ArrayList<>();
        this.mensajes.add(mensaje);
        this.tipoMensaje = tipoMensaje;
    }

    public DtMensaje(boolean exito, String mensaje, MensajeTipo tipoMensaje, Long openSGSBeanId) {
        this.exito = exito;
        this.mensajes = new ArrayList<>();
        this.mensajes.add(mensaje);
        this.tipoMensaje = tipoMensaje;
        this.openSGSBeanId = openSGSBeanId;
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public List<String> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<String> mensajes) {
        this.mensajes = mensajes;
    }

    public MensajeTipo getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(MensajeTipo tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public Long getOpenSGSBeanId() {
        return openSGSBeanId;
    }

    public void setOpenSGSBeanId(Long openSGSBeanId) {
        this.openSGSBeanId = openSGSBeanId;
    }

}
