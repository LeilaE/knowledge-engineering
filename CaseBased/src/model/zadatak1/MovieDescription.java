package model.zadatak1;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

public class MovieDescription implements CaseComponent {

	private String gender;
	private int age;
	private String title;
	private int score;
	private int year;
	private String genre;
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "TravelDescription [gender=" + gender + ", age=" + age + ", title=" + title + ", score=" + score
				+ ", year=" + year + ", genre=" + genre + "]";
	}
	
	@Override
	public Attribute getIdAttribute() {
		return null;
	}
	
}
