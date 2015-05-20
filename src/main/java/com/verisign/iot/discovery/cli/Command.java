
package com.verisign.iot.discovery.cli;

import com.verisign.iot.discovery.cli.exception.ExecutionException;
import com.verisign.iot.discovery.cli.exception.OptionsNotValidException;
import joptsimple.OptionSet;

/**
 * Created by nbrasey on 4/30/15.
 */
public interface Command {

	/**
	 * Initialize and validate the arguments
	 * 
	 * @param optionSet
	 *        the parsed Jopt arguments
	 * @throws OptionsNotValidException
	 */
	void initialize ( OptionSet optionSet ) throws OptionsNotValidException;


	/**
	 * Execute the command
	 * 
	 * @throws ExecutionException
	 */
	void execute () throws ExecutionException;

}
