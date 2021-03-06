//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.04 at 02:11:57 AM CEST 
//


package org.sigaim.siie.iso13606.rm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Identification information about a person  corresponding to
 *         GPIC 2.015.
 *       
 * 
 * <p>Java class for SubjectOfCarePersonIdentification complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubjectOfCarePersonIdentification">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.iso.org/iso/search.htm?qt=13606}Person">
 *       &lt;sequence>
 *         &lt;element name="administrativeGenderCode">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="male"/>
 *               &lt;enumeration value="female"/>
 *               &lt;enumeration value="intersex"/>
 *               &lt;enumeration value="unknown"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="birthOrderNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="birthTime" type="{uri:iso.org:21090}TS"/>
 *         &lt;element name="deceasedTime" type="{uri:iso.org:21090}TS" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubjectOfCarePersonIdentification", namespace = "http://www.iso.org/iso/search.htm?qt=13606", propOrder = {
    "administrativeGenderCode",
    "birthOrderNumber",
    "birthTime",
    "deceasedTime"
})
public class SubjectOfCarePersonIdentification
    extends Person
{

    @XmlElement(namespace = "", required = true)
    protected String administrativeGenderCode;
    @XmlElement(namespace = "")
    protected Integer birthOrderNumber;
    @XmlElement(namespace = "", required = true)
    protected TS birthTime;
    @XmlElement(namespace = "")
    protected TS deceasedTime;

    /**
     * Gets the value of the administrativeGenderCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdministrativeGenderCode() {
        return administrativeGenderCode;
    }

    /**
     * Sets the value of the administrativeGenderCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdministrativeGenderCode(String value) {
        this.administrativeGenderCode = value;
    }

    /**
     * Gets the value of the birthOrderNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBirthOrderNumber() {
        return birthOrderNumber;
    }

    /**
     * Sets the value of the birthOrderNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBirthOrderNumber(Integer value) {
        this.birthOrderNumber = value;
    }

    /**
     * Gets the value of the birthTime property.
     * 
     * @return
     *     possible object is
     *     {@link TS }
     *     
     */
    public TS getBirthTime() {
        return birthTime;
    }

    /**
     * Sets the value of the birthTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link TS }
     *     
     */
    public void setBirthTime(TS value) {
        this.birthTime = value;
    }

    /**
     * Gets the value of the deceasedTime property.
     * 
     * @return
     *     possible object is
     *     {@link TS }
     *     
     */
    public TS getDeceasedTime() {
        return deceasedTime;
    }

    /**
     * Sets the value of the deceasedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link TS }
     *     
     */
    public void setDeceasedTime(TS value) {
        this.deceasedTime = value;
    }

}
