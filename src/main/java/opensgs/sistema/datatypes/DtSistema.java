package opensgs.sistema.datatypes;

import opensgs.datatypes.DtOpenSGSManagedBean;

public final class DtSistema extends DtOpenSGSManagedBean {

    private String nombre;
    private String url;
    private boolean mantenimiento;
    private Integer cantidadMaximaUsuarios;
    private Integer fileUploadMaxSize;
    private String correoSoporte;
    private String filesPath;
    private DtServidorCorreo dtServidorCorreo;
    private DtServidorAplicaciones dtServidorAplicaciones;
    private DtServidorAutenticacion dtServidorAutenticacion;

    public DtSistema() {
    }

    public DtSistema(String nombre, String url, boolean mantenimiento, Integer cantidadMaximaUsuarios, Integer fileUploadMaxSize, String correoSoporte, String filesPath, DtServidorCorreo dtServidorCorreo, DtServidorAplicaciones dtServidorAplicaciones, DtServidorAutenticacion dtServidorAutenticacion, boolean activo, boolean borrado, boolean administrable, String modificado, Long id, String creacion) {
        super(activo, borrado, administrable, modificado, id, creacion);
        this.nombre = nombre;
        this.url = url;
        this.mantenimiento = mantenimiento;
        this.cantidadMaximaUsuarios = cantidadMaximaUsuarios;
        this.fileUploadMaxSize = fileUploadMaxSize;
        this.correoSoporte = correoSoporte;
        this.filesPath = filesPath;
        this.dtServidorCorreo = dtServidorCorreo;
        this.dtServidorAplicaciones = dtServidorAplicaciones;
        this.dtServidorAutenticacion = dtServidorAutenticacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isMantenimiento() {
        return mantenimiento;
    }

    public void setMantenimiento(boolean mantenimiento) {
        this.mantenimiento = mantenimiento;
    }

    public Integer getCantidadMaximaUsuarios() {
        return cantidadMaximaUsuarios;
    }

    public void setCantidadMaximaUsuarios(Integer cantidadMaximaUsuarios) {
        this.cantidadMaximaUsuarios = cantidadMaximaUsuarios;
    }

    public Integer getFileUploadMaxSize() {
        return fileUploadMaxSize;
    }

    public void setFileUploadMaxSize(Integer fileUploadMaxSize) {
        this.fileUploadMaxSize = fileUploadMaxSize;
    }

    public String getCorreoSoporte() {
        return correoSoporte;
    }

    public void setCorreoSoporte(String correSoporte) {
        this.correoSoporte = correSoporte;
    }

    public String getFilesPath() {
        return filesPath;
    }

    public void setFilesPath(String filesPath) {
        this.filesPath = filesPath;
    }

    public DtServidorCorreo getDtServidorCorreo() {
        return dtServidorCorreo;
    }

    public void setDtServidorCorreo(DtServidorCorreo dtServidorCorreo) {
        this.dtServidorCorreo = dtServidorCorreo;
    }

    public DtServidorAplicaciones getDtServidorAplicaciones() {
        return dtServidorAplicaciones;
    }

    public void setDtServidorAplicaciones(DtServidorAplicaciones dtServidorAplicaciones) {
        this.dtServidorAplicaciones = dtServidorAplicaciones;
    }

    public DtServidorAutenticacion getDtServidorAutenticacion() {
        return dtServidorAutenticacion;
    }

    public void setDtServidorAutenticacion(DtServidorAutenticacion dtServidorAutenticacion) {
        this.dtServidorAutenticacion = dtServidorAutenticacion;
    }

    @Override
    public String getClassName() {
        return "Sistema";
    }

    @Override
    public String toString() {
        return super.toString() + "DtSistema{" + "nombre=" + nombre + ", url=" + url + ", mantenimiento=" + mantenimiento + ", cantidadMaximaUsuarios=" + cantidadMaximaUsuarios + ", fileUploadMaxSize=" + fileUploadMaxSize + ", correoSoporte=" + correoSoporte + ", filesPath=" + filesPath + ", dtServidorCorreo=" + dtServidorCorreo + ", dtServidorAplicaciones=" + dtServidorAplicaciones + ", dtServidorAutenticacion=" + dtServidorAutenticacion + '}';
    }

}
