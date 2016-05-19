package hu.unideb.inf.prt.KaloriaSzamlalo.controller;

import java.util.regex.Pattern;

import hu.unideb.inf.prt.KaloriaSzamlalo.Main;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Person;
import hu.unideb.inf.prt.KaloriaSzamlalo.services.Services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddingKcalController {

	private Person person;
	private Stage stage;

	@SuppressWarnings("checkstyle:javadocmethod")
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void setPerson(Person person) {
		this.person = person;
	}

	@FXML
	private Label errorNumberFormatMessage;

	@FXML
	private Label errorNotSelectedMessage;

	@FXML
	private ComboBox<String> kcalTypes;

	@FXML
	private TextField amount;

	private ObservableList<String> listOfKcalTypes = FXCollections.observableArrayList();

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void initialize() {
		listOfKcalTypes.add("Szénhidrát");
		listOfKcalTypes.add("Fehérje");
		listOfKcalTypes.add("Zsír");
		kcalTypes.setItems(listOfKcalTypes);
	}

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void handleAdd() {
		Main.getLogger().info("A felhasználó megnyomta a Hozzáadás gombot.");
		String test;
		boolean set = true;
		if (!Pattern.matches("[1-9][0-9]*\\.?[0-9]*", amount.getText())) {
			set = false;
			errorNumberFormatMessage.setVisible(true);
		} else {
			errorNumberFormatMessage.setVisible(false);
		}
		if ((test = kcalTypes.getSelectionModel().selectedItemProperty().getValue()) != null && !errorNumberFormatMessage.isVisible()) {
			switch (test) {
			case "Szénhidrát":
				Services.incGotCH(Double.valueOf(amount.getText()), person);
				break;
			case "Fehérje":
				Services.incGotProtein(Double.valueOf(amount.getText()), person);
				break;
			case "Zsír":
				Services.incGotFat(Double.valueOf(amount.getText()), person);
				break;
			}
			errorNotSelectedMessage.setVisible(false);
		} else {
			set = false;
			if(kcalTypes.getSelectionModel().selectedItemProperty().getValue() == null)
				errorNotSelectedMessage.setVisible(true);
			else
				errorNotSelectedMessage.setVisible(false);
		}
		if (set) {

			stage.close();
		}
	}

}
