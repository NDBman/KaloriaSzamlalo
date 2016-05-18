package hu.unideb.inf.prt.KaloriaSzamlalo.model;

import java.time.LocalDate;

public class DailyGotNutreints {

	private Double gotCH;
	private Double gotProtein;
	private Double gotFat;
	private LocalDate date;

	public DailyGotNutreints() {
	}

	public DailyGotNutreints(Double gotCH, Double gotProtein, Double gorFat, LocalDate date) {
		this.gotCH = gotCH;
		this.gotProtein = gotProtein;
		this.gotFat = gorFat;
		this.date = date;
	}

	public Double getGotCH() {
		return gotCH;
	}

	public void setGotCH(Double gotCH) {
		this.gotCH = gotCH;
	}

	public Double getGotProtein() {
		return gotProtein;
	}

	public void setGotProtein(Double gotProtein) {
		this.gotProtein = gotProtein;
	}

	public Double getGotFat() {
		return gotFat;
	}

	public void setGotFat(Double gotFat) {
		this.gotFat = gotFat;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
