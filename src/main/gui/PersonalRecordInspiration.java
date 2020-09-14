package gui;

import javax.swing.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import java.awt.*;
import java.io.File;

// motivates users with a gif of arnold in his prime, and ronnie coleman screaming "lightweight baby"
public class PersonalRecordInspiration extends JFrame {

    private JLabel arnoldGif;

    private int interfaceHeight = 400;
    private int interfaceWidth = 450;


    public PersonalRecordInspiration() {
        super("myTrainingPal - Congratulations!");
        setSize(interfaceWidth, interfaceHeight);
        setUpLabels();

        getContentPane().add(arnoldGif);

        playSound("data/Ronnie Coleman LIGHTWEIGHT BABY!!!!!.wav");

    }

    // EFFECTS: set up gif of Arnold Posing
    private void setUpLabels() {
        ImageIcon iii = new ImageIcon("data/arnoldPosing.gif");
        arnoldGif = new JLabel(iii);
    }

    // EFFECTS: plays sound of Ronnie Coleman screaming "LightWeight Baby, ... heavy a** weight"
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}
