import Modelo.DatosCSV;
import Vista.VentanaPrincipal;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;

public class GeneradorReports {
    public static void generarReportePDF(VentanaPrincipal ventana, DatosCSV datos, 
                                       String algoritmo, String velocidad, 
                                       String direccion, long tiempoEjecucion, 
                                       int pasos) {
        Document document = new Document();
        try {
            // Crear PDF
            PdfWriter.getInstance(document, new FileOutputStream("Reporte_Ordenamiento.pdf"));
            document.open();

            // Agregar metadatos
            document.addTitle("Reporte de Ordenamiento");
            document.addSubject("Práctica 2 - IPC1");
            document.addKeywords("USAC, IPC1, Ordenamiento");

            // Fuentes personalizadas
            Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Font fontSubtitulo = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Font fontNormal = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

            // 1. Encabezado con información del estudiante
            Paragraph encabezado = new Paragraph("UNIVERSIDAD DE SAN CARLOS DE GUATEMALA", fontTitulo);
            encabezado.setAlignment(Element.ALIGN_CENTER);
            document.add(encabezado);

            encabezado = new Paragraph("Escuela de Ciencias y Sistemas", fontSubtitulo);
            encabezado.setAlignment(Element.ALIGN_CENTER);
            document.add(encabezado);

            encabezado = new Paragraph("Reporte de Práctica 2 - IPC1", fontSubtitulo);
            encabezado.setAlignment(Element.ALIGN_CENTER);
            document.add(encabezado);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Nombre: [TU NOMBRE]", fontNormal));
            document.add(new Paragraph("Carné: [TU CARNET]", fontNormal));
            document.add(new Paragraph(" "));

            // 2. Información del proceso de ordenamiento
            Paragraph infoProceso = new Paragraph("Información del Proceso de Ordenamiento", fontSubtitulo);
            infoProceso.setSpacingAfter(10);
            document.add(infoProceso);

            PdfPTable tablaInfo = new PdfPTable(2);
            tablaInfo.setWidthPercentage(100);
            tablaInfo.setSpacingBefore(10);
            tablaInfo.setSpacingAfter(10);

            agregarCelda(tablaInfo, "Algoritmo utilizado:", fontNormal);
            agregarCelda(tablaInfo, algoritmo, fontNormal);
            agregarCelda(tablaInfo, "Velocidad:", fontNormal);
            agregarCelda(tablaInfo, velocidad, fontNormal);
            agregarCelda(tablaInfo, "Dirección:", fontNormal);
            agregarCelda(tablaInfo, direccion, fontNormal);
            agregarCelda(tablaInfo, "Tiempo de ejecución:", fontNormal);
            agregarCelda(tablaInfo, formatTiempo(tiempoEjecucion), fontNormal);
            agregarCelda(tablaInfo, "Pasos realizados:", fontNormal);
            agregarCelda(tablaInfo, String.valueOf(pasos), fontNormal);

            document.add(tablaInfo);

            // 3. Datos originales
            Paragraph datosOriginales = new Paragraph("Datos Originales", fontSubtitulo);
            datosOriginales.setSpacingAfter(10);
            document.add(datosOriginales);

            // Gráfica original
            BufferedImage imgGraficaOriginal = ventana.getPanelGrafica().getGraficaComoImagen();
            Image graficaOriginal = Image.getInstance(imgGraficaOriginal, null);
            graficaOriginal.scaleToFit(400, 300);
            graficaOriginal.setAlignment(Image.MIDDLE);
            document.add(graficaOriginal);

            // Tabla de datos originales
            PdfPTable tablaDatosOriginales = crearTablaDatos(datos.getEjeX(), datos.getEjeY(), 
                                                           datos.getCategorias(), datos.getValores());
            tablaDatosOriginales.setSpacingBefore(10);
            tablaDatosOriginales.setSpacingAfter(20);
            document.add(tablaDatosOriginales);

            // 4. Datos ordenados
            Paragraph datosOrdenados = new Paragraph("Datos Ordenados", fontSubtitulo);
            datosOrdenados.setSpacingAfter(10);
            document.add(datosOrdenados);

            // Gráfica ordenada (asumiendo que ya está actualizada en el panel)
            BufferedImage imgGraficaOrdenada = ventana.getPanelGrafica().getGraficaComoImagen();
            Image graficaOrdenada = Image.getInstance(imgGraficaOrdenada, null);
            graficaOrdenada.scaleToFit(400, 300);
            graficaOrdenada.setAlignment(Image.MIDDLE);
            document.add(graficaOrdenada);

            // Tabla de datos ordenados
            PdfPTable tablaDatosOrdenados = crearTablaDatos(datos.getEjeX(), datos.getEjeY(), 
                                                          datos.getCategorias(), datos.getValores());
            tablaDatosOrdenados.setSpacingBefore(10);
            document.add(tablaDatosOrdenados);

            document.close();
            JOptionPane.showMessageDialog(ventana, "Reporte generado exitosamente como 'Reporte_Ordenamiento.pdf'", 
                                        "Reporte", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(ventana, "Error al generar el reporte: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static PdfPTable crearTablaDatos(String encabezadoX, String encabezadoY, 
                                           String[] categorias, int[] valores) {
        PdfPTable tabla = new PdfPTable(2);
        tabla.setWidthPercentage(80);
        tabla.setHorizontalAlignment(Element.ALIGN_CENTER);

        // Encabezados con estilo
        PdfPCell celdaEncabezado = new PdfPCell(new Phrase(encabezadoX));
        celdaEncabezado.setBackgroundColor(new BaseColor(79, 129, 189));
        celdaEncabezado.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaEncabezado.setPadding(5);
        tabla.addCell(celdaEncabezado);

        celdaEncabezado = new PdfPCell(new Phrase(encabezadoY));
        celdaEncabezado.setBackgroundColor(new BaseColor(79, 129, 189));
        celdaEncabezado.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaEncabezado.setPadding(5);
        tabla.addCell(celdaEncabezado);

        // Datos con colores alternados
        for (int i = 0; i < categorias.length; i++) {
            PdfPCell celda = new PdfPCell(new Phrase(categorias[i]));
            celda.setBackgroundColor(i % 2 == 0 ? 
                new BaseColor(221, 235, 247) : new BaseColor(207, 226, 243));
            celda.setPadding(5);
            tabla.addCell(celda);

            celda = new PdfPCell(new Phrase(String.valueOf(valores[i])));
            celda.setBackgroundColor(i % 2 == 0 ? 
                new BaseColor(221, 235, 247) : new BaseColor(207, 226, 243));
            celda.setPadding(5);
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(celda);
        }

        return tabla;
    }

    private static void agregarCelda(PdfPTable tabla, String texto, Font font) {
        PdfPCell celda = new PdfPCell(new Phrase(texto, font));
        celda.setBorderColor(BaseColor.LIGHT_GRAY);
        celda.setPadding(5);
        tabla.addCell(celda);
    }

    private static String formatTiempo(long milisegundos) {
        long minutos = TimeUnit.MILLISECONDS.toMinutes(milisegundos);
        long segundos = TimeUnit.MILLISECONDS.toSeconds(milisegundos) - 
                       TimeUnit.MINUTES.toSeconds(minutos);
        long ms = milisegundos - TimeUnit.SECONDS.toMillis(
                 TimeUnit.MILLISECONDS.toSeconds(milisegundos));
        return String.format("%02d:%02d:%03d", minutos, segundos, ms);
    }
}