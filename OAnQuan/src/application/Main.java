package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("gui/resource/menu.fxml"));
			Scene menuScene = new Scene(root);
			primaryStage.setTitle("O An Quan");
			primaryStage.setScene(menuScene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.setProperty("javafx.userAgentStylesheetUrl", "CASPIAN");
		launch(args);
	}
}
