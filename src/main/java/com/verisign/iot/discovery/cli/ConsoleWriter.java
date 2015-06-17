
package com.verisign.iot.discovery.cli;

/**
 * Define an console writer. This interface specifies the behaviour of a generic 
 * console writers that has is responsible for logging out information, on the most
 * appropriated standard of output.
 * 
 * @author nbrasey <nbrasey@verisign.com>
 * @version 1.0
 * @since 4/30/15.
 */
public interface ConsoleWriter 
{
    
    /**
     * Logs out verbose information.
     * 
     * @param message   A <code>String</code> containing the message to be logged out
     */
	void verbose ( String message );

    /**
     * Logs out error information on the standard error.
     * 
     * @param message   A <code>String</code> containing the message to be logged out
     */
	void error ( String message );

    /**
     * Logs out information.
     * 
     * @param message   A <code>String</code> containing the message to be logged out
     */
	void log ( String message );

}
