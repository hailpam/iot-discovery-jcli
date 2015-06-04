package com.verisign.iot.discovery.cli.command;

import com.verisign.iot.discovery.cli.ConsoleWriter;
import com.verisign.iot.discovery.cli.exception.OptionsNotValidException;
import com.verisign.iot.discovery.cli.parser.Options;
import com.verisign.iot.discovery.cli.util.OptionUtil;
import com.verisign.iot.discovery.domain.Fqdn;
import com.verisign.iot.discovery.domain.TLSADiscoveryRecord;
import com.verisign.iot.discovery.exceptions.DnsServiceException;
import joptsimple.OptionSet;
import org.xbill.DNS.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tjmurphy on 6/1/15.
 */
public class TLSARecordCommand extends DnsSdAbstractCommand {

	private Fqdn domain;
	private String port;


	@Override
	public void initialize ( OptionSet optionSet ) throws OptionsNotValidException {
		super.initialize( optionSet );

		String domainStr = OptionUtil.getOptionValue( optionSet, Options.DOMAIN, true );
		this.domain = new Fqdn( "", domainStr );
		this.port = OptionUtil.getOptionValue( optionSet, Options.TLSA_RECORD, true );
	}


	@Override
	public void doExecute ( ConsoleWriter consoleWriter ) throws DnsServiceException {
		Set<TLSADiscoveryRecord> records = this.dnsSd.listTLSARecords( this.domain, port, !this.insecureMode );

		for(TLSADiscoveryRecord record: records){
			consoleWriter.log( record.toDisplay() );
		}
	}
}
