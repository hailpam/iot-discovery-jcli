
package com.verisign.iot.discovery.cli.command;

import com.verisign.iot.discovery.cli.ConsoleWriter;
import com.verisign.iot.discovery.cli.exception.ExecutionException;
import com.verisign.iot.discovery.cli.exception.OptionsNotValidException;
import com.verisign.iot.discovery.cli.parser.Options;
import com.verisign.iot.discovery.exceptions.DnsServiceException;
import joptsimple.OptionSet;

/**
 * This class defines the Help/Usage command.
 *
 * @author nbrasey <nbrasey@verisign.com>
 * @version 1.0
 * @since 4/30/15.
 */
public class ShowHelpCommand extends DnsSdAbstractCommand
{

	@Override
	public void initialize ( OptionSet optionSet ) throws ExecutionException, OptionsNotValidException
    {
		super.initialize( optionSet );
	}


	@Override
	public void doExecute ( ConsoleWriter consoleWriter )
                    throws DnsServiceException
    {
		consoleWriter.log( Options.getUsage() );
	}

}
