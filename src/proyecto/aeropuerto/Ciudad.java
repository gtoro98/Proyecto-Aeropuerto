/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.aeropuerto;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author SamuelLMiller & Gtoro98
 */
public class Ciudad {
    
    private String nombre;
    private String aeropuerto;
    private String pais;
//    private Rectangle circulo;
    private int x, y;

    public Ciudad(String name) {
        this.nombre = name;
    }
    
    public Ciudad(String nombre, String aeropuerto, String pais) {
        this.nombre = nombre;
        this.aeropuerto = aeropuerto;
        this.pais = pais;
    }

//    public void setCirculo(Rectangle circulo) {
//        this.circulo = circulo;
//    }
//
//    public Rectangle getCirculo() {
//        return circulo;
//    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public String getAeropuerto() {
        return aeropuerto;
    }

    public String getPais() {
        return pais;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAeropuerto(String aeropuerto) {
        this.aeropuerto = aeropuerto;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    
    
    public void drawCircle(JPanel jPanel, Graphics2D g2, int x, int y, String name){
        int d = 60;
        g2 = (Graphics2D) jPanel.getGraphics();
        g2.setColor(Color.GREEN);
        g2.fillOval(x-d/2, y-d/2, d, d);
        g2.setColor(Color.BLACK);
        g2.drawString(name, x - d/2, y);
//        Rectangle circulo = new Rectangle(x - d/2, y - d/2, d, d);
//        this.setCirculo(circulo);
//        Rectangle circle = new Rectangle(x-d/2, y-d/2, d, d);
//        this.drawCenteredString(g2, name, circle, new Font("Rockwell", Font.BOLD, 12));
        
    }
    
    public void removeCircle(JPanel jPanel, Graphics2D g2, int x, int y, String name){
        int d = 60;
        g2 = (Graphics2D) jPanel.getGraphics();
        g2.clearRect(x - d/2, y - d/2, d, d);
        g2.setColor(jPanel.getBackground());
        g2.fillRect(x - d/2, y - d/2, d, d);
        g2.drawString(name, x -d/2, y);
//        Rectangle circle = new Rectangle(x-d/2, y-d/2, d, d);
//        this.drawCenteredString(g2, name, circle, new Font("Rockwell", Font.BOLD, 12));
        
        
    }
    
    public void drawCircle2(JPanel jPanel, Graphics g){
        int d = 60;
        g.setColor(Color.GREEN);
        g.fillOval(x-d/2, y-d/2, d, d);
        g.setColor(Color.BLACK);
        Rectangle circle = new Rectangle(x-d/2, y-d/2, d, d);
//        this.drawCenteredString(g, this.getNombre(), circle, new Font("Rockwell", Font.BOLD, 18));
        
        
    }
    
    public void drawCenteredString (Graphics2D g, String name, Rectangle rectangle, Font font){
        FontRenderContext frc = new FontRenderContext(null, true, true);
        Rectangle2D r2D = font.getStringBounds(name, frc);
        
        int rWidth = (int) Math.round(r2D.getWidth());
        int rHeight = (int) Math.round(r2D.getHeight());
        int rX = (int) Math.round(r2D.getX());
        int rY = (int) Math.round(r2D.getY());
        int a = (rectangle.width/2) - (rWidth/2) - rX;
        int b = (rectangle.height/2) - (rHeight/2) - rY;
        
        g.setFont(font);
        
        g.drawString(name, rectangle.x + a, rectangle.y + b);
    }
    
}
