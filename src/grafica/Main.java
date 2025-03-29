package grafica;

import Vista.VentanaPrincipal;
import Modelo.DatosCSV;
import Controlador.ControladorArchivo;
import Controlador.ControladorOrdenamiento;
import Controlador.ControladorPrincipal;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                // Crear instancias
                VentanaPrincipal vista = new VentanaPrincipal();
                DatosCSV modelo = new DatosCSV();
                
                // Crear controladores
                ControladorPrincipal controladorPrincipal = new ControladorPrincipal(vista, modelo);
                ControladorArchivo controladorArchivo = new ControladorArchivo(vista, modelo);
                ControladorOrdenamiento controladorOrdenamiento = new ControladorOrdenamiento(vista, modelo);
                
                // Configurar relaciones
                controladorPrincipal.setControladores(controladorArchivo, controladorOrdenamiento);
                
                // Mostrar ventana
                vista.setVisible(true);
            } catch (Exception e) {
                System.err.println("Error al iniciar la aplicación: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}