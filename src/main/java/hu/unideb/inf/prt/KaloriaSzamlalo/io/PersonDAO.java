package hu.unideb.inf.prt.KaloriaSzamlalo.io;

import hu.unideb.inf.prt.KaloriaSzamlalo.Main;

@FunctionalInterface
public interface PersonDAO {

	public void loadPeople(Main main);
}
