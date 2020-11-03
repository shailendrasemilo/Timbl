/**
 * 
 */
package com.np.tele.crm.workflow.dao;

import org.hibernate.Session;

import com.np.tele.crm.dto.ConfigDto;
import com.np.tele.crm.dto.CrmQrcDto;

/**
 * @author
 *
 */
public interface IWorkFlowDao
{
    public CrmQrcDto approveRejectWaiver( CrmQrcDto inCrmQrcDto );

    public CrmQrcDto saveShiftingAddressStages( CrmQrcDto inCrmQrcDto );

    boolean changeInboxBin( ConfigDto inConfigDto, Session inSession );

    public CrmQrcDto getShiftingDetails( CrmQrcDto inCrmQrcDto );
    
    public CrmQrcDto updateCustomerDetails( CrmQrcDto inCrmQrcDto );
}
