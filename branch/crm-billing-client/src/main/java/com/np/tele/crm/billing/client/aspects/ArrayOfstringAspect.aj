package com.np.tele.crm.billing.client.aspects;

import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfstring;

public aspect ArrayOfstringAspect
{
    public String ArrayOfstring.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "ArrayOfstring [string=" ).append( this.getString().toString() ).append( "]" );
        return builder.toString();
    }
}
