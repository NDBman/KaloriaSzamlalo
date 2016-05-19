package hu.unideb.inf.prt.KaloriaSzamlalo.model;

import java.time.LocalDate;

/**
 * Ez az osztály a felhasználó által egy napon bevitt tápanyagait menti el.
 * Azért szükséges ez, hogy heti statisztika készülhessen a felhasználó tápanyagbeviteléről.
 * 
 * @author Szabó Nándor Attila
 *
 */
public class DailyGotNutreints {

	private Double gotCH;
	private Double gotProtein;
	private Double gotFat;
	private LocalDate date;

	/**
	 * Az osztály üres konstruktora.
	 * Teszteléshez szükséges.
	 */
	public DailyGotNutreints() {
	}

	/**
	 * Az osztály konstrukora. Beállítja a nap alatt bevitt fehérje, szénhidrát és zsír mennyiséget és elmenti a dátumot is.
	 * 
	 * @param gotCH A bevitt szénhidrát mennyiség.
	 * @param gotProtein A bevitt fehérje mennyiség.
	 * @param gorFat A bevitt zsír mennyiség.
	 * @param date A tápanyagok bevitelének aznapi dátuma.
	 */
	public DailyGotNutreints(Double gotCH, Double gotProtein, Double gorFat, LocalDate date) {
		this.gotCH = gotCH;
		this.gotProtein = gotProtein;
		this.gotFat = gorFat;
		this.date = date;
	}

	/**
	 * Visszaadja a nap folyamán bevitt szénhidrát mennyiséget.
	 * 
	 * @return A nap folyamát bevitt szénhidrát mennyiséget.
	 */
	public Double getGotCH() {
		return gotCH;
	}

	/**
	 * Beállítja a nap folyamán bevitt szénhidrát mennyiséget.
	 * 
	 * @param gotCH A nap folyamán bevitt szénhidrát mennyiség.
	 */
	public void setGotCH(Double gotCH) {
		this.gotCH = gotCH;
	}

	/**
	 * Visszaadja a nap folyamán bevitt fehérje mennyiséget.
	 * 
	 * @return A nap folyamán bevitt fehérje mennyiséget.
	 */
	public Double getGotProtein() {
		return gotProtein;
	}

	/**
	 * Beállítja a nap folyamán bevitt fehérje mennyiséget.
	 * 
	 * @param gotProtein A nap folyamán bevitt fehérje mennyiség.
	 */
	public void setGotProtein(Double gotProtein) {
		this.gotProtein = gotProtein;
	}

	/**
	 * Visszaadja a nap folyamán bevitt zsír mennyiséget.
	 * 
	 * @return A nap folyamán bevitt zsír mennyiséget.
	 */
	public Double getGotFat() {
		return gotFat;
	}

	/**
	 * Beállítja a nap folyamán bevitt zsír mennyiséget.
	 * 
	 * @param gotFat A nap folyamán bevitt zsír mennyiség.
	 */
	public void setGotFat(Double gotFat) {
		this.gotFat = gotFat;
	}

	/**
	 * Visszaadja a nap dátumát.
	 * 
	 * @return A nap dátumát.
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Beállítja a nap dátumát.
	 * 
	 * @param date A nap dátuma.
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

}
