package ch.csbe.yatzy.controller;

import java.io.IOException;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.fxml.*;
import javafx.event.ActionEvent;

public class StartController {

	@FXML
	public void play(ActionEvent event) throws IOException {
		Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(StartController.class.getResource("/ch/csbe/yatzy/view/register.fxml"));
		loader.setController(new RegisterController());
		Parent root = loader.load();
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void highscore(ActionEvent event) throws IOException {
		Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(StartController.class.getResource("/ch/csbe/yatzy/view/highscore.fxml"));
		loader.setController(new HighscoreController());
		Parent root = loader.load();
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}
	
	public void close(ActionEvent event) {
		Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
		stage.close();
	}

}
