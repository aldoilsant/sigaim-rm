package org.sigaim.siie.seql.model;

import org.sigaim.siie.utils.Utils;

public class SEQLPathComponent {
	private boolean hasPredicate;
	private String pathIdentifier;
	private SEQLPathPredicate pathPredicate;

	public SEQLPathComponent(String pathComponent, SEQLPathPredicate pathPredicate) {
		this.pathIdentifier=pathComponent;
		this.pathPredicate=pathPredicate;
		if(pathPredicate!=null) {
			this.hasPredicate=true;
		}
	}
	public SEQLPathComponent(String pathComponent){
		this.pathIdentifier=pathComponent;
		if(pathComponent.contains("[")) {
			this.hasPredicate=true;
			String[] parts=pathComponent.split("\\[");
			this.pathIdentifier=parts[0];
			String stringPathPredicate=parts[1].replace("]", "");
			String[] simpleParts=stringPathPredicate.split(",");
			if(simpleParts.length==1) {
				this.pathPredicate=new SEQLPathPredicate(simpleParts[0],null);
			} else {
				this.pathPredicate=new SEQLPathPredicate(simpleParts[0],simpleParts[1]);
			}
		} else {
			this.hasPredicate=false;
			this.pathIdentifier=pathComponent;
		}
	}
	public SEQLPathComponent toReferenceModelPathComponent() {
		if(this.hasPredicate && this.pathPredicate.getKey2()!=null) {
			SEQLPathPredicate newPredicate=new SEQLPathPredicate(Utils.unquote(this.pathPredicate.getKey2()),null);
			return new SEQLPathComponent(this.pathIdentifier,newPredicate);
		} else {
			return new SEQLPathComponent(this.pathIdentifier);
		}
	}
	public SEQLPathComponent toNodeIDPath() {
		if(this.hasPredicate && this.pathPredicate.getKey1()!=null) {
			SEQLPathPredicate newPredicate=new SEQLPathPredicate(Utils.unquote(this.pathPredicate.getKey1()),null);
			return new SEQLPathComponent(this.pathIdentifier,newPredicate);
		} else {
			return new SEQLPathComponent(this.pathIdentifier);
		}
	}
	public String getPathComponent() {
		if(pathPredicate!=null) {
			return this.pathIdentifier+"["+this.pathPredicate+"]";
		} else {
			return this.pathIdentifier;
		}
	}
	public String getPathIdentifier() {
		return pathIdentifier;
	}
	public SEQLPathPredicate getPathPredicate() {
		return pathPredicate;
	}
	public void setPathPredicate(SEQLPathPredicate predicate) {
		this.pathPredicate=predicate;
	}
	public boolean hasPredicate() {
		return hasPredicate;
	}
	@Override public String toString() {
		return this.getPathComponent();
	}
}
