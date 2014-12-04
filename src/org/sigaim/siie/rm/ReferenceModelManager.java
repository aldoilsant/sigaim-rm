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
	Class<?> getPathType(Class<?> referenceModelClass, SEQLPath path,boolean identified);
	Class<?> getPathType(Class<?> referenceModelClass, SEQLPath path);
	Object bind(ContentObject obj) throws SemanticDADLException, ReferenceModelException;
	ContentObject unbind(Object root) throws ReferenceModelException;
	Map<SEQLPathComponent,SingleAttributeObjectBlock>  splitForRMObjectVsDataObject(SingleAttributeObjectBlock block) throws SemanticDADLException, ReferenceModelException;
	String getReferenceModelClassName(SingleAttributeObjectBlock block) throws SemanticDADLException;
	String getReferenceModelClassName(Class<?> referenceModelClass);
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
	public List<Class<?>> getCandidatesForPathClass(Class<?> base);
	public List<String> getCandidatesForPathClass(String base);

	public Object bindSingleAttributeObjectBlock(SingleAttributeObjectBlock block)  throws SemanticDADLException, ReferenceModelException;
	Map<String, Object> createPathMap(ContentObject obj,
				boolean useArchetypeNodes, boolean useImplicitIndexes, List<String> startExclusions, List<String> endExclusions) throws SemanticDADLException, ReferenceModelException;
}
