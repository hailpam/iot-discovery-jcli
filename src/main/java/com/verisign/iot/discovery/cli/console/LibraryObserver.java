
package com.verisign.iot.discovery.cli.console;

import com.verisign.iot.discovery.cli.ConsoleWriter;

import java.util.Observable;
import java.util.Observer;

/**
 * @author nbrasey
 * @version 1.0 May 06, 2015
 */
public class LibraryObserver implements Observer {

	private ConsoleWriter consoleWriter;


	public LibraryObserver ( ConsoleWriter consoleWriter ) {
		this.consoleWriter = consoleWriter;
	}


	@Override
	public void update ( Observable o, Object object ) {

		consoleWriter.verbose( object.toString() );
	}
}
