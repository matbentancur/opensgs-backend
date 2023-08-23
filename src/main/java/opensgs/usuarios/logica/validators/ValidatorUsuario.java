package opensgs.usuarios.logica.validators;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import opensgs.datatypes.DtMensaje;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.servicios.ServicioMensaje;
import opensgs.usuarios.enums.DocumentoTipo;
import opensgs.usuarios.logica.beans.Usuario;
import opensgs.logica.servicios.ServicioPassword;
import opensgs.sistema.logica.beans.Aplicacion;
import opensgs.usuarios.persistencia.manejadores.ManejadorUsuario;

public class ValidatorUsuario {

    private static ValidatorUsuario instance = null;

    private final Pattern pattern;
    private Matcher matcher;
    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%&*.,+-]).{8,128})";

    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final SecureRandom RANDOM = new SecureRandom();

    private ValidatorUsuario() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    public static ValidatorUsuario getInstance() {
        if (instance == null) {
            instance = new ValidatorUsuario();
        }
        return instance;
    }

    //VALIDA QUE LA CONTRASEÑA CUMPLA CON EL PATRON
    private boolean validarPatron(final String password) {
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    //VALIDA QUE EL NOMBRE DE USUARIO NO SE IGUAL A LA CONTRASEÑA
    private boolean validarUsuarioPassword(final String usuario, final String password) {
        return !usuario.equalsIgnoreCase(password);
    }

    //VALIDA QUE LA CONTRAESÑA ANTERIOR NO SE IGUAL A LA NUEVA
    private boolean validarViejoNuevoPassword(final String passwordAnterior, final String passwordNuevo) {
        return !passwordAnterior.equalsIgnoreCase(passwordNuevo);
    }

    //VALIDA QUE LA CONTRASEÑA CUMPLA CON EL TAMAÑO
    private boolean validarLargoPassword(final String password) {
        if (password.length() < 8) {
            return false;
        }
        if (password.length() > 128) {
            return false;
        }
        return true;
    }

    private boolean validarDocumentoUruguayo(String ci) {

        if (ci.length() != 7 && ci.length() != 8) {
            return false;
        } else {
            try {
                Integer.parseInt(ci);
            } catch (NumberFormatException e) {
                return false;
            }
        }

        // Armarmos el array que va a permitir realizar las multiplicaciones necesarias en cada digito.
        int[] arrayCoeficiente = new int[]{2, 9, 8, 7, 6, 3, 4, 1};
        // Variable donde se va a guardar el resultado de la suma.
        int suma = 0;
        // Simplemente para que se entienda que esto es el cardinal de digitos que tiene el array de coeficiente.
        int lenghtArrayCoeficiente = 8;
        // Contamos la cantidad de digitos que tiene la cadena de números de la CI que limpiamos.
        int lenghtCedulaDeIdentidad = ci.length();
        // Esto nos asegura que si la cédula es menor a un millón, para que el cálculo siga funcionando, simplemente le ponemos un cero antes y funciona perfecto.
        if (lenghtCedulaDeIdentidad == 7) {
            ci = 0 + ci;
            lenghtCedulaDeIdentidad++;
        }
        for (int i = 0; i < lenghtCedulaDeIdentidad; i++) {
            // Voy obteniendo cada caracter de la CI.
            int digito = Integer.parseInt(ci.charAt(i) + "");
            // Obtengo el coeficiente correspondiente a esta posición.
            int coeficiente = arrayCoeficiente[i];
            // Multiplico el caracter por el coeficiente y lo acumulo a la suma total
            suma = suma + digito * coeficiente;
        }
        // si la suma es múltiplo de 10 es una ci válida
        if ((suma % 10) == 0) {
            return true;
        } else {
            return false;
        }
    }

    //VALIDA QUE EL PASSWORD INDICADO SEA EL ACTUAL
    private boolean validarPasswordActual(Usuario usuario, final String password) {
        ServicioPassword passwordUtils = new ServicioPassword();
        return passwordUtils.chequearPasswordHash(password, usuario.getClave());
    }

    //VALIDA QUE EL USUARIO NO ESTE BORRADO
    private boolean validarUsuarioBorrado(Usuario usuario) {
        return !usuario.isBorrado();
    }

    //VALIDA QUE EL USUARIO ESTE ACTIVO
    private boolean validarUsuarioActivo(Usuario usuario) {
        return usuario.isActivo();
    }

    //VALIDA EXISTENCIA DE USUARIO
    private Usuario validarExistenciaUsuario(String usuario) {
        Usuario u = ManejadorUsuario.getInstance().obtenerUsuario("email", usuario);
        if (u == null) {
            u = (Usuario) ManejadorUsuario.getInstance().obtenerUsuario("documento", usuario);
        }
        if (u == null) {
            return null;
        }
        return u;
    }

    //VALIDA PERFIL GLOBAL DEL USUARIO
    private boolean validarPerfilGlobal(Usuario usuario) {
        if (usuario.getPerfil().getNombre().equals("Usuario")) {
            return true;
        }
        if (usuario.getPerfil().getNombre().equals("Administrador")) {
            return true;
        }
        return false;
    }

    private List<String> validarUsuario(Usuario usuario) {
        List<String> listaErrores = new ArrayList<>();

        //VALIDA SI EXISTE YA EXISTE EL CORREO ELECTRONICO
        Usuario usuarioExiste = ManejadorUsuario.getInstance().obtenerUsuario("email", usuario.getEmail());
        if (usuarioExiste != null) {
            if (!usuario.getId().equals(usuarioExiste.getId())) {
                listaErrores.add(ServicioMensaje.getInstance().getMensaje("Usuario.email.exists.error"));
            }
        }

        //VALIDA SI EXISTE YA EL DOCUMENTO DE IDENTIDAD
        usuarioExiste = ManejadorUsuario.getInstance().obtenerUsuario("documento", usuario.getDocumento());
        if (usuarioExiste != null) {
            listaErrores.add(ServicioMensaje.getInstance().getMensaje("Usuario.document.exists.error"));
        }

        //VALIDA SI EL DOCUMENTO URUGUAYO ES VALIDO
        if (usuario.getDocumentoTipo().equals(DocumentoTipo.DocumentoUruguayo)) {
            if (!this.validarDocumentoUruguayo(usuario.getDocumento())) {
                listaErrores.add(ServicioMensaje.getInstance().getMensaje("Usuario.document.ci.error"));
            }
        }

        //VALIDA EL PERFIL GLOBAL DEL USUARIO
        if (!this.validarPerfilGlobal(usuario)) {
            listaErrores.add(ServicioMensaje.getInstance().getMensaje("Usuario.profile.error"));
        }
        return listaErrores;

    }

    private List<String> validarClave(Usuario usuario) {
        List<String> listaErrores = new ArrayList<>();

        //TAMAÑO DE CONTRASEÑA
        if (!this.validarLargoPassword(usuario.getClave())) {
            listaErrores.add(ServicioMensaje.getInstance().getMensaje("Password.length.error"));
        }
        //PATRON DE LA CONTRASEÑA
        if (!this.validarPatron(usuario.getClave())) {
            listaErrores.add(ServicioMensaje.getInstance().getMensaje("Password.pattern.error"));
        }
        //EMAIL IGUAL A CONTRASEÑA
        if (!this.validarUsuarioPassword(usuario.getEmail(), usuario.getClave())) {
            listaErrores.add(ServicioMensaje.getInstance().getMensaje("Password.userEqualPass.error"));
        }
        //IDENTIFICACION IGUAL A CONTRASEÑA
        if (!this.validarUsuarioPassword(usuario.getDocumento(), usuario.getClave())) {
            listaErrores.add(ServicioMensaje.getInstance().getMensaje("Password.userEqualPass.error"));
        }
        return listaErrores;
    }

    private List<String> validarBean(Usuario usuario) {
        List<String> listaErrores = new ArrayList<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

        for (ConstraintViolation<Usuario> violation : violations) {
            listaErrores.add(violation.getMessage());
        }
        return listaErrores;
    }

    public DtMensaje validarAutenticarUsuarioLocal(String usuario, String password) {
        List<String> listaErrores = new ArrayList<>();

        Usuario u = this.validarExistenciaUsuario(usuario);

        if (u != null) {
            if (!this.validarPasswordActual(u, password)) {
                listaErrores.add(ServicioMensaje.getInstance().getMensaje("Usuario.autentication.password.error"));
            }
            if (!this.validarUsuarioBorrado(u)) {
                listaErrores.add(ServicioMensaje.getInstance().getMensaje("Usuario.autentication.deleted.error"));
            }
            if (!this.validarUsuarioActivo(u)) {
                listaErrores.add(ServicioMensaje.getInstance().getMensaje("Usuario.autentication.disable.error"));
            }
        } else {
            listaErrores.add(ServicioMensaje.getInstance().getMensaje("Usuario.autentication.password.error"));
        }

        if (listaErrores.isEmpty()) {
            return ServicioMensaje.getInstance().getMensajeOK();
        } else {
            return ServicioMensaje.getInstance().getMensajeERROR(listaErrores);
        }
    }

    public DtMensaje validarCrear(OpenSGSBean openSGSBean) {
        Usuario usuario = (Usuario) openSGSBean;
        List<String> listaErrores = new ArrayList<>();

        listaErrores.addAll(this.validarUsuario(usuario));

        if (listaErrores.isEmpty()) {
            return ServicioMensaje.getInstance().getMensajeOK();
        } else {
            return ServicioMensaje.getInstance().getMensajeERROR(listaErrores);
        }
    }

    public DtMensaje validarModificar(OpenSGSBean openSGSBean) {
        Usuario usuario = (Usuario) openSGSBean;
        List<String> listaErrores = new ArrayList<>();

        listaErrores.addAll(this.validarUsuario(usuario));
        listaErrores.addAll(this.validarBean(usuario));

        if (listaErrores.isEmpty()) {
            return ServicioMensaje.getInstance().getMensajeOK();
        } else {
            return ServicioMensaje.getInstance().getMensajeERROR(listaErrores);
        }
    }

    public DtMensaje validarRegistrarUsuario(Usuario usuario) {
        List<String> listaErrores = new ArrayList<>();

        listaErrores.addAll(this.validarUsuario(usuario));
        listaErrores.addAll(this.validarClave(usuario));
        listaErrores.addAll(this.validarBean(usuario));

        if (listaErrores.isEmpty()) {
            return ServicioMensaje.getInstance().getMensajeOK();
        } else {
            return ServicioMensaje.getInstance().getMensajeERROR(listaErrores);
        }
    }

    public DtMensaje validarModificarMisDatos(Usuario usuario) {
        List<String> listaErrores = new ArrayList<>();

        listaErrores.addAll(this.validarBean(usuario));

        if (listaErrores.isEmpty()) {
            return ServicioMensaje.getInstance().getMensajeOK();
        } else {
            return ServicioMensaje.getInstance().getMensajeERROR(listaErrores);
        }
    }

    public DtMensaje validarModificarMiClave(Usuario usuario, String passwordActual, String passwordNuevo) {
        List<String> listaErrores = new ArrayList<>();

        if (!this.validarPasswordActual(usuario, passwordActual)) {
            listaErrores.add(ServicioMensaje.getInstance().getMensaje("Usuario.autentication.password.error"));
        }
        usuario.setClave(passwordNuevo);
        listaErrores.addAll(this.validarClave(usuario));

        if (listaErrores.isEmpty()) {
            return ServicioMensaje.getInstance().getMensajeOK();
        } else {
            return ServicioMensaje.getInstance().getMensajeERROR(listaErrores);
        }
    }

    public DtMensaje validarAgregarPerfilAplicacion(Usuario usuario, Aplicacion aplicacion) {
        List<String> listaErrores = new ArrayList<>();

        if (usuario.buscarPerfilAplicacion(aplicacion)) {
            listaErrores.add(ServicioMensaje.getInstance().getMensaje("Usuario.profileApplication.exists.error"));
        }

        if (listaErrores.isEmpty()) {
            return ServicioMensaje.getInstance().getMensajeOK();
        } else {
            return ServicioMensaje.getInstance().getMensajeERROR(listaErrores);
        }
    }

}
