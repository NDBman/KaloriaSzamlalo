package hu.unideb.inf.prt.KaloriaSzamlalo.controller;

import hu.unideb.inf.prt.KaloriaSzamlalo.Main;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Person;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class EntryController {

	private Stage stage;
	private Main main;
	private Person person;

	@FXML
	private void setPerson() {
		if ((person = personTable.getSelectionModel().selectedItemProperty().getValue()) != null)
			;

	}
	@SuppressWarnings("checkstyle:javadocmethod")
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	@SuppressWarnings("checkstyle:javadocmethod")
	public void setMain(Main main) {
		this.main = main;
		personTable.setItems(this.main.getPeople());
	}

	@FXML
	private TableView<Person> personTable;
	@FXML
	private TableColumn<Person, String> personColumn;

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void initialize() {
		personColumn.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getUserName()));
	}

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void handleOk() {
		if (person != null) {
			main.createRootPane(person);
			stage.close();
		}else{
			Main.getLogger().info("Nincs felhasználó kiválasztva!");
		}
	}

	@FXML
	@SuppressWarnings("checkstyle:javadocmethod")
	public void handleReg() {
		Main.getLogger().info("A felhasználó megnyomta a Regisztráció gombot.");
		stage.close();
		main.createRegistrationView();
	}
}
