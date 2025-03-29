package Controlador;

import Modelo.AlgoritmoOrdenamiento;
import Vista.VentanaPrincipal;
import Modelo.DatosCSV;
import Vista.DialogoUsuario;
import Ordenar.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ControladorOrdenamiento implements ActionListener {
    private VentanaPrincipal vista;
    private DatosCSV modelo;
    private long tiempoInicio;
    private int pasos;
    private String algoritmoSeleccionado;
    private String direccionOrdenamiento;
    private int velocidadMs;

    public ControladorOrdenamiento(VentanaPrincipal vista, DatosCSV modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnOrdenar()) {
            iniciarProcesoOrdenamiento();
        }
    }

    private void iniciarProcesoOrdenamiento() {
        // 1. Mostrar diálogo para datos del estudiante
        DialogoUsuario dialogoUsuario = new DialogoUsuario(vista);
        dialogoUsuario.setVisible(true);
        
        if (!dialogoUsuario.isConfirmed()) {
            return;
        }
        
        // 2. Obtener parámetros de ordenamiento
        algoritmoSeleccionado = vista.getPanelConfiguracion().getAlgoritmoSeleccionado();
        direccionOrdenamiento = vista.getPanelConfiguracion().getDireccionOrdenamiento();
        velocidadMs = vista.getPanelConfiguracion().getVelocidadMs();
        
        // 3. Clonar datos originales para el reporte
        String[] categoriasOriginal = modelo.getCategorias().clone();
        int[] valoresOriginal = modelo.getValores().clone();
        
        // 4. Ejecutar ordenamiento en hilo separado
        new Thread(() -> {
            tiempoInicio = System.currentTimeMillis();
            pasos = 0;
            
            try {
                AlgoritmoOrdenamiento algoritmo = obtenerAlgoritmo();
                boolean ascendente = direccionOrdenamiento.equals("Ascendente");
                
                algoritmo.ordenar(
                    modelo.getValores(), 
                    modelo.getCategorias(), 
                    ascendente, 
                    velocidadMs,
                    this::actualizarProgreso
                );
                
                // Actualizar vista con datos ordenados
                SwingUtilities.invokeLater(() -> {
                    vista.actualizarGrafica(
                        modelo.getCategorias(),
                        modelo.getValores(),
                        modelo.getEjeX(),
                        modelo.getEjeY(),
                        vista.getTituloGrafica()
                    );
                });
                
                // Generar reporte
                long tiempoEjecucion = System.currentTimeMillis() - tiempoInicio;
                Modelo.GeneradorReports.generarReporte(
                    dialogoUsuario.getNombre(),
                    dialogoUsuario.getCarnet(),
                    algoritmoSeleccionado,
                    direccionOrdenamiento,
                    vista.getPanelConfiguracion().getVelocidad(),
                    tiempoEjecucion,
                    pasos,
                    categoriasOriginal,
                    valoresOriginal,
                    modelo.getCategorias(),
                    modelo.getValores(),
                    modelo.getEjeX(),
                    modelo.getEjeY()
                );
                
                JOptionPane.showMessageDialog(vista, 
                    "Reporte generado exitosamente", 
                    "Proceso completado", 
                    JOptionPane.INFORMATION_MESSAGE);
                
            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(vista, 
                        "Error durante el ordenamiento: " + ex.getMessage(), 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                });
            }
        }).start();
    }
    
    private AlgoritmoOrdenamiento obtenerAlgoritmo() {
        switch(algoritmoSeleccionado) {
            case "Bubble Sort":
                return new Ordenar.BubbleSort();
            case "Quick Sort":
                return new Ordenar.QuickSort(); // Descomentado y corregido
            // Agregar otros algoritmos aquí
            default:
                throw new IllegalArgumentException("Algoritmo no soportado: " + algoritmoSeleccionado);
        }
    }
    
    private void actualizarProgreso(int[] valores, String[] categorias, int pasosActuales) {
        this.pasos = pasosActuales;
        modelo.setValores(valores);
        modelo.setCategorias(categorias);
        
        SwingUtilities.invokeLater(() -> {
            vista.actualizarGrafica(
                categorias,
                valores,
                modelo.getEjeX(),
                modelo.getEjeY(),
                vista.getTituloGrafica()
            );
        });
    }
}