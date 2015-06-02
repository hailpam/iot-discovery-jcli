package com.verisign.iot.discovery.cli.command;

import com.verisign.iot.discovery.cli.ConsoleWriter;
import com.verisign.iot.discovery.cli.exception.OptionsNotValidException;
import com.verisign.iot.discovery.cli.parser.Options;
import com.verisign.iot.discovery.cli.util.OptionUtil;
import com.verisign.iot.discovery.domain.Fqdn;
import com.verisign.iot.discovery.exceptions.DnsServiceException;
import joptsimple.OptionSet;
import org.xbill.DNS.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tjmurphy on 6/1/15.
 */
public class GenericRecordCommand extends DnsSdAbstractCommand {

	private Fqdn domain;


	@Override
	public void initialize ( OptionSet optionSet ) throws OptionsNotValidException {
		super.initialize( optionSet );

		String domainStr = OptionUtil.getOptionValue( optionSet, Options.DOMAIN, true );
		this.domain = new Fqdn( "", domainStr );
	}


	@Override
	public void doExecute ( ConsoleWriter consoleWriter ) throws DnsServiceException {
		TLSARecord tlsaRecord = null;
		TXTRecord txtRecord = null;
		Set<Record> records = new HashSet<>(  );
		try{
			tlsaRecord = new TLSARecord( Name.fromString("iotverisign.com."), DClass.IN, 86400L, 3,1,0, new byte[]{25, 100} );
			txtRecord = new TXTRecord( Name.fromString("iotverisign.com."), DClass.IN, 86400L, "this is from a TXT record" );
			records.add( tlsaRecord );
			records.add( txtRecord );
		}catch ( TextParseException textParseException ){

		}

		for(Record record: records){
			consoleWriter.log( record.rdataToString() );
		}
	}
}
