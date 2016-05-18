package hu.unideb.inf.prt.KaloriaSzamlalo.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import hu.unideb.inf.prt.KaloriaSzamlalo.comput.Comput;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Adding;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.DailyGotNutreints;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Gender;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Goals;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Nutrients;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Person;

public class ComputTest {

	
	private Person pBoy;
	private Person pGirl;
	private Person p;
	private List<Person> people;

	@Before
	public void setUp() throws Exception {
		
		p = new Person();
		pBoy = new Person("janika","János","Kis", Double.valueOf(170), Double.valueOf(60), 20, Gender.MALE, Goals.LOSING_WEIGHT);
		pGirl = new Person("annika","Annabella","Kis", Double.valueOf(173), Double.valueOf(70), 20, Gender.FEMALE,
				Goals.LOSING_WEIGHT);
		people = new ArrayList<Person>();
	}

	@Test
	public void BMRTestLessThen10M() {
		p.setGender(Gender.MALE);
		p.setWeight(Double.valueOf(56));
		p.setAge(Integer.valueOf(9));
		assertEquals(0, Comput.getBMR(p, p.getWeight()), 0.01);
	}

	@Test
	public void BMRTestLessThen10FM() {
		p.setGender(Gender.FEMALE);
		p.setWeight(Double.valueOf(56));
		p.setAge(Integer.valueOf(9));
		assertEquals(0, Comput.getBMR(p, p.getWeight()), 0.01);
	}

	@Test
	public void BMRTest10_18M() {
		p.setGender(Gender.MALE);
		p.setWeight(Double.valueOf(56));
		p.setAge(Integer.valueOf(16));
		assertEquals(1631, Comput.getBMR(p, p.getWeight()), 0.01);
	}

	@Test
	public void BMRTest10_18FM() {
		p.setGender(Gender.FEMALE);
		p.setWeight(Double.valueOf(60));
		p.setAge(Integer.valueOf(16));
		assertEquals(1478, Comput.getBMR(p, p.getWeight()), 0.01);
	}

	@Test
	public void BMRTest19_30M() {
		p.setGender(Gender.MALE);
		p.setWeight(Double.valueOf(60));
		p.setAge(Integer.valueOf(19));
		assertEquals(1597, Comput.getBMR(p, p.getWeight()), 0.01);
	}

	@Test
	public void BMRTest19_30FM() {
		p.setGender(Gender.FEMALE);
		p.setWeight(Double.valueOf(60));
		p.setAge(Integer.valueOf(19));
		assertEquals(1378, Comput.getBMR(p, p.getWeight()), 0.01);
	}

	@Test
	public void BMRTest31_60M() {
		p.setGender(Gender.MALE);
		p.setWeight(Double.valueOf(70));
		p.setAge(Integer.valueOf(31));
		assertEquals(1691, Comput.getBMR(p, p.getWeight()), 0.01);
	}

	@Test
	public void BMRTest31_60FM() {
		p.setGender(Gender.FEMALE);
		p.setWeight(Double.valueOf(70));
		p.setAge(Integer.valueOf(31));
		assertEquals(1438, Comput.getBMR(p, p.getWeight()), 0.01);
	}

	@Test
	public void BMRTest60_M() {
		p.setGender(Gender.MALE);
		p.setWeight(Double.valueOf(70));
		p.setAge(Integer.valueOf(61));
		assertEquals(1432, Comput.getBMR(p, p.getWeight()), 0.01);
	}

	@Test
	public void BMRTest60_FM() {
		p.setGender(Gender.FEMALE);
		p.setWeight(Double.valueOf(70));
		p.setAge(Integer.valueOf(61));
		assertEquals(1331, Comput.getBMR(p, p.getWeight()), 0.01);
	}

	@Test
	public void idealWeightTest() {
		assertEquals(63, Comput.getIdealWeight(pBoy), 0.01);
	}

