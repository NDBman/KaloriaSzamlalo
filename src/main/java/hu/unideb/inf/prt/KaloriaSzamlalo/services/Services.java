package hu.unideb.inf.prt.KaloriaSzamlalo.services;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.time.temporal.ChronoUnit;

import hu.unideb.inf.prt.KaloriaSzamlalo.Main;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Adding;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.DailyGotNutreints;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Gender;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Goals;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Nutrients;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Person;

/**
 * Ez az osztály a program szolgáltatásait végzi.
 * 
 * @author Szabó Nándor.
 *
 */
public class Services {

	/**
	 * Egy grammnyi szénhidrát kalória értéke.
	 */
	private static final Double oneGCHKcal = 4.1;
	/**
	 * Egy grammnyi fehérje kalória értéke.
	 */
	private static final Double oneGProteinKcal = 4.1;
	/**
	 * Egy grammnyi ssír kalória értéke.
	 */
	private static final Double oneGFatKcal = 9.3;
	/**
	 * Az összkalóriaszükségletben a szénhidrát ennyi százalékban szerepel.
	 */
	private static final Double percentageOfCH = 0.48;
	/**
	 * Az összkalóriaszükségletben a fehérje ennyi százalékban szerepel.
	 */
	private static final Double percentageOfProtein = 0.32;
	/**
	 * Az összkalóriaszükséglteben a zsír ennyi százalékban szerepel.
	 */
	private static final Double percentageOfFat = 0.2;

	/**
	 * Kiszámolja és visszaadja a felhasználó napi energiszükségletét. Az
	 * Egészségügyi Világszervezet (WHO) által ajánlott táblázat szerint. Olvass
	 * tovább: http://czinadora-hu.webnode.hu/products/alapanyagcsere-es-napi-
	 * energiaszukseglet-kiszamitasa/
	 * 
	 * @param p
	 *            Egy felhasználó.
	 * @param weight
	 *            A felhasználó tömege. Ami lehet a valós tömege vagy az ideális
	 *            testsúlya, amit a {@link #getIdealWeight(Person)} metódus
	 *            számolja ki.
	 * @return A {@code p} felhasználó energiszükségletét.
	 */
	public static double getBMR(Person p, double weight) {

		if (p.getAge() >= 10 && p.getAge() <= 18)
			return (p.getGender() == Gender.MALE) ? (17.5 * weight + 651) : (12.2 * weight + 746);
		else if (p.getAge() > 18 && p.getAge() <= 30)
			return (p.getGender() == Gender.MALE) ? (15.3 * weight + 679) : (14.7 * weight + 496);
		else if (p.getAge() > 30 && p.getAge() <= 60)
			return (p.getGender() == Gender.MALE) ? (11.6 * weight + 879) : (8.7 * weight + 829);
		else if (p.getAge() > 60)
			return (p.getGender() == Gender.MALE) ? (13.5 * weight + 487) : (10.5 * weight + 596);

		return 0;
	}

	/**
	 * Visszaadja a felhasználó ideális testsúlyát.
	 * 
	 * @param p
	 *            Egy felhasználó. Aminek az ideális testsúlyát számoljuk.
	 * @return A {@code p} felhasználó ideális testsúlyát.
	 */
	public static double getIdealWeight(Person p) {
		return (p.getHeight() - 100) * 0.9;
	}

	/**
	 * Kiszámolja a napi szükséges kalóriamennyiséget, ha súlyt akarunk tartani.
	 * 
	 * @param p
	 *            Egy felhasználó. Aminek a szükséges kalóriamennyiségét
	 *            számoljuk.
	 * @param weight
	 *            A felhasználó tömege. Ami lehet a valós tömege vagy az ideális
	 *            testsúlya, amit a {@link #getIdealWeight(Person)} metódus
	 *            számolja ki.
	 * @return A {@code p} felhasználó napi kalóriszükségletét ha súlyt akar
	 *         tartani.
	 */
	public static double getKcalForKeepingWeight(Person p, double weight) {
		return getBMR(p, weight) * 1.6;
	}

