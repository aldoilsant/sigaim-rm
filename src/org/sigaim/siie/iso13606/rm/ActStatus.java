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
 * <p>Java class for ActStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ActStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="foreseen"/>
 *     &lt;enumeration value="requested"/>
 *     &lt;enumeration value="accepted"/>
 *     &lt;enumeration value="booked"/>
 *     &lt;enumeration value="planned"/>
 *     &lt;enumeration value="ready"/>
 *     &lt;enumeration value="inProgress"/>
 *     &lt;enumeration value="completed"/>
 *     &lt;enumeration value="reported"/>
 *     &lt;enumeration value="terminated"/>
 *     &lt;enumeration value="forwarded"/>
 *     &lt;enumeration value="suspended"/>
 *     &lt;enumeration value="annulledCancelled"/>
 *     &lt;enumeration value="annulledRejected"/>
 *     &lt;enumeration value="substituted"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ActStatus", namespace = "http://www.iso.org/iso/search.htm?qt=13606")
@XmlEnum
public enum ActStatus {

    @XmlEnumValue("foreseen")
    FORESEEN("foreseen"),
    @XmlEnumValue("requested")
    REQUESTED("requested"),
    @XmlEnumValue("accepted")
    ACCEPTED("accepted"),
    @XmlEnumValue("booked")
    BOOKED("booked"),
    @XmlEnumValue("planned")
    PLANNED("planned"),
    @XmlEnumValue("ready")
    READY("ready"),
    @XmlEnumValue("inProgress")
    IN_PROGRESS("inProgress"),
    @XmlEnumValue("completed")
    COMPLETED("completed"),
    @XmlEnumValue("reported")
    REPORTED("reported"),
    @XmlEnumValue("terminated")
    TERMINATED("terminated"),
    @XmlEnumValue("forwarded")
    FORWARDED("forwarded"),
    @XmlEnumValue("suspended")
    SUSPENDED("suspended"),
    @XmlEnumValue("annulledCancelled")
    ANNULLED_CANCELLED("annulledCancelled"),
    @XmlEnumValue("annulledRejected")
    ANNULLED_REJECTED("annulledRejected"),
    @XmlEnumValue("substituted")
    SUBSTITUTED("substituted");
    private final String value;

    ActStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ActStatus fromValue(String v) {
        for (ActStatus c: ActStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}