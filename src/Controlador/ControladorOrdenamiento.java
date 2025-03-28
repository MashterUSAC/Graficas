package Controlador;

import Modelo.DatosCSV;
import Modelo.Ordenar.BubbleSort;
import Modelo.Ordenar.QuickSort;
import Vista.VentanaPrincipal;
import javax.swing.SwingWorker;

public class ControladorOrdenamiento {
    public static void iniciarOrdenamiento(DatosCSV modelo, VentanaPrincipal vista) {
        String algoritmo = vista.getAlgoritmoSeleccionado();
        boolean ascendente = vista.isAscendente();
        int velocidad = vista.getVelocidadOrdenamiento();
        
        vista.habilitarControles(false);
        
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                int[] valores = modelo.getValores().clone();
                String[] categorias = modelo.getCategorias().clone();
                
                switch(algoritmo) {
                    case "Bubble Sort":
                        new BubbleSort().ordenar(valores, categorias, ascendente, velocidad);
                        break;
                    case "Quick Sort":
                        new QuickSort().ordenar(valores, categorias, ascendente, velocidad);
                        break;
                    // Agregar más algoritmos aquí
                }
                
                modelo.setValores(valores);
                modelo.setCategorias(categorias);
                return null;
            }
            
            @Override
            protected void done() {
                vista.mostrarDatos(modelo.getCategorias(), modelo.getValores());
                vista.habilitarControles(true);
            }
        }.execute();
    }
}