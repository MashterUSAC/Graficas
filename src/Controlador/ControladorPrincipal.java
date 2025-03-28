package Controlador;

import Modelo.DatosCSV;
import Vista.VentanaPrincipal;

public class ControladorPrincipal {
    private VentanaPrincipal vista;
    private DatosCSV modelo;
    
    public ControladorPrincipal(VentanaPrincipal vista, DatosCSV modelo) {
        this.vista = vista;
        this.modelo = modelo;
        configurarListeners();
    }
    
    private void configurarListeners() {
        vista.setBuscarListener(e -> ControladorArchivo.seleccionarArchivo(modelo, vista));
        vista.setOrdenarListener(e -> ControladorOrdenamiento.iniciarOrdenamiento(modelo, vista));
    }
}