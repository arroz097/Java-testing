package com.arroz097.models;

import com.arroz097.utils.Utility;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Random;

public class Cat {

    private static final Utility utility = new Utility();
    private static final Random rng = new Random();

    private static final File catFolder = new File("src/assets/cats");
    private static final File catImages = new File(catFolder, "images");
    private static final File sleepFolder = new File(catImages, "sleep");
    private static final File soundFolder = new File(catFolder, "sounds");

    // fazer sistema de stamina
    // private faz com que scripts de fora nao consigam alterar variaveis private
    // final permite que variavel só seja atribuido um valor uma unica vez
    private final String name;
    private final int maxEnergy;
    private int energy;
    private boolean onAction = false;

    private ImageCreator cat;
    private String catImage;

    // talvez uma classe de criar imagens e referenciar aqui?

    // ideia de criar pasta de gatos com diversas imagens e sons diferentes
    // fazer cada gato criado se movendop por conta proprai sozinho na tela
    // fazer cada gato miar aleatoriamente e talvez interagir com outro, gastando os roar
    // se tiver abaixo de tal energia o gato vai dormir por um tempo até conseguir energia novamente

    // Isso é o construtor: ele roda assim que você dá o "new"
    // equivalente ao .new() de OOP dos metatables, this é o equivalente de self
    public Cat(String nome, int energy, int width, int height) {

        // botar um tipo de texto em cima da cabeça do gatos referenciando seu nome
        this.name = nome;
        this.maxEnergy = energy;
        this.energy = energy;

        File[] images = catImages.listFiles((dir, name) -> name.endsWith(".png"));
        if (images != null && images.length > 0) {

            int index = rng.nextInt(images.length);

            File chosenCat = images[index];

            this.catImage = chosenCat.getPath();

            SwingUtilities.invokeLater(() -> {
                this.cat = new ImageCreator(this.catImage, 250, 259);

                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

                this.cat.getFrame().setLocation(rng.nextInt((int) screenSize.getWidth()), rng.nextInt((int) screenSize.getHeight()));
            });

        }
    }


    public Cat(String nome) {
        // this() permite encadear construtores com overload sem ter que precisar repetir o codigo
        this(nome, 10, 200, 200);
    }

    public String getName() {
        return this.name;
    }

    public int getEnergy() {
        return this.energy;
    }

    public boolean getAction() {
        return this.onAction;
    }

    public String getCatImage() {
        return this.catImage;
    }

    public void initialize() {
        // iniciar o movimento aleatorio dos gatos e cada um com sua função aleatoria e etc
    }

    private void wander() {
        new Thread(() -> {

        }).start();
    }

    private void catLogic() {
        // onde toda logica do gato vai ficar
    }

    public void roar() {
        if (this.onAction) return;

        int cost = 1;

        if (this.energy >= cost) {
            this.onAction = true;

            File[] sounds = soundFolder.listFiles();

            assert sounds != null;

            int index = rng.nextInt(sounds.length);

            File chosenSound = sounds[index];

            SoundManager.playSound(chosenSound.getPath());

            int lastWidth = this.cat.getLabel().getWidth();
            int lastHeight = this.cat.getLabel().getHeight();

            this.cat.updateImageLabel(this.cat.getLabel().getWidth() + 50, this.cat.getLabel().getHeight() + 50);

            this.energy--;
            utility.write("gato " + this.name + " diz: " + "RAWRRRR!!!!");
            utility.sleep(0.5);

            utility.write(this.name + " perde " + cost + " stamina");
            utility.sleep(0.5);

            this.cat.updateImageLabel(lastWidth, lastHeight);

            utility.write(this.name + " possui atualmente " + this.energy + " de energia");
            utility.sleep(0.3);

            this.onAction = false;
        } else {
            utility.write(this.name + " nao possui energia suficiente para rugidos, tente um descanso");
        }

    }

    public void sleep() {
        if (this.energy < this.maxEnergy) {
            if (this.onAction) return;

            this.onAction = true;

            utility.write(this.name + " iniciando soneca");

            int recoveredEnergy = 0;

            while (this.energy < 10) {
                utility.sleep(1);

                this.energy++;
                recoveredEnergy++;

                utility.write(this.name + " energia: " + this.energy);
            }

            utility.sleep(0.5);
            utility.write(this.name + " dormiu um bocado e recuperou +" + recoveredEnergy + " de energia"); // talvez dizer quanto de energia recuperou?

            utility.sleep(0.5);

            this.onAction = false;
        } else {
            utility.write(this.name + " nao precisa de descanso por ora");
        }
    }
}