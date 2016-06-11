/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octu.graphics;

import java.awt.Dimension;
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
public class ImageView extends JPanel {

    public static final String EXTENSION_JPG = "jpg";
    public static final String EXTENSION_PNG = "png";
    public static final String EXTENSION_GIF = "gif";

    //for horizental only
    public static final String ALN_CENTER = "CENTER";
    public static final String ALN_RIGHT = "RIGHT";
    public static final String ALN_LEFT = "LEFT";

    private BufferedImage image;
    private Dimension dim;
    private String alignment;

    public ImageView() {
        try {
            image = ImageIO.read(new File(getClass().getClassLoader().getResource("resources/ImprovedMouse2.png").getFile()));
            alignment = ALN_LEFT;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setHorizentalAlignmet(String aln){
        this.alignment = aln;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        if (dim == null) {
            g.drawImage(image, 0, 0, 185, 250, null);
        } else {
            int x = 0;
            int y = 0;
            int w = (int) dim.getWidth();
            int h = (int) dim.getHeight();

            switch (alignment) {
                case ALN_LEFT:
                    break;
                case ALN_CENTER:
                    x = ((int)getPreferredSize().getWidth()/2 - (int)dim.getWidth()/2);
                    break;
                case ALN_RIGHT:
                    x = (int)(getPreferredSize().getWidth() - dim.getWidth());
                    break;

            }
            g.drawImage(image, x, y, (int) dim.getWidth(), (int) dim.getHeight(), null);
        }

    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();
    }

    public void setImagePath(String path) throws IOException {
        image = ImageIO.read(new File(path));
    }

    public void setToRealDim() {
        setPreferredSize(dim);
        repaint();
    }

    public void setImageFromLocal(String path, String extension) {
        try {
            image = ImageIO.read(new File(getClass().getClassLoader().getResource("resources/" + path + "." + extension).getFile()));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void calculateDim() {
        dim = new Dimension();
        //boolean wSmall = (image.getWidth() <= image.getHeight());
        double ratio = 1;
        if (getPreferredSize().getWidth() <= getPreferredSize().getHeight()) {
            System.out.println("smaller");
            ratio = (double) getPreferredSize().getWidth() / image.getWidth();
            System.out.println("rat: " + ratio + " " + getPreferredSize().getWidth() + " | " + getPreferredSize().getHeight());
            dim.setSize(getPreferredSize().getWidth(), image.getHeight() * ratio);
        } else {
            ratio = (double) getPreferredSize().getHeight() / image.getHeight();
            dim.setSize(image.getWidth() * ratio, getPreferredSize().getHeight());
        }

    }

}
