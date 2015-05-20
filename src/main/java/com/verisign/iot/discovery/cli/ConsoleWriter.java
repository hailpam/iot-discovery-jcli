
package com.verisign.iot.discovery.cli;

/**
 * Created by nbrasey on 5/4/15.
 */
public interface ConsoleWriter {
	void verbose ( String message );


	void error ( String message );


	void log ( String message );
}
