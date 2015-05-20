
package com.verisign.iot.discovery.cli.command;

import java.util.Set;

import joptsimple.OptionSet;

import com.verisign.iot.discovery.cli.ConsoleWriter;
import com.verisign.iot.discovery.cli.exception.OptionsNotValidException;
import com.verisign.iot.discovery.cli.parser.Options;
import com.verisign.iot.discovery.cli.util.OptionUtil;
import com.verisign.iot.discovery.domain.Fqdn;
import com.verisign.iot.discovery.domain.TextRecord;
import com.verisign.iot.discovery.exceptions.DnsServiceException;

/**
 * Created by nbrasey on 5/4/15.
 */
public class ListTextRecordCommand extends DnsSdAbstractCommand {

	private Fqdn domain;
	private String textRecord;


	@Override
	public void initialize ( OptionSet optionSet ) throws OptionsNotValidException {
		super.initialize( optionSet );

		String domainStr = OptionUtil.getOptionValue( optionSet, Options.DOMAIN, true );
		this.domain = new Fqdn( "", domainStr );
		this.textRecord = optionSet.valueOf( Options.TEXT_RECORD ).toString();
	}


	@Override
	public void doExecute ( ConsoleWriter consoleWriter ) throws DnsServiceException {
		Set<TextRecord> textRecords = this.dnsSd.listTextRecords( this.domain, this.textRecord, !super.insecureMode );
		for ( TextRecord textRecord : textRecords ) {
			consoleWriter.log( textRecord.toString() );
		}
	}
}
