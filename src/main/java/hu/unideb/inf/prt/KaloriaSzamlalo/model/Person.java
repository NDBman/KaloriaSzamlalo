package hu.unideb.inf.prt.KaloriaSzamlalo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import hu.unideb.inf.prt.KaloriaSzamlalo.services.Services;

/**
 * A felhasználó adatait tároló osztály.
 * 
 * @author Szabó Nándor Attila
 *
 */
public class Person {

	/**
	 * A felhasználó által megadott felhaszálónév.
	 */
	private String userName;

	/**
	 * A felhasználó keresztneve.
	 */
	private String firstName;

	/**
	 * A felhasználó vezetékneve.
	 */
	private String surName;
	/**
	 * A felhasználó magassága cm-ben.
	 */
	private Double height;
	/**
	 * A felhazsnáló testsúlya kg-ban.
	 */
	private Double weight;
	/**
	 * A felhasználó életkora.
	 */
	private Integer age;

	/**
	 * A felhasználó neme.
	 */
	private Gender gender;

	/**
	 * A felhazsnáló célja. Lehet ez súlycsökkentés, súlytartás és
	 * súlygyarapodás.
	 */
	private Goals goal;

	/**
	 * A felhasználó szükséges napi szénhidrát bevitele.
	 */
	private Double cH;

	/**
	 * A felhasználó szükséges napi fehérje bevitele.
	 */
	private Double protein;

	/**
	 * A felhasználó szükséges napi zsír bevitele.
	 */
	private Double fat;

	/**
	 * A felhasználó a nap folyamán már bevitt szénhidrát mennyisége.
	 */
	private Double gotCH;

	/**
	 * A felhasználó a nap folyamán már bevitt fehérje mennyisége.
	 */
	private Double gotProtein;

	/**
	 * A felashználó a nap folyamán már bevitt zsír mennyisége.
	 */
	private Double gotFat;

	/**
	 * A felhasználó napi alapanyagcsere szükséglete.
	 */
	private Double bMR;

	/**
	 * A felhasználó a nap folyamán már bevitt alapanyagcsere mennysiége.
	 */
	private Double gotBMR;

	/**
	 * A felhasználónak a program inditása óta bevitt kalória mennyiségeit és típusait tartalmazó lista.
	 */
	private List<Adding> addings = new ArrayList<Adding>();

	/**
	 * Azt a dátumot tartalmazza, hogy mikor lett példányosítva a felhasználó.
	 */
	private LocalDate today;

	/**
	 * A felhasználó heti statisztikáját tarlmazó lista.
	 */
	private List<DailyGotNutreints> week = new ArrayList<DailyGotNutreints>();

	/**
	 * Jelzi, hogy a felhasználó törölve lett-e.
	 */
	private boolean removed = false;

	/**
	 * Üres konstruktora a Person osztálynak.
	 */
	public Person() {
	}

	/**
	 * Konstruktora a Person osztálynak.
	 * 
	 * @param userName
	 *            A felhasználó által megadott felhasználónév.
	 * @param firstName
	 *            A felhasználó keresztneve.
	 * @param surName
	 *            A felhasználó vezetékneve.
	 * @param height
	 *            A felhasználó magassága cm-ben.
	 * @param weight
	 *            A felhazsnáló súlya kg-ban.
	 * @param age
	 *            A felhazsnáló életkora.
	 * @param gender
	 *            A felhasználó neme.
	 * @param goal
	 *            A felahsználó célja.
	 */
	public Person(String userName, String firstName, String surName, Double height, Double weight, Integer age,
			Gender gender, Goals goal) {
		this.userName = userName;
		this.firstName = firstName;
		this.surName = surName;
		this.height = height;
		this.weight = weight;
		this.age = age;
		this.gender = gender;
		this.goal = goal;
		gotCH = 0.0;
		gotProtein = 0.0;
		gotFat = 0.0;
		gotBMR = 0.0;
		today = LocalDate.now();
		Services.setEssentials(this);
	}

