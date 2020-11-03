
package org.datacontract.schemas._2004._07;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfstring;


/**
 * <p>Java class for Customer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Customer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/}ApiResponse">
 *       &lt;sequence>
 *         &lt;element name="Refferal" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="ServiceAddOnPlanCode" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
 *         &lt;element name="addonPlanCode" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
 *         &lt;element name="alternateEmailId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="alternateNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="authorizedSignatoryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="automaticnotification" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="bankAccountNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankAccountType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankBranch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billDetailPreference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billPreference" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="billingAddress" type="{http://schemas.datacontract.org/2004/07/}BillingAddress" minOccurs="0"/>
 *         &lt;element name="businessPartnerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="businessPartnerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="categoryType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chargeName" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
 *         &lt;element name="crfDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="crfEntryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="crfNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dateOfBirth" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="deposit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="deviceOwner" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailIdValidationFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fatherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ftApprovalDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ftSubmitDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="gender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="installationAddress" type="{http://schemas.datacontract.org/2004/07/}InstallationAddress" minOccurs="0"/>
 *         &lt;element name="itemCode" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="localContactAddress" type="{http://schemas.datacontract.org/2004/07/}LocalContactAddress" minOccurs="0"/>
 *         &lt;element name="localContactPersonContactNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="localContactPersonName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="middleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobileHandsetImieNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobileHandsetMake" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobileHandsetModelNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobileNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="motherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nationality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noOfWiringPoints" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="offerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="organizationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="panNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="passportNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="passportValidity" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="prepaidorPostpaidFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="propertyType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="saleRepresentativeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="saleRepresentativeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceModel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="speedBoosterPlanCode" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
 *         &lt;element name="telephone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="teleserviceBankTransId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="teleserviceChequeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="teleservicePayment" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="teleservicePaymentBank" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="teleservicePaymentBranch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="teleservicePaymentCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="teleservicePaymentMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="teleservicePaymentRefId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="teleservicePgTransId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="teleserviceReferenceTransId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telesolutionBankTransId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telesolutionChequeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="telesolutionPayment" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="telesolutionPaymentBank" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telesolutionPaymentBranch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telesolutionPaymentCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telesolutionPaymentDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="telesolutionPaymentMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telesolutionPaymentRefId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telesolutionPgTransId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telesolutionReferenceTransId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="typeOfApplication" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usageBoosterPlanCode" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
 *         &lt;element name="vehicleMake" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vehicleRegistrationNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="visaType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="visaValidity" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="zone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Customer", propOrder = {
    "refferal",
    "serviceAddOnPlanCode",
    "addonPlanCode",
    "alternateEmailId",
    "alternateNumber",
    "authorizedSignatoryName",
    "automaticnotification",
    "bankAccountNo",
    "bankAccountType",
    "bankBranch",
    "bankName",
    "billDetailPreference",
    "billPreference",
    "billingAddress",
    "businessPartnerCode",
    "businessPartnerName",
    "categoryType",
    "chargeName",
    "crfDate",
    "crfEntryDate",
    "crfNo",
    "dateOfBirth",
    "deposit",
    "deviceOwner",
    "emailId",
    "emailIdValidationFlag",
    "fatherName",
    "firstName",
    "ftApprovalDate",
    "ftSubmitDate",
    "gender",
    "installationAddress",
    "itemCode",
    "lastName",
    "localContactAddress",
    "localContactPersonContactNo",
    "localContactPersonName",
    "middleName",
    "mobileHandsetImieNo",
    "mobileHandsetMake",
    "mobileHandsetModelNo",
    "mobileNo",
    "motherName",
    "nationality",
    "noOfWiringPoints",
    "offerName",
    "organizationName",
    "panNo",
    "passportNo",
    "passportValidity",
    "prepaidorPostpaidFlag",
    "propertyType",
    "saleRepresentativeCode",
    "saleRepresentativeName",
    "serviceCode",
    "serviceModel",
    "speedBoosterPlanCode",
    "telephone",
    "teleserviceBankTransId",
    "teleserviceChequeDate",
    "teleservicePayment",
    "teleservicePaymentBank",
    "teleservicePaymentBranch",
    "teleservicePaymentCity",
    "teleservicePaymentMode",
    "teleservicePaymentRefId",
    "teleservicePgTransId",
    "teleserviceReferenceTransId",
    "telesolutionBankTransId",
    "telesolutionChequeDate",
    "telesolutionPayment",
    "telesolutionPaymentBank",
    "telesolutionPaymentBranch",
    "telesolutionPaymentCity",
    "telesolutionPaymentDate",
    "telesolutionPaymentMode",
    "telesolutionPaymentRefId",
    "telesolutionPgTransId",
    "telesolutionReferenceTransId",
    "title",
    "typeOfApplication",
    "usageBoosterPlanCode",
    "vehicleMake",
    "vehicleRegistrationNo",
    "visaType",
    "visaValidity",
    "zone"
})
@XmlSeeAlso({
    CustomerDetail.class
})
public class Customer
    extends ApiResponse
{

    @XmlElement(name = "Refferal")
    protected Long refferal;
    @XmlElementRef(name = "ServiceAddOnPlanCode", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<ArrayOfstring> serviceAddOnPlanCode;
    @XmlElementRef(name = "addonPlanCode", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<ArrayOfstring> addonPlanCode;
    @XmlElementRef(name = "alternateEmailId", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> alternateEmailId;
    @XmlElementRef(name = "alternateNumber", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> alternateNumber;
    @XmlElementRef(name = "authorizedSignatoryName", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> authorizedSignatoryName;
    protected Boolean automaticnotification;
    @XmlElementRef(name = "bankAccountNo", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> bankAccountNo;
    @XmlElementRef(name = "bankAccountType", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> bankAccountType;
    @XmlElementRef(name = "bankBranch", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> bankBranch;
    @XmlElementRef(name = "bankName", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> bankName;
    @XmlElementRef(name = "billDetailPreference", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> billDetailPreference;
    protected Integer billPreference;
    @XmlElementRef(name = "billingAddress", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<BillingAddress> billingAddress;
    @XmlElementRef(name = "businessPartnerCode", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> businessPartnerCode;
    @XmlElementRef(name = "businessPartnerName", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> businessPartnerName;
    @XmlElementRef(name = "categoryType", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> categoryType;
    @XmlElementRef(name = "chargeName", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<ArrayOfstring> chargeName;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar crfDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar crfEntryDate;
    @XmlElementRef(name = "crfNo", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> crfNo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateOfBirth;
    protected BigDecimal deposit;
    @XmlElementRef(name = "deviceOwner", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> deviceOwner;
    @XmlElementRef(name = "emailId", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> emailId;
    @XmlElementRef(name = "emailIdValidationFlag", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> emailIdValidationFlag;
    @XmlElementRef(name = "fatherName", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> fatherName;
    @XmlElementRef(name = "firstName", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> firstName;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ftApprovalDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ftSubmitDate;
    @XmlElementRef(name = "gender", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> gender;
    @XmlElementRef(name = "installationAddress", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<InstallationAddress> installationAddress;
    @XmlElementRef(name = "itemCode", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<ArrayOfstring> itemCode;
    @XmlElementRef(name = "lastName", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> lastName;
    @XmlElementRef(name = "localContactAddress", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<LocalContactAddress> localContactAddress;
    @XmlElementRef(name = "localContactPersonContactNo", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> localContactPersonContactNo;
    @XmlElementRef(name = "localContactPersonName", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> localContactPersonName;
    @XmlElementRef(name = "middleName", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> middleName;
    @XmlElementRef(name = "mobileHandsetImieNo", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> mobileHandsetImieNo;
    @XmlElementRef(name = "mobileHandsetMake", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> mobileHandsetMake;
    @XmlElementRef(name = "mobileHandsetModelNo", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> mobileHandsetModelNo;
    @XmlElementRef(name = "mobileNo", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> mobileNo;
    @XmlElementRef(name = "motherName", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> motherName;
    @XmlElementRef(name = "nationality", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> nationality;
    protected Integer noOfWiringPoints;
    @XmlElementRef(name = "offerName", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> offerName;
    @XmlElementRef(name = "organizationName", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> organizationName;
    @XmlElementRef(name = "panNo", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> panNo;
    @XmlElementRef(name = "passportNo", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> passportNo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar passportValidity;
    @XmlElementRef(name = "prepaidorPostpaidFlag", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> prepaidorPostpaidFlag;
    @XmlElementRef(name = "propertyType", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> propertyType;
    @XmlElementRef(name = "saleRepresentativeCode", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> saleRepresentativeCode;
    @XmlElementRef(name = "saleRepresentativeName", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> saleRepresentativeName;
    @XmlElementRef(name = "serviceCode", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> serviceCode;
    @XmlElementRef(name = "serviceModel", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> serviceModel;
    @XmlElementRef(name = "speedBoosterPlanCode", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<ArrayOfstring> speedBoosterPlanCode;
    @XmlElementRef(name = "telephone", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> telephone;
    @XmlElementRef(name = "teleserviceBankTransId", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> teleserviceBankTransId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar teleserviceChequeDate;
    protected Double teleservicePayment;
    @XmlElementRef(name = "teleservicePaymentBank", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> teleservicePaymentBank;
    @XmlElementRef(name = "teleservicePaymentBranch", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> teleservicePaymentBranch;
    @XmlElementRef(name = "teleservicePaymentCity", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> teleservicePaymentCity;
    @XmlElementRef(name = "teleservicePaymentMode", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> teleservicePaymentMode;
    @XmlElementRef(name = "teleservicePaymentRefId", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> teleservicePaymentRefId;
    @XmlElementRef(name = "teleservicePgTransId", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> teleservicePgTransId;
    @XmlElementRef(name = "teleserviceReferenceTransId", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> teleserviceReferenceTransId;
    @XmlElementRef(name = "telesolutionBankTransId", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> telesolutionBankTransId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar telesolutionChequeDate;
    protected Double telesolutionPayment;
    @XmlElementRef(name = "telesolutionPaymentBank", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> telesolutionPaymentBank;
    @XmlElementRef(name = "telesolutionPaymentBranch", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> telesolutionPaymentBranch;
    @XmlElementRef(name = "telesolutionPaymentCity", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> telesolutionPaymentCity;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar telesolutionPaymentDate;
    @XmlElementRef(name = "telesolutionPaymentMode", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> telesolutionPaymentMode;
    @XmlElementRef(name = "telesolutionPaymentRefId", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> telesolutionPaymentRefId;
    @XmlElementRef(name = "telesolutionPgTransId", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> telesolutionPgTransId;
    @XmlElementRef(name = "telesolutionReferenceTransId", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> telesolutionReferenceTransId;
    @XmlElementRef(name = "title", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> title;
    @XmlElementRef(name = "typeOfApplication", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> typeOfApplication;
    @XmlElementRef(name = "usageBoosterPlanCode", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<ArrayOfstring> usageBoosterPlanCode;
    @XmlElementRef(name = "vehicleMake", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> vehicleMake;
    @XmlElementRef(name = "vehicleRegistrationNo", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> vehicleRegistrationNo;
    @XmlElementRef(name = "visaType", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> visaType;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar visaValidity;
    @XmlElementRef(name = "zone", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> zone;

    /**
     * Gets the value of the refferal property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRefferal() {
        return refferal;
    }

    /**
     * Sets the value of the refferal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRefferal(Long value) {
        this.refferal = value;
    }

    /**
     * Gets the value of the serviceAddOnPlanCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getServiceAddOnPlanCode() {
        return serviceAddOnPlanCode;
    }

    /**
     * Sets the value of the serviceAddOnPlanCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setServiceAddOnPlanCode(JAXBElement<ArrayOfstring> value) {
        this.serviceAddOnPlanCode = ((JAXBElement<ArrayOfstring> ) value);
    }

    /**
     * Gets the value of the addonPlanCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getAddonPlanCode() {
        return addonPlanCode;
    }

    /**
     * Sets the value of the addonPlanCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setAddonPlanCode(JAXBElement<ArrayOfstring> value) {
        this.addonPlanCode = ((JAXBElement<ArrayOfstring> ) value);
    }

    /**
     * Gets the value of the alternateEmailId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAlternateEmailId() {
        return alternateEmailId;
    }

    /**
     * Sets the value of the alternateEmailId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAlternateEmailId(JAXBElement<String> value) {
        this.alternateEmailId = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the alternateNumber property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAlternateNumber() {
        return alternateNumber;
    }

    /**
     * Sets the value of the alternateNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAlternateNumber(JAXBElement<String> value) {
        this.alternateNumber = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the authorizedSignatoryName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAuthorizedSignatoryName() {
        return authorizedSignatoryName;
    }

    /**
     * Sets the value of the authorizedSignatoryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAuthorizedSignatoryName(JAXBElement<String> value) {
        this.authorizedSignatoryName = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the automaticnotification property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAutomaticnotification() {
        return automaticnotification;
    }

    /**
     * Sets the value of the automaticnotification property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAutomaticnotification(Boolean value) {
        this.automaticnotification = value;
    }

    /**
     * Gets the value of the bankAccountNo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBankAccountNo() {
        return bankAccountNo;
    }

    /**
     * Sets the value of the bankAccountNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBankAccountNo(JAXBElement<String> value) {
        this.bankAccountNo = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the bankAccountType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBankAccountType() {
        return bankAccountType;
    }

    /**
     * Sets the value of the bankAccountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBankAccountType(JAXBElement<String> value) {
        this.bankAccountType = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the bankBranch property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBankBranch() {
        return bankBranch;
    }

    /**
     * Sets the value of the bankBranch property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBankBranch(JAXBElement<String> value) {
        this.bankBranch = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the bankName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBankName() {
        return bankName;
    }

    /**
     * Sets the value of the bankName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBankName(JAXBElement<String> value) {
        this.bankName = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the billDetailPreference property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBillDetailPreference() {
        return billDetailPreference;
    }

    /**
     * Sets the value of the billDetailPreference property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBillDetailPreference(JAXBElement<String> value) {
        this.billDetailPreference = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the billPreference property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBillPreference() {
        return billPreference;
    }

    /**
     * Sets the value of the billPreference property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBillPreference(Integer value) {
        this.billPreference = value;
    }

    /**
     * Gets the value of the billingAddress property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BillingAddress }{@code >}
     *     
     */
    public JAXBElement<BillingAddress> getBillingAddress() {
        return billingAddress;
    }

    /**
     * Sets the value of the billingAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BillingAddress }{@code >}
     *     
     */
    public void setBillingAddress(JAXBElement<BillingAddress> value) {
        this.billingAddress = ((JAXBElement<BillingAddress> ) value);
    }

    /**
     * Gets the value of the businessPartnerCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBusinessPartnerCode() {
        return businessPartnerCode;
    }

    /**
     * Sets the value of the businessPartnerCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBusinessPartnerCode(JAXBElement<String> value) {
        this.businessPartnerCode = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the businessPartnerName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBusinessPartnerName() {
        return businessPartnerName;
    }

    /**
     * Sets the value of the businessPartnerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBusinessPartnerName(JAXBElement<String> value) {
        this.businessPartnerName = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the categoryType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCategoryType() {
        return categoryType;
    }

    /**
     * Sets the value of the categoryType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCategoryType(JAXBElement<String> value) {
        this.categoryType = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the chargeName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getChargeName() {
        return chargeName;
    }

    /**
     * Sets the value of the chargeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setChargeName(JAXBElement<ArrayOfstring> value) {
        this.chargeName = ((JAXBElement<ArrayOfstring> ) value);
    }

    /**
     * Gets the value of the crfDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCrfDate() {
        return crfDate;
    }

    /**
     * Sets the value of the crfDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCrfDate(XMLGregorianCalendar value) {
        this.crfDate = value;
    }

    /**
     * Gets the value of the crfEntryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCrfEntryDate() {
        return crfEntryDate;
    }

    /**
     * Sets the value of the crfEntryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCrfEntryDate(XMLGregorianCalendar value) {
        this.crfEntryDate = value;
    }

    /**
     * Gets the value of the crfNo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCrfNo() {
        return crfNo;
    }

    /**
     * Sets the value of the crfNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCrfNo(JAXBElement<String> value) {
        this.crfNo = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the dateOfBirth property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the value of the dateOfBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateOfBirth(XMLGregorianCalendar value) {
        this.dateOfBirth = value;
    }

    /**
     * Gets the value of the deposit property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDeposit() {
        return deposit;
    }

    /**
     * Sets the value of the deposit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDeposit(BigDecimal value) {
        this.deposit = value;
    }

    /**
     * Gets the value of the deviceOwner property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDeviceOwner() {
        return deviceOwner;
    }

    /**
     * Sets the value of the deviceOwner property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDeviceOwner(JAXBElement<String> value) {
        this.deviceOwner = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the emailId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEmailId() {
        return emailId;
    }

    /**
     * Sets the value of the emailId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEmailId(JAXBElement<String> value) {
        this.emailId = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the emailIdValidationFlag property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEmailIdValidationFlag() {
        return emailIdValidationFlag;
    }

    /**
     * Sets the value of the emailIdValidationFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEmailIdValidationFlag(JAXBElement<String> value) {
        this.emailIdValidationFlag = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the fatherName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFatherName() {
        return fatherName;
    }

    /**
     * Sets the value of the fatherName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFatherName(JAXBElement<String> value) {
        this.fatherName = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFirstName(JAXBElement<String> value) {
        this.firstName = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the ftApprovalDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFtApprovalDate() {
        return ftApprovalDate;
    }

    /**
     * Sets the value of the ftApprovalDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFtApprovalDate(XMLGregorianCalendar value) {
        this.ftApprovalDate = value;
    }

    /**
     * Gets the value of the ftSubmitDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFtSubmitDate() {
        return ftSubmitDate;
    }

    /**
     * Sets the value of the ftSubmitDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFtSubmitDate(XMLGregorianCalendar value) {
        this.ftSubmitDate = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGender(JAXBElement<String> value) {
        this.gender = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the installationAddress property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link InstallationAddress }{@code >}
     *     
     */
    public JAXBElement<InstallationAddress> getInstallationAddress() {
        return installationAddress;
    }

    /**
     * Sets the value of the installationAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link InstallationAddress }{@code >}
     *     
     */
    public void setInstallationAddress(JAXBElement<InstallationAddress> value) {
        this.installationAddress = ((JAXBElement<InstallationAddress> ) value);
    }

    /**
     * Gets the value of the itemCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getItemCode() {
        return itemCode;
    }

    /**
     * Sets the value of the itemCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setItemCode(JAXBElement<ArrayOfstring> value) {
        this.itemCode = ((JAXBElement<ArrayOfstring> ) value);
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLastName(JAXBElement<String> value) {
        this.lastName = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the localContactAddress property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link LocalContactAddress }{@code >}
     *     
     */
    public JAXBElement<LocalContactAddress> getLocalContactAddress() {
        return localContactAddress;
    }

    /**
     * Sets the value of the localContactAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link LocalContactAddress }{@code >}
     *     
     */
    public void setLocalContactAddress(JAXBElement<LocalContactAddress> value) {
        this.localContactAddress = ((JAXBElement<LocalContactAddress> ) value);
    }

    /**
     * Gets the value of the localContactPersonContactNo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLocalContactPersonContactNo() {
        return localContactPersonContactNo;
    }

    /**
     * Sets the value of the localContactPersonContactNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLocalContactPersonContactNo(JAXBElement<String> value) {
        this.localContactPersonContactNo = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the localContactPersonName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLocalContactPersonName() {
        return localContactPersonName;
    }

    /**
     * Sets the value of the localContactPersonName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLocalContactPersonName(JAXBElement<String> value) {
        this.localContactPersonName = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the middleName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMiddleName() {
        return middleName;
    }

    /**
     * Sets the value of the middleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMiddleName(JAXBElement<String> value) {
        this.middleName = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the mobileHandsetImieNo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMobileHandsetImieNo() {
        return mobileHandsetImieNo;
    }

    /**
     * Sets the value of the mobileHandsetImieNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMobileHandsetImieNo(JAXBElement<String> value) {
        this.mobileHandsetImieNo = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the mobileHandsetMake property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMobileHandsetMake() {
        return mobileHandsetMake;
    }

    /**
     * Sets the value of the mobileHandsetMake property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMobileHandsetMake(JAXBElement<String> value) {
        this.mobileHandsetMake = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the mobileHandsetModelNo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMobileHandsetModelNo() {
        return mobileHandsetModelNo;
    }

    /**
     * Sets the value of the mobileHandsetModelNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMobileHandsetModelNo(JAXBElement<String> value) {
        this.mobileHandsetModelNo = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the mobileNo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMobileNo() {
        return mobileNo;
    }

    /**
     * Sets the value of the mobileNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMobileNo(JAXBElement<String> value) {
        this.mobileNo = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the motherName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMotherName() {
        return motherName;
    }

    /**
     * Sets the value of the motherName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMotherName(JAXBElement<String> value) {
        this.motherName = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the nationality property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNationality() {
        return nationality;
    }

    /**
     * Sets the value of the nationality property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNationality(JAXBElement<String> value) {
        this.nationality = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the noOfWiringPoints property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNoOfWiringPoints() {
        return noOfWiringPoints;
    }

    /**
     * Sets the value of the noOfWiringPoints property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNoOfWiringPoints(Integer value) {
        this.noOfWiringPoints = value;
    }

    /**
     * Gets the value of the offerName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOfferName() {
        return offerName;
    }

    /**
     * Sets the value of the offerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOfferName(JAXBElement<String> value) {
        this.offerName = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the organizationName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOrganizationName() {
        return organizationName;
    }

    /**
     * Sets the value of the organizationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOrganizationName(JAXBElement<String> value) {
        this.organizationName = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the panNo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPanNo() {
        return panNo;
    }

    /**
     * Sets the value of the panNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPanNo(JAXBElement<String> value) {
        this.panNo = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the passportNo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPassportNo() {
        return passportNo;
    }

    /**
     * Sets the value of the passportNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPassportNo(JAXBElement<String> value) {
        this.passportNo = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the passportValidity property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPassportValidity() {
        return passportValidity;
    }

    /**
     * Sets the value of the passportValidity property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPassportValidity(XMLGregorianCalendar value) {
        this.passportValidity = value;
    }

    /**
     * Gets the value of the prepaidorPostpaidFlag property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPrepaidorPostpaidFlag() {
        return prepaidorPostpaidFlag;
    }

    /**
     * Sets the value of the prepaidorPostpaidFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPrepaidorPostpaidFlag(JAXBElement<String> value) {
        this.prepaidorPostpaidFlag = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the propertyType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPropertyType() {
        return propertyType;
    }

    /**
     * Sets the value of the propertyType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPropertyType(JAXBElement<String> value) {
        this.propertyType = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the saleRepresentativeCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaleRepresentativeCode() {
        return saleRepresentativeCode;
    }

    /**
     * Sets the value of the saleRepresentativeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaleRepresentativeCode(JAXBElement<String> value) {
        this.saleRepresentativeCode = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the saleRepresentativeName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaleRepresentativeName() {
        return saleRepresentativeName;
    }

    /**
     * Sets the value of the saleRepresentativeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaleRepresentativeName(JAXBElement<String> value) {
        this.saleRepresentativeName = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the serviceCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getServiceCode() {
        return serviceCode;
    }

    /**
     * Sets the value of the serviceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setServiceCode(JAXBElement<String> value) {
        this.serviceCode = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the serviceModel property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getServiceModel() {
        return serviceModel;
    }

    /**
     * Sets the value of the serviceModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setServiceModel(JAXBElement<String> value) {
        this.serviceModel = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the speedBoosterPlanCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getSpeedBoosterPlanCode() {
        return speedBoosterPlanCode;
    }

    /**
     * Sets the value of the speedBoosterPlanCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setSpeedBoosterPlanCode(JAXBElement<ArrayOfstring> value) {
        this.speedBoosterPlanCode = ((JAXBElement<ArrayOfstring> ) value);
    }

    /**
     * Gets the value of the telephone property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelephone() {
        return telephone;
    }

    /**
     * Sets the value of the telephone property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelephone(JAXBElement<String> value) {
        this.telephone = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the teleserviceBankTransId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTeleserviceBankTransId() {
        return teleserviceBankTransId;
    }

    /**
     * Sets the value of the teleserviceBankTransId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTeleserviceBankTransId(JAXBElement<String> value) {
        this.teleserviceBankTransId = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the teleserviceChequeDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTeleserviceChequeDate() {
        return teleserviceChequeDate;
    }

    /**
     * Sets the value of the teleserviceChequeDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTeleserviceChequeDate(XMLGregorianCalendar value) {
        this.teleserviceChequeDate = value;
    }

    /**
     * Gets the value of the teleservicePayment property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTeleservicePayment() {
        return teleservicePayment;
    }

    /**
     * Sets the value of the teleservicePayment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTeleservicePayment(Double value) {
        this.teleservicePayment = value;
    }

    /**
     * Gets the value of the teleservicePaymentBank property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTeleservicePaymentBank() {
        return teleservicePaymentBank;
    }

    /**
     * Sets the value of the teleservicePaymentBank property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTeleservicePaymentBank(JAXBElement<String> value) {
        this.teleservicePaymentBank = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the teleservicePaymentBranch property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTeleservicePaymentBranch() {
        return teleservicePaymentBranch;
    }

    /**
     * Sets the value of the teleservicePaymentBranch property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTeleservicePaymentBranch(JAXBElement<String> value) {
        this.teleservicePaymentBranch = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the teleservicePaymentCity property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTeleservicePaymentCity() {
        return teleservicePaymentCity;
    }

    /**
     * Sets the value of the teleservicePaymentCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTeleservicePaymentCity(JAXBElement<String> value) {
        this.teleservicePaymentCity = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the teleservicePaymentMode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTeleservicePaymentMode() {
        return teleservicePaymentMode;
    }

    /**
     * Sets the value of the teleservicePaymentMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTeleservicePaymentMode(JAXBElement<String> value) {
        this.teleservicePaymentMode = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the teleservicePaymentRefId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTeleservicePaymentRefId() {
        return teleservicePaymentRefId;
    }

    /**
     * Sets the value of the teleservicePaymentRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTeleservicePaymentRefId(JAXBElement<String> value) {
        this.teleservicePaymentRefId = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the teleservicePgTransId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTeleservicePgTransId() {
        return teleservicePgTransId;
    }

    /**
     * Sets the value of the teleservicePgTransId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTeleservicePgTransId(JAXBElement<String> value) {
        this.teleservicePgTransId = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the teleserviceReferenceTransId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTeleserviceReferenceTransId() {
        return teleserviceReferenceTransId;
    }

    /**
     * Sets the value of the teleserviceReferenceTransId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTeleserviceReferenceTransId(JAXBElement<String> value) {
        this.teleserviceReferenceTransId = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the telesolutionBankTransId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelesolutionBankTransId() {
        return telesolutionBankTransId;
    }

    /**
     * Sets the value of the telesolutionBankTransId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelesolutionBankTransId(JAXBElement<String> value) {
        this.telesolutionBankTransId = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the telesolutionChequeDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTelesolutionChequeDate() {
        return telesolutionChequeDate;
    }

    /**
     * Sets the value of the telesolutionChequeDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTelesolutionChequeDate(XMLGregorianCalendar value) {
        this.telesolutionChequeDate = value;
    }

    /**
     * Gets the value of the telesolutionPayment property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTelesolutionPayment() {
        return telesolutionPayment;
    }

    /**
     * Sets the value of the telesolutionPayment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTelesolutionPayment(Double value) {
        this.telesolutionPayment = value;
    }

    /**
     * Gets the value of the telesolutionPaymentBank property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelesolutionPaymentBank() {
        return telesolutionPaymentBank;
    }

    /**
     * Sets the value of the telesolutionPaymentBank property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelesolutionPaymentBank(JAXBElement<String> value) {
        this.telesolutionPaymentBank = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the telesolutionPaymentBranch property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelesolutionPaymentBranch() {
        return telesolutionPaymentBranch;
    }

    /**
     * Sets the value of the telesolutionPaymentBranch property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelesolutionPaymentBranch(JAXBElement<String> value) {
        this.telesolutionPaymentBranch = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the telesolutionPaymentCity property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelesolutionPaymentCity() {
        return telesolutionPaymentCity;
    }

    /**
     * Sets the value of the telesolutionPaymentCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelesolutionPaymentCity(JAXBElement<String> value) {
        this.telesolutionPaymentCity = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the telesolutionPaymentDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTelesolutionPaymentDate() {
        return telesolutionPaymentDate;
    }

    /**
     * Sets the value of the telesolutionPaymentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTelesolutionPaymentDate(XMLGregorianCalendar value) {
        this.telesolutionPaymentDate = value;
    }

    /**
     * Gets the value of the telesolutionPaymentMode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelesolutionPaymentMode() {
        return telesolutionPaymentMode;
    }

    /**
     * Sets the value of the telesolutionPaymentMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelesolutionPaymentMode(JAXBElement<String> value) {
        this.telesolutionPaymentMode = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the telesolutionPaymentRefId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelesolutionPaymentRefId() {
        return telesolutionPaymentRefId;
    }

    /**
     * Sets the value of the telesolutionPaymentRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelesolutionPaymentRefId(JAXBElement<String> value) {
        this.telesolutionPaymentRefId = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the telesolutionPgTransId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelesolutionPgTransId() {
        return telesolutionPgTransId;
    }

    /**
     * Sets the value of the telesolutionPgTransId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelesolutionPgTransId(JAXBElement<String> value) {
        this.telesolutionPgTransId = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the telesolutionReferenceTransId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelesolutionReferenceTransId() {
        return telesolutionReferenceTransId;
    }

    /**
     * Sets the value of the telesolutionReferenceTransId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelesolutionReferenceTransId(JAXBElement<String> value) {
        this.telesolutionReferenceTransId = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTitle(JAXBElement<String> value) {
        this.title = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the typeOfApplication property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTypeOfApplication() {
        return typeOfApplication;
    }

    /**
     * Sets the value of the typeOfApplication property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTypeOfApplication(JAXBElement<String> value) {
        this.typeOfApplication = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the usageBoosterPlanCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getUsageBoosterPlanCode() {
        return usageBoosterPlanCode;
    }

    /**
     * Sets the value of the usageBoosterPlanCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setUsageBoosterPlanCode(JAXBElement<ArrayOfstring> value) {
        this.usageBoosterPlanCode = ((JAXBElement<ArrayOfstring> ) value);
    }

    /**
     * Gets the value of the vehicleMake property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getVehicleMake() {
        return vehicleMake;
    }

    /**
     * Sets the value of the vehicleMake property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setVehicleMake(JAXBElement<String> value) {
        this.vehicleMake = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the vehicleRegistrationNo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getVehicleRegistrationNo() {
        return vehicleRegistrationNo;
    }

    /**
     * Sets the value of the vehicleRegistrationNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setVehicleRegistrationNo(JAXBElement<String> value) {
        this.vehicleRegistrationNo = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the visaType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getVisaType() {
        return visaType;
    }

    /**
     * Sets the value of the visaType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setVisaType(JAXBElement<String> value) {
        this.visaType = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the visaValidity property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getVisaValidity() {
        return visaValidity;
    }

    /**
     * Sets the value of the visaValidity property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setVisaValidity(XMLGregorianCalendar value) {
        this.visaValidity = value;
    }

    /**
     * Gets the value of the zone property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getZone() {
        return zone;
    }

    /**
     * Sets the value of the zone property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setZone(JAXBElement<String> value) {
        this.zone = ((JAXBElement<String> ) value);
    }

}
