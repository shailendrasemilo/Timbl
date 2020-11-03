package com.np.tele.crm.billing.plan.request.engine;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.np.tele.crm.billing.plan.request.engine.bm.BillingPlanRequestManagerImpl;
import com.np.tele.crm.billing.plan.request.engine.bm.IBillingPlanRequestManager;
import com.np.tele.crm.utils.StringUtils;

public class BillingPlanRequestEngine
{
    static
    {
        DOMConfigurator.configureAndWatch( System.getProperty( "log4j.configuration" ) );
    }
    private static final Logger        LOGGER                    = Logger.getLogger( BillingPlanRequestEngine.class );
    private IBillingPlanRequestManager billingPlanRequestManager = null;

    public IBillingPlanRequestManager getBillingPlanRequestManager()
    {
        return billingPlanRequestManager;
    }

    public void setBillingPlanRequestManager( IBillingPlanRequestManager inBillingPlanRequestManager )
    {
        billingPlanRequestManager = inBillingPlanRequestManager;
    }

    private boolean proces( String inAuthority )
    {
        return getBillingPlanRequestManager().getBillingPlanRequest( inAuthority );
    }

    public void init()
    {
        LOGGER.info( "Billing Plan Request Engine initiated..." );
        setBillingPlanRequestManager( new BillingPlanRequestManagerImpl() );
    }

    public static void main( String[] args )
    {
        if ( StringUtils.isNotBlank( args[0] ) )
        {
            boolean processFlag = true;
            String authority = args[0];
            LOGGER.info( "Authority : " + authority );
            BillingPlanRequestEngine billingPlanRequestEngine = new BillingPlanRequestEngine();
            billingPlanRequestEngine.init();
            while ( true )
            {
                processFlag = billingPlanRequestEngine.proces( authority );
                LOGGER.info( "Returned to main, is further processing required : " + ( processFlag ? "Yes" : "No" ) );
                try
                {
                    Thread.sleep( 60 * 60 * 60 );
                }
                catch ( InterruptedException ex )
                {
                    LOGGER.info( "InterruptedException:" + ex.getMessage() );
                }
            }
        }
    }
}
