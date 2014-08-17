package org.sigaim.siie.dadl;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.openehr.am.parser.AttributeValue;
import org.openehr.am.parser.ComplexObjectBlock;
import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.DADLParser;
import org.openehr.am.parser.KeyedObject;
import org.openehr.am.parser.MultipleAttributeObjectBlock;
import org.openehr.am.parser.ObjectBlock;
import org.openehr.am.parser.PrimitiveObjectBlock;
import org.openehr.am.parser.SimpleValue;
import org.openehr.am.parser.SingleAttributeObjectBlock;
import org.openehr.rm.support.basic.Interval;
import org.sigaim.siie.annotations.DADL;

public class OpenEHRDADLManager implements DADLManager {
	  private static org.apache.log4j.Logger log = Logger
               .getLogger(OpenEHRDADLManager.class);
	@Override
	public ContentObject parseDADL(InputStream is) {
		DADLParser dadlParser=new DADLParser(is);
		try {
			ContentObject obj=dadlParser.parse();
			return obj;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
 
	private void serializeSingleAttributeObjectBlock(StringBuilder input, SingleAttributeObjectBlock block) {
		StringBuilder ret=input;
		for(AttributeValue value : block.getAttributeValues()) {
			this.serializeAttributeValue(ret,value);
		}
	}
	public String serialize(SimpleValue value) {
		StringBuilder ret=new StringBuilder();
		this.serializeSimpleValue(ret,value);
		return ret.toString();
	}
	public void serialize(StringBuilder input, SimpleValue value) {
		this.serializeSimpleValue(input,value);
	}
	@Override
	public String serializeSimpleValue(SimpleValue value) {
		StringBuilder ret=new StringBuilder();
		this.serializeSimpleValue(ret, value);
		return ret.toString();
	}
	public void serializeSimpleValue(StringBuilder input, SimpleValue value) {
		StringBuilder ret=input;
		if(value.getValue() instanceof String) {
			ret.append("\"");
			ret.append(value.getValue().toString());
			ret.append("\"");
		} else if(value.getValue() instanceof Double) {
			DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
			otherSymbols.setDecimalSeparator('.');
			DecimalFormat df = new DecimalFormat("##.#######",otherSymbols);
			df.setMinimumFractionDigits(1);
			ret.append(df.format((Double)value.getValue()));
		} else ret.append(value.getValue().toString());
	}
	private String serializeSimpleIntervalValue(Interval<Comparable> interval) {
		return interval.toString();
	}
	private void serializePrimitiveObjectBlock(StringBuilder input, PrimitiveObjectBlock block) {
		StringBuilder ret=input;
		if(block==null) return;
		if(block.getSimpleIntervalValue()!=null) {
			ret.append(this.serializeSimpleIntervalValue(block.getSimpleIntervalValue()));
		} else if(block.getSimpleListValue()!=null) {
			for(SimpleValue value: block.getSimpleListValue()) {
				this.serializeSimpleValue(ret,value);
			}
		} else if(block.getSimpleValue()!=null) {
			this.serializeSimpleValue(ret,block.getSimpleValue());
		} else if(block.getTermCode()!=null) {
			ret.append(block.getTermCode());
		} else if(block.getTermCodeListValue()!=null) {
			for(String term : block.getTermCodeListValue()) {
				ret.append(term);
			}
		}
	}
	private void serializeKeyedObject(StringBuilder input,KeyedObject obj) {
		StringBuilder ret=input;
		ret.append("[");
		this.serializeSimpleValue(ret,obj.getKey());
		ret.append("]=<");
		this.serializeObjectBlock(ret,obj.getObject());
		ret.append(">");
	}
	private void serializeMultipleAttributeObjectBlock(StringBuilder input, MultipleAttributeObjectBlock block) {
		StringBuilder ret=input;
		for(KeyedObject obj : block.getKeyObjects()) {
			if(obj!=null) {
				this.serializeKeyedObject(ret,obj);				
			} else {
				log.warn("Warning: empty entry in keyed object list");
			}
		}
	}
	private void serializeComplexObjectBlock(StringBuilder input, ComplexObjectBlock block) {
		StringBuilder ret=input;
		if(block instanceof MultipleAttributeObjectBlock) {
			this.serializeMultipleAttributeObjectBlock(ret,(MultipleAttributeObjectBlock)block);
		} else {
			this.serializeSingleAttributeObjectBlock(ret,(SingleAttributeObjectBlock)block);
		}
	}
	private void serializeObjectBlock(StringBuilder input, ObjectBlock block) {
		StringBuilder ret=input;
		if(block instanceof ComplexObjectBlock) {
			this.serializeComplexObjectBlock(ret,(ComplexObjectBlock)block);
		} else {
			this.serializePrimitiveObjectBlock(ret,(PrimitiveObjectBlock)block);
		}
		
	}
	private void serializeAttributeValue(StringBuilder input, AttributeValue value) {
		StringBuilder ret=input;
		ret.append(value.getId());
		ret.append(" = <");
		this.serializeObjectBlock(ret,value.getValue());
		ret.append(">");
	}

	public Class<?> getRootClass(ContentObject obj) {
		return null;
	}
	@Override
	public String serialize(ContentObject obj, boolean packed) {
		StringBuilder ret=new StringBuilder("");
		if(obj.getAttributeValues()!=null) {
			List<AttributeValue> values=obj.getAttributeValues();
			for(AttributeValue value : values) {
				this.serializeAttributeValue(ret,value);
			}
		} else {
			ret.append("<");
			this.serializeComplexObjectBlock(ret,obj.getComplexObjectBlock());
			ret.append(">");
		}
		//Indent
		if(packed) return ret.toString();
		int tabCount=0;
		StringBuilder builder=new StringBuilder(ret);
		for(int i=0;i<builder.length();i++) {
			if(builder.charAt(i)=='<') {
				tabCount++;
			} else if(builder.charAt(i)=='>') {
				tabCount--;
			}
			if(builder.length()<=i) break;
			if( builder.charAt(i)=='>' || builder.charAt(i)=='<') {
					for(int j=0;j<tabCount;j++) {
						builder.insert(i+1, '\t');
					}
					builder.insert(i+1, '\n');
				int rTabCount=builder.charAt(i)=='>'?tabCount:tabCount-1;
				builder.insert(i++, '\n');
				for(int j=0;j<rTabCount;j++) {
					builder.insert(i++, '\t');
				}
			}
		}
		return builder.toString();
	}
	@Override
	public String serialize(SingleAttributeObjectBlock obj) {
		StringBuilder ret=new StringBuilder();
		 this.serializeSingleAttributeObjectBlock(ret,obj);
		 return ret.toString();
	}
}
