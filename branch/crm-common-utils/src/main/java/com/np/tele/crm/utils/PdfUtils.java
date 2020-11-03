package com.np.tele.crm.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.np.tele.crm.constants.IAppConstants;

public class PdfUtils
{
    public static void generatePaymentReciept( String[] lables,
                                               String[] lableValue,
                                               String iconPath,
                                               OutputStream inOutStream )
        throws FileNotFoundException, DocumentException, BadElementException, MalformedURLException, IOException
    {
        Document document = new Document( PageSize.A4 );
        PdfWriter pdfWriter = PdfWriter.getInstance( document, inOutStream );
        Image clientLogo = Image.getInstance( iconPath );
        document.open();
        document.add( new Paragraph( IAppConstants.NEW_LINE ) );
        document.add( new Paragraph( IAppConstants.NEW_LINE ) );
        clientLogo.setAbsolutePosition( 415, 700 );
        clientLogo.scaleToFit( 90, 100 );
        // Create PDF Table
        createTable( lables, lableValue, document, clientLogo );
        document.close();
        pdfWriter.flush();
        pdfWriter.close();
    }

    private static void createTable( String[] inLables, String[] inValues, Document document, Image clientLogo )
    {
        PdfPTable table = null;
        PdfPCell labelCell = null;
        PdfPCell valueCell = null;
        PdfPCell cell = new PdfPCell();
        cell.setBorder( Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT );
        cell.setPaddingLeft( 15 );
        cell.setPaddingRight( 15 );
        cell.setPaddingBottom( 10 );
        PdfPCell paraCell = new PdfPCell();
        paraCell.setBorder( Rectangle.TOP | Rectangle.LEFT | Rectangle.RIGHT );
        PdfPCell tableCell = new PdfPCell();
        tableCell.setBorder( Rectangle.LEFT | Rectangle.RIGHT );
        StringBuilder sb = new StringBuilder();
        StringBuilder vsb = new StringBuilder();
        Paragraph paragraph = null;
        try
        {
            Font fontBold = FontFactory.getFont( "Arial", 8, Font.BOLD );
            Font fontSimple = FontFactory.getFont( "Arial", 8, Font.NORMAL );
            PdfPTable outer = new PdfPTable( 1 );
            outer.setWidthPercentage( 85 );
            table = new PdfPTable( 2 );
            table.setWidthPercentage( 95 );
            // Payment Receipt
            paragraph = new Paragraph( "Online Payment Receipt", FontFactory.getFont( "Arial", 10, Font.BOLD ) );
            paragraph.setAlignment( Element.ALIGN_CENTER );
            paraCell.setPaddingTop( 30 );
            paraCell.setPaddingBottom( 10 );
            paraCell.addElement( paragraph );
            for ( int i = 0; i < inLables.length; i++ )
            {
                sb.append( "  " ).append( inLables[i] ).append( IAppConstants.NEW_LINE )
                        .append( IAppConstants.NEW_LINE );
                vsb.append( "  " ).append( StringUtils.trimToEmpty( inValues[i] ) ).append( IAppConstants.NEW_LINE )
                        .append( IAppConstants.NEW_LINE );
            }
            paragraph = new Paragraph( new Chunk( sb.toString(), fontSimple ) );
            labelCell = new PdfPCell( paragraph );
            labelCell.setPaddingTop( 8 );
            labelCell.setPaddingBottom( 8 );
            paragraph = new Paragraph( new Chunk( vsb.toString(), fontSimple ) );
            valueCell = new PdfPCell( paragraph );
            valueCell.setPaddingTop( 8 );
            valueCell.setPaddingBottom( 8 );
            table.addCell( labelCell );
            table.addCell( valueCell );
            outer.addCell( paraCell );
            tableCell.addElement( table );
            outer.addCell( tableCell );
            document.add( new Paragraph( IAppConstants.NEW_LINE ) );
            Paragraph paragraph3 = new Paragraph( "------------------------------------------ Thank you for using Timbl Online Payment Services ------------------------------------------",
                                                  fontSimple );
            paragraph3.setAlignment( Element.ALIGN_CENTER );
            cell.addElement( paragraph3 );
            cell.addElement( new Phrase( IAppConstants.NEW_LINE ) );
            Paragraph paragraph4 = new Paragraph( "All payment made are subject to realization.", fontSimple );
            paragraph4.setAlignment( Element.ALIGN_LEFT );
            cell.addElement( paragraph4 );
            cell.addElement( new Paragraph( IAppConstants.NEW_LINE ) );
            Paragraph paragraph5 = new Paragraph( "This is a system generated document and does not require a signature.",
                                                  fontBold );
            paragraph5.setAlignment( Element.ALIGN_LEFT );
            cell.addElement( paragraph5 );
            LineSeparator line1 = new LineSeparator( 1, 100, null, Element.ALIGN_CENTER, 7 );
            Paragraph lineParagraph1 = new Paragraph();
            lineParagraph1.add( line1 );
            cell.addElement( lineParagraph1 );
            Phrase phrase = new Phrase();
            phrase.add( new Chunk( "RI Network Pvt. Ltd., ", fontBold ) );
            phrase.add( new Chunk( "758 First Floor, Udyog Vihar V, Gurgaon - 122016, Haryana | Ph: 92121-99966",
                                   fontSimple ) );
            Paragraph paragraph6 = new Paragraph( phrase );
            paragraph6.setAlignment( Element.ALIGN_LEFT );
            cell.addElement( paragraph6 );
            outer.addCell( cell );
            document.add( clientLogo );
            document.add( outer );
        }
        catch ( DocumentException ex )
        {
            ex.printStackTrace();
        }
    }
    /*public static void main( String[] args )
    {
        File pdf = new File( "PaymentReceipt.pdf" );
        try
        {
            String[] lables = new String[]
            { "Receipt No.", "Customer Name", "Customer ID", "Transaction Refernce Number", "Payment Date and Time",
                    "Amount (INR)" };
            String[] lableValue = new String[]
            { "2000182T01", "Anand pandey", "2000182", "13131T1321321321",
                    IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.format( new Date() ), 123.01 + "" };
            String iconPath = "http://timbl.co.in/images/TIMBL_Logo_W-168px_X_H-67_px.png";//getServlet().getServletContext().getRealPath( IAppConstants.NEXTRA_ICON_PATH );
            FileOutputStream pdfFos = new FileOutputStream( pdf );
            PdfUtils.generatePaymentReciept( lables, lableValue, iconPath, pdfFos );
            pdfFos.flush();
            pdfFos.close();
        }
        catch ( Exception ex )
        {
            System.err.println( ex );
        }
    }*/
}
