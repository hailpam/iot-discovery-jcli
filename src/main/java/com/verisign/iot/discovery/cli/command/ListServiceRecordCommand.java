
package com.verisign.iot.discovery.cli.command;

import com.verisign.iot.discovery.cli.ConsoleWriter;
import com.verisign.iot.discovery.cli.exception.OptionsNotValidException;
import com.verisign.iot.discovery.cli.parser.Options;
import com.verisign.iot.discovery.cli.util.OptionUtil;
import com.verisign.iot.discovery.domain.Fqdn;
import com.verisign.iot.discovery.domain.ServiceRecord;
import com.verisign.iot.discovery.exceptions.DnsServiceException;
import java.util.Set;
import joptsimple.OptionSet;

/**
 * Created by nbrasey on 5/4/15.
 */
public class ListServiceRecordCommand extends DnsSdAbstractCommand {

	private Fqdn domain;
	private String serviceType;


	@Override
	public void initialize ( OptionSet optionSet ) throws OptionsNotValidException {
		super.initialize( optionSet );

		String domainStr = OptionUtil.getOptionValue( optionSet, Options.DOMAIN, true );
		this.domain = new Fqdn(domainStr);

		this.serviceType = OptionUtil.getOptionValue(optionSet, Options.SERVICE, true);
	}


	@Override
	public void doExecute ( ConsoleWriter consoleWriter )
                    throws DnsServiceException {
		Set<ServiceRecord> serviceRecords = this.dnsSd.listServiceRecords( this.domain, this.serviceType, !super.insecureMode );
		for ( ServiceRecord svcRecord : serviceRecords ) {
			consoleWriter.log( svcRecord.toString() );
		}
	}
}
