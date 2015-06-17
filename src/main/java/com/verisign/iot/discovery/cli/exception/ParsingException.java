
package com.verisign.iot.discovery.cli.exception;

/**
 * Custom Exception. It is raised any time a parsing error occurs.
 * 
 * @author nbrasey <nbrasey@verisign.com>
 * @version 1.0
 * @since 4/30/15.
 */
public class ParsingException extends Exception 
{

	private static final long serialVersionUID = 1938828805076909222L;


	public ParsingException () 
    {
	}


	public ParsingException ( String message ) 
    {
		super( message );
	}


	public ParsingException ( String message, Throwable cause ) 
    {
		super( message, cause );
	}


	public ParsingException ( Throwable cause ) 
    {
		super( cause );
	}


	public ParsingException ( String message, Throwable cause, boolean enableSuppression, 
                              boolean writableStackTrace ) 
    {
		super( message, cause, enableSuppression, writableStackTrace );
	}
    
}
