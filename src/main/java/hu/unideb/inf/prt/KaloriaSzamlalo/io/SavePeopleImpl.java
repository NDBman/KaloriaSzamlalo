package hu.unideb.inf.prt.KaloriaSzamlalo.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.Ignore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import hu.unideb.inf.prt.KaloriaSzamlalo.model.Person;

public class SavePeopleImpl implements SavePeople {

	@Override
	@Ignore
	public void savePeople(List<Person> people) {
		Gson gson = new GsonBuilder().create();
		
		for (Person person : people) {
			try {
				
				FileWriter fileWriter = new FileWriter(
						new File(SavePeopleImpl.class.getResource("/save/").getFile() + person.getUserName() + ".json"));
				gson.toJson(person, fileWriter);
				fileWriter.close();
			} catch (JsonIOException | IOException e) {
				e.printStackTrace();
			}

		}
	}

}
