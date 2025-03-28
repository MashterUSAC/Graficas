import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelOrdenamiento extends JPanel {
    private JComboBox<String> cbAlgoritmo;
    private JComboBox<String> cbVelocidad;
    private JComboBox<String> cbTipoOrden;
    private JButton btnOrdenar;
    private JLabel lblTiempo;
    private JLabel lblPasos;

    public PanelOrdenamiento() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(5, 2, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Opciones de Ordenamiento"));

        // Configuración de componentes
        add(new JLabel("Algoritmo:"));
        cbAlgoritmo = new JComboBox<>(new String[]{"Bubble Sort", "Insert Sort", "Select Sort", "Merge Sort", "QuickSort", "ShellSort"});
        add(cbAlgoritmo);

        add(new JLabel("Velocidad:"));
        cbVelocidad = new JComboBox<>(new String[]{"Baja", "Media", "Alta"});
        add(cbVelocidad);

        add(new JLabel("Tipo de orden:"));
        cbTipoOrden = new JComboBox<>(new String[]{"Ascendente", "Descendente"});
        add(cbTipoOrden);

        btnOrdenar = new JButton("Iniciar Ordenamiento");
        add(btnOrdenar);

        add(new JLabel("Tiempo:"));
        lblTiempo = new JLabel("00:00:000");
        add(lblTiempo);

        add(new JLabel("Pasos:"));
        lblPasos = new JLabel("0");
        add(lblPasos);
    }

    // Métodos para acceder a los valores seleccionados
    public String getAlgoritmoSeleccionado() {
        return (String) cbAlgoritmo.getSelectedItem();
    }

    public String getVelocidadSeleccionada() {
        return (String) cbVelocidad.getSelectedItem();
    }

    public String getTipoOrdenSeleccionado() {
        return (String) cbTipoOrden.getSelectedItem();
    }

    // Métodos para actualizar información
    public void setTiempo(String tiempo) {
        lblTiempo.setText(tiempo);
    }

    public void setPasos(int pasos) {
        lblPasos.setText(String.valueOf(pasos));
    }

    // Método para agregar listener al botón
    public void addOrdenarListener(ActionListener listener) {
        btnOrdenar.addActionListener(listener);
    }
}