package opensgs.solicitudes.datatypes;

public final class DtEstadoSolicitud {

    private Long id;
    private boolean entregado;
    private boolean financiado;
    private boolean cursado;
    private boolean aprobado;

    public DtEstadoSolicitud() {
    }

    public DtEstadoSolicitud(Long id, boolean entregado, boolean financiado, boolean cursado, boolean aprobado) {
        this.id = id;
        this.entregado = entregado;
        this.financiado = financiado;
        this.cursado = cursado;
        this.aprobado = aprobado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    public boolean isFinanciado() {
        return financiado;
    }

    public void setFinanciado(boolean financiado) {
        this.financiado = financiado;
    }

    public boolean isCursado() {
        return cursado;
    }

    public void setCursado(boolean cursado) {
        this.cursado = cursado;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    @Override
    public String toString() {
        return "DtEstadoSolicitud{" + "id=" + id + ", entregado=" + entregado + ", financiado=" + financiado + ", cursado=" + cursado + ", aprobado=" + aprobado + '}';
    }

}
