package coreClasses;

import java.io.IOException;
import java.util.Properties;

public class FedExProperties extends Properties{

private static  FedExProperties props = null;

private  FedExProperties() throws IOException
{
    load(getClass().getResourceAsStream("/fedEx.properties"));
}

public static synchronized  FedExProperties getInstance() throws IOException
{
    if (props == null)
    {
        props = new  FedExProperties();
    }
    return props;
}
}