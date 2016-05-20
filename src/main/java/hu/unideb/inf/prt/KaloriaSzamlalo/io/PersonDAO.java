package hu.unideb.inf.prt.KaloriaSzamlalo.io;

import java.util.List;

import hu.unideb.inf.prt.KaloriaSzamlalo.model.Person;

/**
 * @author Szabó Nándor Attila
 *
 */
public interface PersonDAO {

	/**
	 * Feladata a felhasználók betöltése a {@code Main} osztály {@code people}
	 * listájába a felhasználókat. Json állományokat olvas.
	 *
	 */
	public void loadPeople();

	/**
	 * Feladata az argumentumként megadott listában szereplő {@code Person}
	 * példányokat lementeni Json állományokba. Valamint felismeri, hogy ha egy
	 * felhasználó {@code removed} attribútumát már {@code true}-ra állítottuk,
	 * ekkor törli a felhasználóhoz tartozó állományt.
	 * 
	 * @param people
	 *            {@code Person} osztály példányait tartalmazó lista.
	 */
	public void savePeople(List<Person> people);
}
