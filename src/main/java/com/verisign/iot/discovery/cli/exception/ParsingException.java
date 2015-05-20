
package com.verisign.iot.discovery.cli.exception;

/**
 * Created by nbrasey on 4/30/15.
 */
public class ParsingException extends Exception {

	private static final long serialVersionUID = 1938828805076909222L;


	public ParsingException () {
	}


	public ParsingException ( String message ) {
		super( message );
	}


	public ParsingException ( String message, Throwable cause ) {
		super( message, cause );
	}


	public ParsingException ( Throwable cause ) {
		super( cause );
	}


	public ParsingException ( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
		super( message, cause, enableSuppression, writableStackTrace );
	}
}
