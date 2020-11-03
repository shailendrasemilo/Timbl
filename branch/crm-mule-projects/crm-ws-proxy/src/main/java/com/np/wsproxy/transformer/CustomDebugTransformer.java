package com.np.wsproxy.transformer;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

public class CustomDebugTransformer extends AbstractMessageTransformer{
	int seq = 0;
    /**
     * @param args
     */
    public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
    	String tname = Thread.currentThread().getName() ;
    	System.out.println(++seq + " - " + tname + " - " + message.getUniqueId());
    	if(tname.indexOf("dispatch") > -1){
    		System.out.println(message.getPayloadForLogging());
    	}
        return message ;
    }
}