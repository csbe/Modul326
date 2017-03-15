package ch.csbe.yatzy.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {
	
	@FXML
	private TextField player1;
	
	@FXML
	private TextField player2;

	public void back(ActionEvent event) throws IOException {
		Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ch/csbe/yatzy/view/start.fxml"));
		loader.setController(new StartController());
		Parent root = loader.load();
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}
	
	public void start(ActionEvent event) throws IOException {
		if(!player1.getText().equals("")&&!player2.getText().equals("")){
			Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ch/csbe/yatzy/view/game.fxml"));
			loader.setController(new GameController(player1.getText(),player2.getText()));
			Parent root = loader.load();
			
			Scene scene = new Scene(root);
			stage.setScene(scene);
		}else{
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setContentText("Enter the player name!");
			a.setHeaderText("Input Error");
			a.show();
		}
	}
	
}
