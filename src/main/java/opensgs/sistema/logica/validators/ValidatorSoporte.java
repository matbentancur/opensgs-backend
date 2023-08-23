package opensgs.sistema.logica.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import opensgs.datatypes.DtMensaje;
import opensgs.logica.servicios.ServicioMensaje;
import opensgs.sistema.logica.beans.Soporte;

public class ValidatorSoporte {

    private static ValidatorSoporte instance = null;

    private ValidatorSoporte() {
    }

    public static ValidatorSoporte getInstance() {
        if (instance == null) {
            instance = new ValidatorSoporte();
        }
        return instance;
    }

    public DtMensaje validar(Soporte soporte) {
        List<String> listaErrores = new ArrayList<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Soporte>> violations = validator.validate(soporte);

        for (ConstraintViolation<Soporte> violation : violations) {
            listaErrores.add(violation.getMessage());
        }
        if (listaErrores.isEmpty()) {
            return ServicioMensaje.getInstance().getMensajeOK();
        } else {
            return ServicioMensaje.getInstance().getMensajeERROR(listaErrores);
        }
    }

}
