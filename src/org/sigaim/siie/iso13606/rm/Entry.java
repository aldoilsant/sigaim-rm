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
 *         Represents the information  acquired for a  single clinical
 *         activity  or recording.  The  Entry  may be  nilled  if  it 
 *         corrects a previous erroneous version. 
 *       
 * 
 * <p>Java class for Entry complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Entry">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.iso.org/iso/search.htm?qt=13606}Content">
 *       &lt;sequence>
 *         &lt;element name="actId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="actStatus" type="{http://www.iso.org/iso/search.htm?qt=13606}ActStatus" minOccurs="0"/>
 *         &lt;element name="subjectOfInformationCategory" type="{http://www.iso.org/iso/search.htm?qt=13606}SubjectOfInformationCategory" minOccurs="0"/>
 *         &lt;element name="uncertaintyExpressed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="items" type="{http://www.iso.org/iso/search.htm?qt=13606}Item" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="infoProvider" type="{http://www.iso.org/iso/search.htm?qt=13606}FunctionalRole" minOccurs="0"/>
 *         &lt;element name="otherParticipations" type="{http://www.iso.org/iso/search.htm?qt=13606}FunctionalRole" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="subjectOfInformation" type="{http://www.iso.org/iso/search.htm?qt=13606}RelatedParty" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Entry", namespace = "http://www.iso.org/iso/search.htm?qt=13606", propOrder = {
    "actId",
    "actStatus",
    "subjectOfInformationCategory",
    "uncertaintyExpressed",
    "items",
    "infoProvider",
    "otherParticipations",
    "subjectOfInformation"
})
public class Entry
    extends Content
{

    @XmlElement(namespace = "")
    protected String actId;
    @XmlElement(namespace = "")
    protected ActStatus actStatus;
    @XmlElement(namespace = "")
    protected SubjectOfInformationCategory subjectOfInformationCategory;
    @XmlElement(namespace = "")
    protected boolean uncertaintyExpressed;
    @XmlElement(namespace = "", nillable = true)
    protected List<Item> items;
    @XmlElement(namespace = "")
    protected FunctionalRole infoProvider;
    @XmlElement(namespace = "")
    protected List<FunctionalRole> otherParticipations;
    @XmlElement(namespace = "")
    protected RelatedParty subjectOfInformation;

    /**
     * Gets the value of the actId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActId() {
        return actId;
    }

    /**
     * Sets the value of the actId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActId(String value) {
        this.actId = value;
    }

    /**
     * Gets the value of the actStatus property.
     * 
     * @return
     *     possible object is
     *     {@link ActStatus }
     *     
     */
    public ActStatus getActStatus() {
        return actStatus;
    }

    /**
     * Sets the value of the actStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActStatus }
     *     
     */
    public void setActStatus(ActStatus value) {
        this.actStatus = value;
    }

    /**
     * Gets the value of the subjectOfInformationCategory property.
     * 
     * @return
     *     possible object is
     *     {@link SubjectOfInformationCategory }
     *     
     */
    public SubjectOfInformationCategory getSubjectOfInformationCategory() {
        return subjectOfInformationCategory;
    }

    /**
     * Sets the value of the subjectOfInformationCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubjectOfInformationCategory }
     *     
     */
    public void setSubjectOfInformationCategory(SubjectOfInformationCategory value) {
        this.subjectOfInformationCategory = value;
    }

    /**
     * Gets the value of the uncertaintyExpressed property.
     * 
     */
    public boolean isUncertaintyExpressed() {
        return uncertaintyExpressed;
    }

    /**
     * Sets the value of the uncertaintyExpressed property.
     * 
     */
    public void setUncertaintyExpressed(boolean value) {
        this.uncertaintyExpressed = value;
    }

    /**
     * Gets the value of the items property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the items property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Item }
     * 
     * 
     */
    public List<Item> getItems() {
        if (items == null) {
            items = new ArrayList<Item>();
        }
        return this.items;
    }

    /**
     * Gets the value of the infoProvider property.
     * 
     * @return
     *     possible object is
     *     {@link FunctionalRole }
     *     
     */
    public FunctionalRole getInfoProvider() {
        return infoProvider;
    }

    /**
     * Sets the value of the infoProvider property.
     * 
     * @param value
     *     allowed object is
     *     {@link FunctionalRole }
     *     
     */
    public void setInfoProvider(FunctionalRole value) {
        this.infoProvider = value;
    }

    /**
     * Gets the value of the otherParticipations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the otherParticipations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOtherParticipations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FunctionalRole }
     * 
     * 
     */
    public List<FunctionalRole> getOtherParticipations() {
        if (otherParticipations == null) {
            otherParticipations = new ArrayList<FunctionalRole>();
        }
        return this.otherParticipations;
    }

    /**
     * Gets the value of the subjectOfInformation property.
     * 
     * @return
     *     possible object is
     *     {@link RelatedParty }
     *     
     */
    public RelatedParty getSubjectOfInformation() {
        return subjectOfInformation;
    }

    /**
     * Sets the value of the subjectOfInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link RelatedParty }
     *     
     */
    public void setSubjectOfInformation(RelatedParty value) {
        this.subjectOfInformation = value;
    }

}
