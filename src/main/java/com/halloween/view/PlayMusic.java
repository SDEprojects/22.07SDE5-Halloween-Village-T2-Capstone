package com.halloween.view;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * This class is responsible for all background-music-related audio, including the playing,
 * stopping, and changing the volume of background music.
 */
public class PlayMusic {

  private Clip clip;
  private FloatControl musicVolume;

  /**
   * Plays the background music from a desired music file.
   *
   * @param musicName Name of a music file.
   */
  public void play(String musicName) {
    try {

      // get the url path for our music
      URL url = getClass().getResource(musicName);

      // if the desired music file does not exist, alert the user
      if (url == null) {
        System.out.println("Error: Failed to find background music file.");
      }
      // if the clip already exists, the music is already playing
      if (clip != null) {
        System.out.println("The background music is already playing.");
      }
      // check if the appropriate music file exists and
      // check if a clip already exist to avoid playing multiple layers of background music
      if (url != null && clip == null) {
        // NOTE: Audio input stream can take a URL, file, or string
        AudioInputStream audioInput = AudioSystem.getAudioInputStream(url);
        clip = AudioSystem.getClip();
        clip.open(audioInput);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        // set the music volume
        musicVolume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        musicVolume.setValue(-20.0f);
        // start the music
        clip.start();
      }

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Stops the background music.
   */
  public void stop() {
    // check if clip exists and if it does, stop the clip
    if (clip != null) {
      clip.stop();
    }
    clip = null;
  }

  /**
   * Increases the volume of the background music by 10 decibels.
   */
  public void increaseVolume() {
    try {
      musicVolume.setValue(musicVolume.getValue() + 10.0f);
    } catch (IllegalArgumentException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Decreases the volume of background music by 10 decibels.
   */
  public void decreaseVolume() {
    try {
      musicVolume.setValue(musicVolume.getValue() - 10.0f);
    } catch (IllegalArgumentException ex) {
      ex.printStackTrace();
    }
  }

}