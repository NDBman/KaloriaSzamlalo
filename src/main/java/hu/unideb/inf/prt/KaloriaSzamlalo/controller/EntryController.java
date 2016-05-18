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

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setMain(Main main) {
		this.main = main;
		personTable.setItems(this.main.getPeople());
	}

	@FXML
	private TableView<Person> personTable;
	@FXML
	private TableColumn<Person, String> personColumn;

	@FXML
	public void initialize() {
		personColumn.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getUserName()));
	}

	@FXML
	public void handleOk() {
		if (person != null) {
			main.createRootPane(person);
			stage.close();
		}
	}

	@FXML
	public void handleReg() {
		stage.close();
		main.createRegistrationView();
	}
}
