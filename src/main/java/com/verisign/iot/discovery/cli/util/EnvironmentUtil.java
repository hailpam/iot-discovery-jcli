
package com.verisign.iot.discovery.cli.util;

import com.verisign.iot.discovery.cli.common.EnvVariables;

/**
 * Utility Class. It defines a set of utility methods to aid in dealing with 
 * environment variables.
 * 
 * @author nbrasey <nbrasey@verisign.com>
 * @version 1.0
 * @since 4/30/15.
 */
public final class EnvironmentUtil 
{

	public static boolean isInsecureEnvironment () 
    {
		String insecureEnvVarString = EnvironmentUtil.getEnv( EnvVariables.INSECURE_SYSTEM_ENV );

		if ( insecureEnvVarString != null && !insecureEnvVarString.trim().isEmpty() ) {
			try {
				return Integer.parseInt( insecureEnvVarString ) == 1;
			}
			catch ( NumberFormatException e ) {
				// Just ignore the variable in that case
				return false;
			}
		}
        
		return false;
	}


	public static String getEnv ( String envVariableName ) 
    {
		return System.getenv( envVariableName );
	}


	private EnvironmentUtil () 
    {
		throw new AssertionError( String.format( "Class %s not instantiable", this.getClass().getName() ) );
	}
    
}
