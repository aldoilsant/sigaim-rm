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
 *         Creates a  relationship between  two Record Components that
 *         is orthogonal  to the common one of containment.  Note that
 *         if a recipient is not permitted to see the component linked
 *         to,  even revealing the  link constitutes an  inappropriate
 *         disclosure.  A new attribute is included in this version of
 *         type CD.CV.  A Schematron rule polices  that either this or
 *         the LinkRole (defined in part 3)  is used, but not both.  A
 *         further rule ensures that if the latter is used, it matches
 *         the LinkNature semantically.
 *       
 * 
 * <p>Java class for Link complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Link">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="followLink" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="nature" type="{http://www.iso.org/iso/search.htm?qt=13606}LinkNature"/>
 *         &lt;element name="role" type="{http://www.iso.org/iso/search.htm?qt=13606}LinkRole" minOccurs="0"/>
 *         &lt;element name="roleCV" type="{uri:iso.org:21090}CD.CV" minOccurs="0"/>
 *         &lt;element name="targetRcId" type="{uri:iso.org:21090}II" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Link", namespace = "http://www.iso.org/iso/search.htm?qt=13606", propOrder = {
    "followLink",
    "nature",
    "role",
    "roleCV",
    "targetRcId"
})
public class Link {

    @XmlElement(namespace = "")
    protected boolean followLink;
    @XmlElement(namespace = "", required = true)
    protected LinkNature nature;
    @XmlElement(namespace = "")
    protected LinkRole role;
    @XmlElement(namespace = "")
    protected CDCV roleCV;
    @XmlElement(namespace = "", required = true)
    protected List<II> targetRcId;

    /**
     * Gets the value of the followLink property.
     * 
     */
    public boolean isFollowLink() {
        return followLink;
    }

    /**
     * Sets the value of the followLink property.
     * 
     */
    public void setFollowLink(boolean value) {
        this.followLink = value;
    }

    /**
     * Gets the value of the nature property.
     * 
     * @return
     *     possible object is
     *     {@link LinkNature }
     *     
     */
    public LinkNature getNature() {
        return nature;
    }

    /**
     * Sets the value of the nature property.
     * 
     * @param value
     *     allowed object is
     *     {@link LinkNature }
     *     
     */
    public void setNature(LinkNature value) {
        this.nature = value;
    }

    /**
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link LinkRole }
     *     
     */
    public LinkRole getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link LinkRole }
     *     
     */
    public void setRole(LinkRole value) {
        this.role = value;
    }

    /**
     * Gets the value of the roleCV property.
     * 
     * @return
     *     possible object is
     *     {@link CDCV }
     *     
     */
    public CDCV getRoleCV() {
        return roleCV;
    }

    /**
     * Sets the value of the roleCV property.
     * 
     * @param value
     *     allowed object is
     *     {@link CDCV }
     *     
     */
    public void setRoleCV(CDCV value) {
        this.roleCV = value;
    }

    /**
     * Gets the value of the targetRcId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the targetRcId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTargetRcId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link II }
     * 
     * 
     */
    public List<II> getTargetRcId() {
        if (targetRcId == null) {
            targetRcId = new ArrayList<II>();
        }
        return this.targetRcId;
    }

}