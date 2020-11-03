package com.np.tele.crm.dto;

import java.io.Serializable;
import java.util.List;

import com.np.tele.crm.pojos.CrmQrcActionTakenPojo;
import com.np.tele.crm.pojos.CrmQrcAttributedToPojo;
import com.np.tele.crm.pojos.CrmQrcCategoryBinMappingPojo;
import com.np.tele.crm.pojos.CrmQrcRootCausePojo;
import com.np.tele.crm.pojos.CrmQrcSubSubCategoriesPojo;

public class QrcConfigDto
    implements Serializable
{
    private String                             loggedUser      = null;
    private String                             statusCode      = null;
    private String                             statusDesc      = null;
    private String                             clientIPAddress = null;
    private String                             serverIPAddress = null;
    private CrmQrcActionTakenPojo              qrcActionTakenPojo;
    private List<CrmQrcActionTakenPojo>        qrcActionTakenPojos;
    private CrmQrcRootCausePojo                qrcRootCausePojo;
    private List<CrmQrcRootCausePojo>          qrcRootCausePojos;
    private CrmQrcCategoryBinMappingPojo       categoryBinMappingPojo;
    private List<CrmQrcCategoryBinMappingPojo> categoryBinMappingPojos;
    private List<CrmQrcAttributedToPojo>       qrcAttributedToPojos;
    private CrmQrcAttributedToPojo             crmQrcAttributedToPojo;
    private CrmQrcSubSubCategoriesPojo         crmQrcSubSubCategoriesPojo;
    private List<CrmQrcSubSubCategoriesPojo>   crmQrcSubSubCategoriesPojos;
    private List<String>                       userAssociatedServices;
    private List<String>                       userAssociatedPartners;

    public List<String> getUserAssociatedServices()
    {
        return userAssociatedServices;
    }

    public void setUserAssociatedServices( List<String> inUserAssociatedServices )
    {
        userAssociatedServices = inUserAssociatedServices;
    }

    public List<String> getUserAssociatedPartners()
    {
        return userAssociatedPartners;
    }

    public void setUserAssociatedPartners( List<String> inUserAssociatedPartners )
    {
        userAssociatedPartners = inUserAssociatedPartners;
    }

    public CrmQrcCategoryBinMappingPojo getCategoryBinMappingPojo()
    {
        return categoryBinMappingPojo;
    }

    public void setCategoryBinMappingPojo( CrmQrcCategoryBinMappingPojo inCategoryBinMappingPojo )
    {
        categoryBinMappingPojo = inCategoryBinMappingPojo;
    }

    public List<CrmQrcCategoryBinMappingPojo> getCategoryBinMappingPojos()
    {
        return categoryBinMappingPojos;
    }

    public void setCategoryBinMappingPojos( List<CrmQrcCategoryBinMappingPojo> inCategoryBinMappingPojos )
    {
        categoryBinMappingPojos = inCategoryBinMappingPojos;
    }

    public String getLoggedUser()
    {
        return loggedUser;
    }

    public void setLoggedUser( String inLoggedUser )
    {
        loggedUser = inLoggedUser;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode( String inStatusCode )
    {
        statusCode = inStatusCode;
    }

    public String getStatusDesc()
    {
        return statusDesc;
    }

    public void setStatusDesc( String inStatusDesc )
    {
        statusDesc = inStatusDesc;
    }

    public String getClientIPAddress()
    {
        return clientIPAddress;
    }

    public void setClientIPAddress( String inClientIPAddress )
    {
        clientIPAddress = inClientIPAddress;
    }

    public String getServerIPAddress()
    {
        return serverIPAddress;
    }

    public void setServerIPAddress( String inServerIPAddress )
    {
        serverIPAddress = inServerIPAddress;
    }

    public CrmQrcActionTakenPojo getQrcActionTakenPojo()
    {
        return qrcActionTakenPojo;
    }

    public void setQrcActionTakenPojo( CrmQrcActionTakenPojo inQrcRcaPojo )
    {
        qrcActionTakenPojo = inQrcRcaPojo;
    }

    public List<CrmQrcActionTakenPojo> getQrcActionTakenPojos()
    {
        return qrcActionTakenPojos;
    }

    public void setQrcActionTakenPojos( List<CrmQrcActionTakenPojo> inQrcRcaPojos )
    {
        qrcActionTakenPojos = inQrcRcaPojos;
    }

    public CrmQrcRootCausePojo getQrcRootCausePojo()
    {
        return qrcRootCausePojo;
    }

    public void setQrcRootCausePojo( CrmQrcRootCausePojo inQrcResolutionCodePojo )
    {
        qrcRootCausePojo = inQrcResolutionCodePojo;
    }

    public List<CrmQrcRootCausePojo> getQrcRootCausePojos()
    {
        return qrcRootCausePojos;
    }

    public void setQrcRootCausePojos( List<CrmQrcRootCausePojo> inQrcResolutionCodePojos )
    {
        qrcRootCausePojos = inQrcResolutionCodePojos;
    }

    public List<CrmQrcAttributedToPojo> getQrcAttributedToPojos()
    {
        return qrcAttributedToPojos;
    }

    public void setQrcAttributedToPojos( List<CrmQrcAttributedToPojo> qrcAttributedToPojos )
    {
        this.qrcAttributedToPojos = qrcAttributedToPojos;
    }

    public CrmQrcAttributedToPojo getCrmQrcAttributedToPojo()
    {
        return crmQrcAttributedToPojo;
    }

    public void setCrmQrcAttributedToPojo( CrmQrcAttributedToPojo crmQrcAttributedToPojo )
    {
        this.crmQrcAttributedToPojo = crmQrcAttributedToPojo;
    }

    public CrmQrcSubSubCategoriesPojo getCrmQrcSubSubCategoriesPojo()
    {
        return crmQrcSubSubCategoriesPojo;
    }

    public void setCrmQrcSubSubCategoriesPojo( CrmQrcSubSubCategoriesPojo crmQrcSubSubCategoriesPojo )
    {
        this.crmQrcSubSubCategoriesPojo = crmQrcSubSubCategoriesPojo;
    }

    public List<CrmQrcSubSubCategoriesPojo> getCrmQrcSubSubCategoriesPojos()
    {
        return crmQrcSubSubCategoriesPojos;
    }

    public void setCrmQrcSubSubCategoriesPojos( List<CrmQrcSubSubCategoriesPojo> crmQrcSubSubCategoriesPojos )
    {
        this.crmQrcSubSubCategoriesPojos = crmQrcSubSubCategoriesPojos;
    }
}
