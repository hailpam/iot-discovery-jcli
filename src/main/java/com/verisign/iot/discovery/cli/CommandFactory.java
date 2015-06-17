
package com.verisign.iot.discovery.cli;

import com.verisign.iot.discovery.cli.command.CheckDnsSecCommand;
import com.verisign.iot.discovery.cli.command.ListServiceInstanceCommand;
import com.verisign.iot.discovery.cli.command.ListServiceRecordsCommand;
import com.verisign.iot.discovery.cli.command.ListServiceTypesCommand;
import com.verisign.iot.discovery.cli.command.ListTextRecordCommand;
import com.verisign.iot.discovery.cli.command.ShowHelpCommand;
import com.verisign.iot.discovery.cli.command.TLSARecordCommand;
import com.verisign.iot.discovery.cli.exception.CommandNotFoundException;
import com.verisign.iot.discovery.cli.exception.OptionsNotValidException;
import com.verisign.iot.discovery.cli.parser.Options;
import joptsimple.OptionSet;

/**
 * Define a <code>Command</code> factory. This class implements the factory
 * design pattern to create commands according to a set of options.
 *
 * @author nbrasey <nbrasey@verisign.com>
 * @version 1.0
 * @since 4/30/15.
 */
public class CommandFactory {

	/**
	 * This builds the command.
	 *
	 * @param optionSet     A set of options to be used
	 * 
     * @return The so built <code>Command</code> instance.
	 * 
     * @throws CommandNotFoundException
     *          In case the command cannot be built by the provided options
	 * @throws OptionsNotValidException
     *          In case the provided options are not valid
	 */
	public static Command buildCommand ( OptionSet optionSet ) 
                            throws CommandNotFoundException, OptionsNotValidException 
    {

		String[] argsCommandOption = new String[] {
                                        Options.LIST_SERVICES, Options.LIST_INSTANCES, 
                                        Options.SERVICE_RECORDS, Options.TEXT_RECORD, 
                                        Options.DNS_SEC_STATUS, Options.HELP, Options.TLSA_RECORD
		};

		Command command = null;

		for ( String cmdOption : argsCommandOption ) {

			if ( optionSet.has( cmdOption ) ) {

				// If a command has been created already, we return an error.
				if ( command != null ) {
					throw new OptionsNotValidException( "Conflicting mutually exclusive arguments detected" );
				}

				command = CommandFactory.buildCommand( cmdOption );
			}
		}

		if ( command == null ) {
			throw new OptionsNotValidException( "Missing command" );
		}
        
		return command;
	}

    /**
     * Helper method to parse the option and build the command accordingly.
     * 
     * @param commandOption     A <code>String</code> representing the option
     * 
     * @return      The <code>Command</code> built accordingly
     * 
     * @throws CommandNotFoundException 
     *          In case there is no command corresponding to the provided options
     */
	private static Command buildCommand ( String commandOption ) 
                            throws CommandNotFoundException 
    {
		if ( Options.LIST_SERVICES.equals( commandOption ) ) {
			return new ListServiceTypesCommand();
		}
		else if ( Options.LIST_INSTANCES.equals( commandOption ) ) {
			return new ListServiceInstanceCommand();
		}
		else if ( Options.SERVICE_RECORDS.equals( commandOption ) ) {
			return new ListServiceRecordsCommand();
		}
		else if ( Options.TEXT_RECORD.equals( commandOption ) ) {
			return new ListTextRecordCommand();
		}
		else if ( Options.DNS_SEC_STATUS.equals( commandOption ) ) {
			return new CheckDnsSecCommand();
		}
		else if ( Options.TLSA_RECORD.equals( commandOption ) ) {
			return new TLSARecordCommand();
		}
		else if ( Options.HELP.equals( commandOption ) ) {
			return new ShowHelpCommand();
		}
		else {
			throw new CommandNotFoundException( String.format( "No command registered for the argument: %s", commandOption ) );
		}
	}

}
