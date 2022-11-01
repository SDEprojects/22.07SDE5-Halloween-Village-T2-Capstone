package com.halloween.view;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * This class is responsible for all sound-effect-related audio, including the playing, stopping,
 * and changing the volume of sound effects.
 * <p>For reference, sound effects include footsteps sound and knocking-on-the-door sound.</p>
 */
public class SoundEffects {

  private static Clip clip;
  private static FloatControl soundVolumeControl;
  private static float soundVolume = (-10.0f);
  private static float minSoundVolume = (-80.0f);

  /**
   * Plays a sound effect.
   *
   * @param soundName Name of a sound effect file.
   */
  public static void playSound(String soundName) {
    try {
      // get the url path for our sound effect
      URL url = SoundEffects.class.getResource(soundName);

      if (url != null) {
        // NOTE: Audio input stream can take a URL, file, or string
        AudioInputStream audioInput = AudioSystem.getAudioInputStream(url);
        clip = AudioSystem.getClip();
        clip.open(audioInput);
        // set the sound volume
        soundVolumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        soundVolumeControl.setValue(soundVolume);
        clip.start();
      } else {
        System.out.println("Error: Cannot find sound effect file.");
      }

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Mutes the sound effect.
   */
  public static void muteSoundEffects() {
    try {
      soundVolume = (-80.0f);
      soundVolumeControl.setValue(soundVolume);
    } catch (IllegalArgumentException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Unmutes the sound effect.
   */
  public static void unmuteSoundEffects() {
    try {
      soundVolume = (-10.0f);
      soundVolumeControl.setValue(soundVolume);
    } catch (IllegalArgumentException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Increases the volume of sound effects.
   */
  public void increaseVolume() {
    try {
      soundVolumeControl.setValue(soundVolumeControl.getValue() + 10.0f);
    } catch (IllegalArgumentException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Decreases the volume of sound effects.
   */
  public void decreaseVolume() {
    try {
      soundVolumeControl.setValue(soundVolumeControl.getValue() - 10.0f);
    } catch (IllegalArgumentException ex) {
      ex.printStackTrace();
    }
  }

}