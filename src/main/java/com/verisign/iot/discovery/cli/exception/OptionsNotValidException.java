
package com.verisign.iot.discovery.cli.exception;

/**
 * Custom Exception. It is raised any time passed options are not valid.
 * 
 * @author nbrasey <nbrasey@verisign.com>
 * @version 1.0
 * @since 4/30/15.
 */
public class OptionsNotValidException extends Exception 
{

	private static final long serialVersionUID = 6227833313445446744L;


	public OptionsNotValidException ( String message ) 
    {
		super( message );
	}
    
}
