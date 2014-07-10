//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.04 at 02:11:57 AM CEST 
//


package org.sigaim.siie.iso13606.rm;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SubjectOfInformationCategory.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SubjectOfInformationCategory">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="subjectOfCare"/>
 *     &lt;enumeration value="relativeSubjectOfCare"/>
 *     &lt;enumeration value="foetus"/>
 *     &lt;enumeration value="mother"/>
 *     &lt;enumeration value="donor"/>
 *     &lt;enumeration value="unrelatedPerson"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SubjectOfInformationCategory", namespace = "http://www.iso.org/iso/search.htm?qt=13606")
@XmlEnum
public enum SubjectOfInformationCategory {

    @XmlEnumValue("subjectOfCare")
    SUBJECT_OF_CARE("subjectOfCare"),
    @XmlEnumValue("relativeSubjectOfCare")
    RELATIVE_SUBJECT_OF_CARE("relativeSubjectOfCare"),
    @XmlEnumValue("foetus")
    FOETUS("foetus"),
    @XmlEnumValue("mother")
    MOTHER("mother"),
    @XmlEnumValue("donor")
    DONOR("donor"),
    @XmlEnumValue("unrelatedPerson")
    UNRELATED_PERSON("unrelatedPerson");
    private final String value;

    SubjectOfInformationCategory(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SubjectOfInformationCategory fromValue(String v) {
        for (SubjectOfInformationCategory c: SubjectOfInformationCategory.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}