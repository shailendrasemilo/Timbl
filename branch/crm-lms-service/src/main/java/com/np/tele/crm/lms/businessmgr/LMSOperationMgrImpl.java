package com.np.tele.crm.lms.businessmgr;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.LMSDto;
import com.np.tele.crm.lms.dao.ILMSOperationDao;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class LMSOperationMgrImpl
    implements ILMSOperationMgr
{
    private ILMSOperationDao    lmsOperationDao = null;
    private static final Logger LOGGER          = Logger.getLogger( LMSOperationMgrImpl.class );

    public ILMSOperationDao getLmsOperationDao()
    {
        return lmsOperationDao;
    }

    public void setLmsOperationDao( ILMSOperationDao lmsOperationDao )
    {
        this.lmsOperationDao = lmsOperationDao;
    }

    @Override
    public LMSDto lmsOperation( String inOperationParam, LMSDto inLMSDto )
    {
        CRMServiceCode statuCode = CRMServiceCode.CRM999;
        LOGGER.info( "IN LmsOperation Method. Operation Name ::" + inOperationParam );
        boolean setStatusCode = true;
        try
        {
            if ( ( CommonValidator.isValidCollection( inLMSDto.getLeadPojos() ) )
                    || StringUtils.isValidObj( inLMSDto.getLeadPojo() ) )
            {
                if ( StringUtils.equals( ServiceParameter.CREATE.getParameter(), inOperationParam ) )
                {
                    inLMSDto = getLmsOperationDao().createLead( inLMSDto );
                    setStatusCode = false;
                }
                else if ( StringUtils.equals( ServiceParameter.SAVE.getParameter(), inOperationParam ) )
                {
                    if ( inLMSDto.getLeadPojo().getLmsId() > 0 )
                    {
                        inLMSDto = getLmsOperationDao().saveLead( inLMSDto );
                        setStatusCode = false;
                    }
                    else
                    {
                        LOGGER.info( "Required Details Are Not Present, While Saving Lead " );
                        statuCode = CRMServiceCode.CRM997;
                    }
                }
                else if ( StringUtils.equals( ServiceParameter.FORWARD.getParameter(), inOperationParam ) )
                {
                    if ( StringUtils.isNotBlank( inLMSDto.getToStage() ) && inLMSDto.getLeadPojo().getLmsId() > 0 )
                    {
                        if ( StringUtils.equals( CRMOperationStages.AREA_MANAGER.getCode(), inLMSDto.getToStage() ) )
                        {
                            if ( StringUtils.isNotBlank( inLMSDto.getToUserId() ) )
                            {
                                LOGGER.info( "going to forward Lead " );
                                inLMSDto = getLmsOperationDao().forwardLead( inLMSDto );
                                setStatusCode = false;
                            }
                            else
                            {
                                LOGGER.info( "To user Id Found Null For Stage " + inLMSDto.getToStage() );
                                statuCode = CRMServiceCode.CRM997;
                            }
                        }
                        else
                        {
                            LOGGER.info( "going to forward Lead " );
                            inLMSDto = getLmsOperationDao().forwardLead( inLMSDto );
                            setStatusCode = false;
                        }
                    }
                    else
                    {
                        LOGGER.info( "Required Details Are Not Present, While Forwarding Lead to Another Stage" );
                        statuCode = CRMServiceCode.CRM997;
                    }
                }
                else if ( ( StringUtils.equals( ServiceParameter.MATURE.getParameter(), inOperationParam )/* && StringUtils
                                                                                                          .isValidObj( inLMSDto.getCrfMappingPojos() ) && !inLMSDto.getCrfMappingPojos()
                                                                                                          .isEmpty() */)
                        || StringUtils.equals( ServiceParameter.CLOSE.getParameter(), inOperationParam ) )
                {
                    if ( inLMSDto.getLeadPojo().getLmsId() > 0 )
                    {
                        inLMSDto = getLmsOperationDao().changeLeadStatus( inLMSDto, inOperationParam );
                        setStatusCode = false;
                    }
                    else
                    {
                        LOGGER.info( "Required Details Are Not Present, While Going to Change the status of Lead " );
                        statuCode = CRMServiceCode.CRM997;
                    }
                }
                else if ( StringUtils.equals( ServiceParameter.LIST.getParameter(), inOperationParam ) )
                {
                    inLMSDto = getLmsOperationDao().leadCustomerProfileSearch( inLMSDto );
                    setStatusCode = false;
                }
                else if ( StringUtils.equals( ServiceParameter.SEARCH.getParameter(), inOperationParam ) )
                {
                    inLMSDto = getLmsOperationDao().searchSocietyByPinCode( inLMSDto );
                }
                else if ( StringUtils.equals( "AreaId", inOperationParam ) )
                {
                    inLMSDto = getLmsOperationDao().getAreaNameByAreaId( inLMSDto );
                }
                else
                {
                    LOGGER.info( "Operation Name Is Not Correct. Operation Name ::" + inOperationParam );
                    statuCode = CRMServiceCode.CRM007;
                }
            }
            else if ( StringUtils.equals( ServiceParameter.CHECK_DUPLICATE.getParameter(), inOperationParam ) )
            {
                LOGGER.info( "checking duplicate crfId:" + inLMSDto.getLmsCrfMappingPojo().getCrfValue() );
                setStatusCode = getLmsOperationDao().checkDuplicateCRFId( inLMSDto );
                if ( setStatusCode )
                {
                    statuCode = CRMServiceCode.CRM307;
                }
                else
                {
                    setStatusCode = false;
                }
            }
            else
            {
                statuCode = CRMServiceCode.CRM997;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Exception in lmsOperation Method ", ex );
            statuCode = CRMServiceCode.CRM997;
        }
        finally
        {
            if ( setStatusCode )
            {
                inLMSDto.setStatusCode( statuCode.getStatusCode() );
                inLMSDto.setStatusDesc( statuCode.getStatusDesc() );
            }
        }
        return inLMSDto;
    }
}
