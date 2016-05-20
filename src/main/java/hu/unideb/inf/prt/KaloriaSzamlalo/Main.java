package hu.unideb.inf.prt.KaloriaSzamlalo;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.unideb.inf.prt.KaloriaSzamlalo.controller.AddingKcalController;
import hu.unideb.inf.prt.KaloriaSzamlalo.controller.EditDataController;
import hu.unideb.inf.prt.KaloriaSzamlalo.controller.EntryController;
import hu.unideb.inf.prt.KaloriaSzamlalo.controller.RegistrationController;
import hu.unideb.inf.prt.KaloriaSzamlalo.controller.RootPaneController;
import hu.unideb.inf.prt.KaloriaSzamlalo.controller.WeekStaticsController;
import hu.unideb.inf.prt.KaloriaSzamlalo.io.PersonDAOImpl;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Person;
import hu.unideb.inf.prt.KaloriaSzamlalo.services.Services;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Szabó Nándor Attila
 *
 */
public class Main extends Application {

	private Stage primaryStage;

	private BorderPane rootPane;

	private static ObservableList<Person> people = FXCollections.observableArrayList();

	/**
	 * Visszaadja a Felhasználók listáját.
	 * 
	 * @return A felhasználók listáját.
	 */
	public static ObservableList<Person> getPeople() {
		return people;
	}

	private static Main main;

	private static Logger logger = LoggerFactory.getLogger(Main.class);

	/**
	 * Visszaadja a {@code Main} osztály loggerét.
	 * 
	 * @return A {@code Main} osztály loggerét.
	 */
	public static Logger getLogger() {
		return logger;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		main = new Main();

		PersonDAOImpl dao = new PersonDAOImpl();
		dao.loadPeople(null);

		Services.resetGotNutrients(Main.getPeople());
		for(Person person : getPeople()){
			Services.fillEmptyDays(person.getWeek());
		}
		main.primaryStage = primaryStage;
		main.primaryStage.setTitle("Kalória Számláló");
		createEntry();

	}

	/**
	 * A main függvény. Ez indítja el a grafikus felületet valamint annak
	 * leállásakor meghívja
	 * {@link hu.unideb.inf.prt.KaloriaSzamlalo.io.PersonDAOImpl#savePeople(java.util.List, java.nio.file.Path)}
	 * függvényt amely perzisztáztálja a felhasználókat.
	 * 
	 * @param args indításkor megadott argmentumtömb.
	 */
	public static void main(String args[]) {
		launch(args);
		Services.resetUndos(getPeople());
		PersonDAOImpl savePeople = new PersonDAOImpl();
		savePeople.savePeople(getPeople(),null);
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void createEntry() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/fxml/EntryView.fxml"));
		try {
			BorderPane entry = (BorderPane) loader.load();
			Stage stage = new Stage();
			stage.setTitle("Bejelentkezés");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(null);

			Scene scene = new Scene(entry);
			stage.setScene(scene);

			EntryController controller = loader.getController();
			controller.setStage(stage);
			controller.setMain(main);

			stage.setResizable(false);
			stage.show();

		} catch (IOException | IllegalStateException e) {
			logger.error("Nem találhato vagy nem hozzáférhető az EntryView.fxml", e);
		}

	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void createRootPane(Person person) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/fxml/RootPane.fxml"));
		try {
			rootPane = (BorderPane) loader.load();
			Scene scene = new Scene(rootPane);
			main.primaryStage.setScene(scene);
			RootPaneController controller = loader.getController();
			controller.setMain(main);
			controller.setStage(main.primaryStage);
			controller.setPerson(person);
			controller.setStageFocusListener(main.primaryStage);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (IOException | IllegalStateException e) {
			logger.error("Nem találhato vagy nem hozzáférhető az RootPane.fxml", e);
			
		}
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void createAddingKcal(Person person) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/fxml/AddingKcalView.fxml"));

		try {
			AnchorPane pane = (AnchorPane) loader.load();
			Stage stage = new Stage();
			stage.setTitle("Kalória hozzáadása");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(main.primaryStage);

			Scene scene = new Scene(pane);
			stage.setScene(scene);

			AddingKcalController controller = loader.getController();
			controller.setPerson(person);
			controller.setStage(stage);

			stage.setResizable(false);
			stage.show();

		} catch (IOException | IllegalStateException e) {
			logger.error("Nem találhato vagy nem hozzáférhető az AddingKcalView.fxml", e);
		}
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void createRegistrationView() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/fxml/RegistrationView.fxml"));
		try {
			BorderPane pane = (BorderPane) loader.load();
			Stage stage = new Stage();
			stage.setResizable(false);
			stage.setTitle("Regisztráció");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(null);

			RegistrationController controller = loader.getController();
			controller.setMain(main);
			controller.setStage(stage);

			Scene scene = new Scene(pane);
			stage.setScene(scene);

			stage.show();

		} catch (IOException | IllegalStateException e) {
			logger.error("Nem találhato vagy nem hozzáférhető az Registration.fxml", e);
		}
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void createEditDataView(Person person) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/fxml/EditDataView.fxml"));
		try {
			BorderPane pane = (BorderPane) loader.load();
			Stage stage = new Stage();
			stage.setResizable(false);
			stage.setTitle("Adatok szerkesztése");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(main.primaryStage);

			EditDataController controller = loader.getController();
			controller.setPerson(person);
			controller.setDatas();
			controller.setStage(stage);
			controller.setMain(main);

			Scene scene = new Scene(pane);
			stage.setScene(scene);

			stage.show();

		} catch (IOException | IllegalStateException e) {
			logger.error("Nem találhato vagy nem hozzáférhető az EditDataView.fxml", e);
		}
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	public void createWeekStatics(Person person) {

		Stage stage = new Stage();
		stage.setResizable(false);
		stage.setTitle("Heti Statisztika");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(main.primaryStage);

		WeekStaticsController controller = new WeekStaticsController();
		controller.setPerson(person);

		Scene scene = controller.setBars();
		stage.setScene(scene);
		stage.getScene().getStylesheets().add("/style/style.css");
		stage.show();
	}

	/**
	 * Megkeres egy felhasználót felhasználónév alapján és visszaadja azt.
	 * 
	 * @param name A felhasználó felhasználóneve.
	 * @return Egy {@code Person} típusú objektumot.
	 */
	public Person getPersonByUserName(String name) {
		return getPeople().stream().filter(p -> p.getUserName().equals(name)).findFirst().orElse(null);
	}

}
