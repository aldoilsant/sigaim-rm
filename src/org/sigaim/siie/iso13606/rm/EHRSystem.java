package org.sigaim.siie.iso13606.rm;

import java.util.ArrayList;
import java.util.List;

public class EHRSystem {
	protected II identifier;
	protected List<EHRExtract> allEhrs;
	protected List<SubjectOfCare> allSubjectsOfCare;
	protected List<HealthcareFacility> allHealthcareFacilities;
	
	public EHRSystem() {
		
	}
	public EHRSystem(II identifier) {
		this.identifier=identifier;
	}
	public II getIdentifier() {
		return identifier;
	}
	public void setIdentifier(II identifier){
		this.identifier=identifier;
	}
	public List<EHRExtract> getAllEhrs() {
		if(allEhrs==null) {
			allEhrs=new ArrayList<EHRExtract>();
		}
		return allEhrs;
	}
	public void setAllEhrs(List<EHRExtract> allEhrs) {
		this.allEhrs = allEhrs;
	}
	public List<SubjectOfCare> getAllSubjectsOfCare() {
		if(allSubjectsOfCare==null) {
			allSubjectsOfCare=new ArrayList<SubjectOfCare>();
		}
		return allSubjectsOfCare;
	}
	public void setAllSubjectsOfCare(List<SubjectOfCare> allSubjectsOfCare) {
		this.allSubjectsOfCare = allSubjectsOfCare;
	}
	public List<HealthcareFacility> getAllHealthcareFacilities() {
		if(allHealthcareFacilities==null) {
			allHealthcareFacilities=new ArrayList<HealthcareFacility>();
		}
		return allHealthcareFacilities;
	}
	public void setAllHealthcareFacilities(
			List<HealthcareFacility> allHealthcareFacilities) {
		this.allHealthcareFacilities = allHealthcareFacilities;
	}
}
