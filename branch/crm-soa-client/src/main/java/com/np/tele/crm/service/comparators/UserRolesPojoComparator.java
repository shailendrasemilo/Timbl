package com.np.tele.crm.service.comparators;

import java.util.Comparator;

import com.np.tele.crm.service.client.UserRolesPojo;
import com.np.tele.crm.utils.StringUtils;

public class UserRolesPojoComparator
    implements Comparator<UserRolesPojo>
{
    @Override
    public int compare( UserRolesPojo inExist, UserRolesPojo inNew )
    {
        if ( !StringUtils.equals( inExist.getUserId(), inNew.getUserId() ) )
        {
            return -1;
        }
        if ( !StringUtils.equals( inExist.getStatus(), inNew.getStatus() ) )
        {
            return -1;
        }
        if ( !StringUtils.equals( inExist.getParameterValue(), inNew.getParameterValue() ) )
        {
            return -1;
        }
        if ( inExist.getRecordId() != inNew.getRecordId() )
        {
            return -1;
        }
        if ( StringUtils.isValidObj( inExist.getCrmParameter() ) && StringUtils.isValidObj( inNew.getCrmParameter() ) )
        {
            if ( ! ( inExist.getCrmParameter().equals( inNew.getCrmParameter() ) ) )
            {
                return -1;
            }
        }
        if ( StringUtils.isValidObj( inExist.getGroups() ) && StringUtils.isValidObj( inNew.getGroups() ) )
        {
            if ( ! ( inExist.getGroups().equals( inNew.getGroups() ) ) )
            {
                return -1;
            }
            if ( inExist.isEditable() != inNew.isEditable() )
            {
                return -1;
            }
        }
        if ( StringUtils.isValidObj( inExist.getCrmRoles() ) && StringUtils.isValidObj( inNew.getCrmRoles() ) )
        {
            if ( ! ( inExist.getCrmRoles().equals( inNew.getCrmRoles() ) ) )
            {
                return -1;
            }
        }
        if ( inExist.isCreateAccess() != inNew.isCreateAccess() )
        {
            return -1;
        }
        if ( inExist.isDeleteAccess() != inNew.isDeleteAccess() )
        {
            return -1;
        }
        if ( inExist.isReadAccess() != inNew.isReadAccess() )
        {
            return -1;
        }
        if ( inExist.isRecoverAccess() != inNew.isRecoverAccess() )
        {
            return -1;
        }
        if ( inExist.isUpdateAccess() != inNew.isUpdateAccess() )
        {
            return -1;
        }
        return 0;
    }
}
