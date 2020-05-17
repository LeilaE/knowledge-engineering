package model.primer2;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

public class TravelDescription implements CaseComponent {

	private String id;
	private String type;
	private float price;
	private int persons;
	private String region;
	private String transportation;
	private int duration;
	private String season;
	private String accommodation;
	private String hotel;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}

	public int getPersons() {
		return persons;
	}
	public void setPersons(int persons) {
		this.persons = persons;
	}

	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}

	public String getTransportation() {
		return transportation;
	}
	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}

	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}

	public String getAccommodation() {
		return accommodation;
	}
	public void setAccommodation(String accommodation) {
		this.accommodation = accommodation;
	}

	public String getHotel() {
		return hotel;
	}
	public void setHotel(String hotel) {
		this.hotel = hotel;
	}
	
	@Override
	public String toString() {
		return "TravelDescription [id=" + id + ", type=" + type + ", price=" + price + ", persons=" + persons
				+ ", region=" + region + ", transportation=" + transportation + ", duration=" + duration + ", season="
				+ season + ", accommodation=" + accommodation + ", hotel=" + hotel + "]";
	}
	
	@Override
	public Attribute getIdAttribute() {
		return null;
	}
	
}
