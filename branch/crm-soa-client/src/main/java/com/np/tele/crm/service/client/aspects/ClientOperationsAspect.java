package com.np.tele.crm.service.client.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.StopWatch;
import com.np.tele.crm.utils.StringUtils;

@Aspect
public class ClientOperationsAspect
{
    private static final Logger LOGGER = Logger.getLogger( ClientOperationsAspect.class );

    @Around("@within(com.np.tele.crm.constants.ClientOperations)")
    public Object swAround( ProceedingJoinPoint pJoinPoint )
        throws Throwable
    {
        StopWatch stopWatch = null;
        Object result = null;
        if ( StringUtils.isValidObj( pJoinPoint ) )
        {
            if ( StringUtils.isValidObj( pJoinPoint.getTarget() ) && StringUtils.isValidObj( pJoinPoint.getSignature() ) )
            {
                String className = pJoinPoint.getTarget().getClass().getSimpleName();
                stopWatch = new StopWatch( className + ":" + pJoinPoint.getSignature().getName() );
                stopWatch.start();
                if ( StringUtils.endsWith( className, "Client" ) )
                {
                    Object[] args = pJoinPoint.getArgs();
                    Object obj = null;
                    for ( int i = 0; i < args.length; i++ )
                    {
                        obj = args[i];
                        if ( ! ( obj instanceof String ) && StringUtils.isValidObj( obj ) )
                        {
                            CRMUtils.setSimpleProperty( obj, IAppConstants.SERVER_IP_ADDRESS, CRMUtils.getServerIp() );
                            CRMUtils.setSimpleProperty( obj, IAppConstants.CLIENT_IP_ADDRESS, Thread.currentThread()
                                    .getName() );
                            break;
                        }
                    }
                }
            }
            result = pJoinPoint.proceed();
        }
        if ( StringUtils.isValidObj( pJoinPoint ) )
        {
            if ( StringUtils.isValidObj( pJoinPoint.getTarget() ) && StringUtils.isValidObj( pJoinPoint.getSignature() ) )
            {
                stopWatch.stop();
                LOGGER.info( stopWatch );
            }
        }
        return result;
    }
}
