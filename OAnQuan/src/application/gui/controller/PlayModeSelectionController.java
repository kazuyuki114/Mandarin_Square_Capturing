package application.gui.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PlayModeSelectionController {
	private Stage stage;
	private Scene playScene;
	private Scene menuScene;
	
	@FXML
	private Button pvpButton;
	
	@FXML 
	private Button pvbButton;
	
	@FXML
	private Button backButton;

	public void startPvP(ActionEvent event) throws IOException {
        navigateToPlayScene(event, "/application/gui/resource/PlayScene.fxml");
    }

    public void startPvB(ActionEvent event) throws IOException {
        navigateToPlayScene(event, "/application/gui/resource/PlayVsBotScene.fxml");
    }
    
    private void navigateToPlayScene(ActionEvent event, String fxmlFilePath) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
            Parent root = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            playScene = new Scene(root);
            stage.setScene(playScene);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error loading FXML file: " + e.getMessage());
            e.printStackTrace();
        }
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
