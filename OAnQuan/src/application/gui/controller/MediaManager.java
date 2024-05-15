package application.gui.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
/* Class to manager the background sound */
public class MediaManager {
    private static MediaPlayer mediaPlayer;
    private static boolean isPlaying = false;
    private static double musicVolume = 100.0;
    private static double soundEffectVolume = 100.0;
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

}
