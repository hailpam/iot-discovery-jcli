
package com.verisign.iot.discovery.cli.command;

import com.verisign.iot.discovery.cli.Command;
import com.verisign.iot.discovery.cli.ConsoleWriter;
import com.verisign.iot.discovery.cli.console.DefaultConsoleWriter;
import com.verisign.iot.discovery.cli.console.LibraryObserver;
import com.verisign.iot.discovery.cli.exception.ExecutionException;
import com.verisign.iot.discovery.cli.exception.ExitCodeMapper;
import com.verisign.iot.discovery.cli.exception.ExitCodes;
import com.verisign.iot.discovery.cli.exception.OptionsNotValidException;
import com.verisign.iot.discovery.cli.parser.Options;
import com.verisign.iot.discovery.cli.util.DisplayUtil;
import com.verisign.iot.discovery.cli.util.EnvironmentUtil;
import com.verisign.iot.discovery.cli.util.OptionUtil;
import com.verisign.iot.discovery.commons.StatusCode;
import com.verisign.iot.discovery.exceptions.DnsServiceException;
import com.verisign.iot.discovery.services.DnsServicesDiscovery;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import joptsimple.OptionSet;

/**
 * Created by nbrasey on 5/4/15.
 */
public abstract class DnsSdAbstractCommand implements Command {

	protected DnsServicesDiscovery dnsSd;
	protected ConsoleWriter consoleWriter;

	protected boolean insecureMode = false;
	protected boolean verboseMode = false;

	protected String dnsServer = null;
	protected String trustAnchorFileLocation = null;


	public DnsSdAbstractCommand () {
	}


	@Override
	public void initialize ( OptionSet optionSet ) throws OptionsNotValidException {

		// Initialize the insecure mode from the arguments or from the environment
		this.insecureMode = this.insecureMode || optionSet.has( Options.INSECURE );
		this.insecureMode = this.insecureMode || EnvironmentUtil.isInsecureEnvironment();

		// Initialize the verbose mode
		this.verboseMode = optionSet.has( Options.VERBOSE );

		// Initialize the DNS Server
		if ( optionSet.has( Options.DNS_SERVER ) ) {
			this.dnsServer = optionSet.valueOf( Options.DNS_SERVER ).toString();
		}

		// Initialize the trust anchor file
		if ( optionSet.has( Options.TRUST_ANCHOR ) ) {
			this.trustAnchorFileLocation = optionSet.valueOf( Options.TRUST_ANCHOR ).toString();
		}

		this.consoleWriter = new DefaultConsoleWriter( verboseMode );

		// Initialize the library
		this.dnsSd = new DnsServicesDiscovery();

		// Configure the trusted file
		if ( this.trustAnchorFileLocation != null ) {
			this.dnsSd.trustAnchorFile( new File( this.trustAnchorFileLocation ) );
		}

		LibraryObserver libraryObserver = new LibraryObserver( this.consoleWriter );

		this.dnsSd.introspected( true );
		this.dnsSd.observer( libraryObserver );

	}


	@Override
	public void execute () throws ExecutionException {

		// Set the DNS server
		if ( this.dnsServer != null && !this.dnsServer.trim().isEmpty() ) {
			try {
				InetAddress inetAddress = InetAddress.getByName( this.dnsServer );
				this.dnsSd.dnsServer( inetAddress );
                if(OptionUtil.checkResolverAddress(dnsServer))
                    throw new UnknownHostException("Invalid Resolver Address");
			}
			catch ( UnknownHostException e ) {
				throw new ExecutionException( String.format( DisplayUtil.INVALID_DNS_HOST,
                        this.dnsServer ),
						ExitCodes.UNKNOWN_DNS_SRV_HOST.getExitCode() );
			}
		}

		try {
			this.dnsSd.checkConfiguration( true );
			// Execute the specific command
			doExecute( this.consoleWriter );
		}
		catch ( DnsServiceException e ) {

			StatusCode statusCode = e.dnsError();

			// Map the library error code to the application exit code
			ExitCodes exitCode = ExitCodeMapper.map( statusCode );

			this.consoleWriter.verbose( e.printableErrorsTrace() );
			throw new ExecutionException( e.getMessage(), exitCode.getExitCode() );
		}
	}


	public abstract void doExecute ( ConsoleWriter consoleWriter )
                            throws DnsServiceException;
}
