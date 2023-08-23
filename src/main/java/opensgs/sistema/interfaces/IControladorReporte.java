package opensgs.sistema.interfaces;

import java.util.List;
import javax.activation.DataHandler;
import opensgs.datatypes.DtMensaje;
import opensgs.sistema.datatypes.DtReporte;
import opensgs.usuarios.datatypes.DtSesion;

public interface IControladorReporte {

    public DtMensaje crearReporteAplicacion(DtReporte dtReporte, Long aplicacionId, DtSesion dtSesion);

    public List<DtReporte> listarReportesSistema(boolean borrado, DtSesion dtSesion);

    public List<DtReporte> listarReportesAplicacion(Long aplicacionId, boolean borrado, DtSesion dtSesion);

    public List<DtReporte> listarReportesActivos(DtSesion dtSesion);

    public DataHandler descargarReporteCSV(Long id, DtSesion dtSesion);

    public DataHandler descargarReporteCSVAplicacion(Long id, Long aplicacionId, DtSesion dtSesion);

}
