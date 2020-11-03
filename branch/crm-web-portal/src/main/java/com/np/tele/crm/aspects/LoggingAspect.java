package com.np.tele.crm.aspects;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.MDC;

import com.np.tele.crm.utils.StopWatch;
import com.np.tele.crm.utils.StringUtils;

@Aspect
public class LoggingAspect
{
    private static final Logger LOGGER = Logger.getLogger( LoggingAspect.class );

    @Pointcut("@within(com.np.tele.crm.constants.AppMonitor)")
    public void beanImplementation()
    {
    }

    @Pointcut("execution(public org.apache.struts.action.ActionForward *(..))")
    public void actionImplementation()
    {
    }

    @Around("actionImplementation()")
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
                HttpServletRequest requestPojo = null;
                for ( final Object argument : pJoinPoint.getArgs() )
                {
                    if ( argument instanceof HttpServletRequest )
                    {
                        requestPojo = (HttpServletRequest) argument;
                        Thread.currentThread().setName( requestPojo.getRemoteAddr() );
                        break;
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
                MDC.remove( "VALUE" );
            }
        }
        return result;
    }
    //    @Before("beanImplementation()")
    //    public void logBeforeAction( JoinPoint joinPoint )
    //    {
    //        if ( StringUtils.isValidObj( joinPoint ) )
    //        {
    //            if ( StringUtils.isValidObj( joinPoint.getTarget() ) && StringUtils.isValidObj( joinPoint.getSignature() ) )
    //            {
    //                //                LOGGER.info( "Entering:" + joinPoint.getTarget().getClass().getSimpleName() + "."
    //                //                        + joinPoint.getSignature().getName() );
    //                String className = joinPoint.getTarget().getClass().getSimpleName();
    //                if ( StringUtils.endsWith( className, "Action" ) )
    //                {
    //                    HttpServletRequest requestPojo = null;
    //                    for ( final Object argument : joinPoint.getArgs() )
    //                    {
    //                        if ( argument instanceof HttpServletRequest )
    //                        {
    //                            requestPojo = (HttpServletRequest) argument;
    //                            Thread.currentThread().setName( requestPojo.getRemoteAddr() );
    //                            break;
    //                        }
    //                    }
    //                }
    //            }
    //        }
    //    }
    //    @After("beanImplementation() || actionImplementation()")
    //    public void logAfterAction( JoinPoint joinPoint )
    //    {
    //        if ( StringUtils.isValidObj( joinPoint ) )
    //        {
    //            if ( StringUtils.isValidObj( joinPoint.getTarget() ) && StringUtils.isValidObj( joinPoint.getSignature() ) )
    //            {
    //                LOGGER.info( "Exiting:" + joinPoint.getTarget().getClass().getSimpleName() + "."
    //                        + joinPoint.getSignature().getName() );
    //            }
    //        }
    //    }
    //    @Before("execution(public * *(..))")
    //    public void logBefore( JoinPoint joinPoint )
    //    {
    //        if ( StringUtils.isValidObj( joinPoint ) )
    //        {
    //            if ( StringUtils.isValidObj( joinPoint.getTarget() ) && StringUtils.isValidObj( joinPoint.getSignature() ) )
    //            {
    //                String className = joinPoint.getTarget().getClass().getSimpleName();
    //                if ( StringUtils.contains( className, "Action" ) )
    //                {
    //                    LOGGER.info( "Entering:" + joinPoint.getTarget().getClass().getSimpleName() + "."
    //                            + joinPoint.getSignature().getName() );
    //                }
    //            }
    //        }
    //    }
    //    @Before("@within(com.np.tele.crm.constants.AppMonitor)")
    //    public void logBefore( JoinPoint joinPoint )
    //    {
    //        if ( StringUtils.isValidObj( joinPoint ) )
    //        {
    //            if ( StringUtils.isValidObj( joinPoint.getTarget() ) && StringUtils.isValidObj( joinPoint.getSignature() ) )
    //            {
    //                LOGGER.info( "Entering:" + joinPoint.getTarget().getClass().getSimpleName() + "."
    //                        + joinPoint.getSignature().getName() );
    //            }
    //        }
    //    }
    //    @After("execution(public * *(..))")
    //    public void logAfter( JoinPoint joinPoint )
    //    {
    //        if ( StringUtils.isValidObj( joinPoint ) )
    //        {
    //            if ( StringUtils.isValidObj( joinPoint.getTarget() ) && StringUtils.isValidObj( joinPoint.getSignature() ) )
    //            {
    //                String className = joinPoint.getTarget().getClass().getSimpleName();
    //                if ( StringUtils.contains( className, "Action" ) )
    //                {
    //                    LOGGER.info( "Exiting:" + joinPoint.getTarget().getClass().getSimpleName() + "."
    //                            + joinPoint.getSignature().getName() );
    //                }
    //            }
    //        }
    //    }
    //    @After("@within(com.np.tele.crm.constants.AppMonitor)")
    //    public void logAfter( JoinPoint joinPoint )
    //    {
    //        if ( StringUtils.isValidObj( joinPoint ) )
    //        {
    //            if ( StringUtils.isValidObj( joinPoint.getTarget() ) && StringUtils.isValidObj( joinPoint.getSignature() ) )
    //            {
    //                LOGGER.info( "Exiting:" + joinPoint.getTarget().getClass().getSimpleName() + "."
    //                        + joinPoint.getSignature().getName() );
    //            }
    //        }
    //    }
}
