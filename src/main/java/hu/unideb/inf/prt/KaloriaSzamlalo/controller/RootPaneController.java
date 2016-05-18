package hu.unideb.inf.prt.KaloriaSzamlalo.controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import hu.unideb.inf.prt.KaloriaSzamlalo.Main;
import hu.unideb.inf.prt.KaloriaSzamlalo.comput.Comput;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class RootPaneController {

	private Main main;
	private Stage stage;
	private Person person;
	private NumberFormat formatter = new DecimalFormat("#0.00");

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	private Label nameLabel;

	@FXML
	private ProgressBar chProgressBar;
	@FXML
	private ProgressBar proteinProgressBar;
	@FXML
	private ProgressBar fatProgressBar;
	@FXML
	private ProgressBar allProgressBar;

	@FXML
	private Label chLabel;
	@FXML
	private Label proteinLabel;
	@FXML
	private Label fatLabel;
	@FXML
	private Label allLabel;

	public void setLabels() {
		nameLabel.setText(person.getFirstName());
		chLabel.setText(formatter.format(person.getGotCH()) + "/" + formatter.format(person.getcH()) + " (gramm)");
		proteinLabel.setText(
				formatter.format(person.getGotProtein()) + "/" + formatter.format(person.getProtein()) + " (gramm)");
		fatLabel.setText(formatter.format(person.getGotFat()) + "/" + formatter.format(person.getFat()) + " (gramm)");
		allLabel.setText(formatter.format(person.getGotBMR()) + "/" + formatter.format(person.getbMR()) + " (gramm)");
		chProgressBar.setProgress(person.getGotCH() / person.getcH());
		proteinProgressBar.setProgress(person.getGotProtein() / person.getProtein());
		fatProgressBar.setProgress(person.getGotFat() / person.getFat());
		allProgressBar.setProgress(person.getGotBMR() / person.getbMR());

	}

	public void setStageFocusListener(Stage stage) {
		stage.focusedProperty().addListener((o, oldaValue, newValue) -> setLabels());
	}

	@FXML
	public void addingKcalButtton() {
		main.createAddingKcal(person);
	}

	@FXML
	public void handleDataEditor() {
		main.createEditDataView(person);
	}

	@FXML
	public void handleWeekStatitcs() {
		main.createWeekStatics(person);
	}

	@FXML
	public void handleLogOut() {
		stage.close();
		main.createEntry();
	}

	@FXML
	public void handleUndo() {
		Comput.undo(person);
		setLabels();
	}

}
