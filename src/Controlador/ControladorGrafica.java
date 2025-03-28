import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ControladorGrafica {
    private Vector<String> categorias;
    private Vector<Integer> valores;
    private String ejeX;
    private String ejeY;

    public ControladorGrafica() {
        categorias = new Vector<>();
        valores = new Vector<>();
    }

    public boolean cargarDatosDesdeCSV(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            // Leer encabezados
            String linea = br.readLine();
            if (linea != null) {
                String[] encabezados = linea.split(",");
                if (encabezados.length >= 2) {
                    ejeX = encabezados[0];
                    ejeY = encabezados[1];
                }
            }

            // Leer datos
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 2) {
                    categorias.add(datos[0]);
                    try {
                        valores.add(Integer.parseInt(datos[1]));
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Error en formato de datos numéricos", "Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
            }
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public String seleccionarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar archivo CSV");
        
        int resultado = fileChooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }

    // Getters para los datos
    public Vector<String> getCategorias() {
        return categorias;
    }

    public Vector<Integer> getValores() {
        return valores;
    }

    public String getEjeX() {
        return ejeX;
    }

    public String getEjeY() {
        return ejeY;
    }

    public void limpiarDatos() {
        categorias.clear();
        valores.clear();
        ejeX = null;
        ejeY = null;
    }
}