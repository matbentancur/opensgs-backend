package opensgs.sistema.logica.servicios;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import opensgs.logica.servicios.ServicioEtiquetasRemplazables;
import opensgs.sistema.enums.FuenteTipo;
import opensgs.sistema.logica.beans.Aplicacion;
import opensgs.sistema.logica.beans.Archivo;
import opensgs.sistema.logica.beans.Constancia;
import opensgs.sistema.logica.beans.ConstanciaElementoImagen;
import opensgs.sistema.logica.beans.ConstanciaElementoTexto;
import opensgs.sistema.persistencia.manejadores.ManejadorConstanciaElementoImagen;
import opensgs.sistema.persistencia.manejadores.ManejadorConstanciaElementoTexto;
import opensgs.usuarios.logica.beans.Usuario;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class ServicioConstancia {

    private static ServicioConstancia instance = null;
    private static String tempDirectory;

    private ServicioConstancia() {
    }

    public static ServicioConstancia getInstance() {
        if (instance == null) {
            instance = new ServicioConstancia();
            tempDirectory = ServicioSistema.obtenerFilesPath() + "tmp/";
        }
        return instance;
    }

    public DataHandler emitirConstancia(Constancia constancia, Usuario usuario) {
        Path path = Paths.get(tempDirectory + constancia.getArchivo().getNombre());

        try {
            PDDocument documento = this.cargarPDFConstancia(constancia.getArchivo());
            documento = this.agregarTextos(documento, constancia.getId(), constancia.getAplicacion(), usuario);
            documento = this.agregarImagenes(documento, constancia.getId());
            documento.save(new File(path.toString()));
            documento.close();

            File archivoDescarga = new File(path.toString());
            DataSource dataSource = new FileDataSource(archivoDescarga);
            return new DataHandler(dataSource);
        } catch (IOException ex) {
            Logger.getLogger(ServicioConstancia.class.getName()).log(Level.SEVERE, null, ex);
        }
//        finally {
//            try {
//                Files.deleteIfExists(path);
//            } catch (IOException ex) {
//                Logger.getLogger(ServicioConstancia.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        return null;
    }

    private PDDocument cargarPDFConstancia(Archivo archivo) {
        File file = ServicioArchivo.getInstance().obtenerFile(archivo);
        try {
            PDDocument documento = PDDocument.load(file);
            return documento;
        } catch (IOException ex) {
            Logger.getLogger(ServicioConstancia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private PDImageXObject cargarImagenConstancia(PDDocument documento, Archivo archivo) {
        File file = ServicioArchivo.getInstance().obtenerFile(archivo);
        try {
            PDImageXObject imagen = PDImageXObject.createFromFileByContent(file, documento);
            return imagen;
        } catch (IOException ex) {
            Logger.getLogger(ServicioConstancia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private PDDocument agregarTextos(PDDocument documento, Long constanciaId, Aplicacion aplicacion, Usuario usuario) {
        PDPage page = documento.getPage(0);
        List<ConstanciaElementoTexto> listaConstanciaElementoTexto = ManejadorConstanciaElementoTexto.getInstance().listarElementoTextoConstanciaActivas(constanciaId);

        try {
            PDPageContentStream contentStream = new PDPageContentStream(
                    documento,
                    page,
                    PDPageContentStream.AppendMode.APPEND,
                    true,
                    true
            );
            for (ConstanciaElementoTexto constanciaElementoTexto : listaConstanciaElementoTexto) {

                contentStream.beginText();
                contentStream.setFont(
                        this.fuenteTipoToPDType1Font(constanciaElementoTexto.getFuenteTipo()),
                        constanciaElementoTexto.getFuenteTamanio()
                );
                contentStream.newLineAtOffset(constanciaElementoTexto.getPosicionX(), constanciaElementoTexto.getPosicionY());
                String texto = constanciaElementoTexto.getTexto();
                texto = this.procesarEtiquetas(texto, aplicacion, usuario);
                contentStream.showText(texto);
                contentStream.endText();
            }
            contentStream.close();
        } catch (IOException ex) {
            Logger.getLogger(ServicioConstancia.class.getName()).log(Level.SEVERE, null, ex);
        }

        return documento;
    }

    private PDDocument agregarImagenes(PDDocument documento, Long constanciaId) {
        PDPage page = documento.getPage(0);
        List<ConstanciaElementoImagen> listaConstanciaElementoImagens = ManejadorConstanciaElementoImagen.getInstance().listarElementoImagenConstanciaActivas(constanciaId);

        try {
            PDPageContentStream contentStream = new PDPageContentStream(
                    documento,
                    page,
                    PDPageContentStream.AppendMode.APPEND,
                    true,
                    true
            );
            for (ConstanciaElementoImagen constanciaElementoImagen : listaConstanciaElementoImagens) {
                PDImageXObject imagen = this.cargarImagenConstancia(documento, constanciaElementoImagen.getArchivo());
                contentStream.drawImage(
                        imagen,
                        constanciaElementoImagen.getPosicionX(),
                        constanciaElementoImagen.getPosicionY(),
                        constanciaElementoImagen.getAncho(),
                        constanciaElementoImagen.getAlto()
                );
            }
            contentStream.close();
        } catch (IOException ex) {
            Logger.getLogger(ServicioConstancia.class.getName()).log(Level.SEVERE, null, ex);
        }

        return documento;
    }

    private String procesarEtiquetas(String texto, Aplicacion aplicacion, Usuario usuario) {
        texto = ServicioEtiquetasRemplazables.getInstance().reemplazarEtiquetasSistema(texto);
        if (aplicacion != null) {
            texto = ServicioEtiquetasRemplazables.getInstance().reemplazarEtiquetasAplicacion(texto, aplicacion);
        }
        if (usuario != null) {
            texto = ServicioEtiquetasRemplazables.getInstance().reemplazarEtiquetasUsuario(texto, usuario);
        }
        return texto;
    }

    private PDType1Font fuenteTipoToPDType1Font(FuenteTipo fuenteTipo) {
        switch (fuenteTipo) {
            case COURIER:
                return PDType1Font.COURIER;
            case COURIER_BOLD:
                return PDType1Font.COURIER_BOLD;
            case COURIER_BOLD_OBLIQUE:
                return PDType1Font.COURIER_BOLD_OBLIQUE;
            case COURIER_OBLIQUE:
                return PDType1Font.COURIER_OBLIQUE;
            case HELVETICA:
                return PDType1Font.HELVETICA;
            case HELVETICA_BOLD:
                return PDType1Font.HELVETICA_BOLD;
            case HELVETICA_BOLD_OBLIQUE:
                return PDType1Font.HELVETICA_BOLD_OBLIQUE;
            case HELVETICA_OBLIQUE:
                return PDType1Font.HELVETICA_OBLIQUE;
            case TIMES_BOLD:
                return PDType1Font.TIMES_BOLD;
            case TIMES_BOLD_ITALIC:
                return PDType1Font.TIMES_BOLD_ITALIC;
            case TIMES_ITALIC:
                return PDType1Font.TIMES_ITALIC;
            case TIMES_ROMAN:
                return PDType1Font.TIMES_ROMAN;
            default:
                break;
        }
        return null;
    }

}
