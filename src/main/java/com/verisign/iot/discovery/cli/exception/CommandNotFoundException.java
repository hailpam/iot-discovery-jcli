
package com.verisign.iot.discovery.cli.exception;

/**
 * Custom Exception. It is used any time a specific instance of <code>Command</code> 
 * cannot be found.
 * 
 * @author nbrasey <nbrasey@verisign.com>
 * @version 1.0
 * @since 4/30/15.
 */
public class CommandNotFoundException extends Exception 
{

	private static final long serialVersionUID = 7036623471905877757L;


	public CommandNotFoundException () {}


	public CommandNotFoundException ( String message ) 
    {
		super( message );
	}


	public CommandNotFoundException ( String message, Throwable cause ) 
    {
		super( message, cause );
	}


	public CommandNotFoundException ( Throwable cause ) 
    {
		super( cause );
	}


	public CommandNotFoundException ( String message, Throwable cause, boolean enableSuppression, 
                                      boolean writableStackTrace ) 
    {
		super( message, cause, enableSuppression, writableStackTrace );
	}
}