	/**
	 * Visszaadja a felasználó felhasználónevét.
	 * 
	 * @return userName A felhasználónévét.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Beállítja a felhasználó felhasználó nevét.
	 * 
	 * @param userName
	 *            A felhasználónév.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Visszaadja a felhasználó keresztnevét.
	 * 
	 * @return name A felhazsnáló keresztnevét.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Beállítja a felhasználó nevét a megadott keresztnévre.
	 * 
	 * @param firstName
	 *            A felhasználó keresztneve.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Visszaadja a felhasználó vezetéknevét.
	 * 
	 * @return surName A felhasználó vezetéknevét.
	 */
	public String getSurName() {
		return surName;
	}

	/**
	 * Beállítja a felhasználó a megadott vezetéknevét.
	 * 
	 * @param surName
	 *            A felhasználó vezetékneve.
	 */
	public void setSurName(String surName) {
		this.surName = surName;
	}

	/**
	 * Visszaadja a felhasználó magasságát cm-ben.
	 * 
	 * @return height A felhasználó magasságát cm-ben.
	 */
	public Double getHeight() {
		return height;
	}

	/**
	 * Beállítija a felhasználó magasságát cm-ben.
	 * 
	 * @param height
	 *            A felhasználó magassága cm-ben.
	 */
	public void setHeight(Double height) {
		this.height = height;
	}

	/**
	 * Visszaadja a felhasználó testsúlyát kg-ban.
	 * 
	 * @return weight A felhasználó tetsúlyát kg-ban.
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * Beállítja a felhazsnáló testsúlyát kg-ban.
	 * 
	 * @param weight
	 *            A felhasználó testsúlya kg-ban.
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	/**
	 * Visszaadja a felhasználó életkorát.
	 * 
	 * @return age A felhasználó életkorát.
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * Beállítja a felhasználó életkorát.
	 * 
	 * @param age
	 *            A felahsználó életkora.
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * Visszaadja a felhasználó nemét.
	 * 
	 * @return gender A felhasznló nemét.
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * Beállítja a felhasználó nemét.
	 * 
	 * @param gender
	 *            A felhasználó neme.
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * Visszaadja a felahsználó célját.
	 * 
	 * @return goal A felhasználó célját.
	 */
	public Goals getGoal() {
		return goal;
	}

	/**
	 * Beállítja a felahsználó célját.
	 * 
	 * @param goal
	 *            A felhasználó célja.
	 */
	public void setGoal(Goals goal) {
		this.goal = goal;
	}

	/**
	 * Visszaadja a felhasználó szükséges napi szénhidrát bevitelét.
	 * 
	 * @return cH a szükséges napi szénhidrát bevitelt.
	 */
	public Double getcH() {
		return cH;
	}

	/**
	 * Beállítja a szükséges napi szénhidrát bevitelt.
	 * 
	 * @param cH
	 *            A szükséges napi szénhidrát bevitel.
	 */
	public void setcH(Double cH) {
		this.cH = cH;
	}

	/**
	 * Visszaadja a szükséges napi fehérje bevitelt.
	 * 
	 * @return protein A szükséges napi fehérje bevitelt.
	 */
	public Double getProtein() {
		return protein;
	}

	/**
	 * Beállítja a szükséges napi fehérje bevitelt.
	 * 
	 * @param protein
	 *            A szükséges napi fehérje bevitel.
	 */
	public void setProtein(Double protein) {
		this.protein = protein;
	}

	/**
	 * Visszaadja a szükséges napi zsír bevtielt.
	 * 
	 * @return fat A szükséges napi zsír bevitelt.
	 */
	public Double getFat() {
		return fat;
	}

	/**
	 * Beállítja a szükséges napi zsír bevitelt.
	 * 
	 * @param fat
	 *            A szükséges napi zsír bevitel.
	 */
	public void setFat(Double fat) {
		this.fat = fat;
	}

	/**
	 * Vissza adja a nap folyamán már bevitt szénhidrát mennyiséget.
	 * 
	 * @return A nap folyamán már bevitt szénhidrát mennyiséget.
	 */
	public Double getGotCH() {
		return gotCH;
	}

	/**
	 * Visszaadja a nap folyamán már bevitt fehérje mennyiséget.
	 * 
	 * @return A nap folyamán már bevitt mennyiséget.
	 */
	public Double getGotProtein() {
		return gotProtein;
	}

	/**
	 * Visszaadja a nap folyamán már bevitt zsír mennyiséget.
	 * 
	 * @return A nap folyamán már bevitt zsír mennyiséget.
	 */
	public Double getGotFat() {
		return gotFat;
	}

