package Controlador;

import Vista.VentanaPrincipal;
import Modelo.DatosCSV;

public class ControladorPrincipal {
    private VentanaPrincipal vista;
    private DatosCSV modelo;
    private ControladorArchivo controladorArchivo;
    private ControladorOrdenamiento controladorOrdenamiento;

    public ControladorPrincipal(VentanaPrincipal vista, DatosCSV modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }
    
    public void setControladores(ControladorArchivo controladorArchivo, 
                               ControladorOrdenamiento controladorOrdenamiento) {
        this.controladorArchivo = controladorArchivo;
        this.controladorOrdenamiento = controladorOrdenamiento;
        
        // Configurar vista
        vista.setControladorArchivo(controladorArchivo);
        vista.setControladorOrdenamiento(controladorOrdenamiento);
    }
    
    public void habilitarOrdenamiento(boolean habilitar) {
        vista.habilitarBotonOrdenar(habilitar);
    }
}