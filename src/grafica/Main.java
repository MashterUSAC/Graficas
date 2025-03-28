
public class Main {
    public static void main(String[] args) {
        // Crear instancias del modelo
        DatosCSV datosModelo = new DatosCSV();
        
        // Crear vista principal
        VentanaPrincipal vistaPrincipal = new VentanaPrincipal();
        
        // Crear controlador principal que coordinará todo
        new ControladorPrincipal(vistaPrincipal, datosModelo);
    }
}