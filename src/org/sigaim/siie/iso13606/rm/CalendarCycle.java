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
 * <p>Java class for CalendarCycle.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CalendarCycle">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CY"/>
 *     &lt;enumeration value="MY"/>
 *     &lt;enumeration value="CM"/>
 *     &lt;enumeration value="CW"/>
 *     &lt;enumeration value="WY"/>
 *     &lt;enumeration value="DM"/>
 *     &lt;enumeration value="CD"/>
 *     &lt;enumeration value="DY"/>
 *     &lt;enumeration value="DW"/>
 *     &lt;enumeration value="HD"/>
 *     &lt;enumeration value="CH"/>
 *     &lt;enumeration value="NH"/>
 *     &lt;enumeration value="CN"/>
 *     &lt;enumeration value="SN"/>
 *     &lt;enumeration value="CS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CalendarCycle")
@XmlEnum
public enum CalendarCycle {

    CY,
    MY,
    CM,
    CW,
    WY,
    DM,
    CD,
    DY,
    DW,
    HD,
    CH,
    NH,
    CN,
    SN,
    CS;

    public String value() {
        return name();
    }

    public static CalendarCycle fromValue(String v) {
        return valueOf(v);
    }

}
