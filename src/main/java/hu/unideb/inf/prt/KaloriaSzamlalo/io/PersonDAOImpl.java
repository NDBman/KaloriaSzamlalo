package hu.unideb.inf.prt.KaloriaSzamlalo.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Ignore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import hu.unideb.inf.prt.KaloriaSzamlalo.Main;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Person;

public class PersonDAOImpl implements PersonDAO {

	public void loadPeople(Main main) {
		Gson gson = new GsonBuilder().create();
		Path path = Paths.get(System.getProperty("user.home"),"save");
		
		File dir = path.toFile();
		dir.mkdir();
		for (File f : dir.listFiles()) {
			try {
				Person person = gson.fromJson(new FileReader(f.getPath()), Person.class);
				if (person != null)
					main.getPeople().add(person);
			} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
				e.printStackTrace();
			}
		}

	}
	
	@Override
	@Ignore
	public void savePeople(List<Person> people) {
		Gson gson = new GsonBuilder().create();
		
		for (Person person : people) {
			try {
				Path path = Paths.get(System.getProperty("user.home"),"save", person.getUserName() + ".json");
				FileWriter fileWriter = new FileWriter(
						path.toFile());
				gson.toJson(person, fileWriter);
				fileWriter.close();
			} catch (JsonIOException | IOException e) {
				e.printStackTrace();
			}

		}
	}

}
