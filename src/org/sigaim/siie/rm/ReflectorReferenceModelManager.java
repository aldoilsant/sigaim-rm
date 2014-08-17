package org.sigaim.siie.rm;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.openehr.am.parser.AttributeValue;
import org.openehr.am.parser.BooleanValue;
import org.openehr.am.parser.CharacterValue;
import org.openehr.am.parser.ComplexObjectBlock;
import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.IntegerValue;
import org.openehr.am.parser.KeyedObject;
import org.openehr.am.parser.MultipleAttributeObjectBlock;
import org.openehr.am.parser.ObjectBlock;
import org.openehr.am.parser.PrimitiveObjectBlock;
import org.openehr.am.parser.RealValue;
import org.openehr.am.parser.SimpleValue;
import org.openehr.am.parser.SingleAttributeObjectBlock;
import org.openehr.am.parser.StringValue;
import org.sigaim.siie.dadl.DADLManager;
import org.sigaim.siie.dadl.exceptions.SemanticDADLException;
import org.sigaim.siie.rm.exceptions.ReferenceModelException;
import org.sigaim.siie.seql.model.SEQLPath;
import org.sigaim.siie.seql.model.SEQLPathComponent;
import org.sigaim.siie.utils.Utils;

public class ReflectorReferenceModelManager implements ReferenceModelManager{
	private static org.apache.log4j.Logger log = Logger.getLogger(ReflectorReferenceModelManager.class);
	private Map<String,Class<?>> classesForString;
	private List<Class<?>> rmObjects;
	private Class<?> root;
	private DADLManager dadlManager;
	
