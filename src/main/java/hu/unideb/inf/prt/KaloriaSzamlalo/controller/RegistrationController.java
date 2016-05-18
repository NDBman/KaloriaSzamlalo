package hu.unideb.inf.prt.KaloriaSzamlalo.controller;

import java.util.regex.Pattern;

import hu.unideb.inf.prt.KaloriaSzamlalo.Main;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Gender;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Goals;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrationController {

	private Main main;
	private Stage stage;
	private Person person;
	private ObservableList<String> genders = FXCollections.observableArrayList();
	private ObservableList<String> goals = FXCollections.observableArrayList();

	@FXML
	public void initialize() {
		genders.add("Nő");
		genders.add("Férfi");
		genderCBox.setItems(genders);

		goals.add("Súlycsökkentés");
		goals.add("Súlytartás");
		goalCBox.setItems(goals);
	}

	@FXML
	private Label genderErrorMessage;

	@FXML
	private Label ageErrorMessage;

	@FXML
	private Label weightErrorMessage;

	@FXML
	private Label heightErrorMessage;

	@FXML
	private Label goalErrorMessage;

	@FXML
	private TextField userField;

	@FXML
	private TextField surNameField;

	@FXML
	private TextField firstNameField;

	@FXML
	private ComboBox<String> genderCBox;

	@FXML
	private TextField ageField;

	@FXML
	private TextField weightField;

	@FXML
	private TextField heightField;

	@FXML
	private ComboBox<String> goalCBox;

	public void setMain(Main main) {
		this.main = main;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	public void handleCancel() {
		stage.close();
		main.createEntry();
	}

	private boolean checkFields() {
		boolean result = true;

		if (genderCBox.getSelectionModel().selectedItemProperty().getValue() == null) {
			genderErrorMessage.setVisible(true);
			result = false;
		}
		if (goalCBox.getSelectionModel().selectedItemProperty().getValue() == null) {
			goalErrorMessage.setVisible(true);
			result = false;
		}
		if (!Pattern.matches("[1-9][0-9]*", ageField.getText())) {
			ageErrorMessage.setVisible(true);
			result = false;
		}else if(Integer.parseInt(ageField.getText()) < 10){
			result = false;
		}
		if (!Pattern.matches("[1-9][0-9]*.?[0-9]*", weightField.getText())) {
			weightErrorMessage.setVisible(true);
			result = false;
		}
		if(!Pattern.matches("[1-9][0-9]*.?[0-9]*", heightField.getText())){
			heightErrorMessage.setVisible(true);
			result = false;
		}
		if(userField.getText().isEmpty() || surNameField.getText().isEmpty() || firstNameField.getText().isEmpty()){
			result = false;
		}
		return result;
	}

	@FXML
	public void handleRegistration() {
		if (checkFields()) {
			Gender gender = (genderCBox.getSelectionModel().selectedIndexProperty().getValue().toString() == "Férfi")
					? (gender = Gender.MALE) : (gender = Gender.FEMALE);
			Goals goal = (goalCBox.getSelectionModel().selectedIndexProperty().getValue()
					.toString() == "Súlycsökkentés") ? (goal = Goals.LOSING_WEIGHT) : (goal = Goals.KEEPING_WEIGHT);
			person = new Person(userField.getText(), firstNameField.getText(), surNameField.getText(),
					Double.valueOf(heightField.getText()), Double.valueOf(weightField.getText()),
					Integer.parseInt(ageField.getText()), gender, goal);
			main.getPeople().add(person);
			
			main.createEntry();
			stage.close();
		}
		
	}

}
