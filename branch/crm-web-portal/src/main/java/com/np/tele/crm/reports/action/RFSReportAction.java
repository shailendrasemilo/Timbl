package com.np.tele.crm.reports.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.ReportEnum;
import com.np.tele.crm.gis.bm.GISUtils;
import com.np.tele.crm.reports.bm.IReportBMngr;
import com.np.tele.crm.reports.form.CRMReportForm;
import com.np.tele.crm.service.client.AreaPojo;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.ReportDto;
import com.np.tele.crm.service.client.RfsReportPojo;
import com.np.tele.crm.service.client.SocietyPojo;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class RFSReportAction
    extends DispatchAction
{
    private static final Logger LOGGER        = Logger.getLogger( RFSReportAction.class );
    private IReportBMngr        reportManager = null;

    public IReportBMngr getReportManager()
    {
        return reportManager;
    }

    public void setReportManager( IReportBMngr reportManager )
    {
        this.reportManager = reportManager;
    }

    public ActionForward rfsReportPage( final ActionMapping inMapping,
                                        final ActionForm inForm,
                                        final HttpServletRequest inRequest,
                                        final HttpServletResponse inResponse )
    {
        CRMReportForm reportForm = (CRMReportForm) inForm;
        reportForm.setStatePojoList( GISUtils.getAllStates( IAppConstants.COUNTRY_INDIA ) );
        return inMapping.findForward( IActionForwardConstant.RFS_REPORT );
    }

    public ActionForward rfsReport( final ActionMapping inMapping,
                                    final ActionForm inForm,
                                    final HttpServletRequest inRequest,
                                    final HttpServletResponse inResponse )
    {
        LOGGER.info( inRequest.getContentLength() );
        List<CityPojo> cityPojos = new ArrayList<CityPojo>();
        List<AreaPojo> areaPojos = new ArrayList<AreaPojo>();
        List<SocietyPojo> societyPojos = new ArrayList<SocietyPojo>();
        CRMReportForm inReportForm = (CRMReportForm) inForm;
        ReportDto reportDto = getReportManager().getRFSReport( inReportForm, ReportEnum.RFS_REPORT );
        if ( CommonValidator.isValidCollection( reportDto.getRfsReportPojos() ) )
        {
            LOGGER.info( "rfsReport Size::" + reportDto.getRfsReportPojos().size() );
            SortingComparator<RfsReportPojo> sorter = new SortingComparator<RfsReportPojo>( "state" );
            Collections.sort( reportDto.getRfsReportPojos(), sorter );
            inReportForm.setRfsPojoList( reportDto.getRfsReportPojos() );
        }
        else
        {
            LOGGER.info( "Else Block::" );
        }
        inReportForm.setStatePojoList( GISUtils.getAllStates( IAppConstants.COUNTRY_INDIA ) );
        if ( StringUtils.isNotBlank( inReportForm.getState() ) && !StringUtils.equals( inReportForm.getState(), "0" ) )
        {
            cityPojos = GISUtils
                    .getAllCities( IAppConstants.COUNTRY_INDIA, Integer.parseInt( inReportForm.getState() ) );
        }
        if ( StringUtils.isNotBlank( inReportForm.getCity() ) && !StringUtils.equals( inReportForm.getCity(), "0" ) )
        {
            areaPojos = GISUtils.getAllAreas( IAppConstants.COUNTRY_INDIA, null,
                                              Integer.parseInt( inReportForm.getCity() ) );
        }
        if ( StringUtils.isNotBlank( inReportForm.getArea() ) && !StringUtils.equals( inReportForm.getArea(), "0" ) )
        {
            societyPojos = GISUtils.getAllSocietybyArea( Integer.parseInt( inReportForm.getArea() ) );
            if ( CommonValidator.isValidCollection( societyPojos ) )
            {
                for ( SocietyPojo societyPojo : societyPojos )
                {
                      if ( StringUtils.isNotBlank( societyPojo.getSocietyName() ) )
                        {
                                societyPojo.setLocalityName( societyPojo.getLocalityName().concat( IAppConstants.DASH )
                                .concat( societyPojo.getSocietyName() ) );
                    }
                    else
                    {
                        societyPojo.setLocalityName( societyPojo.getLocalityName() );
                    }
                }
            }
        }
        inReportForm.setCityPojoList( cityPojos );
        inReportForm.setAreaPojoList( areaPojos );
        inReportForm.setSocietyPojoList( societyPojos );
        return inMapping.findForward( IActionForwardConstant.RFS_REPORT );
    }
}
