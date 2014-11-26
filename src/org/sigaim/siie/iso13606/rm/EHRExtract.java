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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 *         The root node of an EHR Extract.
 *       
 * 
 * <p>Java class for EHRExtract complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EHRExtract">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="authorisingParty" type="{uri:iso.org:21090}II" minOccurs="0"/>
 *         &lt;element name="ehrId" type="{uri:iso.org:21090}II"/>
 *         &lt;element name="ehrSystem" type="{uri:iso.org:21090}II"/>
 *         &lt;element name="rmId" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *         &lt;element name="subjectOfCare" type="{uri:iso.org:21090}II"/>
 *         &lt;element name="timeCreated" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="allCompositions" type="{http://www.iso.org/iso/search.htm?qt=13606}Composition" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="criteria" type="{http://www.iso.org/iso/search.htm?qt=13606}ExtractCriteria" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="folders" type="{http://www.iso.org/iso/search.htm?qt=13606}Folder" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="demographicExtract" type="{http://www.iso.org/iso/search.htm?qt=13606}IdentifiedEntity" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EHRExtract", namespace = "http://www.iso.org/iso/search.htm?qt=13606", propOrder = {
    "authorisingParty",
    "ehrId",
    "ehrSystem",
    "rmId",
    "subjectOfCare",
    "timeCreated",
    "allCompositions",
    "criteria",
    "folders",
    "demographicExtract"
})
public class EHRExtract {

    @XmlElement(namespace = "")
    protected II authorisingParty;
    @XmlElement(namespace = "", required = true)
    protected II ehrId;
    @XmlElement(namespace = "", required = true)
    protected II ehrSystem;
    @XmlElement(namespace = "", required = true)
    protected Object rmId;
    @XmlElement(namespace = "", required = true)
    protected II subjectOfCare;
    @XmlElement(namespace = "", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timeCreated;
    @XmlElement(namespace = "")
    protected List<Composition> allCompositions;
    @XmlElement(namespace = "")
    protected List<ExtractCriteria> criteria;
    @XmlElement(namespace = "")
    protected List<Folder> folders;
    @XmlElement(namespace = "")
    protected List<IdentifiedEntity> demographicExtract;

    /**
     * Gets the value of the authorisingParty property.
     * 
     * @return
     *     possible object is
     *     {@link II }
     *     
     */
    public II getAuthorisingParty() {
        return authorisingParty;
    }

    /**
     * Sets the value of the authorisingParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link II }
     *     
     */
    public void setAuthorisingParty(II value) {
        this.authorisingParty = value;
    }

    /**
     * Gets the value of the ehrId property.
     * 
     * @return
     *     possible object is
     *     {@link II }
     *     
     */
    public II getEhrId() {
        return ehrId;
    }

    /**
     * Sets the value of the ehrId property.
     * 
     * @param value
     *     allowed object is
     *     {@link II }
     *     
     */
    public void setEhrId(II value) {
        this.ehrId = value;
    }

    /**
     * Gets the value of the ehrSystem property.
     * 
     * @return
     *     possible object is
     *     {@link II }
     *     
     */
    public II getEhrSystem() {
        return ehrSystem;
    }

    /**
     * Sets the value of the ehrSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link II }
     *     
     */
    public void setEhrSystem(II value) {
        this.ehrSystem = value;
    }

    /**
     * Gets the value of the rmId property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getRmId() {
        return rmId;
    }

    /**
     * Sets the value of the rmId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setRmId(Object value) {
        this.rmId = value;
    }

    /**
     * Gets the value of the subjectOfCare property.
     * 
     * @return
     *     possible object is
     *     {@link II }
     *     
     */
    public II getSubjectOfCare() {
        return subjectOfCare;
    }

    /**
     * Sets the value of the subjectOfCare property.
     * 
     * @param value
     *     allowed object is
     *     {@link II }
     *     
     */
    public void setSubjectOfCare(II value) {
        this.subjectOfCare = value;
    }

    /**
	 * Gets the value of the timeCreated property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
    public XMLGregorianCalendar getTimeCreated() {
        return timeCreated;
    }

    /**
     * Sets the value of the timeCreated property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimeCreated(XMLGregorianCalendar value) {
        this.timeCreated = value;
    }

    /**
     * Gets the value of the allCompositions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the allCompositions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAllCompositions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Composition }
     * 
     * 
     */
    public List<Composition> getAllCompositions() {
        if (allCompositions == null) {
            allCompositions = new ArrayList<Composition>();
        }
        return this.allCompositions;
    }

    /**
     * Gets the value of the criteria property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the criteria property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCriteria().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExtractCriteria }
     * 
     * 
     */
    public List<ExtractCriteria> getCriteria() {
        if (criteria == null) {
            criteria = new ArrayList<ExtractCriteria>();
        }
        return this.criteria;
    }

    /**
     * Gets the value of the folders property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the folders property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFolders().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Folder }
     * 
     * 
     */
    public List<Folder> getFolders() {
        if (folders == null) {
            folders = new ArrayList<Folder>();
        }
        return this.folders;
    }

    /**
     * Gets the value of the demographicExtract property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the demographicExtract property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDemographicExtract().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdentifiedEntity }
     * 
     * 
     */
    public List<IdentifiedEntity> getDemographicExtract() {
        if (demographicExtract == null) {
            demographicExtract = new ArrayList<IdentifiedEntity>();
        }
        return this.demographicExtract;
    }

}
