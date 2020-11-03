package com.np.tele.crm.report.dao;

import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.dto.ReportDto;
import com.np.tele.crm.ext.pojos.InaReportPojo;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.HibernateReportUtil;
import com.np.tele.crm.utils.StringUtils;

public class INAReportDaoImpl
    implements IINAReportDao
{
    private static final Logger LOGGER = Logger.getLogger( INAReportDaoImpl.class );

    @Override
    public ReportDto getINAReport( ReportDto inReportDto, String inNamedQuery )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        try
        {
            sessionFactory = HibernateReportUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( inNamedQuery );
            if ( StringUtils.isValidObj( inReportDto.getFromDate() )
                    && StringUtils.isValidObj( inReportDto.getToDate() ) )
            {
                String fromDateStr = inReportDto.getFromDate();
                String toDateStr = inReportDto.getToDate();
                fromDateStr += IAppConstants.START_TIME;
                toDateStr += IAppConstants.END_TIME;
                LOGGER.info( "from Date:::;" + fromDateStr );
                LOGGER.info( "To Date:::;" + toDateStr );
                Date fromDate = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.parse( fromDateStr );
                Date toDate = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.parse( toDateStr );
                query.setTimestamp( "FROM_DATE", fromDate );
                query.setTimestamp( "TO_DATE", toDate );
            }
            if ( StringUtils.isNotBlank( inReportDto.getProductType() ) )
            {
                query.setString( "SNAME1", inReportDto.getProductType() );
                query.setString( "SNAME2", inReportDto.getProductType() );
                query.setString( "SNAME3", inReportDto.getProductType() );
            }
            else
            {
                query.setString( "SNAME1", CRMDisplayListConstants.BROADBAND.getCode() );
                query.setString( "SNAME2", CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() );
                query.setString( "SNAME3", CRMDisplayListConstants.RADIO_FREQUENCY.getCode() );
            }
            if ( StringUtils.isNotBlank( inReportDto.getServiceType() ) )
            {
                query.setString( "STYPE1", inReportDto.getServiceType() );
                query.setString( "STYPE2", inReportDto.getServiceType() );
            }
            else
            {
                query.setString( "STYPE1", CRMDisplayListConstants.PRE_PAID.getCode() );
                query.setString( "STYPE2", CRMDisplayListConstants.POST_PAID.getCode() );
            }
            query.setResultTransformer( Transformers.aliasToBean( InaReportPojo.class ) );
            inReportDto.setInaReportPojos( query.list() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "unable to retrieve report data: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return inReportDto;
    }
}
