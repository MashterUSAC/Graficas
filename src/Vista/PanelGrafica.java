package Vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import javax.swing.JPanel;

public class PanelGrafica extends JPanel {
    private String[] categorias;
    private int[] valores;
    private Color[] coloresBarras;
    private String tituloGrafica = "Grafica de Datos";
    private String ejeX;
    private String ejeY;
    // ... otros atributos ...

    public class VentanaPrincipal extends JFrame {
    private PanelGrafica panelGrafica;
    }
    
    public void setDatos(String[] categorias, int[] valores) {
    this.categorias = categorias != null ? categorias.clone() : null;
    this.valores = valores != null ? valores.clone() : null;
    repaint();
    }

 public void setEjes(String ejeX, String ejeY) {
        this.ejeX = ejeX;
        this.ejeY = ejeY;
        repaint();
    }

    public void setTituloGrafica(String titulo) {
        this.tituloGrafica = titulo;
        repaint();
    }
    
    public PanelGrafica() {
        // Colores predefinidos para las barras
        coloresBarras = new Color[] {
            new Color(79, 129, 189),   // Azul
            new Color(155, 187, 89),   // Verde
            new Color(192, 80, 77),    // Rojo
            new Color(128, 100, 162),  // Morado
            new Color(247, 150, 70),   // Naranja
            new Color(75, 172, 198),   // Celeste
            new Color(169, 89, 90)     // Vino
        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (categorias == null || valores == null) {
            g.drawString("No hay datos para mostrar", getWidth()/2 - 50, getHeight()/2);
            return;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Configuración de la gráfica
        int padding = 50;
        int anchoBarra = (getWidth() - 2*padding) / categorias.length - 5;
        int maxValor = Arrays.stream(valores).max().orElse(1);

        // Dibujar ejes con estilo mejorado
        g2d.setColor(new Color(100, 100, 100));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(padding, getHeight() - padding, padding, padding);
        g2d.drawLine(padding, getHeight() - padding, getWidth() - padding, getHeight() - padding);

        // Dibujar título y etiquetas
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.drawString(tituloGrafica, getWidth()/2 - tituloGrafica.length()*3, padding/2);

        // Dibujar barras con colores alternados
        for (int i = 0; i < categorias.length; i++) {
            int alturaBarra = (int) ((double) valores[i] / maxValor * (getHeight() - 2*padding - 20));
            Color colorBarra = coloresBarras[i % coloresBarras.length]; // Ciclo de colores
            
            // Barra con borde y relleno
            g2d.setColor(colorBarra);
            g2d.fillRect(
                padding + i * (anchoBarra + 5), 
                getHeight() - padding - alturaBarra, 
                anchoBarra, 
                alturaBarra
            );
            
            g2d.setColor(colorBarra.darker());
            g2d.drawRect(
                padding + i * (anchoBarra + 5), 
                getHeight() - padding - alturaBarra, 
                anchoBarra, 
                alturaBarra
            );

            // Valor encima de la barra
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.PLAIN, 10));
            String valorStr = String.valueOf(valores[i]);
            g2d.drawString(
                valorStr, 
                padding + i * (anchoBarra + 5) + anchoBarra/2 - valorStr.length()*3, 
                getHeight() - padding - alturaBarra - 5
            );

            // Categoría debajo del eje X
            g2d.setFont(new Font("Arial", Font.PLAIN, 10));
            String categoria = categorias[i].length() > 10 ? 
                categorias[i].substring(0, 7) + "..." : categorias[i];
            g2d.drawString(
                categoria, 
                padding + i * (anchoBarra + 5) + anchoBarra/2 - categoria.length()*3, 
                getHeight() - padding/2
            );
        }
    }

    // Método para guardar la gráfica como imagen (para el reporte PDF)
    public BufferedImage getGraficaComoImagen() {
        BufferedImage imagen = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = imagen.createGraphics();
        this.paint(g2d);
        g2d.dispose();
        return imagen;
    }
}