import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

public class GeneradorReportes {
    public static void generarReporte(DatosCSV datos, String algoritmo, String tipoOrden, 
                                    String velocidad, String tiempo, int pasos) {
        Document document = new Document();
        
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Reporte.pdf"));
            document.open();
            
            // Título
            Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph titulo = new Paragraph("Reporte de Ordenamiento", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            
            // Información del estudiante
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Nombre: [Tu nombre]"));
            document.add(new Paragraph("Carné: [Tu carné]"));
            
            // Datos del proceso
            document.add(new Paragraph("\nDatos del proceso:"));
            document.add(new Paragraph("Algoritmo: " + algoritmo));
            document.add(new Paragraph("Tipo de orden: " + tipoOrden));
            document.add(new Paragraph("Velocidad: " + velocidad));
            document.add(new Paragraph("Tiempo: " + tiempo));
            document.add(new Paragraph("Pasos: " + pasos));
            
            // Datos mínimo y máximo
            int min = datos.getValores()[0];
            int max = datos.getValores()[datos.getValores().length-1];
            document.add(new Paragraph("\nDato mínimo: " + min));
            document.add(new Paragraph("Dato máximo: " + max));
            
            // Tabla de datos originales
            document.add(new Paragraph("\nDatos originales:"));
            agregarTablaDatos(document, datos.getCategorias(), datos.getValores(), datos.getEjeX(), datos.getEjeY());
            
            // Tabla de datos ordenados
            document.add(new Paragraph("\nDatos ordenados:"));
            agregarTablaDatos(document, datos.getCategorias(), datos.getValores(), datos.getEjeX(), datos.getEjeY());
            
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void agregarTablaDatos(Document document, String[] categorias, int[] valores, String ejeX, String ejeY) 
            throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        
        // Encabezados
        PdfPCell cell1 = new PdfPCell(new Phrase(ejeX));
        PdfPCell cell2 = new PdfPCell(new Phrase(ejeY));
        table.addCell(cell1);
        table.addCell(cell2);
        
        // Datos
        for (int i = 0; i < categorias.length; i++) {
            table.addCell(categorias[i]);
            table.addCell(String.valueOf(valores[i]));
        }
        
        document.add(table);
    }
}