package hu.unideb.inf.prt.KaloriaSzamlalo.model;

/**
 * Ez az osztály tárolja az egyes felhasználók által hozzáadáott tápanyag
 * mennyiségét és típusát. Célja az, hogy a visszavonás opcióval lehessen
 * törölni a napi mennyiséghez hhozzáadott értéket.
 * 
 * @author Szabó Nándor Attila
 *
 */
public class Adding {

	private Double amount;
	private Nutrients nutrient;

	/**
	 * Az osztály konstruktora, beállítja a {@code amount} és {@link nutrient}
	 * értékeit.
	 * 
	 * @param amount
	 *            A tápanyaghozzáadás mennyisége.
	 * @param nutrient
	 *            A tápanyag típusa.
	 */
	public Adding(Double amount, Nutrients nutrient) {
		this.amount = amount;
		this.nutrient = nutrient;
	}

	/**
	 * Visszaadja a tápanyag mennyiséget.
	 * 
	 * @return A tápanyagmennyiséget.
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * Visszaadja a tápanyag típusát.
	 * 
	 * @return A tápanyag típusát.
	 */
	public Nutrients getNutrient() {
		return nutrient;
	}

}
