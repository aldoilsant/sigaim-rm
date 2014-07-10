package org.sigaim.siie.seql.model;

public class SEQLFromComponent implements SEQLEvaluable{
	private String referenceModelClass;
	private String identifiedVariable;
	private String archetypeId;
	private SEQLFromComponent parent;
	private Boolean useAllVersions;
	
	public Boolean getUseAllVersions() {
		return useAllVersions;
	}
	public void setUseAllVersions(Boolean useAllVersions) {
		this.useAllVersions = useAllVersions;
	}
	public SEQLFromComponent(String referenceModelClass, String identifiedVariable, String archetypeId) {
		this(referenceModelClass,identifiedVariable,archetypeId,false);
	}
	public SEQLFromComponent(String referenceModelClass, String identifiedVariable, String archetypeId, Boolean useAllVersions) {
		this.referenceModelClass=referenceModelClass;
		this.identifiedVariable=identifiedVariable;
		this.archetypeId=archetypeId;
		this.useAllVersions=useAllVersions;
	}
	
	public String getReferenceModelClass() {
		return referenceModelClass;
	}
	public String getIdentifiedVariable() {
		return identifiedVariable;
	}
	public String getArchetypeId() {
		return archetypeId;
	}
	public void setParent(SEQLFromComponent parent) {
		this.parent=parent;
	}
	public SEQLFromComponent getParent() {
		return parent;
	}
	@Override public String toString() {
		return referenceModelClass+" "+identifiedVariable+" "+archetypeId;
	}
}