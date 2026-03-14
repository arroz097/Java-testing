package com.arroz097.models;

import com.arroz097.utils.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageCreator {

    private static final Utility utility = new Utility();

    private final BufferedImage originalImage;
    private final JFrame frame;
    private final JLabel label;

    public ImageCreator(String path, int width, int height) {

        this.originalImage = utility.loadImage(path);
        this.frame = new JFrame("sprite");
        this.frame.setSize(width, height);
        //this.frame.setLayout(null);
        this.frame.setLocationRelativeTo(null); // centraliza o frame
        this.frame.setAlwaysOnTop(true); // deixa o frame sempre acima de outros
        this.frame.setUndecorated(true); // remove as bordas do frame
        this.frame.setBackground(new java.awt.Color(0, 0, 0, 0)); // "a" é o alpha, a opacidade, 0 é invisivel

        Image resizedImage = utility.resizeImage(this.originalImage, width, height);
        ImageIcon image = new ImageIcon(resizedImage);

        this.label = new JLabel(image);
        this.label.setSize(image.getIconWidth(), image.getIconHeight());

        this.frame.add(this.label);
        this.frame.setVisible(true);

    }

    public void updateImageLabel(int width, int height) {

        Image resizedImage = utility.resizeImage(this.originalImage, width, height);
        ImageIcon image = new ImageIcon(resizedImage);

        this.label.setIcon(image);
        //utility.setSizeCentered(this.frame, width, height);

        this.label.repaint();
        this.frame.revalidate();
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public JLabel getLabel() {
        return this.label;
    }

}