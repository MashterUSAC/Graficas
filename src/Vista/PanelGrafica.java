package vista;

import javax.swing.*;
import java.awt.*;

public class PanelGrafica extends JPanel {
    private String[] categorias;
    private int[] valores;
    private String titulo;
    private String ejeX;
    private String ejeY;
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (valores == null || categorias == null) return;
        
        Graphics2D g2d = (Graphics2D) g;
        int ancho = getWidth();
        int alto = getHeight();
        int margen = 50;
        
        // Dibujar ejes
        g2d.drawLine(margen, alto - margen, ancho - margen, alto - margen); // Eje X
        g2d.drawLine(margen, margen, margen, alto - margen); // Eje Y
        
        // Dibujar título y etiquetas
        if (titulo != null) {
            g2d.drawString(titulo, ancho/2 - titulo.length()*3, margen/2);
        }
        if (ejeX != null) {
            g2d.drawString(ejeX, ancho/2 - ejeX.length()*3, alto - margen/2);
        }
        if (ejeY != null) {
            g2d.rotate(-Math.PI/2);
            g2d.drawString(ejeY, -alto/2 - ejeY.length()*3, margen/2);
            g2d.rotate(Math.PI/2);
        }
        
        // Dibujar gráfica de barras
        int espacioBarras = (ancho - 2*margen) / valores.length;
        int maxValor = obtenerMaximo(valores);
        
        for (int i = 0; i < valores.length; i++) {
            int alturaBarra = (int)((alto - 2*margen) * ((double)valores[i] / maxValor));
            int x = margen + i * espacioBarras + espacioBarras/4;
            int y = alto - margen - alturaBarra;
            
            g2d.setColor(new Color(70, 130, 180));
            g2d.fillRect(x, y, espacioBarras/2, alturaBarra);
            
            // Etiquetas
            g2d.setColor(Color.BLACK);
            g2d.drawString(categorias[i], x, alto - margen + 15);
            g2d.drawString(String.valueOf(valores[i]), x, y - 5);
        }
    }
    
    private int obtenerMaximo(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int num : array) {
            if (num > max) max = num;
        }
        return max;
    }
    
    // Métodos para actualizar datos
    public void actualizarDatos(String[] categorias, int[] valores) {
        this.categorias = categorias;
        this.valores = valores;
        repaint();
    }
    
    public void setTitulos(String titulo, String ejeX, String ejeY) {
        this.titulo = titulo;
        this.ejeX = ejeX;
        this.ejeY = ejeY;
        repaint();
    }
}