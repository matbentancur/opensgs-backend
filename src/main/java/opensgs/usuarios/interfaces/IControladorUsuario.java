package opensgs.usuarios.interfaces;

import opensgs.datatypes.DtMensaje;
import opensgs.usuarios.datatypes.DtUsuario;
import opensgs.usuarios.datatypes.DtRegistrarUsuario;
import opensgs.usuarios.datatypes.DtSesion;

public interface IControladorUsuario {

    //USUARIO
    public DtMensaje autenticarUsuario(String usuario, String password);

    public DtSesion iniciarSesion(String usuario, String password);

    public DtMensaje cerrarSesion(DtSesion dtSesion);

    public DtUsuario obtenerMisDatos(DtSesion dtSesion);

    public DtMensaje modificarMisDatos(DtUsuario dtUsuario, DtSesion dtSesion);

    public DtMensaje modificarMiClave(String passwordActual, String passwordNuevo, DtSesion dtSesion);

    public DtMensaje recuperarMiClave(String email);

    public DtMensaje registrarUsuario(DtRegistrarUsuario dtRegistrarUsuario);
    
    public DtMensaje agregarPerfilAplicacion(DtUsuario dtUsuario, Long perfilId, Long aplicacionId, DtSesion dtSesion);

}
