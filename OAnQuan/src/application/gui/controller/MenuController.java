package application.gui.controller;

import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.media.Media;
/*import javafx.scene.media.MediaPlayer;*/
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;

public class MenuController implements Initializable{
	private Media media;
	//private MediaPlayer mediaPlayer;
    private Stage stage;
    private Scene settingScene;
    private Scene helpScene;
    private Scene playModeSelectionScene;

    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			if(!MediaManager.isPlaying()) {
				File file = new File("src/application/gui/music/Title_Reverse_1999_Soundtrack.mp3");
				System.out.println(file.exists());
				media = new Media(file.toURI().toString());
		        MediaManager.setMediaPlayer(media);
		        MediaManager.setPlaying(true);
			}
	        MediaManager.getMediaPlayer().play();
	        MediaManager.getMediaPlayer().setOnEndOfMedia(() -> MediaManager.getMediaPlayer().seek(MediaManager.getMediaPlayer().getStartTime()));
	}
	// click on settingButton
	public void openSetting(ActionEvent event) throws IOException {
	    try {
	        //MediaManager.getMediaPlayer().pause();
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/gui/resource/SettingScene.fxml"));
	        Parent root = loader.load();
	        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        settingScene = new Scene(root);
	        stage.setScene(settingScene);
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        System.err.println("Error loading FXML file: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	public void openHelp(ActionEvent event) throws IOException {
		/*
		 * Parent root = FXMLLoader.load(getClass().getResource(
		 * "/javafx/scene/transition/scene2/scene2.fxml")); Scene scene =
		 * helpButton.getScene(); root.translateYProperty().set(scene.getHeight());
		 * 
		 * parentContainer.getChildren().add(root);
		 * 
		 * Timeline timeline = new Timeline(); KeyValue kv = new
		 * KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN); KeyFrame kf =
		 * new KeyFrame(Duration.seconds(1), kv); timeline.getKeyFrames().add(kf);
		 * timeline.setOnFinished(t -> {
		 * parentContainer.getChildren().remove(anchorRoot); }); timeline.play();
		 */
		try {
	    	MediaManager.getMediaPlayer().pause();
	    	MediaManager.setPlaying(false);
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/gui/resource/HelpScene.fxml"));
	        Parent root = loader.load();
	        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        helpScene = new Scene(root);
	        stage.setScene(helpScene);
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        System.err.println("Error loading FXML file: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	// Quit the program
	public void quitProgram(ActionEvent event) throws IOException{
		// Raise an alert before quit the program
		Alert quitAlert = new Alert(Alert.AlertType.CONFIRMATION);
		quitAlert.setTitle("Quit");
		quitAlert.setHeaderText("Are you sure to quit?");
		quitAlert.setContentText("Choose your option: ");
		
		ButtonType buttonYes = new ButtonType("YES", ButtonBar.ButtonData.YES);
		ButtonType buttonNo = new ButtonType("NO", ButtonBar.ButtonData.NO);
		ButtonType buttonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
		quitAlert.getButtonTypes().setAll(buttonYes, buttonNo, buttonCancel);
		Optional<ButtonType> result = quitAlert.showAndWait();
		
		if(result.get() == buttonYes) {
			Platform.exit();
		} 
	}
	// Start the game when clicking on startButton
	public void startGame(ActionEvent event) throws IOException {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/gui/resource/PlayModeSelection.fxml"));
	        Parent root = loader.load();
	        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        playModeSelectionScene = new Scene(root);
	        stage.setScene(playModeSelectionScene);
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        System.err.println("Error loading FXML file: " + e.getMessage());
	        e.printStackTrace();
	    }
	}


}
