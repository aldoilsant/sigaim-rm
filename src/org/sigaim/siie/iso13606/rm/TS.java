//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.04 at 02:11:57 AM CEST 
//


package org.sigaim.siie.iso13606.rm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TS">
 *   &lt;complexContent>
 *     &lt;extension base="{uri:iso.org:21090}QTY">
 *       &lt;attribute name="value">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;pattern value="[1-2][0-9]{3,3}(((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9]|3[0-1])(([0-1][0-9]|2[0-3])([0-5][0-9]([0-5][0-9](\.[0-9]{1,4})?)?)?)?)?)?([+\-](0[0-9]|1[0-3])([0-5][0-9]))?"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TS")
@XmlSeeAlso({
    TSDATETIME.class,
    TSDATE.class
})
public class TS
    extends QTY
{

    @XmlAttribute(name = "value")
    protected String value;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

}
