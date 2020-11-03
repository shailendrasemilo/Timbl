/**
 * 
 */
package com.np.tele.crm.qrc.profile.bm;

import com.np.tele.crm.qrc.forms.QrcForm;
import com.np.tele.crm.service.client.CrmQrcDto;

/**
 * @author
 *
 */
public interface ICustomerProfileMgr
{
    public CrmQrcDto updateCustomerCategory( QrcForm qrcForm, CrmQrcDto crmQrcDto );

    public CrmQrcDto updateCustomerBillCycle( QrcForm qrcForm );

    public CrmQrcDto getCustomerBillCycle( String inCustomerId );

    public CrmQrcDto saveGracePeriod( QrcForm qrcForm );

    public CrmQrcDto cancelBillCycleRequest( String customerId, String recordID, String string );
}
