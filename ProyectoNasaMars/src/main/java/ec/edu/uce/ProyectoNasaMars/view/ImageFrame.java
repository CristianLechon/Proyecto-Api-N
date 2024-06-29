package ec.edu.uce.ProyectoNasaMars.view;

import javax.swing.*;
import java.awt.*;

public class ImageFrame extends JFrame {

    private ImagePanel imagePanel;

    public ImageFrame(String imageUrl) {
        setTitle("Image Viewer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        imagePanel = new ImagePanel();
        imagePanel.loadImageFromURL(imageUrl); // Cargar la imagen en ImagePanel

        add(imagePanel, BorderLayout.CENTER);

        setVisible(true);
    }
}

