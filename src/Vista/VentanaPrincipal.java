package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    private JButton btnCargarArchivo;
    private JButton btnOrdenar;
    private JTextField txtRutaArchivo;
    private PanelGrafica panelGrafica; // Cambiado de Vista.PanelGrafica a PanelGrafica
    private JTextField txtTituloGrafica;

    public VentanaPrincipal() {
        setTitle("USAC Processing Data");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior
        JPanel panelSuperior = new JPanel();
        txtRutaArchivo = new JTextField(30);
        txtRutaArchivo.setEditable(false);
        btnCargarArchivo = new JButton("Cargar Archivo");
        
        // Agregar campo para título de gráfica
        txtTituloGrafica = new JTextField(20);
        txtTituloGrafica.setText("Título de Gráfica");
        panelSuperior.add(new JLabel("Archivo:"));
        panelSuperior.add(txtRutaArchivo);
        panelSuperior.add(btnCargarArchivo);
        panelSuperior.add(new JLabel("Título:"));
        panelSuperior.add(txtTituloGrafica);
        add(panelSuperior, BorderLayout.NORTH);

        // Panel central
        panelGrafica = new PanelGrafica();
        add(panelGrafica, BorderLayout.CENTER);

        // Panel inferior
        JPanel panelInferior = new JPanel();
        btnOrdenar = new JButton("Ordenar");
        btnOrdenar.setEnabled(false);
        panelInferior.add(btnOrdenar);
        add(panelInferior, BorderLayout.SOUTH);
    }

    // Métodos para controladores
    public void setControladorArchivo(ActionListener listener) {
        btnCargarArchivo.addActionListener(listener);
    }
    
    public JButton getBtnOrdenar() {
        return btnOrdenar;
    }
    
    public PanelGrafica getPanelGrafica() {
    return panelGrafica;
    }
    
    public String getTituloGrafica() {
    return txtTituloGrafica.getText();
    }
    
    public void setTituloGrafica(String titulo) {
    txtTituloGrafica.setText(titulo);
    panelGrafica.setTituloGrafica(titulo);
    }

    public void setControladorOrdenamiento(ActionListener listener) {
        btnOrdenar.addActionListener(listener);
    }

    public void mostrarRutaArchivo(String ruta) {
        txtRutaArchivo.setText(ruta);
    }

    public void habilitarBotonOrdenar(boolean habilitar) {
        btnOrdenar.setEnabled(habilitar);
    }

    public void actualizarGrafica(String[] categorias, int[] valores, String ejeX, String ejeY) {
        panelGrafica.setDatos(categorias, valores);
        panelGrafica.setEjes(ejeX, ejeY);
        panelGrafica.repaint();
    }

    public String mostrarFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }
    
}