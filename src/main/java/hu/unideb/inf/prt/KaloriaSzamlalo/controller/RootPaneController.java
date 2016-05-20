package hu.unideb.inf.prt.KaloriaSzamlalo.controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import hu.unideb.inf.prt.KaloriaSzamlalo.Main;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Person;
import hu.unideb.inf.prt.KaloriaSzamlalo.services.Services;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

/**
 * @author Szabó Nándor Attila
 *
 */
public class RootPaneController {

	private Main main;
	private Stage stage;
	private Person person;
	private NumberFormat formatter = new DecimalFormat("#0.00");

	@SuppressWarnings("checkstyle:javadocmethod")
	public void setPerson(Person person) {
		this.person = person;
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void setMain(Main main) {
		this.main = main;
	}

	@SuppressWarnings("checkstyle:javadocmethod")
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

	@SuppressWarnings("checkstyle:javadocmethod")
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

	@SuppressWarnings("checkstyle:javadocmethod")
	public void setStageFocusListener(Stage stage) {
		stage.focusedProperty().addListener((o, oldaValue, newValue) -> setLabels());
	}

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void addingKcalButtton() {
		Main.getLogger().info("A felhasználó megnyomta a Kalória hozzáadása gombot.");
		main.createAddingKcal(person);
	}

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void handleDataEditor() {
		Main.getLogger().info("A felhasználó megnyomta a Saját adatok szerkesztése gombot.");
		main.createEditDataView(person);
	}

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void handleWeekStatitcs() {
		Main.getLogger().info("A felhasználó megnyomta a Heti statisztika megtekintése gombot.");
		main.createWeekStatics(person);
	}

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void handleLogOut() {
		Main.getLogger().info("A felhasználó megnyomta a Kijelentkezés gombot.");
		stage.close();
		main.createEntry();
	}

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void handleUndo() {
		Main.getLogger().info("A felhasználó megynomta a Visszavonás gombot.");
		Services.undo(person);
		setLabels();
	}
	
	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void handleDelete(){
		person.setRemoved(true);
		stage.close();
		main.createEntry();
	}

}
