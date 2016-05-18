package hu.unideb.inf.prt.KaloriaSzamlalo.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import hu.unideb.inf.prt.KaloriaSzamlalo.Main;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Person;

public class PersonDAOImpl implements PersonDAO {

	public void loadPeople(Main main) {
		Gson gson = new GsonBuilder().create();
		File dir = new File(PersonDAOImpl.class.getResource("/save/").getFile());
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

}
