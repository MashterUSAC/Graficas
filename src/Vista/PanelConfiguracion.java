package Vista;

import javax.swing.*;
import java.awt.*;

public class PanelConfiguracion extends JPanel {
    private JComboBox<String> comboAlgoritmos;
    private JComboBox<String> comboDireccion;
    private JComboBox<String> comboVelocidad;

    public PanelConfiguracion() {
        setLayout(new GridLayout(3, 2, 5, 5));
        setBorder(BorderFactory.createTitledBorder("Configuración de Ordenamiento"));
        
        // Configuración de algoritmos
        String[] algoritmos = {"Bubble Sort", "Quick Sort", "Insertion Sort", "Selection Sort"};
        comboAlgoritmos = new JComboBox<>(algoritmos);
        add(new JLabel("Algoritmo:"));
        add(comboAlgoritmos);
        
        // Configuración de dirección
        String[] direcciones = {"Ascendente", "Descendente"};
        comboDireccion = new JComboBox<>(direcciones);
        add(new JLabel("Dirección:"));
        add(comboDireccion);
        
        // Configuración de velocidad
        String[] velocidades = {"Lenta", "Media", "Rápida"};
        comboVelocidad = new JComboBox<>(velocidades);
        add(new JLabel("Velocidad:"));
        add(comboVelocidad);
    }

    public String getAlgoritmoSeleccionado() {
        return (String) comboAlgoritmos.getSelectedItem();
    }
    
    public String getDireccionOrdenamiento() {
        return (String) comboDireccion.getSelectedItem();
    }
    
    public String getVelocidad() {
        return (String) comboVelocidad.getSelectedItem();
    }
    
    public int getVelocidadMs() {
        switch(getVelocidad()) {
            case "Rápida": return 100;
            case "Media": return 300;
            case "Lenta": return 500;
            default: return 300;
        }
    }
}