//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.04 at 02:11:57 AM CEST 
//


package org.sigaim.siie.iso13606.rm;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Restrictions or filters on the creation of the EHR Extract.
 *         Note  that  maxSensitivity  is of type Sensitivity  (as per
 *         part 4) not Integer  as given in part 1.  allVersions is BL
 *         type when used in part 4, but Boolean here.
 *       
 * 
 * <p>Java class for ExtractCriteria complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExtractCriteria">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="allVersions" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="archetypeIds" type="{uri:iso.org:21090}II" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="maxSensitivity" type="{http://www.iso.org/iso/search.htm?qt=13606}Sensitivity" minOccurs="0"/>
 *         &lt;element name="multimediaIncluded" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="otherConstraints" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="timePeriod" type="{uri:iso.org:21090}IVL_TS" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtractCriteria", namespace = "http://www.iso.org/iso/search.htm?qt=13606", propOrder = {
    "allVersions",
    "archetypeIds",
    "maxSensitivity",
    "multimediaIncluded",
    "otherConstraints",
    "timePeriod"
})
public class ExtractCriteria {

    @XmlElement(namespace = "")
    protected Boolean allVersions;
    @XmlElement(namespace = "")
    protected List<II> archetypeIds;
    @XmlElement(namespace = "")
    protected BigInteger maxSensitivity;
    @XmlElement(namespace = "")
    protected Boolean multimediaIncluded;
    @XmlElement(namespace = "")
    protected String otherConstraints;
    @XmlElement(namespace = "")
    protected IVLTS timePeriod;

    /**
     * Gets the value of the allVersions property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isAllVersions() {
        return allVersions;
    }

    /**
     * Sets the value of the allVersions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllVersions(boolean value) {
        this.allVersions = value;
    }

    /**
     * Gets the value of the archetypeIds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the archetypeIds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArchetypeIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link II }
     * 
     * 
     */
    public List<II> getArchetypeIds() {
        if (archetypeIds == null) {
            archetypeIds = new ArrayList<II>();
        }
        return this.archetypeIds;
    }

    /**
     * Gets the value of the maxSensitivity property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxSensitivity() {
        return maxSensitivity;
    }

    /**
     * Sets the value of the maxSensitivity property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxSensitivity(BigInteger value) {
        this.maxSensitivity = value;
    }

    /**
     * Gets the value of the multimediaIncluded property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isMultimediaIncluded() {
        return multimediaIncluded;
    }

    /**
     * Sets the value of the multimediaIncluded property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMultimediaIncluded(boolean value) {
        this.multimediaIncluded = value;
    }

    /**
     * Gets the value of the otherConstraints property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherConstraints() {
        return otherConstraints;
    }

    /**
     * Sets the value of the otherConstraints property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherConstraints(String value) {
        this.otherConstraints = value;
    }

    /**
     * Gets the value of the timePeriod property.
     * 
     * @return
     *     possible object is
     *     {@link IVLTS }
     *     
     */
    public IVLTS getTimePeriod() {
        return timePeriod;
    }

    /**
     * Sets the value of the timePeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link IVLTS }
     *     
     */
    public void setTimePeriod(IVLTS value) {
        this.timePeriod = value;
    }

}
