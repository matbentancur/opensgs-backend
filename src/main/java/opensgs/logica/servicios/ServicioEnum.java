package opensgs.logica.servicios;

import java.util.ArrayList;
import java.util.List;
import opensgs.enums.Alcance;
import opensgs.sistema.enums.AlcanceArchivo;
import opensgs.usuarios.enums.AlcancePerfil;
import opensgs.enums.Elemento;
import opensgs.enums.Operacion;
import opensgs.sistema.enums.ConstanciaTipo;
import opensgs.sistema.enums.FuenteTipo;
import opensgs.sistema.enums.NotificacionDestino;
import opensgs.sistema.enums.ServidorAutenticacionTipo;
import opensgs.sistema.enums.ServidorCorreoIdentificacion;
import opensgs.sistema.enums.ServidorCorreoSeguridad;
import opensgs.solicitudes.enums.EsctructuraDatos;
import opensgs.usuarios.enums.DocumentoTipo;

public class ServicioEnum {

    private static ServicioEnum instance = null;

    private ServicioEnum() {
    }

    public static ServicioEnum getInstance() {
        if (instance == null) {
            instance = new ServicioEnum();
        }
        return instance;
    }

    public List<String> getListEnum(String enumName) {
        List<String> lista = new ArrayList<String>();

        if (enumName.equals("Elemento")) {
            return this.enumToList(Elemento.class);
        } else if (enumName.equals("Operacion")) {
            return this.enumToList(Operacion.class);
        } else if (enumName.equals("Alcance")) {
            return this.enumToList(Alcance.class);
        } else if (enumName.equals("AlcancePerfil")) {
            return this.enumToList(AlcancePerfil.class);
        } else if (enumName.equals("AlcanceArchivo")) {
            return this.enumToList(AlcanceArchivo.class);
        } else if (enumName.equals("ServidorAutenticacionTipo")) {
            return this.enumToList(ServidorAutenticacionTipo.class);
        } else if (enumName.equals("ServidorCorreoIdentificacion")) {
            return this.enumToList(ServidorCorreoIdentificacion.class);
        } else if (enumName.equals("ServidorCorreoSeguridad")) {
            return this.enumToList(ServidorCorreoSeguridad.class);
        } else if (enumName.equals("DocumentoTipo")) {
            return this.enumToList(DocumentoTipo.class);
        } else if (enumName.equals("NotificacionDestino")) {
            return this.enumToList(NotificacionDestino.class);
        } else if (enumName.equals("EsctructuraDatos")) {
            return this.enumToList(EsctructuraDatos.class);
        } else if (enumName.equals("ConstanciaTipo")) {
            return this.enumToList(ConstanciaTipo.class);
        } else if (enumName.equals("FuenteTipo")) {
            return this.enumToList(FuenteTipo.class);
        }

        return lista;
    }

    public <E extends Enum<E>> E parseEnum(Class<E> enumType, String elementoEnumString) {
        return E.valueOf(enumType, elementoEnumString);
    }

    private <E extends Enum<E>> List<String> enumToList(Class<E> enumType) {
        List<String> lista = new ArrayList<String>();
        for (E item : enumType.getEnumConstants()) {
            lista.add(item.toString());
        }
        return lista;
    }

}
