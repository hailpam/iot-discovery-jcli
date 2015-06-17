
package com.verisign.iot.discovery.cli.exception;

/**
 * Custom Exception. It is used any time a error occurs at runtime.
 * 
 * @author nbrasey <nbrasey@verisign.com>
 * @version 1.0
 * @since 4/30/15.
 */
public class ExecutionException extends Exception 
{

	private static final long serialVersionUID = 4455353859446551777L;
	private int exitCode;


	public ExecutionException ( String message, int exitCode ) 
    {
		super( message );
		this.exitCode = exitCode;
	}


	public int getExitCode () 
    {
		return this.exitCode;
	}
    
}
