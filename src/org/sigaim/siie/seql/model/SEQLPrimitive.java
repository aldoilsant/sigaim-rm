package org.sigaim.siie.seql.model;

public class SEQLPrimitive implements SEQLEvaluable{
	public enum SEQLPrimitiveType {
		BOOLEAN,
		STRING,
		FLOAT,
		INTEGER,
		DATE
	}
	private String value;
	private SEQLPrimitiveType type;	

	public SEQLPrimitive(String value, SEQLPrimitiveType type) {
		this.value=value;
		this.type=type;
	}
	public String getValue() {
		return value;
	}

	public SEQLPrimitiveType getType() {
		return type;
	}
	@Override public String toString() {
		return this.getValue();
	}
}
