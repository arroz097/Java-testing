package com.arroz097.models;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundManager {

    public static void playSound(String path) {

        try {
            File audioFile = new File(path);

            if (audioFile.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(audioFile);

                Clip clip = AudioSystem.getClip();

                clip.open(audioInput);
                clip.start();
            } else {
                System.out.println("arquivo de som nao encontraod");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


}