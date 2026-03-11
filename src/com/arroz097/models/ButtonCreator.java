package com.arroz097.models;

import com.arroz097.utils.Utility;

import javax.swing.*;
import java.awt.*;

public class ButtonCreator {

    private static final Utility utility = new Utility();

    private boolean isMoving = false;

    private final JFrame frame;
    private final JButton button;

    // talvez criar uma variavel de cooldown em relação aos botoes?
    // assim evitando spam e filas de execuçção, nem que seja um cooldown curto

    public ButtonCreator(String frameText, String buttonText, int frameWidth, int frameHeight, int buttonWidth, int buttonHeight) {

        // configura o frame
        this.frame = new JFrame(frameText);
        this.frame.setSize(frameWidth, frameHeight);
        this.frame.setLocationRelativeTo(null);
        this.frame.setLayout(new GridBagLayout());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // configura o botão
        this.button = new JButton(buttonText);
        this.button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        this.button.setFocusPainted(false);

        this.frame.add(this.button);
        this.frame.setVisible(true);
    }

    public ButtonCreator(String frameText, String buttonText) {
        this(frameText, buttonText, 300, 200, 180, 30);
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public JButton getButton() {
        return this.button;
    }

    // inutil? talvez
    public void removeButton() {
        this.frame.remove(this.button);
        this.frame.revalidate();
        this.frame.repaint();
    }

    public void moveButton(int xPos, int yPos) {
        if (this.isMoving) return;

        this.isMoving = true;

        new Thread(() -> {
            double xLocation = 0;
            double yLocation = 0;

            int currentX = this.frame.getX();
            int currentY = this.frame.getY();

            this.button.setText("moving...");

            for (int i = 0; i < 30; i++) {
                xLocation = utility.lerp(xLocation, xPos, 0.1);
                yLocation = utility.lerp(yLocation, yPos, 0.1);

                this.frame.setLocation(currentX + (int) xLocation, currentY + (int) yLocation);

                utility.sleep(0.1);
            }

            utility.sleep(1);

            this.isMoving = false;

            this.button.setText("move!");

        }).start();

    }

}