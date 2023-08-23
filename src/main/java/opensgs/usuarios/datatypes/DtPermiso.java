package opensgs.usuarios.datatypes;

import opensgs.datatypes.DtOpenSGSBean;
import opensgs.enums.Elemento;
import opensgs.enums.Operacion;

public final class DtPermiso extends DtOpenSGSBean{
    
    private String elemento;
    private String operacion;

    public DtPermiso() {
    }

    public DtPermiso(String elemento, String operacion, Long id, String creacion) {
        super(id, creacion);
        this.elemento = elemento;
        this.operacion = operacion;
    }

    public DtPermiso(String elemento, String operacion) {
        super();
        this.elemento = elemento;
        this.operacion = operacion;
    }

    public DtPermiso(Elemento elemento, Operacion operacion) {
        super();
        this.elemento = elemento.toString();
        this.operacion = operacion.toString();
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }
    
    @Override
    public String getClassName() {
        return "Permiso";
    }

    @Override
    public String toString() {
        return super.toString() + "DtPermiso{" + "elemento=" + elemento + ", operacion=" + operacion + '}';
    }
   
}
