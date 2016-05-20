package hu.unideb.inf.prt.KaloriaSzamlalo.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import hu.unideb.inf.prt.KaloriaSzamlalo.Main;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Person;

/**
 * Felhasználók betöltéséért és perzisztlásáért feleős osztály.
 * 
 * @author Szabó Nándor Attila
 *
 */
public class PersonDAOImpl implements PersonDAO {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hu.unideb.inf.prt.KaloriaSzamlalo.io.PersonDAO#loadPeople(hu.unideb.inf.
	 * prt.KaloriaSzamlalo.Main)
	 */
	@Override
	public void loadPeople() {
		Gson gson = new GsonBuilder().create();
		Path path = Paths.get(System.getProperty("user.home"), "save");
		if (path.toFile().exists()) {
			File dir = path.toFile();
			for (File f : dir.listFiles()) {
				try {
					Person person = gson.fromJson(new FileReader(f.getPath()), Person.class);
					if (person != null) {
						Main.getPeople().add(person);
					}

				} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
					Main.getLogger().error("Nem sikerült a felhasználók betöltés", e);
				}
			}
		} else {

			path.toFile().mkdir();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hu.unideb.inf.prt.KaloriaSzamlalo.io.PersonDAO#savePeople(java.util.List)
	 */
	@Override
	public void savePeople(List<Person> people) {
		Gson gson = new GsonBuilder().create();

		for (Person person : people) {

			try {
				
				if (person.isRemoved()) {
					Main.getLogger().info("Törölt állomány elérési útvonala: "
							+ Paths.get(System.getProperty("user.home"), "save", person.getUserName() + ".json"));
					Paths.get(System.getProperty("user.home"), "save", person.getUserName() + ".json").toFile()
							.delete();
				} else {
					Path path = Paths.get(System.getProperty("user.home"), "save", person.getUserName() + ".json");
					FileWriter fileWriter = new FileWriter(path.toFile());
					gson.toJson(person, fileWriter);
					fileWriter.close();
				}
			} catch (JsonIOException | IOException e) {
				Main.getLogger().error("Nem sikerült elmenteni a felhasználókat", e);
			}

		}
	}

}
