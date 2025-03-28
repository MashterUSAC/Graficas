import javax.swing.*;
import java.io.*;

public class ControladorArchivo {
    private String[][] datos; // Para almacenar datos del CSV
    
    public void cargarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(null);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try {
                // Leer el archivo CSV
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String linea;
                int filas = contarLineas(archivo);
                
                datos = new String[filas][2]; // 2 columnas: categoría y conteo
                int i = 0;
                
                while ((linea = br.readLine()) != null) {
                    String[] valores = linea.split(",");
                    datos[i][0] = valores[0]; // Categoría
                    datos[i][1] = valores[1]; // Conteo
                    i++;
                }
                br.close();
                
                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(null, "Archivo cargado correctamente");
                
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al leer el archivo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private int contarLineas(File archivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        int lineas = 0;
        while (br.readLine() != null) lineas++;
        br.close();
        return lineas;
    }
    
    public String[][] getDatos() {
        return datos;
    }
}