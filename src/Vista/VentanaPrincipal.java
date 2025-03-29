package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    private JButton btnCargarArchivo;
    private JButton btnOrdenar;
    private JTextField txtRutaArchivo;
    private JTextField txtTituloGrafica;
    private PanelGrafica panelGrafica;
    private PanelConfiguracion panelConfiguracion;

    public VentanaPrincipal() {
        setTitle("USAC Processing Data");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        txtRutaArchivo = new JTextField(30);
        txtRutaArchivo.setEditable(false);
        btnCargarArchivo = new JButton("Cargar Archivo");
        
        txtTituloGrafica = new JTextField(20);
        txtTituloGrafica.setText("Título de Gráfica");
        
        panelSuperior.add(new JLabel("Archivo:"));
        panelSuperior.add(txtRutaArchivo);
        panelSuperior.add(btnCargarArchivo);
        panelSuperior.add(new JLabel("Título:"));
        panelSuperior.add(txtTituloGrafica);
        panelConfiguracion = new PanelConfiguracion();
        add(panelSuperior, BorderLayout.NORTH);

        // Panel central
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelGrafica = new PanelGrafica();
        panelConfiguracion = new PanelConfiguracion();
        
        panelCentral.add(panelGrafica, BorderLayout.CENTER);
        panelCentral.add(panelConfiguracion, BorderLayout.EAST);
        add(panelCentral, BorderLayout.CENTER);

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
    
    public void setControladorOrdenamiento(ActionListener listener) {
        btnOrdenar.addActionListener(listener);
    }

    // Getters
    public JButton getBtnOrdenar() {
        return btnOrdenar;
    }
    
    public PanelGrafica getPanelGrafica() {
        return panelGrafica;
    }
    
    public PanelConfiguracion getPanelConfiguracion() {
        return panelConfiguracion;
    }
    
    public String getTituloGrafica() {
        return txtTituloGrafica.getText();
    }
    
    // Métodos de utilidad
    public void mostrarRutaArchivo(String ruta) {
        txtRutaArchivo.setText(ruta);
    }
    
    public void habilitarBotonOrdenar(boolean habilitar) {
        btnOrdenar.setEnabled(habilitar);
    }
    
    public void actualizarGrafica(String[] categorias, int[] valores, String ejeX, String ejeY, String titulo) {
        panelGrafica.setDatos(categorias, valores);
        panelGrafica.setEjes(ejeX, ejeY);
        panelGrafica.setTituloGrafica(titulo);
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