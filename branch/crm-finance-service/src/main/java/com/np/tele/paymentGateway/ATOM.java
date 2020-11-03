package com.np.tele.paymentGateway;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.ext.pojos.AtomPgwPojo;
import com.np.tele.crm.pojos.CrmPgwTransactionsPojo;
import com.np.tele.crm.utils.EncryptionUtil;
import com.np.tele.crm.utils.StringUtils;

public class ATOM
{
    private static final Logger LOGGER = Logger.getLogger( ATOM.class );

    public CRMServiceCode getRedirectURL( CrmPgwTransactionsPojo inCrmPgwTransactionsPojo, AtomPgwPojo inAtomPgwPojo )
    {
        LOGGER.info( "in getRedirectURL" );
        CRMServiceCode serviceCode = CRMServiceCode.CRM901;
        String xmlURL = "";
        String xmlttype = "";
        String xmltempTxnId = "";
        String xmltoken = "";
        String xmltxnStage = "";
        StringBuilder request = new StringBuilder();
        request.append( inAtomPgwPojo.getPaymentUrl() )
                .append( "?login=" + inAtomPgwPojo.getLoginId() )
                .append( "&pass=" + inAtomPgwPojo.getPassword() )
                .append( "&ttype=" + inAtomPgwPojo.getTransType() )
                .append( "&prodid=" + inAtomPgwPojo.getProdId() )
                .append( "&txncurr=" + inAtomPgwPojo.getTxnCurr() )
                .append( "&ru=" + inAtomPgwPojo.getResponseUrl() )
                .append( "&txnscamt=" + inAtomPgwPojo.getTxnScAmt() )
                .append( "&amt=" + inCrmPgwTransactionsPojo.getAmount() )
                .append( "&clientcode=" + EncryptionUtil.base64encode( inCrmPgwTransactionsPojo.getCustomerId(), true ) )
                .append( "&txnid=" + inCrmPgwTransactionsPojo.getPgwTrackId() )
                .append( "&date="
                                 + new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" ).format( Calendar.getInstance()
                                         .getTime() ) )
                .append( "&custacc=" + StringUtils.leftPad( inCrmPgwTransactionsPojo.getCustomerId(), 10, '0' ) )
                .append( "&udf1=" + inCrmPgwTransactionsPojo.getUdf1() )
                .append( "&udf2=" + inCrmPgwTransactionsPojo.getUdf2() )
                .append( "&udf3=" + inCrmPgwTransactionsPojo.getUdf3() )
                .append( "&udf4=" + inCrmPgwTransactionsPojo.getUdf4() )
        //                .append( "&mdd="
        //                                 + ( StringUtils.equals( inCrmPgwTransactionsPojo.getPaymentOption(), "IB" )
        //                                                                                                            ? "NB"
        //                                                                                                            : inCrmPgwTransactionsPojo
        //                                                                                                                    .getPaymentOption() ) )
        ;
        LOGGER.info( "first request :::: " + request.toString() );
        try
        {
            URL url = new URL( StringUtils.replace( request.toString(), " ", "%20" ) );
            URLConnection conn = url.openConnection( Proxy.NO_PROXY );
            if ( conn.getContentLength() != 0 )
            {
                BufferedReader inBuf = new BufferedReader( new InputStreamReader( conn.getInputStream() ) );
                String inputLine;
                String vXMLStr = "";
                while ( ( inputLine = inBuf.readLine() ) != null )
                {
                    vXMLStr += inputLine;
                }
                inBuf.close();
                LOGGER.info( "received content :: " + vXMLStr );
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                InputSource isBuf = new InputSource();
                isBuf.setCharacterStream( new StringReader( vXMLStr ) );
                Document doc = dBuilder.parse( isBuf );
                doc.getDocumentElement().normalize();
                LOGGER.info( "Root element :" + doc.getDocumentElement().getNodeName() );
                NodeList nList = doc.getElementsByTagName( "RESPONSE" );
                for ( int tempN = 0; tempN < nList.getLength(); tempN++ )
                {
                    Node nNode = nList.item( tempN );
                    if ( nNode.getNodeType() == Node.ELEMENT_NODE )
                    {
                        Element eElement = (Element) nNode;
                        /* System.out.println( "URL : "
                                 + eElement.getElementsByTagName( "url" ).item( 0 ).getChildNodes().item( 0 )
                                         .getNodeValue() );*/
                        xmlURL = eElement.getElementsByTagName( "url" ).item( 0 ).getChildNodes().item( 0 )
                                .getNodeValue();
                        NodeList aList = eElement.getElementsByTagName( "param" );
                        String vParamName;
                        for ( int atrCnt = 0; atrCnt < aList.getLength(); atrCnt++ )
                        {
                            vParamName = aList.item( atrCnt ).getAttributes().getNamedItem( "name" ).getNodeValue();
                            //System.out.println( "paramName : : " + vParamName );
                            if ( vParamName.equals( "ttype" ) )
                            {
                                xmlttype = aList.item( atrCnt ).getChildNodes().item( 0 ).getNodeValue();
                            }
                            else if ( vParamName.equals( "tempTxnId" ) )
                            {
                                xmltempTxnId = aList.item( atrCnt ).getChildNodes().item( 0 ).getNodeValue();
                            }
                            else if ( vParamName.equals( "token" ) )
                            {
                                xmltoken = aList.item( atrCnt ).getChildNodes().item( 0 ).getNodeValue();
                            }
                            else if ( vParamName.equals( "txnStage" ) )
                            {
                                xmltxnStage = aList.item( atrCnt ).getChildNodes().item( 0 ).getNodeValue();
                            }
                        }
                        LOGGER.info( "URL :: " + xmlURL );
                        LOGGER.info( "param : ttype :: " + xmlttype );
                        LOGGER.info( "param : tempTxnId :: " + xmltempTxnId );
                        LOGGER.info( "param : token :: " + xmltoken );
                        LOGGER.info( "param : txnStage :: " + xmltxnStage );
                    }
                }
                if ( StringUtils.checkAllvalidObj( new String[]
                { xmlURL, xmlttype, xmltempTxnId, xmltoken, xmltxnStage }, false ) )
                {
                    String Atom2Request = xmlURL + "?ttype=" + xmlttype + "&tempTxnId=" + xmltempTxnId + "&token="
                            + xmltoken + "&txnStage=" + xmltxnStage;
                    Atom2Request = Atom2Request.replace( " ", "%20" );
                    LOGGER.info( "ATOM 2nd Request : " + Atom2Request );
                    inCrmPgwTransactionsPojo.setPgwPaymentId( xmltempTxnId );
                    inCrmPgwTransactionsPojo.setPgwPaymentPage( Atom2Request );
                    inCrmPgwTransactionsPojo.setRedirectUrl( Atom2Request );
                    serviceCode = CRMServiceCode.CRM001;
                }
                else
                {
                    inCrmPgwTransactionsPojo.setPgwErrorCode( serviceCode.getStatusCode() );
                    inCrmPgwTransactionsPojo.setPgwErrorMsg( "Invalid response. Required details not present." );
                    inCrmPgwTransactionsPojo.setStatus( CRMStatusCode.FAILURE.getStatusCode() );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "error while getting redirect url for ATOM", ex );
            inCrmPgwTransactionsPojo.setPgwErrorCode( serviceCode.getStatusCode() );
            inCrmPgwTransactionsPojo.setPgwErrorMsg( ex.getMessage() );
            inCrmPgwTransactionsPojo.setStatus( CRMStatusCode.FAILURE.getStatusCode() );
        }
        return serviceCode;
    }
}
