
package com.verisign.iot.discovery.cli.console;

import com.verisign.iot.discovery.cli.ConsoleWriter;
import java.util.Observable;
import java.util.Observer;

/**
 * This class an <code>Observer</code> of the Discovery Core Library 
 * (main implementation is <code>DnsServicesDiscovery</code>). It logs out
 * the information that the library notifies asynchrnously.
 * 
 * @author nbrasey <nbrasey@verisign.com>
 * @version 1.0
 * @since 4/30/15.
 * 
 * @see <a href="https://github.com/verisign/iot-discovery-services">IoT Services Discovery</a>
 */
public class LibraryObserver implements Observer 
{

	private ConsoleWriter consoleWriter;
    
    
	public LibraryObserver ( ConsoleWriter consoleWriter ) 
    {
		this.consoleWriter = consoleWriter;
	}


	@Override
	public void update ( Observable o, Object object ) 
    {
		consoleWriter.verbose( object.toString() );
	}
    
}
