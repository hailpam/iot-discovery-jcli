
package com.verisign.iot.discovery.cli;

import com.verisign.iot.discovery.cli.exception.ParsingException;
import joptsimple.OptionSet;


/**
 * Define an options parser. This interface specifies the behaviour of a generic
 * options parser that has to take in input command line arguments and transform
 * them in a <code>OptionSet</code>.
 * 
 * @author nbrasey <nbrasey@verisign.com>
 * @version 1.0
 * @since 4/30/15.
 */
public interface CommandOptionParser 
{
    /**
     * Parses the command line options.
     * 
     * @param args  An array of options passed in input.
     * 
     * @return  The <code>OptionSet</code> built accordingly
     * 
     * @throws ParsingException 
     *          In case any problem occurs in parsing the passed options
     */
	OptionSet parse ( String[] args ) throws ParsingException;
}
