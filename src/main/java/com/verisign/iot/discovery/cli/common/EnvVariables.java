
package com.verisign.iot.discovery.cli.common;

/**
 * Utility class. I encapsulates the any interactions with the running Environement.
 * 
 * @author nbrasey <nbrasey@verisign.com>
 * @version 1.0
 * @since 4/30/15.
 */
public final class EnvVariables 
{
    /** Environment variable that allows to deactivate the DNSSEC check */
	public static final String INSECURE_SYSTEM_ENV = "INSECURE";

	private EnvVariables () 
    {
		throw new AssertionError( String.format( "Class %s not instantiable", this.getClass().getName() ) );
	}

}
