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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 *         Validates the use of an Access Policy.
 *       
 * 
 * <p>Java class for Attestation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Attestation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="performer" type="{uri:iso.org:21090}II"/>
 *         &lt;element name="proof" type="{uri:iso.org:21090}ED" minOccurs="0"/>
 *         &lt;element name="function" type="{uri:iso.org:21090}CD.CV" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Attestation", namespace = "http://www.iso.org/iso/search.htm?qt=13606", propOrder = {
    "time",
    "performer",
    "proof",
    "function"
})
public class Attestation {

    @XmlElement(namespace = "", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar time;
    @XmlElement(namespace = "", required = true)
    protected II performer;
    @XmlElement(namespace = "")
    protected ED proof;
    @XmlElement(namespace = "")
    protected CDCV function;

    /**
     * Gets the value of the time property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTime(XMLGregorianCalendar value) {
        this.time = value;
    }

    /**
     * Gets the value of the performer property.
     * 
     * @return
     *     possible object is
     *     {@link II }
     *     
     */
    public II getPerformer() {
        return performer;
    }

    /**
     * Sets the value of the performer property.
     * 
     * @param value
     *     allowed object is
     *     {@link II }
     *     
     */
    public void setPerformer(II value) {
        this.performer = value;
    }

    /**
     * Gets the value of the proof property.
     * 
     * @return
     *     possible object is
     *     {@link ED }
     *     
     */
    public ED getProof() {
        return proof;
    }

    /**
     * Sets the value of the proof property.
     * 
     * @param value
     *     allowed object is
     *     {@link ED }
     *     
     */
    public void setProof(ED value) {
        this.proof = value;
    }

    /**
     * Gets the value of the function property.
     * 
     * @return
     *     possible object is
     *     {@link CDCV }
     *     
     */
    public CDCV getFunction() {
        return function;
    }

    /**
     * Sets the value of the function property.
     * 
     * @param value
     *     allowed object is
     *     {@link CDCV }
     *     
     */
    public void setFunction(CDCV value) {
        this.function = value;
    }

}
