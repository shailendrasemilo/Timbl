/**
 * 
 */
package com.np.tele.crm.qrc.profile.dao;

import com.np.tele.crm.dto.CrmQrcDto;

/**
 * @author 
 *
 */
public interface ICustomerProfileDao
{
    public CrmQrcDto updateBillingCycle( CrmQrcDto inCrmQrcDto );

    public CrmQrcDto updateCustomerProfile( CrmQrcDto inCrmQrcDto );

    public CrmQrcDto getBillCycleHistory( CrmQrcDto inCrmQrcDto );

    public CrmQrcDto updatePrepaidCustomerGracePeriod( CrmQrcDto inCrmQrcDto );
    
    public CrmQrcDto updateMultipleGracePeriod( CrmQrcDto inCrmQrcDto );

    public CrmQrcDto cancelBillCycleRequest( CrmQrcDto inCrmQrcDto );

    public CrmQrcDto synchronizeBillCycleToBilling( CrmQrcDto inCrmQrcDto );
}
