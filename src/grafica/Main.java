package grafica;

import Modelo.DatosCSV;
import Vista.VentanaPrincipal;
import Controlador.ControladorPrincipal;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal vista = new VentanaPrincipal();
            DatosCSV modelo = new DatosCSV();
            new ControladorPrincipal(vista, modelo);
            vista.setVisible(true);
        });
    }
}