package Modelo;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.Date;

public class GeneradorReportes {
    public static void generarReporte(DatosCSV datos, String algoritmo, String velocidad, 
                                    String orden, long tiempoEjecucion) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("ReporteOrdenamiento.pdf"));
            document.open();
            
            // Título
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Reporte de Ordenamiento", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);
            
            // Información básica
            document.add(new Paragraph("Fecha: " + new Date()));
            document.add(new Paragraph("Algoritmo utilizado: " + algoritmo));
            document.add(new Paragraph("Velocidad: " + velocidad));
            document.add(new Paragraph("Orden: " + orden));
            document.add(new Paragraph("Tiempo de ejecución: " + tiempoEjecucion + " ms"));
            document.add(new Paragraph(" "));
            
            // Datos originales
            document.add(new Paragraph("Datos originales:"));
            document.add(crearTablaDatos(datos.getCategorias(), datos.getValores()));
            document.add(new Paragraph(" "));
            
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static PdfPTable crearTablaDatos(String[] categorias, int[] valores) {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        
        // Encabezados
        table.addCell("Categoría");
        table.addCell("Valor");
        
        // Datos
        for (int i = 0; i < categorias.length; i++) {
            table.addCell(categorias[i]);
            table.addCell(String.valueOf(valores[i]));
        }
        
        return table;
    }
}