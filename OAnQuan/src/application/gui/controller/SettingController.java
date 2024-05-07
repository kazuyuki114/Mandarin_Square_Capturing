package application.gui.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class SettingController implements Initializable{
	private Media media;
	//private MediaPlayer mediaPlayer;
	private Stage stage;
	private Scene menuScene;
    @FXML
    private Slider musicSlider;
    
    public static double musicValue = 100.0;
    @FXML
    private Slider soundEffectSlider;
    
    public static double soundEffectValue = 100;
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if(!MediaManager.isPlaying()) {
			File file = new File("src/application/gui/music/Title_Reverse_1999_Soundtrack.mp3");
			System.out.println(file.exists());
			media = new Media(file.toURI().toString());
	        MediaManager.setMediaPlayer(media);
	        MediaManager.setPlaying(true);
		}
        MediaManager.getMediaPlayer().play();
        MediaManager.getMediaPlayer().setOnEndOfMedia(() -> MediaManager.getMediaPlayer().seek(MediaManager.getMediaPlayer().getStartTime()));
        musicSlider.setValue(musicValue);
        musicSlider.valueProperty().addListener(new ChangeListener<Number>() {
        	@Override
        	public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
        		musicValue = musicSlider.getValue();
        		MediaManager.getMediaPlayer().setVolume(musicSlider.getValue() * 0.01);
        	}
        });
	}
	public void backToMenu(ActionEvent event) throws IOException {
	    try {
	        //MediaManager.getMediaPlayer().pause();
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/gui/resource/menu.fxml"));
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
