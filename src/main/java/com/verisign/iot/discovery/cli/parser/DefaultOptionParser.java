
package com.verisign.iot.discovery.cli.parser;

import com.verisign.iot.discovery.cli.CommandOptionParser;
import com.verisign.iot.discovery.cli.exception.ParsingException;
import java.util.Arrays;
import joptsimple.OptionException;
import joptsimple.OptionSet;

/**
 * @author nbrasey
 * @version 1.0 May 05, 2015
 */
public class DefaultOptionParser implements CommandOptionParser 
{

	private static final String JOPTSIMPLE_UNRECOGNIZED_OPTION_EXCEPTION = "joptsimple.UnrecognizedOptionException";
	private static final String LONG_OPTION_ERROR = "unrecognized option '--%s'";
	private static final String SHORT_OPTION_ERROR = "invalid option -- '%s'";

	private joptsimple.OptionParser parser = new joptsimple.OptionParser();


	public DefaultOptionParser () 
    {
		initParserOptions();
	}


	@Override
	public OptionSet parse ( String[] args ) throws ParsingException 
    {
		try {
			return this.parser.parse( args );
		}
		catch ( OptionException e ) {
			throw new ParsingException( extractUsefulMessage( e ) );
		}
		catch ( Exception e ) {
			throw new ParsingException( e );
		}
	}


	// This is a workaround for the bad exceptions handling from jopt
	private String extractUsefulMessage ( OptionException ex ) 
    {

		String className = ex.getClass().getName();

		if ( className.equals( JOPTSIMPLE_UNRECOGNIZED_OPTION_EXCEPTION ) ) {
			String exceptionMessage = ex.getMessage();
			String option = exceptionMessage.substring( 0, exceptionMessage.indexOf( " " ) );
			if ( option.length() > 1 ) {
				return String.format( LONG_OPTION_ERROR, option );
			}
			else {
				return String.format( SHORT_OPTION_ERROR, option );
			}
		}
		else {
			return ex.getMessage();
		}
	}


	private void initParserOptions () 
    {

		// Add the commands
		this.parser.acceptsAll( Arrays.asList( Options.LIST_INSTANCES_S, Options.LIST_INSTANCES ), 
            "list service instances" );
		this.parser.acceptsAll( Arrays.asList( Options.LIST_SERVICES_S, Options.LIST_SERVICES ), 
            "list services" );
		this.parser.acceptsAll( Arrays.asList(Options.SERVICE_RECORDS_S, Options.SERVICE_RECORDS ), 
            "get service record" );
		this.parser.acceptsAll( Arrays.asList( Options.TEXT_RECORD_S, Options.TEXT_RECORD ), 
            "get text record" ).withRequiredArg();
		this.parser.acceptsAll( Arrays.asList( Options.TLSA_RECORD_S, Options.TLSA_RECORD ), 
            "get tlsa records" ).withOptionalArg();
		this.parser.acceptsAll( Arrays.asList( Options.DNS_SEC_STATUS, Options.DNS_SEC_STATUS_S ), 
            "Check DNSSEC status of resolver" ).withOptionalArg()
                                               .ofType( String.class );

		// Add the options
		this.parser.acceptsAll( Arrays.asList( Options.DOMAIN_S, Options.DOMAIN ), 
            "domain name to query [required]" )
				   .requiredIf(Options.LIST_INSTANCES, Options.LIST_SERVICES, Options.SERVICE_RECORDS, 
                               Options.TEXT_RECORD, Options.TLSA_RECORD )
				   .withRequiredArg()
				   .ofType( String.class );
		this.parser.acceptsAll( Arrays.asList( Options.DNS_SERVER_S, Options.DNS_SERVER ), "DNS Server" )
				   .withRequiredArg()
				   .ofType( String.class );
		this.parser.acceptsAll( Arrays.asList(Options.SUPPLEMENT_S, Options.SUPPLEMENT ), "service" )
				   .requiredIf(Options.LIST_INSTANCES, Options.SERVICE_RECORDS)
				   .withRequiredArg()
				   .ofType( String.class );
		this.parser.acceptsAll( Arrays.asList( Options.INSECURE_S, Options.INSECURE ),
				"Do not perform DNSSEC validation of <domain>" );
		this.parser.acceptsAll( Arrays.asList( Options.VERBOSE_S, Options.VERBOSE ), "print verbose messages" );
		this.parser.acceptsAll( Arrays.asList( Options.HELP_S, Options.HELP ), "print the help message" )
                    .forHelp();

		this.parser.acceptsAll( Arrays.asList( Options.TRUST_ANCHOR_S, Options.TRUST_ANCHOR ),
				"specify the file containing trust anchor keys" ).withRequiredArg();

	}
    
}
