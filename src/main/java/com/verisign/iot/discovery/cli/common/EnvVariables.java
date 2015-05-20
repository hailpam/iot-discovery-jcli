
package com.verisign.iot.discovery.cli.common;

/**
 * @author nbrasey
 * @version 1.0 May 05, 2015
 */
public final class EnvVariables {

	public static final String INSECURE_SYSTEM_ENV = "INSECURE";


	private EnvVariables () {
		throw new AssertionError( String.format( "Class %s not instantiable", this.getClass().getName() ) );
	}
}
