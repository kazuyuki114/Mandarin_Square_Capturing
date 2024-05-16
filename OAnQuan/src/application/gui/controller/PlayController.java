package application.gui.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.stage.Stage;

public class PlayController implements Initializable {
	private Media media;
//	private MediaPlayer mediaPlayer;
	private Stage stage;
	private Scene menuScene;
	
    @FXML
    private Slider musicSlider;

    @FXML
    private Slider soundEffectSlider;
    
    @FXML
    private Button backButton;
	
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
	public void backToMenu(ActionEvent event) throws IOException {
	    try {
	        MediaManager.getMediaPlayer().pause();
	        MediaManager.setPlaying(false);
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/gui/resource/MenuScene.fxml"));
	        Parent root = loader.load();
	        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        menuScene = new Scene(root);
	        stage.setScene(menuScene);
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        System.err.println("Error loading FXML file: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	
}
