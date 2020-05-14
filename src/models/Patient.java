package models;

import java.util.ArrayList;
import java.util.HashMap;

public class Patient {

	private String name;
	private String age;
	private String activity;
	private boolean smoker;
	private boolean pregnant;
	private ArrayList<String> genetics = new ArrayList<String>();
	
	private ArrayList<String> symptoms = new ArrayList<String>();
	private HashMap<String, String> tests = new HashMap<String, String>();
	
	
	
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Patient(String name, String age, String activity, boolean smoker, boolean pregnant,
			ArrayList<String> genetics) {
		super();
		this.name = name;
		this.age = age;
		this.activity = activity;
		this.smoker = smoker;
		this.pregnant = pregnant;
		this.genetics = genetics;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public boolean isSmoker() {
		return smoker;
	}

	public void setSmoker(boolean smoker) {
		this.smoker = smoker;
	}

	public boolean isPregnant() {
		return pregnant;
	}

	public void setPregnant(boolean pregnant) {
		this.pregnant = pregnant;
	}

	public ArrayList<String> getGenetics() {
		return genetics;
	}

	public void setGenetics(ArrayList<String> genetics) {
		this.genetics = genetics;
	}

	public ArrayList<String> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(ArrayList<String> symptoms) {
		this.symptoms = symptoms;
	}

	public HashMap<String, String> getTests() {
		return tests;
	}

	public void setTests(HashMap<String, String> tests) {
		this.tests = tests;
	}
	
	
}
