package com.arroz097.models;

import com.arroz097.utils.Utility;
import com.arroz097.models.SpritePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageCreator {

    private static final Utility utility = new Utility();

    private final BufferedImage originalImage;
    private final JFrame frame;
    //private final JLabel label;
    private final SpritePanel spritePanel;

    public ImageCreator(String path, int width, int height) {

        this.originalImage = utility.loadImage(path);

        this.frame = new JFrame("sprite");
        this.frame.setSize(width, height);
        //this.frame.setLayout(null);

        this.frame.setLocationRelativeTo(null); // centraliza o frame
        this.frame.setAlwaysOnTop(true); // deixa o frame sempre acima de outros
        this.frame.setUndecorated(true); // remove as bordas do frame
        this.frame.setBackground(new Color(0, 0, 0, 0)); // "a" é o alpha, a opacidade, 0 é invisivel

        int textPadding = 30;

        this.spritePanel = new SpritePanel(this.originalImage, width, height, textPadding);

        this.frame.add(this.spritePanel, BorderLayout.CENTER);
        this.frame.setVisible(true);

        /*
        Image resizedImage = utility.resizeImage(this.originalImage, width, height);
        ImageIcon image = new ImageIcon(resizedImage);

        this.label = new JLabel(image);
        this.label.setSize(image.getIconWidth(), image.getIconHeight());

        this.frame.add(this.label);
        this.frame.setVisible(true);

         */

    }

    public void updateSpriteSize(int width, int height) {
        this.spritePanel.setImageSize(width, height);
        //this.spritePanel.setPreferredSize(new Dimension(width, height));

        //this.frame.pack();
        this.frame.repaint();
    }

    public int getSpriteWidth() {
        return this.spritePanel.getDrawWidth();
    }

    public int getSpriteHeight() {
        return this.spritePanel.getDrawHeight();
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public SpritePanel getSpritePanel() {
        return this.spritePanel;
    }

}