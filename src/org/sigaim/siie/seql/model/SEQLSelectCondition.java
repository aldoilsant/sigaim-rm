package org.sigaim.siie.seql.model;

import java.util.List;

import org.sigaim.siie.seql.model.SEQLPathComponent;


public class SEQLSelectCondition {
	private SEQLPath path;
	private String name;
	private Boolean withDescendants;
	
	public SEQLSelectCondition(String path) {
		this(path,null);
	}
	public SEQLSelectCondition(String path, String name) {
		this(path,name,false);
	}
	public SEQLSelectCondition(String path, String name, Boolean withDescendants) {
		this.path=new SEQLPath(path);
		this.name=name;
		this.withDescendants=withDescendants;
	}
	public boolean isAbsolute() {
		return path.isAbsolute();
	}
	public SEQLPath getPath() {
		return path;
	}
	public String getIdentifiedVariableId() {
		if(!this.isAbsolute()){
			return path.getFirstPathComponent().getPathIdentifier();
		} else return null;
	}
	public Boolean getWithDescendants() {
		return withDescendants;
	}
	public void setWithDescendants(Boolean withDescendants) {
		this.withDescendants = withDescendants;
	}
}
