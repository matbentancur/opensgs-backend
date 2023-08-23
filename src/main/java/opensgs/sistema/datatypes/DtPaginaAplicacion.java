package opensgs.sistema.datatypes;

import opensgs.datatypes.DtOpenSGSManagedBean;

public class DtPaginaAplicacion extends DtOpenSGSManagedBean {

    private String nombre;
    private String titulo;
    private String textoInicial;
    private String textoFinal;
    private Integer pagina;
    private String esctructuraDatos;
    private DtPlantillaAplicacion dtPlantillaAplicacion;

    public DtPaginaAplicacion() {
    }

    public DtPaginaAplicacion(String nombre, String titulo, String textoInicial, String textoFinal, Integer pagina, String esctructuraDatos, DtPlantillaAplicacion dtPlantillaAplicacion, boolean activo, boolean borrado, boolean administrable, String modificado, Long id, String creacion) {
        super(activo, borrado, administrable, modificado, id, creacion);
        this.nombre = nombre;
        this.titulo = titulo;
        this.textoInicial = textoInicial;
        this.textoFinal = textoFinal;
        this.pagina = pagina;
        this.esctructuraDatos = esctructuraDatos;
        this.dtPlantillaAplicacion = dtPlantillaAplicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTextoInicial() {
        return textoInicial;
    }

    public void setTextoInicial(String textoInicial) {
        this.textoInicial = textoInicial;
    }

    public String getTextoFinal() {
        return textoFinal;
    }

    public void setTextoFinal(String textoFinal) {
        this.textoFinal = textoFinal;
    }

    public Integer getPagina() {
        return pagina;
    }

    public void setPagina(Integer pagina) {
        this.pagina = pagina;
    }

    public String getEsctructuraDatos() {
        return esctructuraDatos;
    }

    public void setEsctructuraDatos(String esctructuraDatos) {
        this.esctructuraDatos = esctructuraDatos;
    }

    public DtPlantillaAplicacion getDtPlantillaAplicacion() {
        return dtPlantillaAplicacion;
    }

    public void setDtPlantillaAplicacion(DtPlantillaAplicacion dtPlantillaAplicacion) {
        this.dtPlantillaAplicacion = dtPlantillaAplicacion;
    }

    @Override
    public String getClassName() {
        return "PaginaAplicacion";
    }

    @Override
    public String toString() {
        return super.toString() + "DtPaginaAplicacion{" + "nombre=" + nombre + ", titulo=" + titulo + ", textoInicial=" + textoInicial + ", textoFinal=" + textoFinal + ", pagina=" + pagina + ", esctructuraDatos=" + esctructuraDatos + ", dtPlantillaAplicacion=" + dtPlantillaAplicacion + '}';
    }

}
