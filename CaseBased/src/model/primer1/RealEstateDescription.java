package model.primer1;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

public class RealEstateDescription implements CaseComponent {

	private int age;
	private int distanceToPublicTransportation;
	private int localStores;
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public int getDistanceToPublicTransportation() {
		return distanceToPublicTransportation;
	}
	public void setDistanceToPublicTransportation(int distanceToPublicTransportation) {
		this.distanceToPublicTransportation = distanceToPublicTransportation;
	}

	public int getLocalStores() {
		return localStores;
	}
	public void setLocalStores(int localStores) {
		this.localStores = localStores;
	}
	
	@Override
	public String toString() {
		return "RealEstateDescription [age=" + age + ", distanceToPublicTransportation="
				+ distanceToPublicTransportation + ", localStores=" + localStores + "]";
	}
	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
