package ch.csbe.yatzy;


import ch.csbe.yatzy.controller.StartController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		StartController controller = new StartController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("view/start.fxml"));
		loader.setController(controller);
		Parent root = loader.load();
		
		Scene scene = new Scene(root);

		//scene.getStylesheets().add("style.css");

		stage.setTitle("Yatzee");
		stage.setScene(scene);
		// setUserAgentStylesheet(STYLESHEET_CASPIAN);
		stage.show();

	}

}
