import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ControladorPrincipal {
    private VentanaPrincipal vista;
    private DatosCSV modelo;
    private Thread hiloOrdenamiento;

    public ControladorPrincipal(VentanaPrincipal vista, DatosCSV modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        // Configurar listeners
        configurarListeners();
        
        // Mostrar la ventana
        vista.setVisible(true);
    }
    
    private void configurarListeners() {
        vista.addBuscarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ruta = vista.mostrarSelectorArchivos();
                if (ruta != null) {
                    vista.getRutaArchivoTextField().setText(ruta);
                }
            }
        });
        
        vista.addAceptarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarArchivo();
            }
        });
        
        vista.addOrdenarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarOrdenamiento();
            }
        });
    }
    
    private void cargarArchivo() {
        String ruta = vista.getRutaArchivo();
        String titulo = vista.getTituloGrafica();
        
        if (ruta.isEmpty()) {
            vista.mostrarMensaje("Debe seleccionar un archivo");
            return;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            // Leer encabezados
            String linea = br.readLine();
            if (linea == null) throw new IOException("Archivo vacío");
            
            String[] encabezados = linea.split(",");
            if (encabezados.length != 2) throw new IOException("Formato de archivo incorrecto");
            
            // Leer datos
            String[] categorias = new String[100]; // Tamaño máximo temporal
            int[] valores = new int[100];
            int contador = 0;
            
            while ((linea = br.readLine()) != null && contador < 100) {
                String[] partes = linea.split(",");
                if (partes.length != 2) continue;
                
                categorias[contador] = partes[0].trim();
                valores[contador] = Integer.parseInt(partes[1].trim());
                contador++;
            }
            
            // Redimensionar arrays al tamaño real
            String[] categoriasFinal = new String[contador];
            int[] valoresFinal = new int[contador];
            System.arraycopy(categorias, 0, categoriasFinal, 0, contador);
            System.arraycopy(valores, 0, valoresFinal, 0, contador);
            
            // Actualizar modelo
            modelo.cargarDatos(categoriasFinal, valoresFinal, encabezados[0], encabezados[1]);
            modelo.setTituloGrafica(titulo);
            
            // Actualizar vista
            vista.actualizarGrafica(categoriasFinal, valoresFinal, encabezados[0], encabezados[1]);
            
        } catch (IOException | NumberFormatException ex) {
            vista.mostrarMensaje("Error al leer el archivo: " + ex.getMessage());
        }
    }
    
    private void iniciarOrdenamiento() {
        if (hiloOrdenamiento != null && hiloOrdenamiento.isAlive()) {
            vista.mostrarMensaje("Ya hay un ordenamiento en proceso");
            return;
        }
        
        String algoritmo = vista.getAlgoritmoSeleccionado();
        boolean ascendente = vista.getTipoOrdenSeleccionado().equals("Ascendente");
        String velocidad = vista.getVelocidadSeleccionada();
        
        // Determinar velocidad (milisegundos entre pasos)
        int delay;
        switch(velocidad) {
            case "Alta": delay = 100; break;
            case "Media": delay = 500; break;
            default: delay = 1000; break;
        }
        
        hiloOrdenamiento = new Thread(() -> {
            long inicio = System.currentTimeMillis();
            int pasos = 0;
            
            // Ordenar en el modelo
            modelo.ordenarDatos(ascendente, algoritmo);
            
            // Actualizar vista con los datos ordenados
            SwingUtilities.invokeLater(() -> {
                vista.actualizarGrafica(modelo.getCategorias(), modelo.getValores(), 
                                     modelo.getEjeX(), modelo.getEjeY());
                
                long fin = System.currentTimeMillis();
                long tiempoTotal = fin - inicio;
                
                // Formatear tiempo
                long minutos = tiempoTotal / 60000;
                long segundos = (tiempoTotal % 60000) / 1000;
                long milisegundos = tiempoTotal % 1000;
                
                String tiempoFormateado = String.format("%02d:%02d:%03d", minutos, segundos, milisegundos);
                
                vista.actualizarTiempo(tiempoFormateado);
                vista.actualizarPasos(pasos);
            });
        });
        
        hiloOrdenamiento.start();
    }
}