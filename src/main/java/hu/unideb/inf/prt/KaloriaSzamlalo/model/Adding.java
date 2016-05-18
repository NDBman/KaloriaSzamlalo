package hu.unideb.inf.prt.KaloriaSzamlalo.model;

public class Adding {

	private Double amount;
	private Nutrients nutrient;

	public Adding(Double amount, Nutrients nutrient) {
		this.amount = amount;
		this.nutrient = nutrient;
	}

	public Double getAmount() {
		return amount;
	}

	public Nutrients getNutrient() {
		return nutrient;
	}

}
