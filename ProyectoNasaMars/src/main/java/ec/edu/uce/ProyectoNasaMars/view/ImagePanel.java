package ec.edu.uce.ProyectoNasaMars.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImagePanel extends JPanel {

    private BufferedImage image;

    public ImagePanel() {

    }

    public void loadImageFromURL(String urlImagen) {
        try {
            URL url = new URL(urlImagen);
            image = ImageIO.read(url);
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
        } else {
            g.drawString("No se pudo cargar la imagen", 10, 20);
        }
    }

}