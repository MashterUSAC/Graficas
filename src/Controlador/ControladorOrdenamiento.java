package Controlador;

import Modelo.AlgoritmoOrdenamiento;
import Vista.VentanaPrincipal;
import Modelo.DatosCSV;
import Ordenar.*;
import Vista.PanelOrdenamiento;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorOrdenamiento implements ActionListener {
    private VentanaPrincipal vista;
    private DatosCSV modelo;

    public ControladorOrdenamiento(VentanaPrincipal vista, DatosCSV modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setControladorOrdenamiento(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (modelo.getValores() == null) {
            JOptionPane.showMessageDialog(
                vista, 
                "Primero cargue un archivo", 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Crear diálogo de configuración
        PanelOrdenamiento panelConfig = new PanelOrdenamiento(vista);
        int opcion = JOptionPane.showConfirmDialog(
            vista, 
            panelConfig, 
            "Configuración de Ordenamiento", 
            JOptionPane.OK_CANCEL_OPTION
        );

        if (opcion == JOptionPane.OK_OPTION) {
            ordenarDatos(
                panelConfig.getAlgoritmoSeleccionado(),
                panelConfig.getDireccionOrdenamiento(),
                panelConfig.getVelocidad()
            );
        }
    }

    private void ordenarDatos(String algoritmo, String direccion, int velocidad) {
        new Thread(() -> {
            try {
                AlgoritmoOrdenamiento ordenador = crearAlgoritmo(algoritmo);
                if (ordenador != null) {
                    int[] valores = modelo.getValores().clone();
                    String[] categorias = modelo.getCategorias().clone();
                    
                    ordenador.ordenar(valores, categorias, direccion.equals("Ascendente"), velocidad);
                    
                    modelo.setValores(valores);
                    modelo.setCategorias(categorias);
                    
                    SwingUtilities.invokeLater(() -> {
                        vista.actualizarGrafica(
                            modelo.getCategorias(), 
                            modelo.getValores(),
                            modelo.getEjeX(),
                            modelo.getEjeY()
                        );
                    });
                }
            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(
                        vista, 
                        "Error durante el ordenamiento: " + ex.getMessage(), 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE
                    );
                });
            }
        }).start();
    }

    private AlgoritmoOrdenamiento crearAlgoritmo(String nombre) {
        switch(nombre) {
            case "Bubble Sort": return new BubbleSort();
            //case "Quick Sort": return new QuickSort();
            default: return null;
        }
    }
    
    
}