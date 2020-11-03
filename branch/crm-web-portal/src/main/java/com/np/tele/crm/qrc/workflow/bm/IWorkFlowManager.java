package com.np.tele.crm.qrc.workflow.bm;

import java.util.List;

import com.np.tele.crm.qrc.forms.QrcForm;
import com.np.tele.crm.service.client.CrmFinanceDto;
import com.np.tele.crm.service.client.CrmPartnerMappingPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.GisDto;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.RemarksPojo;

public interface IWorkFlowManager
{
    public CrmQrcDto approveRejectWaiver( QrcForm qrcForm );

    public CrmQrcDto shiftingInitiation( QrcForm qrcForm );

    public CrmQrcDto submitIfr( QrcForm qrcForm, String userId );

    public List<CrmPartnerMappingPojo> getServicePartners( String inNpId, String inProduct );

    public CrmFinanceDto paymentPosting( QrcForm qrcForm );

    public CrmFinanceDto paymentReversal( QrcForm qrcForm, String userId, RemarksPojo inRemarks );

    public CrmQrcDto forwardToNextBin( QrcForm qrcForm );

    public CrmQrcDto getShiftingDetials( QrcForm qrcForm );

    public CrmQrcDto submitcsd( QrcForm qrcForm, String userId );

    public List<PartnerPojo> getPartnerDetailsbyId( String inNpId );

    public CrmQrcDto submitftLevel2( QrcForm qrcForm, String userId );

    public CrmQrcDto submitIfrEOCL1( QrcForm qrcForm, String userId );

    public CrmQrcDto submitCSDLevel3( QrcForm qrcForm, String userId );

    public CrmQrcDto submitNOCLevel1( CrmQrcDto crmQrcDto );

    public GisDto getGISDetailsById( String stateId, String cityId, String areaId, String societyId );

    public CrmQrcDto updateCustomerDetailsForMAC( QrcForm qrcForm, String userId );
}
