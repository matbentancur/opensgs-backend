package opensgs.sistema.logica.servicios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import opensgs.datatypes.DtMensaje;
import opensgs.logica.servicios.ServicioMensaje;
import opensgs.sistema.logica.beans.Archivo;
import opensgs.sistema.persistencia.manejadores.ManejadorArchivo;

public class ServicioArchivo {

    private static ServicioArchivo instance = null;
    private static String filesDirectory;
    private static String tempDirectory;

    private ServicioArchivo() {
    }

    public static ServicioArchivo getInstance() {
        if (instance == null) {
            instance = new ServicioArchivo();
            filesDirectory = ServicioSistema.obtenerFilesPath() + "files/";
            tempDirectory = ServicioSistema.obtenerFilesPath() + "tmp/";
        }
        return instance;
    }

    public DtMensaje subirArchivoTemporal(DataHandler archivoSubir, String archivoSubirNombre) {
        try {
            this.crearDirectorios();

            Path path = Paths.get(tempDirectory + archivoSubirNombre);

            InputStream input = archivoSubir.getInputStream();
            File nuevoArchivo = new File(path.toString());

            OutputStream outputStream = new FileOutputStream(nuevoArchivo);

            byte[] b = new byte[100000];
            int bytesRead = 0;
            while ((bytesRead = input.read(b)) != -1) {
                outputStream.write(b, 0, bytesRead);
            }
            outputStream.close();
            return ServicioMensaje.getInstance().getMensajeOK("Archivo.upload.success");

        } catch (IOException ex) {
            Logger.getLogger(ServicioArchivo.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR("Archivo.upload.error");
        }
    }

    public DtMensaje borrarArchivoTemporal(String archivoNombre) {
        try {
            Path path = Paths.get(tempDirectory + archivoNombre);
            Files.deleteIfExists(path);
            return ServicioMensaje.getInstance().getMensajeOK("Archivo.delete.success");
        } catch (IOException ex) {
            Logger.getLogger(ServicioArchivo.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR("Archivo.delete.error");
        }
    }

    public DtMensaje moverArchivoTemporal(String archivoNombre) {
        try {
            this.crearDirectorios();

            Path tempPath = Paths.get(tempDirectory + archivoNombre);
            Path filePath = Paths.get(filesDirectory + archivoNombre);

            Files.move(tempPath, filePath, StandardCopyOption.REPLACE_EXISTING);
            return ServicioMensaje.getInstance().getMensajeOK("Archivo.moved.success");
        } catch (Exception ex) {
            Logger.getLogger(ServicioArchivo.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR("Archivo.moved.error");
        }
    }

//    public DtMensaje copiarArchivoATemporal(String archivoNombre) {
//        try {
//            this.crearDirectorios();
//
//            Path tempPath = Paths.get(tempDirectory + archivoNombre);
//            Path filePath = Paths.get(filesDirectory + archivoNombre);
//
//            Files.copy(filePath, tempPath, StandardCopyOption.REPLACE_EXISTING);
//            return ServicioMensaje.getInstance().getMensajeOK("Archivo.moved.success");
//        } catch (Exception ex) {
//            Logger.getLogger(ServicioArchivo.class.getName()).log(Level.SEVERE, null, ex);
//            return ServicioMensaje.getInstance().getMensajeERROR("Archivo.moved.error");
//        }
//    }

    public DtMensaje borrarArchivo(Long id) {
        try {
            Archivo archivo = ManejadorArchivo.getInstance().obtenerArchivo(id);
            Path path = Paths.get(filesDirectory + archivo.getUbicacion());
            Files.deleteIfExists(path);
            return ServicioMensaje.getInstance().getMensajeOK("Archivo.delete.success");
        } catch (IOException ex) {
            Logger.getLogger(ServicioArchivo.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR("Archivo.delete.error");
        }
    }

    public DataHandler descargarArchivo(Archivo archivo) {

        Path path = Paths.get(filesDirectory + archivo.getUbicacion());

        File archivoDescarga = new File(path.toString());
        DataSource dataSource = new FileDataSource(archivoDescarga);
        return new DataHandler(dataSource);
    }

    public File obtenerFile(Archivo archivo) {
        Path path = Paths.get(filesDirectory + archivo.getUbicacion());
        return new File(path.toString());
    }

    public Archivo obtenerMetadatos(String archivoSubirNombre, Archivo archivo) {
        try {
            Path path = Paths.get(tempDirectory + archivoSubirNombre);

            File archivoSubido = new File(path.toString());

            String mimeType = Files.probeContentType(path);
            if (mimeType != null) {
                archivo.setMime(mimeType);
            }

            String extension = this.obtenerExtension(archivo.getMime());
            if (extension == null) {
                return null;
            }
            archivo.setExtension(extension);

            archivo.setPeso(archivoSubido.length());
            archivo.setUbicacion(archivoSubirNombre);

            return archivo;

        } catch (Exception ex) {
            Logger.getLogger(ServicioArchivo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private String obtenerExtension(String mimeType) {
        switch (mimeType) {
            case "application/pdf":
                return "pdf";
            case "application/rtf":
                return "rtf";
            case "application/vnd.oasis.opendocument.text":
                return "odt";
            case "application/vnd.oasis.opendocument.spreadsheet":
                return "ods";
            case "application/vnd.oasis.opendocument.presentation":
                return "odp";
            case "application/vnd.oasis.opendocument.graphics":
                return "odg";
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
                return "docx";
            case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
                return "xlsx";
            case "application/vnd.openxmlformats-officedocument.presentationml.presentation":
                return "pptx";
            case "image/png":
                return "png";
            case "image/gif":
                return "gif";
            case "image/jpeg":
                return "jpg";
            case "image/svg+xml":
                return "svg";
            default:
                return null;
        }

    }

    private DtMensaje crearDirectorios() {
        try {
            File directorioTemporal = new File(tempDirectory);
            if (!directorioTemporal.exists()) {
                directorioTemporal.mkdirs();
            }

            File directorioArchivos = new File(filesDirectory);
            if (!directorioArchivos.exists()) {
                directorioArchivos.mkdirs();
            }
            return ServicioMensaje.getInstance().getMensajeOK("Archivo.directory.success");
        } catch (Exception ex) {
            Logger.getLogger(ServicioArchivo.class.getName()).log(Level.SEVERE, null, ex);
            return ServicioMensaje.getInstance().getMensajeERROR("Archivo.directory.error");
        }
    }

//    public DtMensaje subirArchivo(byte[] fileContent, Archivo archivo) {
//        OutputStream outputStream = null;
//        try {
//            
//            Path path = Paths.get(filesPath + archivo.getNombre());
//            
//            File file = new File(path.toString());
//            
//            outputStream = new FileOutputStream(file);
//            outputStream.write(fileContent);
//            
//            return ServicioMensaje.getInstance().getMensajeOK("OpenSGSBean.upload.success");
//            
//        } catch (IOException ex) {
//            Logger.getLogger(ServicioArchivo.class.getName()).log(Level.SEVERE, null, ex);
//            return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.upload.error");
//        } finally {
//            if (outputStream != null) {
//                try {
//                    outputStream.close();
//                } catch (IOException ex) {
//                    Logger.getLogger(ServicioArchivo.class.getName()).log(Level.SEVERE, null, ex);
//                    return ServicioMensaje.getInstance().getMensajeERROR("OpenSGSBean.upload.error");
//                }
//            }
//        }
//    }
//    private static byte[] getFileContent(InputStream inputStream) {
//        try {
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            int reads = inputStream.read();
//            while (reads != -1) {
//                baos.write(reads);
//                reads = inputStream.read();
//            }
//            return baos.toByteArray();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return null;
//    }
}
