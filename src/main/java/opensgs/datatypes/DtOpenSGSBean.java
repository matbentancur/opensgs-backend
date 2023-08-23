package opensgs.datatypes;

public abstract class DtOpenSGSBean {

    private Long id;
    private String creacion;

    public DtOpenSGSBean() {
    }

    public DtOpenSGSBean(Long id, String creacion) {
        this.id = id;
        this.creacion = creacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreacion() {
        return creacion;
    }

    public void setCreacion(String creacion) {
        this.creacion = creacion;
    }
    
    public abstract String getClassName();

    @Override
    public String toString() {
        return "DtOpenSGSBean{" + "id=" + id + ", creacion=" + creacion + '}';
    }

}
