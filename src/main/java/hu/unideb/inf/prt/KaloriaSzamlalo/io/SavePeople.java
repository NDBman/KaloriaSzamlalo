package hu.unideb.inf.prt.KaloriaSzamlalo.io;

import java.util.List;

import hu.unideb.inf.prt.KaloriaSzamlalo.model.Person;

@FunctionalInterface
public interface SavePeople {

	public void savePeople(List<Person> people);
}
