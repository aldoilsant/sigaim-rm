//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.04 at 02:11:57 AM CEST 
//


package org.sigaim.siie.iso13606.rm;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Defines the set of Record Components or Archetypes to which
 *         this policy applies.  If no Target  criteria  are specified
 *         then the policy is assumed to apply to the whole Extract.
 *       
 * 
 * <p>Java class for Target complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Target">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rcIds" type="{uri:iso.org:21090}II" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="archetypeIds" type="{uri:iso.org:21090}II" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="timePeriod" type="{uri:iso.org:21090}IVL_TS" minOccurs="0"/>
 *         &lt;element name="otherCriteria" type="{uri:iso.org:21090}CD.CV" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Target", namespace = "http://www.iso.org/iso/search.htm?qt=13606", propOrder = {
    "rcIds",
    "archetypeIds",
    "timePeriod",
    "otherCriteria"
})
public class Target {

    @XmlElement(namespace = "")
    protected List<II> rcIds;
    @XmlElement(namespace = "")
    protected List<II> archetypeIds;
    @XmlElement(namespace = "")
    protected IVLTS timePeriod;
    @XmlElement(namespace = "")
    protected List<CDCV> otherCriteria;

    /**
     * Gets the value of the rcIds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rcIds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRcIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link II }
     * 
     * 
     */
    public List<II> getRcIds() {
        if (rcIds == null) {
            rcIds = new ArrayList<II>();
        }
        return this.rcIds;
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

    /**
     * Gets the value of the otherCriteria property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the otherCriteria property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOtherCriteria().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CDCV }
     * 
     * 
     */
    public List<CDCV> getOtherCriteria() {
        if (otherCriteria == null) {
            otherCriteria = new ArrayList<CDCV>();
        }
        return this.otherCriteria;
    }

}
