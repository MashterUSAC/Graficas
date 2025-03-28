package Controlador;

import Modelo.DatosCSV;
import Vista.VentanaPrincipal;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ControladorArchivo {
    public static void seleccionarArchivo(DatosCSV modelo, VentanaPrincipal vista) {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(vista);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            try {
                modelo.cargarDatos(fileChooser.getSelectedFile().getPath());
                vista.mostrarDatos(modelo.getCategorias(), modelo.getValores());
                vista.habilitarOrdenar(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, 
                    "Error al cargar archivo: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}