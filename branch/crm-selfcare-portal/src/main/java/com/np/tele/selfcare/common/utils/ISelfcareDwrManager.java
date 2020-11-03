package com.np.tele.selfcare.common.utils;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.CrmSelfcareCategoriesPojo;

public interface ISelfcareDwrManager
{
    List<CrmSelfcareCategoriesPojo> getSelfcareCategories( String subject );

    String[] saveCustomerProfileDetails( String recordID, String custID, String rolCategory, String newValue );

    String[] sendEmailVerificationLink( HttpSession inSession );

    List<com.np.tele.crm.service.client.ContentPojo> getMigrationPolicy( String oldBasePlanCode,
                                                                           String newBasePlanCode,
                                                                           String oldAddonPlanCode,
                                                                           String newAddonPlanCode,
                                                                           String serviceType,
                                                                           String customerId );
}
