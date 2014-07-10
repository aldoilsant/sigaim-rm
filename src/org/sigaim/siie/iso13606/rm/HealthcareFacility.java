package org.sigaim.siie.iso13606.rm;

public class HealthcareFacility {
	protected II identifier;
	
	public HealthcareFacility() {
		
	}
	public HealthcareFacility(II identifier) {
		this.identifier=identifier;
	}
	public II getIdentifier() {
		return identifier;
	}
	public void setIdentifier(II identifier){
		this.identifier=identifier;
	}
}
