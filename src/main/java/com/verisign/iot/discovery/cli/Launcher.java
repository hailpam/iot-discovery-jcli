
package com.verisign.iot.discovery.cli;

import com.verisign.iot.discovery.cli.console.DefaultConsoleWriter;
import com.verisign.iot.discovery.cli.exception.CommandNotFoundException;
import com.verisign.iot.discovery.cli.exception.ExecutionException;
import com.verisign.iot.discovery.cli.exception.ExitCodes;
import com.verisign.iot.discovery.cli.exception.OptionsNotValidException;
import com.verisign.iot.discovery.cli.exception.ParsingException;
import com.verisign.iot.discovery.cli.parser.DefaultOptionParser;
import com.verisign.iot.discovery.cli.parser.Options;
import joptsimple.OptionSet;

/**
 * Created by nbrasey on 4/30/15.
 */
public class Launcher {

	public static void main ( String[] args ) {

		ConsoleWriter consoleWriter = new DefaultConsoleWriter();

		CommandOptionParser parser = new DefaultOptionParser();

//        String test[] = { "-d", "com", "-t", "example"};

		try {
			// Parse the arguments
			OptionSet optionSet = parser.parse( args );
//            OptionSet optionSet = parser.parse( test );

			// Build the specific command from the parsed arguments
			Command command = CommandFactory.buildCommand( optionSet );

			// Initialize the command
			command.initialize( optionSet );

			// Execute the command
			command.execute();
		}
		catch ( ParsingException e ) {
			consoleWriter.error("ERROR: Argument parsing error: " + e.getMessage());
			consoleWriter.error(Options.getUsage());
			System.exit(ExitCodes.INVALID_ARGS.getExitCode());
		}
		catch ( CommandNotFoundException e ) {
			consoleWriter.error( "ERROR: Command not found" );
			consoleWriter.error(Options.getUsage());
			System.exit( ExitCodes.INVALID_ARGS.getExitCode() );
		}
		catch ( OptionsNotValidException e ) {
			consoleWriter.error("ERROR: invalid command arguments: " + e.getMessage());
			consoleWriter.error(Options.getUsage());
			System.exit( ExitCodes.INVALID_ARGS.getExitCode() );
		}
		catch ( ExecutionException e ) {
			// Handles specific command exceptions
			consoleWriter.error("ERROR: " + e.getMessage());
			System.exit( e.getExitCode() );
		}
		catch ( RuntimeException e ) {
			// Handles generic exceptions
			consoleWriter.error("ERROR: " + e.getMessage());
			System.exit( ExitCodes.GENERIC_ERROR.getExitCode() );
		}
	}
}
