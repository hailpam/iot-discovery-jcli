
package com.verisign.iot.discovery.cli.command;

import com.verisign.iot.discovery.cli.ConsoleWriter;
import com.verisign.iot.discovery.cli.exception.OptionsNotValidException;
import com.verisign.iot.discovery.cli.parser.Options;
import com.verisign.iot.discovery.cli.util.OptionUtil;
import com.verisign.iot.discovery.domain.Fqdn;
import com.verisign.iot.discovery.exceptions.DnsServiceException;
import java.util.Set;
import joptsimple.OptionSet;

/**
 * Created by nbrasey on 5/4/15.
 */
public class ListServiceTypesCommand extends DnsSdAbstractCommand {

	private Fqdn domain;


	@Override
	public void initialize ( OptionSet optionSet ) throws OptionsNotValidException {
		super.initialize( optionSet );

		String domainStr = OptionUtil.getOptionValue( optionSet, Options.DOMAIN, true );
		this.domain = new Fqdn(domainStr);
	}


	@Override
	public void doExecute ( ConsoleWriter consoleWriter )
                    throws DnsServiceException {
		Set<String> serviceTypes = this.dnsSd.listServiceTypes( this.domain, !super.insecureMode );
		for ( String serviceType : serviceTypes ) {
			consoleWriter.log( serviceType );
		}
	}
}
