package ch.csbe.yatzy.controller;

import java.io.IOException;
import java.util.Map;

import ch.csbe.yatzy.helper.Serializer;
import ch.csbe.yatzy.model.Highscore;
import ch.csbe.yatzy.model.Player;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.beans.value.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;

public class HighscoreController {
	@FXML
	private TableView table;

	public void back(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ch/csbe/yatzy/view/start.fxml"));
		loader.setController(new StartController());
		Parent root = loader.load();

		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void initialize() {
		TableColumn<Map.Entry<Player, Integer>, String> column1 = new TableColumn<>("Player");
		column1.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Map.Entry<Player, Integer>, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(
							TableColumn.CellDataFeatures<Map.Entry<Player, Integer>, String> p) {
						// this callback returns property for just one cell, you
						// can't use a loop here
						// for first column we use key
						return new SimpleStringProperty(p.getValue().getKey().getName());
					}
				});
		column1.setMinWidth(130);
		TableColumn<Map.Entry<Player, Integer>, String> column2 = new TableColumn<>("Score");
		column2.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Map.Entry<Player, Integer>, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(
							TableColumn.CellDataFeatures<Map.Entry<Player, Integer>, String> p) {
						// for second column we use value
						return new SimpleStringProperty(p.getValue().getValue()+"");
					}
				});

		Highscore hs = Serializer.load();
		if(hs == null){
			hs = new Highscore();
		}
		ObservableList<Map.Entry<Player, Integer>> items = FXCollections.observableArrayList(hs.result().entrySet());
		table.getColumns().setAll(column1, column2);
		table.setItems(items);
	}
}
