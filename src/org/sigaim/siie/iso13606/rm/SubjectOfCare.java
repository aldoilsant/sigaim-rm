package org.sigaim.siie.iso13606.rm;

public class SubjectOfCare {
	protected II identifier;
	
	public SubjectOfCare() {
		
	}
	public SubjectOfCare(II identifier) {
		this.identifier=identifier;
	}
	public II getIdentifier() {
		return identifier;
	}
	public void setIdentifier(II identifier){
		this.identifier=identifier;
	}
}
