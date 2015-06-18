
package com.verisign.iot.discovery.cli.command;

import com.verisign.iot.discovery.cli.ConsoleWriter;
import com.verisign.iot.discovery.cli.common.ExitCodes;
import com.verisign.iot.discovery.cli.exception.ExecutionException;
import com.verisign.iot.discovery.cli.exception.OptionsNotValidException;
import com.verisign.iot.discovery.cli.parser.Options;
import com.verisign.iot.discovery.cli.util.DisplayUtil;
import com.verisign.iot.discovery.cli.util.OptionUtil;
import com.verisign.iot.discovery.commons.StatusCode;
import com.verisign.iot.discovery.domain.Fqdn;
import com.verisign.iot.discovery.exceptions.DnsServiceException;
import com.verisign.iot.discovery.exceptions.LookupException;
import java.util.Set;
import joptsimple.OptionSet;


/**
 * This class defines the listing Service Types command.
 *
 * @author nbrasey <nbrasey@verisign.com>
 * @version 1.0
 * @since 4/30/15.
 */
public class ListServiceTypesCommand extends DnsSdAbstractCommand
{

	private Fqdn domain;


	@Override
	public void initialize ( OptionSet optionSet ) throws ExecutionException, OptionsNotValidException
    {
		super.initialize( optionSet );

		String domainStr = OptionUtil.getOptionValue( optionSet, Options.DOMAIN, true );
        try {
            this.domain = new Fqdn(domainStr);
        } catch(IllegalArgumentException iae) {
            throw new ExecutionException(DisplayUtil.map(StatusCode.ILLEGAL_FQDN),
                                         ExitCodes.INVALID_FQDN.getExitCode());
        }
	}


	@Override
	public void doExecute ( ConsoleWriter consoleWriter )
                    throws DnsServiceException
    {
		Set<String> serviceTypes = null;
        try {
            serviceTypes = this.dnsSd.listServiceTypes( this.domain, !super.insecureMode );
        } catch(LookupException le) {
            throw new DnsServiceException(le.dnsError(),
                                          String.format(DisplayUtil.map(le.dnsError()), domain),
                                          true);
        }
        for ( String serviceType : serviceTypes ) {
			consoleWriter.log( serviceType );
		}
	}

}
