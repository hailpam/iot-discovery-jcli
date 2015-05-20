
package com.verisign.iot.discovery.cli;

import com.verisign.iot.discovery.cli.exception.ParsingException;
import joptsimple.OptionSet;

/**
 * Created by nbrasey on 4/30/15.
 */
public interface CommandOptionParser {

	OptionSet parse ( String[] args ) throws ParsingException;

}
