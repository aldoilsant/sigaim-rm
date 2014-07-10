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
 * <p>Java class for VersionStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="VersionStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="draft"/>
 *     &lt;enumeration value="finished"/>
 *     &lt;enumeration value="update"/>
 *     &lt;enumeration value="correction"/>
 *     &lt;enumeration value="deletion"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "VersionStatus", namespace = "http://www.iso.org/iso/search.htm?qt=13606")
@XmlEnum
public enum VersionStatus {

    @XmlEnumValue("draft")
    DRAFT("draft"),
    @XmlEnumValue("finished")
    FINISHED("finished"),
    @XmlEnumValue("update")
    UPDATE("update"),
    @XmlEnumValue("correction")
    CORRECTION("correction"),
    @XmlEnumValue("deletion")
    DELETION("deletion");
    private final String value;

    VersionStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static VersionStatus fromValue(String v) {
        for (VersionStatus c: VersionStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}