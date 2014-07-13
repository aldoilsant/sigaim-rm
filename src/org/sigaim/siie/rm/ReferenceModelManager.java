package org.sigaim.siie.rm;

import java.util.List;
import java.util.Map;

import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.ObjectBlock;
import org.openehr.am.parser.SimpleValue;
import org.openehr.am.parser.SingleAttributeObjectBlock;
import org.sigaim.siie.dadl.exceptions.SemanticDADLException;
import org.sigaim.siie.rm.exceptions.ReferenceModelException;
import org.sigaim.siie.seql.model.SEQLPath;
import org.sigaim.siie.seql.model.SEQLPathComponent;

public interface ReferenceModelManager {
	Class<?> referenceModelClassFromString(String sclass);
	Class<?> getPathType(String sreferenceModelClass, SEQLPath path);
	Object bind(ContentObject obj) throws SemanticDADLException, ReferenceModelException;
	ContentObject unbind(Object root) throws ReferenceModelException;
	Map<SEQLPathComponent,SingleAttributeObjectBlock>  splitForRMObjectVsDataObject(SingleAttributeObjectBlock block) throws SemanticDADLException, ReferenceModelException;
	String getReferenceModelClassName(SingleAttributeObjectBlock block) throws SemanticDADLException;
	ObjectBlock solveReferenceModelPath(SingleAttributeObjectBlock block, List<SEQLPathComponent> components) throws ReferenceModelException;
	String getArchetypeIdForRMObject(SingleAttributeObjectBlock block)  throws ReferenceModelException;
	String getArchetypeNodeIdForRMObject(SingleAttributeObjectBlock block)  throws ReferenceModelException;
	Class<?> getRootClass();
	boolean isRMObjectClass(Class<?> tclass);
	boolean isArchetypedClass(Class<?> tclass);
	SingleAttributeObjectBlock getSingleAttributeObjectBlockFromContentObject(ContentObject obj) throws SemanticDADLException;
	ObjectBlock unbindGeneric(Object obj) throws ReferenceModelException;
	public List<String> getSubclassesOrSelf(String base);
	public List<Class<?>> getSubclassesOrSelf(Class<?> base);
	public Object bindSingleAttributeObjectBlock(SingleAttributeObjectBlock block)  throws SemanticDADLException, ReferenceModelException;
}
