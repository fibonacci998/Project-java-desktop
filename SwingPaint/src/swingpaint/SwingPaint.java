/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingpaint;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Field;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author tuan phan anh
 */
public class SwingPaint extends Canvas{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] a) {
        JFrame f=new JFrame("Draw shape and text on Canvas");
        final Canvas canvas=new SwingPaint();
        f.add(canvas);
        f.setSize(400,400);
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event){
                //saveCanvas(canvas);
                System.exit(0);
            }
        });
    }
    public void paint(Graphics g){
        Graphics2D g2=(Graphics2D) g;
        Color color1;
        try {
            Field field = Class.forName("java.awt.Color").getField("white");
            color1 = (Color)field.get(null);
        } catch (Exception e) {
            color1 = null; // Not defined
        }
        g2.setBackground(color1);
        g2.clearRect(0, 0, this.getWidth(), this.getHeight());
        g2.setColor(Color.red);
        String text="Draw a rectangleeeeeeeeeeeeeeeeeeeeeeee";
        FontMetrics fm=g2.getFontMetrics();
        int w=fm.stringWidth(text);
        int h=fm.getAscent();
        g2.drawOval(160, 160, w*2, h*4);     
        g2.drawString(text, 160+(w/2), 160+(h*2));
        
        
    }
    public static void saveCanvas(Canvas canvas){
        BufferedImage image=new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2=(Graphics2D) image.getGraphics();
        canvas.paint(g2);
        try {
            ImageIO.write(image, "png", new File("d:\\canvas.png"));
        } catch (Exception e) {
        }
    }
}
