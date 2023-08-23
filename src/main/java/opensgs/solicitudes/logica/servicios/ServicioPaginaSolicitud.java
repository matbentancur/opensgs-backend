package opensgs.solicitudes.logica.servicios;

import opensgs.solicitudes.datatypes.DtPaginaSolicitud;
import opensgs.solicitudes.enums.EsctructuraDatos;
import opensgs.solicitudes.logica.beans.PaginaSolicitud;
import opensgs.solicitudes.paginas.beans.cep.DatosPersonalesCEP;
import opensgs.solicitudes.paginas.beans.cep.DifusionCEP;
import opensgs.solicitudes.paginas.beans.cep.EducacionCEP;
import opensgs.solicitudes.paginas.beans.cep.SituacionLaboralCEP;

public class ServicioPaginaSolicitud {

    private static ServicioPaginaSolicitud instance = null;

    private ServicioPaginaSolicitud() {
    }

    public static ServicioPaginaSolicitud getInstance() {
        if (instance == null) {
            instance = new ServicioPaginaSolicitud();
        }
        return instance;
    }

    public PaginaSolicitud parseClassName(String className) {
        if (className.equals("DatosPersonalesCEP")) {
            return new DatosPersonalesCEP();
        } else if (className.equals("DifusionCEP")) {
            return new DifusionCEP();
        } else if (className.equals("EducacionCEP")) {
            return new EducacionCEP();
        } else if (className.equals("SituacionLaboralCEP")) {
            return new SituacionLaboralCEP();
        }
        return null;
    }

    public EsctructuraDatos parseEsctructuraDatos(String esctructuraDatosString) {
        if (esctructuraDatosString.equals("DatosPersonalesCEP")) {
            return EsctructuraDatos.DatosPersonalesCEP;
        } else if (esctructuraDatosString.equals("DifusionCEP")) {
            return EsctructuraDatos.DifusionCEP;
        } else if (esctructuraDatosString.equals("EducacionCEP")) {
            return EsctructuraDatos.EducacionCEP;
        } else if (esctructuraDatosString.equals("SituacionLaboralCEP")) {
            return EsctructuraDatos.SituacionLaboralCEP;
        }
        return null;
    }

    public PaginaSolicitud getNewPaginaSolicitud(EsctructuraDatos esctructuraDatos) {
        switch (esctructuraDatos) {
            case DatosPersonalesCEP:
                return new DatosPersonalesCEP();
            case DifusionCEP:
                return new DifusionCEP();
            case EducacionCEP:
                return new EducacionCEP();
            case SituacionLaboralCEP:
                return new SituacionLaboralCEP();
            default:
                break;
        }
        return null;
    }

    public PaginaSolicitud getNewPaginaSolicitud(DtPaginaSolicitud dtPaginaSolicitud) {
        if (dtPaginaSolicitud.getClassName().equals("DatosPersonalesCEP")) {
            return new DatosPersonalesCEP(dtPaginaSolicitud);
        } else if (dtPaginaSolicitud.getClassName().equals("DifusionCEP")) {
            return new DifusionCEP(dtPaginaSolicitud);
        } else if (dtPaginaSolicitud.getClassName().equals("EducacionCEP")) {
            return new EducacionCEP(dtPaginaSolicitud);
        } else if (dtPaginaSolicitud.getClassName().equals("SituacionLaboralCEP")) {
            return new SituacionLaboralCEP(dtPaginaSolicitud);
        }
        return null;
    }
    
    public PaginaSolicitud parseDtPaginaSolicitud(DtPaginaSolicitud dtPaginaSolicitud) {
        return this.parseClassName(dtPaginaSolicitud.getClassName());
    }

}
