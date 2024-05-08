package application.gui.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
/* Class to manager the background sound */
public class MediaManager {
    private static MediaPlayer mediaPlayer;
    private static boolean isPlaying = false;
    
    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public static void setMediaPlayer(Media media) {
        if (mediaPlayer != null) {
            mediaPlayer.dispose();
        }
        mediaPlayer = new MediaPlayer(media);
    }

	public static boolean isPlaying() {
		return isPlaying;
	}

	public static void setPlaying(boolean isPlaying) {
		MediaManager.isPlaying = isPlaying;
	}
}
