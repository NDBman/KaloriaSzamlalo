package hu.unideb.inf.prt.KaloriaSzamlalo.io;

import java.util.List;

import hu.unideb.inf.prt.KaloriaSzamlalo.model.Person;


/**
 * @author Szabó Nándor Attila
 *
 */
public interface PersonDAO {

	/**
	 * Feladata a felhasználók betöltése a {@code Main} osztály {@code people} listájába a felhasználókat.
	 * Json állományokat olvas.
	 * 
	 * @param main A {@code Main} osztály egy példánya.
	 */
	public void loadPeople();
	/**
	 * Feladata az argumentumként megadott listában szereplő {@code Person} példányokat lementeni Json állományokba.
	 * 
	 * @param people {@code Person} osztály példányait tartalmazó lista.
	 */
	public void savePeople(List<Person> people);
}
