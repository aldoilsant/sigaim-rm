package org.sigaim.siie.iso13606.rm;

public class Performer {
	protected II identifier;
	
	public Performer() {
		
	}
	public Performer(II identifier) {
		this.identifier=identifier;
	}
	public II getIdentifier() {
		return identifier;
	}
	public void setIdentifier(II identifier){
		this.identifier=identifier;
	}
}
