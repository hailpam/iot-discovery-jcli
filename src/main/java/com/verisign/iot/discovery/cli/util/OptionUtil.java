
package com.verisign.iot.discovery.cli.util;

import joptsimple.OptionSet;

import com.verisign.iot.discovery.cli.exception.OptionsNotValidException;

/**
 * @author nbrasey
 * @version 1.0 May 05, 2015
 */
public final class OptionUtil {

	public static String getOptionValue ( OptionSet optionSet, String option, boolean mandatory )
			throws OptionsNotValidException {
		boolean hasOption = optionSet.has( option );
		if ( !hasOption && mandatory ) {
			throw new OptionsNotValidException( String.format( "A mandatory option \"%s\" is missing", option ) );
		}else{
			String value = null;
			try{
				value = optionSet.valueOf( option ).toString();
			} catch (NullPointerException npe){

			}

			if(hasOption && mandatory){
				if ( value == null || value.trim().isEmpty() ) {
					throw new OptionsNotValidException( String.format( "Null or empty value for the option \"%s\"", option ) );
				}
			}
			return value;
		}
	}


	private OptionUtil () {
		throw new AssertionError( String.format( "Class %s not instantiable", this.getClass().getName() ) );
	}
}
