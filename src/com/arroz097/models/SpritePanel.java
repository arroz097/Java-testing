package com.arroz097.models;

import com.arroz097.utils.Utility;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

// extends faz a classe herdar metodos de outra classe aparentemente
public class SpritePanel extends JPanel {

    private static final Utility utility = new Utility();

    private BufferedImage image;
    private int drawWidth;
    private int drawHeight;
    private final int textPadding;
    private String spriteName = "";

    public SpritePanel(BufferedImage image, int width, int height, int textPadding) {
        this.image = image;
        this.drawWidth = width;
        this.drawHeight = height;
        this.textPadding = textPadding;

        setOpaque(false);
        setPreferredSize(new Dimension(width, height + textPadding));
        setSize(width, height);
    }

    public void setImageSize(int width, int height) {
        this.drawWidth = width;
        this.drawHeight = height;

        setPreferredSize(new Dimension(width, height));
        setSize(width, height);

        revalidate();
        repaint();
    }

    public void setSpriteImage(String path) {
        this.image = utility.loadImage(path);
        repaint();
    }

    public void setSpriteName(String name) {
        this.spriteName = name;
        repaint();
    }

    public int getDrawWidth() {
        return this.drawWidth;
    }

    public int getDrawHeight() {
        return this.drawHeight;
    }

    // registrar o que é super, e protected
    // protected significa que apenas a propria classe, subclasse e classes do mesmo pacote podem acessar
    @Override
    protected void paintComponent(Graphics g) {
        // super significa referencia para classe pai
        super.paintComponent(g);

        int x = (getWidth() - drawWidth) / 2;
        int y = (getHeight() - drawHeight) / 2 + textPadding / 2;

        g.drawImage(image, x, y, drawWidth, drawHeight, null);

        FontMetrics metrics = g.getFontMetrics();
        int textWidth = metrics.stringWidth(this.spriteName);

        int textX = (getWidth() - textWidth) / 2;
        int textY = (textPadding / 2) + (metrics.getAscent() / 2);

        Graphics2D g2d = (Graphics2D) g;

        // salva o composite original
        Composite original = g2d.getComposite();

        // seta transparência (0.0 = invisível, 1.0 = opaco)
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        g2d.setFont(new Font("Arial", Font.ITALIC, 18));
        g2d.setColor(Color.WHITE);
        g2d.drawString(this.spriteName, textX, textY);

        // restaura o composite original pra nao afetar o resto
        g2d.setComposite(original);


    }
}