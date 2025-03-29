package Controlador;

import Modelo.DatosCSV;
import Vista.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPrincipal {
    private VentanaPrincipal vista;
    private DatosCSV modelo;

    public ControladorPrincipal(VentanaPrincipal vista) {
        this.vista = vista;
        this.modelo = new DatosCSV();
        
        // Configurar controladores específicos
        new ControladorArchivo(vista, modelo);
        new ControladorOrdenamiento(vista, modelo);
    }
}