package com.np.tele.selfcare.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.PdfUtils;
import com.np.tele.crm.utils.StringUtils;
import com.np.tele.selfcare.forms.SelfcareQuickPayForm;

public class SelfcarePaymentPDFAction
    extends Action
{
    private final Logger LOGGER = Logger.getLogger( SelfcarePaymentPDFAction.class );

    @Override
    public ActionForward execute( ActionMapping inMapping,
                                  ActionForm inForm,
                                  HttpServletRequest inRequest,
                                  HttpServletResponse inResponse )
        throws Exception
    {
        SelfcareQuickPayForm quickPayForm = (SelfcareQuickPayForm) inForm;
        if ( StringUtils.isValidObj( quickPayForm ) && StringUtils.isValidObj( quickPayForm.getPaymentGatewayPojo() ) )
        {
            LOGGER.info( "SelfcarePaymentPDFAction :::" );
            String[] lables = new String[]
            { "Receipt No.", "Customer Name", "Customer ID", "Payment Date and Time", "Amount (INR)", "Payment Status" };
            String[] lableValue = new String[]
            {
                    quickPayForm.getPaymentGatewayPojo().getPgwTransactionId(),
                    quickPayForm.getCustomerDetailsPojo().getCustFname() + " "
                            + StringUtils.trimToEmpty( quickPayForm.getCustomerDetailsPojo().getCustLname() ),
                    quickPayForm.getCustomerDetailsPojo().getCustomerId(),
                    DateUtils.convertXmlCalToString( quickPayForm.getPaymentGatewayPojo().getPgwResponseDatetime(),
                                                     IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS ),
                    quickPayForm.getPaymentGatewayPojo().getAmount() + "",
                    CRMStatusCode.getStatus( quickPayForm.getPaymentGatewayPojo().getStatus() ) };
            String client = getServlet().getServletContext().getInitParameter( "client" );
            String iconPath = getServlet().getServletContext()
                    .getRealPath( String.format( IAppConstants.PDF_ICON_PATH, client ) );
            // setting content type PDF
            String fileName = IAppConstants.INTLINE_FILE_NAME;
            StringUtils.replace( fileName, "test", "PaymentReceipt_"
                    + quickPayForm.getCustomerDetailsPojo().getCustomerId() + IAppConstants.UNDERSCORE
                    + quickPayForm.getPaymentGatewayPojo().getPgwTransactionId() );
            inResponse.setContentType( IAppConstants.PDF_CONTENT_TYPE );
            inResponse.setHeader( IAppConstants.CONTENT_DISPOSITION, fileName );
            PdfUtils.generatePaymentReciept( lables, lableValue, iconPath, inResponse.getOutputStream() );
        }
        return null;
    }
    // PDF header
    //            Document document = new Document( PageSize.A4 );
    //            document.open();
    //            Image image1 = Image.getInstance( iconPath );
    //            image1.setAbsolutePosition( 470, 767 );
    //            image1.scaleToFit( 90, 100 );
    //            document.add( image1 );
    //            Font fontBold = FontFactory.getFont( IAppConstants.PDF_FONT, 12, Font.BOLD, new BaseColor( 50, 80, 150 ) );
    //            Font fontHeading = FontFactory
    //                    .getFont( IAppConstants.PDF_FONT, 12, Font.BOLD, new BaseColor( 50, 80, 150 ) );
    //            Font fontSimple = FontFactory.getFont( IAppConstants.PDF_FONT, 8, Font.NORMAL );
    //            Paragraph paragraph = new Paragraph( "Nextra Teleservices Pvt. Ltd.", fontBold );
    //            paragraph.setAlignment( Element.ALIGN_CENTER );
    //            document.add( new Phrase( IAppConstants.NEW_LINE ) );
    //            document.add( paragraph );
    //            Paragraph paragraph2 = new Paragraph( "Plot No. 17-18, Udyog Vihar Phase-I, Gurgaon-122016, India.\n\n",
    //                                                  fontSimple );
    //            paragraph2.setAlignment( Element.ALIGN_CENTER );
    //            document.add( paragraph2 );
    //            // line seperator
    //            LineSeparator line = new LineSeparator( 1, 100, null, Element.ALIGN_CENTER, 10 );
    //            Paragraph lineParagraph = new Paragraph();
    //            lineParagraph.add( line );
    //            document.add( lineParagraph );
    //            document.add( new Paragraph( IAppConstants.NEW_LINE ) );
    //            // Payment Receipt
    //            Chunk chunk = new Chunk( "Payment Receipt" );
    //            chunk.setUnderline( 0.1f, -2 );
    //            chunk.setFont( fontHeading );
    //            paragraph = new Paragraph( chunk );
    //            paragraph.setAlignment( Element.ALIGN_CENTER );
    //            document.add( paragraph );
    //            document.add( new Paragraph( IAppConstants.NEW_LINE ) );
    //            document.add( new Paragraph( IAppConstants.NEW_LINE ) );
    //            // Table
    //            PdfPTable pdfTable = createTable( lables, lableValue );
    //            document.add( pdfTable );
    //            Paragraph paragraph3 = new Paragraph( "------------Thank for using Nextra on-line payment services------------",
    //                                                  fontSimple );
    //            paragraph3.setAlignment( Element.ALIGN_CENTER );
    //            document.add( paragraph3 );
    //            document.close();
}
