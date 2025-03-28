import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelConfiguracion extends JPanel {
    private JTextField txtRutaArchivo;
    private JTextField txtTituloGrafica;
    private JButton btnBuscar;
    private JButton btnAceptar;

    public PanelConfiguracion() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Configuración de componentes
        JLabel lblRutaArchivo = new JLabel("Ruta del archivo:");
        txtRutaArchivo = new JTextField(20);
        btnBuscar = new JButton("Buscar");
        
        JLabel lblTituloGrafica = new JLabel("Título de la gráfica:");
        txtTituloGrafica = new JTextField(20);
        btnAceptar = new JButton("Aceptar");

        // Posicionamiento
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblRutaArchivo, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtRutaArchivo, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 0;
        add(btnBuscar, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblTituloGrafica, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtTituloGrafica, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnAceptar, gbc);
    }

    // Métodos para acceder a los componentes
    public String getRutaArchivo() {
        return txtRutaArchivo.getText();
    }

    public void setRutaArchivo(String ruta) {
        txtRutaArchivo.setText(ruta);
    }

    public String getTituloGrafica() {
        return txtTituloGrafica.getText();
    }

    // Métodos para agregar listeners
    public void addBuscarListener(ActionListener listener) {
        btnBuscar.addActionListener(listener);
    }

    public void addAceptarListener(ActionListener listener) {
        btnAceptar.addActionListener(listener);
    }
}