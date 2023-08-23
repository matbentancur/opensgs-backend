package opensgs.usuarios.datatypes;

import opensgs.datatypes.DtOpenSGSBean;
import opensgs.sistema.datatypes.DtAplicacion;

public class DtPerfilAplicacion extends DtOpenSGSBean {

    private DtPerfil dtPerfil;
    private DtAplicacion dtAplicacion;

    public DtPerfilAplicacion() {
    }

    public DtPerfilAplicacion(DtPerfil dtPerfil, DtAplicacion dtAplicacion) {
        this.dtPerfil = dtPerfil;
        this.dtAplicacion = dtAplicacion;
    }

    public DtPerfilAplicacion(DtPerfil dtPerfil, DtAplicacion dtAplicacion, Long id, String creacion) {
        super(id, creacion);
        this.dtPerfil = dtPerfil;
        this.dtAplicacion = dtAplicacion;
    }

    public DtPerfil getDtPerfil() {
        return dtPerfil;
    }

    public void setDtPerfil(DtPerfil dtPerfil) {
        this.dtPerfil = dtPerfil;
    }

    public DtAplicacion getDtAplicacion() {
        return dtAplicacion;
    }

    public void setDtAplicacion(DtAplicacion dtAplicacion) {
        this.dtAplicacion = dtAplicacion;
    }

    @Override
    public String getClassName() {
        return "PerfilAplicacion";
    }

    @Override
    public String toString() {
        return super.toString() + "DtPerfilAplicacion{" + "dtPerfil=" + dtPerfil + ", dtAplicacion=" + dtAplicacion + '}';
    }

}
