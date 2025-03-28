import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    private JTextField txtRutaArchivo;
    private JTextField txtTituloGrafica;
    private JButton btnBuscar;
    private JButton btnAceptar;
    private JPanel panelGrafica;
    private JComboBox<String> comboAlgoritmos;
    private JComboBox<String> comboTipoOrden;
    private JComboBox<String> comboVelocidad;
    private JButton btnOrdenar;
    private JLabel lblTiempo;
    private JLabel lblPasos;

    public VentanaPrincipal() {
        setTitle("USAC Processing Data");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Panel superior
        JPanel panelSuperior = new JPanel(new GridLayout(2, 3));
        txtRutaArchivo = new JTextField();
        btnBuscar = new JButton("Buscar");
        txtTituloGrafica = new JTextField();
        btnAceptar = new JButton("Aceptar");
        
        panelSuperior.add(new JLabel("Ruta del archivo:"));
        panelSuperior.add(txtRutaArchivo);
        panelSuperior.add(btnBuscar);
        panelSuperior.add(new JLabel("Título de la gráfica:"));
        panelSuperior.add(txtTituloGrafica);
        panelSuperior.add(btnAceptar);
        
        add(panelSuperior, BorderLayout.NORTH);
        
        // Panel central (gráfica)
        panelGrafica = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Aquí se dibujará la gráfica
            }
        };
        add(panelGrafica, BorderLayout.CENTER);
        
        // Panel inferior (controles de ordenamiento)
        JPanel panelInferior = new JPanel(new GridLayout(1, 5));
        
        comboAlgoritmos = new JComboBox<>(new String[]{"Bubble Sort", "Insert Sort", "Select Sort", "Merge Sort", "QuickSort", "ShellSort"});
        comboTipoOrden = new JComboBox<>(new String[]{"Ascendente", "Descendente"});
        comboVelocidad = new JComboBox<>(new String[]{"Baja", "Media", "Alta"});
        btnOrdenar = new JButton("Ordenar");
        lblTiempo = new JLabel("Tiempo: 00:00:000");
        lblPasos = new JLabel("Pasos: 0");
        
        panelInferior.add(new JLabel("Algoritmo:"));
        panelInferior.add(comboAlgoritmos);
        panelInferior.add(new JLabel("Tipo:"));
        panelInferior.add(comboTipoOrden);
        panelInferior.add(new JLabel("Velocidad:"));
        panelInferior.add(comboVelocidad);
        panelInferior.add(btnOrdenar);
        panelInferior.add(lblTiempo);
        panelInferior.add(lblPasos);
        
        add(panelInferior, BorderLayout.SOUTH);
    }

    // Métodos para los listeners
    public void addBuscarListener(ActionListener listener) {
        btnBuscar.addActionListener(listener);
    }
    
    public void addAceptarListener(ActionListener listener) {
        btnAceptar.addActionListener(listener);
    }
    
    public void addOrdenarListener(ActionListener listener) {
        btnOrdenar.addActionListener(listener);
    }
    
    // Getters para los valores de los controles
    public String getRutaArchivo() { return txtRutaArchivo.getText(); }
    public JTextField getRutaArchivoTextField() { return txtRutaArchivo; }
    public String getTituloGrafica() { return txtTituloGrafica.getText(); }
    public String getAlgoritmoSeleccionado() { return (String)comboAlgoritmos.getSelectedItem(); }
    public String getTipoOrdenSeleccionado() { return (String)comboTipoOrden.getSelectedItem(); }
    public String getVelocidadSeleccionada() { return (String)comboVelocidad.getSelectedItem(); }
    
    // Métodos para actualizar la vista
    public void actualizarGrafica(String[] categorias, int[] valores, String ejeX, String ejeY) {
        // Implementar la actualización de la gráfica
        panelGrafica.repaint();
    }
    
    public void actualizarTiempo(String tiempo) {
        lblTiempo.setText("Tiempo: " + tiempo);
    }
    
    public void actualizarPasos(int pasos) {
        lblPasos.setText("Pasos: " + pasos);
    }
    
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    
    public String mostrarSelectorArchivos() {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getPath();
        }
        return null;
    }
}