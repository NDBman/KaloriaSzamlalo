package hu.unideb.inf.prt.KaloriaSzamlalo.controller;

import hu.unideb.inf.prt.KaloriaSzamlalo.model.DailyGotNutreints;
import hu.unideb.inf.prt.KaloriaSzamlalo.model.Person;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

public class WeekStaticsController {

	private Person person;

	@SuppressWarnings("checkstyle:javadocmethod")
	public void setPerson(Person person) {
		this.person = person;
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "checkstyle:javadocmethod"})
	public Scene setBars() {
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		StackedBarChart statics = new StackedBarChart<String, Number>(xAxis, yAxis);
		xAxis.setLabel("Napok");
		yAxis.setLabel("Kalória");

		XYChart.Series seriesCH = new XYChart.Series();
		XYChart.Series seriesProtein = new XYChart.Series();
		XYChart.Series seriesFat = new XYChart.Series();
		for (DailyGotNutreints dgNutrients : person.getWeek()) {
			seriesCH.getData().add(new XYChart.Data(dgNutrients.getDate().toString(), dgNutrients.getGotCH()));
			seriesProtein.getData()
					.add(new XYChart.Data(dgNutrients.getDate().toString(), dgNutrients.getGotProtein()));
			seriesFat.getData().add(new XYChart.Data(dgNutrients.getDate().toString(), dgNutrients.getGotFat()));
		}
		seriesCH.setName("Szénhidrát");
		seriesProtein.setName("Fehérje");
		seriesFat.setName("Zsír");
		statics.getData().addAll(seriesCH, seriesProtein, seriesFat);

		return new Scene(statics, 800, 430);
	}
}