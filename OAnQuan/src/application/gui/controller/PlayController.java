package application.gui.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.media.Media;

public class PlayController implements Initializable {
	private Media media;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if(!MediaManager.isPlaying()) {
			File file = new File("src/application/gui/music/Battle_P2_Reverse_1999_Soundtrac_ Extended.mp3");
			System.out.println(file.exists());
			media = new Media(file.toURI().toString());
	        MediaManager.setMediaPlayer(media);
	        MediaManager.setPlaying(true);
		}
        MediaManager.getMediaPlayer().play();
        MediaManager.getMediaPlayer().setOnEndOfMedia(() -> MediaManager.getMediaPlayer().seek(MediaManager.getMediaPlayer().getStartTime()));

	}
	
}
