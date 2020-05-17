package model.primer1;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

public class TransactionDescription implements CaseComponent {

	private int year;
	private int price;
	private RealEstateDescription realEstateDescription;
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public RealEstateDescription getRealEstateDescription() {
		return realEstateDescription;
	}
	public void setRealEstateDescription(RealEstateDescription realEstateDescription) {
		this.realEstateDescription = realEstateDescription;
	}
	
	@Override
	public String toString() {
		return "TransactionDescription [year=" + year + ", price=" + price + ", realEstateDescription="
				+ realEstateDescription + "]";
	}
	
	@Override
	public Attribute getIdAttribute() {
		return null;
	}
}
