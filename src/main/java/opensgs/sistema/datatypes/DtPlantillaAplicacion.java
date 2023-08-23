package opensgs.sistema.datatypes;

import opensgs.datatypes.DtOpenSGSManagedBean;

public final class DtPlantillaAplicacion extends DtOpenSGSManagedBean {

    private String nombre;
    private String textoAcuerdo;
    private String textoEntrega;

    public DtPlantillaAplicacion() {
    }

    public DtPlantillaAplicacion(String nombre, String textoAcuerdo, String textoEntrega, boolean activo, boolean borrado, boolean administrable, String modificado, Long id, String creacion) {
        super(activo, borrado, administrable, modificado, id, creacion);
        this.nombre = nombre;
        this.textoAcuerdo = textoAcuerdo;
        this.textoEntrega = textoEntrega;
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
    public String getClassName() {
        return "PlantillaAplicacion";
    }

    @Override
    public String toString() {
        return "DtPlantillaAplicacion{" + "nombre=" + nombre + ", textoAcuerdo=" + textoAcuerdo + ", textoEntrega=" + textoEntrega + '}';
    }

}
