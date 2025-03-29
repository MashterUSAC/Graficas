package Lografica;

import Vista.VentanaPrincipal;
import Modelo.DatosCSV;
import Controlador.ControladorArchivo;
import Controlador.ControladorOrdenamiento;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Crear instancias
            VentanaPrincipal vista = new VentanaPrincipal();
            DatosCSV modelo = new DatosCSV();
            
            // Crear controladores
            ControladorArchivo controladorArchivo = new ControladorArchivo(vista, modelo);
            ControladorOrdenamiento controladorOrdenamiento = new ControladorOrdenamiento(vista, modelo);
            
            // Configurar vista
            vista.setVisible(true);
        });
    }
}