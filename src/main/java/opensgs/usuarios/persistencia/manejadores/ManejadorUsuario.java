package opensgs.usuarios.persistencia.manejadores;

import opensgs.usuarios.logica.beans.Usuario;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.usuarios.datatypes.DtSesion;
import opensgs.usuarios.datatypes.DtUsuario;


public class ManejadorUsuario {
    
    private static ManejadorUsuario instance = null;
    
    private ManejadorUsuario() {
    }
    
    public static ManejadorUsuario getInstance() {
        if (instance == null) {
            instance = new ManejadorUsuario();
        }
        return instance;
    }
    
    public Usuario obtenerUsuario(DtUsuario dtUsuario) {
        return (Usuario) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtUsuario);
    }
    
    public Usuario obtenerUsuario(String parametro, String valor) {
        return (Usuario) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(new Usuario(), parametro, valor);
    }
    
    public Usuario obtenerUsuario(DtSesion dtSesion) {
        return (Usuario) ManejadorOpenSGSBean.getInstance().obtenerOpenSGSBean(dtSesion.getDtUsuario());
    }
    
    public void crearUsuario(Usuario usuario) throws Exception {
        ManejadorOpenSGSBean.getInstance().crearOpenSGSBean(usuario);
    }
    
    public void modificarUsuario(Usuario usuario) throws Exception {
        ManejadorOpenSGSBean.getInstance().modificarOpenSGSBean(usuario);
    }
        
    public Integer contarUsuarios() {
        return ManejadorOpenSGSBean.getInstance().contarOpenSGSBean(new Usuario());
    }

}