	public boolean isRMObjectClass(Class<?> tclass) {
		return rmObjects.contains(tclass);
	}
	/**
	 * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
	 *
	 * @param packageName The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private static Map<String,Class<?>> getClasses(String packageName)
	        throws ClassNotFoundException, IOException {
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    assert classLoader != null;
	    String path = packageName.replace('.', '/');
	    Enumeration<URL> resources = classLoader.getResources(path);
	    List<File> dirs = new ArrayList<File>();
	    while (resources.hasMoreElements()) {
	        URL resource = resources.nextElement();
	        dirs.add(new File(resource.getFile().replace("%20", " ")));
	    }
	    HashMap<String,Class<?>> classes = new HashMap<String,Class<?>>();
	    for (File directory : dirs) {
	        classes.putAll(findClasses(directory, packageName));
	    }
	    return classes;
	}

	/**
	 * Recursive method used to find all classes in a given directory and subdirs.
	 *
	 * @param directory   The base directory
	 * @param packageName The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	private static HashMap<String,Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
	    HashMap<String,Class<?>> classes = new HashMap<String,Class<?>>();
	    if (!directory.exists()) {
	        return classes;
	    }
	    File[] files = directory.listFiles();
	    for (File file : files) {
	        if (file.isDirectory()) {
	            assert !file.getName().contains(".");
	            classes.putAll(findClasses(file, packageName + "." + file.getName()));
	        } else if (file.getName().endsWith(".class")) {
	        	String className=file.getName().substring(0, file.getName().length() - 6);
	            classes.put(className.toLowerCase(),Class.forName(packageName + '.' + className));
	        }
	    }
	    return classes;
	}
	private static List<Class<?>> getRMObjectClasses() {
		List<Class<?>> objects=new ArrayList<Class<?>>();
		objects.add(org.sigaim.siie.iso13606.rm.EHRExtract.class);
		objects.add(org.sigaim.siie.iso13606.rm.RecordComponent.class);
		objects.add(org.sigaim.siie.iso13606.rm.Composition.class);
		objects.add(org.sigaim.siie.iso13606.rm.Folder.class);
		objects.add(org.sigaim.siie.iso13606.rm.Content.class);
		objects.add(org.sigaim.siie.iso13606.rm.Section.class);
		objects.add(org.sigaim.siie.iso13606.rm.Entry.class);
		objects.add(org.sigaim.siie.iso13606.rm.Item.class);
		objects.add(org.sigaim.siie.iso13606.rm.Cluster.class);
		objects.add(org.sigaim.siie.iso13606.rm.Element.class);
		objects.add(org.sigaim.siie.iso13606.rm.ExtractCriteria.class);
		objects.add(org.sigaim.siie.iso13606.rm.AuditInfo.class);
		objects.add(org.sigaim.siie.iso13606.rm.AttestationInfo.class);
		objects.add(org.sigaim.siie.iso13606.rm.FunctionalRole.class);
		objects.add(org.sigaim.siie.iso13606.rm.RelatedParty.class);
		objects.add(org.sigaim.siie.iso13606.rm.Link.class);
		objects.add(org.sigaim.siie.iso13606.rm.HealthcareFacility.class);
		objects.add(org.sigaim.siie.iso13606.rm.Performer.class);
		objects.add(org.sigaim.siie.iso13606.rm.SubjectOfCare.class);
		objects.add(org.sigaim.siie.iso13606.rm.EHRSystem.class);
		return objects;
	}
	private static Map<String,Class<?>> getAllReferenceModelClasses() throws ClassNotFoundException {
		HashMap<String,Class<?>> classes=new HashMap<String,Class<?>>();
		classes.put("AD".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.AD"));
		classes.put("ADXP".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXP"));
		classes.put("ADXPADL".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPADL"));
		classes.put("ADXPAL".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPAL"));
		classes.put("ADXPBNN".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPBNN"));
		classes.put("ADXPBNR".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPBNR"));
		classes.put("ADXPBNS".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPBNS"));
		classes.put("ADXPBR".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPBR"));
		classes.put("ADXPCAR".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPCAR"));
		classes.put("ADXPCEN".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPCEN"));
		classes.put("ADXPCNT".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPCNT"));
		classes.put("ADXPCPA".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPCPA"));
		classes.put("ADXPCTY".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPCTY"));
		classes.put("ADXPDAL".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPDAL"));
		classes.put("ADXPDINST".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPDINST"));
		classes.put("ADXPDINSTA".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPDINSTA"));
		classes.put("ADXPDINSTQ".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPDINSTQ"));
		classes.put("ADXPDIR".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPDIR"));
		classes.put("ADXPDMOD".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPDMOD"));
		classes.put("ADXPDMODID".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPDMODID"));
		classes.put("ADXPINT".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPINT"));
		classes.put("ADXPPOB".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPPOB"));
		classes.put("ADXPPRE".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPPRE"));
		classes.put("ADXPSAL".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPSAL"));
		classes.put("ADXPSTA".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPSTA"));
		classes.put("ADXPSTB".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPSTB"));
		classes.put("ADXPSTR".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPSTR"));
		classes.put("ADXPSTTYP".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPSTTYP"));
		classes.put("ADXPUNID".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPUNID"));
		classes.put("ADXPUNIT".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPUNIT"));
		classes.put("ADXPZIP".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ADXPZIP"));
		classes.put("ANY".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ANY"));
		classes.put("AccessPolicy".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.AccessPolicy"));
		classes.put("ActStatus".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ActStatus"));
		classes.put("AddressPartType".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.AddressPartType"));
		classes.put("Attestation".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.Attestation"));
		classes.put("AttestationInfo".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.AttestationInfo"));
		classes.put("AuditInfo".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.AuditInfo"));
		classes.put("AuditLogConstraints".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.AuditLogConstraints"));
		classes.put("BL".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.BL"));
		classes.put("CD".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.CD"));
		classes.put("CDCV".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.CDCV"));
		classes.put("CO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.CO"));
		classes.put("CS".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.CS"));
		classes.put("CalendarCycle".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.CalendarCycle"));
		classes.put("Cluster".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.Cluster"));
		classes.put("CodingRationale".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.CodingRationale"));
		classes.put("Composition".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.Composition"));
		classes.put("Compression".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.Compression"));
		classes.put("Content".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.Content"));
		classes.put("DemographicExtract".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.DemographicExtract"));
		classes.put("ED".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ED"));
		classes.put("EDTEXT".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.EDTEXT"));
		classes.put("EHRAuditLogEntry".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.EHRAuditLogEntry"));
		classes.put("EHRAuditLogExtract".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.EHRAuditLogExtract"));
		classes.put("EHRExtract".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.EHRExtract"));
		classes.put("EHRSystem".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.EHRSystem"));
		classes.put("EIVLTS".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.EIVLTS"));
		classes.put("EIVLTSDATE".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.EIVLTSDATE"));
		classes.put("EIVLTSDATETIME".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.EIVLTSDATETIME"));
		classes.put("EN".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.EN"));
		classes.put("ENXP".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ENXP"));
		classes.put("Element".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.Element"));
		classes.put("EntityNamePartQualifier".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.EntityNamePartQualifier"));
		classes.put("EntityNamePartType".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.EntityNamePartType"));
		classes.put("EntityNameUse".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.EntityNameUse"));
		classes.put("Entry".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.Entry"));
		classes.put("ExtractCriteria".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ExtractCriteria"));
		classes.put("Folder".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.Folder"));
		classes.put("FunctionalRole".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.FunctionalRole"));
		classes.put("FunctionalRoles".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.FunctionalRoles"));
		classes.put("HXIT".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.HXIT"));
		classes.put("HealthcareFacility".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.HealthcareFacility"));
		classes.put("HealthcareProfessionalRole".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.HealthcareProfessionalRole"));
		classes.put("II".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.II"));
		classes.put("INT".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.INT"));
		classes.put("INTNONNEG".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.INTNONNEG"));
		classes.put("INTPOS".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.INTPOS"));
		classes.put("ISO3166CountyCode".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ISO3166CountyCode"));
		classes.put("IVLCO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLCO"));
		classes.put("IVLHIGHCO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLHIGHCO"));
		classes.put("IVLHIGHINT".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLHIGHINT"));
		classes.put("IVLHIGHMO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLHIGHMO"));
		classes.put("IVLHIGHPQ".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLHIGHPQ"));
		classes.put("IVLHIGHPQTIME".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLHIGHPQTIME"));
		classes.put("IVLHIGHREAL".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLHIGHREAL"));
		classes.put("IVLHIGHRTO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLHIGHRTO"));
		classes.put("IVLHIGHTS".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLHIGHTS"));
		classes.put("IVLHIGHTSDATE".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLHIGHTSDATE"));
		classes.put("IVLHIGHTSDATETIME".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLHIGHTSDATETIME"));
		classes.put("IVLINT".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLINT"));
		classes.put("IVLLOWCO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLLOWCO"));
		classes.put("IVLLOWINT".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLLOWINT"));
		classes.put("IVLLOWMO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLLOWMO"));
		classes.put("IVLLOWPQ".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLLOWPQ"));
		classes.put("IVLLOWPQTIME".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLLOWPQTIME"));
		classes.put("IVLLOWREAL".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLLOWREAL"));
		classes.put("IVLLOWRTO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLLOWRTO"));
		classes.put("IVLLOWTS".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLLOWTS"));
		classes.put("IVLLOWTSDATE".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLLOWTSDATE"));
		classes.put("IVLLOWTSDATETIME".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLLOWTSDATETIME"));
		classes.put("IVLMO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLMO"));
		classes.put("IVLPQ".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLPQ"));
		classes.put("IVLPQTIME".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLPQTIME"));
		classes.put("IVLREAL".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLREAL"));
		classes.put("IVLRTO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLRTO"));
		classes.put("IVLTS".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLTS"));
		classes.put("IVLTSDATE".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLTSDATE"));
		classes.put("IVLTSDATETIME".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLTSDATETIME"));
		classes.put("IVLWIDTHCO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLWIDTHCO"));
		classes.put("IVLWIDTHTS".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLWIDTHTS"));
		classes.put("IVLWIDTHTSDATE".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IVLWIDTHTSDATE"));
		classes.put("IdentifiedEntity".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IdentifiedEntity"));
		classes.put("IdentifiedHealthcareProfessional".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IdentifiedHealthcareProfessional"));
		classes.put("IdentifierReliability".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IdentifierReliability"));
		classes.put("IdentifierScope".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IdentifierScope"));
		classes.put("IntegrityCheckAlgorithm".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.IntegrityCheckAlgorithm"));
		classes.put("Item".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.Item"));
		classes.put("ItemCategory".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ItemCategory"));
		classes.put("Link".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.Link"));
		classes.put("LinkNature".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.LinkNature"));
		classes.put("LinkRole".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.LinkRole"));
		classes.put("MO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.MO"));
		classes.put("MaxSensitivityConstraints".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.MaxSensitivityConstraints"));
		classes.put("Mode".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.Mode"));
		classes.put("NullFlavor".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.NullFlavor"));
		classes.put("ObjectFactory".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ObjectFactory"));
		classes.put("Organisation".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.Organisation"));
		classes.put("PIVLTS".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.PIVLTS"));
		classes.put("PIVLTSDATE".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.PIVLTSDATE"));
		classes.put("PIVLTSDATETIME".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.PIVLTSDATETIME"));
		classes.put("PQ".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.PQ"));
		classes.put("PQR".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.PQR"));
		classes.put("PQTIME".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.PQTIME"));
		classes.put("PQV".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.PQV"));
		classes.put("Performer".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.Performer"));
		classes.put("Person".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.Person"));
		classes.put("PostalAddressUse".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.PostalAddressUse"));
		classes.put("QSDCO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSDCO"));
		classes.put("QSDINT".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSDINT"));
		classes.put("QSDMO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSDMO"));
		classes.put("QSDPQ".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSDPQ"));
		classes.put("QSDPQTIME".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSDPQTIME"));
		classes.put("QSDREAL".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSDREAL"));
		classes.put("QSDRTO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSDRTO"));
		classes.put("QSDTS".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSDTS"));
		classes.put("QSDTSDATE".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSDTSDATE"));
		classes.put("QSDTSDATETIME".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSDTSDATETIME"));
		classes.put("QSETBOUNDEDPIVL".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSETBOUNDEDPIVL"));
		classes.put("QSETCO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSETCO"));
		classes.put("QSETINT".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSETINT"));
		classes.put("QSETMO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSETMO"));
		classes.put("QSETPQ".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSETPQ"));
		classes.put("QSETPQTIME".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSETPQTIME"));
		classes.put("QSETREAL".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSETREAL"));
		classes.put("QSETRTO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSETRTO"));
		classes.put("QSETTS".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSETTS"));
		classes.put("QSETTSDATE".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSETTSDATE"));
		classes.put("QSETTSDATETIME".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSETTSDATETIME"));
		classes.put("QSICO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSICO"));
		classes.put("QSIINT".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSIINT"));
		classes.put("QSIMO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSIMO"));
		classes.put("QSIPQ".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSIPQ"));
		classes.put("QSIPQTIME".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSIPQTIME"));
		classes.put("QSIREAL".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSIREAL"));
		classes.put("QSIRTO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSIRTO"));
		classes.put("QSITS".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSITS"));
		classes.put("QSITSDATE".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSITSDATE"));
		classes.put("QSITSDATETIME".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSITSDATETIME"));
		classes.put("QSPCO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSPCO"));
		classes.put("QSPINT".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSPINT"));
		classes.put("QSPMO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSPMO"));
		classes.put("QSPPQ".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSPPQ"));
		classes.put("QSPPQTIME".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSPPQTIME"));
		classes.put("QSPREAL".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSPREAL"));
		classes.put("QSPRTO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSPRTO"));
		classes.put("QSPTS".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSPTS"));
		classes.put("QSPTSDATE".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSPTSDATE"));
		classes.put("QSPTSDATETIME".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSPTSDATETIME"));
		classes.put("QSSCO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSSCO"));
		classes.put("QSSINT".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSSINT"));
		classes.put("QSSMO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSSMO"));
		classes.put("QSSPQ".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSSPQ"));
		classes.put("QSSPQTIME".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSSPQTIME"));
		classes.put("QSSREAL".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSSREAL"));
		classes.put("QSSRTO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSSRTO"));
		classes.put("QSSTS".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSSTS"));
		classes.put("QSSTSDATE".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSSTSDATE"));
		classes.put("QSSTSDATETIME".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSSTSDATETIME"));
		classes.put("QSUCO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSUCO"));
		classes.put("QSUINT".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSUINT"));
		classes.put("QSUMO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSUMO"));
		classes.put("QSUPQ".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSUPQ"));
		classes.put("QSUPQTIME".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSUPQTIME"));
		classes.put("QSUREAL".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSUREAL"));
		classes.put("QSURTO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSURTO"));
		classes.put("QSUTS".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSUTS"));
		classes.put("QSUTSDATE".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSUTSDATE"));
		classes.put("QSUTSDATETIME".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QSUTSDATETIME"));
		classes.put("QTY".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.QTY"));
		classes.put("REAL".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.REAL"));
		classes.put("RTO".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.RTO"));
		classes.put("RecordComponent".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.RecordComponent"));
		classes.put("RelatedParty".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.RelatedParty"));
		classes.put("ReportStatus".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ReportStatus"));
		classes.put("Request".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.Request"));
		classes.put("SC".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.SC"));
		classes.put("ST".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.ST"));
		classes.put("STNT".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.STNT"));
		classes.put("Section".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.Section"));
		classes.put("SoftwareOrDevice".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.SoftwareOrDevice"));
		classes.put("StructureType".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.StructureType"));
		classes.put("SubjectOfCare".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.SubjectOfCare"));
		classes.put("SubjectOfCarePersonIdentification".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.SubjectOfCarePersonIdentification"));
		classes.put("SubjectOfInformationCategory".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.SubjectOfInformationCategory"));
		classes.put("TEL".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.TEL"));
		classes.put("TELURL".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.TELURL"));
		classes.put("TS".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.TS"));
		classes.put("TSDATE".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.TSDATE"));
		classes.put("TSDATEFULL".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.TSDATEFULL"));
		classes.put("TSDATETIME".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.TSDATETIME"));
		classes.put("TSDATETIMEFULL".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.TSDATETIMEFULL"));
		classes.put("Target".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.Target"));
		classes.put("TelecommunicationAddressUse".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.TelecommunicationAddressUse"));
		classes.put("TimingEvent".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.TimingEvent"));
		classes.put("UncertaintyType".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.UncertaintyType"));
		classes.put("UpdateMode".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.UpdateMode"));
		classes.put("VersionStatus".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.VersionStatus"));
		classes.put("XReference".toLowerCase(),Class.forName("org.sigaim.siie.iso13606.rm.XReference"));
		return classes;
	}
	
	public ReflectorReferenceModelManager(DADLManager dadlManager) {
		this.dadlManager=dadlManager;
		try {
			classesForString=ReflectorReferenceModelManager.getAllReferenceModelClasses();
			rmObjects=ReflectorReferenceModelManager.getRMObjectClasses();
			log.info("Loaded "+classesForString.size()+" reference model classes");
		} catch(Exception e){
			e.printStackTrace();
		}
		root=org.sigaim.siie.iso13606.rm.EHRExtract.class;
	}
		
	@Override
	public Class<?> referenceModelClassFromString(String sclass) {
		return classesForString.get(sclass.toLowerCase());
	}
	
	public boolean isReferenceModelClass(Class<?> tclass) {
		String className=tclass.getSimpleName().toLowerCase();
		if(classesForString.get(className)!=null) {
			return true;
		} else return false;
	}
	@Override
	public List<String> getSubclassesOrSelf(String base) {
		base=Utils.toUppercaseNotation(base);
		List<String> ret=new ArrayList<String>();
		Class<?> theClass=this.referenceModelClassFromString(base);
		List<Class<?>> subclassesOrSelf=this.getSubclassesOrSelf(theClass);
		for(Class<?> subClass : subclassesOrSelf) {
			ret.add(Utils.toUppercaseNotation(subClass.getSimpleName()));
		}
		return ret;
	}
	@Override
	public List<Class<?>> getSubclassesOrSelf(Class<?> base) {
		ArrayList<Class<?>> ret=new ArrayList<Class<?>>();
		for(Class<?> rmClass : classesForString.values()) {
			if(base.isAssignableFrom(rmClass)) {
				ret.add(rmClass);
			}
		}
		return ret;
	}
	@Override
	public ObjectBlock solveReferenceModelPath(SingleAttributeObjectBlock block, List<SEQLPathComponent> components) throws ReferenceModelException { 
		//Use the path components to solve the serialized value 
		if(components.size()==0) return block;
		List<SEQLPathComponent> subComponents=new ArrayList<SEQLPathComponent>(components);
		SEQLPathComponent component=subComponents.remove(0);
		String attributeName=component.getPathIdentifier().toLowerCase();
		for(AttributeValue value : block.getAttributeValues()) {
			if(value.getId().equals(attributeName)) {
				ObjectBlock child=value.getValue();
				if(child instanceof SingleAttributeObjectBlock) {
					//recurse
					return this.solveReferenceModelPath((SingleAttributeObjectBlock)child, subComponents);
				} else if(child instanceof MultipleAttributeObjectBlock) {
					//Use the predicate as key of the collection
					MultipleAttributeObjectBlock mblock=(MultipleAttributeObjectBlock)child;
					//Iterate over the key objects and find the correct key
					for(KeyedObject keyedObject: mblock.getKeyObjects()) {
						String key=this.dadlManager.serializeSimpleValue(keyedObject.getKey());
						if(key.equals(component.getPathPredicate().getKey2())) {
							ObjectBlock kvalue=keyedObject.getObject();
							if(kvalue instanceof SingleAttributeObjectBlock) {
								return this.solveReferenceModelPath((SingleAttributeObjectBlock)kvalue, subComponents);
							} else { //Primitive
								if(subComponents.size()>0) {
									throw new ReferenceModelException("Found path dereferencing primitive object block");
								}
								return kvalue;
							}
						}
					}
					throw new ReferenceModelException("Unable to solve path "+component+" for block "+block);
				} else if (child instanceof PrimitiveObjectBlock) {
					if(subComponents.size()>0) {
						throw new ReferenceModelException("Found path dereferencing primitive object block");
					}
					return child;
				}
			}
		}
		return null;
		//throw new ReferenceModelException("Unknown path component "+attributeName+" for block "+block);
	}
	private Class<?> getPathTypeForComponent(Class<?> referenceModelClass, List<SEQLPathComponent> components) {
		if(components.size()==0) return referenceModelClass;
		List<SEQLPathComponent> subComponents=new ArrayList<SEQLPathComponent>(components);
		SEQLPathComponent component=subComponents.remove(0);
		String attributeName=component.getPathIdentifier().toLowerCase();
		try {
			PropertyDescriptor[] descriptors=Introspector.getBeanInfo(referenceModelClass).getPropertyDescriptors();
			for(PropertyDescriptor descriptor: descriptors) {
				Method readMethod=descriptor.getReadMethod();
				if(readMethod!=null) {
					String methodName=readMethod.getName().toLowerCase();
					if(methodName.equals("get"+attributeName)) {
						Type t=readMethod.getGenericReturnType();
						if( t instanceof ParameterizedType ) {
					    	ParameterizedType pt=(ParameterizedType)t;
					    	Type[] parameters=pt.getActualTypeArguments();
					    	referenceModelClass=(Class<?>) parameters[0];
						} else if(t instanceof Class) {
						    referenceModelClass=(Class<?>) t;
						}
						List<Class<?>> subclasses=this.getSubclassesOrSelf(referenceModelClass);
						for(Class<?> subclass : subclasses) {
							Class<?> ret=this.getPathTypeForComponent(subclass, subComponents);
							if(ret!=null) {
								return ret;
							}
						}
						return null;
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Class<?> getPathType(String sreferenceModelClass, SEQLPath path) {
		Class<?> referenceModelClass=null;
		boolean isAbsolute=false;
		if(sreferenceModelClass==null) {
			referenceModelClass=root;
			isAbsolute=true;
		} else {
			referenceModelClass=this.referenceModelClassFromString(Utils.toUppercaseNotation(sreferenceModelClass));
		}
		List<SEQLPathComponent> components=path.getPathComponents();
		if(components==null || components.size()==0) {
			return this.referenceModelClassFromString(sreferenceModelClass);
		}
		path=path.toUppercaseNotation();
		components=path.getPathComponents();
		if(!isAbsolute) { //remove identifiedVariable
			components.remove(0);
		}
		List<Class<?>> subclasses=this.getSubclassesOrSelf(referenceModelClass);
		for(Class<?> subclass : subclasses) {
			Class<?> ret=this.getPathTypeForComponent(subclass, components);
			if(ret!=null) return ret;
		}
		return null;
	}
	private Object bindMultipleAttributeObjectBlock(MultipleAttributeObjectBlock block) throws SemanticDADLException, ReferenceModelException {
		if(block.getKeyObjects().size()==0) return null;
		SimpleValue keySample=block.getKeyObjects().get(0).getKey();
		if(keySample instanceof IntegerValue) { //Cast to List
			ArrayList multipleValue=new ArrayList();
			for(KeyedObject obj : block.getKeyObjects()) {
				Object value=this.bindObjectBlock(obj.getObject());
				int key=((IntegerValue)obj.getKey()).getValue().intValue()-1; //Note indexes start with 1
				multipleValue.add(key,value);
			}
			for(Object instance : multipleValue) {
				if(instance==null) {
					log.warn("Bind index mismatch");
				}
			}
			return multipleValue;
		} else { //Cast to map
			HashMap map=new HashMap();
			for(KeyedObject obj : block.getKeyObjects()) {
				Object value=this.bindObjectBlock(obj.getObject());
				Object key=(obj.getKey()).getValue();
				map.put(key,value);
			}
			return map;
		}
	}
	private Object bindComplexObjectBlock(ComplexObjectBlock block) throws SemanticDADLException, ReferenceModelException {
		if(block instanceof MultipleAttributeObjectBlock) {
			return this.bindMultipleAttributeObjectBlock((MultipleAttributeObjectBlock)block);
		} else {
			return this.bindSingleAttributeObjectBlock((SingleAttributeObjectBlock)block);
		}
	}
	private Object bindPrimitiveObjectBlock(PrimitiveObjectBlock block) throws SemanticDADLException, ReferenceModelException  {
		if(block.getSimpleValue()!=null) {
			return block.getSimpleValue().getValue();
		} else {
			throw new ReferenceModelException("Binding of primitive values other than SimpleValue is not yet implemented");
		}
	}
	private Object bindObjectBlock(ObjectBlock block) throws SemanticDADLException, ReferenceModelException {
		if(block instanceof ComplexObjectBlock) {
			return this.bindComplexObjectBlock((ComplexObjectBlock) block);
		} else {
			return this.bindPrimitiveObjectBlock((PrimitiveObjectBlock) block);
		}
	}
	public String getReferenceModelClassName(SingleAttributeObjectBlock block) throws SemanticDADLException {
		String referenceModelClassName=null;
		for(AttributeValue value : block.getAttributeValues()) {
			if(value.getId().equals("reference_model_class_name")) {
				try {
					PrimitiveObjectBlock pblock=(PrimitiveObjectBlock)value.getValue();
					referenceModelClassName=Utils.toUppercaseNotation(pblock.getSimpleValue().getValue().toString().toLowerCase());
				} catch(Exception e) {
					throw new SemanticDADLException("Invalid value for referenceModelClassName");
				}
				break;
			}
		}
		/*if(referenceModelClassName==null) {
			throw new SemanticDADLException("Missing reference model class name for block: "+block);
		}*/
		return referenceModelClassName;
	}
	@Override
	public Object bindSingleAttributeObjectBlock(SingleAttributeObjectBlock block)  throws SemanticDADLException, ReferenceModelException{
		//Attempt to get the reference model class name
		String referenceModelClassName=null;
		try {
			referenceModelClassName=this.getReferenceModelClassName(block);
		} catch(Exception e) {}
		if(referenceModelClassName==null) {
			return null;
			//throw new SemanticDADLException("Object blocks must have the mandatory field referenceModelClassName");
		}
		Class<?> objectClass=this.referenceModelClassFromString(referenceModelClassName);
		if(objectClass==null) {
			throw new SemanticDADLException("Uknown reference model class: "+referenceModelClassName);
		}
		//Create instance of the class
		Object instance=null;
		try {
			instance=objectClass.newInstance();
		} catch(Exception e) {
			throw new ReferenceModelException("Error creating reference model class instance");
		}
		//Now bind the attributes using setters. For that, we have to recursively instance each value
		try {
			for(AttributeValue value : block.getAttributeValues()) {
				String id=Utils.toUppercaseNotation(value.getId()).toLowerCase();
				if(!id.equals("referencemodelclassname")) {
					Object setterParameter=this.bindObjectBlock(value.getValue());
					if(setterParameter==null) continue;
					//Attempt to find the property setter
					PropertyDescriptor[] descriptors=Introspector.getBeanInfo(objectClass).getPropertyDescriptors();
					boolean found=false;
					for(PropertyDescriptor descriptor: descriptors) {
						if(Collection.class.isAssignableFrom(setterParameter.getClass())) {
							//We must use the getter and add
							Method method=descriptor.getReadMethod();
							if(method!=null) {
								String methodName=method.getName().toLowerCase();
								if(methodName.equals("get"+id)) {
									//Found getter
									Object collection=method.invoke(instance);
									Class collectionClass=collection.getClass();
									for(Method collectionMethod:collectionClass.getMethods()) {
										String addMethodName=collectionMethod.getName();
										if(addMethodName.equals("addAll") && collectionMethod.getParameterTypes().length==1) {
											//Call add method
											collectionMethod.invoke(collection, setterParameter);
											found=true;
											break;
										} else if(addMethodName.equals("putAll") && collectionMethod.getParameterTypes().length==1) {
											collectionMethod.invoke(collection, setterParameter);
											found=true;
											break;
										}
									}
								}
							}
						} else {
							Method method=descriptor.getWriteMethod();
							if(method!=null) {
								String methodName=method.getName().toLowerCase();
								if(methodName.equals("set"+id)) {
									//Found setter
									if(setterParameter==null) {
										throw new ReferenceModelException("Unable to bind object for attribute"+value.getId());
									}
									if(!method.getParameterTypes()[0].isAssignableFrom(setterParameter.getClass())) {
										//Attempt primitive to object binding
										if(setterParameter instanceof Boolean && method.getParameterTypes()[0].equals(boolean.class)) {
											//Use autoboxing
											method.invoke(instance, setterParameter);
										} else if(setterParameter instanceof Double && method.getParameterTypes()[0].equals(double.class)) {
											//Use autoboxing
											method.invoke(instance, setterParameter);
										} else if(setterParameter instanceof Integer && method.getParameterTypes()[0].equals(int.class)) {
											//Use autoboxing
											method.invoke(instance, setterParameter);
										} 
										else if(setterParameter instanceof String && method.getParameterTypes()[0].equals(XMLGregorianCalendar.class)) {
											XMLGregorianCalendar xgc=DatatypeFactory.newInstance().newXMLGregorianCalendar((String)setterParameter);
											try {
												method.invoke(instance, xgc);
											} catch(Exception e) {
												e.printStackTrace();
												throw new ReferenceModelException("Unable to convert enum value "+setterParameter);
											}
										} else if(setterParameter instanceof String) {
											Class<?> parameterType=method.getParameterTypes()[0];
											if(parameterType.isEnum()) {
												try {
													method.invoke(instance, Enum.valueOf((Class<Enum>) parameterType, (String)setterParameter));
												} catch(Exception e) {
													e.printStackTrace();
													throw new ReferenceModelException("Unable to convert enum value "+setterParameter);
												}
											} else {
												throw new ReferenceModelException("Unable to cast object "+setterParameter+" to "+method.getParameterTypes()[0].getName());
											}
										} else {
											throw new ReferenceModelException("Unable to cast object "+setterParameter+" to "+method.getParameterTypes()[0].getName());
										}
									} else {
										method.invoke(instance, setterParameter);
									}
									found=true;
									break;
								}
							}
						}
					}
					if(!found) {
						throw new ReferenceModelException("Unable to get setter for class "+objectClass.getName()+" and attribute "+value.getId() + " with value "+setterParameter);
					}
				}	
			}
		} 
		catch(ReferenceModelException e) {
			throw e;
		} catch(SemanticDADLException e2) {
			throw e2;
		}
		catch(Exception e3) {
			e3.printStackTrace();
			throw new ReferenceModelException("Error binding attributes to class "+objectClass.getName());
		}
		return instance;
	}
	public Object bind(ContentObject obj) throws SemanticDADLException, ReferenceModelException{
		if(obj.getAttributeValues()!=null) {
			throw new SemanticDADLException("Root of DADL reference model file must be an object block");
		} else {
			ComplexObjectBlock block=obj.getComplexObjectBlock();
			//Find the reference model class
			if(!(block instanceof SingleAttributeObjectBlock)) {
				throw new SemanticDADLException("Root of DADL reference model file must be a single attribute object block");
			} else {
				SingleAttributeObjectBlock sblock=(SingleAttributeObjectBlock) block;
				return this.bindSingleAttributeObjectBlock(sblock);
			}
		}
	}
	
	private PrimitiveObjectBlock createSingleValuePrimitiveObjectBlock(SimpleValue value) {
		return new PrimitiveObjectBlock(null,value,null,null,null,null);
	}
	public SingleAttributeObjectBlock getSingleAttributeObjectBlockFromContentObject(ContentObject obj) throws SemanticDADLException{
		if(obj.getAttributeValues()!=null) {
			throw new SemanticDADLException("Root  must be an object block");
		} else {
			ComplexObjectBlock block=obj.getComplexObjectBlock();
			//Find the reference model class
			if(!(block instanceof SingleAttributeObjectBlock)) {
				throw new SemanticDADLException("Root must be a single attribute object block");
			} else {
				SingleAttributeObjectBlock sblock=(SingleAttributeObjectBlock) block;
				return sblock;
			}
		}
		
	}
	private SimpleValue unbindSimpleValue(Object obj) throws ReferenceModelException  {
		if(obj instanceof Boolean) {
			return new BooleanValue((Boolean)obj);
		} else if(obj instanceof Character) {
			return new CharacterValue((Character)obj);
		} else if(obj instanceof Integer) {
			return  new IntegerValue((Integer)obj);
		} else if(obj instanceof Double) {
			return new RealValue((Double) obj);
		} else if(obj instanceof Float) {
			//Without precision loss
			Float f=(Float) obj;
			float fv=f.floatValue();
			double dv=(double)fv;
			return new RealValue(dv); //autoboxing
		} else if(obj instanceof String) {
			return new StringValue(""+obj);
		} else if(obj instanceof XMLGregorianCalendar) {
			return new StringValue(obj.toString());
		} else if(obj instanceof Enum) {
			return new StringValue(""+((Enum)obj));
		} else {
			throw new ReferenceModelException("Cannot serialize object of class "+obj.getClass().getName()+": not implemented");
		}
	}
	private PrimitiveObjectBlock unbindPrimitiveObject(Object obj) throws ReferenceModelException {
		return this.createSingleValuePrimitiveObjectBlock(this.unbindSimpleValue(obj));
	}
	
	private MultipleAttributeObjectBlock unbindCollection(Collection col) throws ReferenceModelException{
		List<KeyedObject> kobjects=new ArrayList<KeyedObject>();
		if(List.class.isAssignableFrom(col.getClass())) {
			int i=1;
			for(Object contained: (List)col) {
				KeyedObject obj= new KeyedObject(new IntegerValue(new Integer(i++)),this.unbindGeneric(contained));
				kobjects.add(obj);
			}
			if(((List)col).size()!=i-1) {
				log.warn("Index mismatch");
			}
		} else if(Map.class.isAssignableFrom(col.getClass())) { 
			Map colMap=(Map)col;
			for(Object key : colMap.keySet()) {
				KeyedObject obj= new KeyedObject(this.unbindSimpleValue(key),this.unbindGeneric(colMap.get(key)));
				kobjects.add(obj);
			}
			
		} else throw new ReferenceModelException("Cannot serialize collection of type "+col.getClass().getName()+": not implemented");
		return new MultipleAttributeObjectBlock(null, kobjects);
	}
	@Override 
	public ObjectBlock unbindGeneric(Object obj) throws ReferenceModelException {
		if(this.isReferenceModelClass(obj.getClass()) && !(obj instanceof Enum)) {
			return this.unbindObject(obj);
		} else if(Collection.class.isAssignableFrom(obj.getClass())) {
			return this.unbindCollection((Collection)obj);
		} else {
			return this.unbindPrimitiveObject(obj);
		}
	}
	public long millisecondsFromInterval(long start, long end) {
		return (end-start)/(1000*1000);
	}
	private SingleAttributeObjectBlock unbindObject(Object object) {
		//Iterate all getters. If it is a primitive create a primitive, if it is a collection
		//create a keyed object, and if not create another single attribute object
		try {
			List<AttributeValue> attributeValues=new ArrayList<AttributeValue>();
			//Add the reference model class name attribute
			attributeValues.add(new AttributeValue("reference_model_class_name",
					new PrimitiveObjectBlock(null,new StringValue(object.getClass().getSimpleName()),null,null,null,null)
					)
			);
			//This way we ignore Object class properties
			PropertyDescriptor[] descriptors=Introspector.getBeanInfo(object.getClass()).getPropertyDescriptors();
			for(PropertyDescriptor descriptor : descriptors) {
				Method readMethod=descriptor.getReadMethod();
				if(readMethod==null) { //Hackfix for wrong isXXX Boolean properties
					Method writeMethod=descriptor.getWriteMethod();
					if(writeMethod.getParameterTypes()[0].equals(Boolean.class)) {
						String readMethodName="is"+descriptor.getName().substring(0, 1).toUpperCase() + descriptor.getName().substring(1);
						Method booleanGetterMethod=object.getClass().getMethod(readMethodName, null);
						if(booleanGetterMethod!=null && booleanGetterMethod.getReturnType().equals(Boolean.class)) {
							readMethod=booleanGetterMethod;
						}
					}
				}
				if(readMethod!=null && !readMethod.getName().equals("getClass")) { //Hackfix for slow introspector
					//Invoke the reader 
					Object propertyObject=readMethod.invoke(object);
					ObjectBlock unbinded;
					if(propertyObject!=null) {
						unbinded=this.unbindGeneric(propertyObject);
						if(unbinded!=null) {
							AttributeValue newValue=new AttributeValue(Utils.toUnderscoreNotation(descriptor.getName()),unbinded);
							attributeValues.add(newValue);
						}
					}
				}
			}
			return new SingleAttributeObjectBlock(null, attributeValues);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public ContentObject unbind(Object root) throws ReferenceModelException {
		//Each object is a singleattributeobjectblock
		//Each collection is a multipleattributeobjectblock
		//A primitive is a PrimitiveObjectBlock that has a given value
		SingleAttributeObjectBlock sblock=this.unbindObject(root);
		return new ContentObject(null,sblock);
	}
	private SimpleValue getSimpleValueFromObjectBlock(SingleAttributeObjectBlock block) {
		for(AttributeValue vl: block.getAttributeValues()) {
			if(vl.getId().equals("reference_model_class_name")) {
				return ((PrimitiveObjectBlock) vl.getValue()).getSimpleValue();
			}
		}
		return null;
	}

	private SEQLPathComponent createPathComponentFromSingleAttributeObjectBlock(String id, SingleAttributeObjectBlock block, SimpleValue collectionKey) throws ReferenceModelException{
		//Create the path component. Add the id and possible the collection "index"
		String sPath=id;
		if(collectionKey!=null) {
			sPath+="["+this.dadlManager.serializeSimpleValue(collectionKey)+"]";
		}
		return new SEQLPathComponent(sPath);
	}
	
	@Override
	public Map<SEQLPathComponent,SingleAttributeObjectBlock> splitForRMObjectVsDataObject(SingleAttributeObjectBlock block) throws SemanticDADLException, ReferenceModelException{
		//Recurse and if we find a collection or a single attribute value that is an rmobject add it to the list
		//We need some kind of path to "store" these other objects, that is, save the relation with the input block
		Map<SEQLPathComponent, SingleAttributeObjectBlock> ret=new HashMap<SEQLPathComponent, SingleAttributeObjectBlock>();
		List<AttributeValue> toRemove=new ArrayList<AttributeValue>();
		//First, we iterate all the data values
		for(AttributeValue value : block.getAttributeValues()) {
			ObjectBlock atvalue=value.getValue();
			//The only option for being an rmobject is a ComplexObjectBlock
			if(atvalue instanceof ComplexObjectBlock) {
				//Either a collection or another object
				if(atvalue instanceof SingleAttributeObjectBlock) {
					//Object, directly test for rmobject class
					SingleAttributeObjectBlock sblock=(SingleAttributeObjectBlock) atvalue;
					String referenceModelClassName=this.getReferenceModelClassName(sblock);
					if(referenceModelClassName==null) {
						//Continue: missing value;
						continue;
					}
					Class<?> referenceModelClass=this.referenceModelClassFromString(referenceModelClassName);
					if(this.isRMObjectClass(referenceModelClass)) {
						toRemove.add(value);
						ret.put(this.createPathComponentFromSingleAttributeObjectBlock(value.getId(),sblock,null), sblock);
						//Remove the block and add it to the result. 
					} //Else nothing. A data value that will be serialized in with the container rm object
				} else { //MultipleAttributeObjectBlock, collection
					MultipleAttributeObjectBlock mblock=(MultipleAttributeObjectBlock) atvalue;
					//Test if this is a primitive collection or a rmobject collection
					if(mblock.getKeyObjects().size()>0) {
						ComplexObjectBlock tblock=(ComplexObjectBlock)mblock.getKeyObjects().get(0).getObject();
						if(tblock instanceof SingleAttributeObjectBlock) {
							SingleAttributeObjectBlock sblock=(SingleAttributeObjectBlock) tblock;
							String referenceModelClassName=this.getReferenceModelClassName(sblock);
							Class<?> referenceModelClass=this.referenceModelClassFromString(referenceModelClassName);
							if(this.isRMObjectClass(referenceModelClass)) {
								toRemove.add(value);
								//Iterate the collection and create the paths
								for(KeyedObject keyedObject : mblock.getKeyObjects()) {
									ret.put(this.createPathComponentFromSingleAttributeObjectBlock(value.getId(),sblock, keyedObject.getKey()),(SingleAttributeObjectBlock)keyedObject.getObject());
								}
							}
						} else {
							log.warn("Keyed object is not single attribute!");
						}
					}
				}
			} 
		}
		block.getAttributeValues().removeAll(toRemove);
		return ret;
	}

	protected SEQLPath getArchetypeIdPath() {
		return  new SEQLPath("meaning/code_system_name");
		//return  new SEQLPath("archetype_id/root");
	}

	protected SEQLPath getArchetypeNodePath() {
		return new SEQLPath("archetype_id/extension");
	}
	public String getArchetypeIdForRMObject(SingleAttributeObjectBlock block) throws ReferenceModelException {
		ObjectBlock oblock=this.solveReferenceModelPath(block, this.getArchetypeIdPath().getPathComponents());
		if(oblock==null) return null;
		if(!(oblock instanceof PrimitiveObjectBlock)) {
			throw new ReferenceModelException("Archetype id path is not a primitive object block");
		}
		PrimitiveObjectBlock pblock=(PrimitiveObjectBlock) oblock;
		SimpleValue value=pblock.getSimpleValue();
		if(value==null) {
			throw new ReferenceModelException("Invalid primitive object block for archetype id");
		}
		return value.getValue().toString();
	}
	public String getArchetypeNodeIdForRMObject(SingleAttributeObjectBlock block) throws ReferenceModelException {
		ObjectBlock oblock=this.solveReferenceModelPath(block, this.getArchetypeNodePath().getPathComponents());
		if(oblock==null) return null;
		if(!(oblock instanceof PrimitiveObjectBlock)) {
			throw new ReferenceModelException("Archetype id path is not a primitive object block");
		}
		PrimitiveObjectBlock pblock=(PrimitiveObjectBlock) oblock;
		SimpleValue value=pblock.getSimpleValue();
		if(value==null) {
			throw new ReferenceModelException("Invalid primitive object block for archetype id");
		}
		return value.getValue().toString();
	}
	@Override
	public Class<?> getRootClass() {
		return org.sigaim.siie.iso13606.rm.EHRSystem.class;
	}
	public boolean isArchetypedClass(Class<?> tclass) {
		return org.sigaim.siie.iso13606.rm.RecordComponent.class.isAssignableFrom(tclass);
	}
}
