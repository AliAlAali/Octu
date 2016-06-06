/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package octu.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Ali
 */
public class ImageView extends JPanel{
    
    public static final String EXTENSION_JPG = "jpg";
    public static final String EXTENSION_PNG = "png";
    public static final String EXTENSION_GIF = "gif";
    
    private BufferedImage image;
    
    public ImageView(){
        try {
            image = ImageIO.read(new File(getClass().getClassLoader().getResource("resources/ImprovedMouse2.png").getFile()));
        } catch (IOException ex) {
           ex.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0,185,250, null);
        
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();
    }
    
    public void setImagePath(String path) throws IOException{
        image = ImageIO.read(new File(path));
    }
    
    public void setImageFromLocal(String path, String extension){
       try {
            image = ImageIO.read(new File(getClass().getClassLoader().getResource("resources/" + path + "." + extension).getFile()));
        } catch (IOException ex) {
           ex.printStackTrace();
        }
    }
    
    
}
