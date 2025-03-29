// Vista/DialogoUsuario.java
package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoUsuario extends JDialog {
    private JTextField txtNombre;
    private JTextField txtCarnet;
    private boolean confirmed = false;

    public DialogoUsuario(JFrame parent) {
        super(parent, "Datos del Estudiante", true);
        setSize(300, 200);
        setLayout(new GridLayout(3, 2, 5, 5));
        
        txtNombre = new JTextField();
        txtCarnet = new JTextField();
        
        add(new JLabel("Nombre:"));
        add(txtNombre);
        add(new JLabel("Carné:"));
        add(txtCarnet);
        
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(e -> {
            confirmed = true;
            setVisible(false);
        });
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> setVisible(false));
        
        add(btnAceptar);
        add(btnCancelar);
    }
    
    public boolean isConfirmed() {
        return confirmed;
    }
    
    public String getNombre() {
        return txtNombre.getText();
    }
    
    public String getCarnet() {
        return txtCarnet.getText();
    }
}