	/**
	 * Kiszámolja a napi szükséges kalóriamennyiséget, ha súlyt akarunk
	 * csökkenteni.
	 * 
	 * @param p
	 *            Egy felhasználó. Aminek a szükséges kalóriamennyiségét
	 *            számoljuk.
	 * @param weight
	 *            A felhasználó tömege. Ami lehet a valós tömege vagy az ideális
	 *            testsúlya, amit a {@link #getIdealWeight(Person)} metódus
	 *            számolja ki.
	 * @return A {@code p} felhasználó napi kalóriszükségletét ha súlyt akar
	 *         csönteni.
	 */
	public static double getKcalForGentlyLosingWeight(Person p, double weight) {
		return getBMR(p, weight) * 1.1;
	}

	/**
	 * Kiszámolja a testzsír százalékot.
	 * 
	 * @param p
	 *            A felhasználó akinek a testzsír százalékát számoljuk.
	 * @return A {@code p} felhasználó testzsír százalékát.
	 */
	public static double getFatPercentage(Person p) {
		int gend = (p.getGender() == Gender.MALE) ? 1 : 0;
		return (1.2 * p.getWeight() / Math.pow(p.getHeight() / 100.0, 2)) + (0.23 * p.getAge()) - 5.4 - (10.8 * gend);
	}

	/**
	 * Beállítja a szükséges napi tápanyag mennyiségeket a felahsználónak.
	 * 
	 * @param p
	 *            A felhasználó akinek beállítjuk a szükséges napi
	 *            tápanyagszükségletét.
	 */
	public static void setEssentials(Person p) {
		double bmr;
		if (p.getGoal() == Goals.LOSING_WEIGHT)
			bmr = (getFatPercentage(p) > 30) ? getKcalForGentlyLosingWeight(p, getIdealWeight(p))
					: getKcalForGentlyLosingWeight(p, p.getWeight());
		else
			bmr = (getFatPercentage(p) > 30) ? getKcalForKeepingWeight(p, getIdealWeight(p))
					: getKcalForKeepingWeight(p, p.getWeight());

		p.setcH(bmr * percentageOfCH / oneGCHKcal);
		p.setProtein(bmr * percentageOfProtein / oneGProteinKcal);
		p.setFat(bmr * percentageOfFat / oneGFatKcal);
		p.setbMR(p.getcH() + p.getProtein() + p.getFat());

	}

	/**
	 * Visszavon egy a felahsználó által előzőleg beírt kalóriahozzáadást.
	 * Annyiszor vonhatunk vissza ahány hozzáadás volt a program indulása óta.
	 * 
	 * @param person
	 *            A felhasználó akinek a kalória hozzáadásait visszavonjuk.
	 */
	public static void undo(Person person) {

		Adding adding;
		int size;
		if ((size = person.getAddings().size()) > 0) {
			adding = person.getAddings().get(size - 1);
			switch (adding.getNutrient()) {
			case CH:
				person.setGotCH(person.getGotCH() - adding.getAmount());
				break;
			case PROTEIN:
				person.setGotProtein(person.getGotProtein() - adding.getAmount());
				break;
			case FAT:
				person.setGotFat(person.getGotFat() - adding.getAmount());
				break;
			default:

			}
			person.getAddings().remove(size - 1);
			updateGotBMR(person);
		}
	}

	/**
	 * Beállítja a felhasználó a nap folyamán már bevitt tápanyagszükségletét.
	 * Mindig meghívódik automatikus amikor a {@link #incGotFat(Double, Person)}
	 * , {@link #incGotCH(Double, Person)},
	 * {@link #incGotProtein(Double, Person)} vagy {@link #undo(Person)} metódus
	 * lefut.
	 * 
	 * @param person
	 *            A felhasználó akinek a nap folyamán már bevitt
	 *            tápanyagszükségletét beállítjuk.
	 */
	public static void updateGotBMR(Person person) {
		person.setGotBMR(person.getGotCH() + person.getGotFat() + person.getGotProtein());
	}

	/**
	 * Növeli a felhasználó a nap folyamán már bevitt zsír szükségletét.
	 * 
	 * @param gotFat
	 *            A {@code person} felhasználó által bevitt zsír mennyiség
	 *            amivel növeljük a nap folyamán már bevitt zsír szükségletet.
	 * @param person
	 *            A felhasználó aki {@code gotFat} mennyiségű zsírt vitt be.
	 */
	public static void incGotFat(Double gotFat, Person person) {
		person.setGotFat(person.getGotFat() + gotFat);
		Adding adding = new Adding(gotFat, Nutrients.FAT);
		person.getAddings().add(adding);
		updateGotBMR(person);
	}

