//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.04 at 02:11:57 AM CEST 
//


package org.sigaim.siie.iso13606.rm;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EntityNamePartType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EntityNamePartType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="FAM"/>
 *     &lt;enumeration value="GIV"/>
 *     &lt;enumeration value="PFX"/>
 *     &lt;enumeration value="SFX"/>
 *     &lt;enumeration value="DEL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EntityNamePartType")
@XmlEnum
public enum EntityNamePartType {

    FAM,
    GIV,
    PFX,
    SFX,
    DEL;

    public String value() {
        return name();
    }

    public static EntityNamePartType fromValue(String v) {
        return valueOf(v);
    }

}
