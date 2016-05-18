package hu.unideb.inf.prt.KaloriaSzamlalo.comput;

import java.time.LocalDate;
import java.util.List;

import hu.unideb.inf.prt.KaloriaSzamlalo.model.Adding;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.DailyGotNutreints;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Gender;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Goals;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Nutrients;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Person;

public class Comput {

	private static final Double oneGCHKcal = 4.1;
	private static final Double oneGProteinKcal = 4.1;
	private static final Double oneGFatKcal = 9.3;
	private static final Double percentageOfCH = 0.48;
	private static final Double percentageOfProtein = 0.32;
	private static final Double percentageOfFat = 0.2;

	/**
	 * Kiszámolja és visszaadja a felhasználó napi energiszükségletét. Az
	 * Egészségügyi Világszervezet (WHO) által ajánlott táblázat szerint. Olvass
	 * tovább: http://czinadora-hu.webnode.hu/products/alapanyagcsere-es-napi-
	 * energiaszukseglet-kiszamitasa/
	 * 
	 * @param p
	 *            Egy felhasználó.
	 * @return A p felhasználó energiszükségletét.
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

	public static double getIdealWeight(Person p) {
		return (p.getHeight() - 100) * 0.9;
	}

	public static double getKcalForKeepingWeight(Person p, double weight) {
		return getBMR(p, weight) * 1.6;
	}

	public static double getKcalForGentlyLosingWeight(Person p, double weight) {
		return getBMR(p, weight) * 1.1;
	}

	public static double getFatPercentage(Person p) {
		int gend = (p.getGender() == Gender.MALE) ? 1 : 0;
		return (1.2 * p.getWeight() / Math.pow(p.getHeight() / 100.0, 2)) + (0.23 * p.getAge()) - 5.4 - (10.8 * gend);
	}

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
	
	public static void undo(Person person){
	
			Adding adding;
			int size;
			if ((size = person.getAddings().size()) > 0) {
				adding = person.getAddings().get(size-1);
				switch (adding.getNutrient()) {
				case CH:
					person.setGotCH(person.getGotCH()-adding.getAmount());
					break;
				case PROTEIN:
					person.setGotProtein(person.getGotProtein()-adding.getAmount());
					break;
				case FAT:
					person.setGotFat(person.getGotFat()-adding.getAmount());
					break;
				default:
					
				}
				person.getAddings().remove(size-1);
				updateGotBMR(person);
			}
	}
	
	public static void updateGotBMR(Person person){
		person.setGotBMR(person.getGotCH() + person.getGotFat() + person.getGotProtein());
	}
	
	public static void incGotFat(Double gotFat, Person person){
		person.setGotFat(person.getGotFat() + gotFat);
		Adding adding = new Adding(gotFat, Nutrients.FAT);
		person.getAddings().add(adding);
		updateGotBMR(person);
	}
	
	public static void incGotProtein(Double gotProtein, Person person){
		person.setGotProtein(person.getGotProtein() + gotProtein);
		Adding adding = new Adding(gotProtein, Nutrients.PROTEIN);
		person.getAddings().add(adding);
		updateGotBMR(person);
	}
	public static void incGotCH(Double gotCH, Person person){
		person.setGotCH(person.getGotCH() + gotCH);
		Adding adding = new Adding(gotCH, Nutrients.CH);
		person.getAddings().add(adding);
		updateGotBMR(person);
	}
	
	public static void resetGotNutrients(List<Person> people){
		for(Person person : people){
			if(LocalDate.now().minusDays(1).isAfter(person.getToday()) || LocalDate.now().minusDays(1).equals(person.getToday())){
				if(person.getWeek().size() >= 6)
					person.getWeek().remove(0);
				person.getWeek().add(new DailyGotNutreints(person.getGotCH(), person.getGotProtein(), person.getGotFat(), person.getToday()));
				person.setToday(LocalDate.now());
				person.setGotCH(0.0);
				person.setGotProtein(0.0);
				person.setGotFat(0.0);
				person.setGotBMR(0.0);
			}
		}
	}
	
	public static void resetUndos(List<Person> people){
		for(Person person: people){
			person.getAddings().clear();
		}
	}
}
