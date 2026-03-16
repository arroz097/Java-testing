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
    private String catType;
    private String catSound;

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
        File[] sounds = soundFolder.listFiles();

        if (sounds != null) {
            int soundIndex = rng.nextInt(sounds.length);

            File chosenSound = sounds[soundIndex];

            this.catSound = chosenSound.getPath();
        }

        if (images != null && images.length > 0) {

            int imageIndex = rng.nextInt(images.length);

            File chosenCat = images[imageIndex];

            this.catType = chosenCat.getName();

            utility.write(chosenCat.getName());

            SwingUtilities.invokeLater(() -> {
                this.cat = new ImageCreator(chosenCat.getPath(), 250, 250);

                this.cat.getSpritePanel().setSpriteName(this.name);

                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Image icon = new ImageIcon("src/assets/misc/cat_icon.png").getImage();

                this.cat.getFrame().setLocation(rng.nextInt((int) screenSize.getWidth()), rng.nextInt((int) screenSize.getHeight()));
                this.cat.getFrame().setTitle(this.name);

                this.cat.getFrame().setIconImage(icon);
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

    public JFrame getCatFrame() {
        return this.cat.getFrame();
    }

    public void initialize() {
        // iniciar o movimento aleatorio dos gatos e cada um com sua função aleatoria e etc
        catLogic();
    }

    private void wander() {
        if (this.onAction) { return; }

        final int[] x = {0};
        final int[] y = {0};

        int randomX = rng.nextInt(-60, 60);
        int randomY = rng.nextInt(-60, 60);

        Timer walk = new Timer(60, null);

        walk.addActionListener(e -> {

            // otimizar a velociodade e tentar deixar mmais lento a movimentaçao
            // usar lerp talvez?

            if (randomX < 0 ) {
                x[0] = Math.clamp(x[0] - 1, randomX, 1);
            } else {
                x[0] = Math.clamp(x[0] + 1, 0, randomX);
            }

            if (randomY < 0 ) {
                y[0] = Math.clamp(y[0] - 1, randomY, 1);
            } else {
                y[0] = Math.clamp(y[0] + 1, 0, randomY);
            }

            //utility.write(x[0]);

            if (x[0] == randomX) { walk.stop(); }
            if (y[0] == randomY) { walk.stop(); }

            getCatFrame().setLocation(getCatFrame().getX() + x[0], getCatFrame().getY() + y[0]);
        });

        walk.start();

    }

    private void catLogic() {
        // onde toda logica do gato vai ficar
        new Thread(() -> {
            while (true) {
                // deixar mais aleatorio depois
                utility.sleep(rng.nextInt(3, 7));

                boolean canAct = rng.nextBoolean();

                if (canAct) {
                    if (this.energy >= 1) {
                        roar();
                    } else {
                        sleep();
                    }
                }

                wander();

            }
        }).start();
    }

    public void roar() {
        if (this.onAction) return;

        int cost = 1;

        if (this.energy >= cost) {
            this.onAction = true;

            SoundManager.playSound(this.catSound);

            int lastWidth = this.cat.getSpriteWidth();
            int lastHeight = this.cat.getSpriteHeight();

            int targetWidth = lastWidth + 50;
            int targetHeight = lastHeight + 50;

            // final nao trava o conteudo, apenas referencia, então tabelas e seus valores podem mudar
            final int[] currentWidth = {lastWidth};
            final int[] currentHeight = {lastHeight};
            final boolean[] growing = {true};

            // 16ms = 60 fps, 33 ms = ~30fps
            Timer timer = new Timer(16, null);

            timer.addActionListener(e -> {
                if (growing[0]) {
                    currentWidth[0] = Math.min(currentWidth[0] + 2, targetWidth);
                    currentHeight[0] = Math.min(currentHeight[0] + 2, targetHeight);

                    this.cat.updateSpriteSize(currentWidth[0], currentHeight[0]);

                    if (currentWidth[0] == targetWidth && currentHeight[0] == targetHeight) {
                        growing[0] = false;
                    }
                } else {
                    currentWidth[0] = Math.max(currentWidth[0] - 2, lastWidth);
                    currentHeight[0] = Math.max(currentHeight[0] - 2, lastHeight);

                    this.cat.updateSpriteSize(currentWidth[0], currentHeight[0]);

                    if (currentWidth[0] == lastWidth && currentHeight[0] == lastHeight) {
                        this.cat.updateSpriteSize(lastWidth, lastHeight);
                        timer.stop();
                    }
                }
            });

            timer.start();

            //this.cat.updateImageLabel(this.cat.getLabel().getWidth() + 50, this.cat.getLabel().getHeight() + 50);

            this.energy--;
            utility.write("gato " + this.name + " diz: " + "RAWRRRR!!!!");
            utility.sleep(0.5);

            utility.write(this.name + " perde " + cost + " stamina");
            utility.sleep(0.5);

           // this.cat.updateImageLabel(lastWidth, lastHeight);

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

            this.cat.getSpritePanel().setSpriteImage(sleepFolder + "/" + catType);

            //this.cat = new ImageCreator(sleepFolder + "/" + catType, 250, 250);

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

            this.cat.getSpritePanel().setSpriteImage(catImages + "/" + catType);

            this.onAction = false;
        } else {
            utility.write(this.name + " nao precisa de descanso por ora");
        }
    }
}