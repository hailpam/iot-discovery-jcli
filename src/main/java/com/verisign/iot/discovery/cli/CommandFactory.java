
package com.verisign.iot.discovery.cli;

import com.verisign.iot.discovery.cli.command.CheckDnsSecCommand;
import com.verisign.iot.discovery.cli.command.ListServiceInstanceCommand;
import com.verisign.iot.discovery.cli.command.ListServiceRecordCommand;
import com.verisign.iot.discovery.cli.command.ListServiceTypesCommand;
import com.verisign.iot.discovery.cli.command.ListTextRecordCommand;
import com.verisign.iot.discovery.cli.command.ShowHelpCommand;
import com.verisign.iot.discovery.cli.exception.OptionsNotValidException;
import com.verisign.iot.discovery.cli.parser.Options;
import com.verisign.iot.discovery.cli.exception.CommandNotFoundException;
import joptsimple.OptionSet;

/**
 * Created by nbrasey on 5/4/15.
 */
public class CommandFactory {

	/**
	 * This builds the command
	 *
	 * @param optionSet
	 * @return the command corresponding to the argument
	 * @throws CommandNotFoundException
	 * @throws OptionsNotValidException
	 */
	public static Command buildCommand ( OptionSet optionSet ) throws CommandNotFoundException, OptionsNotValidException {

		String[] argsCommandOption =
				new String[] {
				Options.LIST_SERVICES, Options.LIST_INSTANCES, Options.SERVICE_RECORD, Options.TEXT_RECORD, Options.DNS_SEC_STATUS,
				Options.HELP
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


	private static Command buildCommand ( String commandOption ) throws CommandNotFoundException {
		if ( Options.LIST_SERVICES.equals( commandOption ) ) {
			return new ListServiceTypesCommand();
		}
		else if ( Options.LIST_INSTANCES.equals( commandOption ) ) {
			return new ListServiceInstanceCommand();
		}
		else if ( Options.SERVICE_RECORD.equals( commandOption ) ) {
			return new ListServiceRecordCommand();
		}
		else if ( Options.TEXT_RECORD.equals( commandOption ) ) {
			return new ListTextRecordCommand();
		}
		else if ( Options.DNS_SEC_STATUS.equals( commandOption ) ) {
			return new CheckDnsSecCommand();
		}
		else if ( Options.HELP.equals( commandOption ) ) {
			return new ShowHelpCommand();
		}
		else {
			throw new CommandNotFoundException( String.format( "No command registered for the argument: %s", commandOption ) );
		}
	}

}