	/**
	 * Növeli a felhasználó a nap folyamán már bevitt fehérje szükségletét.
	 * 
	 * @param gotProtein
	 *            A {@code person} felhasználó által bevitt fehérje mennyiség
	 *            amivel növeljük a nap folyamán már bevitt fehérje
	 *            szükségletet.
	 * @param person
	 *            A felhasználó aki {@code gotProtein} mennyiségű fehérjét vitt
	 *            be.
	 */
	public static void incGotProtein(Double gotProtein, Person person) {
		person.setGotProtein(person.getGotProtein() + gotProtein);
		Adding adding = new Adding(gotProtein, Nutrients.PROTEIN);
		person.getAddings().add(adding);
		updateGotBMR(person);
	}

	/**
	 * Növeli a felhasználó a nap folyamán már bevitt szénhidrát szükségletét.
	 * 
	 * @param gotCH
	 *            A {@code person} felhasználó által bevitt szénhidrát mennyiség
	 *            amivel növeljük a nap folyamán már bevitt szénhidrát
	 *            szükségletet.
	 * @param person
	 *            A felhasználó aki {@code gotCH} mennyiségű szénhidrátot vitt
	 *            be.
	 */
	public static void incGotCH(Double gotCH, Person person) {
		person.setGotCH(person.getGotCH() + gotCH);
		Adding adding = new Adding(gotCH, Nutrients.CH);
		person.getAddings().add(adding);
		updateGotBMR(person);
	}

	/**
	 * Visszaállítja a felhasználók által a nap folyamán bevitt
	 * kalóriamennyiséget ha már a legutoljára bevitt kalóriamennyiség napja
	 * elmúlt.
	 * 
	 * @param people
	 *            A felahsználók listája aminek a nap folyamán bevitt
	 *            kalórimennyiségét nullázzuk.
	 */
	public static void resetGotNutrients(List<Person> people) {
		for (Person person : people) {
			if (LocalDate.now().minusDays(1).isAfter(person.getToday())
					|| LocalDate.now().minusDays(1).equals(person.getToday())) {
				if (person.getWeek().size() >= 6)
					person.getWeek().remove(0);
				person.getWeek().add(new DailyGotNutreints(person.getGotCH(), person.getGotProtein(),
						person.getGotFat(), person.getToday()));
				person.setToday(LocalDate.now());
				person.setGotCH(0.0);
				person.setGotProtein(0.0);
				person.setGotFat(0.0);
				person.setGotBMR(0.0);
			}
		}
	}

	/**
	 * Törli a lehetséges visszavonásokat a felhasználóknak. Ez a függvény a
	 * program bezárulásakor hívódik meg hiszen nem akarjuk tárolni ezeket az
	 * adatokat.
	 * 
	 * @param people
	 *            A felhasználók listája.
	 */
	public static void resetUndos(List<Person> people) {
		for (Person person : people) {
			person.getAddings().clear();
		}
	}

	public static void fillEmptyDays(List<DailyGotNutreints> week) {
		int gap;

		if (week.size() >= 1) {
			Main.getLogger().info("Útolsó megnyitás óta eltelt napok: "
					+ week.get(week.size() - 1).getDate().until(LocalDate.now()).getDays());
			if ((gap = week.get(week.size() - 1).getDate().until(LocalDate.now()).getDays()) > 1) {

				for (int i = 0; i < gap - 1; i++) {
					DailyGotNutreints filler = new DailyGotNutreints(0.0, 0.0, 0.0,
							week.get(week.size() - 1).getDate().plusDays(1));
					week.add(filler);
				}
				int plusSize;
				if ((plusSize = week.size()) > 7) {
					plusSize -= 7;
					for (int i = 0; i < plusSize; i++) {
						week.remove(0);
					}
				}
			} else if (gap < 0) {
				gap = Math.abs(gap);
				if (gap < week.size())
					for (int i = 0; i < gap; i++) {
						Main.getLogger().info("Week size: " + week.size() + " Gap " + gap);
						week.remove(week.size() - 1);
					}
				else
					week.clear();
			}
		}

	}
}
