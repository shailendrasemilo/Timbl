package com.np.tele.crm.masterdata.bm;

import com.np.tele.crm.alerts.bm.IEmailAlert;
import com.np.tele.crm.alerts.bm.ISmsAlert;
import com.np.tele.crm.cap.bm.ICrmCapManager;
import com.np.tele.crm.config.bm.ICrmConfigManager;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.finance.bm.IFinanceManager;
import com.np.tele.crm.gis.bm.IGISManager;
import com.np.tele.crm.inbox.bm.IInboxManager;
import com.np.tele.crm.lms.bm.ILmsManager;
import com.np.tele.crm.qrc.bm.IQrcManager;
import com.np.tele.crm.qrc.config.bm.IQrcConfigManager;
import com.np.tele.crm.usrmngmnt.bm.IUserBMngr;

public class AppDataManagerImpl
    implements IAppDataManager
{
    private IUserBMngr        userMngmntBm;
    private IMasterBMngr      masterDataBm;
    private ISmsAlert         smsAlertImpl;
    private IEmailAlert       emailAlertImpl;
    private IGISManager       gisManagerImpl;
    private ICrmConfigManager crmConfigManager;
    private IFinanceManager   financeManagerBm;
    private IQrcManager       qrcManagerBm;
    private ILmsManager       lmsManagerBm;
    private ICrmCapManager    crmCapManagerBm;
    private IQrcConfigManager qrcConfigManagerBm;
    private IInboxManager     inboxManagerBm;

    public AppDataManagerImpl()
    {
        IAppConstants.flyWeightBeanMap.put( IAppConstants.APP_DATA_MANAGER, this );
    }

    public IQrcConfigManager getQrcConfigManagerBm()
    {
        return qrcConfigManagerBm;
    }

    public void setQrcConfigManagerBm( IQrcConfigManager inQrcConfigManagerBm )
    {
        qrcConfigManagerBm = inQrcConfigManagerBm;
        IAppConstants.flyWeightBeanMap.put( IAppConstants.QRC_CONFIG_MANAGER, qrcConfigManagerBm );
    }

    public IUserBMngr getUserMngmntBm()
    {
        return userMngmntBm;
    }

    public void setUserMngmntBm( IUserBMngr inUserMngmntBm )
    {
        userMngmntBm = inUserMngmntBm;
        IAppConstants.flyWeightBeanMap.put( IAppConstants.USER_MANAGEMENT, userMngmntBm );
    }

    public IMasterBMngr getMasterDataBm()
    {
        return masterDataBm;
    }

    public void setMasterDataBm( IMasterBMngr inMasterDataBm )
    {
        masterDataBm = inMasterDataBm;
        IAppConstants.flyWeightBeanMap.put( IAppConstants.MASTER_DATA, masterDataBm );
    }

    public ISmsAlert getSmsAlertImpl()
    {
        return smsAlertImpl;
    }

    public void setSmsAlertImpl( ISmsAlert inSmsAlertImpl )
    {
        smsAlertImpl = inSmsAlertImpl;
        IAppConstants.flyWeightBeanMap.put( IAppConstants.SMS_ALERT, smsAlertImpl );
    }

    public IEmailAlert getEmailAlertImpl()
    {
        return emailAlertImpl;
    }

    public void setEmailAlertImpl( IEmailAlert inEmailAlertImpl )
    {
        emailAlertImpl = inEmailAlertImpl;
        IAppConstants.flyWeightBeanMap.put( IAppConstants.EMAIL_ALERT, emailAlertImpl );
    }

    public IGISManager getGisManagerImpl()
    {
        return gisManagerImpl;
    }

    public void setGisManagerImpl( IGISManager inGisManagerImpl )
    {
        gisManagerImpl = inGisManagerImpl;
        IAppConstants.flyWeightBeanMap.put( IAppConstants.GIS_MANAGER, gisManagerImpl );
    }

    public ICrmConfigManager getCrmConfigManager()
    {
        return crmConfigManager;
    }

    public void setCrmConfigManager( ICrmConfigManager inCrmConfigManager )
    {
        crmConfigManager = inCrmConfigManager;
        IAppConstants.flyWeightBeanMap.put( IAppConstants.CRM_CONFIG_MANAGER, crmConfigManager );
    }

    public IFinanceManager getFinanceManagerBm()
    {
        return financeManagerBm;
    }

    public void setFinanceManagerBm( IFinanceManager financeManagerBm )
    {
        this.financeManagerBm = financeManagerBm;
        IAppConstants.flyWeightBeanMap.put( IAppConstants.FINANCE_MANAGER, financeManagerBm );
    }

    /**
     * @return the qrcManagerBm
     */
    public IQrcManager getQrcManagerBm()
    {
        return qrcManagerBm;
    }

    /**
     * @param inQrcManagerBm the qrcManagerBm to set
     */
    public void setQrcManagerBm( IQrcManager inQrcManagerBm )
    {
        this.qrcManagerBm = inQrcManagerBm;
        IAppConstants.flyWeightBeanMap.put( IAppConstants.QRC_MANAGER, qrcManagerBm );
    }

    public ILmsManager getLmsManagerBm()
    {
        return lmsManagerBm;
    }

    public void setLmsManagerBm( ILmsManager lmsManagerBm )
    {
        this.lmsManagerBm = lmsManagerBm;
        IAppConstants.flyWeightBeanMap.put( IAppConstants.LMS_MANAGER, lmsManagerBm );
    }

    public ICrmCapManager getCrmCapManagerBm()
    {
        return crmCapManagerBm;
    }

    public void setCrmCapManagerBm( ICrmCapManager inCrmCapManagerBm )
    {
        this.crmCapManagerBm = inCrmCapManagerBm;
        IAppConstants.flyWeightBeanMap.put( IAppConstants.CRM_CAP_MANAGER, crmCapManagerBm );
    }

    public IInboxManager getInboxManagerBm()
    {
        return inboxManagerBm;
    }

    public void setInboxManagerBm( IInboxManager inInboxManagerBm )
    {
        this.inboxManagerBm = inInboxManagerBm;
        IAppConstants.flyWeightBeanMap.put( IAppConstants.INBOX_MANAGER, inboxManagerBm );
    }
}
