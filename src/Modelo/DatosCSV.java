package Modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DatosCSV {
    private String[] categorias;
    private int[] valores;
    private String ejeX;
    private String ejeY;

    public void cargarDatos(String rutaArchivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
        String[] encabezados = br.readLine().split(",");
        this.ejeX = encabezados[0];
        this.ejeY = encabezados[1];
        
        java.util.List<String> tempCat = new java.util.ArrayList<>();
        java.util.List<Integer> tempVal = new java.util.ArrayList<>();
        String linea;
        
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(",");
            if (partes.length >= 2) {
                tempCat.add(partes[0]);
                tempVal.add(Integer.parseInt(partes[1]));
            }
        }
        
        this.categorias = tempCat.toArray(new String[0]);
        this.valores = tempVal.stream().mapToInt(i -> i).toArray();
        br.close();
    }

    // Getters y Setters
    public String[] getCategorias() { return categorias; }
    public int[] getValores() { return valores; }
    public String getEjeX() { return ejeX; }
    public String getEjeY() { return ejeY; }
    public void setCategorias(String[] categorias) { this.categorias = categorias; }
    public void setValores(int[] valores) { this.valores = valores; }
}