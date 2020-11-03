package com.np.tele.crm.qrc.config.forms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.CrmQrcActionTakenPojo;
import com.np.tele.crm.service.client.CrmQrcAttributedToPojo;
import com.np.tele.crm.service.client.CrmQrcCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcCategoryBinMappingPojo;
import com.np.tele.crm.service.client.CrmQrcRootCausePojo;
import com.np.tele.crm.service.client.CrmQrcSubCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;

public class QrcConfigForm
    extends ActionForm
{
    private final static Logger                LOGGER = Logger.getLogger( QrcConfigForm.class );
    private long                               qrcRcaId;
    private List<CrmQrcActionTakenPojo>        qrcActionTakenPojos;
    private long                               qrcCategoryId;
    private long                               qrcSubCategoryId;
    private List<CrmQrcCategoriesPojo>         qrcCategories;
    private List<CrmQrcSubCategoriesPojo>      qrcSubCategoriesPojos;
    private List<CrmQrcSubSubCategoriesPojo>   qrcSubSubCategoriesPojos;
    private String                             serviceName;
    private List<ContentPojo>                  productTypes;
    private List<CrmQrcRootCausePojo>          qrcRootCausePojos;
    private List<CrmQrcAttributedToPojo>       attributedToPojos;
    private long                               qrcResolutionCodeId;
    private long                               qrcSubSubCategoryId;
    private String                             qrcType;
    private List<CrmQrcSubCategoriesPojo>      qrcSubCategories;
    private List<CrmQrcSubSubCategoriesPojo>   qrcSubSubCategories;
    private List<CrmQrcCategoryBinMappingPojo> categoryBinMappingPojos;
    private List<CrmRcaReasonPojo>             functionBins;
    private String                             resolutionStr;
    private List<CrmRcaReasonPojo>             functionalBinList;
    private List<ContentPojo>                  resolutionTypeList;
    private List<ContentPojo>                  qrcTypeList;
    private String                             moduleType;

    public String getResolutionStr()
    {
        return resolutionStr;
    }

    public void setResolutionStr( String inResolutionStr )
    {
        resolutionStr = inResolutionStr;
    }

    public long getQrcSubSubCategoryId()
    {
        return qrcSubSubCategoryId;
    }

    public void setQrcSubSubCategoryId( long inQrcSubSubCategoryId )
    {
        qrcSubSubCategoryId = inQrcSubSubCategoryId;
    }

    public String getQrcType()
    {
        return qrcType;
    }

    public void setQrcType( String inQrcType )
    {
        qrcType = inQrcType;
    }

    public List<CrmQrcSubCategoriesPojo> getQrcSubCategories()
    {
        return qrcSubCategories;
    }

    public void setQrcSubCategories( List<CrmQrcSubCategoriesPojo> inQrcSubCategories )
    {
        qrcSubCategories = inQrcSubCategories;
    }

    public List<CrmQrcSubSubCategoriesPojo> getQrcSubSubCategories()
    {
        return qrcSubSubCategories;
    }

    public void setQrcSubSubCategories( List<CrmQrcSubSubCategoriesPojo> inQrcSubSubCategories )
    {
        qrcSubSubCategories = inQrcSubSubCategories;
    }

    public List<CrmQrcCategoryBinMappingPojo> getCategoryBinMappingPojos()
    {
        return categoryBinMappingPojos;
    }

    public void setCategoryBinMappingPojos( List<CrmQrcCategoryBinMappingPojo> inCategoryBinMappingPojos )
    {
        categoryBinMappingPojos = inCategoryBinMappingPojos;
    }

    public List<CrmRcaReasonPojo> getFunctionBins()
    {
        return functionBins;
    }

    public void setFunctionBins( List<CrmRcaReasonPojo> inFunctionBins )
    {
        functionBins = inFunctionBins;
    }

    public long getQrcRcaId()
    {
        return qrcRcaId;
    }

    public void setQrcRcaId( long inQrcRcaId )
    {
        qrcRcaId = inQrcRcaId;
    }

    public List<CrmQrcActionTakenPojo> getQrcActionTakenPojos()
    {
        return qrcActionTakenPojos;
    }

    public void setQrcActionTakenPojos( List<CrmQrcActionTakenPojo> inQrcActionTakenPojos )
    {
        qrcActionTakenPojos = inQrcActionTakenPojos;
    }

    public long getQrcCategoryId()
    {
        return qrcCategoryId;
    }

    public void setQrcCategoryId( long inQrcCategoryId )
    {
        qrcCategoryId = inQrcCategoryId;
    }

    public List<CrmQrcCategoriesPojo> getQrcCategories()
    {
        return qrcCategories;
    }

    public void setQrcCategories( List<CrmQrcCategoriesPojo> inQrcCategories )
    {
        qrcCategories = inQrcCategories;
    }

    public String getServiceName()
    {
        return serviceName;
    }

    public void setServiceName( String inServiceName )
    {
        serviceName = inServiceName;
    }

    public List<ContentPojo> getProductTypes()
    {
        return productTypes;
    }

    public void setProductTypes( List<ContentPojo> inProductTypes )
    {
        productTypes = inProductTypes;
    }

    public List<CrmQrcAttributedToPojo> getAttributedToPojos()
    {
        return attributedToPojos;
    }

    public void setAttributedToPojos( List<CrmQrcAttributedToPojo> attributedToPojos )
    {
        this.attributedToPojos = attributedToPojos;
    }

    public long getQrcResolutionCodeId()
    {
        return qrcResolutionCodeId;
    }

    public void setQrcResolutionCodeId( long qrcResolutionCodeId )
    {
        this.qrcResolutionCodeId = qrcResolutionCodeId;
    }

    public List<CrmQrcSubCategoriesPojo> getQrcSubCategoriesPojos()
    {
        return qrcSubCategoriesPojos;
    }

    public void setQrcSubCategoriesPojos( List<CrmQrcSubCategoriesPojo> qrcSubCategoriesPojos )
    {
        this.qrcSubCategoriesPojos = qrcSubCategoriesPojos;
    }

    public List<CrmQrcSubSubCategoriesPojo> getQrcSubSubCategoriesPojos()
    {
        return qrcSubSubCategoriesPojos;
    }

    public void setQrcSubSubCategoriesPojos( List<CrmQrcSubSubCategoriesPojo> qrcSubSubCategoriesPojos )
    {
        this.qrcSubSubCategoriesPojos = qrcSubSubCategoriesPojos;
    }

    public long getQrcSubCategoryId()
    {
        return qrcSubCategoryId;
    }

    public void setQrcSubCategoryId( long qrcSubCategoryId )
    {
        this.qrcSubCategoryId = qrcSubCategoryId;
    }

    public List<CrmRcaReasonPojo> getFunctionalBinList()
    {
        return functionalBinList;
    }

    public void setFunctionalBinList( List<CrmRcaReasonPojo> functionalBinList )
    {
        this.functionalBinList = functionalBinList;
    }

    public List<ContentPojo> getResolutionTypeList()
    {
        return resolutionTypeList;
    }

    public void setResolutionTypeList( List<ContentPojo> resolutionTypeList )
    {
        this.resolutionTypeList = resolutionTypeList;
    }

    public List<ContentPojo> getQrcTypeList()
    {
        return qrcTypeList;
    }

    public void setQrcTypeList( List<ContentPojo> qrcTypeList )
    {
        this.qrcTypeList = qrcTypeList;
    }

    public List<CrmQrcRootCausePojo> getQrcRootCausePojos()
    {
        return qrcRootCausePojos;
    }

    public void setQrcRootCausePojos( List<CrmQrcRootCausePojo> inQrcRootCausePojos )
    {
        qrcRootCausePojos = inQrcRootCausePojos;
    }

    public String getModuleType()
    {
        return moduleType;
    }

    public void setModuleType( String inModuleType )
    {
        moduleType = inModuleType;
    }

    @Override
    public void reset( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        LOGGER.info( "in QrcConfigForm:reset()" );
        String method = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        LOGGER.info( "method:: " + method );
        QrcConfigFormHelper.reset( this, method );
        super.reset( inMapping, inRequest );
    }

    @Override
    public ActionErrors validate( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        LOGGER.info( "In validate method........" );
        String method = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        LOGGER.info( "In validate method........" + method );
        ActionErrors actionError = new ActionErrors();
        actionError = QrcConfigFormHelper.validate( this, method );
        return actionError;
    }
}
