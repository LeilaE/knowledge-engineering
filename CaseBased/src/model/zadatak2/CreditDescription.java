package model.zadatak2;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

public class CreditDescription implements CaseComponent {

	private int id;
	private int age;
	private String gender;
	private String housing;
	private int ammount;
	private int duration;
	private String purpose;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHousing() {
		return housing;
	}
	public void setHousing(String housing) {
		this.housing = housing;
	}

	public int getAmmount() {
		return ammount;
	}
	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}

	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	@Override
	public String toString() {
		return "CreditDescription [id=" + id + ", age=" + age + ", gender=" + gender + ", housing=" + housing
				+ ", ammount=" + ammount + ", duration=" + duration + ", purpose=" + purpose + "]";
	}
	
	@Override
	public Attribute getIdAttribute() {
		return null;
	}

}
