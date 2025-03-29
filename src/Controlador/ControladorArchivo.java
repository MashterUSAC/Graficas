package Controlador;

import Vista.VentanaPrincipal;
import Modelo.DatosCSV;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControladorArchivo implements ActionListener {
    private VentanaPrincipal vista;
    private DatosCSV modelo;

    public ControladorArchivo(VentanaPrincipal vista, DatosCSV modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setControladorArchivo(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ruta = vista.mostrarFileChooser();
        if (ruta != null) {
            try {
                modelo.cargarDatos(ruta);
                vista.mostrarRutaArchivo(ruta);
                vista.actualizarGrafica(
                    modelo.getCategorias(), 
                    modelo.getValores(),
                    modelo.getEjeX(),
                    modelo.getEjeY()
                );
                vista.habilitarBotonOrdenar(true);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                    vista, 
                    "Error al leer el archivo: " + ex.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}