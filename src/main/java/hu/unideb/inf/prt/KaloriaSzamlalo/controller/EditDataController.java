package hu.unideb.inf.prt.KaloriaSzamlalo.controller;

import java.util.regex.Pattern;

import hu.unideb.inf.prt.KaloriaSzamlalo.Main;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Goals;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Person;
import hu.unideb.inf.prt.KaloriaSzamlalo.services.Services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditDataController {

	private Person person;
	private Stage stage;
	private Main main;

	@SuppressWarnings("checkstyle:javadocmethod")
	public void setMain(Main main) {
		this.main = main;
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void setPerson(Person person) {
		this.person = person;

	}

	private ObservableList<String> goals = FXCollections.observableArrayList();

	@FXML
	private Label ageErrorMessage;

	@FXML
	private Label weightErrorMessage;

	@FXML
	private Label heightErrorMessage;

	@FXML
	private Label goalErrorMessage;

	@FXML
	private TextField surNameField;

	@FXML
	private TextField firstNameField;

	@FXML
	private TextField ageField;

	@FXML
	private TextField weightField;

	@FXML
	private TextField heightField;

	@FXML
	private ComboBox<String> goalCBox;

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void initialize() {

		goals.add("Súlycsökkentés");
		goals.add("Súlytartás");
		goalCBox.setItems(goals);
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void setDatas() {
		surNameField.setText(person.getSurName());
		firstNameField.setText(person.getFirstName());

		if (person.getGoal() == Goals.KEEPING_WEIGHT) {

			goalCBox.getSelectionModel().select(1);
		} else {
			goalCBox.getSelectionModel().select(0);
		}
		ageField.setText(person.getAge().toString());
		weightField.setText(person.getWeight().toString());
		heightField.setText(person.getHeight().toString());
	}

	private boolean checkFields() {
		boolean result = true;

		if (goalCBox.getSelectionModel().selectedItemProperty().getValue() == null) {
			goalErrorMessage.setVisible(true);
			result = false;
		}else{
			goalErrorMessage.setVisible(false);
		}
		if (!Pattern.matches("[1-9][0-9]*", ageField.getText())) {
			ageErrorMessage.setVisible(true);
			result = false;
		}else if(Integer.parseInt(ageField.getText()) < 10){
			result = false;
		}else{
			ageErrorMessage.setVisible(false);
		}
		if (!Pattern.matches("[1-9][0-9]*\\.?[0-9]*", weightField.getText())) {
			weightErrorMessage.setVisible(true);
			result = false;
		}else{
			weightErrorMessage.setVisible(false);
		}
		if (!Pattern.matches("[1-9][0-9]*\\.?[0-9]*", heightField.getText())) {
			heightErrorMessage.setVisible(true);
			result = false;
		}else{
			heightErrorMessage.setVisible(false);
		}
		return result;
	}

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void handleSave() {
		Main.getLogger().info("A felhasználó menyomta a Mentés gombot.");
		if (checkFields()) {
			main.getPersonByUserName(person.getUserName()).setSurName(surNameField.getText());
			main.getPersonByUserName(person.getUserName()).setFirstName(firstNameField.getText());
			main.getPersonByUserName(person.getUserName()).setAge(Integer.parseInt(ageField.getText()));
			main.getPersonByUserName(person.getUserName()).setWeight(Double.valueOf(weightField.getText()));
			main.getPersonByUserName(person.getUserName()).setHeight(Double.valueOf(heightField.getText()));
			if (goalCBox.getSelectionModel().selectedItemProperty().getValue() == "Súlycsökkentés")
				main.getPersonByUserName(person.getUserName()).setGoal(Goals.LOSING_WEIGHT);
			else
				main.getPersonByUserName(person.getUserName()).setGoal(Goals.KEEPING_WEIGHT);
			Services.setEssentials(main.getPersonByUserName(person.getUserName()));
			stage.close();
		}
	}

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void handleCancel() {
		stage.close();
	}
}
