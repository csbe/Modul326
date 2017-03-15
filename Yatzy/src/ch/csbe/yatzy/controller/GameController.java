package ch.csbe.yatzy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ch.csbe.yatzy.helper.GameHelper;
import ch.csbe.yatzy.helper.Serializer;
import ch.csbe.yatzy.model.Dice;
import ch.csbe.yatzy.model.Game;
import ch.csbe.yatzy.model.Highscore;
import ch.csbe.yatzy.model.Player;
import ch.csbe.yatzy.model.Tableau;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameController {

	private String p1, p2;
	private Game game;
	private List<ImageView> dices = new ArrayList<ImageView>();

	@FXML
	private Label player1, player2, player11, player21, player111, player211, turn;

	@FXML
	private Label leftTotal1, leftTotal2, rightTotal1, rightTotal2, total1, total2, leftBonus1, leftBonus2;

	@FXML
	private ImageView dice1, dice2, dice3, dice4, dice5;

	@FXML
	private GridPane gridPane, gridPaneRight;

	public GameController(String player1, String player2) {
		p1 = player1;
		p2 = player2;
		game = new Game(new Player(p1), new Player(p2));
	}

	public void roll(ActionEvent event) throws IOException {
		game.roll();

		for (int i = 0; i < dices.size(); i++) {
			dices.get(i).setImage(game.getDices().get("dice" + (i + 1)).getImage());
		}
		paintDices();
		paintCheckings();
	}

	public void play(ActionEvent event) throws IOException {
		checkFinish();
		game.changeTableau();
	}

	public Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridyPane) {
		Node result = null;
		ObservableList<Node> childrens = gridyPane.getChildren();

		for (Node node : childrens) {
			if (node != null && gridyPane.getColumnIndex(node) != null && gridyPane.getRowIndex(node) != null
					&& gridyPane.getRowIndex(node) == row && gridyPane.getColumnIndex(node) == column) {
				result = node;
				break;
			}
		}

		return result;
	}

	public void paintCheckings() {
		for (int i = 0; i < 6; i++) {
			if (!(getNodeByRowColumnIndex(1 + i, 2 + game.whichTableau(), gridPane) instanceof Label))
				gridPane.add((Node) generateCheckbox(), 2 + game.whichTableau(), 1 + i);
		}

		for (int i = 0; i < 7; i++) {
			boolean show = false;
			switch (i) {
			case 0:
				show = GameHelper.isCount(game.getDices(), 3);
				break;
			case 1:
				show = GameHelper.isCount(game.getDices(), 4);
				break;
			case 2:
				show = GameHelper.isFullHouse(game.getDices());
				break;
			case 3:
				show = GameHelper.isSmallStreet(game.getDices());
				break;
			case 4:
				show = GameHelper.isBigStreet(game.getDices());
				break;
			case 5:
				show = GameHelper.isCount(game.getDices(), 5);
				break;
			case 6:
				show = true;
			}

			if (!(getNodeByRowColumnIndex(1 + i, 1 + game.whichTableau(), gridPaneRight) instanceof Label)) {
				CheckBox cb = generateCheckbox();
				if (show) {
					cb.setStyle("-fx-border-color: green;");
				}
				gridPaneRight.add((Node) cb, 1 + game.whichTableau(), 1 + i);
			}
		}
	}

	public CheckBox generateCheckbox() {
		CheckBox cb = new CheckBox();
		cb.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				CheckBox chk = (CheckBox) event.getSource();
				if (gridPane.getChildren().contains(chk)) {
					int value = 0;
					switch (gridPane.getRowIndex(chk)) {
					case 1:
						value = GameHelper.calculate(game.getDices(), 1);
						game.setType("one", value);
						break;
					case 2:
						value = GameHelper.calculate(game.getDices(), 2);
						game.setType("two", value);
						break;
					case 3:
						value = GameHelper.calculate(game.getDices(), 3);
						game.setType("three", value);
						break;
					case 4:
						value = GameHelper.calculate(game.getDices(), 4);
						game.setType("four", value);
						break;
					case 5:
						value = GameHelper.calculate(game.getDices(), 5);
						game.setType("five", value);
						break;
					case 6:
						value = GameHelper.calculate(game.getDices(), 6);
						game.setType("six", value);
						break;
					}

					gridPane.add(new Label(value + ""), gridPane.getColumnIndex(chk), gridPane.getRowIndex(chk));
					gridPane.getChildren().remove(chk);
				} else if (gridPaneRight.getChildren().contains(chk)) {
					int value = 0;
					switch (gridPaneRight.getRowIndex(chk)) {
					case 1:
						value = (GameHelper.isCount(game.getDices(), 3)) ? GameHelper.calculateSum(game.getDices()) : 0;
						game.setType("thriple", value);
						break;
					case 2:
						value = (GameHelper.isCount(game.getDices(), 4)) ? GameHelper.calculateSum(game.getDices()) : 0;
						game.setType("quatruple", value);
						break;
					case 3:
						value = (GameHelper.isFullHouse(game.getDices())) ? 25 : 0;
						game.setType("fullhouse", value);
						break;
					case 4:
						value = (GameHelper.isSmallStreet(game.getDices())) ? 30 : 0;
						game.setType("smallstreet", value);
						break;
					case 5:
						value = (GameHelper.isBigStreet(game.getDices())) ? 40 : 0;
						game.setType("bigstreet", value);
						break;
					case 6:
						value = (GameHelper.isCount(game.getDices(), 5)) ? 50 : 0;
						game.setType("yatzy", value);
						break;
					case 7:
						value = GameHelper.calculateSum(game.getDices());
						game.setType("chance", value);
						break;
					}

					checkFinish();

					gridPaneRight.add(new Label(value + ""), gridPaneRight.getColumnIndex(chk),
							gridPaneRight.getRowIndex(chk));
					gridPaneRight.getChildren().remove(chk);
				}

				Iterator<Node> grids = gridPane.getChildren().iterator();
				while (grids.hasNext()) {
					Node n = grids.next();
					if (n instanceof CheckBox)
						grids.remove();
				}

				Iterator<Node> gridsr = gridPaneRight.getChildren().iterator();
				while (gridsr.hasNext()) {
					Node n = gridsr.next();
					if (n instanceof CheckBox)
						gridsr.remove();
				}

				int gamer = game.getTableaus().indexOf(game.getActualTableau()) + 1;
				// leftTotal1, leftTotal2, rightTotal1, rightTotal2, total1,
				// total2, leftBonus1, leftBonus2
				if (gamer == 1) {
					leftTotal1.setText(game.getActualTableau().calculateUp() + "");
					leftBonus1.setText(game.getActualTableau().getBonus() + "");
					rightTotal1.setText(game.getActualTableau().calculateDown() + "");
					total1.setText(game.getActualTableau().calculateTotal() + "");
				} else {
					leftTotal2.setText(game.getActualTableau().calculateUp() + "");
					leftBonus2.setText(game.getActualTableau().getBonus() + "");
					rightTotal2.setText(game.getActualTableau().calculateDown() + "");
					total2.setText(game.getActualTableau().calculateTotal() + "");
				}

				game.changeTableau();
				for (int i = 0; i < dices.size(); i++) {
					dices.get(i).setEffect((ColorAdjust) null);

					dices.get(i).setImage(null);
				}

				paintDices();

			}
		});
		return cb;
	}

	public void checkFinish() {
		if (game.getRound() >= 13) {
			Tableau winner = (game.getTableaus().get(0).calculateTotal() > game.getTableaus().get(1).calculateTotal())
					? game.getTableaus().get(0) : game.getTableaus().get(1);
			turn.setText("Your the winner player " + winner.getPlayer().getName());
			Alert a = new Alert(Alert.AlertType.INFORMATION);
			a.setContentText("Your the winner player " + winner.getPlayer().getName());

			Highscore hs = Serializer.load();
			if (hs == null) {
				hs = new Highscore();
			}
			hs.add(winner.getPlayer(), winner.calculateTotal());
			Serializer.save(hs);
			a.setHeaderText("Game finish");
			a.show();
			a.setOnCloseRequest(new EventHandler<DialogEvent>() {

				@Override
				public void handle(DialogEvent event) {
					Stage stage = (Stage) ((Label) player1).getScene().getWindow();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/ch/csbe/yatzy/view/start.fxml"));
					loader.setController(new StartController());
					Parent root;
					try {
						root = loader.load();

						Scene scene = new Scene(root);
						stage.setScene(scene);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
		}
	}

	public void initialize() {
		player1.setText(p1);
		player2.setText(p2);
		player11.setText(p1);
		player21.setText(p2);
		player111.setText(p1);
		player211.setText(p2);

		dices.add(dice1);
		dices.add(dice2);
		dices.add(dice3);
		dices.add(dice4);
		dices.add(dice5);
		for (int i = 0; i < dices.size(); i++) {
			dices.get(i).setImage(null);
		}
		turn.setText("It's your turn " + game.getActualTableau().getPlayer().getName());
	}

	public void paintDices() {
		turn.setText("It's your turn " + game.getActualTableau().getPlayer().getName());
		final ColorAdjust blackout = new ColorAdjust();
		blackout.setBrightness(-0.2);

		for (int i = 0; i < dices.size(); i++) {
			EventHandler<MouseEvent> eh = new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
					ImageView image = ((ImageView) event.getSource());
					String dice = image.getId();
					// System.out.println("handler " + dice);
					Dice d = game.getDices().get(dice);

					d.setFixed(!d.isFixed());
					if (d.isFixed()) {
						image.setEffect(blackout);
						image.setCache(true);
						image.setCacheHint(CacheHint.SPEED);
					} else {
						image.setEffect((ColorAdjust) null);
					}

				}
			};
			dices.get(i).setOnMouseClicked(eh);
		}

	}

}
