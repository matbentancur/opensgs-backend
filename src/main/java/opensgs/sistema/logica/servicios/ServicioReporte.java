package opensgs.sistema.logica.servicios;

import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import opensgs.persistencia.manejadores.ManejadorOpenSGSBean;
import opensgs.sistema.logica.beans.Reporte;
import opensgs.sistema.logica.controladores.ControladorReporte;
import opensgs.sistema.persistencia.manejadores.ManejadorReporte;

public class ServicioReporte {

    private static ServicioReporte instance = null;

    private ServicioReporte() {
    }

    public static ServicioReporte getInstance() {
        if (instance == null) {
            instance = new ServicioReporte();
        }
        return instance;
    }

    public List<String[]> ejecutarSQL(String sql) throws Exception{
        try {
            List<String[]> stringList = new ArrayList<>();
            List<Object[]> entities = ManejadorOpenSGSBean.getInstance().ejecutarSQL(sql);
            if (entities != null) {
                for (Object[] entity : entities) {
                    String[] fila = new String[entity.length];
                    int posicion = 0;
                    for (Object entityCol : entity) {
                        String strValue = String.valueOf(entityCol);
                        fila[posicion] = strValue;
                        posicion++;
                    }
                    stringList.add(fila);
                }
            }
            return stringList;
        } catch (Exception e) {
            throw e;
        }
    }

    private List<String[]> agregarCabezal(List<String[]> stringList, Reporte reporte) {
        if (reporte.getCabezal() != null && !reporte.getCabezal().equals("")) {
            String[] cabezal = new String[reporte.getCabezal().length()];
            cabezal = reporte.getCabezal().split(",", -1);
            stringList.add(0, cabezal);
        }
        return stringList;
    }

    public DataHandler descargarReporteCSV(Long id) {

        Reporte reporte = ManejadorReporte.getInstance().obtenerReporte(id);

        try {
            List<String[]> stringList = this.ejecutarSQL(reporte.getSentenciaSQL());

            stringList = this.agregarCabezal(stringList, reporte);

            File tempFile = File.createTempFile(reporte.getNombre(), ".csv");
            CSVWriter csvWriter;
            csvWriter = new CSVWriter(new FileWriter(tempFile));
            csvWriter.writeAll(stringList);
            csvWriter.close();
            DataSource dataSource = new FileDataSource(tempFile);
            return new DataHandler(dataSource);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControladorReporte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControladorReporte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ServicioReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
//    public InputStream descargarReporteCSV(DtReporte dtReporte) {
//        InputStream imputStream = null;
//        try {
//            List<String[]> stringList = ServicioReporte.getInstance().ejecutarSQL(dtReporte.getSentenciaSQL());
//            //AGREGA EL CABEZAL
//            if (dtReporte.getCabezal() != null && !dtReporte.equals("")) {
//                String[] cabezal = new String[dtReporte.getCabezal().length()];
//                cabezal = dtReporte.getCabezal().split(",", -1);
//                stringList.add(0, cabezal);
//            }
//            File temp = null;
//            try {
//                temp = File.createTempFile("archivo-temp", ".csv");
//                CSVWriter csvWriter;
//                csvWriter = new CSVWriter(new FileWriter(temp));
//                csvWriter.writeAll(stringList);
//                csvWriter.close();
//            } catch (IOException ex) {
//                Logger.getLogger(ControladorReporte.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            imputStream = new FileInputStream(temp);
//
//        } catch (FileNotFoundException fnfe) {
//            Exception ex = new Exception("No se pudo descargar");
//            Logger.getLogger(ControladorReporte.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return imputStream;
//    }

}
