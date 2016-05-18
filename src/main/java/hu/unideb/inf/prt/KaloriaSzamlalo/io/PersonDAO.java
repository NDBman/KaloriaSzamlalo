package hu.unideb.inf.prt.KaloriaSzamlalo.io;

import java.util.List;

import hu.unideb.inf.prt.KaloriaSzamlalo.Main;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Person;


public interface PersonDAO {

	public void loadPeople(Main main);
	public void savePeople(List<Person> people);
}
