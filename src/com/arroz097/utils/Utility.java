package com.arroz097.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Utility {

    // tudo em java é Object, tudo herda de Object, classes, strings, numeros, etc
    public void write(Object message) {
        System.out.println(message);
    }

    /*
     nao há necessidade de fazer int lerp, double ja consegue dar conta

     public int lerp(int a, int b, int t) {
        return a + (b - a) * t;
    }
     */

    public double lerp(double a, double b, double t) {
        return a + (b - a) * t;
    }

    public void newThread(Runnable thread) {
        new Thread(thread).start();
    }

    public void setSizeCentered(Component gui, int width, int height) {
        int centerX = gui.getX() + gui.getWidth() / 2;
        int centerY = gui.getY() + gui.getHeight() / 2;

        gui.setBounds(centerX - width / 2, centerY - height / 2, width, height);
    }

    public BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar imagem: " + path, e);
        }
    }

    public BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = resizedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.drawImage(originalImage, 0, 0, width, height, null);
        g2d.dispose();

        return resizedImage;
    }

    // entender o conceito de "L" em numeros longos, tem relação com milimis
    public void sleep(int threadDelay) {
        try {
            Thread.sleep(threadDelay * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // entender o conceito de (long) depois, ainda nao compreendi muito bem
    public void sleep(double threadDelay) {
        try {
            // (long) é um tipo de cast, tal qual (int)
            Thread.sleep((long) (threadDelay * 1000L));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}