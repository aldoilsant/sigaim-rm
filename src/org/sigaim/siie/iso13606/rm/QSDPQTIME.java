//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.04 at 02:11:57 AM CEST 
//


package org.sigaim.siie.iso13606.rm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QSD_PQ.TIME complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QSD_PQ.TIME">
 *   &lt;complexContent>
 *     &lt;extension base="{uri:iso.org:21090}QSET_PQ.TIME">
 *       &lt;sequence>
 *         &lt;element name="first" type="{uri:iso.org:21090}QSET_PQ.TIME" minOccurs="0"/>
 *         &lt;element name="second" type="{uri:iso.org:21090}QSET_PQ.TIME" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QSD_PQ.TIME", propOrder = {
    "first",
    "second"
})
public class QSDPQTIME
    extends QSETPQTIME
{

    protected QSETPQTIME first;
    protected QSETPQTIME second;

    /**
     * Gets the value of the first property.
     * 
     * @return
     *     possible object is
     *     {@link QSETPQTIME }
     *     
     */
    public QSETPQTIME getFirst() {
        return first;
    }

    /**
     * Sets the value of the first property.
     * 
     * @param value
     *     allowed object is
     *     {@link QSETPQTIME }
     *     
     */
    public void setFirst(QSETPQTIME value) {
        this.first = value;
    }

    /**
     * Gets the value of the second property.
     * 
     * @return
     *     possible object is
     *     {@link QSETPQTIME }
     *     
     */
    public QSETPQTIME getSecond() {
        return second;
    }

    /**
     * Sets the value of the second property.
     * 
     * @param value
     *     allowed object is
     *     {@link QSETPQTIME }
     *     
     */
    public void setSecond(QSETPQTIME value) {
        this.second = value;
    }

}
