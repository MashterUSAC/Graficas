package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelOrdenamiento extends JPanel {
    private JComboBox<String> cbAlgoritmos;
    private JComboBox<String> cbVelocidad;
    private JRadioButton rbAscendente;
    private JRadioButton rbDescendente;
    private JButton btnOrdenar;
    
    public PanelOrdenamiento() {
        setLayout(new FlowLayout());
        
        // Configuración de algoritmos
        cbAlgoritmos = new JComboBox<>(new String[]{"Bubble Sort", "Quick Sort"});
        add(new JLabel("Algoritmo:"));
        add(cbAlgoritmos);
        
        // Configuración de velocidad
        cbVelocidad = new JComboBox<>(new String[]{"Lenta", "Media", "Rápida"});
        add(new JLabel("Velocidad:"));
        add(cbVelocidad);
        
        // Configuración de orden
        rbAscendente = new JRadioButton("Ascendente", true);
        rbDescendente = new JRadioButton("Descendente");
        ButtonGroup bgOrden = new ButtonGroup();
        bgOrden.add(rbAscendente);
        bgOrden.add(rbDescendente);
        add(new JLabel("Orden:"));
        add(rbAscendente);
        add(rbDescendente);
        
        // Botón de ordenar
        btnOrdenar = new JButton("Ordenar");
        btnOrdenar.setEnabled(false);
        add(btnOrdenar);
    }
    
    public void setOrdenarListener(ActionListener listener) {
        btnOrdenar.addActionListener(listener);
    }
    
    public void habilitarOrdenar(boolean habilitar) {
        btnOrdenar.setEnabled(habilitar);
    }
    
    public void habilitarControles(boolean habilitar) {
        cbAlgoritmos.setEnabled(habilitar);
        cbVelocidad.setEnabled(habilitar);
        rbAscendente.setEnabled(habilitar);
        rbDescendente.setEnabled(habilitar);
    }
    
    public String getAlgoritmoSeleccionado() {
        return (String) cbAlgoritmos.getSelectedItem();
    }
    
    public boolean isAscendente() {
        return rbAscendente.isSelected();
    }
    
    public int getVelocidadOrdenamiento() {
        switch((String)cbVelocidad.getSelectedItem()) {
            case "Rápida": return 50;
            case "Media": return 200;
            case "Lenta": return 500;
            default: return 200;
        }
    }
}