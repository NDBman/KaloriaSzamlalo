package hu.unideb.inf.prt.KaloriaSzamlalo.controller;

import java.util.regex.Pattern;

import hu.unideb.inf.prt.KaloriaSzamlalo.comput.Comput;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Person;
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

	public void setStage(Stage stage) {
		this.stage = stage;
	}

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
	public void initialize() {
		listOfKcalTypes.add("Szénhidrát");
		listOfKcalTypes.add("Fehérje");
		listOfKcalTypes.add("Zsír");
		kcalTypes.setItems(listOfKcalTypes);
	}

	@FXML
	public void handleAdd() {
		String test;
		boolean set = true;
		if (Pattern.matches("[1-9][0-9]*.?[0-9]*", amount.getText())) {
			if ((test = kcalTypes.getSelectionModel().selectedItemProperty().getValue()) != null) {
				switch (test) {
				case "Szénhidrát":
					Comput.incGotCH(Double.valueOf(amount.getText()), person);
					break;
				case "Fehérje":
					Comput.incGotProtein(Double.valueOf(amount.getText()), person);
					break;
				case "Zsír":
					Comput.incGotFat(Double.valueOf(amount.getText()), person);
					break;
				}
			} else {
				set = false;
				errorNotSelectedMessage.setVisible(true);
			}
		} else {
			set = false;
			errorNumberFormatMessage.setVisible(true);
		}

		if (set) {
			
			stage.close();
		}
	}

}
