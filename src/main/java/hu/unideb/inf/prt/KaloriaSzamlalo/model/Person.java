package hu.unideb.inf.prt.KaloriaSzamlalo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import hu.unideb.inf.prt.KaloriaSzamlalo.comput.Comput;

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
	 * Üres konstruktora a Person osztálynak.
	 */

	private Double gotCH;

	private Double gotProtein;

	private Double gotFat;

	private Double bMR;

	private Double gotBMR;

	private List<Adding> addings = new ArrayList<Adding>();

	private LocalDate today;

	private List<DailyGotNutreints> week = new ArrayList<DailyGotNutreints>();

	/**
	 * Üres konstruktora a Person osztálynak.
	 */
	public Person() {
	}

	/**
	 * Konstruktora a Person osztálynak.
	 * 
	 * @param name
	 *            A felhasználó neve.
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

		Comput.setEssentials(this);
	}

	/**
	 * Visszaadja a felasználó felhasználónevét.
	 * 
	 * @return userName A felhasználónév.
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
	 * @return name A felhazsnáló keresztneve.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Beállítja a felhasználó nevét a megadott keresztnévre.
	 * 
	 * @param name
	 *            A felhasználó keresztneve.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Visszaadja a felhasználó vezetéknevét.
	 * 
	 * @return surName A felhasználó vezetékneve.
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
	 * @return height A felhasználó magassága cm-ben.
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
	 * @return weight A felhasználó tetsúlya kg-ban.
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
	 * @return age A felhasználó életkora.
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
	 * @return gender A felhasznló neme.
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
	 * @return goal A felhasználó célja.
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
	 * @return cH a szükséges napi szénhidrát bevitel.
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
	 * @return protein A szükséges napi fehérje bevitel.
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
	 * @return fat A szükséges napi zsír bevitel.
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

	public Double getGotCH() {
		return gotCH;
	}

	public Double getGotProtein() {
		return gotProtein;
	}

	public Double getGotFat() {
		return gotFat;
	}

	public void setGotCH(Double gotCH) {
		this.gotCH = gotCH;
	}

	public void setGotProtein(Double gotProtein) {
		this.gotProtein = gotProtein;
	}

	public void setGotFat(Double gotFat) {
		this.gotFat = gotFat;
	}

	public Double getbMR() {
		return bMR;
	}

	public void setbMR(Double bMR) {
		this.bMR = bMR;
	}

	public Double getGotBMR() {
		return gotBMR;
	}

	public void setGotBMR(Double gotBMR) {
		this.gotBMR = gotBMR;
	}

	public List<Adding> getAddings() {
		return addings;
	}

	public LocalDate getToday() {
		return today;
	}

	public void setToday(LocalDate today) {
		this.today = today;
	}

	public List<DailyGotNutreints> getWeek() {
		return week;
	}

	public void setWeek(List<DailyGotNutreints> week) {
		this.week = week;
	}

}
