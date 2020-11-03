package com.np.tele.crm.utils;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXBConverter
{
    public static String pojo2Xml( Object object, JAXBContext context )
        throws JAXBException
    {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
        StringWriter writer = new StringWriter();
        marshaller.marshal( object, writer );
        String xmlStringData = writer.toString();
        return xmlStringData;
    }

    public static Object xml2Pojo( String xmlStringData, JAXBContext context )
        throws JAXBException
    {
        StringReader reader = new StringReader( xmlStringData );
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object unmarshallerObject = unmarshaller.unmarshal( reader );
        return unmarshallerObject;
    }
}
