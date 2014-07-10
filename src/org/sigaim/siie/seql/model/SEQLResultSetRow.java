package org.sigaim.siie.seql.model;

import java.util.ArrayList;
import java.util.List;

import org.openehr.am.parser.ContentObject;
import org.sigaim.siie.seql.model.SEQLException;

public class SEQLResultSetRow {
	private List<ContentObject> columnvalues;
	public SEQLResultSetRow( ) {
		this.columnvalues=new ArrayList<ContentObject>();
	}
	public void appendColumnValue(ContentObject object) {
		this.columnvalues.add(object);
	}
	public ContentObject getColumnAtIndex(int index) throws SEQLException {
		return this.columnvalues.get(index);
	}
}
