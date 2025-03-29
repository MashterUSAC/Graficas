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
        cargarArchivo();
    }
    
    private void cargarArchivo() {
        try {
            String rutaArchivo = vista.mostrarFileChooser();
            if (rutaArchivo != null) {
                modelo.cargarDatos(rutaArchivo);
                
                vista.actualizarGrafica(
                    modelo.getCategorias(),
                    modelo.getValores(),
                    modelo.getEjeX(),
                    modelo.getEjeY(),
                    vista.getTituloGrafica() // Usa el título de la vista o uno personalizado
                );
                
                vista.habilitarBotonOrdenar(true);
                vista.mostrarRutaArchivo(rutaArchivo);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(vista, 
                "Error al cargar archivo: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}