	@Test
	public void keepingWeightMTest() {
		assertEquals(2555.2, Comput.getKcalForKeepingWeight(pBoy, pBoy.getWeight()), 0.01);
	}

	@Test
	public void keepingWeightFMTest() {
		assertEquals(2440, Comput.getKcalForKeepingWeight(pGirl, pGirl.getWeight()), 0.01);
	}

	@Test
	public void losingWeightMTest() {
		assertEquals(1756.7, Comput.getKcalForGentlyLosingWeight(pBoy, pBoy.getWeight()), 0.01);
	}

	@Test
	public void losingWeightFMTest() {
		assertEquals(1677.5, Comput.getKcalForGentlyLosingWeight(pGirl, pGirl.getWeight()), 0.01);
	}

	@Test
	public void fatPercentageMTest() {
		assertEquals(13.313, Comput.getFatPercentage(pBoy), 0.01);
	}

	@Test
	public void fatPercentageFMTest() {
		assertEquals(27.266, Comput.getFatPercentage(pGirl), 0.01);
	}

	@Test
	public void essentialsFMLWLessThan30PFTest() {
		Comput.setEssentials(pGirl);
		assertEquals(196.39, pGirl.getcH(), 0.01);
		assertEquals(130.92, pGirl.getProtein(), 0.01);
		assertEquals(36.07, pGirl.getFat(), 0.01);
	}

	@Test
	public void essentialsFMKWLessThan30PFTest() {
		p = pGirl;
		p.setGoal(Goals.KEEPING_WEIGHT);
		Comput.setEssentials(pGirl);
		assertEquals(285.65, p.getcH(), 0.01);
		assertEquals(190.43, p.getProtein(), 0.01);
		assertEquals(52.47, p.getFat(), 0.01);
	}

	@Test
	public void essentialsFMLWMoreThan30PFTest() {
		p = pGirl;
		p.setWeight(Double.valueOf(95));
		Comput.setEssentials(pGirl);
		assertEquals(188.25, pGirl.getcH(), 0.01);
		assertEquals(125.5, pGirl.getProtein(), 0.01);
		assertEquals(34.57, pGirl.getFat(), 0.01);
	}

	@Test
	public void essentialsFMKWMoreThan30PFTest() {
		p = pGirl;
		p.setWeight(Double.valueOf(95));
		p.setGoal(Goals.KEEPING_WEIGHT);
		Comput.setEssentials(pGirl);
		assertEquals(273.81, pGirl.getcH(), 0.01);
		assertEquals(182.54, pGirl.getProtein(), 0.01);
		assertEquals(50.29, pGirl.getFat(), 0.01);
	}

	@Test
	public void updateGotBMRTest() {
		p = pGirl;
		Comput.updateGotBMR(p);
		assertEquals(0, p.getGotBMR(), 0.01);
	}

	@Test
	public void setGotCHTest() {
		p = pGirl;
		Comput.incGotCH(15.0, p);
		assertEquals(15.0, p.getGotCH(), 0.01);
	}

	@Test
	public void setGotProteinTest() {
		p = pGirl;
		Comput.incGotProtein(23.0, p);
		assertEquals(23.0, p.getGotProtein(), 0.01);
	}

	@Test
	public void setGotFatTest() {
		p = pGirl;
		Comput.incGotFat(11.0,p);
		assertEquals(11.0, p.getGotFat(), 0.01);
	}

	@Test
	public void setGotCHNotNullTest() {
		p = pGirl;
		p.setGotCH(10.0);
		Comput.incGotCH(15.0,p);
		assertEquals(25.0, p.getGotCH(), 0.01);
	}

	@Test
	public void setGotProteinNotNullTest() {
		p = pGirl;
		p.setGotProtein(10.0);
		Comput.incGotProtein(23.0,p);
		assertEquals(33.0, p.getGotProtein(), 0.01);
	}

	@Test
	public void setGotFatNotNullTest() {
		p = pGirl;
		p.setGotFat(10.0);
		Comput.incGotFat(11.0,p);
		assertEquals(21.0, p.getGotFat(), 0.01);
	}

