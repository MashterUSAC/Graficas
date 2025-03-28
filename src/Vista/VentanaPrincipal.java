package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    private PanelGrafica panelGrafica;
    private PanelOrdenamiento panelOrdenamiento;
    private JButton btnBuscar;
    private JTextField txtRuta;
    
    public VentanaPrincipal() {
        setTitle("USAC Processing Data");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Panel superior con controles
        JPanel panelSuperior = new JPanel();
        btnBuscar = new JButton("Buscar Archivo");
        txtRuta = new JTextField(30);
        txtRuta.setEditable(false);
        panelSuperior.add(new JLabel("Archivo CSV:"));
        panelSuperior.add(txtRuta);
        panelSuperior.add(btnBuscar);
        add(panelSuperior, BorderLayout.NORTH);
        
        // Panel central con gráfica
        panelGrafica = new PanelGrafica();
        add(panelGrafica, BorderLayout.CENTER);
        
        // Panel inferior con controles de ordenamiento
        panelOrdenamiento = new PanelOrdenamiento();
        add(panelOrdenamiento, BorderLayout.SOUTH);
    }
    
    public void setBuscarListener(ActionListener listener) {
        btnBuscar.addActionListener(listener);
    }
    
    public void setOrdenarListener(ActionListener listener) {
        panelOrdenamiento.setOrdenarListener(listener);
    }
    
    public void mostrarDatos(String[] categorias, int[] valores) {
        panelGrafica.setDatos(categorias, valores);
    }
    
    public void habilitarOrdenar(boolean habilitar) {
        panelOrdenamiento.habilitarOrdenar(habilitar);
    }
    
    public void habilitarControles(boolean habilitar) {
        btnBuscar.setEnabled(habilitar);
        panelOrdenamiento.habilitarControles(habilitar);
    }
    
    public String getAlgoritmoSeleccionado() {
        return panelOrdenamiento.getAlgoritmoSeleccionado();
    }
    
    public boolean isAscendente() {
        return panelOrdenamiento.isAscendente();
    }
    
    public int getVelocidadOrdenamiento() {
        return panelOrdenamiento.getVelocidadOrdenamiento();
    }
    
    public void setRutaArchivo(String ruta) {
        txtRuta.setText(ruta);
    }
}