package opensgs.logica.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import opensgs.datatypes.DtMensaje;
import opensgs.datatypes.DtOpenSGSBean;
import opensgs.logica.beans.OpenSGSBean;
import opensgs.logica.beans.OpenSGSManagedBean;
import opensgs.logica.servicios.ServicioMensaje;
import opensgs.usuarios.logica.validators.ValidatorUsuario;

public class ValidatorOpenSGSBean {

    private static ValidatorOpenSGSBean instance = null;

    private ValidatorOpenSGSBean() {
    }

    public static ValidatorOpenSGSBean getInstance() {
        if (instance == null) {
            instance = new ValidatorOpenSGSBean();
        }
        return instance;
    }

    public DtMensaje validarBean(OpenSGSBean openSGSBean) {
        List<String> listaErrores = new ArrayList<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<OpenSGSBean>> violations = validator.validate(openSGSBean);

        for (ConstraintViolation<OpenSGSBean> violation : violations) {
            listaErrores.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
        }
        if (listaErrores.isEmpty()) {
            return ServicioMensaje.getInstance().getMensajeOK();
        } else {
            return ServicioMensaje.getInstance().getMensajeERROR(listaErrores);
        }
    }

    public DtMensaje validarCrear(OpenSGSBean openSGSBean) {

        DtMensaje dtMensaje = this.validarBean(openSGSBean);
        if (!dtMensaje.isExito()) {
            return dtMensaje;
        }

        return ServicioMensaje.getInstance().getMensajeOK();
    }

    public DtMensaje validarCrear(OpenSGSManagedBean openSGSManagedBean) {

        if (!openSGSManagedBean.isAdministrable()) {
            return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.create.manageable.error");
        }

        DtMensaje dtMensaje = this.validarBean(openSGSManagedBean);
        if (!dtMensaje.isExito()) {
            return dtMensaje;
        }

        if (openSGSManagedBean.getClass().getName().equals("Usuario")) {
            return ValidatorUsuario.getInstance().validarCrear(openSGSManagedBean);
        }
        return ServicioMensaje.getInstance().getMensajeOK();
    }

    public DtMensaje validarModificar(OpenSGSBean openSGSBean) {

        OpenSGSManagedBean openSGSManagedBean = (OpenSGSManagedBean) openSGSBean;

        if (!openSGSManagedBean.isAdministrable()) {
            return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.update.manageable.error");
        }

        DtMensaje dtMensaje = this.validarBean(openSGSBean);
        if (!dtMensaje.isExito()) {
            return dtMensaje;
        }

        if (openSGSBean.getClass().getName().equals("Usuario")) {
            dtMensaje = ValidatorUsuario.getInstance().validarModificar(openSGSBean);
            if (!dtMensaje.isExito()) {
                return dtMensaje;
            }
        }
        return ServicioMensaje.getInstance().getMensajeOK();
    }

    public DtMensaje validarBorrar(OpenSGSBean openSGSBean) {

        OpenSGSManagedBean openSGSManagedBean = (OpenSGSManagedBean) openSGSBean;

        if (!openSGSManagedBean.isAdministrable()) {
            return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.delete.manageable.error");
        }

        return ServicioMensaje.getInstance().getMensajeOK();
    }

    public DtMensaje validarRestaurar(OpenSGSBean openSGSBean) {

        OpenSGSManagedBean openSGSManagedBean = (OpenSGSManagedBean) openSGSBean;

        if (!openSGSManagedBean.isAdministrable()) {
            return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.restore.manageable.error");
        }

        return ServicioMensaje.getInstance().getMensajeOK();
    }

    public DtMensaje validarDesactivar(OpenSGSBean openSGSBean) {

        OpenSGSManagedBean openSGSManagedBean = (OpenSGSManagedBean) openSGSBean;

        if (!openSGSManagedBean.isAdministrable()) {
            return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.disable.manageable.error");
        }

        return ServicioMensaje.getInstance().getMensajeOK();
    }

    public DtMensaje validarActivar(OpenSGSBean openSGSBean) {

        OpenSGSManagedBean openSGSManagedBean = (OpenSGSManagedBean) openSGSBean;

        if (!openSGSManagedBean.isAdministrable()) {
            return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.enable.manageable.error");
        }

        return ServicioMensaje.getInstance().getMensajeOK();
    }

    public DtMensaje isCreable(DtOpenSGSBean dtOpenSGSBean) {
        if (this.isProtected(dtOpenSGSBean)) {
            return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.create.error");
        }
        if (this.isSistema(dtOpenSGSBean)) {
            return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.create.error");
        }
        return ServicioMensaje.getInstance().getMensajeOK();
    }

    public DtMensaje isModificable(DtOpenSGSBean dtOpenSGSBean) {
        if (this.isProtected(dtOpenSGSBean)) {
            return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.update.error");
        }
        return ServicioMensaje.getInstance().getMensajeOK();
    }

    public DtMensaje isDeletable(DtOpenSGSBean dtOpenSGSBean) {
        if (this.isProtected(dtOpenSGSBean)) {
            return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.delete.error");
        }
        if (this.isSistema(dtOpenSGSBean)) {
            return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.delete.error");
        }
        return ServicioMensaje.getInstance().getMensajeOK();
    }

    public DtMensaje isRestorable(DtOpenSGSBean dtOpenSGSBean) {
        if (this.isProtected(dtOpenSGSBean)) {
            return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.restore.error");
        }
        if (this.isSistema(dtOpenSGSBean)) {
            return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.restore.error");
        }
        return ServicioMensaje.getInstance().getMensajeOK();
    }

    public DtMensaje isDeactivatable(DtOpenSGSBean dtOpenSGSBean) {
        if (this.isProtected(dtOpenSGSBean)) {
            return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.disable.error");
        }
        if (this.isSistema(dtOpenSGSBean)) {
            return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.disable.error");
        }
        return ServicioMensaje.getInstance().getMensajeOK();
    }

    public DtMensaje isActivable(DtOpenSGSBean dtOpenSGSBean) {
        if (this.isProtected(dtOpenSGSBean)) {
            return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.enable.error");
        }
        if (this.isSistema(dtOpenSGSBean)) {
            return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.enable.error");
        }
        return ServicioMensaje.getInstance().getMensajeOK();
    }

    private boolean isProtected(DtOpenSGSBean dtOpenSGSBean) {
        if (dtOpenSGSBean.getClassName().equals("Actividad")) {
            return true;
        } else if (dtOpenSGSBean.getClassName().equals("Permiso")) {
            return true;
        } else if (dtOpenSGSBean.getClassName().equals("PerfilAplicacion")) {
            return true;
        } else if (dtOpenSGSBean.getClassName().equals("Sesion")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isSistema(DtOpenSGSBean dtOpenSGSBean) {
        if (dtOpenSGSBean.getClassName().equals("Sistema")) {
            return true;
        } else {
            return false;
        }
    }

}
