
package org.datacontract.schemas._2004._07;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChangeAddress.AddressType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ChangeAddress.AddressType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Billing"/>
 *     &lt;enumeration value="Installation"/>
 *     &lt;enumeration value="Local"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ChangeAddress.AddressType")
@XmlEnum
public enum ChangeAddressAddressType {

    @XmlEnumValue("Billing")
    BILLING("Billing"),
    @XmlEnumValue("Installation")
    INSTALLATION("Installation"),
    @XmlEnumValue("Local")
    LOCAL("Local");
    private final String value;

    ChangeAddressAddressType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ChangeAddressAddressType fromValue(String v) {
        for (ChangeAddressAddressType c: ChangeAddressAddressType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
