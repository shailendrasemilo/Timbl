package com.np.tele.crm.qrc.dao;

import java.util.List;

import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.dto.CrmQrcDto;
import com.np.tele.crm.ext.pojos.CustomerUsageDetailsPojo;

public interface IBillingDataDao
{
    CRMServiceCode getCustomerUsageDetails( String inUsageFromDate,
                                            String inUsageToDate,
                                            String inCustomerId,
                                            List<CustomerUsageDetailsPojo> inCustomerUsageDetailsPojos );

    CRMServiceCode getCustUsageDetails( String inUsageFromDate,
                                        String inUsageToDate,
                                        String inCustomerId,
                                        List<CustomerUsageDetailsPojo> inCustomerUsageDetailsPojos );

    CrmQrcDto getCustUsageDetailsInGB( CrmQrcDto inCrmQrcDto );
}