	/**
	 * Beállítja a nap folyamán már bevitt szénhidrát mennyiséget.
	 * 
	 * @param gotCH
	 *            A nap folyamán már bevitt szénhidrát mennyiség.
	 */
	public void setGotCH(Double gotCH) {
		this.gotCH = gotCH;
	}

	/**
	 * Beállítja a nap folyamán már bevitt fehérje mennyiséget.
	 * 
	 * @param gotProtein
	 *            A nap folyamán már bevitt fehérje mennyiség.
	 */
	public void setGotProtein(Double gotProtein) {
		this.gotProtein = gotProtein;
	}

	/**
	 * Beállítja a nap folyamán már bevitt zsír mennyiséget.
	 * 
	 * @param gotFat
	 *            A nap folyamán már bevitt zsír mennyiség.
	 */
	public void setGotFat(Double gotFat) {
		this.gotFat = gotFat;
	}

	/**
	 * Visszaadja a napi alapanyagcsere szükségletet.
	 * 
	 * @return Az napi alapanyagcsere szükségletet.
	 */
	public Double getbMR() {
		return bMR;
	}

	/**
	 * Beállítja a napi alapanyagcsere szükségletet.
	 * 
	 * @param bMR
	 *            A napi alapanyagcsereszükséglet.
	 */
	public void setbMR(Double bMR) {
		this.bMR = bMR;
	}

	/**
	 * Visszaadja a nap folyamán már bevitt alapanyagcsere szükségletet.
	 * 
	 * @return A nap folyamán bevitt alapanyagcsere szükségletet.
	 */
	public Double getGotBMR() {
		return gotBMR;
	}

	/**
	 * Beállítja a nap folyamán már bevitt alapanyagcsere szükségletet.
	 * 
	 * @param gotBMR
	 *            A nap folyamán már bevitt alapanyagcsere szükséglet.
	 */
	public void setGotBMR(Double gotBMR) {
		this.gotBMR = gotBMR;
	}

	/**
	 * Visszaadja a program indítása óta beírt tápanyag bevitelek listáját.
	 * 
	 * @return A tápanyag bevitelek listáját.
	 */
	public List<Adding> getAddings() {
		return addings;
	}

	/**
	 * Visszaadja melyik napon tölöttük be a promba a felhasználót utoljára.
	 * 
	 * @return Az utolsó programba töltés dátumát.
	 */
	public LocalDate getToday() {
		return today;
	}

	/**
	 * Visszaadja az utolsó programba betöltés napjának dátumát.
	 * 
	 * @param today
	 *            Az utolsó programba betöltés dátuma.
	 */
	public void setToday(LocalDate today) {
		this.today = today;
	}

	/**
	 * Visszaadja Az elmúlt hét napban bevitt kalórimennyiségek egy listáját.
	 * 
	 * @return Az elmúlt hét napban bevitt kalóriamennyiségek egy listáját.
	 */
	public List<DailyGotNutreints> getWeek() {
		return week;
	}

	/**
	 * Igaz értéket ad vissza ha törölve lett a felhasználó. Ez jelzi a
	 * {@link hu.unideb.inf.prt.KaloriaSzamlalo.io.PersonDAOImpl} számára, hogy
	 * perzisztáláskor ne mentse el és a már meglévő mentést törölje.
	 * 
	 * @return Hogy törölve van-e a felhasználó.
	 */
	public boolean isRemoved() {
		return removed;
	}

	/**
	 * Beállítja, hogy a felhasznó törölve van-e.
	 * 
	 * @param removed A felhasználó töröltségét jelző értéket (igaz / hamis).
	 */
	public void setRemoved(boolean removed) {
		this.removed = removed;
	}

	@Override
	public String toString() {
		return "Person [userName=" + userName + ", firstName=" + firstName + ", surName=" + surName + ", height="
				+ height + ", weight=" + weight + ", age=" + age + ", gender=" + gender + ", goal=" + goal + ", cH="
				+ cH + ", protein=" + protein + ", fat=" + fat + ", gotCH=" + gotCH + ", gotProtein=" + gotProtein
				+ ", gotFat=" + gotFat + ", bMR=" + bMR + ", gotBMR=" + gotBMR + ", addings=" + addings + ", today="
				+ today + ", week=" + week + ", removed=" + removed + "]";
	}
	
	

}
