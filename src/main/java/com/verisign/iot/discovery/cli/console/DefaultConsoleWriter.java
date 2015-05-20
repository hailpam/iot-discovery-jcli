
package com.verisign.iot.discovery.cli.console;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.verisign.iot.discovery.cli.ConsoleWriter;

/**
 * Created by nbrasey on 4/30/15.
 */
public class DefaultConsoleWriter implements ConsoleWriter {

	private boolean verbose;


	public DefaultConsoleWriter () {
	}


	public DefaultConsoleWriter ( boolean verbose ) {
		this.verbose = verbose;

		BasicConfigurator.configure();
		Logger.getLogger( "org.jitsi.dnssec" ).setLevel( Level.OFF );
	}


	@Override
	public void verbose ( String message ) {
		if ( verbose ) {
			log( message );
		}
	}


	@Override
	public void error ( String message ) {
		System.err.println( message );
	}


	@Override
	public void log ( String message ) {
		// TODO: Add also logging into separate log files as well as on the console
		System.out.println( message );
	}

}
