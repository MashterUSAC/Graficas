// Modelo/GeneradorReports.java
package Modelo;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneradorReports {
    public static void generarReporte(String nombre, String carnet, String algoritmo, 
                                    String direccion, String velocidad, long tiempoEjecucion, 
                                    int pasos, String[] categoriasOriginal, int[] valoresOriginal,
                                    String[] categoriasOrdenado, int[] valoresOrdenado,
                                    String ejeX, String ejeY) {
        try {
            Document document = new Document();
            String fileName = "Reporte_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            
            document.open();
            
            // Encabezado
            Paragraph header = new Paragraph("Reporte de Ordenamiento");
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);
            
            // Datos del estudiante
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Generado por: " + nombre));
            document.add(new Paragraph("Carné: " + carnet));
            document.add(new Paragraph("Fecha: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())));
            
            // Datos del proceso
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Algoritmo utilizado: " + algoritmo));
            document.add(new Paragraph("Dirección: " + direccion));
            document.add(new Paragraph("Velocidad: " + velocidad));
            document.add(new Paragraph("Tiempo de ejecución: " + formatTiempo(tiempoEjecucion)));
            document.add(new Paragraph("Pasos realizados: " + pasos));
            
            // Tabla de datos originales
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Datos Originales:"));
            document.add(crearTablaDatos(categoriasOriginal, valoresOriginal, ejeX, ejeY));
            
            // Tabla de datos ordenados
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Datos Ordenados:"));
            document.add(crearTablaDatos(categoriasOrdenado, valoresOrdenado, ejeX, ejeY));
            
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static PdfPTable crearTablaDatos(String[] categorias, int[] valores, String ejeX, String ejeY) {
        PdfPTable table = new PdfPTable(2);
        
        PdfPCell cell1 = new PdfPCell(new Phrase(ejeX));
        PdfPCell cell2 = new PdfPCell(new Phrase(ejeY));
        
        table.addCell(cell1);
        table.addCell(cell2);
        
        for (int i = 0; i < categorias.length; i++) {
            table.addCell(categorias[i]);
            table.addCell(String.valueOf(valores[i]));
        }
        
        return table;
    }
    
    private static String formatTiempo(long milisegundos) {
        long minutos = (milisegundos / 1000) / 60;
        long segundos = (milisegundos / 1000) % 60;
        long ms = milisegundos % 1000;
        return String.format("%02d:%02d:%03d", minutos, segundos, ms);
    }
    String userHome = System.getProperty("user.home");
    String fileName = userHome + "/Desktop/Reporte_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".pdf";
}