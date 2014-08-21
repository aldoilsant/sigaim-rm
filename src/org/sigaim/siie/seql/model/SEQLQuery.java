package org.sigaim.siie.seql.model;

import java.util.ArrayList;
import java.util.List;

import org.sigaim.siie.seql.model.SEQLException;

public class SEQLQuery {
	private boolean merged;
	private List<SEQLSelectCondition> selectConditions;
	private SEQLFromCondition fromCondition;
	private SEQLWhereCondition whereCondition;
	private SEQLHavingCondition havingCondition;
	private String mergedVariable;
	

	public SEQLQuery() {
		selectConditions=new ArrayList<SEQLSelectCondition>();
		fromCondition=new SEQLFromCondition();
		whereCondition=new SEQLWhereCondition();
		havingCondition= new SEQLHavingCondition();
		merged=false;
	}
	public void addSelectCondition(String path, String name, Boolean withDescendants) {
		selectConditions.add(new SEQLSelectCondition(path,name,withDescendants));
	}
	public SEQLFromCondition getFromCondition() {
		return fromCondition;
	}
	public SEQLWhereCondition getWhereCondition() {
		return whereCondition;
	}
	public List<SEQLSelectCondition> getSelectConditions() {
		return selectConditions;
	}
	@Override public String toString() {
		return "FROM "+fromCondition.toString()+"\nWHERE "+whereCondition.toString();
	}
	protected List<String> getSelectIdentifiedVariables() {
		List<String> ret=new ArrayList<String>();
		for(SEQLSelectCondition cond : selectConditions) {
			String var=cond.getIdentifiedVariableId();
			if(var!=null) {
				ret.add(var);
			}
		}
		return ret;
	}
	public List<String> getIdentifiedVariables() throws SEQLException{
		List<String> selectVariables=this.getSelectIdentifiedVariables();
		List<String> fromVariables=this.fromCondition.getIdentifiedVariables();
		for(String selectIdentifiedVariable : selectVariables) {
			if(!fromVariables.contains(selectIdentifiedVariable)) {
				throw new SEQLException("Uknown identified variable "+selectIdentifiedVariable +" in SELECT clause");
			}
		}
		selectVariables.addAll(fromVariables);
		return selectVariables;
	}
	public boolean isMerged() {
		return merged;
	}
	public void setMerged(boolean merged) {
		this.merged = merged;
	}
	public String getMergedVariable() {
		return mergedVariable;
	}
	public void setMergedVariable(String mergedVariable) {
		this.mergedVariable = mergedVariable;
	}
	public SEQLHavingCondition getHavingCondition() {
		return havingCondition;
	}
}
