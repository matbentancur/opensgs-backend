package opensgs.usuarios.datatypes;

import opensgs.datatypes.DtOpenSGSBean;

public final class DtSesion extends DtOpenSGSBean{

    private String codigo;
    private DtUsuario dtUsuario;

    public DtSesion() {
    }

    public DtSesion(String codigo, DtUsuario dtUsuario, Long id, String creacion) {
        super(id, creacion);
        this.codigo = codigo;
        this.dtUsuario = dtUsuario;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public DtUsuario getDtUsuario() {
        return dtUsuario;
    }

    public void setDtUsuario(DtUsuario dtUsuario) {
        this.dtUsuario = dtUsuario;
    }
    
    @Override
    public String getClassName() {
        return "Sesion";
    }

    @Override
    public String toString() {
        return "DtSesion{" + "codigo=" + codigo + ", dtUsuario=" + dtUsuario + '}';
    }

}
