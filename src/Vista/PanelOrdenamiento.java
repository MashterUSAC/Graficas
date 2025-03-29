package Vista;

import javax.swing.*;
import java.awt.*;

public class PanelOrdenamiento extends JPanel {
    private JComboBox<String> comboAlgoritmos;
    private JComboBox<String> comboDireccion;
    private JSlider sliderVelocidad;

    public PanelOrdenamiento(JFrame parent) {
        setLayout(new GridLayout(3, 2, 5, 5));
        
        // Algoritmos
        add(new JLabel("Algoritmo:"));
        comboAlgoritmos = new JComboBox<>(new String[]{"Bubble Sort", "Quick Sort"});
        add(comboAlgoritmos);
        
        // Dirección
        add(new JLabel("Dirección:"));
        comboDireccion = new JComboBox<>(new String[]{"Ascendente", "Descendente"});
        add(comboDireccion);
        
        // Velocidad
        add(new JLabel("Velocidad:"));
        sliderVelocidad = new JSlider(JSlider.HORIZONTAL, 0, 1000, 500);
        add(sliderVelocidad);
    }

    public String getAlgoritmoSeleccionado() {
        return (String) comboAlgoritmos.getSelectedItem();
    }

    public String getDireccionOrdenamiento() {
        return (String) comboDireccion.getSelectedItem();
    }

    public int getVelocidad() {
        return 1000 - sliderVelocidad.getValue(); // Invertir para que mayor valor = más lento
    }
}