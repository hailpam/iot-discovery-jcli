package com.verisign.iot.discovery.cli.util;

import com.verisign.iot.discovery.commons.StatusCode;

/**
 * Utility Class. It defines constant message and utility functions to display any
 * error message on the default output streams.
 *
 * @author pmaresca <pmaresca@verisign.com>
 * @version 1.0
 * @since 6/16/15.
 */
public class DisplayUtil
{

    public static final String UNEXPECTED_ERROR = "Unexpected Error";
    public static final String INVALID_ARGUMENT = "Invalid Argument Usage";
    public static final String INVALID_DNS_HOST = "Invalid DNS Server Host: [%s]";
    public static final String INTERNAL_ERROR = "Inernal Lib Error";
    public static final String UNREACHABLE_DNS = "Unreachable DNS Server or Timeout Expired";
    public static final String LOOKUP_ERROR = "Generic Lookup Error: look at the Errors Trace";
    public static final String INSECURE_DNS_RESPONSE = "DNSSEC Status Check failed for domain";
    public static final String UNEXPECTED_RR_TYPE = "Unexpected Resource Record Type";
    public static final String DNS_FAILURE = "DNS Server Failure";
    public static final String DNS_NAME_NOT_EXIST = "DNS Name does not exist: [%s]";
    public static final String RR_TYPE_NOT_EXIST = "RR Type does not exist";
    public static final String INVALID_FQDN = "Invalid Fully Qualified Domain Name";

    public static final String SECURE_DNS_RESPONSE = "DNSSEC Status Check for domain successful";


    public static String map(StatusCode status)
    {
        if(status == StatusCode.SERVER_ERROR) return DNS_FAILURE;
        else if(status == StatusCode.RESOLUTION_NAME_ERROR) return DNS_NAME_NOT_EXIST;
        else if(status == StatusCode.RESOLUTION_RR_TYPE_ERROR) return RR_TYPE_NOT_EXIST;
        else if(status == StatusCode.RESOURCE_UNEXPECTED) return UNEXPECTED_RR_TYPE;
        else if(status == StatusCode.SERVER_ERROR || status == StatusCode.RESOURCE_INSECURE_ERROR)
            return INSECURE_DNS_RESPONSE;
        else if(status == StatusCode.RESOURCE_LOOKUP_ERROR) return LOOKUP_ERROR;
        else if(status == StatusCode.NETWORK_ERROR) return UNREACHABLE_DNS;
        else if(status == StatusCode.ILLEGAL_FQDN) return INVALID_FQDN;

        return String.format("Unrecognized status: [%s]", status.statusLabel());
    }

    private DisplayUtil()
    {
        throw new AssertionError(String.format("Class %s not instantiable",
                this.getClass().getName()));
    }

}