	@Test
	public void undoCHTest() {
		p = pGirl;
		p.getAddings().add(new Adding(12.0, Nutrients.CH));
		p.setGotCH(12.0);
		Comput.undo(p);
		assertEquals(0.0, p.getGotCH(), 0.01);
	}

	@Test
	public void undoProteinTest() {
		p = pGirl;
		p.getAddings().add(new Adding(12.0, Nutrients.PROTEIN));
		p.setGotProtein(12.0);
		Comput.undo(p);
		assertEquals(0.0, p.getGotProtein(), 0.01);
	}

	@Test
	public void undoFatTest() {
		p = pGirl;
		p.getAddings().add(new Adding(12.0, Nutrients.FAT));
		p.setGotFat(12.0);
		Comput.undo(p);
		assertEquals(0.0, p.getGotFat(), 0.01);
	}
	
	@Test
	public void undoNoAddingTest() {
		p = pGirl;
		p.setGotCH(10.0);
		Comput.undo(p);
	}
	
	@Test
	public void resetGotNutrientsAfterTest(){
		people.clear();
		pGirl.setToday(LocalDate.now().minusDays(10));
		people.add(pGirl);
		Comput.resetGotNutrients(people);
		assertEquals(LocalDate.now(),pGirl.getToday());
		assertEquals(0.0, pGirl.getGotCH(),0.01);
		assertEquals(0.0, pGirl.getGotProtein(),0.01);
		assertEquals(0.0, pGirl.getGotFat(),0.01);
		assertEquals(0.0, pGirl.getGotBMR(),0.01);
	}
	
	@Test
	public void resetGotNutrientsNowTest(){
		people.clear();
		pGirl.setToday(LocalDate.now());
		pGirl.setGotCH(1.0);
		pGirl.setGotFat(1.0);
		pGirl.setGotProtein(1.0);
		Comput.updateGotBMR(pGirl);
		people.add(pGirl);
		Comput.resetGotNutrients(people);
		assertEquals(LocalDate.now(),pGirl.getToday());
		assertEquals(1.0, pGirl.getGotCH(),0.01);
		assertEquals(1.0, pGirl.getGotProtein(),0.01);
		assertEquals(1.0, pGirl.getGotFat(),0.01);
		assertEquals(3.0, pGirl.getGotBMR(),0.01);
	}
	
	@Test
	public void resetGotNutrientsTodayTest(){
		people.clear();
		pGirl.setToday(LocalDate.now().minusDays(1));
		people.add(pGirl);
		Comput.resetGotNutrients(people);
		assertEquals(LocalDate.now(),pGirl.getToday());
		assertEquals(0.0, pGirl.getGotCH(),0.01);
		assertEquals(0.0, pGirl.getGotProtein(),0.01);
		assertEquals(0.0, pGirl.getGotFat(),0.01);
		assertEquals(0.0, pGirl.getGotBMR(),0.01);
	}
	
	@Test
	public void resetGotNutrientsWeekTest(){
		people.clear();
		for(int i = 0;i < 7;i++){
			p.getWeek().add(new DailyGotNutreints());			
		}
		p.setToday(LocalDate.now().minusDays(2));
		people.add(p);
		
		Comput.resetGotNutrients(people);
		assertEquals(7, p.getWeek().size());
	}

	@Test
	public void resetGotNutrientsDayTest(){
		people.clear();
		p.setToday(LocalDate.of(2016,05,11));
		people.add(p);
		Comput.resetGotNutrients(people);
		assertEquals(LocalDate.of(2016, 05, 11), p.getWeek().get(0).getDate());
	}
	
	@Test
	public void resetUndosTest(){
		people.clear();
		pGirl.getAddings().add(new Adding(11.0, Nutrients.CH));
		people.add(pGirl);
		Comput.resetUndos(people);
		for(Person person : people){
			assertEquals(0, person.getAddings().size());
		}
	}
}
