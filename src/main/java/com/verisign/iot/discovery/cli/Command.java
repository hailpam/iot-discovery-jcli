
package com.verisign.iot.discovery.cli;

import com.verisign.iot.discovery.cli.exception.ExecutionException;
import com.verisign.iot.discovery.cli.exception.OptionsNotValidException;
import joptsimple.OptionSet;

/**
 * Define a command to be executed. A command lifecycle spans over
 * the initialization (options are checked against the intended usage)
 * and execution (actual parameters are put together and the service is invoked).
 *
 * @author nbrasey <nbrasey@verisign.com>
 * @version 1.0
 * @since 4/30/15.
 */
public interface Command
{

	/**
	 * Initialize and validate the arguments.
	 *
	 * @param optionSet     A set of options
	 *
     * @throws ExecutionException
     *          In case runtime errors occur at init time
     * @throws OptionsNotValidException
     *          In case options are not valid
	 */
	void initialize ( OptionSet optionSet ) throws ExecutionException, OptionsNotValidException;


	/**
	 * Execute the command according to the given options.
	 *
	 * @throws ExecutionException
     *          In case of any execution problems
	 */
	void execute () throws ExecutionException;

}
