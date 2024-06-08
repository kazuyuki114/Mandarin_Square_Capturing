package application.gui.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
/* Class to manager the background sound */
public class MediaManager {
    private static MediaPlayer mediaPlayer;
    private static MediaPlayer soundPlayer;
    private static boolean isPlaying = false;
    private static double musicVolume = 100.0;
    private static double soundEffectVolume = 100.0;
    private static double currentMusicVolume = 0;
    private static double currentSoundVolume = 0;

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public static void setMediaPlayer(Media media) {
        if (mediaPlayer != null) {
            mediaPlayer.dispose();
        }
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(musicVolume);
    }

	public static boolean isPlaying() {
		return isPlaying;
	}

	public static void setPlaying(boolean isPlaying) {
		MediaManager.isPlaying = isPlaying;
	}

	public static double getMusicVolume() {
		return musicVolume;
	}

	public static void setMusicVolume(double musicVolume) {
		MediaManager.musicVolume = musicVolume;
	}

	public static double getSoundEffectVolume() {
		return soundEffectVolume;
	}

	public static void setSoundEffectVolume(double soundEffectVolume) {
		MediaManager.soundEffectVolume = soundEffectVolume;
	}

	public static MediaPlayer getSoundPlayer() {
		return soundPlayer;
	}

	public static void setSoundPlayer(Media media) {
        if (soundPlayer != null) {
            soundPlayer.dispose();
        }
        soundPlayer = new MediaPlayer(media);
        soundPlayer.setVolume(soundEffectVolume);
	}

	public static double getCurrentMusicVolume() {
		return currentMusicVolume;
	}

	public static void setCurrentMusicVolume(double currentMusicVolume) {
		MediaManager.currentMusicVolume = currentMusicVolume;
	}

	public static double getCurrentSoundVolume() {
		return currentSoundVolume;
	}

	public static void setCurrentSoundVolume(double currentSoundVolume) {
		MediaManager.currentSoundVolume = currentSoundVolume;
	}

}
